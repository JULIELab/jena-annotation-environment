/** 
 * LogEntry.java
 * 
 * Copyright (c) 2007, JULIE Lab. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0 
 *
 * Author: tomanek
 * 
 * Current version: 0.9 	
 * Since version:   0.7
 *
 * Creation date: Aug 01, 2006
 * 
 * an object for entries in the log table of the db
 */

package de.julielab.annoenv.db.sql;

import java.util.Vector;

public class LogEntry {

	public String level;
	public String message;
	public String sourceClass;
	public String sourceMethod;
	public String timeEntered;
	public int sequence;
	
	public LogEntry (String level, String message, String sourceClass, String sourceMethod, String timeEntered, int sequence) {
		this.level = level;
		this.message = message;
		this.sourceClass = sourceClass;
		this.sourceMethod = sourceMethod;
		this.timeEntered = timeEntered;
		this.sequence = sequence;

		
	}
	
	public String toString() {
		return level + "\t" + message + "\t" + timeEntered;
	}
	
	public Vector<String> toVector(){
		final Vector<String> v = new Vector<String>();
		v.add(this.level);
		v.add(this.message);
		v.add(this.sourceClass);
		v.add(this.sourceMethod);
		v.add(this.timeEntered);
		v.add((Integer.valueOf(this.sequence)).toString());
		return v;
	}
}
