/** 
 * PolynomialFit.java
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
 * Creation date: Jan 30, 2007 
 * 

 * 
 **/

package de.julielab.annoenv.utils;

import jamlab.Polyfit;

/**
 * A function for the extrapolation of new values given some already measured
 * values. It is basically used to predict the AL selection time of a new round
 * based on the previous rounds.
 */
public class PolynomialFit {

	public static double extrapolate(double[] x, double[] y, double new_x) {

		if (x.length != y.length) {
			throw new IllegalStateException(
					"polyfit error: number of x-values different from number of y-values.");
		}

		if (x.length > 2) {
			int order = 1;
			// strange: maximal possible order (x.length-1) results in very bad
			// estimations

			double new_y = 0;

			try {
				Polyfit polyfit = new Polyfit(x, y, order);
				double[] coeff = polyfit.getPolynomialCoefficients();

				for (int i = 0; i < coeff.length; i++) {
					double tmp = Math.pow(new_x, i)
							* coeff[coeff.length - i - 1];
					new_y += tmp;
				}

			} catch (Exception e) {
				throw new RuntimeException("polyfit error.", e);
			}

			return new_y;
		} else {
			return 0;
		}
	}

}
