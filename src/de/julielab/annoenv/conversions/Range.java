/** 
 * Range.java
 * 
 * Copyright (c) 2007, JULIE Lab. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0 
 *
 * Author: muehlhausen
 * 
 * Current version: 2.0
 * Since version:   0.7
 *
 * Creation date: Oct 17, 2006
 * 
 */

package de.julielab.annoenv.conversions;

/**
 * Class that represents float-Intervall
 */
public class Range {

	private float begin;

	private float end;

	public float getBegin() {
		return begin;
	}

	public void setBegin(float begin) {
		this.begin = begin;
	}

	public float getEnd() {
		return end;
	}

	public void setEnd(float end) {
		this.end = end;
	}

	public String toString() {
		return "Begin: " + begin + "  End: " + end;
	}


	public boolean equals(Object arg0) {
		if (arg0 instanceof Range) {
			Range range = (Range) arg0;
			if (range.begin == this.begin && range.end == this.end) {
				return true;
			}
			return false;
		}
		return false;
	}


	public int hashCode() {
		return this.toString().hashCode();
	}

}
