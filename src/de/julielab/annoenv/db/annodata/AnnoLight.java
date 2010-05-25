/** 
 * AnnoLight.java
 * 
 * Copyright (c) 2007, JULIE Lab. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0 
 *
 * Author: tomanek, klaue, kampe
 * 
 * Current version: 2.0
 * Since version:   1.2
 *
 * Creation date: Januar, 2008 
 *  
 **/

package de.julielab.annoenv.db.annodata;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import de.julielab.annoenv.db.sql.SQLDatabaseManager;
import de.julielab.annoenv.db.sql.SQLFunctions;

/**
 * representation of project with core data
 */
public class AnnoLight extends AnnoCore implements Comparable {

	public AnnoLight() {
	}

	private ArrayList<BaseDataLight> basedataLight;

	protected File priolistFile = null;

	// AL fields and methods
	protected File alT2File = null;

	protected File alTagsFile = null;

	protected String alCorpusBin = "";

	protected String alCorpusTok = "";

	protected String alCorpusPos = "";

	// get
	public final ArrayList<BaseDataLight> getBaseDataLight() {
		return this.basedataLight;
	}

	/*
	 * the most recent basedata object (basically needed for AL, where this ´ *
	 * returns the basedata for the current iteration)
	 */

	public final BaseDataLight getCurrentBaseDataLight() {
		return this.basedataLight.get(0);
	}

	public File getAlCorpusBin() {
		return new File(this.alCorpusBin);
	}

	public File getAlCorpusTok() {
		return new File(this.alCorpusTok);
	}

	public File getAlCorpusPos() {
		return new File(this.alCorpusPos);
	}

	/**
	 * @param filename
	 * @param sdm
	 * @since 1.6
	 */
	public File writeT2File(final String filename, SQLDatabaseManager sdm) {
		this.alT2File = sdm.getUnicodeText("project", "AL_t2_file",
				"project_id='" + this.getProjectID() + "'", filename);
		return this.alT2File;
	}

	/**
	 * @param filename
	 * @param sdm
	 * @since 1.6
	 */
	public File writeTagsFile(final String filename, SQLDatabaseManager sdm) {
		this.alTagsFile = sdm.getUnicodeText("project", "AL_tags_file",
				"project_id='" + this.getProjectID() + "'", filename);
		return this.alTagsFile;
	}

	/**
	 * @param sdm
	 *            Connection to database
	 * @return The content of the priolist as a CharSequence (read "String").
	 * @since 1.6
	 */
	public List<String> getPriolist(SQLDatabaseManager sdm) {
		List<String> textList = null;

		textList = sdm.getTextAsList("project", "priolist_file", "project_id='"
				+ this.getProjectID() + "'");
		return textList;
	}

	/**
	 * @param filename
	 * @param sdm
	 * @return A file handle to the written priolist.
	 * @since 1.6
	 */
	public File writePriolistFile(String filename, SQLDatabaseManager sdm) {
		this.priolistFile = sdm.getUnicodeText("project", "priolist_file",
				"project_id='" + this.getProjectID() + "'", filename);
		return this.priolistFile;

	}

	public void setPriolistFile(final File f) {
		if (!f.isFile())
			throw new RuntimeException("priolist file does not exist");
		priolistFile = f;
	}

	// set
	public void setBaseDataLight(final ArrayList<BaseDataLight> bdllist) {
		this.basedataLight = bdllist;
	}

	public void setAlCorpusBin(final String filepath) {
		this.alCorpusBin = filepath;
	}

	public void setAlCorpusTok(final String filepath) {
		this.alCorpusTok = filepath;
	}

	public void setAlCorpusPos(final String filepath) {
		this.alCorpusPos = filepath;
	}

	public void setT2File(final File f) {
		if (!f.isFile()) {
			throw new RuntimeException("t2 file does not exist");
		}

		alT2File = f;
	}

	public void setTagsFile(File f) {
		if (!f.isFile()) {
			throw new RuntimeException("tags file " + f + " does not exist");
		}

		alTagsFile = f;
	}

	// overrides inherited method updateProject()

	public boolean updateProject(SQLFunctions sf) {
		boolean success = false;
		success = sf.updateProjectLight(projectID, name, description, status,
				userID, alBatchsize, alCorpusFraction, alIterations, alStatus,
				alCommittee, (float) alTrainprop, basedataLight, alT2File,
				priolistFile);
		return success;
	}

	public int hashCode() {
		return this.projectID;
	}

	public boolean equals(final Object o) {
		if (o instanceof AnnoLight) {
			return o.hashCode() == this.hashCode();
		}
		return false;
	}

	public int compareTo(final Object o) {
		if (o instanceof AnnoLight) {
			final String login = ((AnnoLight) o).name.toLowerCase();
			final String thislogin = this.name.toLowerCase();
			return thislogin.compareTo(login);
		}

		throw new ClassCastException();
	}

}
