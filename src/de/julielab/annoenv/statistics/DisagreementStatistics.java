/** 
 * DisagreementStatistics.java
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
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;

import de.julielab.annoenv.db.annodata.AnnoCore;
import de.julielab.annoenv.db.sql.SQLDatabaseManager;
import de.julielab.annoenv.db.sql.SQLFunctions;

import de.julielab.annoenv.utils.settings.AnnoMasterSettings;

/**
 * Generates gnuplot for the disagreement statistics. Supports two modes: -
 * overall disagreement (avg over sentence dis) - selection disagreement (avg
 * over sentence dis)
 */
public class DisagreementStatistics {

	private AnnoMasterSettings annomasterSettings;

	private File tmpDir;

	private final static int SMALL_STEPSIZE = 1000;

	private final static int LARGE_STEPSIZE = 5000;

	public final static int ALLDIS_TYPE = 1;

	public final static int SELDIS_TYPE = 2;

	SQLDatabaseManager sdm = null;

	private int type = 0;

	public DisagreementStatistics(SQLDatabaseManager sdm) {
		this.sdm = sdm;
		annomasterSettings = new AnnoMasterSettings();
		tmpDir = annomasterSettings.TMP_DIR;
		if (!tmpDir.isDirectory()) {
			throw new RuntimeException("general tmp dir does not exist: "
					+ tmpDir.toString());
		}
		if (!tmpDir.canWrite()) {
			throw new RuntimeException("cannot write to general tmp dir: "
					+ tmpDir.toString());
		}
		tmpDir = new File(tmpDir.toString() + File.separator + "AnnoEnv_"
				+ System.currentTimeMillis());

		if (!tmpDir.mkdir()) {
			throw new RuntimeException(
					"Error: could not create temporaty directory: "
							+ tmpDir.toString());
		}

	}

	/**
	 * preparations: write a gnuplot file
	 */
	private void makePreparations(File plotFile, File gnuFile, File dataFile,
			AnnoCore project) {

		ArrayList<DisValue> disData = (new SQLFunctions(sdm))
				.getDisagreementData(project.getProjectID());

		if (disData == null || disData.size() < 2)
			throw new IllegalArgumentException(
					"could not find enough disagreement values for this project (PID="
							+ project.getProjectID() + ")");

		String plotTitle = "project: " + project.getName();

		StringBuffer template = new StringBuffer();
		template.append("set terminal png medium size 400,280" + "\n");

		template.append("set out \"" + plotFile.toString() + "\"" + "\n");
		template.append("set title '" + plotTitle + "'" + "\n");

		template.append("set xlabel 'K tokens'" + "\n");
		template.append("set ylabel 'disagreement'" + "\n");

		template.append("set yrange [0:] \n");

		int maxTokens = disData.get(disData.size() - 1).tokens;
		int stepsize = 0;
		if (maxTokens > 4 * LARGE_STEPSIZE) {
			stepsize = LARGE_STEPSIZE;
		} else {
			stepsize = SMALL_STEPSIZE;
		}

		template.append("set xtics (");

		for (int i = 0; i <= maxTokens / stepsize; i++) {
			int toks = i * stepsize;
			String end = (i == (maxTokens / stepsize)) ? "" : ", ";
			template.append("\"" + i + "\" " + toks + end);
		}
		template.append(") \n");

		if (this.type == SELDIS_TYPE) { // disagreement of selected sentences

			template.append("plot '" + dataFile.toString()
					+ "' u 3:4 w lp ps 0.1 lw 3 title 'selection dis'");

			template.append(",\\\n '" + dataFile.toString()
					+ "' u 3:6 w lp ps 0.1 lw 3 title 'corpus frac'" + "\n");

		} else { // disagreement of all sentences

			template.append("plot '" + dataFile.toString()
					+ "' u 3:5 w lp ps 0.1 lw 3 title 'overall dis'");

		}

		FileWriter fw = null;
		try {
			fw = new FileWriter(gnuFile);
			fw.write(template.toString());

		} catch (IOException e) {
			throw new RuntimeException("failed creating gnuplot file", e);
		} finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		/*
		 * make the data file
		 */

		if (disData.size() <= 2) {
			throw new IllegalStateException(
					"This project has less than 2 annotated documents set to `done`. Annotate more documents and then retry.");
		}

		try {
			fw = new FileWriter(dataFile);
			for (DisValue dis : disData) {
				fw.write(dis.toString() + "\n");
			}
		} catch (IOException e) {
			throw new RuntimeException("failed writing data file for plot", e);
		} finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * call this function to create the disagreement plot for a project.
	 * 
	 * @param projectID
	 * @param disagreement
	 *            type that should be shown (use ALLDIS_TYPE or SELDIS_TYPE)
	 * @return plot file (here: eps file)
	 * @throws Exception
	 * @throws Exception
	 */
	public File makePlot(int projectID, int type) {

		if (type == ALLDIS_TYPE) {
			this.type = ALLDIS_TYPE;
		} else {
			this.type = SELDIS_TYPE;
		}

		File dataFile = new File(tmpDir.toString() + File.separator
				+ "dis.data");
		File gnuFile = new File(tmpDir.toString() + File.separator + "dis.gnu");

		File plotFile = null;
		int status = 0;
		try {
			// get project
			AnnoCore project = (new SQLFunctions(sdm))
					.getCoreProject(projectID);
			if (!project.isAl()) {
				throw new IllegalStateException(
						"disagreement statistics can only be plotted for AL projects.");
			}

			// make plot file
			plotFile = new File(tmpDir.toString() + File.separator + "dis.png");

			// make the gnuplot and data file
			makePreparations(plotFile, gnuFile, dataFile, project);

			// now execute gnuplot
			String cmd = "gnuplot " + gnuFile.toString();
			Process proc;
			proc = Runtime.getRuntime().exec(cmd);
			proc.waitFor();
			status = proc.exitValue();
		} catch (Exception e) {
			if (e instanceof RuntimeException)
				throw (RuntimeException) e;
			else
				throw new RuntimeException(
						"error making plot for disagreement statistics", e);
		}

		if (status != 0)
			throw new RuntimeException(
					"Error: gnuplot could not compile gnuplot file. You might try this manually, see file: "
							+ gnuFile.toString());
		return plotFile;

	}

	/**
	 * just a for debugging and testing
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			SQLDatabaseManager sdm = new SQLDatabaseManager();
			sdm.getConnection();
			DisagreementStatistics D = new DisagreementStatistics(sdm);
			File f = D.makePlot(160, SELDIS_TYPE);
			System.out.println("gnuplot file written to: " + f.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
