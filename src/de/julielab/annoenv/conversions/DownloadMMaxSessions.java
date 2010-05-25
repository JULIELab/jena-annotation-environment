/** 
 * DownloadMMaxSessions.java
 * 
 * Copyright (c) 2007, JULIE Lab. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0 
 *
 * Author: tomanek
 * 
 * Current version: 2.0
 * Since version:   1.1
 *
 * Creation date: May 31, 2007 
 * 
 **/

package de.julielab.annoenv.conversions;

import java.io.File;

import java.sql.SQLException;
import java.util.ArrayList;



import de.julielab.annoenv.db.annodata.AnnoLight;
import de.julielab.annoenv.db.annodata.BaseDataLight;
import de.julielab.annoenv.db.sql.SQLDatabaseManager;
import de.julielab.annoenv.db.sql.SQLFunctions;

import de.julielab.annoenv.ui.annoclient.MMAX.MMAXSession;

/**
 * Class that downloads for each document (basedata) in an annotation project a
 * MMAX session, i.e. all files necessary for MMax for this basedata. This can
 * take quite some time of the annotation project contains many basedata.
 */
public class DownloadMMaxSessions {

	private SQLDatabaseManager sdm = null;

	private SQLFunctions sf = null;

	private AnnoLight project = null;

	public DownloadMMaxSessions(int projectID, SQLDatabaseManager sdm)
			throws SQLException {

		sf = new SQLFunctions(sdm);

		// get project data (might take a while)
		project = sf.getLightProject(projectID);
	}

	public void run(File outputDir) {

		// check whether directory exists
		if (!outputDir.isDirectory()) {
			throw new RuntimeException(
					"Specified output directory does not exist: "
							+ outputDir.toString());
		} else if (!outputDir.canWrite()) {
			throw new RuntimeException(
					"Specified output directory is not writable: "
							+ outputDir.toString());
		}

		ArrayList<BaseDataLight> projectBD = project.getBaseDataLight();

		// loop over basedata and make an MMax session for it
		for (BaseDataLight basedata : projectBD) {
			MMAXSession mmaxSession = new MMAXSession(sdm, outputDir, project
					.getUserID(), basedata.getBasedataID());
			File sessionFile = mmaxSession.downloadSession();
			System.out.println("session for basedata " + basedata.getUri()
					+ " downloaded to: " + sessionFile);
		}

	}

	public static void main(String[] args) throws Exception {

		if (args.length != 2) {
			System.err
					.println("usage: DownloadMMaxSession <project id> <output directory>");
		}

		SQLDatabaseManager sdm = new SQLDatabaseManager();
		sdm.setConnection();
		DownloadMMaxSessions down;

		int pid = (new Integer(args[0])).intValue();
		File outputDir = new File(args[1]);

		down = new DownloadMMaxSessions(pid, sdm);
		down.run(outputDir);

	}
}
