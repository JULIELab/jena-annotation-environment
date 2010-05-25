/** 
 * JDBCLogHandler.java
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
 * 
 */

package de.julielab.annoenv.utils;

import java.util.logging.*;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.*;

import javax.xml.parsers.ParserConfigurationException;

import de.julielab.annoenv.db.sql.SQLDatabaseManager;
import de.julielab.annoenv.db.sql.SQLFunctions;

/**
 * This is our logger that writes to the DB (log table)
 */
public class JDBCLogHandler extends Handler {

	Connection connection;

	SQLDatabaseManager sdm;

	/**
	 * A SQL statement used to insert into the log table.
	 */
	protected final static String insertSQL = "insert into log (level,logger,message,sequence,"
			+ "sourceClass,sourceMethod,threadID,timeEntered)"
			+ "values(?,?,?,?,?,?,?,?)";

	/**
	 * A SQL statement used to clear the log table
	 */
	protected final static String clearSQL = "delete from log;";

	/**
	 * A PreparedStatement object used to hold the main insert statement.
	 */
	protected PreparedStatement prepInsert;

	/**
	 * A PreparedStatement object used to hold the clear statement.
	 */
	protected PreparedStatement prepClear;

	/**
	 * @param driverString
	 *            The JDBC driver to use.
	 * @param connectionString
	 *            The connection string that specifies the database to use.
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws AnnoPlatformException
	 * @throws ParserConfigurationException
	 */
	public JDBCLogHandler() throws SQLException, InstantiationException,
			IllegalAccessException, ClassNotFoundException,
			ParserConfigurationException {

		sdm = new SQLDatabaseManager();
		sdm.setConnection();

		this.connection = sdm.getConnection();
		prepInsert = connection.prepareStatement(insertSQL);
		prepClear = connection.prepareStatement(clearSQL);
	}

	/**
	 * Internal method used to truncate a string to a specified width. Used to
	 * ensure that SQL table widths are not exceeded.
	 * 
	 * @param str
	 *            The string to be truncated.
	 * @param length
	 *            The maximum length of the string.
	 * @return The string truncated.
	 */
	static public String truncate(String str, int length) {
		if (str.length() < length)
			return str;
		return (str.substring(0, length));
	}

	/**
	 * Overridden method used to capture log entries and put them into a JDBC
	 * database.
	 * 
	 * @param record
	 *            The log record to be stored.
	 */
	public void publish(LogRecord record) {
		// first see if this entry should be filtered out
		if (getFilter() != null) {
			if (!getFilter().isLoggable(record))
				return;
		}

		/*
		 * now store the log entry into the table set as timeEntered the DB
		 * system time, this has first to be retrieved with...
		 */
		try {

			String logMessage = record.getMessage().toString();

			// get info on an exception
			String logException = "";
			String throwableMessage="";
			Throwable throwable = record.getThrown();

			if (throwable != null) {
				if (logMessage.length()>0)
					logMessage += "\n";
				throwableMessage = ExceptionUtils.getCausesFromException(throwable);	
//				throwableMessage = (throwable.getMessage()!=null)?throwable.getMessage():"";
//				if (throwable.getCause()!=null && throwable.getCause().getMessage()!=null)
//					throwableMessage += ": " + throwable.getCause().getMessage();
			
				StringWriter sw = new StringWriter();
				throwable.printStackTrace(new PrintWriter(sw));
				logException = "\n" + sw.toString();
			}

			String dbMessage = logMessage   + throwableMessage+ logException; 

			prepInsert.setInt(1, record.getLevel().intValue());
			prepInsert.setString(2, truncate(record.getLoggerName(), 63));
			prepInsert.setString(3, truncate(dbMessage, 255));
			prepInsert.setLong(4, record.getSequenceNumber());
			prepInsert.setString(5, truncate(record.getSourceClassName(), 63));
			prepInsert.setString(6, truncate(record.getSourceMethodName(), 31));
			prepInsert.setInt(7, record.getThreadID());
			Timestamp db_now = (new SQLFunctions(sdm)).getDBSystemTime();
			prepInsert.setTimestamp(8, db_now);
			prepInsert.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Error accessing logging database!");
			e.printStackTrace();
		}

	}

	

	
	/**
	 * Called to close this log handler.
	 */
	public void close() {
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			System.err.println("Error accessing logging database!");
			e.printStackTrace();
		}
	}

	/**
	 * Called to clear all log entries from the database.
	 */
	public void clear() {
		try {
			prepClear.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Error accessing logging database!");
			e.printStackTrace();
		}
	}

	/**
	 * Not really used, but required to implement a handler. Since all data is
	 * immediately sent to the database, there is no reason to flush.
	 */
	public void flush() {
	}
}