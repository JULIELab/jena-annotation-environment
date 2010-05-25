/** 
 * ALChecksTest.java
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
 * Creation date: Jan 29, 2007 
 * 
 * //TODO insert short description
 **/

package de.julielab.annoenv.test;

import de.julielab.annoenv.al.ALChecks;
import junit.framework.TestCase;

public class ALChecksTest extends TestCase {

	public void testCheckCommittee() {
		String committee = "CRF,CRF,CRF";
		boolean chk = ALChecks.isValidCommittee(committee);
		assertTrue(chk);
		
		committee = "CRF,CRF";
		chk = ALChecks.isValidCommittee(committee);
		assertFalse(chk);
		
		committee = "CRF,NB,HMM";
		chk = ALChecks.isValidCommittee(committee);
		assertFalse(chk);
	}
	
	public void testCheckTrainProportion() {
		String committee = "CRF,CRF,CRF";
		double prop = 0.9;
		boolean chk = ALChecks.isValidTrainProportion(committee, prop);
		assertFalse(chk);
		
		prop = 0.8;
		committee = "CRF,CRF,CRF";
		chk = ALChecks.isValidTrainProportion(committee, prop);
		assertTrue(chk);
		
		prop = 0.4;
		committee = "CRF,CRF,CRF";
		chk = ALChecks.isValidTrainProportion(committee, prop);
		assertFalse(chk);
		
		prop = 1;
		committee = "CRF,ME,NB";
		chk = ALChecks.isValidTrainProportion(committee, prop);
		assertTrue(chk);
		
		
	}
	
}
