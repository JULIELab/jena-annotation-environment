/** 
 * DownloadMMaxData.java
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
 * Creation date: Aug 15, 2007 
 * 

 **/

package de.julielab.annoenv.conversions;

import java.io.File;


import de.julielab.annoenv.db.annodata.AnnoLight;
import de.julielab.annoenv.db.annodata.BaseDataLight;
import de.julielab.annoenv.db.sql.SQLDatabaseManager;
import de.julielab.annoenv.db.sql.SQLFunctions;
import de.julielab.annoenv.ui.annoclient.MMAX.MMAXSession;

/**
 * downloads all mmax project of an annotation project
 */
public class DownloadMMaxData {


	public void downloadData(SQLDatabaseManager sdm, File outputDir,
			AnnoLight project) {
		for (BaseDataLight basedata : project.getBaseDataLight()) {
			MMAXSession session = new MMAXSession(sdm, outputDir, project
					.getUserID(), basedata.getBasedataID());
			session.downloadSession();
		}
	}

	/**
	 * main method for debugging purposes
	 */
	public static void main(String[] args) throws Exception {
		DownloadMMaxData down = new DownloadMMaxData();
		SQLDatabaseManager sdm = new SQLDatabaseManager();
		sdm.setConnection();
		SQLFunctions sf = new SQLFunctions(sdm);
		AnnoLight p = sf.getLightProject(157);
		down.downloadData(sdm, new File("/home/kampe/tmp/xmi"), p);
		File outputDir = new File("/home/kampe/tmp/belinda/");
		for (BaseDataLight b : p.getBaseDataLight()) {
			MMAXSession session = new MMAXSession(sdm, outputDir,
					p.getUserID(), b.getBasedataID());
			session.downloadSession();
		}
	}


}
