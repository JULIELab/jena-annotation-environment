/** 
 * OV.java
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
 * Code from Menno Rubingh:
 * General-purpose wrapper class used for Output Value passed through
 * function argument.
 * 
 *  THIS CLASS IS USED VERY WIDELY THROUGHOUT *ALL* PARTS OF THE CODE.
 */

package de.julielab.annoenv.conversions;

//----------------------------------------------------------------------------


public class OV<T> {
	public T value;

	public OV(T val0) {
		value = val0;
	}
}
