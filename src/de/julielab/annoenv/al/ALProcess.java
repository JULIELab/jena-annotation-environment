/** 
 * ALProcess.java
 * 
 * Copyright (c) 2007, JULIE Lab. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0 
 *
 * Author: tomanek
 * 
 * Current version: 2.0
 * Since version:   0.1
 *
 * Creation date: Aug 01, 2006 
 * 
 *   
 */

package de.julielab.annoenv.al;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.julielab.al_libs.exceptions.ALLibsException;
import de.julielab.al_libs.exceptions.ConfigurationException;
import de.julielab.al_libs.selection.Prediction;
import de.julielab.al_libs.selection.SelectionStrategies;
import de.julielab.al_libs.selection.Training;
import de.julielab.al_libs.selection.VoteEntropy;
import de.julielab.al_libs.types.InstanceGeneration;
import de.julielab.al_libs.types.Sentence;
import de.julielab.al_libs.types.Tags;
import de.julielab.al_libs.types.TokenDummyPipe;
import de.julielab.al_libs.utils.IOUtils;
import de.julielab.al_libs.utils.Settings;
import de.julielab.annoenv.db.sql.SQLDatabaseManager;
import de.julielab.annoenv.db.sql.SQLFunctions;
import de.julielab.annoenv.utils.AnnoEnvLogger;
import de.julielab.annoenv.utils.settings.DatabaseSettings;
import edu.umass.cs.mallet.base.classify.Classifier;
import edu.umass.cs.mallet.base.fst.Transducer;
import edu.umass.cs.mallet.base.pipe.Pipe;
import edu.umass.cs.mallet.base.pipe.SerialPipes;
import edu.umass.cs.mallet.base.types.Instance;
import edu.umass.cs.mallet.base.types.InstanceList;



/**
 * This class performs AL! It uses the entityAL-Libs. It is run via ssh (on a
 * remote machine).
 */
public class ALProcess {

	private static Logger logger = AnnoEnvLogger
			.getLogger("de.julielab.annoenv.al.ALProcess");

	private final int NUMBER_MODELS = 3;

	private int traintime = 0;

	private int traindatatime = 0;

	private int testtime = 0;

	private int testdatatime = 0;

	private int seltime = 0;

	private long startSelection = 0;

	private long endSelection = 0;

	public static void main(String[] args) {

		if (args.length != 1) {
			logger
					.severe("#AL settings file is missing... AL cannot be started.");
			System.exit(-1);
		}

		File settingsFile = new File(args[0]);
		Settings settings = null;

		try {
			settings = new Settings(settingsFile);
		} catch (ConfigurationException e) {
			logger.log(Level.SEVERE,
					"#AL settings file incorrect... AL cannot be started. ", e);
			System.exit(-1);
		}

		ALProcess AL = new ALProcess();
		AL.doActiveLearning(settings);

	}

	public ALProcess() {
		// test database settings (if these are not OK, we must not start AL!)
		new DatabaseSettings();
	}

	/**
	 * this runs the actual AL process therefore methods and functions of the
	 * package de.julielab.al-libs are used
	 */
	public ArrayList doActiveLearning(Settings settings) {
		startSelection = System.currentTimeMillis();
		int PID = settings.PROJECT_ID;
		Tags tags = settings.TAGS;
		try {

			// make training data
			logger.info(PID
					+ "#AL process: reading previously annotated data...");
			long t1 = System.currentTimeMillis();
			ArrayList<Sentence> aT = IOUtils.readCorpus(settings.ATFILE, tags);
			InstanceList trainingData = InstanceGeneration
					.makeSentenceInstances(aT, tags);

			// we set the logger again. This is because it gets overwritten by
			// MALLET's logging settings (MALLET functionalities are used in the
			// previous method...)
			logger = AnnoEnvLogger
					.getLogger("de.julielab.annoenv.al.ALProcess");

			Pipe p1 = trainingData.getPipe();
			Pipe dummy_P = new SerialPipes(new Pipe[] { new TokenDummyPipe(
					trainingData.getDataAlphabet(), trainingData
							.getTargetAlphabet()), });
			long t2 = System.currentTimeMillis();
			traindatatime = (int) ((t2 - t1) / 1000);

			// train on (fraction of) aT
			logger.info(PID + "#AL process: performing training...");
			Object[] models = trainModels(settings, trainingData, p1, dummy_P);

			// read aC
			logger.info(PID + "#AL process: reading binary corpus (subsampling fraction: " + settings.SAMPLINGFRAC + ") ...");
			t1 = System.currentTimeMillis();
			ArrayList<Sentence> aC = IOUtils.readBinCorpus(settings.ACFILE,
					settings.ATFILE, (float) settings.SAMPLINGFRAC, tags);
			t2 = System.currentTimeMillis();
			logger.info(PID + "#AL process: reading bincorpus took: "
					+ (t2 - t1) / 1000 + " secs");
			logger.info(PID
					+ "#AL process: number of sentences read from bincorpus: "
					+ aC.size());

			// make instances for aC
			logger.info(PID
					+ "#AL process: making instances for prediction ...");
			InstanceList testingData = makeTestInstances(p1, aC);

			// predict instances, estimate disagreement, write selection
			logger.info(PID
					+ "#AL process: selecting sentences for annotation...");
			ArrayList<Sentence> sel = selectSentences(settings, PID, aT, p1,
					dummy_P, models, aC, testingData);
			writeSelection(settings.SELFILE, sel);

			logger.info(PID + "#AL process: finished successfully. " + sel.size() + " sentences selected.");

			return sel;

		} catch (Exception e) {
			logger.log(Level.SEVERE, PID + "#AL process failed", e);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * select sentences: predict on testing data, calculate sentence-level vote
	 * entropy, add some statistics to DB
	 */
	private ArrayList<Sentence> selectSentences(Settings settings, int PID,
			ArrayList<Sentence> aT, Pipe p1, Pipe dummy_P, Object[] models,
			ArrayList<Sentence> aC, InstanceList testingData)
			throws ALLibsException {

		VoteEntropy VE = new VoteEntropy(settings.TAGS);

		float disSumAll = 0;
		int sentWithNoDis = 0;

		long s1 = System.currentTimeMillis();
		for (int i = 0; i < testingData.size(); i++) {
			Instance inst = (Instance) testingData.get(i);
			Sentence sent = aC.get(i);
			ArrayList[] labels = new ArrayList[NUMBER_MODELS];
			for (int j = 0; j < NUMBER_MODELS; j++) {
				if (settings.CLASSIFIERS.contains(settings.COMMITTEE[j])) {
					labels[j] = Prediction.predictClassifier(p1, dummy_P,
							(Classifier) models[j], sent, inst);
				} else if (settings.TRANSDUCERS
						.contains((settings.COMMITTEE[j]))) {
					labels[j] = Prediction.predictTransducer(p1,
							(Transducer) models[j], inst);
				} else {
					throw new ALLibsException("unknown transducer type: "
							+ settings.COMMITTEE[j]);
				}
			}

			sent.dis = VE.calculateSentenceVE(labels[0], labels[1], labels[2]);

			disSumAll += sent.dis;
			if (sent.dis == 0) {
				sentWithNoDis++;
			}
		}

		long s2 = System.currentTimeMillis();
		testtime = (int) ((s2 - s1) / 1000);

		// do selection
		ArrayList<Sentence> sel = SelectionStrategies.doBatchSelection(aC,
				settings.BATCHSIZE, settings.MINSENTLEN);

		logger.info(PID + "#AL process: number of skipped sentences: "
				+ SelectionStrategies.getOmittedSentences());

		// get some statistics
		int sents = aT.size();
		int toks = 0;
		for (Sentence sent : aT) {
			toks += sent.getNrTokens();
		}
		float noDis = sentWithNoDis / (float) testingData.size();

		float avgDisAll = 0; // averaga disagreement over all sentences of
		// pool-subsample
		double avgDisSel = 0; // average disagreement over selected sentences
		for (Sentence sent : sel) {
			avgDisSel += sent.dis;
		}
		if (settings.BATCHSIZE > 0)
			avgDisSel = avgDisSel / settings.BATCHSIZE;

		if (testingData.size() > 0)
			avgDisAll = disSumAll / testingData.size();

		// put statistics in AL log
		logger.info(PID + "#AL process: average overall disagreement: "
				+ avgDisAll);

		// write statistics to DB
		endSelection = System.currentTimeMillis();
		seltime = (int) ((endSelection - startSelection) / 1000);
		writeStatistics(PID, sents, toks, noDis, avgDisAll, avgDisSel);

		return sel;
	}

	private void writeStatistics(int PID, int sents, int toks, float noDis,
			float avgDisAll, double avgDisSel) {
		// write statistics to DB
		try {
			SQLDatabaseManager sdm = new SQLDatabaseManager();
			sdm.setConnection();
			(new SQLFunctions(sdm)).addALStatistics(PID, avgDisSel, avgDisAll,
					noDis, sents, toks, traintime, traindatatime, testtime,
					testdatatime, seltime);
		} catch (Exception e) {
			logger.log(Level.SEVERE, PID
					+ "#AL process: error writing statistics to DB.", e);
			e.printStackTrace();
		}
	}

	/**
	 * create instances for prediction
	 */
	private InstanceList makeTestInstances(Pipe p1, ArrayList<Sentence> aC) {
		long s1 = System.currentTimeMillis();

		InstanceList testingData = new InstanceList(p1);
		for (int i = 0; i < aC.size(); i++) {
			Sentence sent = (Sentence) aC.get(i);
			Instance inst = new Instance(sent.sentence, "", "", "", p1);
			testingData.add(inst);
		}
		long s2 = System.currentTimeMillis();
		testdatatime = (int) ((s2 - s1) / 1000);

		return testingData;
	}

	/**
	 * train the models (classifiers or transducers) for prediction
	 */
	private Object[] trainModels(Settings settings, InstanceList trainingData,
			Pipe p1, Pipe dummy_P) throws ALLibsException {
		long s1 = System.currentTimeMillis();

		Object[] models = new Object[NUMBER_MODELS];

		for (int i = 0; i < NUMBER_MODELS; i++) {
			long seed = System.currentTimeMillis() * 1000;

			InstanceList modelTrainingData;
			if (settings.TRAINPROP[0] == 1) {
				// train on all data
				modelTrainingData = trainingData;
			} else {
				// train on fraction (first value of TRAINPROP)
				modelTrainingData = (trainingData.split(new Random(seed),
						settings.TRAINPROP))[0];
			}

			if (settings.CLASSIFIERS.contains((settings.COMMITTEE[i]))) {
				models[i] = Training.trainClassifier(settings.COMMITTEE[i],
						dummy_P, modelTrainingData);
			} else if (settings.TRANSDUCERS.contains(settings.COMMITTEE[i])) {
				models[i] = Training.trainTransducer(settings.COMMITTEE[i], p1,
						modelTrainingData);
			} else {
				throw new ALLibsException("unknown transducer type: "
						+ settings.COMMITTEE[i]);
			}
		}
		long s2 = System.currentTimeMillis();
		traintime = (int) ((s2 - s1) / 1000);

		return models;
	}

	/**
	 * writes the selected sentences into a file
	 * 
	 * @param selfile
	 *            the file to write to
	 * @param sel
	 *            the selected sentences as ArrayList of Sentence objects
	 */
	private void writeSelection(File selfile, ArrayList<Sentence> sel)
			throws ALLibsException {
		FileWriter f;
		try {
			f = new FileWriter(selfile);
			for (int i = 0; i < sel.size(); i++) {
				String line = sel.get(i).tid + "\t" + sel.get(i).sid;
				f.write(line + "\n");
			}
			f.close();
		} catch (IOException e) {
			throw new ALLibsException(
					"Could not write file with selected sentences ("
							+ selfile.getAbsolutePath() + "): "
							+ e.getMessage());
		}

	}

	/**
	 * @deprecated
	 * just for debugging
	 */
	private void showLogger() {
		System.out.println("name: " + logger.getName());
		System.out.println("handler: " + logger.getHandlers().length);
		Handler[] h = logger.getHandlers();
		for (int i = 0; i < h.length; i++) {
			System.out.println(h[i]);
		}
		System.out.println(logger.toString());
		System.out.println(logger.getParent());
	}

}
