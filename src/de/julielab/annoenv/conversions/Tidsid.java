/** 
 * TidSid.java
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
 * Creation date: Okt 19, 2006
 * 
 *  
 */

package de.julielab.annoenv.conversions;

/**
 * Class that contains data for one Sentence (that is containd in the base
 * data): tid (text ID), sid (sentence ID), offset and distance .
 */
public class Tidsid {

	/**
	 * Text ID
	 */
	private int tid;

	/**
	 * Sentence ID
	 */
	private int sid;

	/**
	 * offset relative to the begin of the basedata words (tokens)
	 */
	private int offset;

	/**
	 * distance in natural numbers from begin to end of the range of words.
	 * Notice that this could differ from the number of tokens due to editing of
	 * base data!!!
	 */
	private int distance;

	/**
	 * @return Returns the distance.
	 */
	public int getDistance() {
		return distance;
	}

	/**
	 * @param distance
	 *            The distance to set.
	 */
	public void setDistance(int distance) {
		this.distance = distance;
	}

	/**
	 * @return Returns the offset.
	 */
	public int getOffset() {
		return offset;
	}

	/**
	 * @param offset
	 *            The offset to set.
	 */
	public void setOffset(int offset) {
		this.offset = offset;
	}

	/**
	 * @return Returns the sid.
	 */
	public int getSid() {
		return sid;
	}

	/**
	 * @param sid
	 *            The sid to set.
	 */
	public void setSid(int sid) {
		this.sid = sid;
	}

	/**
	 * @return Returns the tid.
	 */
	public int getTid() {
		return tid;
	}

	/**
	 * @param tid
	 *            The tid to set.
	 */
	public void setTid(int tid) {
		this.tid = tid;
	}


	public String toString() {

		return "offset: " + offset + "  distance: " + distance + "  tid: "
				+ tid + "  sid: " + sid;
	}

	/**
	 * Checks, if key is conatined in the range of the annotation
	 * 
	 * @param key
	 * @return
	 */
	public boolean contains(Float key) {

		// TODO error by one checken
		if (key >= offset && key <= (offset + distance)) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if the range is partly contained in this tidsid and not as a hole
	 * or not at all
	 * 
	 * @param annotationRange
	 * @return true if range intersects the range of the tidsid
	 */
	public boolean intersectsRage(Range annotationRange) {

		int annoBegin = (int) annotationRange.getBegin();
		int annoEnd = (int) annotationRange.getEnd();

		int tidsidBegin = this.offset;
		int tidsidEnd = tidsidBegin + this.distance;

		// anno is totally enclosed by tidsid range
		if (annoBegin >= tidsidBegin && annoEnd <= tidsidEnd) {
			return false;
		}

		// anno is totally outside of tidsid range
		if (annoBegin < tidsidBegin && annoEnd < tidsidBegin) {
			return false;
		}
		if (annoBegin > tidsidEnd && annoEnd > tidsidEnd) {
			return false;
		}

		return true;
	}

}
