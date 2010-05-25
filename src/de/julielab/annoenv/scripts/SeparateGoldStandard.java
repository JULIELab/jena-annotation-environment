/** 
 * SeparateGoldStandard.java
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

package de.julielab.annoenv.scripts;

import java.io.File;
import java.io.IOException;

import java.util.Random;

/**
 * Randomly moves a given number of abstracts from the (training) corpus as the gold
 * standard corpus.
 * 
 */
public class SeparateGoldStandard {

	File trainTokDir;

	File trainPosDir;

	File goldTokDir;

	File goldPosDir;

	SeparateGoldStandard(String tTok, String tPos, String gTok, String gPos)
			throws IOException {
		trainPosDir = new File(tPos);
		trainTokDir = new File(tTok);
		goldTokDir = new File(gTok);
		goldPosDir = new File(gPos);

		if (!trainPosDir.isDirectory()) {
			throw new IOException(
					"specified training POS directory does not exist...");
		} else if (!trainTokDir.isDirectory()) {
			throw new IOException(
					"specified training TOK directory does not exist...");
		} else if (!goldPosDir.isDirectory()) {
			throw new IOException(
					"specified gold POS directory does not exist...");
		} else if (!goldTokDir.isDirectory()) {
			throw new IOException(
					"specified gold TOK directory does not exist...");
		}
	}

	void moveAbstract(int goldSize) throws IOException {

		/*
		 * assume that pos and tok contains the same filenames, this is not
		 * counterchecked, we might want to make the code more safe one day
		 */
		final long s1 = System.currentTimeMillis();
		final String[] abstractList = trainPosDir.list(); // all abstracts
		final long s2 = System.currentTimeMillis();
		System.out.println("Reading abstract list took: " + (s2 - s1) / 1000
				+ " sec");
		System.out.println("Number of abstracts in training directory: "
				+ abstractList.length);

		if (goldSize > abstractList.length) {
			throw new IOException("goldstandard size > number of abstracts. "
					+ "use smaller goldstandard size.");
		}

		final int[] randomList = getRandomList(abstractList.length, goldSize);

		for (int i = 0; i < randomList.length; i++) {
			// now move the files from train to gold
			final String filename = abstractList[randomList[i]];

			// for the POS files
			File trainPosFile = new File(trainPosDir.toString()
					+ File.separator + filename);
			File goldPosFile = new File(goldPosDir.toString() + File.separator
					+ filename);
			// System.out.println (trainPosFile.toString() + " -> " +
			// goldPosFile.toString());
			boolean ok = trainPosFile.renameTo(goldPosFile);
			if (!ok) {
				String err = "there was an error moving the file: "
						+ trainPosFile.toString() + " -> "
						+ goldPosFile.toString() + "\n"
						+ "maybe insufficient permissions?";
				throw new IOException();
			}

			// do the same for the TOK files
			File trainTokFile = new File(trainTokDir.toString()
					+ File.separator + filename);
			File goldTokFile = new File(goldTokDir.toString() + File.separator
					+ filename);
			// System.out.println (trainTokFile.toString() + " -> " +
			// goldTokFile.toString());
			ok = trainTokFile.renameTo(goldTokFile);
			if (!ok) {
				String err = "there was an error moving the file: "
						+ trainTokFile.toString() + " -> "
						+ goldTokFile.toString() + "\n"
						+ "maybe insufficient permissions?";
				throw new IOException(err);
			}
		}

	}

	/**
	 * gets a list of random numbers out of [0,max)
	 * 
	 * @param max
	 *            the maximal number
	 * @param no
	 *            the size of the random list
	 * @return
	 */
	int[] getRandomList(int max, int no) {
		int[] randomList = new int[no];
		Random random = new Random(System.currentTimeMillis());

		for (int i = 0; i < no; ++i) {
			randomList[i] = random.nextInt(max);
		}

		return randomList;
	}

	/**
	 * to run it...
	 */

	public static void main(String[] args) {

		if (args.length != 5)
			showUsage();
		String tPos = args[0];
		String tTok = args[1];
		String gPos = args[2];
		String gTok = args[3];
		int goldSize = (new Integer(args[4])).intValue();

		SeparateGoldStandard SGS;
		try {
			SGS = new SeparateGoldStandard(tTok, tPos, gTok, gPos);
		} catch (IOException e1) {
			System.err.println(e1.getMessage());
			return;
		}

		// wait some time...
		System.out
				.println("This script MOVES abstracts from training to gold. \n"
						+ "Press ctrl-c if you do not want to continue.");
		int sleepTime = 5;
		System.out.print("Starting after " + sleepTime + " seconds");
		for (int i = 0; i < sleepTime; i++) {
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.print(".");
		}
		System.out.println("\n");

		System.out
				.println("Moving abstracts. This may take a while, be patient...");

		// now move the abstracts
		try {
			SGS.moveAbstract(goldSize);
		} catch (IOException e) {
			System.err.println(e.getMessage());
			return;
		}

		System.out.println("Moved " + goldSize + " abstracts successfully.");
	}

	static void showUsage() {
		System.err
				.println("usage: SeparateGoldStandard trainPosDir trainTokDir goldPosDir goldTokDir goldSize");
		System.exit(-1);
	}
}
