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

import java.awt.Container;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Enumeration;
import java.util.Properties;

import javax.swing.JComponent;

import de.julielab.annoenv.ui.GUIMessages;

/**
 * Annomaster tool tips settings. Contains also a function to generate tooltips.
 * Tooltip text is stored in annomaster.properties.
 */
public class AnnoMasterTooltips {

	private static final String TOOLTIPS_FILE = "settings" + File.separator
			+ "annomaster.tooltips";

	private Properties props = null;

	/**
	 * this is a convenience method to instantiate the tooltips. If tooltip
	 * properties file cannot be found, empty tooltips are generated.
	 */
	public static AnnoMasterTooltips instantiateAnnoMasterTooltips() {
		AnnoMasterTooltips tooltips = null;
		try {
			tooltips = new AnnoMasterTooltips();
		} catch (Exception e) {
			System.err.println("Tooltips disabled: " + e.getMessage());
			tooltips = new AnnoMasterTooltips(null);
		}

		return tooltips;
	}

	public AnnoMasterTooltips() {
		// read in default properties file
		InputStream in = null;
		props = new Properties();

		try {
			in = new FileInputStream(TOOLTIPS_FILE);
			props.load(in);

		} catch (FileNotFoundException e1) {
			throw new IllegalStateException(
					"Could not find tooltips settings file: " + TOOLTIPS_FILE,
					e1);
		} catch (IOException e) {
			throw new IllegalStateException(
					"tooltips settings could not be loaded", e);
		}
	}

	/**
	 * just a dummy constructor to be used when tooltips cannot be instantiated
	 * 
	 * @param dummy
	 *            can be null (is ignored anyways)
	 */
	public AnnoMasterTooltips(Object dummy) {
		props = new Properties();
	}

	public void showSettings() {
		System.out.println("AnnoMaster tooltips: ");
		for (Enumeration e = props.elements(); e.hasMoreElements();) {
			System.out.println(e.nextElement().toString());
		}
	}

	public String getSetting(String source) {
		String result = "";
		if ((source != null) && (props != null) && (props.containsKey(source)))
			result = props.getProperty(source, "err");
		return result;
	}

	public void setDefaultSettings(Container container) {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(
					"/home/klaue/Workspace/AnnoEnv"
							+ "/settings/annomaster.tooltips.new", true));
			Field[] fields = container.getClass().getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				Field f = fields[i];
				String s = f.getType().getSimpleName();
				if (s.equals("JButton") || s.equals("JLabel")
						|| s.equals("JTextField") || s.equals("JTextArea")
						|| s.equals("JRadioButton")) {
					out.write(f.getDeclaringClass().getName() + "."
							+ f.getName() + " = n/a");
					out.newLine();
					System.out.println(f.getName());
					System.out.println(f.getType().getSimpleName());
				}
			}

			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void readTooltips(Container container) {
		Field[] fields = container.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field f = fields[i];
			String s = f.getType().getSimpleName();
			JComponent j = null;
			f.setAccessible(true);
			// System.out.println(f.getName());
			// System.out.println(f.getType());
			// System.out.println(f.isAccessible());
			try {
				if (s.equals("JButton") || s.equals("JLabel")
						|| s.equals("JTextField") || s.equals("JTextArea")
						|| s.equals("JRadioButton")) {
					j = (JComponent) f.get(container);
					String componentClassName = f.getDeclaringClass().getName()
							+ "." + f.getName();
					String tooltipText = getSetting(componentClassName);
					j.setToolTipText(tooltipText);
					if (j.getToolTipText().equals("")) {
						j.setToolTipText(null);
						// System.err.println(j.getClass().getSimpleName()+" no
						// tooltip for this one");
					}
				}
			} catch (Exception e) {
				throw new IllegalStateException(
						"Reading tooltips for current container failed.", e);
			}
		}
	}

	/**
	 * for testing purposes
	 */
	public static void main(String[] args) {
		new AnnoMasterTooltips();
	}

}