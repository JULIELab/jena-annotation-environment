/** 
 * IOBEvaluator.java
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
 
 */

package de.julielab.annoenv.statistics;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.Map;

import de.julielab.annoenv.utils.Constants;

/**
 * needed for calculation of flat inter annotator agreement (and possibly later
 * for goldstandard AL evaluation)
 * 
 * evaluates data based on IOB format you can either directly calculate R/P/F
 * from given IOB data or let the evaluator return numcorr,numans and numrefs
 * (this is especially useful when you want to calculate overall R/P/F of
 * several files: then you calculate numansw, numcorr etc for all files and run
 * the evaluate method on the cumulated numans, numref and numcorr)
 * 
 */
public class IOBEvaluator {

	public String getType() {
		return "IOB tags";
	}

	/**
	 * calculates R/P/F from numcorr, numans, numref
	 * 
	 * @param numcorr
	 * @param numans
	 * @param numref
	 * @return double array with R/P/F
	 * @throws EvaluationException
	 */
	public double[] evaluate(int numcorr, int numans, int numref) {
		double precision = 0;
		double recall = 0;
		double fscore = 0;

		if (numans > 0) {
			precision = numcorr / (double) numans;
		}
		if (numref > 0) {
			recall = numcorr / (double) numref;
		}
		if (precision + recall > 0) {
			fscore = 2 * precision * recall / (precision + recall);
		}

		return new double[] { recall, precision, fscore };
	}

	/**
	 * calculate R/P/F from two IOB lists: goldIOB and evalIOB are arraylists of
	 * IOB-labels, sentences are to be seperated by "O" labels. the token itself
	 * is not considered here...
	 * 
	 * @param goldIOB
	 * @param evalIOB
	 * @return double array with R/P/F
	 * @throws EvaluationException
	 */
	public double[] evaluate(ArrayList<String> goldIOB,
			ArrayList<String> evalIOB) {

		final int[] values = getValues(goldIOB, evalIOB);

		final int numcorr = values[0];
		final int numans = values[1];
		final int numref = values[2];

		double precision = 0;
		double recall = 0;
		double fscore = 0;

		if (numans > 0) {
			precision = numcorr / (double) numans;
		}
		if (numref > 0) {
			recall = numcorr / (double) numref;
		}
		if (precision + recall > 0) {
			fscore = 2 * precision * recall / (precision + recall);
		}

		return new double[] { recall, precision, fscore };
	}

	/**
	 * 
	 * @param gold
	 * @param eval
	 * @return a int array with numscorr, numans, numref
	 * @throws EvaluationException
	 */
	public int[] getValues(ArrayList<String> gold, ArrayList<String> eval) {

		if (gold.size() != eval.size()) {
			throw new IllegalStateException(
					"error!, gold.size!=eval.size -> I quit!");
		}

		HashMap<String, String> gold_chunks = getChunksMulti(getTagList(gold));
		HashMap<String, String> eval_chunks = getChunksMulti(getTagList(eval));

		int numcorr = 0;
		final int numans = eval_chunks.size();
		final int numref = gold_chunks.size();

		// now check the blocks
		for (Map.Entry<String, String> entry : eval_chunks.entrySet()) {
			final String offset = entry.getKey();
			if (gold_chunks.containsKey(offset)) {
				final String tags_eval = entry.getValue();
				final String tags_gold = gold_chunks.get(offset);
				if (tags_eval.equals(tags_gold)) {
					numcorr++;
					// System.out.println ("hit! " + offset + ": " + tags_eval);
				}
			}
		}

		/*
		 * you can calculate R/P/F from values array this way: precision =
		 * numcorr / (double) numans; recall = numcorr / (double) numref; fscore =
		 * 2 * precision * recall / (precision + recall);
		 */

		return new int[] { numcorr, numans, numref };
	}

	/**
	 * converts an iob file (with token<whitespace>IOBtag) into a list of
	 * iobTags only
	 * 
	 * after a sentence an empty line ("") is inserted
	 * 
	 * @param tokenIOBList
	 * @return
	 */
	private ArrayList<String> getTagList(ArrayList<String> tokenIOBList) {
		ArrayList<String> tags = new ArrayList<String>();
		for (String line : tokenIOBList) {
			String[] values = line.split("\t");
			if (values.length == 2) {
				tags.add(values[1]);
			} else {
				// after a sentence there is a emtpy line. Add a O tag for it:
				tags.add(Constants.OUTSIDE_LABEL);
			}
		}

		return tags;

	}

	/**
	 * 
	 * @param taglist
	 *            am arraylist of IOB tags only! (no tokens, one iob tag per
	 *            line)
	 * @return
	 */
	private static HashMap<String, String> getChunksMulti(
			ArrayList<String> taglist) {
		int begin = -1;
		int end = -1;
		boolean inside = false;

		HashMap<String, String> blocks = new HashMap<String, String>();

		for (int i = 0; i < taglist.size(); i++) {
			String curr_tag = taglist.get(i);

			String curr_marker = "";
			if (curr_tag.length() != 0) {
				curr_marker = curr_tag.substring(0, 1);

				curr_tag = (curr_marker.equals(Constants.OUTSIDE_LABEL)) ? ""
						: curr_tag.substring(2, curr_tag.length());
			}

			if (!inside) {
				if (curr_marker.equals(Constants.BEGIN_LABEL)) {
					// was outside
					inside = true;
					begin = i;
					end = -1;
				}

			} else {
				// inside

				if (curr_marker.equals(Constants.BEGIN_LABEL)) {
					// new chunk
					end = i - 1;
					StringBuilder info = new StringBuilder("");

					for (int j = begin; j < end + 1; j++) {
						// System.out.println (j + ": " +
						// (String)taglist.get(j));
						if (info.length() > 0)
							info.append("#");
						info.append(taglist.get(j));
					}

					blocks.put(begin + "," + end, info.toString());
					begin = i;
					end = -1;
				} else if (curr_marker.equals(Constants.OUTSIDE_LABEL)
						|| curr_marker.equals("")) {
					// is now outside
					end = i - 1;
					StringBuilder info = new StringBuilder("");
					for (int j = begin; j < end + 1; j++) {
						// System.out.println (j + ": " +
						// (String)taglist.get(j));
						if (info.length() > 0) {
							info.append("#");
						}
						info.append(taglist.get(j));
					}
					blocks.put(begin + "," + end, info.toString());
					begin = -1;
					end = -1;
					inside = false;
				}
			}
		}
		return blocks;
	}

}
