/** 
 * PolynomialFitTest.java
 * 
 * Copyright (c) 2007, JULIE Lab. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0 
 *
 * Author: tomanek
 * 
 * Current version: 1.0 	
 * Since version:   1.0
 *
 * Creation date: Jan 29, 2007 
 * 
 * 
 **/

package de.julielab.annoenv.test;

import java.sql.SQLException;

import javax.xml.parsers.ParserConfigurationException;

import de.julielab.annoenv.db.sql.SQLDatabaseManager;
import de.julielab.annoenv.db.sql.SQLFunctions;

import de.julielab.annoenv.utils.PolynomialFit;
import junit.framework.TestCase;

public class PolynomialFitTest extends TestCase {

	public void testEstimate() throws InstantiationException, IllegalAccessException, 
	SQLException, ClassNotFoundException, ParserConfigurationException {
		SQLDatabaseManager sdm = new SQLDatabaseManager();
		SQLFunctions sf = new SQLFunctions(sdm);
	
		sf.estimateSelectionTime(157);
	}

	public void testExtrapolate() {
		  
		    try{
		    	
		  	  double[] x = {1, 2, 3, 4,5,6,7};
			  double[] y = {1139, 1453, 1335, 1187, 1516, 1458, 1420};
			  double new_x = 8 ;
	
			  double new_y = PolynomialFit.extrapolate(x,y,new_x);
			  System.out.println(new_y);
				
			}
			catch(Exception e)
			  {
			    System.out.println("Error : "+e.getMessage()+"\n");
			    e.printStackTrace();
			    }
		  }
		
	
	
}
