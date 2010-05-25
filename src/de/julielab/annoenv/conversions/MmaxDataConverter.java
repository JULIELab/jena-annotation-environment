/** 
 * MmaxDataConverter.java
 * 
 * Copyright (c) 2007, JULIE Lab. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0 
 *
 * Author: muehlhausen, kampe
 * 
 * Current version: 2.0
 * Since version:   0.7
 *
 * Creation date: Oct 20, 2006
 */

package de.julielab.annoenv.conversions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 
 * Class to convert an Mmax AL (active learning) Session to the T2 or IOB format
 */
public class MmaxDataConverter {

	// "word_".length()
	private static final int WORD_DELIMITER = 5;

	public final static String ELEMENT_MARKABLE = "markable";

	public final static String ELEMENT_WORD = "word";

	public final static String ATTRIBUTE_SPAN = "span";

	public final static String ATTRIBUTE_ID = "id";

	public final static String ATTRIBUTE_MMAXLEVEL = "mmax_level";

	/*
	 * KT (01.08.2007) these attributes are ignored during export:
	 * ATTRIBUTE_SPAN, ATTRIBUTE_ID, ATTRIBUTE_MMAXLEVEL
	 */

	/**
	 * Marks the beginning of a sentence block in an t2.
	 */
	public final static String MARKER_SENTENCE = "SENTENCE";

	public final static String IOB_I = "I-";

	public final static String IOB_B = "B-";

	public final static String IOB_O = "O";

	public final static String T2_O = "O";

	/**
	 * Default postag used when no pos tag is avaiable due to edited basedata.
	 */
	private final static String POSTAG_DEFAULT = "_NN";

	private final static String PATTERN_NUMBER = "\\d+(\\.\\d+)?";

	private final static String PATTERN_WORD = ".+_";

	private final static String PATTERN_POS = "_.+";

	/**
	 * Seperator inside the word-pos-annotation lines of t2.
	 */
	public final static String SEPERATOR_T2 = " ";

	/**
	 * Seperator inside the sentence header of t2.
	 */
	public final static String SEPERATOR_SENTENCE_HEADER = " ";

	/**
	 * Seperator between the sentence blocks of t2.
	 */
	public final static String SEPERATOR_T2_SENTENCES = "\n";

	/**
	 * Seperator between tokens and annotations
	 */
	public final static String SEPERATOR_IOB = "\t";

	/**
	 * Seperator between the sentence blocks of IOB.
	 */
	public final static String SEPERATOR_IOB_SENTENCES = "\n";

	/**
	 * Converts the Mmax session data to IOB
	 * 
	 * @since 1.6
	 */
	public void convertToIOB(File outputFile, Document markableDoc,
			Document basedataDoc, List<String> priolist,
			Document sentenceMarkableDoc, boolean addIOBLabels, String bdUri) {

		Map<Float, Anno> idAnnoMap = getIdAnnoMap(markableDoc, basedataDoc,
				priolist);
		List<Tidsid> tidsidList = generateTidSidList(sentenceMarkableDoc);

		checkOverlappingAnnotations(markableDoc, tidsidList, basedataDoc, bdUri);

		StringBuilder conversionOutput = new StringBuilder();

		NavigableMap<Float, String> wordMap = getIdWordMap(basedataDoc);

		for (int i = 0; i < tidsidList.size(); i++) {

			Tidsid tidsid = tidsidList.get(i);
			int offset = tidsid.getOffset();
			int distance = tidsid.getDistance();

			int startId = offset;
			int endId = offset + distance;

			List<Float> wordIdList = getWordIdSubList(basedataDoc, startId,
					endId);
			// List<Float> wordIdList2 = getWordIdSubList(wordMap, startId,
			// endId);
			// System.out.println(wordIdList.size() + ", "+wordIdList2.size());

			// int count = 0;
			// for (Float id : wordIdList) {
			// Float id2 = wordIdList2.get(count);
			// id.equals(id2);
			// count++;
			// }

			for (int j = 0; j < wordIdList.size(); j++) {

				Float wordId = wordIdList.get(j);
				String word = wordMap.get(wordId);
				Anno anno = idAnnoMap.get(wordId);

				String IOB_B = MmaxDataConverter.IOB_B;
				String IOB_I = MmaxDataConverter.IOB_I;
				if (!addIOBLabels) {
					IOB_B = "";
					IOB_I = "";
				}

				if (j == 0 && i != 0) {
					conversionOutput.append(SEPERATOR_IOB_SENTENCES);
				}

				if (anno.getBegin() == wordId) {
					conversionOutput.append(word + SEPERATOR_IOB + IOB_B
							+ anno.getType() + "\n");
				} else if (anno.getType().equals(T2_O)) {
					conversionOutput
							.append(word + SEPERATOR_IOB + IOB_O + "\n");
				} else {
					conversionOutput.append(word + SEPERATOR_IOB + IOB_I
							+ anno.getType() + "\n");
				}
			}
		}
		writeBufferToFile(conversionOutput, outputFile);
	}

	/**
	 * Converts the Mmax session data to t2.
	 */
	// TODO annotationen duerfen nicht ueber das Satzende hinaus gehen !
	public void convertToT2(File outputFile, Document markableDoc,
			Document basedataDoc, List<String> priolist,
			List<Tidsid> tidsidList, String posDataPath, String bdUri) {

		Map<Float, Anno> idAnnoMap = getIdAnnoMap(markableDoc, basedataDoc,
				priolist);
		Map<Float, String> idWordAndPOSMap = getIdWordAndPOSMap(basedataDoc,
				tidsidList, posDataPath);
		List<String> sentenceHeaderList = getSentenceHeaderList(tidsidList,
				basedataDoc);

		checkOverlappingAnnotations(markableDoc, tidsidList, basedataDoc, bdUri);

		StringBuffer conversionOutput = new StringBuffer();

		for (int i = 0; i < tidsidList.size(); i++) {

			conversionOutput.append(sentenceHeaderList.get(i));
			Tidsid tidsid = tidsidList.get(i);

			int startId = tidsid.getOffset();
			int endId = tidsid.getOffset() + tidsid.getDistance();
			List<Float> wordIdList = getWordIdSubList(basedataDoc, startId,
					endId);

			for (Float id : wordIdList) {
				String wordAndPOS = idWordAndPOSMap.get(id);
				String word = extractWord(wordAndPOS);
				String pos = extractPOS(wordAndPOS);
				Anno anno = idAnnoMap.get(id);
				conversionOutput.append(word + SEPERATOR_T2 + pos
						+ SEPERATOR_T2 + anno.getType() + "\n");
			}
			conversionOutput.append(SEPERATOR_T2_SENTENCES);
		}

		writeBufferToFile(conversionOutput, outputFile);
	}

	/**
	 * Checks if annotations span over more than one sentence. This alogrithm
	 * assumes that if there is no enclosing tidsid object found in the tidsid
	 * list that encloses the range of an annotation.
	 * 
	 * @since 1.6
	 */
	private void checkOverlappingAnnotations(Document markableDoc,
			List<Tidsid> tidsidList, Document basedataDoc, String bdUri) {

		NodeList markableList = markableDoc
				.getElementsByTagName(ELEMENT_MARKABLE);

		for (int i = 0; i < markableList.getLength(); i++) {
			NamedNodeMap attributes = markableList.item(i).getAttributes();
			Node spanNode = attributes.getNamedItem(ATTRIBUTE_SPAN);
			String spanValue = spanNode.getNodeValue();
			Range annotationRange = getEnclosingAnnotationRange(spanValue);

			Iterator<Tidsid> iter = tidsidList.iterator();
			Tidsid intersectingTidsid = null;
			while (iter.hasNext()) {

				Tidsid tidsid = iter.next();
				if (tidsid.intersectsRage(annotationRange)) {
					intersectingTidsid = tidsid;
				}
				if (intersectingTidsid != null) {
					String sentence = getSentence(intersectingTidsid,
							basedataDoc);
					throw new IllegalStateException(
							"Sentence overlapping annotation found!"
									+ "\nBasedata: " + bdUri + ", "
									+ "Sentence:\n\"" + sentence + "\"");
				}
			}
		}
	}

	/**
	 * Get the sentence associated with the tidsid
	 * 
	 * @param intersectingTidsid
	 * @return sentence implicitly contained in tidsid
	 * 
	 * @since 1.6
	 */
	private String getSentence(Tidsid intersectingTidsid, Document basedataDoc) {

		StringBuffer buffer = new StringBuffer();

		int beginId = intersectingTidsid.getOffset();
		int endId = beginId + intersectingTidsid.getDistance();

		NavigableMap<Float, String> wordIdMap = getIdWordMap(basedataDoc);
		List<Float> wordList = getWordIdSubList(basedataDoc, beginId, endId);

		Iterator<Float> iter = wordList.iterator();

		if (iter.hasNext()) {
			Float id = iter.next();
			String word = wordIdMap.get(id);
			buffer.append(word);
		}

		while (iter.hasNext()) {
			Float id = iter.next();
			String word = wordIdMap.get(id);
			buffer.append(" " + word);
		}

		return buffer.toString();
	}

	/**
	 * Generates tidsidList out of a sentence markable XML file
	 * 
	 * @param sentenceMarkable
	 * @return tidsid list
	 * 
	 * @since 1.6
	 */
	private List<Tidsid> generateTidSidList(Document sentenceMarkableDoc) {

		List<Tidsid> tidsidList = new ArrayList<Tidsid>();

		NodeList markableList = sentenceMarkableDoc
				.getElementsByTagName(ELEMENT_MARKABLE);

		for (int i = 0; i < markableList.getLength(); i++) {
			NamedNodeMap attributes = markableList.item(i).getAttributes();
			Node spanNode = attributes.getNamedItem(ATTRIBUTE_SPAN);
			String spanValue = spanNode.getNodeValue();
			Range sentenceRange = getEnclosingAnnotationRange(spanValue);

			Tidsid tidsid = new Tidsid();
			tidsid.setTid(0);

			// TODO setzt voraus, dass sentences aufsteigend in markable
			// List liegen - sicherstellen das das so ist, ev sortieren
			tidsid.setSid(i);

			// word ids begin with index 1, offset with 0
			// assumes, that sentences only begin at an integer index (i.e.
			// basedata editing was not adopted to sentence markable file)
			tidsid.setOffset((int) (sentenceRange.getBegin() - 1));
			tidsid.setDistance((int) ((sentenceRange.getEnd()
					- sentenceRange.getBegin() + 1)));
			tidsidList.add(i, tidsid);
		}

		return tidsidList;
	}

	/**
	 * Writes buffer to file
	 * 
	 * @param buffer
	 * @param file
	 */
	private void writeBufferToFile(CharSequence buffer, File file) {

		try {
			PrintWriter printWriter = new PrintWriter(file);
			printWriter.append(buffer);
			printWriter.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException("could not write buffer to file", e);
		}
	}

	/**
	 * Exctracts the word form the pattern 'word_POS'.
	 * 
	 * @param wordAndPOS
	 * @return
	 */
	private String extractWord(String wordAndPOS) {

		Pattern wordPattern = Pattern.compile(PATTERN_WORD);
		Matcher wordMatcher = wordPattern.matcher(wordAndPOS);
		wordMatcher.find();
		String wordWithUnderscore = wordMatcher.group();
		return wordWithUnderscore.substring(0, wordWithUnderscore.length() - 1);
	}

	/**
	 * Extracts the POS form the pattern 'word_POS'.
	 * 
	 * @param wordAndPOS
	 * @return
	 */
	private String extractPOS(String wordAndPOS) {

		Pattern posPattern = Pattern.compile(PATTERN_POS);
		Matcher posMatcher = posPattern.matcher(wordAndPOS);
		posMatcher.find();
		return posMatcher.group().substring(1);
	}

	/**
	 * Generates a List form the tidSidFile.
	 * 
	 * @param tidSidFile
	 * @return sentence header List
	 * 
	 * @since 1.6
	 */
	private List<String> getSentenceHeaderList(List<Tidsid> tidsidList,
			Document basedataDoc) {
		List<String> sentenceHeaderList = new ArrayList<String>();

		for (Tidsid tidsid : tidsidList) {
			int startId = tidsid.getOffset();
			int endId = tidsid.getOffset() + tidsid.getDistance();

			int length = getWordIdSubList(basedataDoc, startId, endId).size();
			String sentenceHeader = MARKER_SENTENCE + SEPERATOR_SENTENCE_HEADER
					+ tidsid.getTid() + SEPERATOR_SENTENCE_HEADER
					+ tidsid.getSid() + SEPERATOR_SENTENCE_HEADER + length
					+ "\n";
			sentenceHeaderList.add(sentenceHeader);
		}
		return sentenceHeaderList;
	}

	/**
	 * Gets an flattend mapping form word ids to annotations.
	 * 
	 * @param markableDoc
	 * @param basedataDoc
	 * @param prioList
	 * @return
	 * @since 1.6
	 */
	private Map<Float, Anno> getIdAnnoMap(Document markableDoc,
			Document basedataDoc, List<String> prioList) {

		Map<Float, Anno> idAnnoMap = getInitialIdAnnoMap(basedataDoc);
		// TODO hier einbauen: Prüfung, ob annotation über das Satzende hinaus
		// geht: Achtung: TID und SID muß geprüft werden !

		NodeList markableList = markableDoc
				.getElementsByTagName(ELEMENT_MARKABLE);

		for (int i = 0; i < markableList.getLength(); i++) {
			NamedNodeMap attributes = markableList.item(i).getAttributes();
			Node spanNode = attributes.getNamedItem(ATTRIBUTE_SPAN);
			String spanValue = spanNode.getNodeValue();
			Range annotationRange = getEnclosingAnnotationRange(spanValue);

			// if (annotationRange)
			List<String> annotationList = getAnnotationList(attributes);
			putAnnosInWordMap(idAnnoMap, annotationList, annotationRange,
					prioList);
		}

		return idAnnoMap;
	}

	/**
	 * Generates a map that maps from basedata ids to words.
	 * 
	 * @param baseDataDoc
	 * @return Map that maps basedata ids to words
	 * 
	 * @since 1.6
	 */
	private NavigableMap<Float, String> getIdWordMap(Document basedataDoc) {

		NavigableMap<Float, String> wordMap = new TreeMap<Float, String>();
		NodeList wordList = basedataDoc.getElementsByTagName(ELEMENT_WORD);

		for (int i = 0; i < wordList.getLength(); i++) {
			Node currentElement = wordList.item(i);
			NamedNodeMap attributes = currentElement.getAttributes();
			Node idNode = attributes.getNamedItem(ATTRIBUTE_ID);
			String id = idNode.getNodeValue();
			Float idFloat = Float.parseFloat(id.substring(WORD_DELIMITER));
			String word = currentElement.getFirstChild().getNodeValue();
			wordMap.put(idFloat, word);
		}

		return wordMap;
	}

	/**
	 * Generates a map that maps from words in the basedata file to POS tag. The
	 * basedata contains sentences form several texts. A tidSidFile (tid = Text
	 * ID, sid = Sentence ID) is used to get the corresponnding posdata file
	 * (filename of the posdata file is equal to tid, the used sentence is the
	 * sid-th sentence in the posdata file, the posdata file has one sencence
	 * per line beginning with sid 0).
	 * 
	 * @param basedataDoc
	 * @param tidsidList
	 * @param posDataPath
	 * @return Map with a mapping from words to posTags
	 * 
	 * @since 1.6
	 */
	private Map<Float, String> getIdWordAndPOSMap(Document basedataDoc,
			List<Tidsid> tidsidList, String posDataPath) {

		Map<Float, String> idWordAndPOSMap = new HashMap<Float, String>();
		Map<Float, String> wordMap = getIdWordMap(basedataDoc);

		for (int i = 0; i < tidsidList.size(); i++) {

			Tidsid tidsid = tidsidList.get(i);
			// System.out.println("getting pos for word id: " + wordId);
			int tid = tidsid.getTid();
			int sid = tidsid.getSid();
			int offset = tidsid.getOffset();
			int distance = tidsid.getDistance();

			List<String> wordAndPOSList = getWordAndPOSList(tid, sid,
					posDataPath);

			int startId = offset;
			int endId = offset + distance;

			List<Float> wordIdList = getWordIdSubList(basedataDoc, startId,
					endId);

			int posCounter = 0;

			for (int j = 0; j < wordIdList.size(); j++) {

				Float wordId = wordIdList.get(j);
				String word = wordMap.get(wordId);
				// System.out.println("word: " + word + "\t wordid: " + wordId);
				String wordAndPOSStr = null;

				if (Math.floor(wordId) == wordId) {
					// System.out.println("getting pos for word id: " + wordId);
					wordAndPOSStr = word + "_"
							+ extractPOS(wordAndPOSList.get(posCounter));
					// System.out.println("wordAndPOsStr: " + wordAndPOSStr);
					posCounter++;
				} else {
					wordAndPOSStr = word + POSTAG_DEFAULT;
				}
				idWordAndPOSMap.put(wordId, wordAndPOSStr);
			}
		}
		return idWordAndPOSMap;
	}

	/**
	 * Gets a List with word IDs in the range startId and endId.
	 * 
	 * @param basedataDoc
	 * @param startId
	 * @param endId
	 * @return List with word IDs
	 * 
	 * @since 1.6
	 */
	private List<Float> getWordIdSubList(Document basedataDoc, int startId,
			int endId) {

		List<Float> list = getWordIdList(basedataDoc);
		List<Float> subList = new ArrayList<Float>();

		for (Float item : list) {
			if (item >= (startId + 1) && item <= endId) {
				subList.add(new Float(item));
			}
		}
		return subList;
	}

	/**
	 * Gets a List of the POS tags that are in the file(or text) tid in line(or
	 * sentence) sid.
	 * 
	 * @param tid
	 * @param sid
	 * @param posDataPath
	 * @return List with POS tags
	 */
	private ArrayList<String> getWordAndPOSList(int tid, int sid,
			String posDataPath) {

		ArrayList<String> list = new ArrayList<String>();

		FileInputStream stream;
		BufferedReader bufReader = null;
		try {
			stream = new FileInputStream(posDataPath + File.separator + tid);
			Reader in = new InputStreamReader(stream);
			bufReader = new BufferedReader(in);

			String line = null;
			for (int i = -1; i < sid; i++) {
				line = bufReader.readLine();
			}

			StringTokenizer tokenizer = new StringTokenizer(line);
			while (tokenizer.hasMoreTokens()) {
				String wordAndPOS = tokenizer.nextToken();
				list.add(wordAndPOS);
			}

		} catch (Exception e) {
			throw new RuntimeException("error getting word and POS lists", e);
		} finally {
			if (bufReader != null) {
				try {
					bufReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	/**
	 * Puts the annotations into the wordMap, which maps words to annotations.
	 * 
	 * @param wordMap
	 * @param annotationList
	 */
	private void putAnnosInWordMap(Map<Float, Anno> wordMap,
			List<String> annotationList, Range range, List<String> prioList) {

		String highestPrioAnno = getHighestPrioAnno(annotationList, prioList);
		if (highestPrioAnno == null) {
			return;
		}

		List<Float> containedKeys = getContaindKeys(wordMap, range);
		for (int i = 0; i < containedKeys.size(); i++) {

			Float key = containedKeys.get(i);
			Anno oldAnno = wordMap.get(key);
			Anno anno = new Anno(range.getBegin(), range.getEnd(),
					highestPrioAnno);

			if (oldAnno.getSpan() != 0) {

				if (anno.isNestedIn(oldAnno)) {
					i = containedKeys.size();
					continue;
				}
				if (anno.intersects(oldAnno)) {
					// here could an Exception be thown, if intersection of
					// annotations should not be allowd. But pay attention on
					// reporting this
					// exception in the user GUI to avoid time consuming
					// research if the systems do not run due to such
					// intersection annotations.
				}
				if (anno.getSpan() == oldAnno.getSpan()) {
					if (!anno.hasHigherPrio(oldAnno, prioList)) {
						continue;
					}
				}
			}
			wordMap.put(key, anno);
		}
	}

	/**
	 * Returns Sid of an Id
	 * 
	 * @param tidsidList
	 * @param id
	 * @return sid of id
	 */
	private int getSidOfId(List<Tidsid> tidsidList, Float id) {

		for (Tidsid tidsid : tidsidList) {
			if (tidsid.contains(id)) {
				return tidsid.getSid();
			}
		}
		return -1;
	}

	/**
	 * Gets the keys that are contained in the wordMap that match the intervall
	 * of Floats denoted in range.
	 * 
	 * @param wordMap
	 * @param range
	 * @return List of contained keys
	 */
	private List<Float> getContaindKeys(Map<Float, Anno> wordMap, Range range) {

		List<Float> containedKeys = new ArrayList<Float>();
		Set<Float> keySet = wordMap.keySet();
		Iterator<Float> keyIter = keySet.iterator();

		while (keyIter.hasNext()) {
			Float key = keyIter.next();
			if (key >= range.getBegin() && key <= range.getEnd()) {
				containedKeys.add(key);
			}
		}
		return containedKeys;
	}

	/**
	 * Searches the annotation with the higest priority.
	 * 
	 * @param annotationList
	 * @param prioList
	 * @return annotation with highest priority
	 */
	private String getHighestPrioAnno(List<String> annotationList,
			List<String> prioList) {

		int lowestIndex = Integer.MAX_VALUE;
		for (int i = 0; i < annotationList.size(); i++) {

			String annotation = annotationList.get(i);
			if (prioList.contains(annotation)) {

				int currentIndex = prioList.indexOf(annotation);
				if (currentIndex < lowestIndex) {
					lowestIndex = currentIndex;
				}
			}
		}
		String highestPrioAnno = null;
		if (lowestIndex < prioList.size()) {
			highestPrioAnno = prioList.get(lowestIndex);
		}
		return highestPrioAnno;
	}

	/**
	 * Extracts annotations from the attributes of an (XML-)element.
	 * 
	 * @param attributes
	 * @return List with annotations
	 */
	private List<String> getAnnotationList(NamedNodeMap attributes) {

		List<String> annotationList = new ArrayList<String>();

		for (int j = 0; j < attributes.getLength(); j++) {
			Node attributeNode = attributes.item(j);
			String nodeName = attributeNode.getNodeName();

			// modified by KT (01.08.2007): igores mmax_level attribute now also
			if ((nodeName.equals(ATTRIBUTE_ID))
					|| (nodeName.equals(ATTRIBUTE_SPAN))
					|| (nodeName.equals(ATTRIBUTE_MMAXLEVEL))) {
				continue;
			}
			String annotation = attributeNode.getNodeValue();
			annotationList.add(annotation);
		}

		return annotationList;
	}

	/**
	 * Fills a map with the word from the basedata file with the tokens as keys
	 * and "O" as values.
	 * 
	 * @param basedataFile
	 * @return Map with tokens as keys and "O" as values
	 * @since 1.6
	 */
	private Map<Float, Anno> getInitialIdAnnoMap(Document basedataDoc) {

		List<Float> wordList = getWordIdList(basedataDoc);
		Map<Float, Anno> wordMap = new HashMap<Float, Anno>();
		for (Float word : wordList) {
			Anno anno = new Anno(0, 0, T2_O);
			wordMap.put(word, anno);
		}
		return wordMap;
	}

	/**
	 * Extracts the higest and lowest number of an string like
	 * "word_123..word_321, word_456".
	 * 
	 * @param span
	 * @return Range with the highest and lowest number found
	 */
	public Range getEnclosingAnnotationRange(String span) {

		float lowestNumber = Float.MAX_VALUE;
		float highestNumber = -1;

		Pattern numberPattern = Pattern.compile(PATTERN_NUMBER);

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

	/**
	 * Extracts the word numbers of the basedata file.
	 * 
	 * @param basedataFile
	 * @return List with the word numbers of the basedata filled with strings
	 * @since 1.6
	 */
	private List<Float> getWordIdList(Document basedataDoc) {

		List<Float> keys = new ArrayList<Float>();

		NodeList wordList = basedataDoc.getElementsByTagName(ELEMENT_WORD);

		for (int i = 0; i < wordList.getLength(); i++) {
			NamedNodeMap attributes = wordList.item(i).getAttributes();
			Node idNode = attributes.getNamedItem(ATTRIBUTE_ID);
			String id = idNode.getNodeValue();
			Float idFloat = Float.parseFloat(id.substring(WORD_DELIMITER));
			keys.add(idFloat);
		}

		return keys;
	}

	/**
	 * just for DEBUGGING
	 * 
	 * @param map
	 */
	public void printSortedWordMap(Map<Float, Anno> map) {

		Set<Float> keys = map.keySet();

		List<Float> keyList = new ArrayList<Float>();
		Iterator<Float> iter = keys.iterator();
		while (iter.hasNext()) {
			Float keyItem = iter.next();
			keyList.add(new Float(keyItem));
		}
		Collections.sort(keyList);
		for (int i = 0; i < keyList.size(); i++) {
			Anno anno = map.get(keyList.get(i));
			System.out
					.println("word: " + keyList.get(i) + "\t\tanno:  " + anno);
		}
	}

	/**
	 * just for DEBUGGING
	 * 
	 * @param map
	 */
	public void printIdWordMap(Map<Float, String> map) {
		Set<Float> keys = map.keySet();

		List<Float> keyList = new ArrayList<Float>();
		Iterator<Float> iter = keys.iterator();
		while (iter.hasNext()) {
			Float keyItem = iter.next();
			keyList.add(new Float(keyItem));
		}
		Collections.sort(keyList);
		for (int i = 0; i < keyList.size(); i++) {
			String word = map.get(keyList.get(i));
			System.out.println("word_id: " + keyList.get(i) + "\t\tword:  "
					+ word);
		}
	}

	/**
	 * just for DEBUGGING
	 * 
	 * @param map
	 */
	public void printIdWordAndPOSMap(Map<Float, String> map) {

		Set<Float> keys = map.keySet();

		List<Float> keyList = new ArrayList<Float>();
		Iterator<Float> iter = keys.iterator();
		while (iter.hasNext()) {
			Float keyItem = iter.next();
			keyList.add(new Float(keyItem));
		}
		Collections.sort(keyList);
		for (int i = 0; i < keyList.size(); i++) {
			String wordAndPOS = map.get(keyList.get(i));
			System.out.println("word_id: " + keyList.get(i)
					+ "\t\tWordAndPOS:  " + wordAndPOS);
		}
	}
}
