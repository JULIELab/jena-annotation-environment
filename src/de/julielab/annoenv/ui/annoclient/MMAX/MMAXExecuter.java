/** 
 * MMAXExecuter.java
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

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Logger;

import de.julielab.annoenv.utils.AnnoEnvLogger;

import de.julielab.annoenv.utils.settings.AnnoClientSettings;

/**
 * This class is a wrapper for the MMAX2 Annotation tool. MMAX is run as a
 * separate process (this has historical reasons as earlier (until 2007), MMAX
 * was not open-source).
 */
public class MMAXExecuter extends Thread {

	private static Logger logger = AnnoEnvLogger
			.getLogger("de.julielab.annoenv.ui.annoclient.MMAXExecuter");

	private MMAXStreamHandler errStreamHandler;

	private final AnnoClientSettings acSettings;

	File mmaxFile = null;

	boolean normal;

	public MMAXExecuter(File mmaxFile, AnnoClientSettings acSettings) {
		this.mmaxFile = mmaxFile;
		this.acSettings = acSettings;
		normal = true;
	}

	public void run() {
		normal = runMmax(mmaxFile);
	}

	public boolean isNormalExit() {
		return normal;
	}

	/**
	 * runs MMAX as a {@link Process} executing a {link Runtime} object.
	 * 
	 * @param file
	 *            a File object with the path to the *.mmax file where the
	 *            annotation project is described
	 * @return <code>true</code> if the MMAX Process finished successfully,
	 *         <code>false</code> otherwise
	 */
	public boolean runMmax(final File file) {

		final String path = file.getAbsolutePath();
		int status = -1;

		// String cmd = "java " + AnnoClientConfigurator.MMAX_MEM + " -cp " +
		// AnnoClientConfigurator.MMAX_LIBS + " org.eml.MMAX2.core.MMAX2 " +
		// path;
		final String cmd = acSettings.MMAX_RAWCMD + " " + path;
		System.out.println("Starting MMAX: " + cmd);

		try {
			final Process proc = Runtime.getRuntime().exec(cmd);

			/*
			 * hier passierte früher manchmal ein Fehler: errStreamHandler wird
			 * nicht instanziiert... ich weiss aber nicht, warum und v.a. dies
			 * der Fall ist. Speziell auf den Poolrechnern taucht dieses Problem
			 * immer wieder auf (so gut wie immer), wohingegen es auf meinem
			 * Arbeitsplatzrechner und z.B. auf supreme oder armstrong sehr
			 * selten passiert!
			 */
			final InputStream errStream = proc.getErrorStream();
			errStreamHandler = new MMAXStreamHandler(errStream);
			errStreamHandler.start();

			try {
				status = proc.waitFor();

				if (status == 0) {
					logger.fine("Mmax ended successfully!");
					return true;
				}

				logger.severe("Mmax ended abnormally!");
				return false;
			}

			catch (InterruptedException e) {
				throw new RuntimeException("MMAX executing was interrupted",e);
			}

		} catch (IOException e) {
			throw new RuntimeException("error executing MMAX.",e);
		}
	}

	public ArrayList<String> getMMaxErrors() {
		ArrayList<String> errors;
		if (errStreamHandler == null) {
			errors = new ArrayList<String>();
			// we do not throw the following errors any more as it
			// it not critical not to have the errStreamHandler instantiated
			// logger.severe("errStreamHandler not instantiated");
			// System.err.println("errStreamHandler not instantiated... report
			// administrator!");
			// throw new AnnoPlatformException("Critical error: errStreamHandler
			// not instantiated. Do not annotate as annotations might get lost.
			// Please report administrator!");
		} else {
			// System.err.println("errStreamHandler oK");
			errors = errStreamHandler.getLines();
		}
		return errors;
	}

}
