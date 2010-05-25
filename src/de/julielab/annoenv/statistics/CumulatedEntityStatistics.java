/** 
 * CumulatedEntityStatistics.java
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
 * Creation date: Jan 31, 2007 
 * 
 **/

package de.julielab.annoenv.statistics;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.TreeSet;

import de.julielab.annoenv.conversions.ExportIOB;
import de.julielab.annoenv.db.annodata.AnnoFull;
import de.julielab.annoenv.db.sql.SQLDatabaseManager;
import de.julielab.annoenv.db.sql.SQLFunctions;

import de.julielab.annoenv.utils.Constants;
import de.julielab.annoenv.utils.FlexibleFilenameFilter;
import de.julielab.annoenv.utils.IOUtils;
import de.julielab.annoenv.utils.settings.AnnoMasterSettings;

/**
 * Class to make a gnuplot of cumulated and distinct entity mentions goal: see
 * how well AL works in selecting sentences with new (i.e. distinct) entity
 * mentions.
 * 
 * Currently, only IO (not IOB) is handled !
 * 
 */
public class CumulatedEntityStatistics {

	private AnnoMasterSettings annomasterSettings = null;

	private File tmpDir = null;

	private SQLFunctions sf = null;

	private AnnoFull project = null;

	public CumulatedEntityStatistics(int projectID, SQLDatabaseManager sdm) {
		sf = new SQLFunctions(sdm);
		project = sf.getFullProject(projectID);
		annomasterSettings = new AnnoMasterSettings();
	}

	/**
	 * wrapper for main makePlot method, which reads evalStepSize from project
	 * data
	 * 
	 * @return
	 * @throws Exception
	 */
	public File makePlot() {
		int evalStepSize = 1;
		if (project.getMode().equals("AL")) {
			evalStepSize = project.getAlBatchsize();
		}
		return makePlot(evalStepSize);
	}

	/**
	 * main method to generate plot using gnuplot (called as system call)
	 */
	public File makePlot(int evalStepSize) {
		File plotFile = null;
		try {
			long timestamp = System.currentTimeMillis();
			String dirString = annomasterSettings.TMP_DIR.getAbsolutePath()
					+ File.separator + "AnnoEnv_"+ timestamp;
			tmpDir = new File(dirString);
			if (!tmpDir.mkdir())
				throw new RuntimeException("could not create directory: "
						+ dirString);

			// export annotated basedata to iob
			ExportIOB exportIOB = new ExportIOB(project, tmpDir);
			exportIOB.run(sf);

			// read in all iob
			ArrayList<String> allSentences = new ArrayList<String>();

			String[] iobFiles = tmpDir.list(new FlexibleFilenameFilter("iob"));
			for (int i = 0; i < iobFiles.length; i++) {
				File iobFile = new File(tmpDir.getAbsolutePath()
						+ File.separator + iobFiles[i].toString());
				ArrayList<String> currSentences = IOUtils.readFile(iobFile);
				allSentences.addAll(currSentences);
			}

			// get entities in sentences
			ArrayList<ArrayList<String>> chunks = getChunks(allSentences);

			// count
			ArrayList<String> distEntities = countDistinctEntities(chunks,
					evalStepSize);

			// now write data to disk
			File dataFile = new File(tmpDir.getAbsolutePath() + File.separator
					+ "entities.data");

			FileWriter fw = new FileWriter(dataFile);
			for (int i = 0; i < distEntities.size(); i++)
				fw.write(distEntities.get(i).toString() + "\n");
			fw.close();

			plotFile = new File(tmpDir.getAbsolutePath() + File.separator
					+ "entities.png");
			File gnuFile = new File(tmpDir.getAbsolutePath() + File.separator
					+ "entities.gnu");

			StringBuffer template = makePlotTemplate(dataFile, plotFile);
			fw = new FileWriter(gnuFile);
			fw.write(template.toString());
			fw.close();

			// now execute gnuplot
			String cmd = "gnuplot " + gnuFile.toString();
			Process proc = Runtime.getRuntime().exec(cmd);
			proc.waitFor();
			int status = proc.exitValue();
			if (status != 0) {
				throw new RuntimeException(
						"Error: gnuplot could not compile gnuplot file. You might try this manually on file: "
								+ gnuFile.toString());
			}

		} catch (Exception e) {
			throw new RuntimeException("making plot for cumulated entity statistics failed!", e);
		}
		return plotFile;
	}

	/**
	 * generate gnuplot template
	 */
	private StringBuffer makePlotTemplate(File dataFile, File plotFile) {

		// get project
		String plotTitle = "project: " + project.getName();

		StringBuffer template = new StringBuffer();
		template.append("set terminal png medium size 400,280" + "\n");

		template.append("set out \"" + plotFile.toString() + "\"" + "\n");
		template.append("set title '" + plotTitle + "'" + "\n");

		template.append("set xlabel 'sentences'" + "\n");
		template.append("set ylabel 'distinct entity mentions'" + "\n");

		// template.append("set yrange [0:] \n");

		template.append("plot '" + dataFile.toString()
				+ "' u 1:2 w lp ps 0.1 lw 3 title 'unique entities'");
		template.append(",\\\n '" + dataFile.toString()
				+ "' u 1:3 w lp ps 0.1 lw 3 title 'all entity mentions'");

		return template;
	}

	/**
	 * count the distinct entity mentions rule: same entity AND label ->
	 * distinct mention
	 */
	private ArrayList<String> countDistinctEntities(
			ArrayList<ArrayList<String>> chunks, int evalStepSize) {
		
		if (evalStepSize >= chunks.size()) {
			throw new IllegalStateException(
					"Not enough annotated sentences to evaluate with this evaluation step size (step size: " + evalStepSize + ", annotated sentences: " + chunks.size() + ")");
		}
		ArrayList<String> distEntities = new ArrayList<String>();

		try {
			FileWriter fw = new FileWriter(new File(tmpDir.getAbsolutePath()
					+ File.separator + "entities.mentions"));

			TreeSet<String> entities = new TreeSet<String>();

			int entityCounter = 0;
			for (int i = 0; i < chunks.size(); i++) {
				ArrayList<String> sentChunks = chunks.get(i);
				fw.write("\nsentence " + i + "\n");
				for (String sent : sentChunks) {
					entityCounter++;
					fw.write(" " + sent + "(" + entityCounter + ")\n");
					entities.add(sent);
					fw.write("  -> " + entities.size() + "\n");
				}
				if ((i + 1) % evalStepSize == 0) {
					distEntities.add(i + 1 + "\t" + entities.size() + "\t"
							+ entityCounter + "\t"
							+ (entityCounter / (double) entities.size()));
					fw
							.write("\n\n### evaluation step: sentences: "
									+ (i + 1)
									+ " distinct entities: "
									+ entities.size()
									+ " all entities: "
									+ entityCounter
									+ " proportion: "
									+ (entityCounter / (double) entities.size())
									+ "\n");
				}
			}

			fw.close();
		} catch (Exception e) {
			throw new RuntimeException("obtaining number of entity mentions failed", e);
		}
		return distEntities;

	}

	/**
	 * generate entity chunks from PPD formatted sentences containing annotations
	 */
	private ArrayList<ArrayList<String>> getChunks(ArrayList<String> sentences) {

		String lastLabel = Constants.OUTSIDE_LABEL;
		String currEntity = "";
		String currLabel = "";

		ArrayList<ArrayList<String>> chunks = new ArrayList<ArrayList<String>>();
		int sent = 1;

		ArrayList<String> ents = new ArrayList<String>();
		for (String line : sentences) {

			if (line.equals("") || line.equals("O\tO")) {
				// new sentence
				lastLabel = Constants.OUTSIDE_LABEL;;
				if (currEntity.length() > 0 && !currLabel.equals(Constants.OUTSIDE_LABEL)) {
					ents.add(currEntity + "||" + currLabel);
				}
				chunks.add(ents);
				ents = new ArrayList<String>();
				sent++;
				currEntity = "";
			} else {
				currLabel = line.split("\t")[1];
				// System.out.println(line + " -> " + currLabel);
				if (currLabel.equals(Constants.OUTSIDE_LABEL)
						|| !currLabel.equals(lastLabel)) {
					// old ends

					if (currEntity.length() > 0
							&& !lastLabel.equals(Constants.OUTSIDE_LABEL)) {
						// System.out.println(sent + "#" + currEntity + " -> " +
						// currLabel);
						ents.add(currEntity + "||" + lastLabel);
					}
					currEntity = line.split("\t")[0];

				} else if (currLabel.equals(lastLabel)) {
					// go on with same entity
					// System.out.println("same entity: " +
					// line.split("\t")[0]);
					currEntity += " " + line.split("\t")[0];

				} else if (lastLabel.equals(Constants.OUTSIDE_LABEL)
						&& !currLabel.equals(Constants.OUTSIDE_LABEL)) {
					// System.out.println("new entity");
					currEntity = line.split("\t")[0];
				}
				lastLabel = currLabel;

			}

		}

		return chunks;
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("usage: CumulatedEntityStatistics <project-id>");
			System.exit(-1);
		}

		int id = (new Integer(args[0])).intValue();
		try {
			SQLDatabaseManager sdm = new SQLDatabaseManager();
			sdm.getConnection();
			CumulatedEntityStatistics stats = new CumulatedEntityStatistics(id,
					sdm);
			stats.makePlot();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("finished!");
	}

}
