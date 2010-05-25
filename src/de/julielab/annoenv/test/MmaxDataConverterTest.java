package de.julielab.annoenv.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Text;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import de.julielab.annoenv.conversions.MmaxDataConverter;
import de.julielab.annoenv.conversions.Tidsid;

import junit.framework.TestCase;

/**
 * Unit Test for class MmaxDataConverter
 * 
 * @author muehlhausen
 * @version 1.0 Oct 20, 2006
 */
public class MmaxDataConverterTest extends TestCase {

	final String FILE_T2 = "t2.t2";
	final String FILE_IOB = "iob.iob";
	
	final String FILE_BASEDATA = "basedata.xml";
	final String FILE_MARKABLE = "markable.xml";
	final String FILE_PRIOLIST = "priolist.txt";
	final String FILE_TIDSID = "tidsid.txt";
	final String FILE_SENTENCE_MARKABLE = "sentMarkable.xml";

	final String TEXT = "token";
	final String IOB_B = "B_";
	final String IOB_I = "I_";
	
	private DocumentBuilder builder;
	
	private void initDocumentBuilder() throws ParserConfigurationException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(false);
		factory.setNamespaceAware(false);
		this.builder = factory.newDocumentBuilder();	
		
		builder.setEntityResolver(new EntityResolver()
		{
			public InputSource resolveEntity(String publicId, String systemId)
			throws SAXException, IOException {
				if (systemId != null) {
					if (systemId.endsWith("words.dtd")) {
						return new InputSource(
										new StringReader("<!ELEMENT words (word*)>\n"
														+ "<!ELEMENT word (#PCDATA)>\n"
														+ "<!ATTLIST word id ID #REQUIRED>"));
					} else if (systemId.endsWith("markables.dtd")) {
						return new InputSource(
										new StringReader("<!ELEMENT markables (markable*)>\n"
														+ "<!ATTLIST markable id ID #REQUIRED>"));
					} 
				}
				
				throw new SAXException("Unknown DTD!");		
			}
		});
	}

	// for basedata
	final String[] ids = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "9.5",
			"9.75", "10", "11", "12", "13", "14", "15", "15.5", "16", "17",
			"18", "19", "20", "21", "22", "23", "24", "24.5", "24.75", "25",
			"26", "27" };

	// for basedata; corresponds to ids
	final String[] words = { "Daunomycin", "is", "a", "potent", "inducer",
			"of", "p53", "and", "NF", "-", "kappaB", "transcription",
			"factors", ".", "The", "human", "p", "21", "promoter", "habors",
			"p53", "-", "responsive", "elements", "and", "an", "NF", "-",
			"kappaB", "binding", "site", "." };

	// for markable
	final String[] spans = { "word_2..word_4", "word_4.2..word_6", "word_6.45",
			"word_9..word_9.75, word_11" };

	// for markable; up to three annotations per markable; corresponds to spans
	final String[][] annos = { { "ribosome", "plasma_membrane", "" },
			{ "golgi_apparatus", "", "" }, { "cytosol", "", "" },
			{ "nuclear_membrane", "plasma_membrane", "cytoplasm" } };

	// for priolist
	final String[] prios = { "ribosome", "plasma_membrane", "cytosol",
			"nuclear_membrane" };

	// for tidsid (values of sid have to be '0' because the here generated pos
	// data files have only one sentence)
	final String[][] tidsids = { { "0", "12", "12345", "1" },
			{ "12", "17", "54321", "0" } };

	// for pos data
	final String[] posSen = {
			"Daunomycin_NN is_VBZ a_DT potent_JJ inducer_NN of_IN p53_NN and_CC NF-kappaB_NN transcription_NN factors_NNS ._.",
			"The_DT human_JJ p21_NN promoter_NN harbors_VBZ p53_NN -_HYPH responsive_JJ elements_NNS and_CC an_DT NF-kappaB_NN binding_NN site_NN ._." };

	//for sentenceMarkable - 1 based
	final String[] sentSpans = {"word_1..word_12", "word_13..word_29"};
	
	MmaxDataConverter converter;
	
	public MmaxDataConverterTest(String string) {
		super(string);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {

		converter = new MmaxDataConverter();
		initDocumentBuilder();

		createAllFiles();
		super.setUp();
	}

	/**
	 * Creates all files
	 */
	private void createAllFiles() {
		createBaseDataFile(ids, words);
		createMarkableFile(spans, annos);
		createPriolistFile(prios);
		createPosDataFiles(posSen, tidsids);
		createTidsidFile(tidsids);
		createSentenceMarkable(sentSpans);
	}

	/**
	 * Create sentence markable file
	 * @param sentSpan1
	 */
	private void createSentenceMarkable (String[] sentSpan1) {
		
		Document doc = new Document();
		Element root = new Element("markables");
		doc.setRootElement(root);
		for (int i = 0; i < sentSpan1.length; i++) {
			Element word = new Element("markable");
			word.setAttribute("id", "markable_" + i);
			word.setAttribute("span", "word_" + sentSpan1[i]);
			root.addContent(word);
		}
		writeXML(doc, FILE_SENTENCE_MARKABLE);
	}
	
	/**
	 * Create tidsid file
	 */
	private void createTidsidFile(String[][] tidsids1) {
		try {
			PrintWriter writer = new PrintWriter(new File(FILE_TIDSID));
			for (int i = 0; i < tidsids1.length; i++) {
				writer.append(tidsids1[i][0] + " " + tidsids1[i][1] + " "
						+ tidsids1[i][2] + " " + tidsids1[i][3] + "\n");
			}
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create POS data file, or appends POS data to an already existing file, 'nonexisting' 
	 *  sentences are represented as new lines
	 * 
	 * @precondition tidsids1 has a ascending order within the sentences od the same tid 
	 */
	private void createPosDataFiles(String[] posSen1, String[][] tidsids1) {

		for (int i = 0; i < posSen1.length; i++) {
			try {
				File file = new File(tidsids1[i][2]);
				file.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		for (int i = 0; i < posSen1.length; i++) {
			try {
				File file = new File(tidsids1[i][2]);
				int sentence = Integer.parseInt(tidsids1[i][3]);				
				
				int linesToWrite = 0;
				if (file.exists()) {
					linesToWrite = sentence - getLines(file) + 2;
				} else {
					linesToWrite = sentence;
				}
				FileWriter writer = new FileWriter(file, file.exists());
				
				for (int j = 0; j < linesToWrite; j ++) {
					writer.append("\n");
				}
					
				writer.append(posSen1[i]);					
				writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Calculates the number of files contained in a file
	 * @param file
	 * @return number of lines of a file
	 */
	private int getLines(File file) {
		
		int lineCount = 0;
		BufferedReader reader = null;
		try {
			String line = ""; 
			reader = new BufferedReader(new FileReader(file));
			while (line != null) {
				line = reader.readLine();
				lineCount++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if ( reader != null ) {
			    try { reader.close(); } catch ( IOException e ) { e.printStackTrace(); }
			}
		}	
		return lineCount;
	}

	/**
	 * Ctreate the priolist file
	 */
	private void createPriolistFile(String[] prios) {
		PrintWriter writer;
		try {
			writer = new PrintWriter(new File(FILE_PRIOLIST));
			for (int i = 0; i < prios.length; i++) {
				writer.append(prios[i] + "\n");
			}
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the basedata XML file
	 */
	private void createBaseDataFile(String[] ids1, String[] words1) {

		if (ids1.length != words1.length) {
			System.out.println("ids1.length = " + ids1.length + "words1.length = "
					+ words1.length);
			return;
		}
		Document doc = new Document();
		Element root = new Element("words");
		doc.setRootElement(root);
		for (int i = 0; i < ids1.length; i++) {
			Element word = new Element("word");
			word.setAttribute("id", "word_" + ids1[i]);
			Text text = new Text(words1[i]);
			word.addContent(text);
			root.addContent(word);
		}
		writeXML(doc, FILE_BASEDATA);
	}

	/**
	 * Create the markable XML file
	 */
	private void createMarkableFile(String[] spans1, String[][] annos1) {

		Document doc = new Document();
		Element root = new Element("markables");
		doc.setRootElement(root);
		for (int i = 0; i < spans1.length; i++) {
			Element markable = new Element("markable");
			markable.setAttribute("span", spans1[i]);
			for (int j = 0; j < annos1[i].length; j++) {
				if (!annos1[i][j].equals("")) {
					markable.setAttribute("attr" + j, annos1[i][j]);
				}
			}

			root.addContent(markable);
		}
		writeXML(doc, FILE_MARKABLE);
	}

	/**
	 * Write a Doctument to a file
	 * 
	 * @param doc
	 */
	private void writeXML(Document doc, String filename) {

		PrintWriter writer;
		try {
			writer = new PrintWriter(new File(filename));
			XMLOutputter outputter = new XMLOutputter();
			Format format = Format.getPrettyFormat();
			outputter.setFormat(format);
			outputter.output(doc, writer);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 */
	@Override
	protected void tearDown() throws Exception {
		
		//TODO wieder einkommentieren
		/*
		for (int i = 0; i < tidsids.length; i ++) 
			{ 
				File file = new
				File(tidsids[i][2]); file.delete(); 
			} 
			
		String[] files = {FILE_BASEDATA, FILE_MARKABLE, FILE_PRIOLIST, FILE_TIDSID}; 
		for (int i = 0; i < files.length; i++) 
			{ 
				File file = new File(files[i]);file.delete(); 
			} 
		super.tearDown();
		*/
	}
	
	/**
	 * Test if tokens(words) are all at their place
	 */
	public void testWords() {
		
		createAllFiles();
		final int[] lineNr = { 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
				18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33,
				34, 35 };
		File outputFile = new File (FILE_T2);
		try {
			converter.convertToT2(outputFile, builder.parse(new File(FILE_MARKABLE)),
							builder.parse(new File(FILE_BASEDATA)), getList(new File(FILE_PRIOLIST)), 
							getTidSidList(new File(FILE_TIDSID)), "./","some_uri");
			for (int i = 0; i < lineNr.length; i++) {
				assertTrue("i = " + i, lineContainsWord(lineNr[i], outputFile,
						words[i]));
			}
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Test if sentence overlapping is detected
	 * 
	 * Construction: Deviant to the above defined fields, posSen1 (array for POS data) contains a second
	 *  sentence - seperated form the first with a newline - in the 2nd element to simulate overlapping annotations. 
	 *  Hence the basedata (arrays ids2 and words1) is extended. Additionally the tidsid data has been
	 *  adapted. 
	 */
	public void testSentenceOverlapping() {
		
		createAllFiles();
		final String[] ids1 = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "9.5",
				"9.75", "10", "11", "12",//end of Senctence 1 
				"13", "14", "15", "15.5", "16", "17", "18", "19", "20", "21", "22", "23",
				"24", "24.5", "24.75", "25", "26", "27", //end of sentence 2 
				"28", "29", "30", "31", "32", "33", "34", "35", "36", 
				"37", "38", "39", "40", "41", "42" };//end of senctence 3

		final String[] words1 = { "Daunomycin", "is", "a", "potent", "inducer",
				"of", "p53", "and", "NF", "-", "kappaB", "transcription",
				"factors", ".", "The", "human", "p", "21", "promoter", "habors",
				"p53", "-", "responsive", "elements", "and", "an", "NF", "-",
				"kappaB", "binding", "site", ".", "Inhibition", "of", "human", "p53", "basal", "transcription", 
				"by", "down", "-", "regulation", "of", "protein", "kinase", "Cdelta", "."};
		
		final String[] sentSpans = {"word_1..word_12", "word_13..word_27", "word_28..word_42"};
		
		final String[] spans1 = { "word_1..word_3", "word_4..word_6", "word_8..word_9", "word_10..word_15" };
		final String[][] annos1 = { { "a1" }, { "a1" }, { "a2" }, {"a1"} };
		final String[] prios1 = { "a1", "a2" };
 
		createBaseDataFile(ids1, words1);
		createSentenceMarkable(sentSpans);
		createMarkableFile(spans1, annos1);
		createPriolistFile(prios1);
		
		File outputFile = new File (FILE_T2);
		try {
			converter.convertToIOB(outputFile, builder.parse(new File(FILE_MARKABLE)),
					builder.parse(new File(FILE_BASEDATA)), getList(new File(FILE_PRIOLIST)), 
					builder.parse(new File(FILE_SENTENCE_MARKABLE)), true,"");
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	/**
	 * Test output of method convertToIOB - tests if annotations are correct
	 * 
	 * scenario a - addToIOB is true 
	 * scenario b - addToIOB is false
	 */
	public void testConvertToIOB() {

		createAllFiles();
		
		final String[] spans1 = { "word_1..word_3", "word_4..word_6",
				"word_8..word_9", "word_13..word_15" };
		final String[][] annos1 = { { "a1" }, { "a1" }, { "a2" }, {"a1"} };
		final String[] prios1 = { "a1", "a2" };

		createMarkableFile(spans1, annos1);
		createPriolistFile(prios1);
			
		String iob_b = MmaxDataConverter.IOB_B;
		String iob_i = MmaxDataConverter.IOB_I;
		String iob_o = MmaxDataConverter.IOB_O;

		final String[] iobAnnos1 = { iob_b + "a1", iob_i + "a1", iob_i + "a1",
				iob_b + "a1", iob_i + "a1", iob_i + "a1", iob_o, iob_b + "a2",
				iob_i + "a2", iob_o, iob_o, iob_o, iob_o, iob_o, iob_b + "a1", iob_i + "a1"};
		
		final String[] iobAnnos2 = { "a1", "a1", "a1", "a1", "a1", "a1", iob_o, "a2", "a2", iob_o, iob_o,
				iob_o, iob_o, iob_o, "a1", "a1"};

		final int[] lineNr = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 16, 17 };

		assertTrue("wrong length of arrays inside test case: lineNr.length = "
				+ lineNr.length + "  iobAnnos1.length = " + iobAnnos1.length,
				lineNr.length == iobAnnos1.length);
		
		assertTrue("wrong length of arrays inside test case: lineNr.length = "
				+ lineNr.length + "  iobAnnos2.length = " + iobAnnos2.length,
				lineNr.length == iobAnnos2.length);

		File outputFile = new File(FILE_IOB);
		try {
			//Scenario a - addToIOB is true 
			converter.convertToIOB(outputFile, builder.parse(new File(FILE_MARKABLE)), 
							builder.parse(new File(FILE_BASEDATA)), getList(new File(FILE_PRIOLIST)), 
							builder.parse(new File(FILE_SENTENCE_MARKABLE)), true,"");

			for (int i = 0; i < lineNr.length; i++) {
				assertTrue("Scenario a, i = " + i + " lineNr[i] = " + lineNr[i]
						+ " iobAnnos1[i] = " + iobAnnos1[i], lineContainsAnno(
						lineNr[i], outputFile, iobAnnos1[i]));
			}
			//Scenario b - addToIOB is false
			converter.convertToIOB(outputFile, builder.parse(new File(FILE_MARKABLE)), 
							builder.parse(new File(FILE_BASEDATA)), getList(new File(FILE_PRIOLIST)), 
							builder.parse(new File(FILE_SENTENCE_MARKABLE)), false,"");

			for (int i = 0; i < lineNr.length; i++) {
				assertTrue("Scenario b, i = " + i + " lineNr[i] = " + lineNr[i]
						+ " iobAnnos2[i] = " + iobAnnos2[i], lineContainsAnno(
						lineNr[i], outputFile, iobAnnos2[i]));
			}
			
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Test nested annotations: a) longer annotation has higher priority b)
	 * longer annotation has lower priority c) longer annotation is filtered by
	 * prioity list
	 */
	public void testNestedAnnos() {

		createAllFiles();
		
		final String[] spans1 = { "word_1..word_9.5", "word_3..word_5" };
		final String[][] annos1 = { { "a1" }, { "a2" } };
		String[] prios1 = { "a1", "a2" };

		createMarkableFile(spans1, annos1);
		createPriolistFile(prios1);

		/**
		 * Scenario a - longer annotation has higher priority
		 */
		int lineNr[] = { 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
		String[] anno1 = { "a1", "a1", "a1", "a1", "a1", "a1", "a1", "a1",
				"a1", "a1", "O" };
		assertTrue("wrong length of arrays inside test case - scenario a",
				lineNr.length == anno1.length);

		try {
			File outputFile = new File (FILE_T2);
			converter.convertToT2(outputFile, builder.parse(new File(FILE_MARKABLE)), 
							builder.parse(new File(FILE_BASEDATA)), getList(new File(FILE_PRIOLIST)), 
							getTidSidList(new File(FILE_TIDSID)), "./","some_uri");

			for (int i = 0; i < lineNr.length; i++) {
				assertTrue("i = " + i, lineContainsAnno(lineNr[i], outputFile,
						anno1[i]));
			}

		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/**
		 * Scenario b - longer annotation has lower priority
		 */
		String[] prios2 = { "a2", "a1" };
		createPriolistFile(prios2);
		try {
			File outputFile = new File (FILE_T2);
			converter.convertToT2(outputFile, builder.parse(new File(FILE_MARKABLE))
							, builder.parse(new File(FILE_BASEDATA)), 
							getList(new File(FILE_PRIOLIST)), getTidSidList(new File(FILE_TIDSID)), 
							"./", "some_uri");

			for (int i = 0; i < lineNr.length; i++) {
				assertTrue(lineContainsAnno(lineNr[i], outputFile, anno1[i]));
			}

		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/**
		 * Scenario c - longer annotation is filtered by prioity list
		 */
		String[] anno2 = { "O", "O", "a2", "a2", "a2", "O", "O", "O", "O", "O",
				"O" };
		assertTrue("wrong length of arrays inside test case - scenario c",
				lineNr.length == anno2.length);
		String[] prios3 = { "a2" };
		createPriolistFile(prios3);
		try {
			File outputFile = new File (FILE_T2);
			converter.convertToT2(outputFile, builder.parse(new File(FILE_MARKABLE))
							, builder.parse(new File(FILE_BASEDATA)), 
							getList(new File(FILE_PRIOLIST)), getTidSidList(new File(FILE_TIDSID)), 
							"./","some_uri");

			for (int i = 0; i < lineNr.length; i++) {
				assertTrue(lineContainsAnno(lineNr[i], outputFile, anno2[i]));
			}

		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Test intersecting annotations - the anno with higher priority is dominant in the intersecting part
	 * 
	 * a)	a1 has higher priority
	 * b)	a2 has higher priority
	 */
	public void testIntersectingAnnos() {
		
		createAllFiles();
		final String[] spans1 = { "word_2, word_6", "word_4, word_8" };
		final String[][] annos1 = { { "a1" }, { "a2" } };
		
		//priolist for scenario a)
		final String[] priosA = { "a2", "a1" };
		
		//priolist for scenario b)
		final String[] priosB = { "a1", "a2" };
		
		int lineNr[] = { 2, 3, 4, 5, 6, 7, 8, 9, 10};
		
		//expected result for scenario a
		String[] annoA = { "O", "a1", "a1", "a2", "a2", "a2", "a2", "a2",
				"O"};		
		
		//expected result for scenario b
		String[] annoB = { "O", "a1", "a1", "a1", "a1", "a1", "a2", "a2",
				"O"};
		
		createMarkableFile(spans1, annos1); 
		createPriolistFile(priosA);
		
		try {
			
			//Scenario a)
			File outputFile = new File(FILE_T2);
			converter.convertToT2(outputFile, builder.parse(new File(FILE_MARKABLE)), 
							builder.parse(new File(FILE_BASEDATA)), getList(new File(FILE_PRIOLIST)), 
							getTidSidList(new File(FILE_TIDSID)), "./","some_uri"); 
			
			for (int i = 0; i < lineNr.length; i++) {
				assertTrue("Scenario a", lineContainsAnno(lineNr[i], outputFile, annoA[i]));
			}
		
			//Scenario b)
			createPriolistFile(priosB);
			converter.convertToT2(outputFile, builder.parse(new File(FILE_MARKABLE)), 
							builder.parse(new File(FILE_BASEDATA)), 
							getList(new File(FILE_PRIOLIST)), getTidSidList(new File(FILE_TIDSID)), "./","some_uri"); 
			
			for (int i = 0; i < lineNr.length; i++) {
				assertTrue("Scenario b", lineContainsAnno(lineNr[i], outputFile, annoB[i]));
			}
			
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * Test if priority rule matches with equally long annotations 
	 * a) a1 has higher priority - annotations in the same markables 
	 * b) a2 has higher priority - annotations in the same markables 
	 * c) a1 has higher priority - annotations in different markables 
	 * d) a2 has higher priority - annotations in different markables
	 */
	public void testPriority() {

		final String[] spans1 = { "word_3, word_6" };
		final String[][] annos1 = { { "a1", "a2" } };

		createMarkableFile(spans1, annos1);

		/**
		 * Scenario a - annotation a1 higher priority - annotations in the same
		 * markables
		 */
		a1HigherA2();

		/**
		 * Scenario b - annotation a1 higher priority - annotations in the same
		 * markables
		 */
		a2HigherA1();

		final String[] spans2 = { "word_3, word_6", "word_3, word_6" };
		final String[][] annos2 = { { "a1" }, { "a2" } };

		createMarkableFile(spans2, annos2);
		/**
		 * Scenario c - a1 has higher priority - annotations in different
		 * markables
		 */
		a1HigherA2();

		/**
		 * Scenario d - a2 has higher priority - annotations in different
		 * markables
		 */
		a2HigherA1();
	}

	/**
	 * Helper for testPriority()
	 */
	private void a1HigherA2() {

		final String[] prios1 = { "a1", "a2" };
		createPriolistFile(prios1);

		int lineNr[] = { 2, 3, 4, 5, 6, 7, 8 };
		String[] anno1 = { "O", "O", "a1", "a1", "a1", "a1", "O" };
		assertTrue("wrong length of arrays inside test case",
				lineNr.length == anno1.length);

		try {
			File outputFile = new File (FILE_T2);
			converter.convertToT2(outputFile, builder.parse(new File(FILE_MARKABLE)), 
							builder.parse(new File(FILE_BASEDATA)), 
							getList(new File(FILE_PRIOLIST)), getTidSidList(new File(FILE_TIDSID)), "./","some_uri");

			for (int i = 0; i < lineNr.length; i++) {
				assertTrue("i = " + i, lineContainsAnno(lineNr[i], outputFile,
						anno1[i]));
			}
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private List<String> getList(File file) {
		ArrayList<String> textList = new ArrayList<String>();
		try {
				BufferedReader r = new BufferedReader(new FileReader(file));

				String line;
				while ((line = r.readLine()) != null) {
					textList.add(line);
				}
				r.close();
		} catch (IOException e) {
			System.err.println("File " + file + " could not be read!");
		}
		return textList;
	}

	/**
	 * Helper for testPriority()
	 */
	private void a2HigherA1() {

		final String[] prios2 = { "a2", "a1" };
		createPriolistFile(prios2);

		int lineNr[] = { 2, 3, 4, 5, 6, 7, 8 };
		String[] anno2 = { "O", "O", "a2", "a2", "a2", "a2", "O" };
		assertTrue("worng length of arrays inside test case",
				lineNr.length == anno2.length);

		try {
			File outputFile = new File (FILE_T2);
			converter.convertToT2(outputFile, builder.parse(new File(FILE_MARKABLE)), 
							builder.parse(new File(FILE_BASEDATA)), getList(new File(FILE_PRIOLIST)), 
							getTidSidList(new File(FILE_TIDSID)), "./","some_uri");

			for (int i = 0; i < lineNr.length; i++) {
				assertTrue("i = " + i, lineContainsAnno(lineNr[i], outputFile,
						anno2[i]));
			}

		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Checks if a certain line of a file contains a certain word - assumes that
	 * words appear only at the begin of a line
	 * 
	 * @param lineNr
	 * @param file
	 * @param word
	 * @return true if word is contained at the beginning of the line lineNr
	 */
	private boolean lineContainsWord(int lineNr, File file, String word) {
		return lineContains(lineNr, file, word, true);
	}

	/**
	 * Checks if a certain line of a file contains a certain annotation -
	 * assumes that annotations appear only at the end of a line
	 * 
	 * @param lineNr
	 * @param file
	 * @param anno
	 * @return true if annotation is contained at the end of the line lineNr
	 */
	private boolean lineContainsAnno(int lineNr, File file, String anno) {
		return lineContains(lineNr, file, anno, false);
	}

	/**
	 * Checks if a certain line of a file contains a certain string (at the
	 * beginning or end of the line)
	 * 
	 * @param lineNr
	 * @param file
	 * @param string
	 * @param begin
	 *            if begin is true the string is at the beginn of a line, if
	 *            false it is at the end
	 * @return true if string is contained (at the beginning or end) of the line
	 *         lineNr
	 */
	private boolean lineContains(int lineNr, File file, String string,
			boolean begin) {

		BufferedReader bufReader = null;
		try {
			FileReader reader = new FileReader(file);
			bufReader = new BufferedReader(reader);
			int readLines = 0;
			String line = null;
			do {
				line = bufReader.readLine();
				readLines++;
			} while (line != null && readLines != lineNr);
			if (line != null) {
				Pattern pattern = null;
				if (begin) {
					pattern = Pattern.compile("^" + string);
				} else {
					pattern = Pattern.compile(string + "$");
				}

				Matcher matcher = pattern.matcher(line);
				if (matcher.find()) {
					return true;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if ( bufReader != null ) {
			    try { bufReader.close(); } catch ( IOException e ) { e.printStackTrace(); } 
			}
		}
		return false;
	}
	
	/**
	 * Reads the tidsidFile and puts its data into an List of Tidsid elements.
	 * 
	 * @param tidSidFile
	 * @return
	 */
	private List<Tidsid> getTidSidList(File tidSidFile) {

		List<Tidsid> list = new ArrayList<Tidsid>();

		BufferedReader bufReader = null;
		try {
			FileInputStream stream = new FileInputStream(tidSidFile);
			Reader in = new InputStreamReader(stream);
			bufReader = new BufferedReader(in);

			String line = bufReader.readLine();
			while (line != null) {

				Tidsid tidsid = new Tidsid();
				StringTokenizer tokenizer = new StringTokenizer(line);

				if (tokenizer.countTokens() == 4) {
					String offsetStr = tokenizer.nextToken();
					tidsid.setOffset(Integer.parseInt(offsetStr));
					String distanceStr = tokenizer.nextToken();
					tidsid.setDistance(Integer.parseInt(distanceStr));
					String tidStr = tokenizer.nextToken();
					tidsid.setTid(Integer.parseInt(tidStr));
					String sidStr = tokenizer.nextToken();
					tidsid.setSid(Integer.parseInt(sidStr));
					list.add(tidsid);
				} else {
					throw new IllegalStateException(
							" Tidsid file has an uncorrect number of parameters.");
				}
				line = bufReader.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
			//TODO handle exception
			throw new RuntimeException(e.getMessage());
		} finally {
			if ( bufReader != null ) {
			    try { bufReader.close(); } catch ( IOException e ) { e.printStackTrace(); } 
			}
		}
		return list;
	}
}
