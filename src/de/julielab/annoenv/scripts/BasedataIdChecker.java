/** 
 * BasedataIdChecker.java
 * 
 * Copyright (c) 2010, JULIE Lab. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0 
 *
 * Author: kampe
 * 
 * Current version: 2.0
 * Since version:   1.6
 *
 * Creation date: 20.01.2010 
 **/

package de.julielab.annoenv.scripts;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import java.sql.ResultSet;
import java.sql.SQLException;


import java.util.HashSet;

import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import de.julielab.annoenv.conversions.DownloadMMaxData;
import de.julielab.annoenv.conversions.Range;

import de.julielab.annoenv.db.sql.SQLDatabaseManager;
import de.julielab.annoenv.db.sql.SQLFunctions;

/**
 * Standalone tool to check whether there are problems with basedata. Was used
 * for export of annotations.
 * 
 * @author kampe
 */
public class BasedataIdChecker {

	public final static String ELEMENT_WORD = "word";

	public final static String ELEMENT_MARKABLE = "markable";

	public final static String ATTRIBUTE_ID = "id";

	public final static String ATTRIBUTE_SPAN = "span";

	private static DocumentBuilder builder;

	private TreeSet<String> set;

	private HashSet<String> xmlErrorSet;

	public final static String PATTERN_NUMBER = "\\d+(\\.\\d+)?";

	public final static String PATTERN_WORD_AND_NUMBER = "word_\\d+(\\.\\d+)?";

	public BasedataIdChecker() throws ParserConfigurationException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(false);
		factory.setNamespaceAware(false);
		builder = factory.newDocumentBuilder();

		builder.setEntityResolver(new EntityResolver() {
			public InputSource resolveEntity(String publicId, String systemId)
					throws SAXException, IOException {
				return new InputSource(new StringReader(
						"<!ELEMENT words (word*)>\n"
								+ "<!ELEMENT word (#PCDATA)>\n"
								+ "<!ATTLIST word id ID #REQUIRED>"));
			}
		});

		set = new TreeSet<String>();
		xmlErrorSet = new HashSet<String>();
	}

	public static void main(String[] args) throws ClassNotFoundException,
			IOException, InstantiationException, IllegalAccessException,
			SAXException, ParserConfigurationException {
		DownloadMMaxData down = new DownloadMMaxData();
		ResultSet basedataRS = null;
		BasedataIdChecker b = new BasedataIdChecker();

		// Basedata checks end here
		b.xmlErrorSet.clear();
		// Markable checks start here

		ResultSet markableRS = null;
		try {
			SQLDatabaseManager sdm = new SQLDatabaseManager();
			sdm.setConnection();
			SQLFunctions sf = new SQLFunctions(sdm);
			markableRS = sf.getAllMarkablesInDatabase();
			Reader r = null;

			// output in sorted order
			// int counter = 0;
			Document doc;
			int counter = 0;

			// long time1 = System.currentTimeMillis();
			int unordered = 0;
			while (markableRS.next()) {
				r = markableRS.getCharacterStream(1);
				String id = markableRS.getString(2);
				doc = b.parseIds(r, id);
				if (b.checkSentenceOrder(doc, id)) {
					++unordered;
				}
				// if (doc != null) {
				// b.set.addAll(b.getWordIdSet(doc));
				// }
				++counter;
				if (counter % 1000 == 0) {
					System.out.println(counter);
				}

			}
			System.out.println(unordered);

			b.getProjectInformation(sdm);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (basedataRS != null) {
				try {
					basedataRS.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	
	/**
	 * Extracts the higest and lowest number of an string like
	 * "word_123..word_321, word_456".
	 * 
	 * @param span
	 * @return Range with the highest and lowest number found
	 */
	private Range getEnclosingAnnotationRange(String span) {

		float lowestNumber = Float.MAX_VALUE;
		float highestNumber = -1;

		Pattern numberPattern = Pattern.compile(PATTERN_NUMBER);
		// Pattern wordAndNumberPattern =
		// Pattern.compile(PATTERN_WORD_AND_NUMBER);

		Matcher numberMatcher = numberPattern.matcher(span);
		while (numberMatcher.find()) {

			String number = numberMatcher.group();
			float value = Float.parseFloat(number);
			if (value < lowestNumber) {
				lowestNumber = value;
			}
			if (value > highestNumber) {
				highestNumber = value;
			}
		}
		Range range = new Range();
		range.setBegin(lowestNumber);
		range.setEnd(highestNumber);

		return range;
	}

	private void getProjectInformation(SQLDatabaseManager sdm)
			throws SQLException {
		SQLFunctions sf = new SQLFunctions(sdm);
		for (String uri : xmlErrorSet) {
			System.out.println("Basedata uri: " + uri);
			ResultSet affectedRS = sf.getProjectInfo(uri);
			while (affectedRS.next()) {
				System.out.println("Affected project: "
						+ affectedRS.getString(1));
				String userID = affectedRS.getString(2);
				ResultSet userRS = sf.getCompleteUserName(userID);
				if (userRS.first()) {
					System.out.println("Project belongs to "
							+ userRS.getString(1));
				} else {
					System.out.println("User ID " + affectedRS.getString(1)
							+ " not assigned!");
				}
			}
		}
	}

	private boolean checkSentenceOrder(Document sentenceMarkableDoc, String id) {
		NodeList markableList = sentenceMarkableDoc
				.getElementsByTagName(ELEMENT_MARKABLE);

		float before = Float.NEGATIVE_INFINITY;
		float current;
		for (int i = 0; i < markableList.getLength(); i++) {
			NamedNodeMap attributes = markableList.item(i).getAttributes();
			Node spanNode = attributes.getNamedItem(ATTRIBUTE_SPAN);
			String spanValue = spanNode.getNodeValue();
			Range sentenceRange = getEnclosingAnnotationRange(spanValue);

			current = sentenceRange.getBegin();
			if (current < before) {
				return false;
			}

			before = current;

			// TODO setzt voraus, dass sentences aufsteigend in markable
			// List liegen - sicherstellen daß das so ist, ev sortieren

		}
		return true;
	}

	private Document parseIds(Reader r, String uri) throws IOException {
		Document doc = null;
		try {
			doc = builder.parse(new InputSource(r));
		} catch (SAXException e) {
			if (e
					.getMessage()
					.equals(
							"XML document structures must start and end within the same entity.")) {
				xmlErrorSet.add(uri);
			} else {
				e.printStackTrace();
			}
		}
		return doc;
	}

	private Set<String> getWordIdSet(Document basedataDoc) {

		Set<String> keys = new TreeSet<String>();
		NodeList wordList = basedataDoc.getElementsByTagName(ELEMENT_WORD);

		for (int i = 0; i < wordList.getLength(); i++) {
			NamedNodeMap attributes = wordList.item(i).getAttributes();
			Node idNode = attributes.getNamedItem(ATTRIBUTE_ID);
			String id = idNode.getNodeValue();
			keys.add(id);
		}

		return keys;
	}

}
