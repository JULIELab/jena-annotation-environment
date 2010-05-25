/** 
 * DeleteDir.java
 * 
 * Copyright (c) 2007, JULIE Lab. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0 
 *
 * Author: tomanek
 * 
 * Current version: 0.97 	
 * Since version:   0.7
 *
 * Creation date: Aug 01, 2006
 * 
 * 
 */

package de.julielab.annoenv.utils;

import java.io.File;

public class DeleteDir {



	/**
	 * delete the whole directory with all subdirectories and 
	 * files (recursive method)
	 * @param path the compete path (directory)
	 * @return true if successfull
	 */
	static public boolean deleteDirectory(File path) {
		if (path.exists()) {
			File[] files = path.listFiles();
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
					deleteDirectory(files[i]);
				} else {
					files[i].delete();
				}
			}
		}
		boolean success = path.delete();
		return success;
	}
	
	/**
	 * this does not delete the given directory but 
	 * all files and subdirectories it containes.
	 * @param path
	 * @return
	 */
	static public boolean deleteContents(File path) {
		boolean success=true;
		if (path.exists()) {
			File[] files = path.listFiles();
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
					success = deleteDirectory(files[i]);
				} else {
					files[i].delete();
				}
			}
		}
		return success;
	}
}
