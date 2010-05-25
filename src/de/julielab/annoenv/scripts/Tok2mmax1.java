package de.julielab.annoenv.scripts;


//Tok2mmax.java    MR Mar '06
//
// Code by Menno Rubin
//
//
// Defines method Tok2mmax1.tok2mmax().
//
// Create MMAX2 Basedata/*.xml file, plus optionally also an
// MMAX2 Markables/*_sentence_level.xml file,
// from an input file that contains the tokenized input in following 
// format: WS between all tokens, and '\n' between all sentences.
//


import java.io.*;

//----------------------------------------------------------------------------

public
class Tok2mmax1
	{

	//-------------------------------------------------------------------------
	// INNER HELPER CLASSES
	//-------------------------------------------------------------------------
	
	private static
	class CharGetter
		{
		static final int EOF = -1; //Ret val of 'BufferedReader.read()'
	
		BufferedReader m_brIn;
		int m_iline;  //0-based
		int m_icol;   //0-based
		int m_icolPrev; 
		boolean m_bAtEOF;
		
	
	
		CharGetter( BufferedReader brIn )
			{
			m_brIn = brIn;
			m_iline = 0;
			m_icol = 0;
			m_icolPrev = -1;
			m_bAtEOF = false;
			}
	
		int getc() 
		throws IOException //[thrown by: BufferedReader.mark(), .read()]
			{
			m_icolPrev = m_icol;
	
			//Impl: We always read only separate SINGLE characters.
			m_brIn.mark( 1 ); //make ungetc possible
	
			int ic = m_brIn.read();  
			if ( ic == '\n' )
				{
				m_iline += 1; m_icol = 0;
				}
			else
				{
				if ( ic == EOF ) { m_bAtEOF = true; }
				m_icol += 1;
				}
			
			return ic;
			}
	
		void ungetc( int ic ) 
		throws IOException //[thrown by: BufferedReader.reset()]
			{
			assert( m_icolPrev != -1 ); //Only one ungetc() after getc() allowed
	
			if ( ic == '\n' )
				{
				assert( m_iline > 0 );
				m_iline -= 1; 
				}
			else
				{
				if ( ic == EOF ) { m_bAtEOF = false; }
				}
	
			m_brIn.reset();  
	
			m_icol = m_icolPrev;
			m_icolPrev = -1; //
			}
	
		int iline() { return m_iline; }
		int icol()  { return m_icol; }
		boolean atEOF() { return m_bAtEOF; }
		}
	
	
	//-------------------------------------------------------------------------
	
	private static
	class TokScanner
		{
		static boolean 
		isWS( char c )
			{
			return ( (c == ' ') ||
					(c == '\t') ||
					(c == '\r') ||
					(c == '\n') );
			}
	
	
		// stop scanning on:
		//          EOF
		//          any-non-WS
		// The char where scanning stops is pushed back to the input.
		// Return true if one or more WS chars were scanned.
		// Throw IOException on cg.getc()/ungetch() error.
		static boolean
		scanWS(
			CharGetter cg )   //IN+OUT (state changed)
			throws IOException
			{
			int iSc = 0;
		
			for(;;)
				{
				int ic;
				ic = cg.getc();
				if ( ic == CharGetter.EOF )
					{
					cg.ungetc( ic );
					return ( iSc > 0 );
					}
		
				char c = (char)ic;
				if ( ! isWS(c) )
					{
					cg.ungetc( ic );
					return ( iSc > 0 );
					}
		
				iSc++; //Eat the WS char
				}
			}
	
	
		// stop scanning on: EOF WS
		// The char where scanning stops is pushed back to the input.
		// Return false if # chars scanned = 0.
		// Throw IOException on cg.getc()/ungetch() error.
		static boolean
		scanToken(
			CharGetter cg,      //IN+OUT (state changed)
			StringBuffer buf )  //Dest buffer to write scanned token into
			throws IOException
			{
			buf.setLength( 0 ); //init
		
			int iSc = 0;
		
			for(;;)
				{
				int ic;
				ic = cg.getc();
				if ( ic == CharGetter.EOF )
					{
					cg.ungetc( ic );
					return ( iSc > 0 );
					}
		
				char c = (char)ic;
				if ( isWS(c) )
					{
					cg.ungetc( ic );
					return ( iSc > 0 );
					}
		
				buf.append( c );
				iSc++;
				}
			}
	
		}
	
	

	//-------------------------------------------------------------------------


	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
	// Write Basedata items to output

	private static void
	writeBD_prologue( PrintWriter outBD, boolean bOFMmax )
		{
		if ( outBD == null ) { return; }
		if ( bOFMmax ) 
			{
			outBD.println( "<?xml version='1.0' encoding='UTF-8'?>" );
			outBD.println( "<!DOCTYPE words SYSTEM \"words.dtd\">" );
			outBD.println( "<words>" );
			}
		}

	private static void
	writeBD_epilogue( PrintWriter outBD, boolean bOFMmax )
		{
		if ( outBD == null ) { return; }
		if ( bOFMmax ) 
			{
			outBD.println( "</words>" );
			}
		}

	
//	Print string to PrintWriter, printing every '<' char as string "&lt;"
	// and every '>' char as string "&gt;".  Purpose: Printing of literal
	// string data to XML files.
	private static void
	writeStringXML(  PrintWriter out, String s )
		{
		int i;
		for ( i = 0; i < s.length(); i++ )
			{
			char c = s.charAt(i);
			switch ( c )
				{
			case '<': out.print( "&lt;" );  break;
			case '>': out.print( "&gt;" );  break;
			case '&': out.print( "&amp;" );  break;
			case '\"': out.print( "&quot;" );  break;
			default:  out.print( c );  break;
				}
			}
		}

	
	
	
	private static void
	writeBD_word( PrintWriter outBD, boolean bOFMmax,
		int iWord,
		String sWord, 
		CharGetter cg )    //IN (used for debug output only)
		{
		if ( outBD == null ) { return; }
		if ( bOFMmax )
			{
			
			outBD.print( "<word id=\"word_" + (iWord+1) + "\">" );
			writeStringXML( outBD, sWord );
			outBD.println( "</word>" );
			/*
			outBD.println( "<word id=\"word_" + (iWord+1) + "\">" +
				sWord + "</word>" );
			*/
			}
		else
			{
			outBD.println( "tok( " + iWord + ") " +
				cg.iline() + " " +
				cg.icol() + " [" +
				sWord + "]" );
			}
		}


	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
	// Write Sentence_level items to output

	//NOTE!: Name 'sentence' in "xmlns" field value corresponds with the
	// name of the annotation "level" (for the sentences); i.e. both must 
	// be the same.
	private static void
	writeSL_prologue( PrintWriter outSL, boolean bOFMmax )
		{
		if ( outSL == null ) { return; }
		if ( bOFMmax ) 
			{
			outSL.println( "<?xml version=\"1.0\"?>" );
			outSL.println( "<!DOCTYPE markables SYSTEM \"markables.dtd\">" );
			outSL.println( 
				"<markables xmlns=\"www.eml.org/NameSpaces/sentence\">" );
			}
		}

	private static void
	writeSL_epilogue( PrintWriter outSL, boolean bOFMmax )
		{
		if ( outSL == null ) { return; }
		if ( bOFMmax ) 
			{
			outSL.println( "</markables>" );
			}
		}

	private static void
	writeSL_sentence( PrintWriter outSL, boolean bOFMmax,
		int iSentence,
		int iFirstW,     //IN: First word in sentence
		int iLastW,      //IN: Last word in sentence
		String label )   //IN (used for debug output only)
		{
		if ( outSL == null ) { return; }
		if ( bOFMmax )
			{
			outSL.println( "<markable id=\"markable_" + (iSentence+1) + 
				"\" span=\"word_" + (iFirstW+1) + 
				"..word_" + (iLastW+1) + "\"/>" );
			}
		else
			{
			outSL.println( label + " S( " + iSentence + ") " + 
				iFirstW + " " + iLastW );
			}
		}



	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 

	// Method 'tok2mmax()':
	//
	// Convert WS-separated tokenized input file (with '\n' between
	// the sentences), to files usable by MMAX2.
	//
	// Handles multiple '\n' in a section of whitespace as one '\n'.
	// Newline(s) and whitespace before the 1st token in the input are
	//  disregarded.
	//
	// Argument 'outBD'  = Where to write the output basedata to.
	// Argument 'outSL'  = Where to write the output sentence data to.
	// It is allowed that any/either of 'outBD' and 'outSL' has the
	// actual value 'null', in which case the method does not write that
	// type of output.
	//
	// Argument 'bOFMmax' = Output format for both 'outBD' and 'outSL'.
	// Values:
	//    true  = Produce MMAX xml output
	//    false = Produce debug output.
	//
	// NB: MMAX output has 1-based word index nrs, but debug output has
	// 0-based word index nrs.
	//
	// Returns 'null' on sucess.  
	// On I/O error, aborts the processing and returns an error message;
	// when non-null is returned, then the output (written outBD and/or 
	// outSL) is garbage.
	//
	// NOTE ON USAGE:  In most PrintWriter implementations (used by the 
	// caller for outBD and outSL), the caller should call '.close()' on 
	// the PrintWriter objects after the call to 'tok2mmax()', in order to 
	// cause the output to be actually written !!
	//
	public static String
	tok2mmax(
		String file,
		//InputStream in,        //IN
		boolean bOFMmax,       //IN
		PrintWriter outBD,     //OUT Basedata (= words)
		PrintWriter outSL ) throws  FileNotFoundException  //OUT Sentence Level (= sentences)
		{
		BufferedReader br
			= new BufferedReader( new InputStreamReader( new FileInputStream(file) ) );

		CharGetter cg = new CharGetter( br );
		int iSentence = 0; //0-based
		int iWord = 0; //0-based
		boolean bTokSeen = false; //Set to true after scanning the 1st token
		                          // in the FILE.  Purpose: to handle '\n'
		                          // before the 1st token in the file.

		int iFWIS = -1; //First word in sentence (0-based)


		writeBD_prologue( outBD, bOFMmax );
		writeSL_prologue( outSL, bOFMmax );

		try 
			{
			while( ! cg.atEOF() )
				{
				int iline0 = cg.iline();
				TokScanner.scanWS( cg );
				int iline1 = cg.iline();

				//Check whether the scanWS() call saw a newline; if so,
				// then print an outSL item
				if ( ( iline1 > iline0 ) && bTokSeen )
					{ 
					assert( iWord > iFWIS );
					assert( iWord > 0 );
					writeSL_sentence( outSL, bOFMmax, 
						iSentence, iFWIS, (iWord-1), "-" );
					iFWIS = -1; //reset for next line
					iSentence++;
					}

				StringBuffer buf = new StringBuffer();
	        	if ( ! TokScanner.scanToken( cg, buf ) ) { break; }

				//Write the token to basedata output
				writeBD_word( outBD, bOFMmax, iWord, buf.toString(), cg );

				if ( iFWIS == -1 ) 
					{ iFWIS = iWord; } //Remember 1st word of sentence

				iWord++;
				bTokSeen = true;

	            }//while(!atEOF)
			}
		catch ( IOException e )
			{
			return "Input error (at line " + cg.iline() + ", " +
				"col " + cg.icol() + "): " + e.getMessage();
			}

		
		//Print outSL item for last sentence (if input contains
		// no terminating '\n')
		if ( iFWIS != -1 )
			{
			writeSL_sentence( outSL, bOFMmax, 
				iSentence, iFWIS, (iWord-1), "*" );
			}
		
		
		writeBD_epilogue( outBD, bOFMmax );
		writeSL_epilogue( outSL, bOFMmax );

		return null;
		}



	
	private static void writeMarkable(String file, PrintWriter pwML) {
		
		String line = "";
		try 
		{	
			BufferedReader br
			= new BufferedReader( new InputStreamReader( new FileInputStream(file) ) );
			
			while(br.ready()) {
				line = br.readLine();
				pwML.println(line);
			}
			br.close();
		}
		catch ( IOException e )
		{
			e.getMessage();
		}
	}

	//------------------------------------------------------------------------
	// MAIN() = Test call to method 'tok2mmax()'.


	static void 
	usage()
		{
		System.err.println( 
		    "Usage:\n" +
				    "     java Tok2mmax1 <FileDir> <BasedataDir> <MarkableLevelDir> level_name entity_markable_file\n" +
		    "where:\n" +
				    "     <FileDir> = tokenized input text files (one sentence per line)\n" +
				    "     <BasedataDir> = output directory for basedata files\n" +
				    "     <MarkableLevelDir/> = output directory for sentence- and entity-level markable files (e.g. for cell_component, organism)\n" +
				    "     level_name = name of annotation level (e.g. cell_component, organism etc.)\n" +
				    "     entity_markable_file = e.g cell_component_level.xml\n" +
		    "" );
		}


	public static void main( String[] args ) throws IOException {
		if ( args.length < 5 ) { usage(); System.exit(-1); }
		
		String FileDir = args[0];
		File FileDirPath = new File(FileDir);
		FileDir = FileDirPath.getAbsolutePath();
		
		String BasedataDir = args[1];
		File BasedataDirPath = new File(BasedataDir);
		BasedataDir = BasedataDirPath.getAbsolutePath() + File.separator;
				
		String MarkableDir = args[2];
		File MarkableDirPath = new File(MarkableDir);
		MarkableDir = MarkableDirPath.getAbsolutePath();
		
		String SentenceLevelDir = MarkableDir + File.separator 
								+ "sentence" + File.separator; 
		File SentenceLevel = new File(SentenceLevelDir);
		if (!SentenceLevel.mkdir()) {
			throw new IOException("Sentence directory could not be created!");
		}
		
		String level_name = args[3];
		String MarkableLevelDir = MarkableDir + File.separator
								+ level_name + File.separator;
		File MarkableLevel = new File(MarkableLevelDir);
		if (!MarkableLevel.mkdir()) {
			throw new IOException("Markable directory could not be created!");
		}
		String entity_markable_file = args[4];
		
		//final String ofnameBD = BasedataDir + args[0] + ".xml";
		//final String ofnameSL = SentenceDir + args[0] + ".xml";

		
		
		//Settings (with default values)
		//boolean bOFMmax = false;


		PrintWriter pwBD = null;
		PrintWriter pwSL = null; 
		PrintWriter pwML = null;

		//[For stdout output, use: pwBD = new PrintWriter( System.out ); ]

		/*
		File pathname = new File(MarkableDir);
		String[] files = pathname.list();
		String file = "";
		String markableFile = "";
		for(int i = 0; i < files.length; i++) {
			file = files[i];
			markableFile = pathname.getAbsolutePath() + File.separator + file;
		}
		*/
		//Open output files
		File pathname = new File(FileDir);
		String[] files = pathname.list();
		String inputFile = "";
		
		for(String file : files) {
			//System.out.println(file);
			inputFile = pathname.getAbsolutePath() + File.separator + file;
			//System.out.println(inputFile);
			try {
				pwBD = new PrintWriter( new BufferedOutputStream( 
								new FileOutputStream( BasedataDir + file + ".xml" ) ) );
				pwSL = new PrintWriter( new BufferedOutputStream( 
								new FileOutputStream( SentenceLevelDir + file + ".xml"  ) ) );
				pwML = new PrintWriter( new BufferedOutputStream( 
								new FileOutputStream( MarkableLevelDir + file + ".xml"  ) ) );
			} catch ( IOException e ) { 
				System.err.println( "Fail open output file(s): " + 
							e.getMessage() ); System.exit( -1 );
			}

			try {
				String erm = tok2mmax(inputFile, true, pwBD, pwSL );

				writeMarkable(entity_markable_file, pwML);

				if ( erm != null ) {
					System.err.println( "Fail: " + erm );
					System.exit( -1 );
				}
			} catch (FileNotFoundException e) {
				System.err.println( "Fail open input file: " + 
								e.getMessage() );
				System.exit( -1 );	
			} finally {
				pwBD.close();
				System.out.println( BasedataDir + file + " written" );

				pwSL.close();
				System.out.println( SentenceLevelDir + file + " written" );

				pwML.close();
				System.out.println( MarkableLevelDir + file + " written" );
			}
		}

	}	
	
}	



