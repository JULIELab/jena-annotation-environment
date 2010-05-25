/** 
 * BaseDataFull.java
 * 
 * Copyright (c) 2007, JULIE Lab. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0 
 *
 * Author: tomanek, kampe
 * 
 * Current version: 2.0	
 * Since version:   0.7
 *
 * Creation date: Sep 10, 2008
 * 
 */

package de.julielab.annoenv.db.annodata;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Logger;


import org.w3c.dom.Document;

import de.julielab.annoenv.conversions.Tidsid;
import de.julielab.annoenv.db.sql.SQLDatabaseManager;
import de.julielab.annoenv.db.sql.SQLFunctions;

import de.julielab.annoenv.utils.AnnoEnvLogger;

public class BaseDataFull extends BaseDataLight {

	private ArrayList<Markable> markables;

	protected File basedataFile;

	protected File styleFile;

	protected File tidsidFile;

	private static Logger logger = AnnoEnvLogger
			.getLogger("de.julielab.annoenv.db.annodata.BaseDataFull");

	public BaseDataFull() {
		super();
	}

	public boolean isValid() {
		if (basedata_id != -1 && project_id != -1 && uri != null
				&& status != null && userID != -1 && markables != null) {
			return true;
		}

		logger.warning("objects variables for new basedata object not valid");
		return false;
	}

	public void setMarkables(ArrayList<Markable> markables) {
		this.markables = markables;
	}

	public ArrayList<Markable> getMarkables() {
		return this.markables;
	}

	/**
	 * gets us for the current basedata the one markable (of the current ones)
	 * which has a flag set to main level= true.
	 * 
	 * @return the markable or null if none was found with main level flag set
	 */
	public Markable getMainLevelMarkable() {
		for (Markable m : markables) {
			if (m.isMainLevel()) {
				return m;
			}
		}
		// if there is no main level markable... return null, this
		// can happen for non AL projects
		return null;
	}

	/**
	 * returns the markable with the given ID, null is returned if this ID is
	 * not found
	 * 
	 * @param a
	 *            markable ID
	 * @return a markable object
	 */
	public Markable getMarkable(int wantedID) {
		for (Markable m : markables) {
			if (m.getMarkableID() == wantedID) {
				return m;
			}
		}
		return null;
	}

	public void setStyleFile(File styleFile) {
		this.styleFile = styleFile;
	}

	/**
	 * @param filename
	 * @param sdm
	 * @since 1.6
	 */
	public File writeStyleFile(String filename, SQLDatabaseManager sdm) {
		this.styleFile = sdm.getUnicodeText("basedata", "style_file",
				"basedata_id='" + this.getBasedataID() + "'", filename);
		return styleFile;

	}

	public void setBasedataFile(File basedataFile) {
		this.basedataFile = basedataFile;
	}

	/**
	 * @param filename
	 * @param sdm
	 * @since 1.6
	 */
	public File writeToDisk(String filename, SQLDatabaseManager sdm) {
		this.basedataFile = sdm.getUnicodeText("basedata", "basedata_file",
				"basedata_id='" + this.getBasedataID() + "'", filename);
		return basedataFile;

	}

	/**
	 * @param sdm
	 * @since 1.6
	 */
	public Document getBasedataDoc(SQLDatabaseManager sdm) {
		Document doc = null;

		doc = sdm.fetchDocument("basedata", "basedata_file", "basedata_id='"
				+ this.getBasedataID() + "'");
		return doc;

	}

	public void setTidsidFile(File tidsidFile) {
		this.tidsidFile = tidsidFile;
	}

	/**
	 * @param sdm
	 * @since 1.6
	 */
	public List<Tidsid> getTidsidList(SQLDatabaseManager sdm) {
		String seq = null;
		BufferedReader bufReader = null;
		List<Tidsid> list = new ArrayList<Tidsid>();

		try {
			seq = sdm.getTextAsString("basedata", "AL_TIDSID_file",
					"basedata_id='" + this.getBasedataID() + "'");
			StringReader in = new StringReader(seq);
			bufReader = new BufferedReader(in);

			String line = bufReader.readLine();
			while (line != null) {

				Tidsid tidsid = new Tidsid();
				StringTokenizer tokenizer = new StringTokenizer(line);

				if (tokenizer.countTokens() == 4) {
					String offsetStr = tokenizer.nextToken();
					tidsid.setOffset(Integer.parseInt(offsetStr));
					String distanceStr = tokenizer.nextToken();
					tidsid.setDistance(Integer.parseInt(distanceStr));
					String tidStr = tokenizer.nextToken();
					tidsid.setTid(Integer.parseInt(tidStr));
					String sidStr = tokenizer.nextToken();
					tidsid.setSid(Integer.parseInt(sidStr));
					list.add(tidsid);
				} else {
					throw new IllegalArgumentException(
							"Error: Tidsid file has an uncorrect number of parameters.");
				}
				line = bufReader.readLine();
			}

		} catch (IOException e) {
			throw new RuntimeException(
					"Error: Could not retrieve Tidsid list.", e);
		} finally {
			if (bufReader != null) {
				try {
					bufReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	/**
	 * updates the base data table in the database with files
	 */
	public boolean updateBaseData(SQLDatabaseManager sdm) {
		SQLFunctions sf = new SQLFunctions(sdm);
		boolean success = false;
		success = sf.updateBaseDataFull(basedata_id, description, uri, status,
				userID, annotationTime, basedataFile, styleFile, tidsidFile);
		return success;
	}

	public void write(SQLDatabaseManager sdm) {

		if (basedataFile == null) {
			throw new RuntimeException("basedata_file not set");
		} else if (styleFile == null) {
			throw new RuntimeException("basedata style_file file not set");
		} else if (uri.equals("")) {
			throw new RuntimeException("basedata uri not set");
		} else if (project_id < 0) {
			throw new RuntimeException("basedata project id not set");
		} else if (markables == null) {
			throw new RuntimeException("markable not set");
		} else if (status.equals("")) {
			throw new RuntimeException("basedata status not set");
		}

		// make sure that all markables have mmaxdata ids

		basedata_id = new SQLFunctions(sdm).writeBaseDataFull(project_id,
				description, uri, status, userID, annotationTime, basedataFile,
				styleFile, tidsidFile);

		// now loop over basedata and write it with project id
		for (Markable m : markables) {
			m.setBasedataID(basedata_id);
			m.write(sdm);
		}

		this.setBasedataID(basedata_id);

	}

}
