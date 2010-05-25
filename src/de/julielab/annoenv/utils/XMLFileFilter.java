/** 
 * XMLFileFilter.java
 * 
 * Copyright (c) 2007, JULIE Lab. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0 
 *
 * Author: Klaue
 * 
 * Current version: 0.9
 * Since version:   0.8
 *
 * Creation date: March, 2007 
 * 
 * Filter for javax.swing.FileChooser.
 **/

package de.julielab.annoenv.utils;

import java.io.File;

import javax.swing.filechooser.FileFilter;


public class XMLFileFilter extends FileFilter {

	public boolean accept(File pathname) {
        String ext = null;
        String s = pathname.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        else
        	ext = "";
		if(ext.equals("xml") || ext.equals("xsl") || pathname.isDirectory())
			return true;
		else
			return false;
	}

	public String getDescription() {
		return "xml files or directories";
	}

}
