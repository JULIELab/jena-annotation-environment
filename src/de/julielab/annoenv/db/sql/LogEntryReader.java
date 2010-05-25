/** 
 * LogEntryReader.java
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
 */

package de.julielab.annoenv.db.sql;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;


import javax.xml.parsers.ParserConfigurationException;

public class LogEntryReader {

	static final public int LEVEL_FIELD = 0;

	static final public int MESSAGE_FIELD = 1;

	static final public int SOURCECLASS_FIELD = 2;

	static final public int SOURCEMETHOD_FIELD = 3;

	static final public int SEQUENCE_FIELD = 4;

	static final public String[] filterMapping = { "level", "message",
			"sourceClass", "sourceMethod", "sequence" };

	static final public int TIMESEQUENCE_SORT = 0;

	static final public int LEVEL_SORT = 1;

	static final public String[] sortMapping = {
			"timeEntered DESC, sequence DESC",
			"level DESC, timeEntered DESC, sequence DESC" };

	static final public int DEFAULT_LIMIT = 100;

	private HashMap<String, String> filters = new HashMap<String, String>();

	private int sort = TIMESEQUENCE_SORT;

	private int limit = DEFAULT_LIMIT;

	private String startDate = null;

	private String endDate = null;

	private SQLDatabaseManager sdm = null;

	/**
	 * constructor that creates new connection to database
	 * 
	 * @throws Exception
	 */
	public LogEntryReader() throws Exception {
		sdm = new SQLDatabaseManager();
		sdm.setConnection();
	}

	/**
	 * constructor that reuses provided database connection
	 * 
	 * @param sdm
	 *            the connection to the DB
	 * @throws Exception
	 */
	public LogEntryReader(SQLDatabaseManager sdm)  {
		this.sdm = sdm;
	}

	/**
	 * add a filter to the filter list. The respective filter results in a
	 * phrase "fieldname LIKE "value""
	 * 
	 * @param fieldConst
	 *            one of the constants for the fields, e.g. LEVEL_FIELD
	 * @param value
	 *            what should be filtered in this field, you can use all
	 *            wildcards applicable to MYSQL, especially the % wildcard
	 */
	public void setFilter(int fieldConst, String value) {
		filters.put(filterMapping[fieldConst], value);
	}

	/**
	 * set one of the predefined sorting variants, there are some constants
	 * defined for sorting
	 * 
	 * @param sortConst
	 */
	public void setSort(final int sortConst) {
		sort = sortConst;
	}

	/**
	 * set the limit, e.g. how many log entries will at most be retrieved
	 * 
	 * @param limit
	 */
	public void setLimit(final int limit) {
		this.limit = limit;
	}

	/**
	 * if you want to restrict the date for the log entries you can add both a
	 * starting and an ending date. Both have to be of type Calendar (year,
	 * month and day have to be set). To ignore one of the dates just set it
	 * "null". Both dates are used in inclusive mode (<= date1, >= date2)
	 * 
	 * @param date1
	 *            the starting date (inclusive)
	 * @param date2
	 *            the ending date (inclusive)
	 */
	public void setDate(Calendar date1, Calendar date2) {

		if (date1 != null) {
			// starting date set
			startDate = date1.get(date1.YEAR) + "-" + date1.get(date1.MONTH)
					+ "-" + date1.get(date1.DATE);
		}

		if (date2 != null) {
			// end date set
			endDate = date2.get(date2.YEAR) + "-" + date2.get(date2.MONTH)
					+ "-" + date2.get(date2.DATE);
		}
	}

	public void resetOptions() {
		// remove filters;
		filters.clear();

		// set default sort
		sort = TIMESEQUENCE_SORT;

		// set default limit
		limit = DEFAULT_LIMIT;

		// reset date
		startDate = null;
		endDate = null;
	}

	/**
	 * makes clauses for SQL command
	 * 
	 * @throws ParserConfigurationException
	 * 
	 */
	public ArrayList<LogEntry> getLogEntries() {
		// 1) make WHERE clause
		StringBuffer whereClause = new StringBuffer();

		// the filters
		for (String field : filters.keySet()) {
			final String value = filters.get(field);
			if (whereClause.length() > 0) {
				// for consecutive filters add AND
				whereClause.append(" AND ");
			}
			whereClause.append(field + " LIKE \"" + value + "\"");
		}

		// start date
		if (startDate != null) {
			if (whereClause.length() > 0) {
				// for consecutive filters add AND
				whereClause.append(" AND ");
			}
			whereClause.append("timeEntered >= \"" + startDate + "\"");
		}

		// end date
		if (endDate != null) {
			if (whereClause.length() > 0) {
				// for consecutive filters add AND
				whereClause.append(" AND ");
			}
			whereClause.append("timeEntered <= \"" + endDate + "\"");
		}

		if (whereClause.length() > 0) {
			// insert WHERE if anything was put into where clause
			whereClause.insert(0, " WHERE ");
		}

		// 2) make SORT clause
		String sortClause = " ORDER BY " + sortMapping[sort];

		// 3) make LIMIT clause
		String limitClause = " LIMIT " + limit;

		// 4) now get log entries from DB
		ArrayList<LogEntry> logs = (new SQLFunctions(sdm)).getLogEntries(
				whereClause.toString(), sortClause, limitClause);

		// System.out.println(logs);
		return logs;
	}

	/**
	 * just for debugging and for demonstration
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		Calendar x = new GregorianCalendar();
		x.set(2006, 11, 15);
		Calendar y = new GregorianCalendar();
		y.set(2006, 9, 15);

		LogEntryReader LER = null;
		try {
			LER = new LogEntryReader();
		} catch (Exception e) {
			e.printStackTrace();
		}
		LER.resetOptions();
		LER.setFilter(LER.MESSAGE_FIELD, "%#%");
		LER.setFilter(LER.SEQUENCE_FIELD, "3");
		LER.setDate(y, x);
		LER.setLimit(10);

		ArrayList<LogEntry> logs = LER.getLogEntries();

		// show results
		System.out.println("\nlogging entries: ");
		for (int i = 0; i < logs.size(); i++) {
			System.out.println(logs.get(i).toString());
		}

	}

}
