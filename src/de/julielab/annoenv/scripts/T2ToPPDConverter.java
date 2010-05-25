/** 
 * T2ToPPDConverter.java
 * 
 * Copyright (c) 2007, JULIE Lab. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0 
 *
 * Author: tomanek
 * 
 * Current version: 2.0
 * Since version:   1.0
 *
 * Creation date: Jun 4, 2007 
 * 
 **/

package de.julielab.annoenv.scripts;

import java.io.File;
import java.io.IOException;

import de.julielab.annoenv.conversions.T2ToPipedFormat;

/**
 * converts T2 format into PPD format.
 * @author tomanek
 *
 */
public class T2ToPPDConverter {

	public static void main (String[] args) throws NumberFormatException, 
	IOException {
		final File t2File = new File(args[0]);
		final File outputDir = new File(args[1]);
		final File ppdFile = T2ToPipedFormat.convert(outputDir, t2File, 0);
	
		System.out.println("written ppd file to: " + ppdFile.toString());
		
	}
	
}
