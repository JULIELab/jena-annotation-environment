/** 
 * DownloadBasedataMarkables.java
 * 
 * Copyright (c) 2007, JULIE Lab. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0 
 *
 * Author: klaue
 * 
 * Current version: 2.0
 * Since version:   1.1
 *
 * Creation date: Aug 18, 2009 
 * 
 **/

package de.julielab.annoenv.conversions;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import de.julielab.annoenv.db.annodata.AnnoFull;
import de.julielab.annoenv.db.annodata.MmaxData;
import de.julielab.annoenv.db.sql.SQLDatabaseManager;
import de.julielab.annoenv.db.sql.SQLFunctions;


import de.julielab.annoenv.utils.DeleteDir;

/**
  * downloads all schemata and customization files for a project
 * creates the following folder structure:
 * - mmaxdata(contains folders for all levels)
 * - each level folder consists of a customization file and a schema file
 * 
 */
public class DownloadSchemataCustom {
	public static final String MMAX_FILE_NAME = "MmaxDataFiles";
	
	private File tmpDir;
	private SQLDatabaseManager sdm;
	private File mmaxFile;
	
	
	public  DownloadSchemataCustom(SQLDatabaseManager sdm, File tmpDir) {
		this.sdm = sdm;
		this.tmpDir = tmpDir;
		mmaxFile = new File(tmpDir.getAbsolutePath() + File.separator + MMAX_FILE_NAME);
	}
	
	/**
	 * checks if subdirectories called 'Basedata' and 'Markables' exist
	 */
	public boolean checkForExistingFiles(){
		return mmaxFile.exists();
	}
	
	/**
	 * exports all levels
	 */
	public void doIt(int projectID, boolean overwrite)  {
		
		/*
		 * check whether directory exists
		 */
		if (!tmpDir.isDirectory() || !tmpDir.canRead() || !tmpDir.canWrite())
			throw new RuntimeException(
					"Error: directory does not exist or is not writable: "
							+ tmpDir.toString());
		  
		/*
		 * or if a directory MMAX_FILE_NAME exists, delete only those
		 */
		if(overwrite){
			if(checkForExistingFiles()){
				boolean success = DeleteDir.deleteDirectory(mmaxFile);
				if(!success) {
					throw new RuntimeException("could not delete '" + MMAX_FILE_NAME +  "' directory");
				}
			}
		}
		
		SQLFunctions sf = new SQLFunctions(sdm);
		AnnoFull project = sf.getFullProject(projectID);
		
		/*
		 * make directories
		 * if directory already exists, this does not work (exception)
		 */
		
		boolean mkdirSuccess = true;
		File mmaxFile = new File(tmpDir.getAbsoluteFile() + File.separator + MMAX_FILE_NAME);
		mkdirSuccess = (new File(mmaxFile.toString())).mkdir();
		if(mkdirSuccess == false){
			throw new RuntimeException("could not create MmaxDataFiles dir");
		}
		
		ArrayList<MmaxData> mmaxDataList = project.getMmaxdata();

		for (MmaxData m : mmaxDataList){
			File levelDir = new File(mmaxFile.getAbsolutePath() + File.separator + m.getLevelName());
			mkdirSuccess = mkdirSuccess && (new File(levelDir.toString())).mkdir();
			File schemaFile = m.writeSchemaFile(levelDir.toString() + File.separator + "Schema.xml", sdm);
			File custFile = m.writeCustomFile(levelDir.toString() + File.separator + "Customization.xml", sdm);
		}
		if (mkdirSuccess == false) {
			throw new RuntimeException(
							"could not create directories ");
		}
		
	}
	
	/**
	 * main method for debugging purposes
	 */
	public static void main(String[] args) throws Exception{
		int pid = 1631;
		File outputDir = new File("/tmp/");
		
		SQLDatabaseManager sdm = new SQLDatabaseManager();
		sdm.setConnection();
		DownloadSchemataCustom down;
		try{
			down = new DownloadSchemataCustom(sdm, outputDir);
			down.doIt(pid, false);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
