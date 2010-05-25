/** 
 * MmaxData.java
 * 
 * Copyright (c) 2007, JULIE Lab. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0 
 *
 * Author: buyko
 * 
 * Current version: 2.0 	
 * Since version:   0.7
 *
 * Creation date: Aug 01, 2006
 * 
 */

package de.julielab.annoenv.db.annodata;

import java.io.File;

import de.julielab.annoenv.db.sql.SQLDatabaseManager;
import de.julielab.annoenv.db.sql.SQLFunctions;


/**
 * This class is an object representation of table mmaxdata in DB.
 */
public class MmaxData {



	private int mmaxdataID = -1;

	private int projectID = -1;

	private String levelName = "";

	private boolean mainLevel = false;

	private File schemaFile = null;

	private File customFile = null;

	/**
	 * this is the constructor for a new mmaxdata entity that is not already
	 * contained in the DB. Thus, we cannot provide its mmaxdata_id
	 * 
	 */
	public MmaxData() {
	}

	/**
	 * setter functions
	 */

	public void setMmaxdataID(int id) {
		mmaxdataID = id;
	}

	public void setProjectID(int id) {
		projectID = id;
	}

	public void setLevelName(String name) {
		levelName = name;
	}

	public void setMainLevel(boolean l) {
		mainLevel = l;
	}

	/**
	 * sets where the file is stored on the disk this does not necessarily have
	 * to be set
	 */
	public void setSchemaFile(File f) {
		if (!f.isFile()) {
			throw new RuntimeException("schema file " + f + " does not exist");
		}

		schemaFile = f;
	}

	/**
	 * sets where the file is stored on the disk this does not necessarily have
	 * to be set
	 */
	public void setCustomFile(File f) {
		if (!f.isFile()) {
			throw new IllegalStateException("custom file " + f
					+ " does not exist");
		}

		customFile = f;
	}

	/**
	 * getter functions
	 */
	public int getMmaxdataID() {
		return mmaxdataID;
	}

	public int getProjectID() {
		return projectID;
	}

	public String getLevelName() {
		return levelName;
	}

	public boolean isMainLevel() {
		return mainLevel;
	}

	/**
	 * @param filename
	 * @param sdm
	 * @since 1.6
	 */
	public File writeSchemaFile(String filename, SQLDatabaseManager sdm) {
		this.schemaFile = sdm.getUnicodeText("mmaxdata", "schema_file",
				"mmaxdata_id='" + this.getMmaxdataID() + "'", filename);
		return this.customFile;

	}

	/**
	 * @param filename
	 * @param sdm
	 * @return A file handle to the custom-file.
	 * @since 1.6
	 */
	public File writeCustomFile(String filename, SQLDatabaseManager sdm) {
		this.customFile = sdm.getUnicodeText("mmaxdata", "custom_file",
				"mmaxdata_id='" + this.getMmaxdataID() + "'", filename);
		return this.customFile;

	}

	public boolean isValid() {
		boolean valid = true;
		if (projectID < 0)
			valid = false;

		if (mmaxdataID < 0)
			valid = false;

		if (levelName.equals(""))
			valid = false;

		if (!valid)
			throw new RuntimeException(
					"objects variables for new mmaxdata object not valid");

		return valid;
	}

	public void write(SQLDatabaseManager sdm) {
		if (schemaFile == null) {
			throw new RuntimeException("mmaxdata schema file not set");
		} else if (customFile == null) {
			throw new RuntimeException("mmaxdata custom file not set");
		} else if (projectID < 0) {
			throw new RuntimeException("mmaxdata project id not set");
		} else if (levelName.equals("")) {
			throw new RuntimeException("mmaxdata level name not set");
		}

		// now write a new entry to db and retrieve mmaxdata id
		mmaxdataID = new SQLFunctions(sdm).writeMmaxData(projectID, levelName,
				mainLevel, schemaFile, customFile);
	}

	/**
	 * updates the mmax-schema definition including the level name, the schema
	 * file, customization file
	 * 
	 * what cannot (and should also not) be changed: whether this is the main
	 * level or not!!!

	 */
	public boolean updateMmaxData(SQLDatabaseManager sdm) {
		boolean success = true;
		if (schemaFile == null) {
			throw new IllegalStateException("Schema file not set...");
		} else if (customFile == null) {
			throw new IllegalStateException("Customization file not set...");
		} else if (projectID < 0) {
			throw new IllegalStateException("Project id not set...");
		} else if (levelName.equals("")) {
			throw new IllegalStateException("Level name not set...");
		} else if (mmaxdataID < 0) {
			throw new IllegalStateException("No mmaxdata id available...");
		}

		SQLFunctions sf = new SQLFunctions(sdm);
		success = sf.updateMmaxData(mmaxdataID, levelName, mainLevel,
				schemaFile, customFile);
		return success;
	}

	public String toString() {
		String m = (this.mainLevel) ? " (main)" : "";
		String s = levelName + m;
		return s;
	}

}
