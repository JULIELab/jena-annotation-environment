/** 
 * ALErrors.java
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
 * Creation date: Aug 01, 2006 
 */

package de.julielab.annoenv.al;

//TODO is this class still needed ?
/**
 * This function is copied from the package de.julielab.al.utils.ALError. One
 * day we might want to either integrate the whole package de.julielab.al into
 * this one or at least find a better solution than copying the error messages.
 * At this point at least I don't want the create more dependencies, thus this
 * hack.
 */
public class ALErrors {
	public static String getErrorMessage(int e) {

		String message = "unknown error code: " + e;

		switch (e) {

		// wrong programm calls/usage
		case 101:
			message = "log-properties file not specified correctly.";
			break;
		case 102:
			message = "incorrect usage of AL, parameters missing.";
			break;
		case 103:
			message = "could not read directory or files provided to AL programm";
			break;

		// error with corpus reader
		case 201:
			message = "could not read binary corpus.";
			break;

		// error reading pipe format
		case 301:
			message = "sentence mal-formatted, PipeFormat not correct.";
			break;

		// instance error
		case 401:
			message = "error in AL process";
			break;

		case 501:
			message = "JDBC Logger could not be initialised as connection to DB not possible!";
			break;
		}
		return message;
	}
}
