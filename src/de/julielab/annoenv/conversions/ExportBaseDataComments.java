/** 
 * ExportBaseDataComments.java
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

import java.io.BufferedWriter;
import java.io.File;

import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;

import de.julielab.annoenv.db.annodata.AnnoLight;
import de.julielab.annoenv.db.annodata.BaseDataLight;
import de.julielab.annoenv.db.sql.SQLDatabaseManager;
import de.julielab.annoenv.db.sql.SQLFunctions;


public class ExportBaseDataComments {

	private SQLDatabaseManager sdm;
	
	
	public ExportBaseDataComments(SQLDatabaseManager sdm) {
		this.sdm = sdm;
	}
	
	public void exportComments(int projectID, File f){
		
		//check whether chosen file exists and prepare it for writing
		if (!f.canRead() || !f.canWrite()) 
			throw new RuntimeException("error exporting comments: file does not exist or is not writable: "
							+ f.toString());
	
			
		final SQLFunctions sf = new SQLFunctions(sdm);
		final AnnoLight project = sf.getLightProject(projectID);
		
		ArrayList<BaseDataLight> basedata = null;

		basedata = project.getBaseDataLight();
		
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new FileWriter(f,true));
			for (BaseDataLight bd : basedata) {
				final String URI = bd.getUri();
				final String comment = bd.getDescription();
				out.write(URI + "\t" + comment);
				out.newLine();
			}
			out.close();
		} catch (IOException e) {
			throw new RuntimeException("error exporting comments",e);
		}
	}
	
}
