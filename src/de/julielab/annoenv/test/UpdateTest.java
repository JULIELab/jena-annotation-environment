/** 
 * UpdateTest.java
 * 
 * Copyright (c) 2008, JULIE Lab. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0 
 *
 * Author: tomanek
 * 
 * Current version: //TODO insert current version number 	
 * Since version:   //TODO insert version number of first appearance of this class
 *
 * Creation date: Jan 25, 2008 
 * 
 * //TODO insert short description
 **/

package de.julielab.annoenv.test;


import java.io.File;

import junit.framework.TestCase;
import de.julielab.annoenv.db.annodata.*;
import de.julielab.annoenv.db.sql.SQLDatabaseManager;
import de.julielab.annoenv.db.sql.SQLFunctions;
import de.julielab.annoenv.utils.Constants;

public class UpdateTest extends TestCase{

	public void testUpdate() throws Exception {
		int bdID = 12058;
		
		SQLDatabaseManager sdm = new SQLDatabaseManager();
		sdm.setConnection();
		SQLFunctions sf = new SQLFunctions(sdm);

		AnnoCore p = sf.getCoreProject(126);
		
		sf.resetProject(126);
		System.out.println("project: " + p.toString());
		p.setName("AL testproject");
		p.setDescription("keine");
		p.setAlBatchsize(30);
		p.setAlCommittee("ME,ME,ME");
		p.setAlStatus(Constants.AL_PROJECT_STATUS_IDLE);
		//p.setPriolistFile(new File("priolist"));
		p.updateProject(sf);
		System.out.println("project: " + p.toString());
		/*
		// update basedata
		BaseDataLight bd = sf.getBaseDataLight(bdID);

		System.out.println("bd: " + bd);
		bd.setStatus(bd.STATUS_DONE);
		bd.setDescription("hallöchen");
		bd.setAnnotationTime(222);
		bd.setUri("aber hallo");
		bd.updateBaseData();
		*/
	}
}
