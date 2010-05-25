/** 
 * FlexibleFilenameFilter.java
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
 * Creation date: Jan 31, 2007 
 * 
 * A filename filter: filters by the specified extension.
 **/

package de.julielab.annoenv.utils;

import java.io.File;
import java.io.FilenameFilter;

public class FlexibleFilenameFilter implements FilenameFilter {

	private String extension = "";
	
	public FlexibleFilenameFilter(String extension) {
		this.extension = extension;
	}
	
	public boolean accept(File dir, String name) {
	        return (name.endsWith("." + extension));
	    }
	
	
}
