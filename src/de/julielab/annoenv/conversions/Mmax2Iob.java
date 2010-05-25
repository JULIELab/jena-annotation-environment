/** 
 * Mmax2Iob.java
 * 
 * Copyright (c) 2007, JULIE Lab. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0 
 *
 * Author: tomanek (based on code from Menno Rubingh)
 * 
 * Current version: 2.0
 * Since version:   0.7
 *
 * Creation date: Aug 01, 2006
 * 
 * 
 *  
 */

package de.julielab.annoenv.conversions;

// Mmax2Iob1.java   MR April '06
// changed by KT, 15.08.06
//
// Convert files from the output end of an MMAX2 session, containing
// ONE SINGLE text, to IOB format.
//
// The files read are:
//     - basedata.xml
//     - Markables/project_XY_level.xml
//     - Schemes/XY_scheme.xml 
//     - priolist.txt
//
// The filenames of all these files are specified as parameters to
// the method that does the work.

// Uses: 
// 		XMLReader1.java 
//		 (This source file sits in "/home/rubingh/menno/ae_mmax/altools/").
//

//--------------------------------------------------------------------------
// Implementation:  
//   Most of the code comes from
//      "/home/rubingh/menno/ae_mmax/altools/Mmax2AL1.java".
//   The big difference with Mmax2AL1.java is that in Mmax2Iob1.java,
//   we do not split up the input basedata.xml file into separate 
//   sentences.

import java.io.*;
import java.util.*; 


public class Mmax2Iob {
	

	private static void OUT(String s) {
		System.out.println(s);
	}

	private static void ERR(String s) {
		System.err.println(s);
	}

	// --------------------------------------------------------------------------
	// DATA TYPE

	// Sentence Definition Table Entry = Line from file 'alsession.txt';
	// with in it the extra element 'lAT', into which the data from
	// basedata.xml and from markables.xml is filled in.
	private static class SDTE {
		public int iWord0;

		public int nWords;

		public String textID;

		public int sentID;

		// List lAT: Initialized to empty list; Later the data from
		// basedata.xml and from markables.xml is filled in into it.
		public LinkedList<AnnToken> lAT;

		public SDTE(int i0, int n0, String tid0, int sid0) {
			iWord0 = i0;
			nWords = n0;
			textID = tid0;
			sentID = sid0;
			lAT = new LinkedList<AnnToken>();
		}
	}

	// Tagged sentence = list or array of AnnToken.
	// A LinkedList<AnnToken> is a member in datatype 'SDTE'.
	private static class AnnToken {
		public double id; // Word id from basedata.xml

		public String token;

		// All tags, directly from 'markables.xml'.
		// Note: It is crucial to the program design that the list contains
		// *REFERENCES* to 'Tag' objects.
		public LinkedList<Tag> lTag;

		public AnnToken(double id0, String tok0) {
			id = id0;
			token = tok0;
			lTag = new LinkedList<Tag>();
		}
	}

	// Class 'Tag':
	// Class for tag value from "<markable ... />" item from markables.xml
	// data.
	// Purpose: To create one single OBJECT for each tag value from one
	// span = one "<markable ... />" item from markables.xml file,
	// so that the lSD.lAT.lTag for different words can contain references
	// to the same Tag object when these words belong to the same 'span'.
	// This is how from the lSD contents, we can detect when words with
	// the same string label as tag belong to the same or to different
	// spans. E.g. to distinguish, for the sentence "a b c d e f", between
	// the annotation [ a b c ]TAG1 [ d e f ]TAG1
	// and the annotation [ a b c d e f ]TAG1 .
	// The detection whether words belong to the same span is thus done
	// by comparing *references* to Tag objects. (NOT by comparing the
	// string contents of the Tag objects.)
	//
	// This would probably have been possible by comparing tag string
	// *references* rather than string values. I created a separate
	// class 'Tag' to make everything more explicit, and to make sure
	// that one and only one OBJECT is created for one 'span'
	// (= "<markable ... />" item) from the markables.xml file.
	public static class Tag {
		public String s;

		public Tag(String s0) {
			s = s0;
		}
	}

	private static void lSD_dump(LinkedList<SDTE> lSD) // IN
	{
		Iterator<SDTE> i = lSD.iterator();
		while (i.hasNext()) {
			SDTE sdte = i.next();
			OUT("i0=" + sdte.iWord0 + " " + "n=" + sdte.nWords + " " + "tid="
					+ sdte.textID + " " + "sid=" + sdte.sentID + " " + "nTok="
					+ sdte.lAT.size());

			if (sdte.lAT.size() != 0) {
				OUT("tokens: ");
				Iterator<AnnToken> iAT = sdte.lAT.iterator();
				while (iAT.hasNext()) {
					AnnToken at = iAT.next();
					System.out.print("   " + at.id + " " + at.token + " : ");

					Iterator<Tag> iTag = at.lTag.iterator();
					while (iTag.hasNext()) {
						Tag tag = iTag.next();
						System.out.print(tag.s + " ");
					}
					System.out.println("");
				}
			}
		}

	}

	/*
	 * //Print final contents of list 'lSD' in own ".aiob" format = // = IOB but
	 * before each sentence an extra line added which // contains the following: // //
	 * SENTENCE textID sentenceID nTokens <newline> // | | // keyword Number of
	 * tokens in sentence // //Only call this AFTER 'lSD_remDoubles()' has been
	 * run on 'lSD' ! // private static void lSD_printAIOB( LinkedList<SDTE>
	 * lSD, //IN PrintWriter pwOut ) //OUT (Where to print to) { Iterator<SDTE>
	 * i = lSD.iterator(); while ( i.hasNext() ) { SDTE sdte = i.next();
	 * pwOut.println( "SENTENCE " + sdte.textID + " " + sdte.sentID + " " +
	 * sdte.lAT.size() );
	 * 
	 * assert( sdte.lAT.size() > 0 );
	 * 
	 * Tag tPrev = null; Iterator<AnnToken> iAT = sdte.lAT.iterator(); while (
	 * iAT.hasNext() ) { AnnToken at = iAT.next(); pwOut.print( at.token + " " );
	 * 
	 * if ( at.lTag.size() == 0 ) { pwOut.println( "O" ); tPrev = null; //reset }
	 * else { Tag t = at.lTag.getFirst(); pwOut.print( (t == tPrev) ? "I-" :
	 * "B-" ); pwOut.println( t.s ); tPrev = t; } }
	 * 
	 * pwOut.println( "" ); //Empty line between sentences } }
	 */

	private static void lSD_printIOB(LinkedList<SDTE> lSD, // IN
			PrintWriter pwOut) // OUT (Where to print to)
	{
		Iterator<SDTE> i = lSD.iterator();
		while (i.hasNext()) {
			SDTE sdte = i.next();
			// pwOut.println( "SENTENCE " + sdte.textID + " " +
			// sdte.sentID + " " + sdte.lAT.size() );

			assert (sdte.lAT.size() > 0);

			Tag tPrev = new Tag("O");
			Iterator<AnnToken> iAT = sdte.lAT.iterator();
			while (iAT.hasNext()) {
				AnnToken at = iAT.next();
				pwOut.print(at.token + " ");

				if (at.lTag.size() == 0) {
					pwOut.println("O");
					tPrev = new Tag("O"); // reset
				} else {
					Tag t = at.lTag.getFirst();
					
					/*
					 * here we can switch between IOB output and
					 * IO only (I is then omitted as well)
					 * this has been turned off as we don't need and don't want
					 * IOB annotation
					 * if we turn on IOB again, than make sure that IAA works (might 
					 * not work due to calculation of F/P/R)...
					 */
					//pwOut.print((t.s.equalsIgnoreCase(tPrev.s)) ? "I-" : "B-");
					pwOut.println(t.s);
					tPrev = t;

				}
			}

			pwOut.println(""); // Empty line between sentences
		}

	}

	// --------------------------------------------------------------------------
	// Sentence Definition Table = file 'alsession.txt'

	// Scan input string containing an integer >= iMin.
	// Subroutine used only in 'sdtable_read()'
	private static boolean scanInt(String sIn, int iMin, OV<Integer> ovOut) {
		Integer i;
		try {
			i = new Integer(sIn);
		} catch (Exception e) {
			return false;
			//TODO this kind of exception handling is very special to MR's coding style (not good, though)
		}
		if (i < iMin) {
			return false;
		}
		ovOut.value = i;
		return true;
	}

	// Read file 'alsession.txt'; put the data from the file into
	// create LinkedList<SDTE> 'lOut'.
	private static String sdtable_read(LinkedList<SDTE> lOut, // OUT
			String pathname) // IN (Pathname of file to read)
	{
		lOut.clear(); // init

		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(pathname));

			int iLine = 1;
			String sIn;
			while ((sIn = br.readLine()) != null) {
				// DEBUG OUT( "line " + iLine + ": " + sIn );

				String[] as = sIn.split("[ \t]+");
				// DEBUG OUT( "nTok=" + as.length );
				if (as.length != 4) {
					throw new Exception("Line " + iLine + ": "
							+ "Wrong number of tokens");
				}

				OV<Integer> ovIWord0 = new OV<Integer>(null);
				OV<Integer> ovNWords = new OV<Integer>(null);
				OV<Integer> ovSentID = new OV<Integer>(null);

				if (!scanInt(as[0], 0, ovIWord0)) {
					throw new Exception("Line " + iLine + ": "
							+ "Fail interpret 1st token (iWord0)");
				}
				if (!scanInt(as[1], 0, ovNWords)) {
					throw new Exception("Line " + iLine + ": "
							+ "Fail interpret 2nd token (nWords)");
				}
				if (!scanInt(as[3], 0, ovSentID)) {
					throw new Exception("Line " + iLine + ": "
							+ "Fail interpret 4th token (sentID)");
				}

				// DEBUG OUT( "iWord0 = " + ovIWord0.value.intValue() );
				// DEBUG OUT( "nWords = " + ovNWords.value.intValue() );
				// DEBUG OUT( "sentID = " + ovSentID.value.intValue() );

				SDTE sdeNew = new SDTE(ovIWord0.value.intValue(),
						ovNWords.value.intValue(), as[2], ovSentID.value
								.intValue());

				lOut.add(sdeNew);

				iLine++;
			}
		} catch (Exception e) {
			return "Fail read sdtable: " + e.getMessage();
			//TODO this kind of exception handling is very special to MR's coding style (not good, though)
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

	// For version reading sentence_level.xml instead of 'alsession.txt':
	// Initialize lSD list from the sentence_level.xml data in
	// list 'lSentDef'.
	// This function is called INSTEAD OF function 'sdtable_read()',
	// in case the input MMAX session is a "Praktikum" session.
	// When the input MMAX session is an "AL" session, we use function
	// 'sdtable_read()' instead, to initialize lSD from 'alsession.txt'.
	private static String sdtable_initFromSentDef(LinkedList<SDTE> lOut, // OUT
			LinkedList<XMLReader.XMLTAGVAL> lTVSentDef_in) // IN
	{
		lOut.clear(); // init

		// Top XML element
		if (lTVSentDef_in.size() != 1) {
			return "Top nestinglevel in scheme must have 1 element";
		}

		XMLReader.XMLTAGVAL tvIn0 = lTVSentDef_in.getFirst();
		LinkedList<XMLReader.XMLTAGVAL> lTagNames = tvIn0.lSub;

		Iterator<XMLReader.XMLTAGVAL> iTN = lTagNames.iterator();
		while (iTN.hasNext()) {
			XMLReader.XMLTAGVAL tvName = iTN.next();

			String spanval = XMLReader.lAttr_getValueByName(tvName.lAttr,
					"span");
			if (spanval == null) {
				return "No element 'span' in '<markable...>'";
			}

			// DEBUG OUT( "SV= [" + spanval + "]" );

			// 'spanval' must have string contents "word_N...word_M",
			// where N and M are integers > 0.

			String[] asSub = spanval.split("\\.\\.");
			if (asSub.length != 2) {
				return "S01 Wrong contents of spanval: " + spanval;
			}
			if (!asSub[0].startsWith("word_")) {
				return "S02 Wrong contents of spanval: " + spanval;
			}
			if (!asSub[1].startsWith("word_")) {
				return "S03 Wrong contents of spanval: " + spanval;
			}

			String s1 = asSub[0].substring(5);
			String s2 = asSub[1].substring(5);

			// DEBUG OUT( " . s1=[" + s1 + "] s2=[" + s2 + "]" );

			int i1 = -1;
			int i2 = -1;
			try {
				i1 = Integer.parseInt(s1);
				i2 = Integer.parseInt(s2);
			} catch (Exception e) {
				return "S04 Wrong contents of spanval: " + spanval;
			}

			if (i1 <= 0) {
				return "S05 Wrong contents of spanval: " + spanval;
			}
			if (!(i1 <= i2)) {
				return "S06 Wrong contents of spanval: " + spanval;
			}

			int iWord0 = i1 - 1;
			int n = (i2 - i1 + 1);
			assert (n > 0);

			SDTE sdeNew = new SDTE(iWord0, n, "dummyTextID", 0); // dummy
			// sentence
			// ID

			lOut.add(sdeNew);
		}

		return null; // success
	}

	// --------------------------------------------------------------------------
	// Priority list file 'priolist.txt'
	//
	// File format:
	// One tagvalue per line.
	// The tagvalues are case-insensitive.
	// Lines beginning with '#' and empty lines are ignored.
	// Earlier (higher) in file means higher priority.
	//

	// Read the contents of the file into a LinkedList<String>.
	// Note: Convert each tagvalue from the file to lower-case. Because MMAX2
	// converts the tag-names and -values to lower-case in the
	// markables.xml file (to which the data from the priolist.txt file is
	// compared).
	private static String priolist_read(LinkedList<String> lOut, // OUT
			String pathname) // IN (Pathname of file to read)
	{
		lOut.clear(); // init

		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(pathname));

			int iLine = 0;
			String sIn;
			while ((sIn = br.readLine()) != null) {
				iLine++;

				sIn = sIn.trim();
				if (sIn.length() == 0) {
					continue;
				}
				if (sIn.charAt(0) == '#') {
					continue;
				}

				sIn = sIn.toLowerCase();
				lOut.add(sIn);
			}
		} catch (Exception e) {
			return "Fail read priolist: " + e.getMessage();
			//TODO this kind of exception handling is very special to MR's coding style (not good, though)
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

	private static void priolist_dump(LinkedList<String> lPrio) {
		Iterator<String> i = lPrio.iterator();
		while (i.hasNext()) {
			OUT(" " + i.next());
		}
	}

	// Lookup tag value 'sTag' in lPrio.
	// If found, then return the index number of
	// the tag in the prio-list (SMALLER int value means HIGHER priority).
	// If not found, then return -1.
	private static int priolist_getPrio(LinkedList<String> lPrio, // IN
			String sTag) // OUT
	{
		return lPrio.indexOf(sTag);
	}

	// --------------------------------------------------------------------------
	// Definitions of the XML files with basedata, markables, and scheme;
	// used in the XMLReader1.readXMLFile() calls to read these XML files.

	private static final XMLReader.XMLTAGDEF G_defBasedata = new XMLReader.XMLTAGDEF(
			"words", false, // no text contents
			new XMLReader.XMLTAGDEF("word", true, null // empty xtSub
			));

	private static final XMLReader.XMLTAGDEF G_defMarkables = new XMLReader.XMLTAGDEF(
			"markables", false, // no text contents
			new XMLReader.XMLTAGDEF("markable", false, // no text contents
					null // empty xtSub
			));

	private static final XMLReader.XMLTAGDEF G_defNamedEntityScheme = new XMLReader.XMLTAGDEF(
			"annotationscheme", false, // no text contents
			new XMLReader.XMLTAGDEF("attribute", false, // no text contents
					new XMLReader.XMLTAGDEF("value", false, // no text
							// contents
							null // empty xtSub
					)));

	// --------------------------------------------------------------------------
	// Split basedata into sentences,
	// according to the information from 'alsession.txt'.
	//
	// I.e. add the words from basedata.xml into the right sentence
	// in the list 'lSD'.

	// Return a ref to the sentence in the LinkedList<SDTE>
	// in which word 'id' occurs.
	private static SDTE lSD_getSentence(LinkedList<SDTE> lSD, // IN
			double id) // IN (word id)
	{
		Iterator<SDTE> i = lSD.iterator();
		while (i.hasNext()) {
			SDTE sdte = i.next();

			// Sentence with words firstword=id0...lastword=idN includes words
			// with 'id' in [id0, idN+1)
			double idMin = sdte.iWord0;
			double idMax = (sdte.iWord0 + sdte.nWords + 1.0);
			if ((idMin <= id) && (id < idMax)) // <-- Note: '<', not '<='
			{
				return sdte;
			}
		}
		return null;
	}

	// added Method to convert MMAX XML back to text
	// (otherwise inconsistencies between annotated data and prediction corpus
	private static String xml2Text(String s) {

		// int i = s.indexOf("&lt;");

		String t = s.replace("&lt;", "<");
		t = t.replace("&gt;", ">");
		t = t.replace("&amp;", "&");
		t = t.replace("&quot;", "\"");
		t = t.replace("&apos;", "'");

		return t;

	}

	// Insert words (tokens) from basedata.xml into the LinkedList<SDTE>
	// 'lSD'. The list lSD contains one entry for each annotated
	// sentence. This insertion operation splits the words from
	// 'basedata.xml' into sentences.
	//
	// Read directly from basedata.xml file using code from Christina
	// = workaround for the fact that MMAX2 writes '>' and '<' directly
	// as tokens into the basedata.xml file (when annotator does any
	// basedata editing).
	//
	// Assumes that basedata.xml is sorted on word_N id, from small to
	// large.
	//
	// Uses existing contents of list lSD, as read from alsession.txt file,
	// to direct each input word from basedata.xml into the right sentence.
	//
	// If bIgnoreDET == true, then ignore the last word in 'basedata.xml'
	// if it could not be lodged inside any sentence.
	// (This last token was added to basedata.xml when creating the
	// MMAX-session as a dummy word, to make the style.XSL work.)
	//
	private static String lSD_addBasedata(LinkedList<SDTE> lSD, // IN+OUT (lAT
			// filled in in
			// each element)
			String basedatapath, // IN
			boolean bIgnoreDET) // IN (Ignore dummy end token)
	{
		// Implementation note:
		// Collects any words outside the sentences from lSD into a separate
		// LinkedList<AnnToken> lAT_orphan.
		// After reading the whole basedata file, we check if lAT_orphan
		// contains any items other than the very last token in 'basedata.xml'.
		// (This last token was added to basedata.xml when creating the
		// MMAX-session as a dummy word, to make the style.XSL work.)

		LinkedList<AnnToken> lAT_orphan = new LinkedList<AnnToken>();
		double idLast = -1.0;

		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(basedatapath));
			String wordnumber;
			String word;
			final int numberstart = 15;
			int numberend;
			br.readLine();
			br.readLine();
			br.readLine();
			String curLine = br.readLine();

			while (curLine != null && !(curLine.startsWith("</words>"))) {
				numberend = curLine.indexOf('\"', numberstart);
				wordnumber = curLine.substring(numberstart, numberend);
				word = curLine.substring(numberend + 2, curLine
						.indexOf("</word"));
				word = xml2Text(word);

				// DEBUG OUT( "read [" + wordnumber + "] [" + word + "]" );

				// Convert 'wordnumber' to a double
				double id;
				try {
					id = Double.parseDouble(wordnumber);
				} catch (Exception e) {
					throw new Exception("Fail parse wordnumber '" + wordnumber
							+ "'");
				}

				// Create new 'AnnToken' item for the word
				AnnToken anntoken = new AnnToken(id, word); // "O" ); //init as
				// IOB null tag

				// Add word to sentence in lSD list
				SDTE sdte = lSD_getSentence(lSD, id);
				if (sdte == null) {
					// DEBUG OUT( "Warning: Fail to lodge wordnumber '" +
					// DEBUG wordnumber + "' into any sentence" );
					lAT_orphan.add(anntoken);
				} else {
					sdte.lAT.add(anntoken);
				}

				idLast = id;

				curLine = br.readLine();
			}
		} catch (Exception e) {
			return "Fail read basedata: " + e.getMessage();
			//TODO this kind of exception handling is very special to MR's coding style (not good, though)
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		// DEBUG OUT( "idLast = " + idLast );

		// Check the 'lAT_orphan' list
		Iterator<AnnToken> i = lAT_orphan.iterator();
		while (i.hasNext()) {
			AnnToken at = i.next();
			if (!(bIgnoreDET && (at.id == idLast))) {
				return "Fail to lodge word id '" + at.id + "' into any "
						+ "sentence";
			}
		}

		return null; // ok
	}

	// --------------------------------------------------------------------------
	// Interpret the contents of the Schemes file
	//
	// Get the "annotation levels" (= tag types) into a "map", plus get
	// for each tag type its default value.

	// Get into HashMap<String,String> the relevant Schemes data.
	// Each entry in HashMap:
	// key = tag name
	// val = the default value (= the 1st value from Schemes)
	// 
	// Note: Convert both key and val to lower-case. Because MMAX2
	// converts the tag-names and -values to lower-case in the
	// markables.xml file (to which the data from the Schemes file is
	// compared).
	//
	// Return errmess on error, null on success.
	//
	private static String mapTT_make(LinkedList<String> lPrio,
			HashMap<String, String> mOut, // OUT
			LinkedList<XMLReader.XMLTAGVAL> lTVScheme_in, // IN
			boolean _v) {
		mOut.clear(); // init

		// Top XML element = "<annotationscheme>"
		if (lTVScheme_in.size() != 1) {
			return "Top nestinglevel in scheme must have 1 element";
		}

		XMLReader.XMLTAGVAL tvIn0 = lTVScheme_in.getFirst();
		LinkedList<XMLReader.XMLTAGVAL> lTagNames = tvIn0.lSub;

		Iterator<XMLReader.XMLTAGVAL> iTN = lTagNames.iterator();
		while (iTN.hasNext()) {
			XMLReader.XMLTAGVAL tvName = iTN.next();

			String tagname = XMLReader.lAttr_getValueByName(tvName.lAttr,
					"name");
			if (tagname == null) {
				return "No element 'name' in '<attribute...>' in scheme";
			}

			tagname = tagname.toLowerCase();
			if (_v) {
				OUT(" .  " + tagname);
			}

			if (tvName.lSub == null) {
				return "No sublist at d=1 in scheme";
			}

			if (tvName.lSub.size() < 1) {
				return "Tagname '" + tagname + "' has no values";
			}

			// Get the 1st tag value
			XMLReader.XMLTAGVAL tvVal0 = tvName.lSub.getFirst();
			String tagval0 = XMLReader.lAttr_getValueByName(tvVal0.lAttr,
					"name");
			if (tagval0 == null) {
				return "No element 'name' in '<value...>' of " + tagname;
			}

			tagval0 = tagval0.toLowerCase();
			if (_v) {
				OUT(" . .  " + tagval0);
			}

			// Add new item to output map
			System.err.println(tagname + "\t" + tagval0);
			// if(lPrio.contains(tagname))
			mOut.put(tagname, tagval0);
		}

		return null; // success
	}

	private static void mapTT_dump(HashMap<String, String> mTT) {
		Iterator<Map.Entry<String, String>> i = mTT.entrySet().iterator();
		while (i.hasNext()) {
			Map.Entry<String, String> e = i.next();
			OUT(" " + e.getKey() + " " + e.getValue());
		}
	}

	// --------------------------------------------------------------------------
	// Add the annotations from the the Markables file to the
	// words in the sentences in 'lSD'

	// Subroutine to 'addAnnotations()'.
	// Read the Markables item for one 'span' = one item
	// "<markable ... />" from the markables.xml file.
	// Deliver as output into ovSpam the value of the 'span' attribute.
	// ovSpan.value is guaranteed to be set to non-null value.
	// For each of the tags in the span that have non-default value,
	// create one and exactly one Tag object.
	// Deliver as output into list 'lTags' the tags for the span
	// that have non-default value. This output list may be empty.
	//
	// If bIgnoreDefault == true, then ignore all tags in the markables.xml
	// span that have default value (= 1st value from Schemes file).
	// If strIgnoreTagvalue != null, then ignore all tags in the
	// markables.xml span that have value 'strIgnoreTagvalue'.
	//
	// If bOutAddType == true, then use as tagname in the output:
	// "attr_value", where 'attr' = value of 'name' attribute in XML
	// <attribute ...> tag; and where 'value' value of XML <value .../> tag
	//
	private static String getSpan(HashMap<String, String> mTT, // IN
			XMLReader.XMLTAGVAL tvMrk, // IN (the input <markable...> item)
			boolean bIgnoreDefault, // IN
			String strIgnoreTagvalue, // IN
			boolean bOutAddType, // IN
			OV<String> ovSpan, // OUT
			LinkedList<Tag> lTags, // OUT
			boolean _v) {
		ovSpan.value = null; // init
		lTags.clear();

		String strSpan = null;

		if (_v) {
			OUT(" Start markable");
		}

		Iterator<XMLReader.NV> iNV = tvMrk.lAttr.iterator();
		while (iNV.hasNext()) {
			XMLReader.NV nv = iNV.next();

			// DEBUG OUT( " . nv = " + nv.name + " " + nv.value );

			// NV-pairs with name 'id' and 'span' are special ...
			if (nv.name.equals("id")) { // ignore
			} else if (nv.name.equals("span")) {
				assert (nv.value != null);
				if (_v) {
					OUT(" . span [" + nv.value + "]");
				}
				strSpan = nv.value;
			} else { // It's a tag name+value

				// Check in map 'mTT' if it has other than default value

				String dfltval = mTT.get(nv.name);
				// if ( dfltval == null )
				// { return "Unknown tagname '" + nv.name + "'"; }

				boolean bIgnore = false;
				if (bIgnoreDefault && nv.value.equals(dfltval)) {
					bIgnore = true;
				}
				if ((strIgnoreTagvalue != null)
						&& nv.value.equals(strIgnoreTagvalue)) {
					bIgnore = true;
				}

				if (!bIgnore) {
					// Use the tag string value only the 'tagvalue'.
					// NB: This is the code line to change if something
					// different is wanted in the tag string value.
					String sTag;
					if (bOutAddType) {
						sTag = nv.name + "_" + nv.value;
					} else {
						sTag = nv.value;
					}
					if (_v) {
						OUT(" . tag = " + sTag);
					}

					// Create new Tag object
					Tag tagNew = new Tag(sTag);

					// Add tag to output list
					lTags.add(tagNew);
				}
			}
		}

		if (strSpan == null) {
			return "No item 'span' in '<markable...>'";
		}

		ovSpan.value = strSpan;
		return null; // success
	}

	// Subroutine to 'applySpan_string()'.
	// For each word in idBegin...idEnd, add all tags in 'lTags_in' to
	// the word.
	// Checks if any of the ranges hits words in multiple sentences;
	// if so, then return an error message.
	// Return null on success.
	private static String applySpan_dblRange(double idBegin, double idEnd,
			LinkedList<Tag> lTags_in, // IN
			LinkedList<SDTE> lSD, // IN+OUT (lAT.lTag filled in)
			boolean _v) {
		// Iterate over the sentences
		Iterator<SDTE> i = lSD.iterator();
		while (i.hasNext()) {
			SDTE sdte = i.next();

			// Check that if one of idBegin and idEnd falls inside the
			// sentence, that then the other does so too.
			//
			// Sentence with words firstword=id0...lastword=idN includes words
			// with 'id' in [id0, idN+1)
			double idMin = (double) (sdte.iWord0 );
			double idMax = (double) (sdte.iWord0 + sdte.nWords + 1.0);

			boolean bHasBegin = ((idMin <= idBegin) && (idBegin < idMax));
			boolean bHasEnd = ((idMin <= idEnd) && (idEnd < idMax));

			if (bHasBegin != bHasEnd) {
				return "idBegin " + idBegin + "and idEnd " + idEnd
						+ "not in same sentence";
			}

			// Iterate over the words in the sentence, for each word check
			// if the word is in [idBegin,idEnd], and if so add to the word
			// all the tags in 'lTags'.
			Iterator<AnnToken> iAT = sdte.lAT.iterator();
			while (iAT.hasNext()) {
				AnnToken at = iAT.next();

				if ((idBegin <= at.id) && (at.id <= idEnd)) {
					Iterator<Tag> iTag_in = lTags_in.iterator();
					while (iTag_in.hasNext()) {
						Tag tag_in = iTag_in.next();
						at.lTag.add(tag_in);
					}
				}
			}
		}

		return null;
	}

	// Subroutine to 'addAnnotations()'.
	// Interpret and span definition string 'strSpan' from XML file
	// and apply the span to the words in the sentences in 'lSD'.
	private static String applySpan_string(String strSpan, // IN
			LinkedList<Tag> lTags, // IN
			LinkedList<SDTE> lSD, // IN+OUT (lAT.lTag filled in)
			boolean _v) {

		ArrayList<String> word_ids = new ArrayList<String>();
		String word_id = "";
		String[] splitOnPeriods = strSpan.split("\\.\\.");
		for (int i = 0; i < splitOnPeriods.length; i++) {
			String[] splitOnCommas = splitOnPeriods[i].split(",");
			for (int j = 0; j < splitOnCommas.length; j++) {
				word_id = splitOnCommas[j];
				word_ids.add(word_id);
			}
		}

		String firstWord_id = word_ids.get(0);
		String lastWord_id = word_ids.get(word_ids.size() - 1);
		double[] aId = new double[2];
		aId[0] = -1.0; // idBegin
		aId[1] = -1.0; // idEnd

		if (!firstWord_id.startsWith("word_")) {
			return "Missing 'word_' in span spec";
		}

		try {
			aId[0] = Double.parseDouble(firstWord_id.substring(5));
		} catch (Exception e) {
			return "Could not interpret word_N value";
		}
		if (aId[0] < 0.0) {
			return "word_N value must not be < 0";
		}

		if (!lastWord_id.startsWith("word_")) {
			return "Missing 'word_' in span spec";
		}

		try {
			aId[1] = Double.parseDouble(lastWord_id.substring(5));
		} catch (Exception e) {
			return "Could not interpret word_N value";
		}
		if (aId[1] < 0.0) {
			return "word_N value must not be < 0";
		}

		String erm = applySpan_dblRange(aId[0], // idBegin
				aId[1], // idEnd
				lTags, lSD, _v);
		if (erm != null) {
			return erm;
		}
		return null; // OK

		/*
		 * 
		 * //Split it on ',' String[] asMain = strSpan.split( "," ); if (
		 * asMain.length < 1 ) { return "Empty span spec"; }
		 * 
		 * //Split each substring on '..' int i; for ( i = 0; i < asMain.length;
		 * i++ ) { String sMain = asMain[i]; if(_v) { OUT( " . intrSpan: " +
		 * sMain ); } String[] asSub = sMain.split( "\\.\\." ); // ^^^^^^ //
		 * regex = "\.\." , need extra '\' to put in Java string literal.
		 * 
		 * //The result of the sMain.split() must be either: // - one substring
		 * (in case there is no ".." in sMain), or // - two substrings (in case
		 * there is ".." in SMain). //It's an error if sMain.split() returns
		 * fewer or more substrings. if ( asSub.length < 1 ) { return "Empty
		 * elem in span spec"; } if ( asSub.length > 2 ) { return "Wrong span
		 * spec elem"; }
		 * 
		 * double[] aId = new double[2]; aId[0] = -1.0; //idBegin aId[1] = -1.0;
		 * //idEnd
		 * 
		 * int k; for ( k = 0; k < asSub.length; k++ ) { if ( !
		 * asSub[k].startsWith( "word_" ) ) { return "Missing 'word_' in span
		 * spec"; }
		 * 
		 * try { aId[k] = Double.parseDouble( asSub[k].substring( 5 ) ); } catch (
		 * Exception e ) { return "Could not interpret word_N value"; } if (
		 * aId[k] < 0.0 ) { return "word_N value must not be < 0"; } }
		 * 
		 * if ( asSub.length == 1 ) { aId[1] = aId[0]; //idEnd = idBegin }
		 * 
		 * assert( aId[0] >= 0.0 ); assert( aId[1] >= 0.0 );
		 * 
		 * if(_v) { OUT( " . . range = " + aId[0] + " - " + aId[1] ); }
		 * 
		 * //Apply the tags 'lTags' to the word range aId[0]...aId[1] String erm =
		 * applySpan_dblRange( aId[0], //idBegin aId[1], //idEnd lTags, lSD, _v );
		 * if ( erm != null ) { return erm; }
		 * 
		 * }//for(i in asMain[])
		 * 
		 * return null; //OK
		 * 
		 */
	}

	// Return errmess on error, null on success.
	private static String addAnnotations(HashMap<String, String> mTT, // IN
			LinkedList<XMLReader.XMLTAGVAL> lTVMrkbl_in, // IN
			LinkedList<SDTE> lSD, // IN+OUT (lAT.lTag filled in)
			boolean bIgnoreDefault, // IN
			String strIgnoreTagvalue, // IN
			boolean bOutAddType, // IN
			boolean _v) {
		// Top XML element = "<markables>"
		if (lTVMrkbl_in.size() != 1) {
			return "Top nestinglevel in markables must have 1 element";
		}

		XMLReader.XMLTAGVAL tvIn0 = lTVMrkbl_in.getFirst();
		LinkedList<XMLReader.XMLTAGVAL> lMrk = tvIn0.lSub;

		Iterator<XMLReader.XMLTAGVAL> iM = lMrk.iterator();
		while (iM.hasNext()) {
			XMLReader.XMLTAGVAL tvMrk = iM.next();

			String erm = null;

			//
			// Get the span and tag data from the "<markable .../>" XML entry
			//
			OV<String> ovSpan = new OV<String>(null);
			LinkedList<Tag> lTags = new LinkedList<Tag>();
			erm = getSpan(mTT, tvMrk, bIgnoreDefault, strIgnoreTagvalue,
					bOutAddType, ovSpan, lTags, _v);
			if (erm != null) {
				return erm;
			}
			String strSpan = ovSpan.value;

			//
			// Apply the tag(s) over the span to the words in 'lSD'
			//
			erm = applySpan_string(strSpan, lTags, lSD, _v);
			if (erm != null) {
				return "Wrong span value '" + strSpan + "': " + erm;
			}

		}

		return null; // success
	}

	// --------------------------------------------------------------------------
	// Remove tags from each sentence in 'lSD' until
	// each token only has at most one tag left.

	// 1) Funcs operating on one sentence = one 'LinkedList<AnnToken> lAT'
	// list object.

	// Subroutine to 'lAT_nWordsWithTag()'
	// [NB: Use HashSet for 'lTag' member in class AnnToken]
	private static boolean lTag_contains(LinkedList<Tag> lTag, // IN
			Tag tag_in) // IN (reference)
	{
		Iterator<Tag> i = lTag.iterator();
		while (i.hasNext()) {
			Tag t = i.next();
			if (t.s.equalsIgnoreCase(tag_in.s)) {
				return true;
			} // compare references!
		}
		return false;
	}

	// Return the number of words in the sentence which contains
	// in its lTag list the reference value 'tag_in'.
	private static int lAT_nWordsWithTag(LinkedList<AnnToken> lAT, // IN
			Tag tag_in) // IN (reference)
	{
		int nWords = 0;
		Iterator<AnnToken> iTok = lAT.iterator();
		while (iTok.hasNext()) {
			AnnToken at = iTok.next();
			if (lTag_contains(at.lTag, tag_in)) {
				nWords++;
			}
		}
		return nWords;
	}

//	 Return the number of context words in the sentence which contains
	// in its lTag list the reference value 'tag_in'.
	private static int lAT_nContextWordsWithTag(LinkedList<AnnToken> lAT, // IN
			Tag tag_in, double wordIndex) // IN (reference)
	{
		int nWords = 0;
		int listIndex = 0;
		boolean found = false;
		
		//Iterator<AnnToken> iTok = lAT.iterator();
		for (int i=0; i < lAT.size(); i++) {
			AnnToken at = lAT.get(i);
			if(at.id == wordIndex) {
				listIndex = i;
				nWords++;
				found = true;
				break;
			}
		}
		
		if (found) {
			//forward looking:
			for (int i = listIndex+1; i < lAT.size(); i++) {
				AnnToken at = lAT.get(i);
				if (lTag_contains(at.lTag, tag_in)) {
					nWords++;
				}
				else break;
			}
			//backward looking:
			for (int i = listIndex-1; i >= 0; i--) {
				AnnToken at = lAT.get(i);
				if (lTag_contains(at.lTag, tag_in)) {
					nWords++;
				}
				else break;
			}
		}
		
		return nWords;
	}
	
	// Remove the tag 'tag_in' from all words in the sentence in/on which
	// the tag occurs.
	private static void lAT_removeTag(AnnToken at, LinkedList<AnnToken> lAT, // IN
			Tag tag_in) // IN (reference)
	{

		// Iterator<AnnToken> iTok = lAT.iterator();
		// while ( iTok.hasNext() )
		// {
		// AnnToken at = iTok.next();
		boolean removed = at.lTag.remove(tag_in);
		System.out.println(at.token + " ==> " + tag_in.s + " removed: " + true);
		System.out.println("Tag list size: " + at.lTag.size());
		// }
	}

	private static String lAT_remDoubles(LinkedList<AnnToken> lAT, // IN+OUT
			// (removes
			// from
			// lAT.lTag)
			LinkedList<String> lPrio, // IN
			String textID, // IN (only used for log msg)
			int sentID, // IN (only used for log msg)
			boolean _log, boolean _v) {
		// In each iteration of this loop, we seek the first token in the
		// sentence with multiple tags, and then we remove only ONE of this
		// set of tags from the sentence. (What is removed from the sentence
		// is: all references to that same Tag object.)
		// The loop is exited if no more token with multiple tags is found in
		// the sentence.
		
		double wordIndex = 0;
		
		for (;;) {
			// Find the next token with more than one tag
			AnnToken atClash = null;
			Iterator<AnnToken> iTok = lAT.iterator();
			while (iTok.hasNext()) {
				AnnToken at = iTok.next();
				if (at.lTag.size() > 1) {
					atClash = at;
					wordIndex = atClash.id;
					break;
				}
			}

			if (atClash == null) {
				if (_v) {
					OUT(". done");
				}
				return null; // END OF FOR-LOOP
			}

			assert (atClash.lTag.size() > 1);

			if (_log) {
				System.out.print("Multiple tags on word '" + atClash.token
						+ "' in textID=" + textID + ", sentID=" + sentID
						+ " : ");
				Iterator<Tag> i = atClash.lTag.iterator();
				while (i.hasNext()) {
					System.out.print(i.next().s + " ");
				}
				System.out.println("");
			}

			// In this iteration we will remove only ONE of the two or more
			// tags, and if there are more than two we leave removing the
			// others till later loop iterations.

			Tag tRem = null; // This will be set to the tag to be removed

			// Look at an arbitrary subset of two of the tags on atClash;
			// we arbitrarily take the first two.
			Iterator<Tag> iCTag = atClash.lTag.iterator();
			assert (iCTag.hasNext());
			Tag t1 = iCTag.next();
			assert (iCTag.hasNext());
			Tag t2 = iCTag.next();

			// Lookup the two tags in the prio-list
			int iPrio1 = priolist_getPrio(lPrio, t1.s);
			if (iPrio1 < 0) {
				iPrio1 = 667;
				//return "Tag '" + t1.s + "' not in prio-list";
			}
			int iPrio2 = priolist_getPrio(lPrio, t2.s);
			if (iPrio2 < 0) {
				iPrio2 = 667;
				//return "Tag '" + t2.s + "' not in prio-list";
			}

			OUT(". tag1 = " + t1.s + " prio = " + iPrio1);
			OUT(". tag2 = " + t2.s + " prio = " + iPrio2);

			/*
			 * if ( iPrio1 != iPrio2 ) { //Remove the lower-priority tag tRem = (
			 * (iPrio1 > iPrio2) ? t1 : t2 ); OUT( ". Remove lower-prio tag = " +
			 * tRem.s ); } else //Both tags have same priority {
			 */

			// Look at the # of words annotated by the two tags
			int len1 = lAT_nContextWordsWithTag( lAT, t1, wordIndex );
			System.out.println(t1.s + " with " + len1 + " words");
			assert( len1 > 0 );
			int len2 = lAT_nContextWordsWithTag( lAT, t2, wordIndex );
			assert( len2 > 0 );
			System.out.println(t2.s + " with " + len2 + " words");
			if (len1 != len2) {
				// Remove the tag with fewer #words
				tRem = ((len1 < len2) ? t1 : t2);
				OUT(". Remove tag on fewer words = " + tRem.s);
			} else if (iPrio1 != iPrio2) // Same priority AND same length
			{
				tRem = ((iPrio1 > iPrio2) ? t1 : t2);
				OUT(". Remove lower-prio tag = " + tRem.s);
			}

			else {
				// Remove arbitrarily one of both; take the 1st one.
				tRem = t1;
				OUT(". Tags same; remove arbitrary one = " + tRem.s);
			}
			// }

			assert (tRem != null);

			// Remove the reference to object tRem from all words in the
			// sentence
			lAT_removeTag(atClash, lAT, tRem);
			wordIndex = 0;
		}// for(;;)
	}

	// 2) Funcs that apply the sentence operations to all sentences
	// in the list 'lSD'

	private static String lSD_remDoubles(LinkedList<SDTE> lSD, // IN+OUT
			LinkedList<String> lPrio, // IN
			boolean _log, boolean _v) {
		Iterator<SDTE> i = lSD.iterator();
		while (i.hasNext()) {
			SDTE sdte = i.next();

			String erm = lAT_remDoubles(sdte.lAT, lPrio, sdte.textID,
					sdte.sentID, _log, _v);
			if (erm != null) {
				return erm;
			}
		}

		return null;
	}

	// --------------------------------------------------------------------------
	// MAIN()

	private static void usage() {
		ERR("Usage: Mmax2Iob1 " + "sentencedef_file " + // 0 sentence_level.xml
				"scheme_file " + // 1
				"priolist_txt_file " + // 2
				"basedata_file " + // 3
				"markables_level_file " + // 4
				"output_iob_file " + // 5
				"[options]");
		ERR("Options are: \n"
				+ "   -v     Verbose (dump debug info)\n"
				+ "   -x     Dump contents of XML files as read\n"
				+ "   -u     Ignore tagvalue \"Unspecified\" (default: Ignore the "
				+ "default tagvalue\n"
				+ "   -t     Prepend \"tagtype_\" before output tagvalues");
	}

	public static void main(String[] args) {
		final int N_REQ_ARGS = 6;
		if (args.length < N_REQ_ARGS) {
			usage();
			System.exit(-1);
		}

		String sentdefpath = args[0];
		String schemepath = args[1];
		String priopath = args[2];
		String basedatapath = args[3];
		String markablespath = args[4];
		String outfilepath = args[5];

		// Default option values
		boolean _v = false;
		boolean _vXML = false; // Dump details of XML data as read
		boolean bIgnoreDefault = true; // false;
		String strIgnoreTagvalue = null; // "Unspecified";
		boolean bOutAddType = false; // Take value 'true' for testing

		// Options
		int iOpt;
		for (iOpt = N_REQ_ARGS; iOpt < args.length; iOpt++) {
			String sOpt = args[iOpt];
			if (sOpt.length() < 2) {
				usage();
				System.exit(-1);
			}
			switch (sOpt.charAt(1)) {
			case 'v':
				_v = true;
				break;
			case 'x':
				_vXML = true;
				break;
			case 'u':
				bIgnoreDefault = false;
				strIgnoreTagvalue = "Unspecified";
				break;
			case 't':
				bOutAddType = true;
				break;
			default:
				usage();
				System.exit(-1);
				break;
			}
		}

		OUT("sentdef   = " + sentdefpath);
		OUT("scheme    = " + schemepath);
		OUT("prio      = " + priopath);
		OUT("basedata  = " + basedatapath);
		OUT("markables = " + markablespath);
		OUT("outfile   = " + outfilepath);

		String erm = null;

		// {VERSION THAT READS sentence_level.xml TO INIT lSD
		//
		// Read sentence_level.xml file
		//
		OUT("===Reading sentence_level.xml file ...");
		LinkedList<XMLReader.XMLTAGVAL> lSentDef = new LinkedList<XMLReader.XMLTAGVAL>();
		erm = XMLReader.readXMLFile(G_defMarkables, sentdefpath, lSentDef,
				false);
		if (erm != null) {
			ERR("Fail read '" + sentdefpath + "' : " + erm);
			System.exit(-1);
		}
		if (_vXML) {
			OUT("===SentLevel:");
			XMLReader.lTV_dump(lSentDef, 0);
		}

		// Create initial 'lSD' list from sentence_level.xml data
		LinkedList<SDTE> lSD = new LinkedList<SDTE>();
		erm = sdtable_initFromSentDef(lSD, lSentDef);
		if (erm != null) {
			ERR("Fail init lSD from sent_level data: " + erm);
			System.exit(-1);
		}

		OUT("===lSD after reading sentence def :");
		OUT("lSD.len = " + lSD.size());
		lSD_dump(lSD);

		// }

		// {VERSION THAT READS alsession.txt TO INIT lSD
		/*
		 * // // Read alsession.txt file // OUT( "===Reading alsession.txt ..." );
		 * LinkedList<SDTE> lSD = new LinkedList<SDTE>(); erm = sdtable_read(
		 * lSD, alsessionpath ); if ( erm != null ) { ERR( "Fail read '" +
		 * alsessionpath + "' : " + erm ); System.exit(-1); }
		 * 
		 * OUT( "===lSD after reading alsession.txt :" ); OUT( "lSD.len = " +
		 * lSD.size() ); lSD_dump( lSD );
		 */
		// }
		//
		// Read basedata.xml file, add its data into 'lSD'
		//
		OUT("===Reading basedata ...");
		erm = lSD_addBasedata(lSD, basedatapath, true); // ignore last dummy
		// word
		if (erm != null) {
			ERR("Fail read '" + basedatapath + "' : " + erm);
			System.exit(-1);
		}

		if (_v) {
			OUT("===lSD after adding basedata:");
			lSD_dump(lSD);
		}

		/*
		 * Version for reading basedata.xml that is maybe preferred if the bug
		 * in MMAX2 is repaired that MMAX2 writes '<' into basedata.xml :
		 * 
		 * LinkedList<XMLReader1.XMLTAGVAL> lBasedata = new LinkedList<XMLReader1.XMLTAGVAL>();
		 * erm = XMLReader1.readXMLFile( G_defBasedata, basedatapath, lBasedata,
		 * _v ); if ( erm != null ) { ERR( "Fail read '" + basedatapath + "' : " +
		 * erm ); System.exit(-1); } XMLReader1.lTV_dump( lBasedata, 0 );
		 */

		//
		// Read Schemes/*.xml file
		//
		OUT("===Reading schemes file ...");
		LinkedList<XMLReader.XMLTAGVAL> lScheme = new LinkedList<XMLReader.XMLTAGVAL>();
		erm = XMLReader.readXMLFile(G_defNamedEntityScheme, schemepath,
				lScheme, false);
		if (erm != null) {
			ERR("Fail read '" + schemepath + "' : " + erm);
			System.exit(-1);
		}
		if (_vXML) {
			OUT("===Scheme:");
			XMLReader.lTV_dump(lScheme, 0);
		}

		/*
		 * This code could maybe be used if the '>' bug in MMAX2 is solved.
		 * LinkedList<TagType> lTT = new LinkedList<TagType>(); erm =
		 * lTT_make( lTT, lScheme, false ); if ( erm != null ) { ERR( "Fail make
		 * tagtype-list from scheme data: " + erm ); System.exit(-1); } OUT(
		 * "===lTT:" ); lTT_dump( lTT );
		 */

		//
		// Read 'priolist.txt'
		//
		LinkedList<String> lPrio = new LinkedList<String>();
		erm = priolist_read(lPrio, priopath);
		if (erm != null) {
			ERR("Fail read '" + priopath + "' : " + erm);
			System.exit(-1);
		}
		OUT("===priolist:");
		priolist_dump(lPrio);

		HashMap<String, String> mTT = new HashMap<String, String>();

		erm = mapTT_make(lPrio, mTT, lScheme, false);
		if (erm != null) {
			ERR("Fail make tagtype-map from scheme data: " + erm);
			System.exit(-1);
		}
		if (_v) {
			OUT("===mTT:");
			mapTT_dump(mTT);
		}

		//
		// Read Markables/*.xml file
		//

		OUT("===Reading markables file ...");
		LinkedList<XMLReader.XMLTAGVAL> lMarkables = new LinkedList<XMLReader.XMLTAGVAL>();
		erm = XMLReader.readXMLFile(G_defMarkables, markablespath, lMarkables,
				false);
		if (erm != null) {
			ERR("Fail read '" + markablespath + "' : " + erm);
			System.exit(-1);
		}
		if (_vXML) {
			OUT("===Markables:");
			XMLReader.lTV_dump(lMarkables, 0);
		}

		// Add all tags from markables file into 'lSD'

		erm = addAnnotations(mTT, lMarkables, lSD, bIgnoreDefault,
				strIgnoreTagvalue, bOutAddType, _v);
		if (erm != null) {
			ERR("Fail addAnnotations: " + erm);
			System.exit(-1);
		}

		if (_v) {
			OUT("===lSD after adding markables data:");
			lSD_dump(lSD);
		}

		// Remove multiple tags on the same token

		OUT("===Running remDoubles ...");
		erm = lSD_remDoubles(lSD, lPrio, true, _v);
		if (erm != null) {
			ERR("Fail remDoubles: " + erm);
			System.exit(-1);
		}

		if (_v) {
			OUT("===lSD after remDoubles:");
			lSD_dump(lSD);
		}

		//
		// Print final output to file
		//

		PrintWriter pwOut = null;
		try {
			pwOut = new PrintWriter(outfilepath);
		} catch (Exception e) {
			ERR("Fail open '" + outfilepath + "': " + e.getMessage());
		}

		lSD_printIOB(lSD, pwOut);

		pwOut.close();

		OUT("===Output file '" + outfilepath + "' written");

		System.exit(0);
	}

	/**
	 * @author tomanek
	 * 
	 * to start the conversion not only from the main method, i write a new
	 * function which does essentially the same as the main method (hopefully!)
	 * but can be called from a java programm.
	 * 
	 * @param sentdefpath
	 *            path to sentence level markable
	 * @param markablespath
	 *            path to main level markable
	 * @param basedatapath
	 *            path to basedata
	 * @param priopath
	 *            path to priolist
	 * @param schemepath
	 *            path to main schema
	 * @param outfilepath
	 *            path to outfile
	 */
	public static void run(String sentdefpath, String markablespath,
			String basedatapath, String priopath, String schemepath,
			String outfilepath)  {

		// Default option values
		// don't know exactly what they mean, but I just copied 'em from main
		// methods
		boolean _v = false;
		boolean _vXML = false; // Dump details of XML data as read
		boolean bIgnoreDefault = false; // false: do not ignored unspecified label;
		String strIgnoreTagvalue = null; // "Unspecified";
		boolean bOutAddType = false; // Take value 'true' for testing

		String erm = null;

		// OUT("===Reading sentence_level.xml file ...");
		LinkedList<XMLReader.XMLTAGVAL> lSentDef = new LinkedList<XMLReader.XMLTAGVAL>();
		erm = XMLReader.readXMLFile(G_defMarkables, sentdefpath, lSentDef,
				false);
		if (erm != null)
			throw new RuntimeException("Fail read '" + sentdefpath + "' : " + erm);


		// Create initial 'lSD' list from sentence_level.xml data
		LinkedList<SDTE> lSD = new LinkedList<SDTE>();
		erm = sdtable_initFromSentDef(lSD, lSentDef);
		if (erm != null)
			throw new RuntimeException("Fail init lSD from sent_level data: " + erm);


		// OUT("===Reading basedata ...");
		erm = lSD_addBasedata(lSD, basedatapath, true); // ignore last dummy
		// word
		if (erm != null)
			throw new RuntimeException("Fail read '" + basedatapath + "' : " + erm);


		// OUT("===Reading schemes file ...");
		LinkedList<XMLReader.XMLTAGVAL> lScheme = new LinkedList<XMLReader.XMLTAGVAL>();
		erm = XMLReader.readXMLFile(G_defNamedEntityScheme, schemepath,
				lScheme, false);
		if (erm != null)
			throw new RuntimeException("Fail read '" + schemepath + "' : " + erm);

		// Read 'priolist.txt'
		LinkedList<String> lPrio = new LinkedList<String>();
		erm = priolist_read(lPrio, priopath);
		if (erm != null)
			throw new RuntimeException("Fail read '" + priopath + "' : " + erm);


		HashMap<String, String> mTT = new HashMap<String, String>();

		erm = mapTT_make(lPrio, mTT, lScheme, false);
		if (erm != null)
			throw new RuntimeException("Fail make tagtype-map from scheme data: " + erm);

		// OUT("===Reading markables file ...");
		LinkedList<XMLReader.XMLTAGVAL> lMarkables = new LinkedList<XMLReader.XMLTAGVAL>();
		erm = XMLReader.readXMLFile(G_defMarkables, markablespath, lMarkables,
				false);
		if (erm != null)
			throw new RuntimeException("Fail read '" + markablespath + "' : " + erm);

		// Add all tags from markables file into 'lSD'
		erm = addAnnotations(mTT, lMarkables, lSD, bIgnoreDefault,
				strIgnoreTagvalue, bOutAddType, _v);
		if (erm != null)
			throw new RuntimeException("Fail addAnnotations: " + erm);

		// OUT("===Running remDoubles ...");
		erm = lSD_remDoubles(lSD, lPrio, true, _v);
		if (erm != null)
			throw new RuntimeException("Fail remDoubles: " + erm);


		// Print final output to file
		PrintWriter pwOut = null;
		try {
			pwOut = new PrintWriter(outfilepath);
		} catch (Exception e) {
			throw new RuntimeException("Fail open '" + outfilepath + "': " + e.getMessage());
		}


		lSD_printIOB(lSD, pwOut);

		pwOut.close();

	}

}
