/** 
 * ConvertIOB2T2.java
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
 * Creation date: Feb 13, 2007 
 * 
 **/

package de.julielab.annoenv.conversions;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;



import opennlp.tools.lang.english.PosTagger;
import opennlp.tools.postag.POSDictionary;

import de.julielab.annoenv.db.annodata.AnnoFull;
import de.julielab.annoenv.db.sql.SQLDatabaseManager;
import de.julielab.annoenv.db.sql.SQLFunctions;
import de.julielab.annoenv.utils.Constants;
import de.julielab.annoenv.utils.FlexibleFilenameFilter;
import de.julielab.annoenv.utils.IOUtils;
import de.julielab.annoenv.utils.settings.AnnoMasterSettings;

/**
 * Exports all annotations into one file in the t2-format (i.e. each sentence is
 * preceeded by a header "SENTENCE SID TID LEN", the sentence is in IOB format
 * with each token in a separate line, whitespace, POS Tag, whitespace, label.
 * There is an empty line between the sentences and at the end of the file there
 * are exactly two empty lines).
 * 
 * The T2-File can be used for AL projects.
 */
public class ConvertIOB2T2 {

	private AnnoFull project;

	private File outDir;

	public ConvertIOB2T2(AnnoFull project, File outDir) {
		this.project = project;
		this.outDir = outDir;
	}

	/**
	 * the method that does all
	 */
	public void run(SQLFunctions sf) {

		AnnoMasterSettings annomasterSettings = new AnnoMasterSettings();

		ArrayList<String> t2Data = new ArrayList<String>();

		// get IOBs
		(new ExportIOB(project, outDir)).run(sf);
		File[] iobFiles = outDir.listFiles(new FlexibleFilenameFilter("iob"));

		// get the POS tagger (openNLP with model trained on PennBio)
		String posModel = annomasterSettings.POS_DIR + Constants.POS_MODEL;
		String posDict = annomasterSettings.POS_DIR + Constants.POS_DICTIONARY;
		System.out.println("using POS model and tagdict: " + posModel + " and "
				+ posDict);
		PosTagger posTagger;
		try {
			posTagger = new PosTagger(posModel, new POSDictionary(posDict));
		} catch (IOException e) {
			throw new RuntimeException(
					"could not convert IOB to T2 due to problems with POS dictionary.",
					e);
		}

		// now go through all iob files
		for (File iobFile : iobFiles) {

			// get info for header
			String iob = iobFile.getName();
			String tid = iob.substring(0, iob.indexOf('.'));
			int sid = 0;

			// add sentence header
			t2Data.add("SENTENCE " + tid + " " + sid + " 0");

			// get the data in this iob file
			ArrayList<String> iobContent = IOUtils.readFile(iobFile);

			ArrayList<String> tokens = new ArrayList<String>();
			ArrayList<String> labels = new ArrayList<String>();

			for (int j = 0; j < iobContent.size(); j++) {
				String line = iobContent.get(j);
				if (line.equals("")) { // sentence end
					sid++;

					// get the POS tags for all tokens
					List pos = posTagger.tag(tokens);
					if (labels.size() != tokens.size()
							|| tokens.size() != pos.size()) {
						throw new IllegalStateException(
								"Error converting to T2 format. Number or labels, tokens and pos tags wrong.");
					}

					for (int k = 0; k < tokens.size(); k++) {
						t2Data.add(tokens.get(k) + " " + pos.get(k) + " "
								+ labels.get(k));
					}
					t2Data.add("");

					// add the new sentence
					t2Data.add("SENTENCE " + tid + " " + sid + " 0");

					tokens.clear();
					labels.clear();
				} else { // inside a sentence

					// add token and label
					String[] values = line.split("\t");
					tokens.add(values[0]);
					labels.add(values[1]);
				}
			}

			// last sentence handled separately
			// get the POS tags for all tokens
			List pos = posTagger.tag(tokens);
			if (labels.size() != tokens.size() || tokens.size() != pos.size()) {
				throw new IllegalStateException(
						"Error converting to T2 format. Number or labels, tokens and pos tags wrong.");
			}

			for (int k = 0; k < tokens.size(); k++) {
				t2Data.add(tokens.get(k) + " " + pos.get(k) + " "
						+ labels.get(k));
			}

			t2Data.add("");
		}

		// remove last line and add an additional empty line
		t2Data.remove(t2Data.size() - 1);
		t2Data.add("");

		// write t2Data to disk
		String outFilename;
		try {
			outFilename = outDir.getCanonicalPath() + File.separator
					+ "data.t2";
			IOUtils.writeFile(t2Data, new File(outFilename));
		} catch (IOException e) {
			throw new RuntimeException("error writing t2 file", e);
		}
	}

	/**
	 * main method for debugging
	 */
	public static void main(String[] args) throws Exception {

		if (args.length != 2) {
			System.out
					.println("usage: ConvertIOB2T2 <project-id> <output-dir>");
			System.exit(-1);
		}

		int projectID = (new Integer(args[0])).intValue();
		File outputDir = new File(args[1]);
		if (!outputDir.isDirectory()) {
			System.out.println("Err: output dir " + outputDir.toString()
					+ " does not exist.");
			System.exit(-1);
		}

		SQLDatabaseManager sdm = new SQLDatabaseManager();
		sdm.setConnection();
		SQLFunctions sf = new SQLFunctions(sdm);
		AnnoFull project = sf.getFullProject(projectID);
		System.out.println("using PID: " + project.toString());

		ConvertIOB2T2 e = new ConvertIOB2T2(project, outputDir);
		e.run(sf);
	}
}
