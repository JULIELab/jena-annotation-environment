/** 
 * DownloadBasedataMarkables.java
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
 * Creation date: Aug 01, 2006 
 * 
 * 
 **/

package de.julielab.annoenv.conversions;

import java.io.File;
import java.util.ArrayList;



import de.julielab.annoenv.db.annodata.AnnoFull;
import de.julielab.annoenv.db.annodata.BaseDataFull;
import de.julielab.annoenv.db.annodata.Markable;
import de.julielab.annoenv.db.annodata.MmaxData;
import de.julielab.annoenv.db.sql.SQLDatabaseManager;
import de.julielab.annoenv.db.sql.SQLFunctions;

import de.julielab.annoenv.utils.DeleteDir;

/**
  * downloads all basedata and markable files for a project
 * creates the following folder structure:
 * - basedate (contains all BDs as URI.xml)
 * - markables
 *   -- schema1 (contains all markables for this schema as URI.xml)
 *   -- schema2 (contains all markables for this schema as URI.xml)
 */
public class DownloadBasedataMarkables {

	private SQLDatabaseManager sdm;
	private File tmpDir;
	private File bdDir;
	private File mkDir;

	public DownloadBasedataMarkables(SQLDatabaseManager sdm, File tmpDir) {
		this.sdm = sdm;
		this.tmpDir = tmpDir;
		bdDir = new File(tmpDir.getAbsolutePath() + File.separator + "Basedata");
		mkDir = new File(tmpDir.getAbsolutePath() + File.separator + "Markables");
	}
	
	
	/**
	 * checks if subdirectories called 'Basedata' and 'Markables' exist
	 */
	public boolean checkForExistingFiles(){	
		return bdDir.exists() && mkDir.exists();
	}
	
	/**
	 * exports all basedata that has been set to "done" status
	 */
	public void doIt(int projectID, boolean overwrite){

		/*
		 * check whether directory exists
		 */
		if (!tmpDir.isDirectory() || !tmpDir.canRead() || !tmpDir.canWrite()) {
			throw new RuntimeException("directory does not exist or is not writable: "
							+ tmpDir.toString());
		}
  
		/*
		 * or if a directories 'Basedata' and 'Markables' exist, delete only those
		 */
		if(overwrite){
			if(checkForExistingFiles()){
				boolean success = DeleteDir.deleteDirectory(bdDir) && DeleteDir.deleteDirectory(mkDir);
				if(!success) {
					throw new RuntimeException("could not delete Basedata or Markables directory");
				}
			}
		}
		
		/*
		 * get project data (might take a while)
		 */
		SQLFunctions sf = new SQLFunctions(sdm);
		AnnoFull project = sf.getFullProject(projectID);

		/*
		 * make directories
		 * if directory already exists, this does not work (exception)
		 */
		boolean mkdirSuccess = true;

		mkdirSuccess = (new File(bdDir.toString())).mkdir();
		if (mkdirSuccess == false) {
			throw new RuntimeException("could not create Basedata directory");
		}

		mkdirSuccess = (new File(mkDir.toString())).mkdir();
		if (mkdirSuccess == false) {
			throw new RuntimeException("could not create Markables directory");
		}

		File[] levelDirs = new File[project.getMmaxdata().size()];

		for (int i = 0; i < project.getMmaxdata().size(); i++) {
			MmaxData level = project.getMmaxdata().get(i);
			levelDirs[i] = new File(mkDir.getAbsoluteFile() + File.separator
					+ level.getLevelName());
			mkdirSuccess = (new File(levelDirs[i].toString())).mkdir();
			if (mkdirSuccess == false) {
				throw new RuntimeException(
						"could not create directory for level: "
								+ level.getLevelName());
			}
		}

		/*
		 * loop over BD and markables and do the export
		 */
		ArrayList<BaseDataFull> basedata = null;

			basedata = project.getBaseDataFull();
		for (BaseDataFull bd : basedata) {
			String uri = bd.getUri();

			// export BD (only if set to done)
			if (bd.getStatus().equals("done")) {
				bd.writeToDisk(bdDir.toString() + File.separator + uri + ".xml", sdm);

				// export markables
				ArrayList<Markable> markables = bd.getMarkables();
				for (Markable m : markables) {
					m.writeToDisk(mkDir.toString() + File.separator
							+ m.getLevelName() + File.separator + uri + ".xml", sdm);
				}

			}
		}
	}

	
	/**
	 * main method for debugging
	 */
	public static void main(String[] args) throws Exception {

		if (args.length != 2) {
			System.err
					.println("usage: DownloadBasedataMarkables <project id> <output directory>");
			System.exit(-1);
		}

		int pid = (new Integer(args[0])).intValue();
		File outputDir = new File(args[1]);

		SQLDatabaseManager sdm = new SQLDatabaseManager();
		sdm.setConnection();
		DownloadBasedataMarkables down;
		try {

			down = new DownloadBasedataMarkables(sdm, outputDir);
			System.out.println("Downloading data for project " + pid);
			down.doIt(pid, true);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Successfully downloaded data!");

	}
}