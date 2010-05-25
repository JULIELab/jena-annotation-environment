/** 
 * InternalAnnoMasterFrame.java
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
 * Creation date: May, 2007
 * 
 **/

package de.julielab.annoenv.ui.annomaster;

import javax.swing.JInternalFrame;


import de.julielab.annoenv.db.sql.SQLDatabaseManager;

/**
 * Superclass to some annomaster frames. It supplies subclasses with a SQL
 * database connection and a uniform way to show exception messages.
 */
public class InternalAnnoMasterFrame extends JInternalFrame {
	SQLDatabaseManager sqlDatabaseM;

	public InternalAnnoMasterFrame(SQLDatabaseManager sdm) {
		super();
		sqlDatabaseM = sdm;
	}
}
