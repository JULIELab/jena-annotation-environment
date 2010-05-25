/** 
 * DirectoryAccess.java
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
 * Creation date: October, 2006 
 * 
 * Utility interface to remember the path when choosing and selecting a file again and again.
 * Mainly used in CreateProject.
 * @see CreateProject
 **/

package de.julielab.annoenv.ui.annomaster;

import java.io.File;

public interface DirectoryAccess {
	public File getCurrentPath();
	
	public void setCurrentPath(File currentPath);
}
