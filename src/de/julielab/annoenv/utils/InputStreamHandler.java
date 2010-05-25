/** 
 * InputStreamHandler.java
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
 * Taken from hacks.oreilly.com. Thanks a lot, this helps!
 * 
 */

package de.julielab.annoenv.utils;

import java.io.IOException;
import java.io.InputStream;


public class InputStreamHandler extends Thread {

	private InputStream m_stream;

	/**
	 * The StringBuffer holding the captured output
	 */

	private StringBuffer m_captureBuffer;

	/**
	 * Constructor.
	 * 
	 * @param
	 */

	public InputStreamHandler(StringBuffer captureBuffer, InputStream stream) {
		m_stream = stream;
		m_captureBuffer = captureBuffer;
		start();
	}

	
	public String getStreamText() {
		return m_captureBuffer.toString();
	}

	public void run() {
		try {
			int nextChar;
			while ((nextChar = m_stream.read()) != -1) {
				m_captureBuffer.append((char) nextChar);
			}
		} catch (IOException ioe) {
			throw new RuntimeException("error running inputstream handler",ioe);
		}
	}
}