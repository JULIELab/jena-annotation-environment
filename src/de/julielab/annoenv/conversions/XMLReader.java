/** 
 * XMLReader.java
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
 */

package de.julielab.annoenv.conversions;



import java.io.*;
import java.util.*; //For LinkedList, HashSet



/**
 *  Used in Mmax2AL1.java.
 *  Exports as public stuff:
 *    class XMLTAGDEF           -- for client to define structure of XML file.
 *    classes XMLTAGVAL and NV  -- for output from method 'readXMLFile()'.
 *    method readXMLFile()      -- reads XML file into nested XMLTAGVAL list.
 *    method lTV_dump()         -- dump contents of readXMLFile() output list.
 *    method lAttr_getValueByName() -- read data from readXMLFile() output list.
 */
public class XMLReader
	{
	private static void OUT( String s ) { System.out.println( s ); }
//	private static void ERR( String s ) { System.err.println( s ); }



	//--------------------------------------------------------------------------
	// class CharGetter: 
	// Reading characters from BufferedReader with ungetc-operation.
	// Used in XML reader.

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



	//--------------------------------------------------------------------------
	// XML reader


	// ***********************************************
	// ** XML reader part 1: Definition of XML file **
	// ***********************************************

	// <name attr1="val1" attr2="val2 .../>
	// <name attr1="val1" attr2="val2 ...> ... </name>
	// In this implementation, the contents of an XML tag bracket
	//  is either nothing, or text, or a sequence of XML tags of ONE type.
	//  Contents is not BOTH text and XML tag(s).

	// The client code defines an object of class XMLTAGDEF to specify
	//  to us what type of XML file contents/structure to expect.

	public static 
	class XMLTAGDEF
		{
		public String name;
		public boolean bTextCont;   //true if contents is text
		public XMLTAGDEF xtSub;     //tag in the contents, null if none

		public XMLTAGDEF( 
			String n0, 
			boolean b0,
			XMLTAGDEF sub0 )
			{ 
			name = n0; 
			bTextCont = b0;
			xtSub = sub0;
			}
		}







	// ***********************************************
	// ** XML reader part 1b: Low-level funcs 
	// ***********************************************

	private static boolean isWS( char c )
		{
		return ( (c == ' ') ||
				(c == '\t') ||
				(c == '\r') ||
				(c == '\n') );
		}


	private static boolean 
	isOneOf( char c, char[] ac )
		{
		assert( ac.length > 0 );
		int i;
		for ( i = 0; i < ac.length; i++ )
			{
			if ( ac[i] == c ) { return true; }
			}
		return false;
		}




	// ***********************************************
	// ** XML reader part 2: Aux funcs for reading parts of XML file **
	// ***********************************************



	// Stop scanning on:
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

	// Scan '<' + anything_except_pointy_brackets + TERMINATOR ,
	//  where TERMINATOR = any of '<', '>', EOF,
	//  and where the "anything_except_pointy_brackets" may be empty.
	// However, if the first four scanned chars are "<!--", then the scanning
	// 	stops only: if the three characters "-->" are scanned, or if EOF is
	//  scanned.
	// Write all scanned chars to StringBuffer 'buf', including the
	//  starting '<'.  The TERMINATOR char is written to 'buf' only
	//  if it is '>' or '>', not if it is EOF.
	// Return true if the # of chars scanned (and consumed) is > 0.
	// Throw IOException on cg.getc()/ungetch() error.
	static boolean
	scanXMLTag(
		CharGetter cg,      //IN+OUT (state changed)
		StringBuffer buf )  //OUT (Dest buffer to write scanned stuff into)
		throws IOException
		{
		buf.setLength( 0 ); //init

		
		int iSc = 0; //Number of characters consumed
		char c_1 = '\0'; //previous char
		char c_2 = '\0'; //2nd previous
		boolean bIsComment = false;

		for(;;)
			{
			int ic;
			ic = cg.getc();
			char c = (char)ic;

			if ( (iSc == 3) &&
				 (c_2 == '!') && (c_1 == '-') && (c == '-') )
				{ bIsComment = true; }

			//1st char must be '<'
			if ( (iSc == 0) && (c != '<') )
				{
				cg.ungetc( ic ); 
				return false; 
				}

			if ( ic == CharGetter.EOF ) //Successful end of scan
				{
				cg.ungetc( ic ); //pushback the EOF
				return ( iSc > 0 );
				}

			if ( (!bIsComment) &&
				 (iSc > 0) &&
				 ( (c == '<') || (c == '>') ) ) //Successful end of scan
				{
				//consume the TERMINATOR char from the input
				buf.append( c );
				return true;
				}

			if ( bIsComment &&
				 (iSc > 0) &&
				 ( (c_2 == '-') && (c_1 == '-') && (c == '>') ) )
				{                                     //Successful end of scan
				//consume the "-->" from the input
				buf.append( c );
				return true;
				}


			buf.append( c ); 
			iSc++;
			c_2 = c_1; 
			c_1 = c;
			}
		}

	// Scan anything_except_pointy_brackets + TERMINATOR ,
	//  where TERMINATOR = any of '<', '>', EOF,
	//  and where the "anything_except_pointy_brackets" may be empty.
	// Write all scanned chars to StringBuffer 'buf', NOT including the
	//  TERMINATOR char.
	// Return true if the # of chars scanned (and consumed) is > 0.
	// Throw IOException on cg.getc()/ungetch() error.
	static boolean
	scanXMLTextContents(
		CharGetter cg,      //IN+OUT (state changed)
		StringBuffer buf )  //OUT (Dest buffer to write scanned stuff into)
		throws IOException
		{
		buf.setLength( 0 ); //init

		int iSc = 0;

		for(;;)
			{
			int ic;
			ic = cg.getc();
			char c = (char)ic;

			if ( ic == CharGetter.EOF ) //Successful end of scan
				{
				cg.ungetc( ic );
				return ( iSc > 0 );
				}

			if ( (c == '<') || (c == '>') ) //Successful end of scan
				{
				cg.ungetc( ic );
				return ( iSc > 0 );
				}

			buf.append( c ); 
			iSc++;
			}
		}









	// ***********************************************
	// ** XML reader part 3: Scan tag contents from String:
	// **   tag name and name-value pairs
	// ***********************************************

	
	
	private static void
	strScanWS(
		StringBuffer sIn )   //IN+OUT
		{
		int lenIn = sIn.length();
		if ( lenIn == 0 ) { return; } //nothing to do
		int i1 = 0;
		while ( i1 < lenIn && isWS(sIn.charAt(i1)) ) { i1++; }
		if ( i1 == 0 ) { return; } //nothing to do

		sIn.delete( 0, i1 );
		}
		
	private static String
	strScanItem(
		StringBuffer sIn,   //IN+OUT
		char[] acStop )     //IN (
		{
		int lenIn = sIn.length();
		int i2 = 0;
		while ( i2 < lenIn && !isOneOf( sIn.charAt(i2), acStop ) ) { i2++; }
		if ( i2 == 0 ) { return null; } //sIn contains no item

		String s1 = sIn.substring( 0, i2 ); //first item
		sIn.delete( 0, i2 );

		return s1;
		}



	private static String
	strScanQuoted()
		{

		return null;
		}







	// ***********************************************
	// ** XML reader part 4: Reading the data contents of the XML file **
	// ***********************************************

	// Classes NV and XMLTAGVAL:
	// Used for the output of public method 'readXMLFile()'.

	//Name with value (for attributes within XML tag)
	public static class NV 
		{
		public String name;
		public String value;  //NB: This may be empty string
		
		public NV( String n0, String v0 ) { name = n0; value = v0; }
		}

	public static class XMLTAGVAL
		{
		public LinkedList<NV> lAttr; //Attribute name-value pairs in tag
		public String sTextCont;     //Text contents of tag, null if none
		public LinkedList<XMLTAGVAL> lSub; //Sub-tags in contents, null if none

		public XMLTAGVAL(  
			LinkedList<NV> lAttr0, 
			String sTextCont0, 
			LinkedList<XMLTAGVAL> lSub0 )
			{ lAttr = lAttr0; sTextCont = sTextCont0; lSub = lSub0; }
		}
	


	//Dump LinkedList<XMLTAGVAL> to stdout.
	//(For debug only, to inspect the output of func 'readXMLFile()'.) 
	private static void 
	indent( int depth )
		{
		int i;
		for ( i = 0; i < depth; i++ ) { System.out.print( " .  " ); }
		}
	public static void 
	lTV_dump( LinkedList<XMLTAGVAL> lTV, int depth )
		{
		Iterator<XMLTAGVAL> iTV = lTV.iterator();
        while ( iTV.hasNext() )
            {
			XMLTAGVAL tv = iTV.next();

			indent( depth );
			System.out.println( "NV-pairs: n=" + tv.lAttr.size() );
			Iterator<NV> iNV = tv.lAttr.iterator();
			while ( iNV.hasNext() )
				{
				NV nv = iNV.next();
				indent( depth+1 );
				System.out.println( "[" + nv.name + ":" + nv.value + "]" );
				}

			if ( tv.sTextCont != null )
				{
				indent( depth );
				System.out.println( "textCont: [" + tv.sTextCont + "]" ); 
				}

			if ( tv.lSub != null )
				{
				lTV_dump( tv.lSub, depth+1 );
				}
			}
		}
			



	//Private subroutine to 'readXMLFile()', that does the real work.
	//Recursive.  Reads a *set* of xml tags of tag-name td.name,
	// plus for each its contents.
	//Keeps reading till an end-tag "</xxxx>" is read; in this case,
	// the method sets ovEnd.value := "xxxx" = the tag-name in the end-tag.
	//If reading stops for another reason than scanning an an end-tag,
	// then ovEnd.value is set to 'null'.
	//The (nested) data in the xml tags is written into 'lOut'.
	private static void //String
	readXML_cg( 
		XMLTAGDEF td,               //IN (Definition of structure of XML file)
		CharGetter cg,              //IN
		OV<String> ovEnd,           //OUT
		LinkedList<XMLTAGVAL> lOut, //OUT (Overwritten with the XML data read)
		boolean _v )                //IN
		throws Exception
		{
		ovEnd.value = null; //init
		lOut.clear();

		while( ! cg.atEOF() )
			{
			StringBuffer buf = new StringBuffer();


			scanWS( cg );


			if ( ! scanXMLTag( cg, buf ) ) { break; }

			if(_v) { OUT( "in = [" + buf + "]" ); }


			int buflen = buf.length();
			assert( buflen > 0 );
			if ( buf.charAt( buflen-1 ) != '>' )
				{ throw new Exception( "Incomplete XML tag" ); }

			String sIn = buf.toString();

			//Ignore "<?..."   (e.g. "<?xml")
			// and "<!..."   (e.g. "<!DOCTYPE..." and "<!--...")
			if ( sIn.startsWith( "<?" ) ||
			     sIn.startsWith( "<!" ) )
				{ continue; }


			assert( sIn.length() >= 2 );
			assert( sIn.charAt(0) == '<' );
			assert( sIn.charAt(buflen-1) == '>' );


			//Check if is end-tag
			if ( sIn.charAt(1) == '/' )
				{
				if(_v) { OUT( " `-- isEND" ); }
				ovEnd.value = sIn.substring( 2, buflen-1 );
				return;
				}


			//Remove the enclosing '<' and '>'
			buf.deleteCharAt( buflen-1 );
			buf.deleteCharAt( 0 );
			buflen = buf.length();
			//DEBUG OUT( "buf 1 = [" + buf + "]" );


			//Check if needs closing XML-tag
			boolean bHasSlashAtEnd =
				( (buflen > 0) && (buf.charAt(buflen-1) == '/') );
			boolean bNeedsEndtag = ( ! bHasSlashAtEnd );
			if(_v) { OUT( " `-- needsEnd = " + bNeedsEndtag ); }
			if ( bHasSlashAtEnd )
				{ //Remove the '/' at end
				buf.deleteCharAt( buflen-1 );
				buflen = buf.length();
				}


			//Scan the tagname and name-value pairs inside the xml tag
			LinkedList<NV> lNV = new LinkedList<NV>();
			{
			char[] acWS = { ' ', '\t', '\r', '\n' };
			char[] acEq = { '=' };
			char[] acQu = { '"' };

			StringBuffer sb1 = new StringBuffer( buf );

			//Get the tag name
			String strTagname = strScanItem( sb1, acWS );
			if ( strTagname == null )
				{ throw new Exception( "Missing tag name" ); }

			if(_v) { OUT( " `-- name = [" + strTagname + "]" ); }

			if ( ! strTagname.equals( td.name ) )
                { throw new Exception( "Unexpected tag name" ); }


			
			//DEBUG OUT( " `-- rest = [" + sb1 + "]" ); 

			//Get the name-value pairs in the tag
			for(;;)
				{
				strScanWS( sb1 );

				String sN = strScanItem( sb1, acEq );
				if ( sN == null ) { break; } //END OF FOR-LOOP

				if ( ! sb1.toString().startsWith( "=\"" ) )
					{ throw new Exception( "Missing '=\"' in NV-pair" ); }
				sb1.delete( 0, 2 );

				String sV = strScanItem( sb1, acQu );
				if ( sV == null )
					{ //Value is empty string; this is allowed.
					sV = "";
					}

				if(_v) { OUT( " `-- NV = [" + sN + ":" + sV + "]" ); }
				NV nv = new NV( sN, sV );
				lNV.add( nv );

				if ( ! sb1.toString().startsWith( "\"" ) )
					{ throw new Exception( "Missing closing '\"' in NV-pair" ); }
				sb1.delete( 0, 1 );
				}

			}



			//Scan XML sub-tag contents (if any)
			LinkedList<XMLTAGVAL> lSub = null;
			if ( td.xtSub != null )
				{
				lSub = new LinkedList<XMLTAGVAL>();

				assert( td.bTextCont == false );

				OV<String> ovEndSub = new OV<String>( null );
				readXML_cg( td.xtSub, cg, ovEndSub, lSub, _v );
				if ( ovEndSub.value == null )
					{  throw new Exception( "Missing end tag (3)" ); }

				if(_v) { OUT( " `-- END = [" + ovEndSub.value + "]" ); }
				if ( ! ovEndSub.value.equals( td.name ) )
					{  throw new Exception( "Wrongly-named end tag" ); }
				}


			//Scan text contents (if any)
			String strTextCont = null;
			if ( td.bTextCont )
				{
				assert( td.xtSub == null );

				StringBuffer bufCont = new StringBuffer();
				boolean ok;
				ok = scanXMLTextContents( cg, bufCont );
				if ( !ok )
					{ throw new Exception( "Missing text contents" ); }
				assert( bufCont.length() > 0 );
				if(_v) { OUT( " `-- text cont = [" + bufCont + "]" ); }

				assert( bNeedsEndtag );

				StringBuffer bufEnd = new StringBuffer();
				if ( ! scanXMLTag( cg, bufEnd ) )
					{ throw new Exception( "Missing end tag after text (1)" ); }

				String sInEnd = bufEnd.toString();
				if ( ! sInEnd.startsWith( "</" + td.name ) )
					{ throw new Exception( "Missing end tag after text (2)" ); }

				strTextCont = bufCont.toString();
				}



			//Add (nested) tag data to output list
			XMLTAGVAL tvNew = new XMLTAGVAL( lNV, strTextCont, lSub );
			lOut.add( tvNew );


			}//while(!atEOF)
		}
		


	//Client-called func.
	public static String
	readXMLFile(
		XMLTAGDEF td,               //IN (Definition of structure of XML file)
		String pathname,            //IN (Pathname of file to read)
		LinkedList<XMLTAGVAL> lOut, //OUT (Overwritten with the XML data read)
		boolean _v )
		{
        BufferedReader br = null;
		CharGetter cg = null;
        try
            {
            br = new BufferedReader( new FileReader( pathname ) );
			cg = new CharGetter( br );
			boolean bInTag = false;
			

			OV<String> ovEnd = new OV<String>( null );
			readXML_cg( td, cg, ovEnd, lOut, _v );
			if ( ovEnd.value != null )
				{
				if(_v) { OUT( "*END = " + ovEnd.value ); }
				}
			

			}
        catch ( Exception e )
            {
			//TODO this kind of exception handling is very special to MR's coding style (not good, though)
			String erm = "Fail read xml-file ";
			if ( cg != null ) 
				{ erm += "(at line " + cg.iline() + ", " +
					"col " + cg.icol() + ")"; }
			erm += ": "+ e.getMessage();
			return erm;
            }
        finally
            {
            if ( br != null ) 
                { 
                try { br.close(); } catch ( Exception e ) {
                	e.printStackTrace();
                }
                }
            }
        return null; //success
		}




	// ***********************************************
	// ** XML reader part 5: 
	// **  Client functions for accessing data from the output list 
	// **  of readXMLFile().
	// ***********************************************


	//Read data from the NV attribute list of an XMLTAGVAL element:
    //Return from NV-list lAttr the value of the item with name 'name';
    //Return null if not found.
    public static String
    lAttr_getValueByName(
        LinkedList<NV> lAttr_in,   //IN
        String name )                         //IN 
        {
        Iterator<NV> iNV = lAttr_in.iterator();
        while ( iNV.hasNext() )
            {
            XMLReader.NV nv = iNV.next();
            if ( nv.name.equals( name ) ) { return nv.value; }
            }
        return null;
        }







	}

