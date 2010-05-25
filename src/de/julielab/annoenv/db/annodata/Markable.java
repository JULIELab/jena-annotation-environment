/** 
 * Markable.java
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

package de.julielab.annoenv.db.annodata;

import java.io.File;
import java.sql.Timestamp;
import java.util.logging.Logger;

import org.w3c.dom.Document;

import de.julielab.annoenv.db.sql.SQLDatabaseManager;
import de.julielab.annoenv.db.sql.SQLFunctions;

import de.julielab.annoenv.utils.AnnoEnvLogger;

/**
 * 
 * The Object representation of entries in table markable.
 * 
 * Attention: Markables need the level name set (though this is not stored in
 * the DB) for setting the mmaxdata id when a new project is created, i.e.
 * writing the project which calls the markables write method (KT)
 */
public class Markable {

	private static Logger logger = AnnoEnvLogger
			.getLogger("de.julielab.annoenv.db.annodata.Markable");

	public Markable() {
		markableID = -1;
		basedataID = -1;
		mmaxdataID = -1;
		creationDate = null;
		levelName = null;
		mainLevel = false;
	}

	private int markableID;

	private int basedataID;

	private int mmaxdataID;

	private Timestamp creationDate;

	private String levelName; // needed, see above!

	private boolean mainLevel;

	File markableFile;

	public int getMarkableID() {
		return markableID;
	}

	public void set_markable_id(int markable_id) {
		this.markableID = markable_id;
	}

	public int getBasedataID() {
		return basedataID;
	}

	public void setBasedataID(int basedata_id) {
		this.basedataID = basedata_id;
	}

	public int getMmaxdataID() {
		return mmaxdataID;
	}

	public void setMmaxdataID(int mmaxdata_id) {
		this.mmaxdataID = mmaxdata_id;
	}

	public Timestamp getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Timestamp creation_date) {
		this.creationDate = creation_date;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String level_name) {
		this.levelName = level_name;
	}

	public boolean isMainLevel() {
		return mainLevel;
	}

	public void setMainLevel(boolean main_level) {
		this.mainLevel = main_level;
	}

	public void setMarkableFile(File f) {
		if (!f.isFile()) {
			throw new IllegalStateException("schema file " + f
					+ " does not exist");
		}
		markableFile = f;

	}

	/**
	 * @param sdm
	 *            Connection to database
	 * @param filename
	 *            Location on disk
	 * @return Handle to file on disk
	 * @since 1.6
	 */
	public File writeToDisk(String filename, SQLDatabaseManager sdm) {
		this.markableFile = sdm.getUnicodeText("markable", "markable_file",
				"markable_id='" + this.getMarkableID() + "'", filename);
		return this.markableFile;
	}

	/**
	 * @param sdm
	 * @return XML Document.
	 * @since 1.6
	 */
	public Document getMarkableDoc(SQLDatabaseManager sdm) {
		Document doc = null;

		doc = sdm.fetchDocument("markable", "markable_file", "markable_id='"
				+ this.getMarkableID() + "'");
		return doc;

	}

	/*
	 * updateMarkable --> SQLFunctions.writeMarkable(...)
	 */

	public String toString() {
		String s = "ID: " + markableID + "| Date: " + creationDate.toString();
		return s;
	}

	public void write(SQLDatabaseManager sdm) {

		/*
		 * must have either: - basedata_id, mmaxdata_id and creation_date or -
		 * basedata_id, level_name and creation_date
		 */
		if (basedataID == -1) {
			throw new RuntimeException("markable basedata ID not set");
		} else if (mmaxdataID == -1) {
			throw new RuntimeException("markable mmaxdata ID not set");
		} else if (levelName == null) {
			throw new RuntimeException("markable level_name not set");
		} else if (creationDate == null) {
			throw new RuntimeException("markable creation data not set");
		} else if (markableFile == null) {
			throw new RuntimeException("markable file not set");
		}

		// now write a new entry to db and retrieve mmaxdata id
		markableID = new SQLFunctions(sdm).writeMarkable(basedataID,
				creationDate, markableFile, mmaxdataID, 0);
	}

}