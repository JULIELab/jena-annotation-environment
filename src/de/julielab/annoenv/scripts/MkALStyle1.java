package de.julielab.annoenv.scripts;
/**
 * 
 * 
 * Code used to create all files needed for AL project ! 
 * 
 *
 * old code by Menno Rubin
 * MkALStyle1.java   MR Apr-May '06
 * Create Styles/*.xsl file for MMAX2 for active learning.
 * Also creates the files 'basedata.xml' and 'sentence_level.xml'.
 * (These last two files are generated together with the Styles file to make
 * sure that the references in the Styles/*.xsl and sentence_level.xml files
 *  to word labels "word_NN" in the basedata.xml file are correct.)
 *  
 *  Also creates own file 'alsession.txt', which specifies which words
 *  in the generated MMAX2 files come from which sentence and which textfile.
 *  The file 'alsession.txt' is needed at the *output* side of the MMAX2
 *  edit session to convert the MMAX2 output files back to separate sentences.
 *  
 *  Input: - List of (textID, sentenceID) pairs.  Number of entries in list
 *         is small, i.e. about 20.
 *         - Directory oathname containing tokenized texts.  (E.g.
 *           "/data/data/data_corpora/genomics/GeneOnto/abstracts/tok".)
 *      textID in input list = filename of file in this dir.
 *        - Name of the annotation "level" to mention in the generated
 *        MMAX2  Styles/*.xsl file.  Probably name of the "named entity".
 *        E.g. "protein".
 *
 *Output: Prints the contents of the following files to PrintWriter objects:
 *       - Basedata/basedata.xml
 *       - Markables/sentence_level.xml
 *      - Styles/*.xsl
 *      - alsession.txt
*/

import java.io.*;
import java.util.*; //For LinkedList, HashSet

class MkALStyle1 {
	private static void OUT(String s) {
		System.out.println(s);
	}

	private static void ERR(String s) {
		System.err.println(s);
	}

	private static void aString_dump(String label, String[] as) // for debug
	{
		System.out.print(label + " n=" + as.length + " ");
		int i;
		for (i = 0; i < as.length; i++) {
			System.out.print("[" + as[i] + "] ");
		}
		System.out.println("");
	}

	// Print string to PrintWriter, printing every '<' char as string "&lt;"
	// and every '>' char as string "&gt;". Purpose: Printing of literal
	// string data to XML files.
	private static void writeStringXML(PrintWriter out, String s) {
		int i;
		for (i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			switch (c) {
			case '<':
				out.print("&lt;");
				break;
			case '>':
				out.print("&gt;");
				break;
			case '&':
				out.print("&amp;");
				break;
			case '\"':
				out.print("&quot;");
				break;
			default:
				out.print(c);
				break;
			}
		}
	}

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	// Write Basedata items to output

	private static void writeBD_prologue(PrintWriter outBD, boolean bOFMmax) {
		if (outBD == null) {
			return;
		}
		if (bOFMmax) {
			outBD.println("<?xml version='1.0' encoding='UTF-8'?>");
			outBD.println("<!DOCTYPE words SYSTEM \"words.dtd\">");
			outBD.println("<words>");
		}
	}

	private static void writeBD_epilogue(PrintWriter outBD, boolean bOFMmax) {
		if (outBD == null) {
			return;
		}
		if (bOFMmax) {
			outBD.println("</words>");
		}
	}

	private static void writeBD_word(PrintWriter outBD, boolean bOFMmax,
			int iWord, String sWord) {
		if (outBD == null) {
			return;
		}
		if (bOFMmax) {
			outBD.print("<word id=\"word_" + (iWord + 1) + "\">");
			writeStringXML(outBD, sWord);
			outBD.println("</word>");
		} else {
			outBD.println("tok( " + iWord + ") " + " [" + sWord + "]");
		}
	}

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	// Write Sentence_level items to output

	// NOTE!: Name 'sentence' in "xmlns" field value corresponds with the
	// name of the annotation "level" (for the sentences); i.e. both must
	// be the same.
	private static void writeSL_prologue(PrintWriter outSL, boolean bOFMmax) {
		if (outSL == null) {
			return;
		}
		if (bOFMmax) {
			outSL.println("<?xml version=\"1.0\"?>");
			outSL.println("<!DOCTYPE markables SYSTEM \"markables.dtd\">");
			outSL
					.println("<markables xmlns=\"www.eml.org/NameSpaces/sentence\">");
		}
	}

	private static void writeSL_epilogue(PrintWriter outSL, boolean bOFMmax) {
		if (outSL == null) {
			return;
		}
		if (bOFMmax) {
			outSL.println("</markables>");
		}
	}

	private static void writeSL_sentence(PrintWriter outSL, boolean bOFMmax,
			int iSentence, int iFirstW, // IN: First word in sentence
			int iLastW, // IN: Last word in sentence
			String label) // IN (used for debug output only)
	{
		if (outSL == null) {
			return;
		}
		if (bOFMmax) {
			outSL.println("<markable id=\"markable_" + (iSentence + 1)
					+ "\" span=\"word_" + (iFirstW + 1) + "..word_"
					+ (iLastW + 1) + "\"/>");
		} else {
			outSL.println(label + " S( " + iSentence + ") " + iFirstW + " "
					+ iLastW);
		}
	}

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	// Write parts of XSL file to output

	private static void printXslPrologue(PrintWriter out, // OUT (where to
															// write output to)
			String levelname) // IN
	{
		out
				.print("<xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"1.0\"\n"
						+ "xmlns:mmax=\"org.eml.MMAX2.discourse.MMAX2DiscourseLoader\"\n"
						+ "xmlns:"
						+ levelname
						+ "=\"www.eml.org/NameSpaces/"
						+ levelname
						+ "\">\n"
						+ "<xsl:output method=\"text\" indent=\"yes\" omit-xml-declaration=\"yes\"/>\n"
						+ "<xsl:strip-space elements=\"*\"/>\n"
						+ "\n"
						+ "<xsl:template match=\"words\">\n"
						+ "<xsl:apply-templates/>\n"
						+ "</xsl:template>\n"
						+ "\n"
						+ "<xsl:template match=\"word\">\n"
						+ "<xsl:choose>\n" + "");
	}

	private static final String G_xslMarkable = "<xsl:value-of select=\"mmax:registerDiscourseElement(@id)\"/>\n"
			+ "  <xsl:apply-templates select=\"mmax:getStartedMarkables(@id)\" mode=\"opening\"/>\n"
			+ "  <xsl:value-of select=\"mmax:setDiscourseElementStart()\"/>\n"
			+ "   <xsl:apply-templates/>\n"
			+ "  <xsl:value-of select=\"mmax:setDiscourseElementEnd()\"/>\n"
			+ "  <xsl:apply-templates select=\"mmax:getEndedMarkables(@id)\" mode=\"closing\"/>\n"
			+ "  <xsl:text> </xsl:text>\n" + "";

	private static final String G_xslOtherwise = "<xsl:otherwise>\n"
			+ " <xsl:value-of select=\"mmax:registerDiscourseElement(@id)\"/>\n"
			+ "  <xsl:apply-templates select=\"mmax:getStartedMarkables(@id)\" mode=\"opening\"/>\n"
			+ "  <xsl:value-of select=\"mmax:setDiscourseElementStart()\"/>\n"
			+ " <xsl:apply-templates/>\n"
			+ "  <xsl:value-of select=\"mmax:setDiscourseElementEnd()\"/>\n"
			+ "  <xsl:apply-templates select=\"mmax:getEndedMarkables(@id)\" mode=\"closing\"/>\n"
			+ "<xsl:text> </xsl:text>\n" + "</xsl:otherwise>\n" + "";

	private static void printXslEpilogue(PrintWriter out, // OUT (where to
															// write output to)
			String levelname) // IN
	{
		out
				.print("</xsl:choose>\n"
						+ "</xsl:template>\n"
						+ "\n"
						+ "<xsl:template match=\""
						+ levelname
						+ ":markable\" mode=\"opening\">\n"
						+ "<xsl:value-of select=\"mmax:startBold()\"/>\n"
						+ "<xsl:value-of select=\"mmax:addLeftMarkableHandle(@mmax_level, @id, '[')\"/>\n"
						+ "<xsl:value-of select=\"mmax:endBold()\"/>\n"
						+ "</xsl:template>\n"
						+ "\n"
						+ "<xsl:template match=\""
						+ levelname
						+ ":markable\" mode=\"closing\">\n"
						+ "<xsl:value-of select=\"mmax:startBold()\"/>\n"
						+ "<xsl:value-of select=\"mmax:addRightMarkableHandle(@mmax_level, @id, ']')\"/>\n"
						+ "<xsl:value-of select=\"mmax:endBold()\"/>\n"
						+ "</xsl:template>\n"
						+ "</xsl:stylesheet>\n"
						+ "                \n                \n                   \n\n");
	}

	// -----------------------------------------------------------------------
	// LinkedList<SE>:
	// Ordered list of sentences,
	// + operations on it.
	//
	// Important: This list is ordered so that sentences from one text sit
	// at consecutive list entries. In the list, the sentences from one
	// text are in order from 1st to last sentence in the text.
	// The blocks of sentences for different files are in arbitrary order.
	//
	// The fields SE.asTok[] and SE.iWord0 are also used to generate the files
	// "basedata.xml" and "sentence_level.xml".

	// Entry in ordered list of all sentences
	private static class SE {
		public String textID;

		public int sentID;

		public String s; // the tokenized sentence

		public boolean bLIF; // true if is last sentence in file

		public boolean bAnn; // true if sentence is to be annotated

		// Fields used only if bAnn == true:
		public String[] asTok;

		public int iWord0; // through-numbered over all sentences to be

		// annotated; 0-based.

		public SE(String tid0, int sid0, String s0) {
			textID = tid0;
			sentID = sid0;
			s = s0;
			bLIF = false; // default value
			bAnn = false; // default value

			asTok = null; // init
			iWord0 = 0;
		}

		public void setLIF() {
			bLIF = true;
		}

		public void setAnn() {
			bAnn = true;
		}
	}

	// lse_readFile():
	// Add the sentences from file 'pathname' to end of list 'lSOut'.
	// The appending is done so that the sentences from the file are
	// entered into the list in the right order. The method marks the
	// last sentence from the file by setting its bLIF:=true in the list.
	private static String lse_readFile(LinkedList<SE> lSOut, // IN+OUT (New
																// entries
																// appended)
			String pathname, // IN (Path of file to read)
			String textID) // IN (Label on text)
	{
		try {
			BufferedReader br = new BufferedReader(new FileReader(pathname));

			int iSentence = 0; // sentenceID
			SE seNew = null;
			String sIn;
			while ((sIn = br.readLine()) != null) {
				// TEST int nTok = nTokensInSentence( sIn );
				// TEST OUT( "nTok = " + nTok );

				// DEBUG OUT( textID + " " + iSentence + " : " + sIn );

				seNew = new SE(textID, iSentence, sIn);
				lSOut.add(seNew);

				iSentence++;
			}
			if (seNew == null) {
				throw new IOException("empty file");
			}
			seNew.setLIF(); // Last sentence in file

			br.close();
		} catch (IOException e) {
			return e.getMessage();
		}
		return null; // success
	}

	// lse_setAnn():
	// Mark sentence textID:sentID as sentence to be annotated, by setting
	// its 'bAnn' flag to true. Return false if sentence not found.
	// Called after reading in all texts (by means of 'lse_readFile()' calls).
	private static boolean lse_setAnn(LinkedList<SE> lS, // IN+OUT
			String textID, // IN
			int sentID) // IN
	{
		boolean bFound = false;
		Iterator<SE> iS = lS.iterator();
		while (iS.hasNext()) {
			SE se = iS.next();
			if (se.textID.equals(textID) && (se.sentID == sentID)) {
				se.setAnn();
				bFound = true;
			}
		}
		return bFound;
	}

	// lse_numberAnnWords():
	// Called after all lse_setAnn() calls have been made, to prepare
	// the list<SE> for call(s) to any of the 'lse_printXXX()' methods.
	// In all list entries where bAnn == true, set the fields
	// asTok and iWord0.
	// Return the total # of words in all entries with bAnn == true.
	private static int lse_numberAnnWords(LinkedList<SE> lS) // IN+OUT
	{
		int iWord = 0;
		Iterator<SE> iS = lS.iterator();
		while (iS.hasNext()) {
			SE se = iS.next();
			if (!se.bAnn) {
				continue;
			}

			String[] asTok = se.s.split("[ \t]+");

			se.iWord0 = iWord;
			se.asTok = asTok;

			iWord += asTok.length;
		}

		return iWord;
	}

	private static void lse_dump(LinkedList<SE> lS) // IN
	{
		Iterator<SE> iS = lS.iterator();
		while (iS.hasNext()) {
			SE se = iS.next();
			OUT(se.textID + " " + se.sentID + " last=" + se.bLIF + " ann="
					+ se.bAnn + " : " + se.s);

			if (se.bAnn) {
				OUT("  `-- iWord0=" + se.iWord0);
				aString_dump("  `-- tokens:", se.asTok);
			}

			if (se.bLIF) {
				OUT("----------");
			}
		}
	}

	// Print contents of 'basedata.xml' file.
	// If _bExtraAtEnd == true, then write one extra word at the
	// end beyond the last word from the input. Purpose: Is workaround
	// for apparent bug in XSL execution in MMAX2, which makes that
	// a <xsl:when test="id='word_N'"> does not trigger when word_N is
	// the last word in basedata. So the workaround is to add an extra
	// dummmy word to basedata, so that the word_N mentioned in the last
	// <xsl:when...> clause in the style.xsl generated by
	// 'lse_printStyle()' is no longer the last word in basedata.
	private static void lse_printBasedata(LinkedList<SE> lS, // IN
			boolean _bExtraAtEnd, // IN
			PrintWriter out) // OUT (where to write output to)
	{
		writeBD_prologue(out, true);

		Iterator<SE> iS = lS.iterator();
		int iWordLast = -1;
		while (iS.hasNext()) {
			SE se = iS.next();
			if (!se.bAnn) {
				continue;
			}

			String[] asTok = se.asTok;
			int i;
			for (i = 0; i < asTok.length; i++) {
				int iWord = se.iWord0 + i;
				writeBD_word(out, true, iWord, asTok[i]);
				iWordLast = iWord;
			}
		}

		if (_bExtraAtEnd) {
			assert (iWordLast != -1);
			writeBD_word(out, true, iWordLast + 1, "END");
		}

		writeBD_epilogue(out, true);
	}

	// Print contents of 'Markables/sentence_level.xml' file
	private static void lse_printSentencelevel(LinkedList<SE> lS, // IN
			PrintWriter out) // OUT (where to write output to)
	{
		writeSL_prologue(out, true);

		int iSentence = 0;
		Iterator<SE> iS = lS.iterator();
		while (iS.hasNext()) {
			SE se = iS.next();
			if (!se.bAnn) {
				continue;
			}

			assert (se.asTok.length > 0);

			writeSL_sentence(out, true, iSentence, se.iWord0, // iFirstW
					se.iWord0 + se.asTok.length - 1, // iLastW
					"DUMMY_LABEL");

			iSentence++;
		}

		writeSL_epilogue(out, true);
	}

	// Print contents of own file 'alsession.txt' that specifies which
	// word ranges in the MMAX2 files are the AL-sentences, and for each
	// sentence specifies the textID:sentenceID. Purpose: To make
	// possible the interpretation of the MMAX2 output files.
	// File format: Lines each like
	// iWord0 nWords textID sentID
	// with a single space between the fields, and where
	// iWord0 = 0-based index in basedata where sentence starts
	// nWords = number of words in sentence.
	private static void lse_printOwnSentenceDefTable(LinkedList<SE> lS, // IN
			PrintWriter out) // OUT (where to write output to)
	{
		Iterator<SE> iS = lS.iterator();
		while (iS.hasNext()) {
			SE se = iS.next();
			if (!se.bAnn) {
				continue;
			}

			assert (se.asTok.length > 0);

			out.println(se.iWord0 + " " + se.asTok.length + " " + se.textID
					+ " " + se.sentID);
		}
	}

	// lse_printStyle():
	// Print contents of Styles/*.xsl file
	private static void lse_printStyle(LinkedList<SE> lS, // IN
			String levelname, // IN
			PrintWriter out, // OUT (where to write output to)
			boolean _wTID) // IN (show textID for each text in MMAX display)
	{
		int iWord = 0; // Number through the words in the sentences to
		// be annotated = the words in "basedata.xml".

		// Print xsl prologue
		printXslPrologue(out, levelname);
		out.println("");

		// Tmp store for non-annotated sentences
		LinkedList<String> lBuf = new LinkedList<String>();

		// boolean inFile
		int iWordInFileLast = -1; // number of last word in last annotated
		// sentence in file.

		Iterator<SE> iS = lS.iterator();
		while (iS.hasNext()) {
			SE se = iS.next();

			if (se.bAnn) {
				out.println("<xsl:when test=\"@id='word_" + (se.iWord0 + 1)
						+ "'\">");

				out.println("<xsl:value-of select=\"mmax:startItalic()\"/>");
				out
						.println("<xsl:value-of select=\"mmax:startSuperscript()\"/>");
				out.println("<xsl:text>");

				if (iWordInFileLast == -1) // 1st annotated word in text
				{
					out.print("***************************** BEGIN ABSTRACT ");
					if (_wTID) {
						out.print(se.textID + " ");
					}
					out
							.println("***********************************************");
				} else {
					out.println("");
				}

				// Print out the non-annotated sentences in the text that we
				// walked through before we got to this annotated sentence
				Iterator<String> i = lBuf.iterator();
				while (i.hasNext()) {
					writeStringXML(out, i.next());
					out.println("");
				}
				lBuf.clear();

				out.println("");
				out.println("</xsl:text>");
				out.println("<xsl:value-of select=\"mmax:endSuperscript()\"/>");
				out.println("<xsl:value-of select=\"mmax:endItalic()\"/>");
				out.println("");

				out.print(G_xslMarkable);
				out.println("");
				out.println("</xsl:when>");
				out.println("");

				// Save word number of last word in the annotated sentence
				iWordInFileLast = se.iWord0 + se.asTok.length - 1;
			} else {
				// Non-annotated sentence: Store in lBuf
				lBuf.add(se.s);
			}

			if (se.bLIF) {
				assert (iWordInFileLast != -1);

				out.println("<xsl:when test=\"@id='word_"
						+ (iWordInFileLast + 1) + "'\">");
				out.println("");
				out.print(G_xslMarkable);
				out.println("");

				out.println("<xsl:value-of select=\"mmax:startItalic()\"/>");
				out
						.println("<xsl:value-of select=\"mmax:startSuperscript()\"/>");
				out.println("<xsl:text>");
				out.println("");

				// Print out remaining non-annotated sentences in text
				Iterator<String> i = lBuf.iterator();
				while (i.hasNext()) {
					writeStringXML(out, i.next());
					out.println("");
				}
				lBuf.clear();

				out.print("******************************* END ABSTRACT ");
				if (_wTID) {
					out.print(se.textID + " ");
				}
				out.println("***********************************************");
				out.println("");

				out.println("</xsl:text>");
				out.println("<xsl:value-of select=\"mmax:endSuperscript()\"/>");
				out.println("<xsl:value-of select=\"mmax:endItalic()\"/>");
				out.println("");
				out.println("</xsl:when>");
				out.println("");

				iWordInFileLast = -1; // reset
			}
		}

		// Print xsl epilogue
		out.println("");
		out.print(G_xslOtherwise);
		out.println("");
		printXslEpilogue(out, levelname);
	}

	// -----------------------------------------------------------------------
	// Read input list.sel file into LinkedList<TIDSID>

	// List entry in input list of (textID,sentenceID)
	private static class TIDSID {
		public String textID;

		public int sentID; // Value is >= 0.

		public TIDSID(String tid0, int sid0) {
			textID = tid0;
			sentID = sid0;
		}
	}

	private static String readListSel(LinkedList<TIDSID> lOut, // OUT
			String listselpath) // IN
	{
		lOut.clear(); // init

		BufferedReader br = null;
		int iLine = 0;
		try {
			br = new BufferedReader(new FileReader(listselpath));

			String sIn;
			while ((sIn = br.readLine()) != null) {
				sIn = sIn.trim();

				String[] as = sIn.split("[ \t]+");
				if (as.length < 2) {
					throw new Exception("Data missing in line");
				}

				String strTextID = as[0];
				String strSentID = as[1];
				int iSentID = Integer.parseInt(strSentID);
				// `-- thows NumberFormatException

				TIDSID tsNew = new TIDSID(strTextID, iSentID);
				lOut.add(tsNew);

				iLine++;
			}
		} catch (Exception e) {
			return "Fail read file '" + listselpath + "' (line " + (iLine + 1)
					+ ") : " + e.getMessage();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (Exception e) {
				}
			}
		}

		return null; // success
	}

	// -----------------------------------------------------------------------
	// Definition of public method 'createFiles()'

	// Returns null on success, errmess on error.
	// Parameters:
	// strPathTok IN Abs. path of dir with tokenized files
	// listselpath IN Abs.path of 'list.sel' file
	// levelname IN Annotation level name to mention in generated
	// Styles/*.xsl file
	// pwStyle OUT PrintWriter object to write style.xsl to
	// pwBasedata OUT PrintWriter object to write basdata.xml to
	// pwSentlvl OUT PrintWriter object to write sentence_level.xml to
	// pwAlsess OUT PrintWriter object to write alsession.txt to
	// _v IN Verbose (debug messages to stdout)
	// _t IN Use built-in test inputs, for testing only.
	//
	// The contents of the four generated output files is written
	// to PrintWriter objects.
	// If any of the 'pwXXX' parameters has actual value 'null', then
	// that output is not written.
	// USAGE NOTE: For most PrintWriter implementations, the caller should
	// call PrintWriter.close() after the call to 'createFiles()', to cause
	// the output to be actually written (e.g. to file).
	//
	// If _t == true, then the method uses an internal hard-coded test
	// tid:sid list instead of argument listselpath, and uses
	// "/data/data/data_corpora/genomics/GeneOnto/abstracts/tok" instead
	// of the user-specified arg value 'strPathTok'. Note that this
	// assumes that this directory contains tokenized files corresponding
	// with the built-in test tid:sid list !
	// The _t argument is useful when testing the AL system, because it
	// provides an *extra* point at which the AL iteration can be
	// bootstrapped out of nothing -- it is convenient when testing
	// the AL loop using the repository because it circumvents having
	// previously had to create a list.sel file in the repository.
	// [ IF the '_t' argument is later removed, then please do note
	// that the test case remains useful as a regression test -- therefore
	// when removing it from here the test should be moved to and
	// saved into a separate file 'test_list.sel' containing what's
	// now in the hardcoded 'lIn' below and directory testtok/*
	// containing the files (from
	// "/data/data/data_corpora/genomics/GeneOnto/abstracts/tok")
	// that are referred to by 'test_list.sel'. ]

	public static String createFiles(String strPathTok, // IN: Abs.path of tok/*
														// dir
			String listselpath, // IN: Abs.path of list.sel file
			String levelname, // IN
			PrintWriter pwStyle, // OUT
			PrintWriter pwBasedata, // OUT
			PrintWriter pwSentlvl, // OUT
			PrintWriter pwAlsess, // OUT
			boolean _v, // IN (Verbose)
			boolean _t) // IN (Test)
	{
		String erm = null;

		// ,-------------------------
		// | Get input textID:satzID list
		// `-------------------------

		LinkedList<TIDSID> lIn = new LinkedList<TIDSID>();

		if (_t) {
			strPathTok = "/data/data/data_corpora/genomics/GeneOnto/abstracts/tok";

			// Create test input list, for input dir =
			// "/data/data/data_corpora/genomics/GeneOnto/abstracts/tok"
			lIn.add(new TIDSID("1618911", 3));
			lIn.add(new TIDSID("12384566", 3)); // Test '>' in basedata
			lIn.add(new TIDSID("7707319", 4)); // Two items with same TID
			lIn.add(new TIDSID("7707319", 2));
			lIn.add(new TIDSID("11516400", 0)); // 1st sentence in file
			lIn.add(new TIDSID("8676079", 1));
			lIn.add(new TIDSID("1400430", 1));
			lIn.add(new TIDSID("1400417", 8));
			lIn.add(new TIDSID("16188968", 2));
			lIn.add(new TIDSID("1391960", 1));
			lIn.add(new TIDSID("12381793", 0));
			lIn.add(new TIDSID("11494046", 1));
			lIn.add(new TIDSID("7687629", 12)); // Last sentence in file
			lIn.add(new TIDSID("11497433", 1));
			lIn.add(new TIDSID("8659123", 2));
			lIn.add(new TIDSID("16174773", 1));
			lIn.add(new TIDSID("9486644", 0));
			lIn.add(new TIDSID("10837363", 1)); // 2*sameTID, adjacent s.s
			lIn.add(new TIDSID("10837363", 2));
			lIn.add(new TIDSID("10847589", 1));
		} else {
			erm = readListSel(lIn, listselpath);
			if (erm != null) {
				return "Fail read '" + listselpath + "': " + erm;
			}
		}

		if (_v) {
			OUT("nIn = " + lIn.size());
		}

		// ,-------------------------
		// | Do the work
		// `-------------------------

		// Get list of all filenames to read in which each file occurs
		// only once. Use a "set" object as the container for this.
		HashSet<String> lFile = new HashSet<String>();
		{
			Iterator<TIDSID> iIn = lIn.iterator();
			while (iIn.hasNext()) {
				TIDSID entry = iIn.next();
				lFile.add(entry.textID);
			}
		}

		// Dump the set lFile
		if (_v) {
			OUT("lFile: n = " + lFile.size());
			Iterator<String> iF = lFile.iterator();
			while (iF.hasNext()) {
				String filename = iF.next();
				OUT("file: " + filename);
			}
		}

		// Read all sentences from the input files
		LinkedList<SE> lS = new LinkedList<SE>();
		{
			Iterator<String> iF = lFile.iterator();
			while (iF.hasNext()) {
				String filename = iF.next();
				String pathname = strPathTok + File.separator + filename;
				erm = lse_readFile(lS, pathname, filename);
				if (erm != null) {
					return "Fail read '" + pathname + "': " + erm;
				}
			}
		}

		// Mark in list<SE> the sentences that are to be annotated
		{
			Iterator<TIDSID> iIn = lIn.iterator();
			while (iIn.hasNext()) {
				TIDSID entry = iIn.next();
				boolean ok = lse_setAnn(lS, entry.textID, entry.sentID);
				if (!ok) {
					return "Internal error lse_setAnn(" + entry.textID + ","
							+ entry.sentID + ")";
				}
			}
		}

		// Number the words in the sentences to be annotated
		int nWords = lse_numberAnnWords(lS);
		if (_v) {
			OUT("nWordsTot = " + nWords);
		}

		if (_v) {
			lse_dump(lS);
		}

		if (_v) {
			OUT("========================================================");
			OUT("========================================================");
		}

		// ,-------------------------
		// | Write output
		// `-------------------------

		if (pwBasedata != null) // Write basedata.xml contents
		{
			lse_printBasedata(lS, true, pwBasedata);
		}
		if (pwSentlvl != null) // Write sentence_level.xml contents
		{
			lse_printSentencelevel(lS, pwSentlvl);
		}
		if (pwStyle != null) // Write Styles/*.xsl contents
		{
			lse_printStyle(lS, levelname, pwStyle, true);
		}
		if (pwAlsess != null) // Write own 'alsession.txt' contents
		{
			lse_printOwnSentenceDefTable(lS, pwAlsess);
		}

		return null; // success
	}

	// -----------------------------------------------------------------------
	// MAIN()

	private static void usage() {
		ERR("Usage: MkALStyle1 " + "tokdirpath " + // 0
				"list_sel_path " + // 1
				"levelname " + // 2
				"WHAT " + // 3
				"[options]"); // 4
		ERR("where ");
		ERR("  tokdirpath    = Path of dir with tokenized input files, "
				+ "should not end in '/'");
		ERR("  list_sel_path = Path of 'list.sel' file");
		ERR("  levelname     = \"Annotation level\" name");
		ERR("  WHAT = 'S' for Styles output");
		ERR("         'B' for Basedata output");
		ERR("         'M' for Markables/sentence_level output");
		ERR("         'D' for alsession.txt output");
		ERR("Options:");
		ERR("   -v    Verbose (debug messages to stdout)");
		ERR("   -t    Use internal test tid:sid list and "
				+ "/data/data/data_corpora/genomics/GeneOnto/abstracts/tok "
				+ "instead of args list_sel_path and tokdirpath");
	}

	public static void main(String[] args) {
		String erm = null;

		// ,-------------------------
		// | Cmd line args
		// `-------------------------

		final int N_REQ_ARGS = 4;

		char cWhat = 'S'; // Styles
		// 'B' = basedata
		// 'M' = sentence_level.xml
		// 'D' = alsession.txt

		boolean _v = false;
		boolean _t = false;

		if (args.length < N_REQ_ARGS) {
			usage();
			System.exit(-1);
		}

		String strPathTok = args[0];
		int lenPT = strPathTok.length();
		assert ((lenPT > 0) && (strPathTok.charAt(lenPT - 1) != '/'));

		String listselpath = args[1];

		// Annotation level name to mention in generated Styles/*.xsl file
		// String levelname = "protein";
		// String levelname = "LEVELNAME";
		// String levelname = "NamedEntity";
		String levelname = args[2];
		assert (levelname.length() > 0);

		assert (args[3].length() > 0);
		cWhat = args[3].charAt(0);

		int iOpt;
		for (iOpt = N_REQ_ARGS; iOpt < args.length; iOpt++) {
			String sOpt = args[iOpt];
			if (sOpt.length() < 2) {
				usage();
				System.exit(-1);
			}
			if (sOpt.charAt(0) != '-') {
				usage();
				System.exit(-1);
			}
			switch (sOpt.charAt(1)) {
			case 'v':
				_v = true;
				break;
			case 't':
				_t = true;
				break;
			default:
				usage();
				System.exit(-1);
				break;
			}
		}

		/*
		 * 
		 *  // ,------------------------- // | Get input textID:satzID list //
		 * `-------------------------
		 * 
		 * LinkedList<TIDSID> lIn = new LinkedList<TIDSID>();
		 * 
		 * if ( _t ) { strPathTok =
		 * "/data/data/data_corpora/genomics/GeneOnto/abstracts/tok";
		 * 
		 * //Create test input list, for input dir = //
		 * "/data/data/data_corpora/genomics/GeneOnto/abstracts/tok" lIn.add(
		 * new TIDSID( "1618911", 3 ) ); lIn.add( new TIDSID( "12384566", 3 ) );
		 * //Test '>' in basedata lIn.add( new TIDSID( "7707319", 4 ) ); //Two
		 * items with same TID lIn.add( new TIDSID( "7707319", 2 ) ); lIn.add(
		 * new TIDSID( "11516400", 0 ) ); //1st sentence in file lIn.add( new
		 * TIDSID( "8676079", 1 ) ); lIn.add( new TIDSID( "1400430", 1 ) );
		 * lIn.add( new TIDSID( "1400417", 8 ) ); lIn.add( new TIDSID(
		 * "16188968", 2 ) ); lIn.add( new TIDSID( "1391960", 1 ) ); lIn.add(
		 * new TIDSID( "12381793", 0 ) ); lIn.add( new TIDSID( "11494046", 1 ) );
		 * lIn.add( new TIDSID( "7687629", 12 ) ); //Last sentence in file
		 * lIn.add( new TIDSID( "11497433", 1 ) ); lIn.add( new TIDSID(
		 * "8659123", 2 ) ); lIn.add( new TIDSID( "16174773", 1 ) ); lIn.add(
		 * new TIDSID( "9486644", 0 ) ); lIn.add( new TIDSID( "10837363", 1 ) );
		 * //2*sameTID, adjacent s.s lIn.add( new TIDSID( "10837363", 2 ) );
		 * lIn.add( new TIDSID( "10847589", 1 ) ); } else { erm = readListSel(
		 * lIn, listselpath ); if ( erm != null ) { ERR( "Fail read '" +
		 * listselpath + "': " + erm ); System.exit(-1); } }
		 * 
		 * if(_v) { OUT( "nIn = " + lIn.size() ); }
		 * 
		 * 
		 * 
		 * 
		 * 
		 *  // ,------------------------- // | Do the work //
		 * `-------------------------
		 * 
		 * 
		 * //Get list of all filenames to read in which each file occurs // only
		 * once. Use a "set" object as the container for this. HashSet<String>
		 * lFile = new HashSet<String>(); { Iterator<TIDSID> iIn =
		 * lIn.iterator(); while ( iIn.hasNext() ) { TIDSID entry = iIn.next();
		 * lFile.add( entry.textID ); } }
		 * 
		 * //Dump the set lFile if(_v) { OUT( "lFile: n = " + lFile.size() );
		 * Iterator<String> iF = lFile.iterator(); while ( iF.hasNext() ) {
		 * String filename = iF.next(); OUT( "file: " + filename ); } }
		 * 
		 * 
		 * 
		 * //Read all sentences from the input files LinkedList<SE> lS = new
		 * LinkedList<SE>(); { Iterator<String> iF = lFile.iterator(); while (
		 * iF.hasNext() ) { String filename = iF.next(); String pathname =
		 * strPathTok + File.separator + filename; erm = lse_readFile( lS,
		 * pathname, filename ); if ( erm != null ) { ERR( "Fail read '" +
		 * pathname + "': " + erm ); System.exit(-1); } } }
		 * 
		 * 
		 * //Mark in list<SE> the sentences that are to be annotated { Iterator<TIDSID>
		 * iIn = lIn.iterator(); while ( iIn.hasNext() ) { TIDSID entry =
		 * iIn.next(); boolean ok = lse_setAnn( lS, entry.textID, entry.sentID );
		 * if ( !ok ) { ERR( "Internal error lse_setAnn(" + entry.textID + "," +
		 * entry.sentID + ")" ); System.exit(-1); } } }
		 * 
		 * 
		 * //Number the words in the sentences to be annotated int nWords =
		 * lse_numberAnnWords( lS ); if(_v) { OUT( "nWordsTot = " + nWords ); }
		 * 
		 * 
		 * if(_v) { lse_dump( lS ); }
		 * 
		 * 
		 * 
		 * if(_v) { OUT(
		 * "===========================================================" ); OUT(
		 * "===========================================================" ); }
		 * 
		 *  // WRITE OUTPUT TO STDOUT FOR TESTING // In public func: Write to
		 * outBD, outSL, outStyle if != null.
		 * 
		 * PrintWriter pwOut = new PrintWriter( System.out );
		 * 
		 * if ( cWhat == 'B' ) //Write basedata.xml contents to stdout {
		 * lse_printBasedata( lS, true, pwOut ); } if ( cWhat == 'M' ) //Write
		 * sentence_level.xml contents to stdout { lse_printSentencelevel( lS,
		 * pwOut ); } if ( cWhat == 'S' ) //Write Styles/*.xsl contents to
		 * stdout { lse_printStyle( lS, levelname, pwOut, true ); } if ( cWhat ==
		 * 'D' ) //Write own 'alsession.txt' contents to stdout {
		 * lse_printOwnSentenceDefTable( lS, pwOut ); }
		 * 
		 * pwOut.close(); //<-- !!
		 * 
		 */

		// Write one of the output files to stdout
		// ^^^ ^^^^^^
		PrintWriter pwOut = new PrintWriter(System.out);

		PrintWriter pwStyle = null;
		PrintWriter pwBasedata = null;
		PrintWriter pwSentlvl = null;
		PrintWriter pwAlsess = null;

		switch (cWhat) {
		case 'B':
			pwBasedata = pwOut;
			break;
		case 'M':
			pwSentlvl = pwOut;
			break;
		case 'S':
			pwStyle = pwOut;
			break;
		case 'D':
			pwAlsess = pwOut;
			break;
		}

		erm = createFiles(strPathTok, listselpath, levelname, pwStyle,
				pwBasedata, pwSentlvl, pwAlsess, _v, _t);

		pwOut.close(); // <-- !!

		if (erm != null) {
			ERR(erm);
			System.exit(-1);
		}

		System.exit(0);
	}

}
