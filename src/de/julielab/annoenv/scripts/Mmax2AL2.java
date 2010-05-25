package de.julielab.annoenv.scripts;


//Mmax2AL2.java   MR May '06
//
// Reads files from the output end of an MMAX2 session, and convert
// them to data that can be processed further in the AL-process.
//
// One of the files read is 'alsession.txt', which was generated
// (by MkALStyle1.java) when the MMAX2 session was created.  This
// file tells which word spans in basedata.xml correspond to sentences
// and specifies the textID and sentenceID of these sentences.
//
// The other files read are:
//     - basedata.xml
//     - Markables/project_XY_level.xml
//     - Schemes/XY_scheme.xml 
//     - Schemes/priolist.txt
//
// The filenames of all these files are specified as parameters to
// the method that does the work.
//
//---
//
// Also fills in the POS-tags on the tokens in the MMAX2 output.
// The POS-tags are read from files produced as output by the POS-tagger,
// in OPEN-NLP format; one file per abstract, with filename = textID.  
// The POS-tagged files must all sit in one directory, which is specified
// on the command line (e.g. 
// "/data/data/data_corpora/genomics/GeneOnto/abstracts/pos").
//
//---
//
// The output is in our own ".t2" file format.  This file format is
// as follows:  Each sentence in the output is a line
//
//      SENTENCE textID sentenceID [nTokens]
//
// followed immediately by a block of 'nTokens' lines (one line for each
// token in the sentence) like this:
//
//      token1 POS1 ANNLABEL1
//      token2 POS2 ANNLABEL2
//      ...    ...  ...
//      tokenn POSn ANNLABELn
//
// where
//   SENTENCE  = literal keyword (upper case)
//   tokeni    = token
//   POSi      = POS-label
//   ANNLABELi = annotation tag from MMAX2.
//
// The block lines with the tokens for a sentence is followed for
// each sentence with an empty line.
//
//---
//
// A boolean input parameter specifies whether the ANNLABEL labels 
// in the output either (1) have 'B-' or 'I-' prefixed to them, or 
// (2) do not have 'B-' or 'I-' prefixed to them. 
// Irrespective of this setting, the ANNLABEL value for unlabeled 
// (unannotated) tokens always is the single character 'O'.  
//

//-----------------------------------------------------------------------------
// The class defines as public items only the following two static methods: 
//
//   convertSessionToT2() -- For call from Java-program
//   main() -- For call as stand-alone program.

//-----------------------------------------------------------------------------
// Uses: XMLReader1.java
//
//-----------------------------------------------------------------------------


/**
 * makes IOB
 */


import java.io.*;
import java.util.*; //For LinkedList, HashSet

//Own packages:
//import butil.OV; 



/**
 * makes IOB
 * @deprecated
 */
public
class Mmax2AL2
	{
	private static void OUT( String s ) { System.out.println( s ); }
	private static void ERR( String s ) { System.err.println( s ); }





	//--------------------------------------------------------------------------
	// DATA TYPE


	//Sentence Definition Table Entry = Line from file 'alsession.txt';
	// with in it the extra element 'lAT', into which the data from
	// basedata.xml and from markables.xml is filled in.
	private static
	class SDTE 
		{
		public int iWord0;
		public int nWords;
		public String textID;
		public int    sentID;

		//List lPOS: Contains the POS-tags for the sentence, as returned
		// by method readPosCorpFile().  Initialized to empty list.
		public LinkedList<S2> lPOS;

		//List lAT: Initialized to empty list; Later the data from
		// basedata.xml and from markables.xml is filled in into it.
		public LinkedList<AnnToken> lAT;

		public SDTE( int i0, int n0, String tid0, int sid0 )
			{ 
			iWord0 = i0; nWords = n0; 
			textID = tid0; sentID = sid0; 
			lPOS = new LinkedList<S2>();  
			lAT = new LinkedList<AnnToken>();  
			}
		}


	//Tagged sentence = list or array of AnnToken.
	//A LinkedList<AnnToken> is a member in datatype 'SDTE'.
	private static 
	class AnnToken
		{
		public double id;     //Word id from basedata.xml
		public String token;  

		public String pos;    //POS-tag read from "pos/textID" file 

		//All tags, directly from 'markables.xml'. 
		//Note: It is crucial to the program design that the list contains 
		// *REFERENCES* to 'Tag' objects.
		public LinkedList<Tag> lTag; 

		public AnnToken( double id0, String tok0, String pos0 ) 
			{ 
			id = id0; token = tok0; 
			pos = pos0;
			lTag = new LinkedList<Tag>();
			}
		}


	//Class 'Tag':
	//Class for tag value from "<markable ... />" item from markables.xml
	// data.  
	//Purpose: To create one single OBJECT for each tag value from one
	// span = one "<markable ... />" item from markables.xml file,
	// so that the lSD.lAT.lTag for different words can contain references 
	// to the same Tag object when these words belong to the same 'span'.
	// This is how from the lSD contents, we can detect when words with
	// the same string label as tag belong to the same or to different
	// spans.  E.g. to distinguish, for the sentence "a b c d e f", between 
	// the annotation       [ a b c ]TAG1 [ d e f ]TAG1
	// and the annotation   [ a b c d e f ]TAG1 .
	//The detection whether words belong to the same span is thus done
	// by comparing *references* to Tag objects.  (NOT by comparing the
	// string contents of the Tag objects.)
	//
	//This would probably have been possible by comparing tag string
	// *references* rather than string values.  I created a separate 
	// class 'Tag' to make everything more explicit, and to make sure
	// that one and only one OBJECT is created for one 'span' 
	// (=  "<markable ... />" item) from the markables.xml file.
	private static 
	class Tag
		{
		public String s;

		public Tag( String s0 ) { s = s0; }
		}



	private static void
	lSD_dump(
		LinkedList<SDTE> lSD )   //IN
		{
		Iterator<SDTE> i = lSD.iterator();
		while ( i.hasNext() )
			{
			SDTE sdte = i.next();
			OUT( "i0=" + sdte.iWord0 + " " +
				"n="   + sdte.nWords + " " +
				"tid=" + sdte.textID + " " +
				"sid=" + sdte.sentID + " " +
				"nTok=" + sdte.lAT.size() );

			if ( sdte.lAT.size() != 0 )
				{
				OUT( "tokens: " );
				Iterator<AnnToken> iAT = sdte.lAT.iterator();
				while ( iAT.hasNext() )
					{
					AnnToken at = iAT.next();
					System.out.print( "   " + at.id + " " + at.token + 
						" : " );

					Iterator<Tag> iTag = at.lTag.iterator();
					while ( iTag.hasNext() )
						{
						Tag tag = iTag.next();
						System.out.print( tag.s + " " );
						}
					System.out.println( "" );
					}
				}
			}

		}





	//Print final contents of list 'lSD' in own ".aiob" format =
	// = IOB but before each sentence an extra line added which
	// contains the following:
	//
	//     SENTENCE textID sentenceID nTokens <newline>
	//                |                          |
	//              keyword                  Number of tokens in sentence
	//
	//Only call this AFTER 'lSD_remDoubles()' has been run on 'lSD' !
	//
	private static void
	lSD_printAIOB(
		LinkedList<SDTE> lSD,    //IN
		PrintWriter pwOut )      //OUT (Where to print to)
		{
		Iterator<SDTE> i = lSD.iterator();
		while ( i.hasNext() )
			{
			SDTE sdte = i.next();
			pwOut.println( 
				"SENTENCE " + 
				sdte.textID + " " + 
				sdte.sentID + " " +
				sdte.lAT.size() );

			assert( sdte.lAT.size() > 0 );

			Tag tPrev = new Tag("O");
			Iterator<AnnToken> iAT = sdte.lAT.iterator();
			while ( iAT.hasNext() )
				{
				AnnToken at = iAT.next();
				pwOut.print( at.token + " " );

				if ( at.lTag.size() == 0 )
					{
					pwOut.println( "O" );
					tPrev = new Tag("O"); //reset
					}
				else
					{
					Tag t = at.lTag.getFirst();
					pwOut.print((t.s.equalsIgnoreCase(tPrev.s)) ? "I-" : "B-" );
					pwOut.println( t.s );
					tPrev = t;
					}
				}

			pwOut.println( "" ); //Empty line between sentences
			}

		}


	//Print final contents of list 'lSD' in own ".t2" format.
	//Only call this AFTER 'lSD_remDoubles()' has been run on 'lSD' !
	//
	//If bWithBI is true, then print "B-" or "I-" before nonempty 
	// tags from the MMAX2-annotation.
	//
	private static void
	lSD_printT2(
		LinkedList<SDTE> lSD,    //IN
		boolean bWithBI,         //IN
		PrintWriter pwOut )      //OUT (Where to print to)
		{
		Iterator<SDTE> i = lSD.iterator();
		while ( i.hasNext() )
			{
			SDTE sdte = i.next();
			pwOut.println( 
				"SENTENCE " + 
				sdte.textID + " " + 
				sdte.sentID + " " +
				sdte.lAT.size() );

			assert( sdte.lAT.size() > 0 );

			Tag tPrev = new Tag("O");
			Iterator<AnnToken> iAT = sdte.lAT.iterator();
			while ( iAT.hasNext() )
				{
				AnnToken at = iAT.next();
				pwOut.print( at.token + " " );
				pwOut.print( at.pos + " " );

				if ( at.lTag.size() == 0 )
					{
					pwOut.println( "O" );
					tPrev = new Tag("O"); //reset
					}
				else
					{
					Tag t = at.lTag.getFirst();
					System.out.println("Tag: " + tPrev.s);
					if ( bWithBI )
					    { pwOut.print((t.s.equalsIgnoreCase(tPrev.s)) ? "I-" : "B-" ); }
					pwOut.println( t.s );
					tPrev = t;
					}
				}

			pwOut.println( "" ); //Empty line between sentences
			}

		}






	//--------------------------------------------------------------------------
	// Sentence Definition Table = file 'alsession.txt'



	//Scan input string containing an integer >= iMin.
	//Subroutine used only in 'sdtable_read()'
	private static boolean
	scanInt( 
		String sIn, 
		int iMin,
		OV<Integer> ovOut )
		{
		Integer i;
		try { i = new Integer( sIn ); }
		catch ( Exception e ) { return false; }
		if ( i < iMin ) { return false; }
		ovOut.value = i;
		return true; 
		}

	//Read file 'alsession.txt'; put the data from the file into
	// create LinkedList<SDTE> 'lOut'.
	private static String
	sdtable_read(
		LinkedList<SDTE> lOut,   //OUT
		String pathname )        //IN (Pathname of file to read)
		{
		lOut.clear(); //init

		BufferedReader br = null;
		try
			{
			br = new BufferedReader( new FileReader( pathname ) );

			int iLine = 1;
			String sIn;
			while( ( sIn = br.readLine() ) != null )
				{
				//DEBUG OUT( "line " + iLine + ": " + sIn );

				String[] as = sIn.split( "[ \t]+" );
				//DEBUG OUT( "nTok=" + as.length );
				if ( as.length != 4 )
					{ throw new Exception( "Line " + iLine + ": " +
						"Wrong number of tokens" ); }


				OV<Integer> ovIWord0 = new OV<Integer>( null ); 
				OV<Integer> ovNWords = new OV<Integer>( null ); 
				OV<Integer> ovSentID = new OV<Integer>( null ); 

				if ( ! scanInt( as[0], 0, ovIWord0 ) )
					{ throw new Exception( "Line " + iLine + ": " +
						"Fail interpret 1st token (iWord0)" ); }
				if ( ! scanInt( as[1], 0, ovNWords ) )
					{ throw new Exception( "Line " + iLine + ": " +
						"Fail interpret 2nd token (nWords)" ); }
				if ( ! scanInt( as[3], 0, ovSentID ) )
					{ throw new Exception( "Line " + iLine + ": " +
						"Fail interpret 4th token (sentID)" ); }

				//DEBUG OUT( "iWord0 = " + ovIWord0.value.intValue() );
				//DEBUG OUT( "nWords = " + ovNWords.value.intValue() );
				//DEBUG OUT( "sentID = " + ovSentID.value.intValue() );

				SDTE sdeNew = new SDTE(
					ovIWord0.value.intValue(), 
					ovNWords.value.intValue(), 
					as[2],
					ovSentID.value.intValue() );

				lOut.add( sdeNew );

				iLine++;
				}
			}
		catch ( Exception e )
			{
			return "Fail read sdtable: " + e.getMessage();
			}
		finally
			{
			if ( br != null ) 
				{ 
				try { br.close(); } catch ( Exception e ) {}
				}
			}
		return null; //success
		}





	//--------------------------------------------------------------------------
	// Priority list file 'priolist.txt'
	//
	// File format:
	//   One tagvalue per line.
	//   The tagvalues are case-insensitive.
	//   Lines beginning with '#' and empty lines are ignored.
	//   Earlier (higher) in file means higher priority.
	//


	//Read the contents of the file into a LinkedList<String>.
	//Note: Convert each tagvalue from the file to lower-case.  Because MMAX2
	// converts the tag-names and -values to lower-case in the
	// markables.xml file (to which the data from the priolist.txt file is 
	// compared).
	private static String
	priolist_read(
		LinkedList<String> lOut,   //OUT
		String pathname )          //IN (Pathname of file to read)
		{
		lOut.clear(); //init

		BufferedReader br = null;
		try
			{
			br = new BufferedReader( new FileReader( pathname ) );

			int iLine = 0;
			String sIn;
			while( ( sIn = br.readLine() ) != null )
				{
				iLine++;

				sIn = sIn.trim();
				if ( sIn.length() == 0 ) { continue; }
				if ( sIn.charAt(0) == '#' ) { continue; }

				sIn = sIn.toLowerCase();
				lOut.add( sIn );
				}
			}
		catch ( Exception e )
			{
			return "Fail read priolist: " + e.getMessage();
			}
		finally
			{
			if ( br != null ) 
				{ 
				try { br.close(); } catch ( Exception e ) {}
				}
			}
		return null; //success
		}

	private static void
	priolist_dump( LinkedList<String> lPrio )
		{
        Iterator<String> i = lPrio.iterator();
        while ( i.hasNext() )
            {
			OUT( " " + i.next() );
			}
		}


	//Lookup tag value 'sTag' in lPrio.
	//If found, then return the index number of
	// the tag in the prio-list (SMALLER int value means HIGHER priority).
	//If not found, then return -1.
	private static int
	priolist_getPrio( 
		LinkedList<String> lPrio,    //IN
		String sTag )                //OUT
		{
		return lPrio.indexOf( sTag );
		}





	//--------------------------------------------------------------------------
	// Definitions of the XML files with basedata, markables, and scheme;
	// used in the XMLReader1.readXMLFile() calls to read these XML files.

	private static final XMLReader1.XMLTAGDEF G_defBasedata =
	new XMLReader1.XMLTAGDEF
		(
		"words",
		false, //no text contents
		new XMLReader1.XMLTAGDEF
			(
			"word",
			true,
			null   //empty xtSub
			)
		);

	private static final XMLReader1.XMLTAGDEF G_defMarkables = 
	new XMLReader1.XMLTAGDEF
		(
		"markables",
		false, //no text contents
		new XMLReader1.XMLTAGDEF
			(
			"markable",
			false, //no text contents
			null   //empty xtSub
			)
		);

	private static final XMLReader1.XMLTAGDEF G_defNamedEntityScheme 
	= new XMLReader1.XMLTAGDEF
		(
		"annotationscheme",
		false, //no text contents
		new XMLReader1.XMLTAGDEF
			(
			"attribute",
			false, //no text contents
			new XMLReader1.XMLTAGDEF
				(
				"value",
				false, //no text contents
				null   //empty xtSub
				)
			)
		);


	


	//--------------------------------------------------------------------------
	// Split basedata into sentences,
	// according to the information from 'alsession.txt'.
	//
	// I.e. add the words from basedata.xml into the right sentence
	// in the list 'lSD'.


	//Return a ref to the sentence in the LinkedList<SDTE>
	// in which word 'id' occurs.
	private static SDTE
	lSD_getSentence(
		LinkedList<SDTE> lSD,  //IN
		double id )            //IN (word id)
		{
        Iterator<SDTE> i = lSD.iterator();
        while ( i.hasNext() )
            {
            SDTE sdte = i.next();

			//Sentence with words firstword=id0...lastword=idN includes words 
			// with 'id' in [id0, idN+1)
			double idMin = sdte.iWord0;
			double idMax = ( sdte.iWord0 + sdte.nWords + 1.0 );
			if ( (idMin <= id) && 
				 (id < idMax) )   // <-- Note: '<', not '<=' 
				{
				return sdte;
				}
            }
		return null;
		}
	
	//	 added Method to convert MMAX XML back to text
	// (otherwise inconsistencies between annotated data and prediction corpus
	private static String xml2Text(String s) {
		
		//int i = s.indexOf("&lt;");
		
		String t = s.replace("&lt;", "<");
		t = t.replace("&gt;", ">");
		t = t.replace("&amp;", "&");
		t = t.replace("&quot;", "\"");
		t = t.replace("&apos;", "'");
		
		return t;
		
	}
	


	// Method 'lSD_addBasedata()':
	//
	// Insert words (tokens) from basedata.xml into the LinkedList<SDTE>
	//  'lSD'.  The list lSD contains one entry for each annotated 
	//  sentence.  This insertion operation splits the words from 
	//  'basedata.xml' into sentences. 
	//
	// Uses existing contents of list lSD, as read from alsession.txt file,
	//  to direct each input word from basedata.xml into the right sentence.
	//
	// If bIgnoreDET == true, then ignore the last word in 'basedata.xml'
	//  if it could not be lodged inside any sentence.  
	//  (This last token was added to basedata.xml when creating the
	//  MMAX-session as a dummy word, to make the style.XSL work.)
	//
	// Read directly from basedata.xml file using code from Christina
	//  = workaround for the fact that MMAX2 writes '>' and '<' directly 
	//  as tokens into the basedata.xml file (when annotator does any
	//  basedata editing).
	//
	// Assumes that basedata.xml is sorted on word_N id, from small to
	//  large.
	//
	// Also assumes that the id number 'N' in "word_N" is an integer
	//  for all tokens in the original input corpus, and that 'N' is
	//  non-integer for the tokens that have been added by the annotator
	//  in MMAX2, through base data editing.
	//
	// This method also sets the field 'pos' in each token (= AnnToken
	//  instance) inserted in 'lSD'.  For tokens with integer 'N' in 
	//  word_N, their POS is taken from the list 'lPOS' in the lSD entry 
	//  for the sentence.  (The 'lPOS' list for each sentence in lSD was 
	//  filled in by function 'lSD_readPOS()', which must have been 
	//  called before calling function 'lSD_addBasedata()' !!).  For 
	//  tokens with non-integer 'N', their POS is set to the string value 
	//  "NN". 
	//  
	private static String
	lSD_addBasedata(
		LinkedList<SDTE> lSD,  //IN+OUT (lAT filled in in each element)
		String basedatapath,   //IN
		boolean bIgnoreDET )   //IN (Ignore dummy end token)
		{
		// Implementation note:
		// Collects any words outside the sentences from lSD into a separate
		//  LinkedList<AnnToken> lAT_orphan.
		// After reading the whole basedata file, we check if lAT_orphan
		//  contains any items other than the very last token in 'basedata.xml'.
		//  (This last token was added to basedata.xml when creating the
		//  MMAX-session as a dummy word, to make the style.XSL work.)
		
		LinkedList<AnnToken> lAT_orphan = new LinkedList<AnnToken>();
		double idLast = -1.0;

		String tidPrev = ""; //To detect the 1st token in a new sentence
		int sidPrev = -1;
		int iTokIS = 0;      //Index of integer-id token in sentence

		BufferedReader br = null;
		try
			{
			br = new BufferedReader( new FileReader( basedatapath ) );
            String wordnumber;
            String word;
            final int numberstart=15;
            int numberend;
            br.readLine();
            br.readLine();
            br.readLine();
            String curLine = br.readLine();
            
            while (curLine != null && !(curLine.startsWith("</words>"))) 
				{
                numberend = curLine.indexOf('\"', numberstart);
                wordnumber = curLine.substring(numberstart, numberend);
                word = curLine.substring(numberend+2, curLine.indexOf("</word"));
                word = xml2Text(word);
				//DEBUG OUT( "read [" + wordnumber + "] [" + word + "]" );


				//Convert 'wordnumber' to a double
				double id;
				try { id = Double.parseDouble( wordnumber ); }
				catch ( Exception e )
					{ throw new Exception( "Fail parse wordnumber '" + 
						wordnumber + "'" ); }


				//Check whether 'id' has exact integer value
//String pos = "NN";
				boolean bIsInt = false;
				int tmpInt = (int)id;
				if ( (double)tmpInt == id ) { bIsInt = true; }//pos = "X"; }



				//Create new 'AnnToken' item for the word
				AnnToken anntoken = new AnnToken( 
					id, 
					word,
					"NN" ); //Default POS value


				//Add word to sentence in lSD list
				SDTE sdte = lSD_getSentence( lSD, id );
				if ( sdte == null )
					{ 
					//DEBUG OUT( "Warning: Fail to lodge wordnumber '" + 
					//DEBUG 	wordnumber + "' into any sentence" ); 
					lAT_orphan.add( anntoken );
					}
				else
					{
					if ( (sdte.textID != tidPrev) || 
					     (sdte.sentID != sidPrev) )
						{
						iTokIS = 0;
						}
					
					if ( bIsInt ) 
						{ //Get POS tag from 'lPOS' list as filled by
						  // function 'lSD_readPOS()'
						if ( !( iTokIS < sdte.lPOS.size() ) )
							{ throw new Exception( "Token int-valued id " +
								"beyond input POS data" ); }
				
						S2 s2ret = sdte.lPOS.get( iTokIS );
						//Don't check that s2ret.sToken == word, because
						// of the '<' bug in MMAX2.
						anntoken.pos = s2ret.sTag;
						}

					sdte.lAT.add( anntoken );

					sidPrev = sdte.sentID;
					tidPrev = sdte.textID;
					if ( bIsInt ) { iTokIS++; }
					}
	

				idLast = id;
		

                curLine = br.readLine();
				}
			}
		catch ( Exception e )
			{
			return "Fail read basedata: " + e.getMessage();
			}
		finally
			{
			if ( br != null ) 
				{ 
				try { br.close(); } catch ( Exception e ) {}
				}
			}


		//DEBUG OUT( "idLast = " + idLast );

		//Check the 'lAT_orphan' list
		Iterator<AnnToken> i = lAT_orphan.iterator();
        while ( i.hasNext() )
            {
			AnnToken at = i.next();
			if ( ! ( bIgnoreDET && (at.id == idLast) ) )
				{
				return "Fail to lodge word id '" + at.id + "' into any " +
					"sentence"; 
				}
			}


		return null; //ok
		}






	//--------------------------------------------------------------------------
	// Interpret the contents of the Schemes file
	//
	// Get the "annotation levels" (= tag types) into a "map", plus get
	// for each tag type its default value.



	//Get into HashMap<String,String> the relevant Schemes data.
	//Each entry in HashMap:
	//    key = tag name
	//    val = the default value (= the 1st value from Schemes)
	// 
	//Note: Convert both key and val to lower-case.  Because MMAX2
	// converts the tag-names and -values to lower-case in the
	// markables.xml file (to which the data from the Schemes file is 
	// compared).
	//
	//Return errmess on error, null on success.
	//
	private static String
	mapTT_make(
		HashMap<String,String> mOut,                     //OUT
		LinkedList<XMLReader1.XMLTAGVAL> lTVScheme_in,   //IN
		boolean _v ) 
		{
		mOut.clear(); //init

		//Top XML element = "<annotationscheme>"
		if ( lTVScheme_in.size() != 1 )
			{ return "Top nestinglevel in scheme must have 1 element"; }

		XMLReader1.XMLTAGVAL tvIn0 = lTVScheme_in.getFirst();
		LinkedList<XMLReader1.XMLTAGVAL> lTagNames = tvIn0.lSub;

        Iterator<XMLReader1.XMLTAGVAL> iTN = lTagNames.iterator();
        while ( iTN.hasNext() )
            {
            XMLReader1.XMLTAGVAL tvName = iTN.next();

			String tagname = XMLReader1.lAttr_getValueByName( 
				tvName.lAttr, "name" );
			if ( tagname == null )
				{ return "No element 'name' in '<attribute...>' in scheme"; }

			tagname = tagname.toLowerCase();
			if(_v) { OUT( " .  " + tagname ); }


			if ( tvName.lSub == null )
				{ return "No sublist at d=1 in scheme"; }

			if ( tvName.lSub.size() < 1 )
				{ return "Tagname '" + tagname + "' has no values"; }

			//Get the 1st tag value
			XMLReader1.XMLTAGVAL tvVal0 = tvName.lSub.getFirst();
			String tagval0 = XMLReader1.lAttr_getValueByName( 
				tvVal0.lAttr, "name" );
			if ( tagval0 == null )
				{ return "No element 'name' in '<value...>' of " + tagname; }

			tagval0 = tagval0.toLowerCase();
			if(_v) { OUT( " . .  " + tagval0 ); }

			//Add new item to output map
			mOut.put( tagname, tagval0 );
			}

		return null; //success
		}


	private static void
	mapTT_dump(
		HashMap<String,String> mTT )
		{
        Iterator<Map.Entry<String,String>> i = mTT.entrySet().iterator();
		while ( i.hasNext() )
			{
			Map.Entry<String,String> e = i.next();
			OUT( " " + e.getKey() + " " + e.getValue() );
			}
		}




	//--------------------------------------------------------------------------
	// Add the annotations from the the Markables file to the
	// words in the sentences in 'lSD'



	//Subroutine to 'addAnnotations()'.
	//Read the Markables item for one 'span' = one item
	// "<markable ... />" from the markables.xml file.
	//Deliver as output into ovSpam the value of the 'span' attribute.
	// ovSpan.value is guaranteed to be set to non-null value.
	//For each of the tags in the span that have non-default value,
	// create one and exactly one Tag object.
	//Deliver as output into list 'lTags' the tags for the span
	// that have non-default value.  This output list may be empty.
	//
	//If bIgnoreDefault == true, then ignore all tags in the markables.xml
	// span that have default value (= 1st value from Schemes file).
	//If strIgnoreTagvalue != null, then ignore all tags in the
	// markables.xml span that have value 'strIgnoreTagvalue'.
	//
	//If bOutAddType == true, then use as tagname in the output:
	// "attr_value", where 'attr' = value of 'name' attribute in XML 
	// <attribute ...> tag; and where 'value' value of XML <value .../> tag
	//
	private static String
	getSpan(
		HashMap<String,String> mTT,   //IN
		XMLReader1.XMLTAGVAL tvMrk,   //IN (the input <markable...> item)
		boolean bIgnoreDefault,       //IN
		String strIgnoreTagvalue,     //IN
		boolean bOutAddType,          //IN
		OV<String> ovSpan,            //OUT
		LinkedList<Tag> lTags,        //OUT 
		boolean _v ) 
		{
		ovSpan.value = null; //init
		lTags.clear();

		String strSpan = null;

		if(_v) { OUT( " Start markable" ); }

		Iterator<XMLReader1.NV> iNV = tvMrk.lAttr.iterator();
		while ( iNV.hasNext() )
			{
			XMLReader1.NV nv = iNV.next();

			//DEBUG OUT( " .  nv = " + nv.name + " " + nv.value );

			//NV-pairs with name 'id' and 'span' are special ...
			if ( nv.name.equals( "id" ) )
				{ //ignore
				}
			else
			if ( nv.name.equals( "span" ) )
				{ 
				assert( nv.value != null );
				if(_v) { OUT( " . span [" + nv.value + "]" ); }
				strSpan = nv.value;
				}
			else
				{ //It's a tag name+value

				//Check in map 'mTT' if it has other than default value

				String dfltval = mTT.get( nv.name );
				if ( dfltval == null )
					{ return "Unknown tagname '" + nv.name + "'"; }

				boolean bIgnore = false;
				if ( bIgnoreDefault && 
				     nv.value.equals( dfltval ) )
					{ 
					bIgnore = true; 
					}
				if ( (strIgnoreTagvalue != null) &&
				     nv.value.equals( strIgnoreTagvalue ) )
					{ 
					bIgnore = true; 
					}
			
				if ( ! bIgnore )
					{
					// Use the tag string value only the 'tagvalue'.
					// NB: This is the code line to change if something
					//     different is wanted in the tag string value.
					String sTag;
					if ( bOutAddType )
						{ sTag = nv.name + "_" + nv.value; }
					else
						{ sTag = nv.value; }
					if(_v) { OUT( " . tag = " + sTag ); }

					//Create new Tag object
					Tag tagNew = new Tag( sTag );

					//Add tag to output list
					lTags.add( tagNew );
					}
				}
			}

		if ( strSpan == null )
			{ return "No item 'span' in '<markable...>'"; }

		ovSpan.value = strSpan;
		return null; //success
		}


	//Subroutine to 'applySpan_string()'.
	//For each word in idBegin...idEnd, add all tags in 'lTags_in' to
	// the word.
	//Checks if any of the ranges hits words in multiple sentences;
	// if so, then return an error message.
	//Return null on success.
	private static String
	applySpan_dblRange( 
		double idBegin,
		double idEnd,
		LinkedList<Tag> lTags_in,    //IN
		LinkedList<SDTE> lSD,        //IN+OUT (lAT.lTag filled in)
		boolean _v )
		{
		//Iterate over the sentences
		Iterator<SDTE> i = lSD.iterator();
		while ( i.hasNext() )
			{
			SDTE sdte = i.next();


			//Check that if one of idBegin and idEnd falls inside the
			// sentence, that then the other does so too.
			//
			//Sentence with words firstword=id0...lastword=idN includes words 
			// with 'id' in [id0, idN+1)
			double idMin = (double)( sdte.iWord0 );
			double idMax = (double)( sdte.iWord0 + sdte.nWords + 1.0 );

			boolean bHasBegin = ( (idMin <= idBegin) && (idBegin < idMax) );
			boolean bHasEnd   = ( (idMin <= idEnd)   && (idEnd < idMax) );

			if ( bHasBegin != bHasEnd )
				{ return "idBegin " + idBegin + "and idEnd " + idEnd +
					"not in same sentence"; }



			//Iterate over the words in the sentence, for each word check
			// if the word is in [idBegin,idEnd], and if so add to the word
			// all the tags in 'lTags'.
			Iterator<AnnToken> iAT = sdte.lAT.iterator();
			while ( iAT.hasNext() )
				{
				AnnToken at = iAT.next();

				if ( (idBegin <= at.id) && (at.id <= idEnd) )
					{
					Iterator<Tag> iTag_in = lTags_in.iterator();
					while ( iTag_in.hasNext() )
						{
						Tag tag_in = iTag_in.next();
						at.lTag.add( tag_in );
						}
					}
				}
			}

		return null;
		}


	//Subroutine to 'addAnnotations()'.
	//Interpret and span definition string 'strSpan' from XML file
	// and apply the span to the words in the sentences in 'lSD'.
	private static String
	applySpan_string( 
		String strSpan,            //IN
		LinkedList<Tag> lTags,     //IN
		LinkedList<SDTE> lSD,      //IN+OUT (lAT.lTag filled in)
		boolean _v )
		{
		//Split it on ','
		String[] asMain = strSpan.split( "," );
		if ( asMain.length < 1 ) { return "Empty span spec"; }

		//Split each substring on '..'
		int i;
		for ( i = 0; i < asMain.length; i++ )
			{
			String sMain = asMain[i];
			if(_v) { OUT( " . intrSpan: " + sMain ); }
			String[] asSub = sMain.split( "\\.\\." );
			//                             ^^^^^^  
			// regex = "\.\." , need extra '\' to put in Java string literal.

			//The result of the sMain.split() must be either:
			//  - one substring (in case there is no ".." in sMain), or
			//  - two substrings (in case there is ".." in SMain).
			//It's an error if sMain.split() returns fewer or more substrings.
			if ( asSub.length < 1 ) { return "Empty elem in span spec"; }
			if ( asSub.length > 2 ) { return "Wrong span spec elem"; }

			double[] aId = new double[2];
			aId[0] = -1.0;  //idBegin
			aId[1] = -1.0;  //idEnd

			int k;
			for ( k = 0; k < asSub.length; k++ )
				{
				if ( ! asSub[k].startsWith( "word_" ) )
					{ return "Missing 'word_' in span spec"; }

				try { aId[k] = Double.parseDouble( asSub[k].substring( 5 ) ); }
				catch ( Exception e )
					{ return "Could not interpret word_N value"; }
				if ( aId[k] < 0.0 ) 
					{ return "word_N value must not be < 0"; }
				}

			if ( asSub.length == 1 )
				{
				aId[1] = aId[0];  //idEnd = idBegin
				}

			assert( aId[0] >= 0.0 );
			assert( aId[1] >= 0.0 );

			if(_v) { OUT( " . . range = " + aId[0] + " - " + aId[1] ); }

			//Apply the tags 'lTags' to the word range aId[0]...aId[1]
			String erm = applySpan_dblRange( 
				aId[0],   //idBegin
				aId[1],   //idEnd
				lTags,
				lSD,
				_v );
			if ( erm != null ) { return erm; }

			}//for(i in asMain[])

		return null; //OK
		}

	

	//Return errmess on error, null on success.
	private static String
	addAnnotations( 
		HashMap<String,String> mTT,                     //IN
		LinkedList<XMLReader1.XMLTAGVAL> lTVMrkbl_in,   //IN
		LinkedList<SDTE> lSD,      //IN+OUT (lAT.lTag filled in)
		boolean bIgnoreDefault,       //IN
		String strIgnoreTagvalue,     //IN
		boolean bOutAddType,          //IN
		boolean _v ) 
		{
		//Top XML element = "<markables>"
		if ( lTVMrkbl_in.size() != 1 )
			{ return "Top nestinglevel in markables must have 1 element"; }

		XMLReader1.XMLTAGVAL tvIn0 = lTVMrkbl_in.getFirst();
		LinkedList<XMLReader1.XMLTAGVAL> lMrk = tvIn0.lSub;

        Iterator<XMLReader1.XMLTAGVAL> iM = lMrk.iterator();
        while ( iM.hasNext() )
            {
            XMLReader1.XMLTAGVAL tvMrk = iM.next();

			String erm = null;

			//
			// Get the span and tag data from the "<markable .../>" XML entry
			//
			OV<String> ovSpan = new OV<String>( null );
			LinkedList<Tag> lTags = new LinkedList<Tag>();
			erm = getSpan( mTT, tvMrk, 
				bIgnoreDefault, strIgnoreTagvalue, bOutAddType,
				ovSpan, lTags, _v );
			if ( erm != null )
				{ return erm; }
			String strSpan = ovSpan.value;


			//
			// Apply the tag(s) over the span to the words in 'lSD'
			//
			erm = applySpan_string( strSpan, lTags, lSD, _v );
			if ( erm != null )
				{ return "Wrong span value '" + strSpan + "': " + erm; }

			}

		return null; //success
		}



	//--------------------------------------------------------------------------
	// Remove tags from each sentence in 'lSD' until 
	// each token only has at most one tag left.


	// 1) Funcs operating on one sentence = one 'LinkedList<AnnToken> lAT'
	//    list object.


	//Subroutine to 'lAT_nWordsWithTag()'  
	//[NB: Use HashSet for 'lTag' member in class AnnToken]
	private static boolean
	lTag_contains(
		LinkedList<Tag> lTag,    //IN
		Tag tag_in )             //IN (reference)
		{
		Iterator<Tag> i = lTag.iterator();
		while ( i.hasNext() )
			{
			Tag t = i.next();
			if ( t.s.equalsIgnoreCase(tag_in.s) ) { return true; } //compare references!
			}
		return false;
		}
	

	//Return the number of words in the sentence which contains
	// in its lTag list the reference value 'tag_in'.
	private static int
	lAT_nWordsWithTag(
		LinkedList<AnnToken> lAT,   //IN
		Tag tag_in )                //IN (reference)
		{
		int nWords = 0;
		Iterator<AnnToken> iTok = lAT.iterator();
		while ( iTok.hasNext() )
			{
			AnnToken at = iTok.next();
			if ( lTag_contains( at.lTag, tag_in ) ) { nWords++; }
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
		
		if(found) {
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

	/*
	//Remove the tag 'tag_in' from all words in the sentence in/on which
	// the tag occurs.
	private static void
	lAT_removeTag( 
		LinkedList<AnnToken> lAT,   //IN
		Tag tag_in )                //IN (reference)
		{
		Iterator<AnnToken> iTok = lAT.iterator();
		while ( iTok.hasNext() )
			{
			AnnToken at = iTok.next();
			at.lTag.remove( tag_in );
			}
		}
    */
	
	private static void lAT_removeTag(AnnToken at, LinkedList<AnnToken> lAT, // IN
			Tag tag_in) // IN (reference)
	{
		//Iterator<AnnToken> iTok = lAT.iterator();
		//while (iTok.hasNext()) {
			//AnnToken at = iTok.next();
			at.lTag.remove(tag_in);
		//}
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

			//Comment out Mennos Code: it destroys longer tag sequences 
			//if some internal tag has higher priority (08/06)
			/*
			if (iPrio1 != iPrio2) {
				// Remove the lower-priority tag
				tRem = ((iPrio1 > iPrio2) ? t1 : t2);
				OUT(". Remove lower-prio tag = " + tRem.s);
			} else // Both tags have same priority
			{
			*/
			
			//Look at the # of words annotated by the two tags
			int len1 = lAT_nContextWordsWithTag( lAT, t1, wordIndex );
			System.out.println(t1.s + " with " + len1 + " words");
			assert( len1 > 0 );
			int len2 = lAT_nContextWordsWithTag( lAT, t2, wordIndex );
			assert( len2 > 0 );
			System.out.println(t2.s + " with " + len2 + " words");
			if ( len1 != len2 )
				{
				//Remove the tag with fewer #words
				tRem = ( (len1 < len2 ) ? t1 : t2 );
				OUT( ". Remove tag on fewer words = " + tRem.s );
				}
			else if  ( iPrio1 != iPrio2 ) //Diff priority AND same length
			    {
				tRem = ( (iPrio1 > iPrio2) ? t1 : t2 );
				OUT( ". Remove lower-prio tag = " + tRem.s );
			    }

			else {
				//Remove arbitrarily one of both; take the 1st one.
				tRem = t1;
				OUT( ". Tags same; remove arbitrary one = " + tRem.s );
				}
			
			//}

			assert (tRem != null);

			// Remove the reference to object tRem from all words in the
			// sentence
			lAT_removeTag(atClash, lAT, tRem);
			wordIndex = 0;
		}// for(;;)
	}


	// 2) Funcs that apply the sentence operations to all sentences
	//    in the list 'lSD'

	private static String
	lSD_remDoubles(
		LinkedList<SDTE> lSD,       //IN+OUT
		LinkedList<String> lPrio,   //IN
		boolean _log,
		boolean _v )
		{
        Iterator<SDTE> i = lSD.iterator();
        while ( i.hasNext() )
            {
            SDTE sdte = i.next();
	
			String erm = lAT_remDoubles( sdte.lAT, lPrio,
				sdte.textID, sdte.sentID, _log, _v );
			if ( erm != null ) { return erm; }
			}

		return null;
		}




	//--------------------------------------------------------------------------
    // Reading of file with POS data


    private static class S2
        {
        public String sToken;
        public String sTag;
        
        public S2( String sTok0, String sTag0 ) 
            { sToken = sTok0; sTag = sTag0; }
        }
    


    //Input string = sequence of items "token_POS" with white-space
    // between the items.
	//The separator char '_' between token and POS is configurable,
	// by means of the internal final char SEPCHAR.
    //Token may also contain characters equal to SEPCHAR, but we assume that 
	// POS does not contain any chars equal to SEPCHAR character.  I.e. the 
	// LAST char equalling SEPCHAR in each item is considered as the separator.
    //Convert input string this into a LinkedList<S2>.
    private static String
    convertTokenPOSItems(
        String sIn,             //IN
        LinkedList<S2> lOut )   //OUT
        {
        final char SEPCHAR = '_';
		
        lOut.clear(); //init

        //Split input line into tokens on WS
        String as[] = sIn.split( "[ \t]+" );
        if ( as.length == 0 ) 
            { return "Line without tokens"; }

        //Run through as[], append each item to sbOut with last SEPCHAR
        // replaced by space
        int iItem;
        for ( iItem = 0; iItem < as.length; iItem++ )
            {
            String sItem = as[iItem];
            assert( sItem.length() > 0 );
            //DEBUG OUT( sItem );

            //Seek the 1st SEPCHAR from the end in 'sItem'
            int kSlash = -1;
            int k; 
            for ( k = sItem.length() - 1; k >= 0; k-- )
                {
                if ( sItem.charAt(k) == SEPCHAR )
                    { kSlash = k; break; }
                }
            if ( ! ( (1 <= kSlash) && (kSlash < sItem.length() - 1) ) )
                { return "Couldn't interpret SEPCHAR ('" + SEPCHAR + "')" +
                    " in item '" + sItem + "'"; }

            String sToken = sItem.substring( 0, kSlash );
            String sTag   = sItem.substring( kSlash+1, sItem.length() );
            S2 s2New = new S2( sToken, sTag );
            lOut.add( s2New );
            }
        //DEBUG OUT( "" );

        return null;
        }



    // Read the POS-tag data from sentence 'sentenceID' in file 'textID'
    //  in directory 'fPosDir'.
    // Overwrite output list lOut with the data (tokens + tags) from that
    //  line in the file.
    // Return null on success, errmess on error (such as when file
    //  textID doesn't contain sentenceID).
    private static String
    readPosCorpFile(
        LinkedList<S2> lOut,  //OUT
        File fPosDir,             //IN
        String textID,            //IN
        int sentenceID )          //IN
        {
        lOut.clear(); //init

        String infilepath = fPosDir.getAbsolutePath() + File.separator + textID;

        boolean bSeenSentence = false;
        BufferedReader br = null;
        int iLine = 0;
        try
            {
            br = new BufferedReader( new FileReader( infilepath ) );

            String sIn;
            while( ( sIn = br.readLine() ) != null )
                {
                if ( sIn.length() == 0 ) 
                    { throw new IOException( "Empty line" ); }

                if ( iLine == sentenceID )
                    {
                    String erm = convertTokenPOSItems( sIn, lOut );
                    if ( erm != null ) { throw new IOException( erm ); }
                    bSeenSentence = true;
                    break; //done
                    }

                iLine++;
                }
            }
        catch ( IOException e )
            {
            return "Fail read file '" + infilepath + "' (line " + 
                (iLine+1) + ") : " + e.getMessage();
            }
        finally
            {
            if ( br != null ) 
                { 
                try { br.close(); } catch ( IOException e ) {}
                }
            }

        if ( ! bSeenSentence )
            {
            return "File '" + infilepath + "' does not contain sentence " +
                sentenceID;
            }

        return null; //success
        }



	// For each sentence in list 'lSD', read the POS data for the tokens
	//  in the sentence (= the sentence as in the input corpus) as from file
	//  fPosDir/textID.
    private static String
    lSD_readPOS(
        LinkedList<SDTE> lSD,  //IN+OUT (lAT filled in in each element)
		File fPosDir )         //IN (Dir with the POS-tagged infiles)
		{
        Iterator<SDTE> i = lSD.iterator();
        while ( i.hasNext() )
            {
            SDTE sdte = i.next();

			String erm = readPosCorpFile( 
				sdte.lPOS,
				fPosDir,
				sdte.textID,
				sdte.sentID );
			if ( erm != null ) { return erm; }
            }
        return null;
		}




	//--------------------------------------------------------------------------
	// Definition of public method 'convertSessionToT2()'

	// Returns null on success, errmess on error.
	//
	// Parameters:
	// 1) Input files (read-only)
	//		alsession_txt_file 
	//		scheme_file
	//		priolist_txt_file 
	//		posdirpath
	//		basedata_file
	//		markables_level_file
	// 2) Output
	//		pwOut = PrintWriter object to write output '*.t2' contents to.
	// 3) Debug options
	//		_v       Verbose (dump debug info) for global processing
	//		_vI      Verbose (dump debug info) for internals
	//		_vXML    Dump contents of XML files as read
	// 4) Conversion settings 
	//		bWithBI           Print B-/I- before nonempty MMAX2-tags
	//      bIgnoreDefault    Ignore the default tagvalue (=1st in scheme)
	//      strIgnoreTagvalue Tagvalue (e.g. "Unspecified") to ignore
	//		bOutAddType       Prepend "tagtype_" before output tagvalues
	//
    // USAGE NOTE: For most PrintWriter implementations, the caller should
    //  call PrintWriter.close() after the call to 'convertSessionToT2()', 
	//  to cause the output to be actually written (e.g. to file).
	//
	public static String convertSessionToT2(
		String alsessionpath,     //IN
		String schemepath,        //IN
		String priopath,          //IN
		String posdirpath,        //IN
		String basedatapath,      //IN
		String markablespath,     //IN

		PrintWriter pwOut,        //OUT

		boolean bWithBI,
		boolean bIgnoreDefault,
		String strIgnoreTagvalue,
		boolean bOutAddType,

		boolean _v,  //Global processing
		boolean _vI, //Internals
		boolean _vXML )
		{
		String erm = null;


		File fPosDir = new File( posdirpath );
        if ( ! fPosDir.isDirectory() ) {
			return "'" + posdirpath + "' is not a directory";
        }



		//
		// Read alsession.txt file
		//
		if(_v) {
			OUT( "===Reading alsession.txt ..." );
		}
		LinkedList<SDTE> lSD = new LinkedList<SDTE>();
		erm = sdtable_read( lSD, alsessionpath );
		if ( erm != null ) {
			return "Fail read '" + alsessionpath + "' : " + erm;
		}

		if(_v) { 
			OUT( "===lSD after reading alsession.txt :" );
		    OUT( "lSD.len = " + lSD.size() );
		    lSD_dump( lSD );
		}
		


		//
		// Read POS tags from posdir/* files into field
		//  'lPOS' in each sentence in LSD
		//
		erm = lSD_readPOS( lSD, fPosDir );
		if ( erm != null ) {
			return "Fail read POS information: " + erm;
		}



		//
		// Read basedata.xml file, add its data into 'lSD'
		//

		if(_v) {
			OUT( "===Reading basedata ..." ); 
		}
		erm = lSD_addBasedata( 
			lSD, 
			basedatapath,
			true ); //ignore last dummy word
		if ( erm != null ) {
			return "Fail read '" + basedatapath + "' : " + erm;
		}

		if(_vI) {
			OUT( "===lSD after adding basedata:" );
			lSD_dump( lSD ); 
		}



/*
Version for reading basedata.xml that is maybe preferred if the bug 
in MMAX2 is repaired that MMAX2 writes '<' into basedata.xml :

		LinkedList<XMLReader1.XMLTAGVAL> lBasedata = 
			new LinkedList<XMLReader1.XMLTAGVAL>();
		erm = XMLReader1.readXMLFile( G_defBasedata, basedatapath, 
			lBasedata, _vI );
		if ( erm != null )
			{
			return "Fail read '" + basedatapath + "' : " + erm;
			}
		XMLReader1.lTV_dump( lBasedata, 0 );
*/

		//
		// Read Schemes/*.xml file
		//

		if(_v) { 
			OUT( "===Reading schemes file ..." );
		}
		LinkedList<XMLReader1.XMLTAGVAL> lScheme = 
			new LinkedList<XMLReader1.XMLTAGVAL>();
		erm = XMLReader1.readXMLFile( G_defNamedEntityScheme, schemepath, 
			lScheme, false );
		if ( erm != null ) {
			return "Fail read '" + schemepath + "' : " + erm;
		}
		if(_vXML) { 
			OUT( "===Scheme:" );
			XMLReader1.lTV_dump( lScheme, 0 );
		}


/* This code could maybe be used if the '>' bug in MMAX2 is solved.
		LinkedList<TagType> lTT = new LinkedList<TagType>();
		erm = lTT_make( lTT, lScheme, false );
		if ( erm != null )
			{
			return "Fail make tagtype-list from scheme data: " + erm;
			}
		if(_v) { OUT( "===lTT:" );
		         lTT_dump( lTT ); }
*/

		HashMap<String,String> mTT = new HashMap<String,String>();
		erm = mapTT_make( mTT, lScheme, false );
		if ( erm != null ) {
			return "Fail make tagtype-map from scheme data: " + erm;
		}
		if(_vI) {
			OUT( "===mTT:" );
			mapTT_dump( mTT );
		}

		//
		// Read 'priolist.txt'
		//
	
		LinkedList<String> lPrio = new LinkedList<String>();
		erm = priolist_read( lPrio, priopath );
		if ( erm != null ) {
			return "Fail read '" + priopath + "' : " + erm;
		}
		if(_v) { 
			OUT( "===priolist:" ); 
		    priolist_dump( lPrio );
		}

		//
		// Read Markables/*.xml file
		//

		if(_v) {
			OUT( "===Reading markables file ..." );
		}
		LinkedList<XMLReader1.XMLTAGVAL> lMarkables = 
			new LinkedList<XMLReader1.XMLTAGVAL>();
		erm = XMLReader1.readXMLFile( G_defMarkables, markablespath, 
			lMarkables, false );
		if ( erm != null ) {
			return "Fail read '" + markablespath + "' : " + erm;
		}
		if(_vXML) {
			OUT( "===Markables:" );
			XMLReader1.lTV_dump( lMarkables, 0 );
		}


		// Add all tags from markables file into 'lSD'

		erm = addAnnotations( mTT, lMarkables, lSD, 
			bIgnoreDefault, strIgnoreTagvalue, bOutAddType, _vI );
		if ( erm != null ) {
			return "Fail addAnnotations: " + erm;
		}

		if(_vI) { 
			OUT( "===lSD after adding markables data:" );
			lSD_dump( lSD );
		}


		// Remove multiple tags on the same token

		if(_v) {
			OUT( "===Running remDoubles ..." );
		}
		erm = lSD_remDoubles( lSD, lPrio, true, _vI );
		if ( erm != null ) {
			return "Fail remDoubles: " + erm;
		}

		if(_vI) {
			OUT( "===lSD after remDoubles:" );
			lSD_dump( lSD );
		}


		//
		// Print final output to PrintWriter
		//

		//lSD_printAIOB( lSD, pwOut );
		lSD_printT2( lSD, bWithBI, pwOut );


		if(_v) {
			OUT( "===Output written" );
		}

		return null; //success
	}

	//--------------------------------------------------------------------------
	// MAIN()


	private static void usage() {
		ERR( "Usage: Mmax2AL2 " +
			"alsession_txt_file " +     // 0
			"scheme_file " +            // 2
			"priolist_txt_file " +      // 3
			"posdirpath " +             // 1
			"basedata_file " +          // 4
			"markables_level_file " +   // 5
			"output_t2_file " +         // 6
			"[options]" );
		ERR( "Options are: \n" +
			"   -v     Verbose (dump debug info)\n" +
			"   -x     Dump contents of XML files as read\n" +
			"   -b     Print B-/I- before nonempty MMAX2-tags\n" + 
			"   -u     Ignore tagvalue \"Unspecified\" (default: Ignore the " +
			             "default tagvalue\n" + 
			"   -t     Prepend \"tagtype_\" before output tagvalues" );
		}

	public static void main( String[] args ) {	
		final int N_REQ_ARGS = 7;
		if ( args.length < N_REQ_ARGS ) { 
			usage(); System.exit(-1);
		}

		String alsessionpath = args[0];
		String schemepath    = args[1];
		String priopath      = args[2];
		String posdirpath    = args[3];
		String basedatapath  = args[4];
		String markablespath = args[5];
		String outfilepath   = args[6];

		File fPosDir = new File( posdirpath );
        if ( ! fPosDir.isDirectory() )
            {
            ERR( "'" + posdirpath + "' is not a directory" );
            System.exit( -1 );
            }


		//Default option values
		boolean _v = false;
		boolean _vXML = false; //Dump details of XML data as read
		boolean bIgnoreDefault   = false; //false;
		String strIgnoreTagvalue = null; //"Unspecified";
		boolean bOutAddType = false;  //Take value 'true' for testing
		boolean bWithBI = false; 

		//Options
		int iOpt;
		for ( iOpt = N_REQ_ARGS; iOpt < args.length; iOpt++ ) {
			String sOpt = args[iOpt];
			if ( sOpt.length() < 2 ) { usage(); System.exit(-1); }
			switch ( sOpt.charAt(1) )
				{
			case 'v': _v = true;       break;
			case 'x': _vXML = true;    break;
			case 'b': bWithBI = true;  break;
			case 'u':
				bIgnoreDefault = false;
				strIgnoreTagvalue = "Unspecified";
			break;
			case 't': bOutAddType = true; break;
			default: usage(); System.exit(-1); break;
				}
			}
		

		OUT( "alsession = " + alsessionpath );
		OUT( "scheme    = " + schemepath );
		OUT( "prio      = " + priopath );
		OUT( "posdir    = " + posdirpath );
		OUT( "basedata  = " + basedatapath );
		OUT( "markables = " + markablespath );
		OUT( "outfile   = " + outfilepath );






		//Print output to file 'outfilepath'

		PrintWriter pwOut = null;
		try { pwOut = new PrintWriter( outfilepath ); }
		catch ( FileNotFoundException e )
			{ 
			ERR( "Fail open '" + outfilepath + "': " + e.getMessage() ); 
			System.exit( -1 );
			}



		String erm = null;
		erm = convertSessionToT2(
			alsessionpath,     //IN
			schemepath,        //IN
			priopath,          //IN
			posdirpath,        //IN
			basedatapath,      //IN
			markablespath,     //IN

			pwOut,        //OUT

			bWithBI,
			bIgnoreDefault,
			strIgnoreTagvalue,
			bOutAddType,

			true,  //Global processing
			_v,    //Internals
			_vXML );

		if ( erm != null )
			{ 
			ERR( "Fail convertSessionToT2: " + erm );
			System.exit( -1 );
			}


		pwOut.close();
		OUT( "===Output file '" + outfilepath + "' written" );

		System.exit(0);
		}



	}


