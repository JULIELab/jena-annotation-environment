/** 
 * MMAXSessionTest.java
 * 
 * Copyright (c) 2007, JULIE Lab. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0 
 *
 * Author: tomanek
 * 
 * Current version: 1.2 	
 * Since version:   0.7
 *
 * Creation date: Aug 01, 2006
 * 
 * this is a first version (non graphical) of the annoclient user interface.
 * Currently it is used for testing purposes, i.e. to test the DB interfaces and
 * the MMaxSession class
 * 
 */

package de.julielab.annoenv.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Logger;

import de.julielab.annoenv.db.annodata.AnnoFull;
import de.julielab.annoenv.db.annodata.BaseDataFull;
import de.julielab.annoenv.db.annodata.Markable;
import de.julielab.annoenv.db.annodata.MmaxData;
import de.julielab.annoenv.db.sql.SQLDatabaseManager;
import de.julielab.annoenv.db.sql.SQLFunctions;

import de.julielab.annoenv.ui.annoclient.MMAX.MMAXSession;
import de.julielab.annoenv.utils.AnnoEnvLogger;
import de.julielab.annoenv.utils.settings.AnnoClientSettings;

public class MMAXSessionTest {

	private static Logger logger = AnnoEnvLogger
			.getLogger("de.julielab.annoenv.test.MMAXSessionTest");

	static File tmpDir;

	static int userID;

	static ArrayList<AnnoFull> projects;

	public static void main(String[] args) {

		try {

			SQLDatabaseManager sdm = new SQLDatabaseManager();
			sdm.setConnection();
			SQLFunctions sf = new SQLFunctions(sdm);
			tmpDir = new File("/tmp/");
			int myUserID = 7;
			int myBdID = 12725;
			MMAXSession session = new MMAXSession(sdm, tmpDir, myUserID, myBdID);
			File file = session.downloadSession();
			System.out.println(file);
			//session.uploadSession(false, 0);
			session.cleanUp();
			System.exit(-1);
			//-----------
			
			
			AnnoFull p = sf.getFullProject(8);
			ArrayList<BaseDataFull> b = p.getBaseDataFull();
			System.out.println(b);

			//System.exit(0);

			AnnoClientSettings acSettings = new AnnoClientSettings();
			tmpDir = acSettings.SESSION_DIR;

			System.out.println("logging in as user JamesBond...\n");
			userID = (new SQLFunctions(sdm)).login("CleanJane", "clean.up");
			System.out.println(userID);
			System.out.println("getting user's projects...\n");
			projects = sf.getFullProjects(userID);

			System.out.println("db time: " + sf.getDBSystemTime().toString());
			System.out.println("pc time: "
					+ (new Timestamp(System.currentTimeMillis())).toString());
			// show all projects
			for (int i = 0; i < projects.size(); i++) {
				showProject(projects.get(i), true);
			}
			System.exit(0);

			System.out.println(" ---- annotation mode ---");

			// get session for one project and basedata
			System.out.println("making session for project/basedata...");
			// DefaultAnnoProject project = (DefaultAnnoProject)
			// projects.get(0);
			AnnoFull project = projects.get(1);
			BaseDataFull basedata = project.getBaseDataFull()
					.get(0);

			showProject(project, false);
			showBasedata(basedata);

			// ----- to be used in GUI ----
			// make session
			logger.info("creating MmaxSession...");
			MMAXSession Session = new MMAXSession(sdm, tmpDir, project
					.getUserID(), basedata.getBasedataID());
			File runFile = Session.downloadSession();

			// now execute mmax
			logger.info("running Mmax...");
			// MMAXExecuter mex = new MMAXExecuter(runFile);
			// mex.runMmax(runFile);

			// upload session again
			logger.info("uploading MmaxSession data...");
			Session.uploadSession(false, 0);
			// ----- to be used in GUI ----

			logger.info("all done successfully!");

		} catch (Exception e) {
			e.printStackTrace();
		} 

	}

	// --------------------------------------------------------------------
	// --- for testing only! can be removed as soon as we have the GUI ----
	// --------------------------------------------------------------------
	static void showProject(AnnoFull p, boolean complete) {
		System.out.println("\nProject: " + p.toString());
		if (complete) {
			// show everything for the project
			ArrayList<BaseDataFull> basedata = p.getBaseDataFull();
			for (int i = 0; i < basedata.size(); i++) {
				BaseDataFull b = (BaseDataFull) basedata.get(i);
				showBasedata(b);
			}
			ArrayList mmaxdata = p.getMmaxdata();
			for (int i = 0; i < mmaxdata.size(); i++) {
				MmaxData m = (MmaxData) mmaxdata.get(i);
				showMmaxData(m);
			}
		}
	}

	static void showBasedata(BaseDataFull b) {
		System.out.println("- Basedata: " + b.toString());
		ArrayList markables = b.getMarkables();
		for (int i = 0; i < markables.size(); i++) {
			showMarkable((Markable) markables.get(i));
		}
	}

	static void showMmaxData(MmaxData m) {
		System.out.println("- MmaxData: " + m.toString());
	}

	static void showMarkable(Markable m) {
		System.out.println("  - Markable: " + m.toString());
	}

}
