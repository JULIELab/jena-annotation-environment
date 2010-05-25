/** 
 * AnnoMasterSettings.java
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

/**
 * Class for general settings. DB settings are removed since version 0.96
 * Annoclient Docu was moved to AnnoClientSettings in version 0.97
 * AnnoMasterSettings emerged from BasicSettings
 */
public class AnnoMasterSettings {

	private final static String VERSION = "V. 2.0";

	// private final static String PROPERTIES_FILE = File.separator +
	// "annomaster.properties";
	private final static String PROPERTIES_FILE = "settings" + File.separator
			+ "annomaster.properties";

	public String BASE_DIR = "";

	public File TMP_DIR = new File("/tmp/JANE");

	public String POS_DIR = "resources" + File.separator + "POS"
			+ File.separator; // relative to base dir

	public String ANNOMASTER_ABOUT_HTML = "doc" + File.separator + "html"
			+ File.separator + "about_annomaster.html";

	public String ANNOMASTER_DOCU_HTML = "doc" + File.separator + "html"
			+ File.separator + "docu_annomaster.html";

	/**
	 * just to get an DatabaseSettings object
	 * 
	 * @param mode
	 *            just put '0' (or anything else) here
	 */
	public AnnoMasterSettings(int mode) {

	}

	public AnnoMasterSettings() {

		// read in default properties file
		InputStream in = null;
		Properties props = null;
		props = new Properties();

		try {
			in = new FileInputStream(PROPERTIES_FILE);
			props.load(in);

		} catch (FileNotFoundException e1) {
			throw new IllegalStateException(
					"Could not find anno master settings file: "
							+ PROPERTIES_FILE, e1);
		} catch (IOException e) {
			throw new IllegalStateException(
					"anno master settings could not be loaded", e);
		}

		// now go through setting parameters
		if (props.containsKey("base_dir")) {
			BASE_DIR = props.getProperty("base_dir");
			checkBaseDir();
		} else {
			throw new RuntimeException(
					"annomaster settings file misses value for: BASE directory");
		}

		if (props.containsKey("tmp_dir")) {
			TMP_DIR = new File(props.getProperty("tmp_dir"));
		}

		if (props.containsKey("pos_dir")) {
			POS_DIR = props.getProperty("pos_dir");
			checkPosDir();
		} else {
			throw new RuntimeException(
					"annomaster settings file misses value for: POS directory");
		}

		if (props.containsKey("html.annomaster_about")) {
			ANNOMASTER_ABOUT_HTML = BASE_DIR
					+ props.getProperty("html.annomaster_about");
			checkAnnomasterAboutHTML();
		} else {
			throw new RuntimeException(
					"annomaster settings file misses key/value: html.annomaster_about");
		}

		if (props.containsKey("html.annomaster_docu")) {
			ANNOMASTER_DOCU_HTML = BASE_DIR
					+ props.getProperty("html.annomaster_docu");
			checkAnnomasterDocuHTML();
		} else {
			throw new RuntimeException(
					"annomaster settings file misses key/value: html.annomaster_docu");
		}

	}

	private void checkBaseDir() {
		File tmp = new File(BASE_DIR);
		if (!tmp.isDirectory()) {
			throw new RuntimeException("specified base dir does not exist: "
					+ BASE_DIR);
		}
		if (!BASE_DIR.substring(BASE_DIR.length() - 1).equals("/")) {
			throw new RuntimeException(
					"base dir must end with '/', e.g. '/my/complete/path/to/JANE/'");
		}
	}

	private void checkPosDir() {
		File tmp = new File(POS_DIR);
		if (!tmp.isDirectory()) {
			throw new RuntimeException("specified POS dir does not exist: "
					+ POS_DIR);
		}
		if (!POS_DIR.substring(POS_DIR.length() - 1).equals("/")) {
			throw new RuntimeException("pos dir must end with '/', e.g. 'pos/'");
		}
	}

	private void checkAnnomasterAboutHTML() {
		File tmp = new File(ANNOMASTER_ABOUT_HTML);
		if (!tmp.isFile()) {
			throw new RuntimeException("annomaster-about-file does not exist!");
		}
	}

	private void checkAnnomasterDocuHTML() {
		File tmp = new File(ANNOMASTER_DOCU_HTML);
		if (!tmp.isFile()) {
			throw new RuntimeException("annomaster-docu-file does not exist!");
		}
	}

	private static void setSettings() {
		AnnoMasterSettings settings = new AnnoMasterSettings(0);
		System.out
				.println("\n--------------------------------------------------");
		System.out.println("     JANE " + VERSION
				+ " -- AnnoMaster Configuration");
		System.out
				.println("--------------------------------------------------");
		System.out.println("Press 'enter' to accept default settings\n");

		// to be configured
		String base_dir = "";
		String pos_dir = "";
		String tmp_dir = "";

		// read new values
		Scanner in = new Scanner(System.in);

		while (true) {
			System.out
					.print("1. Base directory of JANE (full path, must end with '/'): ");
			base_dir = in.nextLine();
			settings.BASE_DIR = base_dir;
			settings.checkBaseDir();
			break;
		}

		System.out.print("3. Directory for temporary files (default: "
				+ settings.TMP_DIR + "): ");
		tmp_dir = in.nextLine();
		if (tmp_dir.length() > 0) {
			settings.TMP_DIR = new File(tmp_dir);
		}

		while (true) {
			System.out
					.print("3. Directory with POS dictionary (relative to base dir, must end with '/', default: "
							+ settings.POS_DIR + "): ");
			pos_dir = in.nextLine();
			if (pos_dir.length() > 0) {
				settings.POS_DIR = settings.BASE_DIR + pos_dir;
			} else {
				settings.POS_DIR = settings.BASE_DIR + settings.POS_DIR;
			}
			settings.checkPosDir();
			break;
		}

		// store new values
		Properties newProps = new Properties();
		newProps.setProperty("base_dir", settings.BASE_DIR);
		newProps.setProperty("tmp_dir", settings.TMP_DIR.toString());
		newProps.setProperty("pos_dir", settings.POS_DIR);
		newProps.setProperty("html.annomaster_about",
				settings.ANNOMASTER_ABOUT_HTML);
		newProps.setProperty("html.annomaster_docu",
				settings.ANNOMASTER_DOCU_HTML);

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

		System.out.println("\nAnnoMaster successfully configured!\n");

	}

	public static void main(String[] args) {
		setSettings();
	}

}
