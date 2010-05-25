/** 
 * IOUtils.java
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

package de.julielab.annoenv.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

public class IOUtils {

	/**
	 * writes the content of a string into a file with the specified filename
	 * 
	 * @param content
	 *            the string to write
	 * @param filename
	 *            the filename, full path
	 */
	private static Logger logger = AnnoEnvLogger
			.getLogger("de.julielab.annoenv.db.annodata.IOUtils");

	public static void writeFile(String content, File filename)
			{
		FileWriter fw = null;
		try {
			fw = new FileWriter(filename);
			fw.write(content);
		} catch (IOException e) {
			throw new RuntimeException("Error writing to file: " + filename, e);
		} finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void writeFile(ArrayList<String> contents, File filename)
			throws IOException {
		FileWriter fw = null;
		try {
			fw = new FileWriter(filename);
			for (String content : contents) {
				fw.write(content + "\n");
			}
		} catch (IOException e) {
			throw new IllegalStateException("Error writing to file: " + filename, e);
		} finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * reads file into ArrayList. Each line is one element (String).
	 * 
	 * @param filename
	 *            full path
	 */
	public static ArrayList<String> readFile(File filename) {
		final ArrayList<String> lines = new ArrayList<String>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filename));
			String line = "";
			while ((line = br.readLine()) != null) {
				lines.add(line);
			}
			br.close();
		} catch (IOException e) {
			throw new IllegalStateException("Error reading file: " + filename,e);
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return lines;
	}

	public static StringBuilder readFile2StringBuffer(File filename) {
		StringBuilder lines = new StringBuilder();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filename));
			String line = "";
			while ((line = br.readLine()) != null) {
				lines.append(line);
			}
		} catch (IOException e) {
			throw new IllegalStateException("Error reading file: " + filename,e);
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return lines;
	}
}
