/** 
 * AddBaseDataDialogTest.java
 * 
 * Copyright (c) 2009, JULIE Lab. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0 
 *
 * Author: kampe
 * 
 * Current version: 2.0
 * Since version:   1.6
 * 
 * Creation date: 11.12.2009 
 **/

package de.julielab.annoenv.test.retrofittests;
import java.util.Vector;

import javax.xml.parsers.ParserConfigurationException;

import junit.framework.TestCase;

import de.julielab.annoenv.db.sql.SQLDatabaseManager;
import de.julielab.annoenv.ui.annomaster.AddBaseDataDialog;

/**
 * TODO insert description
 * 
 * @author kampe
 */
public class AddBaseDataDialogTest extends TestCase {

	public void testAddBaseDataDialog() throws ParserConfigurationException {

		AddBaseDataDialog inst = new AddBaseDataDialog(new Vector(), true,
				new SQLDatabaseManager());
		inst.setVisible(true);
	}

	// public AddBaseDataDialog(Vector mmaxDataVector, boolean isAL,
	// SQLDatabaseManager sdm) {
	// this.mmaxDataVector = mmaxDataVector;
	// this.substitutionVector = new Vector(); // dummies
	// this.markablesVector = new Vector();
	// for(int i=0 ; i < this.mmaxDataVector.size(); i++){
	// this.substitutionVector.add(i,"-");
	// this.markablesVector.add(i,new Object());
	// }
	// this.isAL = isAL;
	// this.sdm = sdm;
	// initGUI();
	// }
}
