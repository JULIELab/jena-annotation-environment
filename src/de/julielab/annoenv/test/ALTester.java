/** 
 * ALTester.java
 * 
 * Copyright (c) 2007, JULIE Lab. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0 
 *
 * Author: tomanek
 * 
 * Current version: 0.9 	
 * Since version:   0.7
 *
 * Creation date: Aug 01, 2006 
 * 
 * This is a (probably very outdated) class to thest the AL server.
 * As the called classes want to access /data/data this function 
 * cannot be run on a normal desktop pc but should rather be
 * run on a server with access to this data.
 * 
 */
package de.julielab.annoenv.test;

import java.io.File;
import java.io.InputStream;
import java.sql.SQLException;

import javax.xml.parsers.ParserConfigurationException;

import de.julielab.annoenv.al.ALHandler;
import de.julielab.annoenv.utils.InputStreamHandler;
import de.julielab.annoenv.utils.settings.ActiveLearningSettings;


//TODO unclear why use this

public class ALTester {

	public static void main (String[] args) throws Exception {

		
		ALHandler al;
		System.out.println("AL Tester...");
		try {
			ActiveLearningSettings alSettings = new ActiveLearningSettings();
			System.out.println(alSettings.AL_RAWCMD);
			
			al = new ALHandler(null);

			al.getData();
			al.writeSettings();

			al.runAL();
			al.uploadData();
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
			System.err.println("error in AL Tester");
		}
		
	}
	
	
//	public static void testSSH () {
//		try {
//			
//			String c2 = "ls -al";
//			String cmd = "ssh  supreme " + c2;
//
//			System.out.println(cmd);
//			
//			
//			
//			
//			Process proc = Runtime.getRuntime().exec(cmd);
//
//			StringBuffer inBuffer = new StringBuffer();
//			InputStream inStream = proc.getInputStream();
//			InputStreamHandler stdin = new InputStreamHandler( inBuffer, inStream );
//
//			StringBuffer errBuffer = new StringBuffer();
//			InputStream errStream = proc.getErrorStream();
//			InputStreamHandler stderr = 	new InputStreamHandler( errBuffer , errStream );
//
//			int i = proc.waitFor();
//			if (i!=0) {
//				System.err.println("there was an error...");
//				System.err.println("[STDERR]" + stderr.getStreamText());
//				System.err.println("[STDIN]" + stdin.getStreamText());
//			} else {
//				System.out.println("all ok");
//			}
//		} catch (Exception e) {
//			System.err.println("error in AL Tester");
//		}
//	}
	
	
		
}
