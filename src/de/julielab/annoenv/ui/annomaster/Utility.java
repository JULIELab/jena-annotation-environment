/** 
 * Utility.java
 * 
 * Copyright (c) 2007, JULIE Lab. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0 
 *
 * Author: Klaue
 * 
 * Current version: 2.0
 * Since version:   0.8
 *
 * Creation date: January, 2007 
 * 

 **/

package de.julielab.annoenv.ui.annomaster;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;

import de.julielab.annoenv.db.annodata.MmaxData;
import de.julielab.annoenv.db.sql.LogEntry;
import de.julielab.annoenv.db.sql.SQLDatabaseManager;
import de.julielab.annoenv.db.sql.SQLFunctions;

/**
 * Collection of some GUI utility methods.
 */
public class Utility {
	public static Vector<Vector<String>> convertToVectorOfVectors(
			ArrayList<LogEntry> list) {
		final Vector<Vector<String>> data = new Vector<Vector<String>>();
		for (int i = 0; i < list.size(); i++) {
			data.add(list.get(i).toVector());
		}
		return (data);
	}

	public static JFrame getJFrame(Component c) {
		if ((c instanceof JFrame) || (c == null)) {
			return (c == null) ? null : (JFrame) c;
		}
		return getJFrame(c.getParent());
	}

	/*
	 * public static ArrayList getDefaultProjectsOnly(ArrayList allProjects){
	 * ArrayList list = new ArrayList(); for(int i=0; i<allProjects.size();
	 * i++){ if(allProjects.get(i) instanceof DefaultAnnoProject)
	 * list.add(allProjects.get(i)); } return list; }
	 */


	public static MmaxData getMainLvl(SQLDatabaseManager sdm, int projectID) {
		MmaxData d = null;
		ArrayList<MmaxData> list = null;
		list = new SQLFunctions(sdm)
				.getMmaxDataList(projectID);
		for (int i = 0; i < list.size(); i++) {
			d = list.get(i);
			if (d.isMainLevel()) {
				break;
			}
		}
		return d;
	}
}
