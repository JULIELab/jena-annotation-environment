/** 
 * MMaxSession.java
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
 
 */

package de.julielab.annoenv.ui.annoclient.MMAX;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;

import de.julielab.annoenv.db.annodata.BaseDataFull;
import de.julielab.annoenv.db.annodata.Markable;
import de.julielab.annoenv.db.annodata.MmaxData;
import de.julielab.annoenv.db.sql.SQLDatabaseManager;
import de.julielab.annoenv.db.sql.SQLFunctions;

import de.julielab.annoenv.utils.AnnoEnvLogger;
import de.julielab.annoenv.utils.DeleteDir;
import de.julielab.annoenv.utils.IOUtils;

/**
 * functions for creating a MMAX session: 1) downloads the needed files from the
 * db and 2) creates directories and files in the user's anno client tmp dir
 */
public class MMAXSession {

	File sessionDir;

	private int annotatorID; // the ID of the current annotator (user)

	private BaseDataFull basedata;

	private String[] levels;

	private ArrayList<Markable> markablesList;

	private ArrayList<MmaxData> mmaxdataList;

	private HashMap<String, Integer> MarkableID;

	private File basedataFile;

	private File styleFile;

	private SQLDatabaseManager sdm;

	private static Logger logger = AnnoEnvLogger
			.getLogger("de.julielab.annoenv.ui.annoclient.MmaxSession");

	/**
	 * the session Dir name is based on the temporary directory, the database
	 * internal basedata-id, and a random number to avoid conflicts
	 */
	private File getSessionDir(File tmpDir, int baseDataID) {

		if (!tmpDir.isDirectory() || !tmpDir.canWrite()) {
			String info = "tmpDir  " + tmpDir.toString()
					+ " is not a directory or not writeable.";
			throw new RuntimeException(info);
		}

		Random r = new Random(System.currentTimeMillis());
		int random = Math.abs(r.nextInt());
		File sessionDir = new File(tmpDir.getAbsolutePath()
				+ File.separatorChar + "mmax_" + baseDataID + "_" + random);
		logger.fine("requesting session dir name... " + sessionDir);
		return sessionDir;
	}

	private void makeSessionDir(File sessionDir) {
		boolean success = sessionDir.mkdir();
		if (!success) {
			String info = "Failed creating session directory: "
					+ sessionDir.toString()
					+ " --  probably temporaty directory not writeable!";
			throw new RuntimeException(info);
		}
	}

	/**
	 * the constructor
	 * 
	 * @param sdm
	 *            the sql database manager (which has a connection to db)
	 * @param tmpDir
	 * @param project
	 *            a project object
	 * @param basedata
	 * 
	 */
	public MMAXSession(SQLDatabaseManager sdm, File tmpDir, int userID,
			int basedataID) {
		this.sdm = sdm;

		// create session directory
		sessionDir = getSessionDir(tmpDir, basedataID);
		makeSessionDir(sessionDir);

		this.basedata = new SQLFunctions(sdm).getBaseDataFull(basedataID);
		this.annotatorID = userID;

		// get markables and basedata objects
		markablesList = basedata.getMarkables();
		mmaxdataList = new SQLFunctions(sdm).getMmaxDataList(basedata
				.getProjectID());

		// filename and ID mapping for markables
		MarkableID = new HashMap<String, Integer>();

		// determine all level names
		levels = new String[mmaxdataList.size()];
		for (int i = 0; i < mmaxdataList.size(); i++) {
			levels[i] = mmaxdataList.get(i).getLevelName();
		}
	}

	/**
	 * get all necessery session data for the specified project/basedata
	 * 
	 * @return the full path to the project.mmax file
	 */
	public File downloadSession() {
		// basedata
		File basedataDir = new File(sessionDir.toString() + File.separator
				+ "Basedata");
		if (!basedataDir.mkdir()) {
			throw new RuntimeException("Could not create Basedata directory!");
		}
		basedata.writeToDisk(basedataDir.toString() + File.separator
				+ "Basedata.xml", sdm);

		// write basedata uri to file
		File basedataUri = new File(sessionDir.toString() + File.separatorChar
				+ "Basedata.uri");
		String bdUri = basedata.getUri();
		// TODO do we really have to write it to a file ?
		FileWriter fw = null;
		try {
			fw = new FileWriter(basedataUri);
			fw.write(bdUri + "\n");
		} catch (IOException e) {
			throw new RuntimeException("writing basedata to file failed", e);
		} finally {
			try {
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// styles
		File stylesDir = new File(sessionDir.toString() + File.separator
				+ "Styles");
		if (!stylesDir.mkdir()) {
			throw new RuntimeException("Could not create Styles directory!");
		}

		basedata.writeStyleFile(stylesDir.toString() + File.separator
				+ "default_style.xsl", sdm);

		// markables
		File markablesDir = new File(sessionDir.toString() + File.separator
				+ "Markables");
		if (!markablesDir.mkdir()) {
			throw new RuntimeException("Could not create Markables directory!");
		}

		for (Markable m : markablesList) {
			String level = m.getLevelName();
			String markableFile = markablesDir.toString() + File.separator
					+ level + ".xml";
			m.writeToDisk(markableFile, sdm);
			MarkableID.put(markableFile, m.getMarkableID());
		}

		// customization & schema
		File customsDir = new File(sessionDir.toString() + File.separator
				+ "Customizations");
		if (!customsDir.mkdir()) {
			throw new RuntimeException(
					"Could not create Customizations directory!");
		}

		File schemaDir = new File(sessionDir.toString() + File.separator
				+ "Schemes");
		if (!schemaDir.mkdir()) {
			throw new RuntimeException("Could not create Schemes directory!");
		}

		for (MmaxData mmax : mmaxdataList) {
			String level = mmax.getLevelName();
			mmax.writeCustomFile(customsDir.toString() + File.separator + level
					+ ".xml", sdm);
			mmax.writeSchemaFile(schemaDir.toString() + File.separator + level
					+ ".xml", sdm);
		}

		// create further needed files
		File commonpathFile = new File(sessionDir.toString()
				+ File.separatorChar + "common_paths.xml");
		File projectmmaxFile = new File(sessionDir.toString()
				+ File.separatorChar + "project.mmax");
		File wordsdtdFile = new File(basedataDir.toString()
				+ File.separatorChar + "words.dtd");
		File markablesdtdFile = new File(markablesDir.toString()
				+ File.separatorChar + "markables.dtd");

		SQLFunctions sf = new SQLFunctions(sdm);
		sf.writeMarkableDTDFile(markablesdtdFile);
		sf.writeWordDTDFile(wordsdtdFile);

		IOUtils.writeFile(makeCommonPathFileContent(), commonpathFile);
		IOUtils.writeFile(makeProjectFileContent(), projectmmaxFile);

		return projectmmaxFile;
	}

	/**
	 * store session in DB
	 * 
	 * @param curr_annotation_time
	 *            time (in minutes) that MMAX was running

	 */
	public boolean uploadSession(boolean finished, int curr_annotation_time) {
		SQLFunctions sf = new SQLFunctions(sdm);
		// uploads all markables
		logger.fine("uploading markables ");
		for (String key : MarkableID.keySet()) {
			int markableID = (MarkableID.get(key)).intValue();
			File markableFile = new File(key);
			Markable m = basedata.getMarkable(markableID);
			// System.out.print("..." + markableID);
			Timestamp t = new Timestamp(System.currentTimeMillis());
			int markable_id = sf.writeMarkable(basedata.getBasedataID(), t,
					markableFile, m.getMmaxdataID(), 0);
			// System.out.println ("written markable id: " + markable_id);
		}

		// upload basedata
		logger.fine("uploading basedata...");

		// boolean success = sf.updateBaseData(basedata.getBasedata_id(),
		// basedataFile, styleFile);
		basedata.setStyleFile(styleFile);
		// System.out.println(basedataFile.toString());
		basedata.setBasedataFile(basedataFile);
		// basedata.setStatus(NewAnnoClientMasterFrame.INPROGRESS);
		basedata.addAnnotationTime(curr_annotation_time);
		basedata.setUser(annotatorID); // set the id of the current user
		// who has done the annotation
		basedata.updateBaseData(sdm);

		// clean up session
		cleanUp();

		return true;
	}

	/**
	 * autosave session in DB (only mmaxData)

	 */
	public boolean autoUploadSession(int curr_annotation_time) {
		try {
			SQLFunctions sf = new SQLFunctions(sdm);
			// uploads all markables
			logger.fine("uploading markables ");
			for (String key : MarkableID.keySet()) {
				int markableID = MarkableID.get(key).intValue();
				File markableFile = new File(key);
				Markable m = basedata.getMarkable(markableID);
				// System.out.print("..." + markableID);
				Timestamp t = new Timestamp(System.currentTimeMillis());
				int markable_id = sf.writeMarkable(basedata.getBasedataID(), t,
						markableFile, m.getMmaxdataID(), 1);
			}

			// upload basedata
			logger.fine("uploading basedata...");

			// boolean success = sf.updateBaseData(basedata.getBasedata_id(),
			// basedataFile, styleFile);
			basedata.setStyleFile(styleFile);
			// System.out.println(basedataFile.toString());
			basedata.setBasedataFile(basedataFile);
			basedata.addAnnotationTime(curr_annotation_time);
			basedata.setUser(annotatorID); // set the id of the current user
			// who has done the annotation
			basedata.updateBaseData(sdm);
		} catch (RuntimeException e) {
			throw new RuntimeException("Auto-saving of markable failed.", e);
		}
		return true;
	}

	/**
	 * removes all markables that were created during auto-save
	 */
	public boolean removeAutosaveMarkables() {
		SQLFunctions sf = new SQLFunctions(sdm);
		logger.fine("deleting autosaved markables from basedata_id"
				+ basedata.getBasedataID());
		sf.removeAutosaveMarkables(basedata.getBasedataID());
		return true;
	}

	/**
	 * makes the common path XML file
	 * 
	 * Specialities: if the level is called "sentence" it is set to visible!
	 * This is a temporary hack (KT: 15.07.2009) and should be improved in the
	 * future so that the startup-visibility can be chosen for each level in the
	 * annomaster gui when setting up a project.
	 * 
	 * @return conten of common path file
	 */
	private String makeCommonPathFileContent() {
		String header = "<?xml version=\"1.0\"?>\n"
				+ "<!DOCTYPE common_paths>\n" + "<common_paths>\n"
				+ "<basedata_path>Basedata/</basedata_path>\n"
				+ "<markable_path>Markables/</markable_path>\n"
				+ "<scheme_path>Schemes/</scheme_path>\n"
				+ "<style_path>Styles/</style_path>\n"
				+ "<customization_path>Customizations/</customization_path>\n"
				+ "<views>" + "<stylesheet>default_style.xsl</stylesheet>\n"
				+ "</views>";
		String footer = "<user_switches>\n" + "</user_switches>\n"
				+ "</common_paths>";

		StringBuilder annotations = new StringBuilder("<annotations>");
		for (String level : levels) {
			// add a line in annotations sections for each level

			String line = "";

			if (level.equals("sentence")) {
				// hack: if level is calles "sentence", set it to "visible" only

				line = "<level name=\"" + level + "\" " + "schemefile=\""
						+ level + ".xml\" " + "customization_file=\"" + level
						+ ".xml\" at_startup=\"visible\">" + level
						+ ".xml</level>";
			} else {
				// otherwise keep it in default (i.e., active)
				line = "<level name=\"" + level + "\" " + "schemefile=\""
						+ level + ".xml\" " + "customization_file=\"" + level
						+ ".xml\">" + level + ".xml</level>";
			}
			annotations.append("\n");
			annotations.append(line);
		}
		annotations.append("\n</annotations>");

		return (header + "\n" + annotations.toString() + "\n" + footer);
	}

	private String makeProjectFileContent() {
		String pm = "<?xml version=\"1.0\"?>\n" + "<mmax_project>\n"
				+ "<turns></turns>\n" + "<words>Basedata.xml</words>\n"
				+ "<gestures></gestures>\n" + "<keyactions></keyactions>\n"
				+ "</mmax_project>";
		return pm;
	}

	/**
	 * this deletes all files and directories that have been downloaded and/or
	 * created for the session...
	 */
	public void cleanUp() {
		logger.fine("cleaning up old session data..." + sessionDir);

		boolean success = DeleteDir.deleteDirectory(sessionDir);
		if (!success)
			logger.warning("could not delete session directory: "
					+ sessionDir.toString());

	}

}
