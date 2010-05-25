/** 
 * CorpusWriter.java
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
 * Code from Menno Rubingh
 * 
 */

package de.julielab.annoenv.scripts;

// (was POS2CDF1.java by MR Apr '06)
//
// The Binary Corpus is based on the idea to have the same length for each 
// sentence and by this we can just loop through it and have fast random
// access to the single sentence.
// This is needed for AL where we quickly want to select only a fraction of
// the sentences.
//
// The Binary Corpus can be read with the CorpusReader, which is in package 
// de.julielab.al/utils
//
// Reads corpus with POS information, from file in specified directory,
// e.g. "/data/data/data_corpora/genomics/GeneOnto/abstracts/pos/"
//
// Then performs one of two actions:
//  a) -i option: Inspect all files in the input dir, print the size in bytes
//     of the longest line, this size can then be used as sentence-size!
//  b) Write the data to binary file 'corpus.data' (omit -i)

//----------------------------------------------------------------------------
// Format of file 'corpus.data':
//
// The file starts with 8 bytes, containing the length nSD of
// the sentence-data-field in the subsequent blocks.  This number is
// an unsigned integer >= 1, printed in decimals.
//
// The rest of the file consists of blocks, each as follows:
//       32 bytes = textid 
//        4 bytes = sentence-id = integer printed in decimals
//      nSD bytes = sentence data:
//                    token1 pos1 token2 pos2 ... tokenN posN
//                  with single space between items (but no space after the 
//                  last POS item in the sentence). 
//
// All three fields in the blocks, and also the nSD-field at the begin
// of the file, are TEXT, left-aligned within the block, 
// padded on the right with 0-valued bytes, and with at least one
// 0-valued byte after the text data.
//
//----------------------------------------------------------------------------

import java.io.*;

public class CorpusWriter {

	public static void OUT(String s) {
		System.out.println(s);
	}

	public static void ERR(String s) {
		System.err.println(s);
	}

	// -------------------------------------------------------------------------

	private static class FileUtils1 {
		// Return File object if dir exists, null if doesn't exist
		public static File checkExistDir(String abspath) // abs pathname of
															// dir
		{
			File f = null;
			try {
				f = new File(abspath);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			if (!f.exists()) {
				return null;
			}
			if (!f.isDirectory()) {
				return null;
			}
			return f;
		}

	}

	// For use in File.listFiles() call
	private static class RegFileFilter implements FileFilter {
		public boolean accept(File f) {
			return f.isFile();
		}
	}

	// -------------------------------------------------------------------------

	// Size of the 'header' field with the size of the field 'stdata'
	// in the blocks.
	static final int NSD_DEF_SIZE = 8;

	private static class Block {
		public static final int SIZE_TEXTID = 32;

		public static final int SIZE_SENTID = 4;

		private int size_stdata;

		private int size_block;

		int getSize_stdata() {
			return size_stdata;
		}

		int getSize_block() {
			return size_block;
		}

		public byte[] abTextID;

		public byte[] abSentID;

		public byte[] abSTData;

		public Block(int stdataSize0) {
			size_stdata = stdataSize0;
			assert (size_stdata > 0);
			size_block = (SIZE_TEXTID + SIZE_SENTID + size_stdata);
			abTextID = new byte[SIZE_TEXTID];
			abSentID = new byte[SIZE_SENTID];
			abSTData = new byte[size_stdata];
		}

	}

	// Set the contents of caller-instantiated byte array abOut[] to sIn,
	// left-aligned in the array, followed by 0-valued bytes. Always writes
	// at least one 0-valued byte at the end of the array, truncating sIn if
	// needed.
	private static void BytearrayFromString(byte abOut[], String sIn) {
		byte[] abSIn = sIn.getBytes();
		int i = 0;
		while ((i < abOut.length - 1) && (i < abSIn.length)) {
			abOut[i] = abSIn[i];
			i++;
		}
		while (i < abOut.length - 1) {
			abOut[i] = 0;
			i++;
		}
	}

	// -------------------------------------------------------------------------

	// Input string = sequence of items "token_POS" with white-space
	// between the items.
	// Convert each item "token/POS" in string to format "token POS", i.e.
	// with a space character instead of the '_'.
	// Token may contain '-' characters, but we assume that POS does not
	// contain any '-' character. I.e. we replace the LAST '-' in each
	// item by a space.
	public static String convertTokenPOSItems(String sIn, // IN
			StringBuffer sbOut) // OUT
	{
		sbOut.setLength(0); // init

	// Split input line into tokens on WS
		String as[] = sIn.split("[ \t]+");
		if (as.length == 0) {
			return "Line without tokens";
		}

		// Run through as[], append each item to sbOut with last '/'
		// replaced by space
		int iItem;
		for (iItem = 0; iItem < as.length; iItem++) {
			String sItem = as[iItem];
			assert (sItem.length() > 0);
			// DEBUG OUT( sItem );

			// Seek the 1st '/' from the end in 'sItem'
			int kSlash = -1;
			int k;
			for (k = sItem.length() - 1; k >= 0; k--) {
				if (sItem.charAt(k) == '_') {
					kSlash = k;
					break;
				}
			}
			if (!((1 <= kSlash) && (kSlash < sItem.length() - 1))) {
				return "Couldn't interpret slash in item '" + sItem + "'";
			}

			sbOut.append(sItem.substring(0, kSlash));
			sbOut.append(" ");
			sbOut.append(sItem.substring(kSlash + 1, sItem.length()));

			if (iItem < as.length - 1) {
				sbOut.append(" ");
			}

		}
		// DEBUG OUT( "" );

		return null;
	}

	public static class RPCData {
		public int llMax; // size of longest line seen so far

		public int nSentences; // # of sentences seen so far

		public RPCData() {
			llMax = 0;
			nSentences = 0;
		}
	}

	// Update rpcdata.llMax to length in bytes of longest string in input
	// (incl. POS tags), if current value of rpcdata.llMax is smaller.
	// Increment rpcdata.nSentences with the # of sentenced in the file.
	//
	// Input param 'stdataSize' = Size of the field in the block for the
	// sentence (token + POS) data.
	//
	// If fOut != null, then append blocks for the sentences to binary
	// outfile fOut.
	//
	/**
	 * @param fIn
	 * @param textID
	 * @param stdataSize
	 * @param rpcdata
	 * @param fOut
	 * @return
	 */
	public static String readPosCorpFile(File fIn, // IN
			String textID, // IN
			int stdataSize, // IN
			RPCData rpcdata, // IN+OUT
			RandomAccessFile fOut) // OUT (appended to)
	{
		if (textID.length() > Block.SIZE_TEXTID - 1) {
			return "Text-ID too long";
		}

		int llMax = 0;

		BufferedReader br = null;
		int iLine = 0;
		try {
			br = new BufferedReader(new FileReader(fIn));

			String sIn;
			while ((sIn = br.readLine()) != null) {
				String sSentID = "" + iLine; // Print in decimals to string
				if (sSentID.length() > Block.SIZE_SENTID - 1) {
					throw new Exception("Sentence-ID too long");
				}

				if (sIn.length() == 0) {
					throw new Exception("Empty line");
				}

				if (sIn.length() > rpcdata.llMax) {
					rpcdata.llMax = sIn.length();
				}

				if (sIn.length() > stdataSize - 1) {
					throw new Exception("Sentence too long");
				}

				StringBuffer sb = new StringBuffer();
				String erm = convertTokenPOSItems(sIn, sb);
				if (erm != null) {
					throw new Exception(erm);
				}

				assert (sb.length() == sIn.length());
				// DEBUG OUT( sb.toString() );

				if (fOut != null) {
					// Create new block
					Block blk = new Block(stdataSize);
					BytearrayFromString(blk.abTextID, textID);
					BytearrayFromString(blk.abSentID, sSentID);
					BytearrayFromString(blk.abSTData, sb.toString());

					// Increase outfile size by size of new block
					long offs1 = (long) (blk.getSize_block())
							* (long) (rpcdata.nSentences + 1);
					fOut.setLength((long) NSD_DEF_SIZE + offs1);

					// Write new block to outfile
					fOut.write(blk.abTextID);
					fOut.write(blk.abSentID);
					fOut.write(blk.abSTData);
				}

				iLine++;
				rpcdata.nSentences += 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "Fail read file '" + fIn.getName() + "' (line "
					+ (iLine + 1) + ") : " + e.getMessage();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return null; // success
	}

	// -------------------------------------------------------------------------

	private static void usage() {
		ERR("Usage: CorpusWriter POS-dir sentenceSize "
				+ "outputFilePath [options]");
		ERR("Options:");
		ERR("   -v  Verbose");
		ERR("   -i  Only Inspect input, do not create corpus-data file");
	}

	/**
	 * the main method
	 * args[0] = inputdirpath
	 * args[1] = stdataSize (sentenceSize)
	 * args[2] = outcorpuspath
	 * options
	 */
	public static void main(String[] args) {
		if (args.length < 3) {
			usage();
			System.exit(-1);
		}

		String inputdirpath = args[0];

		int stdataSize = 3036; // = (3*1024) - (32 + 4)
		try {
			stdataSize = Integer.parseInt(args[1]);
		} catch (Exception e) {
			e.printStackTrace();
			usage();
			System.exit(-1);
		}
		if (stdataSize < 1) {
			usage();
			System.exit(-1);
		}

		String outcorpuspath = args[2];

		boolean _v = false;
		boolean _i = false;
		int iOpt;
		for (iOpt = 3; iOpt < args.length; iOpt++) {
			String sOpt = args[iOpt];
			if (sOpt.length() < 2) {
				usage();
				System.exit(-1);
			}
			switch (sOpt.charAt(1)) {
			case 'v':
				_v = true;
				break;
			case 'i':
				_i = true;
				break;
			default:
				usage();
				System.exit(-1);
				break;
			}
		}

		//
		// Get listing of the files in the input dir
		//

		File fInDir = FileUtils1.checkExistDir(inputdirpath);
		if (fInDir == null) {
			ERR("Directory '" + inputdirpath + "' does not exist");
			System.exit(-1);
		}

		File[] af;
		af = fInDir.listFiles(new RegFileFilter());
		OUT("nInFiles = " + af.length);

		//
		// Open output file
		//
		RandomAccessFile fOut = null;
		if (!_i) {
			try {
				fOut = new RandomAccessFile(outcorpuspath, "rw");
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(-1);
			}
		}

		//
		// Write stdatafield size to outfile
		//
		if (fOut != null) {
			try {
				fOut.setLength(NSD_DEF_SIZE);
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(-1);
			}

			String sNSD = "" + stdataSize; // Print in decimals to string
			if (sNSD.length() > NSD_DEF_SIZE) {
				ERR("sentenceSize too long");
				System.exit(-1);
			}

			byte[] abNSD = new byte[NSD_DEF_SIZE];
			BytearrayFromString(abNSD, sNSD);

			try {
				fOut.write(abNSD);
			} catch (IOException e) {
				e.printStackTrace();
				ERR("Fail write nSD length to outfile head: " + e.getMessage());
				System.exit(-1);
			}
		}

		//
		// Run through all input files
		//

		RPCData rpcdata = new RPCData();
		int i;
		for (i = 0; i < af.length; i++) {
			if (_v) {
				OUT("infile = " + af[i].getName());
			}

			String erm = readPosCorpFile(af[i], // input file
					af[i].getName(), // textID
					stdataSize, rpcdata, fOut);
			if (erm != null) {
				ERR(erm);
				System.exit(-1);
			}

			if (!_v) {
				if ((i % 1024) == 0) {
					System.out.print(".");
				}
			}

		}

		OUT("Done");
		OUT("llMax      = " + rpcdata.llMax);
		OUT("nSentences = " + rpcdata.nSentences);

		//
		// Close output file
		//
		if (fOut != null) {
			try {
				fOut.close();
			} catch (IOException e) {
				e.printStackTrace();
				ERR("Fail close output curpusfile: " + e.getMessage());
				System.exit(-1);
			}
			OUT("Written outfile " + outcorpuspath);
		}

		System.exit(0); // success
	}

}
