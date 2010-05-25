/** 
 * SQLDatabaseManager.java
 * 
 * Copyright (c) 2007, JULIE Lab. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0 
 *
 * Author: tomanek, kampe
 * 
 * Current version: 2.0
 * Since version:   0.7
 *
 * Creation date: Aug 01, 2006
 * 
 * The class manages the connection and query execution for an SQL-database
 */

package de.julielab.annoenv.db.sql;

import java.io.BufferedReader;
import java.io.File;

import java.io.FileWriter;
import java.io.IOException;

import java.io.Reader;
import java.io.StringReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.mysql.jdbc.Connection;

import de.julielab.annoenv.utils.AnnoEnvLogger;
import de.julielab.annoenv.utils.settings.DatabaseSettings;

public class SQLDatabaseManager {

	private static Logger logger = AnnoEnvLogger
			.getLogger("de.julielab.annoenv.db.sql.SQLDatabaseManager");

	DatabaseSettings dbSettings = new DatabaseSettings();

	String dbUsr = dbSettings.DB_USER;

	String dbPw = dbSettings.DB_PASSWORD;

	String dbUrl = dbSettings.DB_LOCATION;

	String DbDr = dbSettings.DB_DRIVER;

	private Connection cn;

	private Statement st;

	private DocumentBuilder builder;

	public SQLDatabaseManager() {
		// open a new connection to database
		setConnection();
		// System.out.println("New SQL connection established...");
		initDocumentBuilder();
	}

	private void initDocumentBuilder() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(false);
		factory.setNamespaceAware(false);
		try {
			this.builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			throw new RuntimeException("could not initialize document builder",
					e);
		}

		builder.setEntityResolver(new EntityResolver() {
			public InputSource resolveEntity(String publicId, String systemId)
					throws SAXException, IOException {
				if (systemId != null) {
					if (systemId.endsWith("words.dtd")) {
						return new InputSource(new StringReader(
								"<!ELEMENT words (word*)>\n"
										+ "<!ELEMENT word (#PCDATA)>\n"
										+ "<!ATTLIST word id ID #REQUIRED>"));
					} else if (systemId.endsWith("markables.dtd")) {
						return new InputSource(
								new StringReader(
										"<!ELEMENT markables (markable*)>\n"
												+ "<!ATTLIST markable id ID #REQUIRED>"));
					}
				}

				throw new SAXException("Unknown DTD!");
			}
		});
	}

	//TODO check whether should be removed
	protected void closeStatement() throws SQLException {
		st.close();
	}

	/**
	 * Connect the database and initialize the data field Connection
	 */
	public void setConnection() {
		// TODO check whether already connected, other wise do it
		try {
			Class.forName(DbDr).newInstance();
			cn = (Connection) DriverManager.getConnection(dbUrl, dbUsr, dbPw);
		} catch (InstantiationException e) {
			throw new RuntimeException(
					"DB connection could not be established", e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(
					"DB connection could not be established", e);
		} catch (SQLException e) {
			throw new RuntimeException(
					"DB connection could not be established", e);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(
					"DB connection could not be established", e);
		}

	}

	/**
	 * return the created connection
	 */
	public Connection getConnection() {
		return cn;
	}

	/**
	 * Executes the query. Returns a resultset.
	 */
	protected ResultSet executeQuery(String query) throws SQLException {
		st = cn.createStatement();
		ResultSet rs = null;
		rs = st.executeQuery(query);
		return rs;
	}

	/**
	 * Executes UPDATE, DELETE, INSERT
	 * 
	 * @param update
	 *            Query to be executed
	 * @return 0 if db returns nothing
	 */
	protected int executeUpdate(String update) throws SQLException {
		st = cn.createStatement();
		int response = 0;

		response = st.executeUpdate(update);
		return response;
	}

	/**
	 * This method is a drop-in replacement for the deprecated method
	 * getText(String, String, String, String)
	 * 
	 * @since 1.6
	 */
	public File getUnicodeText(String table, String field, String whereCond,
			String filename) {
		File text = new File(filename);
		ResultSet rs = null;
		try {
			rs = executeQuery("SELECT " + field + " FROM " + table + " WHERE "
					+ whereCond);

			if (rs.next()) {
				Reader reader = rs.getCharacterStream(field);
				FileWriter writer = new FileWriter(text);

				char[] buff = new char[8192];
				int len;
				while (0 < (len = reader.read(buff))) {
					writer.write(buff, 0, len);
				}

				writer.close();
				reader.close();
			}
			rs.close();

		} catch (SQLException e) {
			throw new RuntimeException("Retrieving text from database failed.",
					e);
		} catch (IOException e) {
			throw new RuntimeException("Retrieving text from database failed.",
					e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return text;
	}

	/**
	 * 
	 * Drop-in alternative for getText(String, String, String, String)
	 * 
	 * @param table
	 * @param field
	 * @param whereCond
	 * @since 1.6
	 */
	public String getTextAsString(String table, String field, String whereCond) {
		StringBuilder buffer = new StringBuilder(8192);
		ResultSet rs = null;
		try {
			rs = executeQuery("SELECT " + field + " FROM " + table + " WHERE "
					+ whereCond);

			if (rs.next()) {
				Reader r = rs.getCharacterStream(field);

				char[] buff = new char[8192];
				int len;
				while (0 < (len = r.read(buff))) {
					buffer.append(buff, 0, len);
				}
				r.close();
			}
			rs.close();

		} catch (SQLException e) {
			throw new RuntimeException("Retrieving text from database failed.",
					e);
		} catch (IOException e) {
			throw new RuntimeException("Retrieving text from database failed.",
					e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return buffer.toString();
	}

	/**
	 * 
	 * @param table
	 * @param field
	 * @param whereCond
	 * @return The contents of the field as a List of lines.
	 * @since 1.6
	 */
	public List<String> getTextAsList(String table, String field,
			String whereCond) {
		ArrayList<String> textList = new ArrayList<String>();
		ResultSet rs = null;
		try {
			rs = executeQuery("SELECT " + field + " FROM " + table + " WHERE "
					+ whereCond);

			if (rs.next()) {
				if (rs.getString(1) == null) {
					return textList;
				}
				BufferedReader r = new BufferedReader(rs
						.getCharacterStream(field));
				String line;
				while ((line = r.readLine()) != null) {
					textList.add(line);
				}
				r.close();
			}
			rs.close();

		} catch (SQLException e) {
			throw new RuntimeException("Retrieving text from database failed.",
					e);
		} catch (IOException e) {
			throw new RuntimeException("Retrieving text from database failed.",
					e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return textList;
	}

	/**
	 * @param table
	 * @param field
	 * @param whereCond
	 * @return
	 * 
	 * @since 1.6
	 */
	public Document fetchDocument(String table, String field, String whereCond) {
		ResultSet rs = null;
		Document doc = null;
		Reader r = null;
		try {
			rs = executeQuery("SELECT " + field + " FROM " + table + " WHERE "
					+ whereCond);
			if (rs.next()) {
				r = rs.getCharacterStream(field);
				doc = builder.parse(new InputSource(r));
			}
			rs.close();

		} catch (SQLException e) {
			throw new RuntimeException("Retrieving text from database failed.",
					e);
		} catch (IOException e) {
			throw new RuntimeException("Retrieving text from database failed.",
					e);
		} catch (SAXException e) {
			throw new RuntimeException("Retrieving text from database failed.",
					e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (r != null) {
				try {
					r.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return doc;
	}

}
