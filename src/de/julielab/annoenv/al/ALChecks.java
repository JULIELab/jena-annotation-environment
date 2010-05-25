/** 
 * ALChecks.java
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
 * Creation date: Jan 29, 2007 
 * 
 **/

package de.julielab.annoenv.al;

/**
 * this class contains methods to check whether AL projects are correct. E.g.,
 * it has a list of known classifiers and transducers which can be used for AL.
 */
public class ALChecks {

	private final static String[] KNOWN_COMMITTEE_MEMBERS = { "CRF", "MEMM",
			"ME", "NB" };

	private final static double PROP_LOWER_BOUND = 0.5;

	private final static double PROP_UPPER_BOUND = 0.8;

	/**
	 * check whether the committee string has correct format and whether all
	 * learning paradigm in the committee are known and accepted.
	 * 
	 * It is expected that there are three committee members in comma-separated
	 * form.
	 * 
	 * @param committee
	 *            format of committee string should be: "ALGO,ALGO,ALGO"
	 * @return
	 */
	public static boolean isValidCommittee(String committee) {

		String[] members = committee.split(",");

		if (members.length != 3) {
			System.err.println("number of committee members incorrect");
			return false;
		}

		for (int i = 0; i < members.length; i++) {
			if (!knownAlgo(members[i])) {
				System.err.println("algorithm '" + members[i]
						+ "' not allowed/known.");
				return false;
			}
		}
		return true;
	}

	/**
	 * checks whether the training proportion (i.e. the portion of the already
	 * annotated material used to train a classifier) is ok. Requirements: - if
	 * we have a mixed committee, [0.5,1] is ok - if we have a homogeneous
	 * committee, [0.5,0.8] is ok
	 * 
	 * @param committee
	 * @param prop
	 * @return
	 */
	public static boolean isValidTrainProportion(String committee, double prop) {

		if (prop < PROP_LOWER_BOUND) {
			System.err.println("training proportion must be >= "
					+ PROP_LOWER_BOUND);
			return false;
		}

		String[] members = committee.split(",");

		if (members[0].equals(members[1]) && members[1].equals(members[2])) {
			// homogeneous committee
			if (prop > PROP_UPPER_BOUND) {
				System.err.println("training proportion must be <= "
						+ PROP_UPPER_BOUND);
				return false;
			}
		} else {
			// heterogeneous committee
			if (prop > 1) {
				System.err.println("training proportion must be <= " + 1);
				return false;
			}
		}

		return true;
	}

	/**
	 * checks whether algo is one of the known committee members
	 */
	private static boolean knownAlgo(String algo) {
		for (int i = 0; i < KNOWN_COMMITTEE_MEMBERS.length; i++) {
			if (algo.equals(KNOWN_COMMITTEE_MEMBERS[i])) {
				return true;
			}
		}
		return false;
	}

}
