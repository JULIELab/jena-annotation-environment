/** 
 * AnnoClientSettings.java
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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Properties;
import java.util.Scanner;

import de.julielab.annoenv.utils.FlexibleFilenameFilter;

/**
 * All settings relevant for the annoclient (e.g. MMax Libs, session dir etc) A
 * settings file is read at startup.
 */
public class AnnoClientSettings {



	/*
	 * default values, might get overwritten when properties are read in.
	 */

	public final static String MMAX_MEM_DEFAULT = "256m";

	private final static String VERSION = "V. 2.0";

	// public final static String PROPERTIES_FILE = File.separator +
	// "annoclient.properties";
	public final static String PROPERTIES_FILE = "settings" + File.separator
			+ "annoclient.properties";

	public static String RELEASE_STRING = "JANE V 2.0(May 2010)";

	public File SESSION_DIR = null;

	// the interval when to refresh the AL log on the annoclient GUI
	public int AL_REFRESH_INTERVAL = 5;

	// the interval when the markables should be saved automatically (in
	// seconds)
	// should not be set to a value < 60 !
	public int AUTOSAVE_INTERVAL = 60;

	public String MMAX_LIBS = "";

	public String MMAX_MEM = "";

	public String MMAX_RAWCMD;

	public String BASE_DIR = "";

	public String ANNOCLIENT_ABOUT_HTML = "";

	public String ANNOCLIENT_DOCU_HTML = "";

	public AnnoClientSettings(int mode) {

	}

	public AnnoClientSettings() {

		
		// read in default properties file
		InputStream in = null;
		Properties props = null;
		props = new Properties();

		try {
			in = new FileInputStream(PROPERTIES_FILE);
			props.load(in);

		} catch (FileNotFoundException e1) {
			throw new IllegalStateException("Could not find anno client settings file: "
					+ PROPERTIES_FILE, e1);
		} catch (IOException e) {
			throw new IllegalStateException("anno client settings could not be loaded",
					e);
		}

		// try {
		// now assign the settings
		if (props.containsKey("ac.session_dir")) {
			SESSION_DIR = new File(props.getProperty("ac.session_dir"));
			checkSessionDir();
		}

		if (props.containsKey("ac.refresh_interval")) {
			AL_REFRESH_INTERVAL = (new Integer(props
					.getProperty("ac.refresh_interval"))).intValue();
		}

		if (props.containsKey("mmax.mem")) {
			MMAX_MEM = "-Xmx" + props.getProperty("mmax.mem");
		} else {
			MMAX_MEM = "-Xmx" + MMAX_MEM_DEFAULT;
		}

		// define MMAX libraries
		if (props.containsKey("mmax.jars")) {
			MMAX_LIBS = props.getProperty("mmax.jars");
			String[] jars = MMAX_LIBS.split("" + File.pathSeparatorChar);
			checkMmaxLibs(jars);
		} else {
			System.err
					.println("ERR: error reading anno client settings: MMAX libraries not specified!");
			System.exit(-1);
		}

		if (props.containsKey("autosave.interval")) {
			AUTOSAVE_INTERVAL = (new Integer(props
					.getProperty("autosave.interval"))).intValue();
		}

		if (props.containsKey("base_dir")) {
			BASE_DIR = props.getProperty("base_dir");
			checkBaseDir();
		} else {
			throw new RuntimeException(
					"error reading anno client settings: base dir not specified");
		}

		// } catch (CriticalConfigurationException e) {
		// System.err.println("ERROR: " + e.getMessage());
		// System.exit(-1);
		// }

		// define MMAX Comand
		MMAX_RAWCMD = "java " + MMAX_MEM + " -cp " + MMAX_LIBS
				+ " org.eml.MMAX2.core.MMAX2 ";

		// define html pages
		ANNOCLIENT_ABOUT_HTML = BASE_DIR + File.separator + "doc"
				+ File.separator + "html" + File.separatorChar
				+ "about_annoclient.html";
		ANNOCLIENT_DOCU_HTML = BASE_DIR + File.separator + "doc"
				+ File.separator + "html" + File.separator
				+ "docu_annoclient.html";

	}

	public void checkMmaxLibs(String[] jars) {
		for (String newJar : jars) {
			if (!(new File(newJar)).isFile()) {
				throw new RuntimeException(
						"MMAX jars wrong, following MMAX lib does not exist: "
								+ newJar);
			}
		}
	}

	public void checkBaseDir() {
		File tmp = new File(BASE_DIR);
		if (!tmp.isDirectory()) {
			throw new RuntimeException("specified base dir does not exist: "
					+ BASE_DIR);
		}
	}

	public void checkSessionDir() {
		if (SESSION_DIR.toString().length() == 0 || !SESSION_DIR.isDirectory()) {
			throw new IllegalArgumentException(
					"specified session directory does not exist: "
							+ SESSION_DIR);
		}
	}

	public String getMMAXLibs(File libDir) {
		if (!libDir.isDirectory()) {
			throw new RuntimeException("MMAX lib directory does not exist: "
					+ libDir.toString());
		}

		File[] jars = libDir.listFiles(new FlexibleFilenameFilter("jar"));
		StringBuffer classpath = new StringBuffer();
		for (File jar : jars) {
			String spacer = "";
			spacer = (classpath.length() > 0) ? (File.pathSeparator) : "";
			classpath.append(spacer + jar);
		}

		return classpath.toString();

	}

	public void showSettings() {
		System.out.println("AnnoClient settings are: ");
		System.out.println("* base dir: " + BASE_DIR);
		System.out.println("* mmax memm: " + MMAX_MEM);
		System.out.println("* mmax libs: " + MMAX_LIBS);
		System.out.println("* mmax cmd: " + MMAX_RAWCMD);

		System.out.println("* al refresh interval: " + AL_REFRESH_INTERVAL);
		System.out.println("* session dir: " + SESSION_DIR);

	}

	private void setSettings() {
		AnnoClientSettings settings = new AnnoClientSettings(0);
		System.out
				.println("\n--------------------------------------------------");
		System.out.println("    JANE + " + VERSION
				+ " -- AnnoClient Configuration");
		System.out
				.println("--------------------------------------------------");
		System.out.println("Press 'enter' to accept default settings\n");

		// read new values
		String base_dir;
		String session_dir;
		String mmax_dir;
		Scanner in = new Scanner(System.in);

		while (true) {
			System.out
					.print("1. Base directory of JANE (full path, must end with '/'): ");
			base_dir = in.nextLine();
			settings.BASE_DIR = base_dir;
			settings.checkBaseDir();
			break;
		}

		while (true) {
			System.out.print("2. Directory for tempory files ("
					+ settings.SESSION_DIR + "): ");
			session_dir = in.nextLine();
			if (session_dir.length() != 0) {
				settings.SESSION_DIR = new File(session_dir);
			}
			settings.checkSessionDir();
			break; // go to next property
		}

		while (true) {
			System.out
					.print("3. Directory with MMAX libraries (no defaults available): ");
			mmax_dir = in.nextLine();
			if (mmax_dir.length() != 0) {
				settings.MMAX_LIBS = settings.getMMAXLibs(new File(mmax_dir));
				break;
			}
		}

		// store new values
		Properties newProps = new Properties();
		newProps.setProperty("base_dir", settings.BASE_DIR);
		newProps.setProperty("mmax.jars", settings.MMAX_LIBS);
		newProps.setProperty("mmax.mem", settings.MMAX_MEM);
		newProps.setProperty("ac.session_dir", settings.SESSION_DIR.toString());
		newProps.setProperty("ac.refresh_interval",
				settings.AL_REFRESH_INTERVAL + "");
		newProps.setProperty("autosave.interval", settings.AUTOSAVE_INTERVAL
				+ "");

		URL propsURL = settings.getClass().getResource(PROPERTIES_FILE);
		OutputStream out = null;
		try {
			out = new FileOutputStream(new File(propsURL.getFile()), false);
		} catch (FileNotFoundException e) {
			System.err.println("Error: cannot write to properties file ("
					+ PROPERTIES_FILE + ").");
			System.exit(-1);
		}
		try {
			newProps.store(out, "");
			out.close();
		} catch (IOException e) {
			System.err.println("Error: cannot writing toproperties file ("
					+ PROPERTIES_FILE + ").\n");
			e.printStackTrace();
			System.exit(-1);
		}

		System.out.println("\nAnnoclient successfully configured!\n");

	}

	/**
	 * configure the settings for annoclient my calling the main method
	 */
	public static void main(String[] args) {
		AnnoClientSettings a = new AnnoClientSettings(0);
		a.setSettings();

	}

}
