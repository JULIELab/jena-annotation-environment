/** 
 * ExportMarkables.java
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
 * 
 **/

package de.julielab.annoenv.conversions;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import de.julielab.annoenv.db.annodata.AnnoFull;
import de.julielab.annoenv.db.annodata.BaseDataFull;
import de.julielab.annoenv.db.annodata.Markable;
import de.julielab.annoenv.db.sql.SQLDatabaseManager;
import de.julielab.annoenv.db.sql.SQLFunctions;

/**
 * class to export a project's main markables markables are stored under the uri
 * from the basedata + ".xml"
 */
public class ExportMarkables {

	private AnnoFull project;

	private File tmpDir = null;

	public ExportMarkables(AnnoFull project, File outDir) {

		this.project = project;
		this.tmpDir = outDir;

	}

	public void run(SQLDatabaseManager sdm) {
		// make directories and get needed data
		init();
		// get the basedata and convert it
		export(sdm);
	}

	private void init() {
		if (!tmpDir.isDirectory()) {
			throw new RuntimeException("directory does not exist: "
					+ tmpDir.toString());
		}
	}

	/**
	 * exports all basedata that has been set to "done" status
	 */
	private void export(SQLDatabaseManager sdm) {

		// now loop overall all basedata and make iob files

		ArrayList<BaseDataFull> basedata = null;

		basedata = project.getBaseDataFull();

		// System.out.println("basedata: " + basedata.size());
		for (BaseDataFull bd : basedata) {

			if (!bd.getStatus().equals("done")) {
				// do not export
				continue;
			}

			// export bd's main markable
			ArrayList<Markable> markables = bd.getMarkables();
			File mainmarkableFile = new File(tmpDir.toString() + File.separator
					+ bd.getUri() + ".xml");

			for (Markable m : markables) {
				if (m.isMainLevel()) {
					m.writeToDisk(mainmarkableFile.toString(), sdm);
				}
			}
		}
	}

	/**
	 * for testing and debugging use the main function... otherwise employ the
	 * ExportIOB object with its run() function
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			SQLDatabaseManager sdm = new SQLDatabaseManager();
			sdm.setConnection();
			SQLFunctions sf = new SQLFunctions(sdm);
			AnnoFull project = sf.getFullProject(65);
			// System.out.println(project.toString());
			ExportMarkables E = new ExportMarkables(project, new File(
					"/home/tomanek/tmp/AnnoEnv/1"));
			E.run(sdm);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}