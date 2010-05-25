/** 
 * AnnodataTest.java
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
 * Creation date: Jan 23, 2008 
 * 
 * //TODO insert short description
 **/

package de.julielab.annoenv.test;

import de.julielab.annoenv.db.annodata.AnnoCore;
import de.julielab.annoenv.db.annodata.AnnoFull;
import de.julielab.annoenv.db.annodata.AnnoLight;
import de.julielab.annoenv.db.sql.SQLDatabaseManager;
import de.julielab.annoenv.db.sql.SQLFunctions;
import junit.framework.TestCase;

public class AnnodataTest extends TestCase {

	public void testProject() throws Exception {

		int PID = 126;

		SQLDatabaseManager sdm = new SQLDatabaseManager();
		sdm.setConnection();
		SQLFunctions sf = new SQLFunctions(sdm);
		AnnoFull p = sf.getFullProject(PID);
		System.out.println(p.getBaseDataLight());
		System.out.println(p);
	}

}
