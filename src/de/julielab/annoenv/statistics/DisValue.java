/** 
 * DisValue.java
 * 
 * Copyright (c) 2007, JULIE Lab. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0 
 *
 * Author: tomanek
 * 
 * Current version: 2.0	
 * Since version:   0.7
 *
 * Creation date: Aug 01, 2006
 * 
 * 
 */

package de.julielab.annoenv.statistics;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * helper object which stores one disagreement value along with the nr of tokens
 * and sentences annotated, the project id and the time it was created
 */
public class DisValue {

	int projectID;

	int sentences;

	int tokens;

	double seldis;

	double alldis;

	double nodis;

	float corpus_frac;

	Timestamp timeEntered;

	DecimalFormat df;

	public DisValue(int projectID, int sentences, int tokens, double seldis,
			double alldis, double nodis, Timestamp timeEntered, float frac) {
		this.projectID = projectID;
		this.sentences = sentences;
		this.tokens = tokens;
		this.seldis = seldis;
		this.alldis = alldis;
		this.nodis = nodis;
		this.timeEntered = timeEntered;
		this.corpus_frac = frac;
		df = (DecimalFormat) NumberFormat.getNumberInstance(Locale.US);
		df.setMinimumFractionDigits(6);
		/*
		 * here we chose the US locale as gnuplot expects this locale for
		 * numbers to be plotted!
		 */
	}

	public String toString() {
		String s = projectID + "\t" + sentences + "\t" + tokens + "\t"
				+ df.format(seldis) + "\t" + df.format(alldis) + "\t"
				+ df.format(corpus_frac) + "\t" + timeEntered;
		return s;
	}

	public String getPlotString() {
		String s = sentences + "\t" + tokens + "\t" + df.format(seldis) + "\t "
				+ df.format(alldis) + "\t" + df.format(corpus_frac);
		return s;
	}

}
