/** 
 * ProjectEditionTest.java
 * 
 * Copyright (c) 2007, JULIE Lab. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0 
 *
 * Author: tomanek
 * 
 * Current version: 1.2 	
 * Since version:   1.2
 *
 * Creation date: Sep 20, 2007 
 * 
 * just an informal test to see whether extended project editing works
 * 
 * Use really carefully!
 **/

package de.julielab.annoenv.test;

import java.io.File;

import de.julielab.annoenv.db.annodata.AnnoFull;
import de.julielab.annoenv.db.annodata.MmaxData;
import de.julielab.annoenv.db.sql.SQLDatabaseManager;
import de.julielab.annoenv.db.sql.SQLFunctions;


public class ProjectEditionTest {

	public static void main(String[] args) throws Exception {
		
		int pid=137;
		SQLDatabaseManager sdm = new SQLDatabaseManager();
		sdm.setConnection();
		SQLFunctions sf = new SQLFunctions(sdm);
		AnnoFull project = sf.getFullProject(pid);
		project.writePriolistFile("/home/tomanek/tmp/jane/137_prio.lst", sdm);
		
		project.setName("new name");
		project.setDescription("new description");
		project.setPriolistFile(new File("/home/tomanek/tmp/jane/new_prio.lst"));
		
		// remove comment to really test this function
		//project.updateProject();
		//project.updatePrioList();
		
		for (MmaxData m : project.getMmaxdata()) {
			if (m.isMainLevel()) {
				m.setCustomFile(new File("/home/tomanek/tmp/jane/custom_new.xml"));
				m.setSchemaFile(new File("/home/tomanek/tmp/jane/schema_new.xml"));
				
				// remove comment to really test this function
				m.updateMmaxData(sdm);
				break;
			}
		}
		
		System.out.println("project successfully modified");
	}
}
