/** 
 * MMAXStreamHandler.java
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

package de.julielab.annoenv.ui.annoclient.MMAX;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Handles MMAX output (stdout and stderr) stream so that it can be shown inside
 * the anno client.
 */
public class MMAXStreamHandler extends Thread {

	final private InputStream inStream;

	final private ArrayList<String> lines;

	/*
	 * just a hack to see if instantiated returns true if instantiated,
	 * otherwise an exception is thrown
	 */

	public boolean isInstantiated() {
		return true;
	}

	public MMAXStreamHandler(InputStream stream) {
		inStream = stream;
		lines = new ArrayList<String>();
	}

	public synchronized ArrayList<String> getLines() {
		if (lines == null) {
			throw new RuntimeException("MMAXStreamHandler error!");
		}

		ArrayList<String> a = (ArrayList<String>) lines.clone();
		lines.clear();
		return a;
	}

	public void run() {
		try {
			int nextChar;
			final StringBuffer line = new StringBuffer();
			while ((nextChar = inStream.read()) != -1) {
				addChar(nextChar, line);
			}
		} catch (IOException ioe) {
			throw new RuntimeException("MMAXStreamHandler error!",ioe);
		}
	}

	private synchronized void addChar(final int nextChar,
			final StringBuffer line) {
		if (nextChar == 10) {
			// if there is a line break: new line = char(10);
			// System.out.println("mmx:" + line.toString());
			lines.add(line.toString());
			line.delete(0, line.length());
		} else {
			line.append((char) nextChar);
		}
	}
}
