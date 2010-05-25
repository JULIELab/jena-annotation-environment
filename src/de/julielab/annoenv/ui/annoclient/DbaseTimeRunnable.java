/** 
 * DbaseTimeRunnable.java
 * 
 * Copyright (c) 2007, JULIE Lab. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0 
 *
 * Author: Klaue
 * 
 * Current version: 2.0
 * Since version:   0.9
 *
 * Creation date: June, 2007 
 * 
 **/

package de.julielab.annoenv.ui.annoclient;



import javax.swing.JButton;

import de.julielab.annoenv.db.annodata.AnnoCore;
import de.julielab.annoenv.db.sql.SQLFunctions;
import de.julielab.annoenv.ui.GUIMessages;

/**
 * Estimated AL selection time.
 */
public class DbaseTimeRunnable implements Runnable {
	private JButton button;

	private SQLFunctions sdf;

	private AnnoCore annoProject;

	public DbaseTimeRunnable(JButton button, SQLFunctions sdf,
			AnnoCore annoProject) {
		this.button = button;
		this.sdf = sdf;
		this.annoProject = annoProject;
	}

	public void run() {
		try {
			button.setText(Integer.valueOf(
					sdf.estimateSelectionTime(annoProject.getProjectID()))
					.toString()
					+ " min");
		} catch (RuntimeException e) {
			GUIMessages.exceptionMessage(e);
		}
	}

}
