/** 
 * IOEvaluator.java
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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.julielab.annoenv.utils.Constants;

/**
 * see IOBEvaluator for description
 */
public class IOEvaluator {

	public String getType() {
		return "IO tags";
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
		double[] eval = new double[] { recall, precision, fscore };
		return eval;
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

		int[] values = getValues(goldIOB, evalIOB);

		int numcorr = values[0];
		int numans = values[1];
		int numref = values[2];

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
		double[] eval = new double[] { recall, precision, fscore };
		return eval;

	}

	int[] getValues(ArrayList<String> gold, ArrayList<String> eval) {

		if (gold.size() != eval.size()) {
			throw new IllegalStateException(
					"error!, gold.size!=eval.size -> I quit!");
		}

		HashMap<String, String> gold_chunks = getChunksIO(getTagList(gold));
		HashMap<String, String> eval_chunks = getChunksIO(getTagList(eval));

		// System.out.println(gold_chunks);
		// System.out.println(eval_chunks);

		int numcorr = 0;
		int numans = eval_chunks.size();
		int numref = gold_chunks.size();

		// now check the blocks
		for (Map.Entry<String, String> entry : eval_chunks.entrySet()) {
			String offset = entry.getKey();
			if (gold_chunks.containsKey(offset)) {
				String tags_eval = entry.getValue();
				String tags_gold = gold_chunks.get(offset);
				if (tags_eval.equals(tags_gold)) {
					numcorr++;
					// System.out.println ("hit! " + offset + ": " + tags_eval);
				}
			}
		}

		int[] values = new int[] { numcorr, numans, numref };

		/*
		 * you can calculate R/P/F from values array this way: precision =
		 * numcorr / (double) numans; recall = numcorr / (double) numref; fscore =
		 * 2 * precision * recall / (precision + recall);
		 */

		return values;
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

	static HashMap<String, String> getChunksIO(ArrayList<String> taglist) {
		int begin = -1;
		int end = -1;

		HashMap<String, String> blocks = new HashMap<String, String>();
		String old_tag = Constants.OUTSIDE_LABEL;
		String curr_tag = "";
		for (int i = 0; i < taglist.size(); i++) {
			curr_tag = taglist.get(i);

			if (curr_tag.equals(old_tag)) {
				// we are inside the same entity ... do nothing
			} else {
				// tags change

				// if we came from an entity: save old one
				if (begin > -1) {
					end = i - 1;

					StringBuilder info = new StringBuilder("");
					for (int j = begin; j < end + 1; j++) {
						// System.out.println (j + ": " +
						// (String)taglist.get(j));
						if (info.length() > 0)
							info.append("#");
						info.append(taglist.get(j));

					}
					// System.out.println (info + ": " + begin + "/" + end);
					blocks.put(begin + "," + end, info.toString());
				}

				// reset offsets
				if (!curr_tag.equals(Constants.OUTSIDE_LABEL)) {
					begin = i; // if a new entity starts
				} else {
					begin = -1; // if we are outside
				}
				end = -1;

			}
			old_tag = curr_tag;
		}
		return blocks;
	}

	/**
	 * reads in an IOFile and returns an ArrayList of the labels
	 * 
	 * @param inFile
	 * @return
	 * @throws IOException
	 */
	public static ArrayList<String> readIOBFile(File inFile) throws IOException {
		BufferedReader br = null;
		ArrayList<String> lines = null;
		try {
			lines = new ArrayList<String>();
			br = new BufferedReader(new FileReader(inFile));
			String line = "";
			while ((line = br.readLine()) != null) {
				String[] values = line.split("\t");
				lines.add(values[1]);
			}
		} finally {
			try {
				br.close();
			} catch (IOException e) {
			}
		}
		return lines;
	}

}
