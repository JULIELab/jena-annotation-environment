/** 
 * SQLFunctionsTest.java
 * 
 * Copyright (c) 2007, JULIE Lab. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0 
 *
 * Author: tomanek
 * 
 * Current version: //TODO insert current version number 	
 * Since version:   //TODO insert version number of first appearance of this class
 *
 * Creation date: Jan 25, 2007 
 * 
 * //TODO insert short description
 **/

package de.julielab.annoenv.test;


import java.sql.SQLException;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;

import de.julielab.annoenv.db.sql.SQLDatabaseManager;
import de.julielab.annoenv.db.sql.SQLFunctions;
import de.julielab.annoenv.utils.AnnoEnvLogger;
import junit.framework.TestCase;

public class SQLFunctionsTest extends TestCase {
	private static Logger logger = AnnoEnvLogger
	.getLogger("x");
	
	public void testLogin () throws InstantiationException, IllegalAccessException, SQLException, ClassNotFoundException, ParserConfigurationException {
		SQLDatabaseManager sdm = new SQLDatabaseManager();
		sdm.setConnection();
		SQLFunctions sf = new SQLFunctions(sdm);
		try {
			sf.login("tomanek", "taomanek");
		} catch (Exception e) {
			logger.warning("not logged in: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	/*
	public void testResetProject () throws InstantiationException, IllegalAccessException, 
	SQLException, ClassNotFoundException,  ParserConfigurationException {
		
		int PID = 139;
		
		SQLDatabaseManager sdm = new SQLDatabaseManager();
		sdm.setConnection();
		SQLFunctions sf = new SQLFunctions(sdm);
		try {
			sf.resetProject(PID);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
/*

	/*
	public void testLogin () throws InstantiationException, IllegalAccessException, SQLException, ClassNotFoundException, AnnoPlatformException {
		SQLDatabaseManager sdm = new SQLDatabaseManager();
		sdm.setConnection();
		SQLFunctions sf = new SQLFunctions(sdm);
		try {
			int userID = sf.login("katrin", "katrin");
		System.out.println(userID);
		sf.logout(userID);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/
	
	public void testCumulatedEntities() throws Exception {
/*
		CumulatedEntityStatistics cum =  new CumulatedEntityStatistics(126);
		//cum.go(1);
		
		ArrayList<String> sents = new ArrayList<String>();
		sents.add(".	O"); // 1
		sents.add("X	O"); 
		sents.add("A1	A");	
		sents.add("O	O"); // 2
		sents.add("B1	B");
		sents.add("A1	A");
		sents.add(""); 	     // 3
		sents.add("A1	A");
		sents.add("X	O");
		sents.add("B2	B");
		sents.add("A3	A");
		sents.add("A3	A");
		sents.add("");
		sents.add("A1	A");
		sents.add("B1	B");
		sents.add("C1	C");
		sents.add("D1	D");
		sents.add("");
		//cum.getChunks(sents);
		//System.out.println(cum.countDistinctEntities(cum.getChunks(sents),2));
		cum.makePlot();
		*/
	}
	
	
	/*
	public void testResetDefaultProject() throws Exception {
		SQLDatabaseManager sdm = new SQLDatabaseManager();
		sdm.setConnection();
		SQLFunctions sf = new SQLFunctions(sdm);
		sf.resetProject(123);
		
	}
	*/
	
	/*
	public void testStatisticsAdd() throws InstantiationException, IllegalAccessException, SQLException, ClassNotFoundException, AnnoPlatformException {
		SQLDatabaseManager sdm = new SQLDatabaseManager();
		sdm.setConnection();
		SQLFunctions sf = new SQLFunctions(sdm);
		sf.addALStatistics(125, 0.2, 0.3, 0.4, 100, 250, 10, 20, 30, 40, 50);
		
	}
	*/
	
	/*
	public void testCopyProject() throws InstantiationException, IllegalAccessException, SQLException, ClassNotFoundException, AnnoPlatformException  {
		
		SQLDatabaseManager sdm = new SQLDatabaseManager();
		sdm.setConnection();
		SQLFunctions sf = new SQLFunctions(sdm);
		
		sf.copyProject(56, 99, "Kopiertest", "default"	);
	}
	*/
}
