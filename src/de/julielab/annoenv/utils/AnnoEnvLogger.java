/** 
 * AnnoEnvLogger.java
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
 * 
 * The logger used in Annoenv. 
 * 
 */

package de.julielab.annoenv.utils;

import java.util.logging.*;

/**
 * if an config-file is provide via the Java Property method, that one is used,
 * otherwise the hard-coded default logging behaviour is used.
 * 
 * to specify the file add the following VM argument:
 * -Djava.util.logging.config.file=<logging.properties>
 */

public class AnnoEnvLogger extends Logger {

	static public Level[] LoggingLevels = { Level.OFF, Level.SEVERE,
			Level.WARNING, Level.INFO, Level.CONFIG, Level.FINE, Level.FINER,
			Level.FINEST, Level.ALL };

	// this function is not really used but it has to be there, otherwise we'll
	// get an error
	protected AnnoEnvLogger(String name, String resourceBundleName) {
		super(name, resourceBundleName);
	}

	/**
	 * create a new logger, set two handlers and the log levels
	 * 
	 * @param name
	 *            the name of the new logger (use fully specified class name)
	 * @return
	 */
	public static Logger getLogger(String name) {

		Logger myLogger = Logger.getLogger(name);

		if (System.getProperty("java.util.logging.config.file") == null) {

			// if no logging config file was specified, we use the default
			// logging options
			myLogger.setLevel(Level.INFO);

			// KT: we do not need to set the console handler explicitly since
			// java logging tool will log to the console anyways (don't know
			// why); the code below is commented since it causes duplicate
			// console logging. If problems with console logging appear, you may
			// want to uncomment the code again.

			/*
			 * ConsoleHandler ch = new ConsoleHandler();
			 * myLogger.addHandler(ch);
			 */

			// add JDBCLogHandler
			JDBCLogHandler jdbcLogHandler = null;
			try {
				jdbcLogHandler = new JDBCLogHandler();
			} catch (Exception e) {
				System.err
						.println("Problem instantiating the database logger!");
				e.printStackTrace();
				System.exit(-1);
			}
			myLogger.addHandler(jdbcLogHandler);
		}

		return myLogger;
	}

}
