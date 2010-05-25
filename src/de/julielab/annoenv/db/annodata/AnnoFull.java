/** 
 * AnnoFull.java
 * 
 * Copyright (c) 2007, JULIE Lab. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0 
 *
 * Author: tomanek, klaue
 * 
 * Current version: 2.0
 * Since version:   1.2
 *
 * Creation date: Januar, 2008 
 **/

package de.julielab.annoenv.db.annodata;


import java.util.ArrayList;
import java.util.HashMap;

import de.julielab.annoenv.al.ALChecks;

import de.julielab.annoenv.db.sql.SQLFunctions;

/**
 * representation of project with all data
 */
public class AnnoFull extends AnnoLight {

	private ArrayList<MmaxData> mmaxdata = null;

	private ArrayList<BaseDataFull> basedataFull;

	// constructor
	public AnnoFull() {
	}

	// get fields
	public ArrayList<MmaxData> getMmaxdata() {
		return mmaxdata;
	}

	public final ArrayList<BaseDataFull> getBaseDataFull() {
		return this.basedataFull;
	}

	/*
	 * the most recent basedata object (basically needed for AL, where this ´ *
	 * returns the basedata for the current iteration)
	 */
	public final BaseDataLight getCurrentBaseDataFull() {
		return this.basedataFull.get(0);
	}

	// set fields
	public void setMmaxdata(ArrayList<MmaxData> m) {
		this.mmaxdata = m;
	}

	public void setBaseDataFull(ArrayList<BaseDataFull> bdllist) {
		this.basedataFull = bdllist;
	}

	public void write(SQLFunctions sf) {

		if (userID < 0) {
			throw new RuntimeException("project user id not set");
		} else if (creationDate == null) {
			throw new RuntimeException("project creation date is not set");
		} else if (name.equals("")) {
			throw new RuntimeException("project name not set");
		} else if (description.equals("")) {
			throw new RuntimeException("project description not set");
		} else if (mode.equals("")) {
			throw new RuntimeException("mode not set");
		} else if (basedataFull == null) {
			throw new RuntimeException("project basedata not set");
		} else if (mmaxdata == null) {
			throw new RuntimeException("mmaxdata id not set");
		} else if (priolistFile == null) {
			throw new RuntimeException("project priolist not set");
		}

		if (isAl()) {
			if (mmaxdata == null) {
				throw new RuntimeException("project mmaxdata not set");
			} else if (alCorpusBin.equals("")) {
				throw new RuntimeException("project corpusBin not set");
			} else if (alCorpusTok.equals("")) {
				throw new RuntimeException("project corpusTok not set");
			} else if (alCorpusPos.equals("")) {
				throw new RuntimeException("project corpusPosnot set");
			} else if (alBatchsize <= 0) {
				throw new RuntimeException("project batchsize not set");
			} else if (alCommittee.equals("")) {
				throw new RuntimeException("project AL committee not set");
			} else if (!ALChecks.isValidCommittee(alCommittee)) {
				throw new RuntimeException("project AL committee incorrect...");
			} else if (alTrainprop <= 0) {
				throw new RuntimeException("project AL training proportion");
			} else if (!ALChecks.isValidTrainProportion(alCommittee,
					alTrainprop)) {
				throw new RuntimeException(
						"project AL training proportion incorrect...");
			} else if (alCorpusFraction <= 0) {
				throw new RuntimeException("project corpusfraction not set");
			} else if (priolistFile == null) {
				throw new RuntimeException("project priolist not set");
			} else if (alTagsFile == null) {
				throw new RuntimeException("project tags file not set");
			}

		}

		if (!isAl()) {
			// now write a new project entry to db and retrieve project id
			projectID = sf.writeDefaultProject(userID, creationDate, name,
					description, status, mode, priolistFile);
		} else {
			projectID = sf.writeALProject(userID, creationDate, name,
					description, status, mode, alCorpusBin, alCorpusTok,
					alCorpusPos, alBatchsize, alStatus, alCorpusFraction,
					alIterations, alCommittee, (float) alTrainprop, alT2File,
					priolistFile, alTagsFile);
		}

		// now write mmaxdata and basedata
		// loop over mmaxdata and write it with project id
		for (MmaxData m : mmaxdata) {
			m.setProjectID(projectID);
			m.write(sf.getSQLDatabaseManager());
		}

		// this associates the level names and mmaxdata ids
		// this is needed for setting the mmaxdata ids to the markables
		final HashMap<String, Integer> levelNames = new HashMap<String, Integer>();
		for (MmaxData m : mmaxdata) {
			final int mmaxdata_id = m.getMmaxdataID();
			final String level_name = m.getLevelName();
			levelNames.put(level_name, Integer.valueOf(mmaxdata_id));
		}

		// now loop over basedata and write it with project id
		for (BaseDataFull b : basedataFull) {
			// loop over markables and set their mmaxdata_id
			// use the levelNames HashMap to determine which mmaxdata id to
			// set
			ArrayList<Markable> markables = b.getMarkables();
			for (Markable m : markables) {
				String level_name = m.getLevelName();
				int mmaxdata_id = (levelNames.get(level_name)).intValue();
				m.setMmaxdataID(mmaxdata_id);
			}

			b.setProjectID(projectID);
			b.write(sf.getSQLDatabaseManager());
		}

	}

	// overrides inherited method updateProject()

	public boolean updateProject(SQLFunctions sf) {
		boolean success = false;
		success = sf.updateProjectFull(projectID, name, description, status,
				userID, alBatchsize, alCorpusFraction, alIterations, alStatus,
				alCommittee, (float) alTrainprop, basedataFull, alT2File,
				priolistFile);

		return success;
	}

	public int hashCode() {
		return super.hashCode();
	}

	public boolean equals(final Object o) {
		return super.equals(o);
	}

	public int compareTo(final Object o) {
		return super.compareTo(o);
	}

}
