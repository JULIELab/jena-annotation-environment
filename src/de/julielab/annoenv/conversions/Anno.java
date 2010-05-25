/** 
 * Anno.java
 * 
 * Copyright (c) 2007, JULIE Lab. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0 
 *
 * Author: muehlhausen
 * 
 * Current version: 0.9 	
 * Since version:   0.7
 *
 * Creation date: Oct 17, 2006 
 * 
 *  This class represents an annotation (in a primitive way).
 */

package de.julielab.annoenv.conversions;

import java.util.List;
/**
 * @deprecated
 *	TODO check and remove this class
 */
public class Anno {

	private float begin;
	private float end;
	private String type;
	
	public Anno(float begin, float end, String type) {
		this.begin = begin;
		this.end = end;
		this.type = type;
	}
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * Returns true if intervall of this anno is an real subset of the interval of the oldAnno, 
	 * 	i.e. it is possible that one boundery of the annotations are equal but not both 
	 * @param oldAnno
	 * @return true if this anno is nested in oldAnno
	 */
	public boolean isNestedIn(Anno oldAnno) {
		
		if (this.begin >= oldAnno.getBegin() && this.end <= oldAnno.getEnd() && oldAnno.getSpan() > this.getSpan()) {
			return true;
		}
		return false;
	}
	
	/**
	 * @return Returns the span of the Anno which can differ from the Length of the Anno in tokens (or words)
	 */
	public float getSpan() {
		
		return end - begin;
	}
	
	/**
	 * @param oldAnno
	 * @return true if the Annos intersect and are not nested
	 */
	public boolean intersects(Anno oldAnno) {
		
		if (this.begin > oldAnno.getBegin() && this.end > oldAnno.getEnd()) {
			return true;
		}
		if (this.begin < oldAnno.getBegin() && this.end < oldAnno.getEnd()) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj.getClass().equals(this.getClass())) {
			if (((Anno)obj).begin == this.begin
				&& ((Anno)obj).end == this.end
				&& ((Anno)obj).type.equals(this.type)) {
				
				return true;
				}
		}
			
		return false;
	}
	
	/**
	 * Checks if this Anno has a higher Priority as otherAnno using a prioList
	 * @param oldAnno
	 * @param prioList
	 * @return true, if this anno has a higher priority as otherAnno
	 */
	public boolean hasHigherPrio(Anno otherAnno, List<String> prioList) {
		
		if (prioList.contains(otherAnno.type) && prioList.contains(this.type)) {
				
			int thisAnnoIndex = prioList.indexOf(this.type);
			int otherAnnoIndex = prioList.indexOf(otherAnno.type);
			if (thisAnnoIndex < otherAnnoIndex) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		
		return "Begin: " + begin + "  End: " + end + "  Type: " + type;
	}
	
	
}
