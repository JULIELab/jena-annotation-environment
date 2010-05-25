/** 
 * ActiveLearnigSettings.java
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
 * 
 */

package de.julielab.annoenv.utils.settings;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * All settings relevant for AL. A settings file is read at startup.
 */
public class ActiveLearningSettings {

	// private final static String PROPERTIES_FILE = File.separator +
	// "activelearning.properties";
	private final static String PROPERTIES_FILE = "settings" + File.separator
			+ "activelearning.properties";

	// for the AL tmp dir
	public static File ALSERVER_TMPDIR = new File("/tmp/");

	// for the AL server
	public static int ALSERVER_PORT = 9999;

	public static String ALSERVER_HOST = "supreme";

	public static String ALSERVER_USER = "annomaster";
	
	// for the AL process
	public static String AL_PROCESS_SERVER = "anno0";

	public static String AL_PATH = "/home/annomaster/programs/AnnoEnv";

	public static String AL_MEMORY = " -Xmx20000m ";

	static String AL_PATHBIN = "";

	static String AL_PATHLIB = "";

	static String AL_BINCLASS = "";

	static String AL_LOGGER = "";

	static String AL_CLASSPATH = "";

	public static String AL_RAWCMD = "";

	public final static int AL_MINSENTLEN = 5;

	public ActiveLearningSettings() {

		// read in default properties file
		InputStream in = null;
		Properties props = null;
		props = new Properties();

		try {
			in = new FileInputStream(PROPERTIES_FILE);
			props.load(in);

		} catch (FileNotFoundException e1) {
			throw new IllegalStateException("Could not find AL settings file: "
					+ PROPERTIES_FILE, e1);
		} catch (IOException e) {
			throw new IllegalStateException("AL settings could not be loaded",
					e);
		}

		// read in the user defineable settings
		
		if (props.containsKey("alserver.user")) {
			ALSERVER_USER = props.getProperty("alserver.user");
		}
		
		if (props.containsKey("alserver.tmpdir")) {
			ALSERVER_TMPDIR = new File(props.getProperty("alserver.tmpdir"));
		}

		if (props.containsKey("alserver.port")) {
			ALSERVER_PORT = (new Integer(props.getProperty("alserver.port")))
					.intValue();
		}

		if (props.containsKey("alserver.host")) {
			ALSERVER_HOST = props.getProperty("alserver.host");
		}

		if (props.containsKey("al.path")) {
			AL_PATH = props.getProperty("al.path");
		}

		if (props.containsKey("al.mem")) {
			AL_MEMORY = "-Xmx" + props.getProperty("al.mem");
		}

		if (props.containsKey("al.process-server")) {
			AL_PROCESS_SERVER = props.getProperty("al.process-server");
		}

		// create needed settings from it
		// the AnnoEnv Libraries (including AnnoEnv's AL libs)
		AL_PATHBIN = AL_PATH + File.separator + "lib" + File.separator
				+ "AnnoEnv.jar";

		AL_PATHLIB = AL_PATH + File.separator + "lib";
		AL_BINCLASS = "de.julielab.annoenv.al.ALProcess";
		AL_LOGGER = "-Djava.util.logging.config.file=" + AL_PATH
				+ File.separator + "settings" + File.separator
				+ "logging.properties";
		AL_CLASSPATH = AL_PATHLIB + File.separator + "*:.";
		
		//TODO old: full jars formulated; new version above works only with newer form of Java (>= 1.5)
//		AL_CLASSPATH = AL_PATHLIB + File.separator + "EntityAL-Libs-2.0.jar"
//		+ ":" + AL_PATHLIB + File.separator + "mallet.jar" + ":"
//		+ AL_PATHLIB + File.separator + "mallet-deps.jar" + ":"
//		+ AL_PATHLIB + File.separator + "UEAstemmer.jar" + ":"
//		+ AL_PATHLIB + File.separator
//		+ "mysql-connector-java-3.1.12-bin.jar" + ":" + AL_PATH
//		+ File.separator + "settings" + ":" + AL_PATHBIN;
		
		AL_RAWCMD = " java " + AL_MEMORY + " -cp " + AL_CLASSPATH + " "
		+ AL_BINCLASS;
		
		//AL_RAWCMD = " java " + AL_MEMORY + " -cp " + AL_CLASSPATH + " "
		//		+ AL_LOGGER + " " + AL_BINCLASS;

	}

	public void showSettings() {
		System.out.println("Active Learning settings are: ");

		System.out.println("* AL server tmp: " + ALSERVER_TMPDIR);
		System.out.println("* AL server host: " + ALSERVER_HOST);
		System.out.println("* AL server port: " + ALSERVER_PORT);
		System.out.println("* AL server user: " + ALSERVER_USER);
		System.out.println("* AL process server: " + AL_PROCESS_SERVER);
		System.out.println("* AL libraries path: " + AL_PATH);
		System.out.println("* AL memory: " + AL_MEMORY);
		System.out.println("* AL command: " + AL_RAWCMD);

	}

	/**
	 * for testing purposes
	 */
	public static void main(String[] args) {
		new ActiveLearningSettings();
	}
}
