/** 
 * ALTimes.java
 * 
 * Copyright (c) 2007, JULIE Lab. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0 
 *
 * Author: tomanek
 * 
 * Current version: 2.0
 * Since version:   0.7
 *
 * Creation date: Jan 30, 2007 
 * 
 
 **/

package de.julielab.annoenv.statistics;

/**
 * object that stores the times needed for AL
 */
public class ALTimes {

	int projectID;

	int seltime;

	int traintime;

	int traindatatime;

	int testtime;

	int testdatatime;

	public ALTimes(int projectID, int a, int b, int c, int d, int e) {
		this.projectID = projectID;
		this.seltime = a;
		this.traintime = b;
		this.traindatatime = c;
		this.testtime = d;
		this.testdatatime = e;
	}

	public String toString() {
		return "sel: " + seltime + "  train: " + traintime + "  traindata: "
				+ traindatatime + "  test: " + testtime + "  testdata: "
				+ testdatatime;
	}
}
