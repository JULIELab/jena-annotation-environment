/** 
 * ALConversions.java
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
 */

package de.julielab.annoenv.al;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Logger;

import org.w3c.dom.Document;

import de.julielab.annoenv.conversions.MkALStyles;
import de.julielab.annoenv.conversions.MmaxDataConverter;
import de.julielab.annoenv.conversions.T2ToPipedFormat;
import de.julielab.annoenv.conversions.Tidsid;
import de.julielab.annoenv.db.sql.SQLFunctions;

import de.julielab.annoenv.utils.AnnoEnvLogger;

/**
 * This is just a wrapper class that combines and wraps methods implemented by
 * Menno Rubingh... this class's purpose is user friendlyness and
 * understandability of the code (hopefully)
 */
public class ALConversions {

	final static String FILE_T2 = "aTcurr.t2";

	private static Logger logger = AnnoEnvLogger
			.getLogger("de.julielab.annoenv.al.menno.ALConversions");

	
	/**
	 * create the needed aT.txt from all necessary (MMax) files TODO: specify
	 * needed input files here...
	 * @param sf 
	 * 
	 * @return file pointing to current aT.txt
	 * @throws IOException 
	 */
	public static File makeALFile(File t2File, Document basedataDoc,
			Document markableDoc, List<String> priolist,
			List<Tidsid> tidsidList, File corpusPosDir, File tmpDir, int PID,
			String bdUri, SQLFunctions sf)  {

		// 1) MMax2AL --> aTcurr.t2
		File t2currFile = new File(FILE_T2);


			// now run conversion
			MmaxDataConverter converter = new MmaxDataConverter();
			converter.convertToT2(t2currFile, markableDoc, basedataDoc,
					priolist, tidsidList, corpusPosDir.getAbsolutePath(),
					bdUri);
			//TODO catch runtime exception and log to db
//		} catch (MmaxConversionException e) {
//			throw new AnnoPlatformException("MMax data conversion failed: "
//					+ e.getMessage());
//		}

		// 2) concat aT.t2 and aTcurr.t2 into aT.t2
		concatFiles(t2File, t2currFile, PID);

		// 3) T2ToPipedFormat --> aT.tmp
		File aTFile = T2ToPipedFormat.convert(tmpDir, t2File, PID);

		// 4) return handle to this file
		return aTFile;
	}

	/**
	 * @param corpusTokDir
	 *            the corpus in tokenized format to take the sentences from
	 * @param sellistFile
	 *            the list.sel from which to select the sentences to put into
	 *            basedata
	 * @param levelname
	 *            the levelname to use... WHERE?
	 * @param styleFile
	 *            the new style file for the basedata to be created (OUT)
	 * @param basedataFile
	 *            the new basedata to be created (OUT)
	 * @param markableFile
	 *            the sentence level markable to be created (OUT)
	 * @param TIDSIDFile
	 *            a file denoting the association between each basedata sentence
	 *            and some TID, SID (OUT)
	 */
	public static void makeMmaxFiles(File corpusTokDir, File sellistFile,
			String levelname, File styleFile, File basedataFile,
			File markableFile, File TIDSIDFile, int PID) {

		logger.fine(PID + "#Converting AL files into MMax format.");

		PrintWriter pwStyle = null;
		PrintWriter pwBasedata = null;
		PrintWriter pwSentlvl = null;
		PrintWriter pwAlsess = null;
		try {
			pwStyle = new PrintWriter(styleFile);
			pwBasedata = new PrintWriter(basedataFile);
			pwSentlvl = new PrintWriter(markableFile);
			pwAlsess = new PrintWriter(TIDSIDFile);

			MkALStyles.createFiles(corpusTokDir, sellistFile, levelname,
						pwStyle, pwBasedata, pwSentlvl, pwAlsess, PID);
			//TODO handle runtime exceptions
//		} catch (AnnoPlatformException e) {
//				logger.severe(PID
//						+ "#Could not convert AL files into MMax format: "
//						+ e.getMessage());
		} catch (IOException e) {
			logger.severe(PID + "#File not found: " + e.getMessage());
			e.printStackTrace();
		} finally {
			pwBasedata.close();
			pwSentlvl.close();
			pwStyle.close();
			pwAlsess.close();
		}

	}

	/**
	 * adds the contents of f2 to f1
	 * 
	 * @param f1
	 * @param f2
	 */
	private static void concatFiles(File f1, File f2, int PID) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(f2));
			FileWriter fw = new FileWriter(f1, true);
			String line = "";
			while ((line = br.readLine()) != null) {
				fw.write(line + "\n");
			}
			fw.close();
			br.close();
		} catch (IOException e) {
			throw new RuntimeException("could not concatenate files...");
		} finally {
			if ( br != null ) {
				try { br.close(); } catch ( IOException e ) { e.printStackTrace(); } 
			}
		}
	}
}
