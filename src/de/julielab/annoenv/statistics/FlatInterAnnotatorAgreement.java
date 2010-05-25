/** 
 * FlatInterAnnotatorAgreement.java
 * 
 * Copyright (c) 2007, JULIE Lab. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0 
 *
 * Author: tomanek
 * 
 * Current version: 2.0	
 * Since version:   0.7
 *
 * Creation date: Aug 01, 2006
 * 
 * 
 */

package de.julielab.annoenv.statistics;

import java.io.File;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;


import org.w3c.dom.Document;

import de.julielab.annoenv.conversions.MmaxDataConverter;
import de.julielab.annoenv.db.annodata.AnnoFull;
import de.julielab.annoenv.db.annodata.BaseDataFull;
import de.julielab.annoenv.db.annodata.Markable;
import de.julielab.annoenv.db.annodata.MmaxData;
import de.julielab.annoenv.db.sql.SQLDatabaseManager;
import de.julielab.annoenv.db.sql.SQLFunctions;

import de.julielab.annoenv.utils.DeleteDir;
import de.julielab.annoenv.utils.IOUtils;
import de.julielab.annoenv.utils.settings.AnnoMasterSettings;

public class FlatInterAnnotatorAgreement {

	final private AnnoMasterSettings annomasterSettings;

	final private ArrayList<String> consideredBDs; // uri of BD considered for
													// evaluation

	final private ArrayList<String> missedBDs; // uri of BD missed (though
												// done) due to BD edit

	private boolean calculatedIAA; // when IAA has been calculated this is set
									// to true and the getter methods can be
									// used

	private double finalRecall, finalPrecision, finalFscore;

	private int finalNumcorr, finalNumans, finalNumref;

	final private AnnoFull goldProject;

	final private AnnoFull evalProject;

	private File tmpDir;

	/**
	 * Calculates IOB/F-measure based IAA ("flat")
	 */
	public FlatInterAnnotatorAgreement(AnnoFull goldProject,
			AnnoFull evalProject) {

		annomasterSettings = new AnnoMasterSettings();
		tmpDir = annomasterSettings.TMP_DIR;

		this.goldProject = goldProject;
		this.evalProject = evalProject;

		if (!tmpDir.isDirectory())
			throw new RuntimeException("Error: general tmp dir does not exist: "
					+ tmpDir.toString());

		if (!tmpDir.canWrite())
			throw new RuntimeException("Error: cannot write to general tmp dir: "
					+ tmpDir.toString());

		tmpDir = new File(tmpDir.toString() + File.separator + "AnnoEnv_"
				+ System.currentTimeMillis());

		if (!tmpDir.mkdir())
			throw new RuntimeException(
					"Error: could not create temporary directory: "
							+ tmpDir.toString());

		consideredBDs = new ArrayList<String>();
		missedBDs = new ArrayList<String>();
	}

	public void calculateIAA(SQLDatabaseManager sdm) {


		// get priolists
		List<String> priolist = goldProject.getPriolist(sdm);

		// get schema file
		File schemaFile = null;
		ArrayList<MmaxData> mmaxdata = goldProject.getMmaxdata();
		if (mmaxdata.size() != 2)
			throw new IllegalStateException(
					"Error: project does not have exactly two schemas.");
		for (MmaxData m : mmaxdata) {
			if (m.isMainLevel()) {
				schemaFile = new File(tmpDir.toString() + File.separator
						+ m.getLevelName() + ".xml");
				m.writeSchemaFile(schemaFile.toString(), sdm);
			}
		}
		if (schemaFile == null)
			throw new IllegalStateException(
					"main schema file not found or downloaded.");

		/*
		 * now get the basedata etc
		 */

		final HashMap<String, BaseDataFull> goldBDids = new HashMap<String, BaseDataFull>();
		final HashMap<String, BaseDataFull> evalBDids = new HashMap<String, BaseDataFull>();

		final TreeSet<String> BDids = new TreeSet<String>(); // the bds to be
																// considered

		// only basedata marked as "done"
		for (BaseDataFull bd : goldProject.getBaseDataFull()) {
			if (bd.getStatus().equals("done"))
				goldBDids.put(bd.getUri(), bd);
		}

		// only basedata marked as "done"
		for (BaseDataFull bd : evalProject.getBaseDataFull()) {
			if (bd.getStatus().equals("done"))
				evalBDids.put(bd.getUri(), bd);
		}

		/*
		 * Schnitt berechnen
		 */

		// System.out.println("gold IDs: " + goldBDids);
		// System.out.println("eval IDs: " + evalBDids);
		for (String uri : goldBDids.keySet()) {
			if (evalBDids.containsKey(uri))
				BDids.add(uri);
		}

		for (String uri : evalBDids.keySet()) {
			if (goldBDids.containsKey(uri))
				BDids.add(uri);
		}

		// System.out.println("IDs: " + BDids);

		// now loop over all selected basedata

		int numans = 0;
		int numcorr = 0;
		int numref = 0;

		// old: when IOBExport produced IOB we had to use the IOB Evaluator
		// IOBEvaluator evaluator = new IOBEvaluator();
		// as we now exort only IO with IOBExport, we need the IOEvaluator!
		IOEvaluator evaluator = new IOEvaluator();

		for (String uri : BDids) {

			// basedata
			BaseDataFull goldBD = goldBDids.get(uri);
			Document goldBDDoc = goldBD.getBasedataDoc(sdm);

			BaseDataFull evalBD = evalBDids.get(uri);
			Document evalBDDoc = evalBD.getBasedataDoc(sdm);

			// markables gold
			ArrayList<Markable> goldMarkables = goldBD.getMarkables();

			Document goldmainmarkableDoc = null;
			Document goldsentmarkableDoc = null;
			// FIXME: There can be more than 2 Markables,
			// i.e. in project Final_Genregulation!
			for (Markable m : goldMarkables) {
				if (m.isMainLevel())
					goldmainmarkableDoc = m.getMarkableDoc(sdm);
				else
					goldsentmarkableDoc = m.getMarkableDoc(sdm);
			}

			boolean bdOK = goldBDDoc.equals(evalBDDoc);
			// System.out.println(uri + ":" + bdOK);

			if (bdOK) { // if basedata is the same
				consideredBDs.add(uri);

				// markables eval
				ArrayList<Markable> evalMarkables = evalBD.getMarkables();

				Document evalmainmarkableDoc = null;
				Document evalsentmarkableDoc = null;
				for (Markable m : evalMarkables) {
					if (m.isMainLevel())
						evalmainmarkableDoc = m.getMarkableDoc(sdm);
					else
						evalsentmarkableDoc = m.getMarkableDoc(sdm);
				}

				/*
				 * convert it to IOB
				 */

				File goldiobFile = new File(tmpDir.toString() + File.separator
						+ uri + ".gold.iob");
				File evaliobFile = new File(tmpDir.toString() + File.separator
						+ uri + ".eval.iob");

				// get needed dtd data
				// SQLFunctions sf = new SQLFunctions(sdm);
				// File markablesdtdFile = new File(tmpDir + File.separator +
				// "markables.dtd");
				// sf.writeMarkableDTDFile(markablesdtdFile);
				// File wordsdtdFile = new File(tmpDir + File.separator +
				// "words.dtd");
				// sf.writeWordDTDFile(wordsdtdFile);

				MmaxDataConverter converter = new MmaxDataConverter();
				// switch here if we want to use I and B tags for IOB export as
				// well
				boolean addIOBLabels = false;
				converter.convertToIOB(goldiobFile, goldmainmarkableDoc,
						goldBDDoc, priolist, goldsentmarkableDoc, addIOBLabels,
						uri);
				converter.convertToIOB(evaliobFile, evalmainmarkableDoc,
						evalBDDoc, priolist, evalsentmarkableDoc, addIOBLabels,
						uri);

				/*
				 * calculate values
				 */
				int[] values = evaluator.getValues(IOUtils
						.readFile(goldiobFile), IOUtils.readFile(evaliobFile));
				numcorr += values[0];
				numans += values[1];
				numref += values[2];

			} else { // basedata different
				missedBDs.add(uri);
			}

		}

		/*
		 * calculate overall R/P/F values
		 */

		double[] eval = evaluator.evaluate(numcorr, numans, numref);
		// System.out.println("Overall R/P/F: " + eval[0] + File.separator +
		// eval[1] + File.separator + eval[2]);

		/*
		 * set values (results) so that getter methods can read them
		 */

		finalNumcorr = numcorr;
		finalNumref = numref;
		finalNumans = numans;
		finalRecall = eval[0];
		finalPrecision = eval[1];
		finalFscore = eval[2];

		/*
		 * clean up
		 */
		DeleteDir.deleteDirectory(tmpDir);

		/*
		 * now the getter methods are available
		 */
		calculatedIAA = true;
	}

	/*
	 * getter methods, which show the results
	 */

	public double getRecall() {
		if (calculatedIAA) {
			return finalRecall;
		}
		throw new RuntimeException("flat IAA calculation has to be run before "
				+ "results can be read with getter methods");
	}

	public double getPrecision() {
		if (calculatedIAA) {
			return finalPrecision;
		}
		throw new RuntimeException("flat IAA calculation has to be run before "
				+ "results can be read with getter methods");
	}

	public double getFscore() {
		if (calculatedIAA) {
			return finalFscore;
		}
		throw new RuntimeException("flat IAA calculation has to be run before "
				+ "results can be read with getter methods");
	}

	public int getNumcorr() {
		if (calculatedIAA) {
			return finalNumcorr;
		}
		throw new RuntimeException("flat IAA calculation has to be run before "
				+ "results can be read with getter methods");
	}

	public int getNumans() {
		if (calculatedIAA) {
			return finalNumans;
		}
		throw new RuntimeException("flat IAA calculation has to be run before "
				+ "results can be read with getter methods");
	}

	public int getNumref() {
		if (calculatedIAA) {
			return finalNumref;
		}
		throw new RuntimeException("flat IAA calculation has to be run before "
				+ "results can be read with getter methods");
	}

	public ArrayList<String> getMissedBD() {
		if (calculatedIAA) {
			return missedBDs;
		}
		throw new RuntimeException("flat IAA calculation has to be run before "
				+ "results can be read with getter methods");
	}

	public ArrayList<String> getConsideredBD() {
		if (calculatedIAA) {
			return consideredBDs;
		}

		throw new RuntimeException("flat IAA calculation has to be run before "
				+ "results can be read with getter methods");
	}

	/**
	 * for debugging purposes
	 * 
	 * @param args
	 *            not used
	 */
	public static void main(String[] args) {
		SQLDatabaseManager sdm;
		try {
			sdm = new SQLDatabaseManager();

			sdm.setConnection();
			SQLFunctions sf = new SQLFunctions(sdm);

			AnnoFull p1 = sf.getFullProject(63);
			AnnoFull p2 = sf.getFullProject(164);
			FlatInterAnnotatorAgreement IAA = new FlatInterAnnotatorAgreement(
					p1, p2);

			IAA.calculateIAA(sdm);
			System.out.println("considered BD: " + IAA.getConsideredBD());
			System.out.println("missed BD (due to inconsisten BD): "
					+ IAA.missedBDs);
			System.out.println("R: " + IAA.getRecall());
			System.out.println("P: " + IAA.getPrecision());
			System.out.println("F: " + IAA.getFscore());
			System.out.println("numcorr: " + IAA.getNumcorr());
			System.out.println("numref : " + IAA.getNumref());
			System.out.println("numans : " + IAA.getNumans());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
