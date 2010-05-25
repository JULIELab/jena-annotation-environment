/** 
 * ALHandlerTest.java
 * 
 * Copyright (c) 2008, JULIE Lab. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0 
 *
 * Author: tomanek
 * 
 * Current version: 2.0
 * Since version:   1.2
 *
 * Creation date: Jan 22, 2008 
 * 
 * 
 **/

package de.julielab.annoenv.test;

import java.io.File;

import de.julielab.annoenv.al.ALHandler;

/**
 * this tests just the ALHandler without the need to run the ALServer (which
 * otherwise starts the ALHandler thread)
 * 
 * To run the test modify the tmpDir and the PID accordingly (must be an AL
 * project).
 * 
 * The following data needs to be accessible (maybe has to be mounted by
 * sshfs?): project's POS, BIN, and TOK corpus files
 * 
 */
public class ALHandlerTest {

	public static void main(String[] args) throws Exception {
		int pid = 162;
		File tmpDir = new File("/tmp");
		ALHandler handler = new ALHandler(tmpDir, pid);
		handler.testRun();
	}
}
