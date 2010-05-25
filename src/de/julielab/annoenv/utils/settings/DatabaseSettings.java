/** 
 * DatabaseSettings.java
 * 
 * Copyright (c) 2007, JULIE Lab. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0 
 *
 * Author: tomanek
 * 
 * Current version: 2.0 	
 * Since version:   0.9
 *
 * Creation date: Jul 16, 2007
 * 
 * 
 */

package de.julielab.annoenv.utils.settings;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import com.mysql.jdbc.Connection;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * Class for database settings, the DB password is encrypted. This class was
 * outsources from BasicSettings.java to keep all database relevant stuff in one
 * separate property file.
 */
public class DatabaseSettings {

	private final static String VERSION = "V. 1.0";

	// private final static String PROPERTIES_FILE = File.separator +
	// "database.properties";
	private final static String PROPERTIES_FILE = "settings" + File.separator
			+ "database.properties";

	// for the encryption
	private static final String ENCRYPTION_SCHEME = "DESede";

	private static final String ENCRYPTION_KEY = "JULIE JANE encryption phrase version 1.0";

	private static final String UNICODE_FORMAT = "UTF8";

	private KeySpec keySpec;

	private SecretKeyFactory keyFactory;

	// the properties that can be used later
	public String DB_USER;

	public String DB_PASSWORD;

	public String DB_LOCATION;

	public String DB_DRIVER = "com.mysql.jdbc.Driver";

	/**
	 * the constructor to be used when you just want to read in the database
	 * properties
	 */
	public DatabaseSettings() {
		Properties props = readProperties();
		getSettings(props);
	}

	/**
	 * just to get an DatabaseSettings object
	 * 
	 * @param mode
	 *            just put '0' (or anything else) here
	 */
	DatabaseSettings(int mode) {
	}

	/**
	 * read properties file
	 */
	private Properties readProperties() {

		// read in default properties file
		InputStream in = null;
		Properties props = null;
		props = new Properties();

		try {
			in = new FileInputStream(PROPERTIES_FILE);
			props.load(in);

		} catch (FileNotFoundException e1) {
			throw new IllegalStateException(
					"Could not find database settings file: " + PROPERTIES_FILE,
					e1);
		} catch (IOException e) {
			throw new IllegalStateException(
					"database settings could not be loaded", e);
		}
		return props;
	}

	/**
	 * get the settings from the properties object
	 */
	private void getSettings(Properties props) {
		getSettingsUser(props);
		getSettingsPassword(props);
		getSettingsDBLocation(props);
	}

	/**
	 * get the DB location
	 */
	private void getSettingsDBLocation(Properties props) {
		if (!props.containsKey("db.location"))
			throw new RuntimeException(
					"basic settings file misses value for: DB location");
		DB_LOCATION = props.getProperty("db.location");
	}

	/**
	 * get the DB password it is assumed that the password is encrypted. this
	 * method will decrypt the password accordingly.
	 */
	private void getSettingsPassword(Properties props) {
		if (!props.containsKey("db.password"))
			throw new RuntimeException(
					"basic settings file misses value for: DB password");

		try {
			initDES();
			String cipher = props.getProperty("db.password");
			DB_PASSWORD = decrypt(cipher);
		} catch (Exception e) {
			throw new RuntimeException("problem decrypting password", e);
		}

	}

	/**
	 * get the DB user
	 */
	private void getSettingsUser(Properties props) {
		if (!props.containsKey("db.user"))
			throw new RuntimeException(
					"basic settings file misses value for: DB user");
		DB_USER = props.getProperty("db.user");
	}

	/**
	 * set the database settings, the password will be written to the properties
	 * file as a DES cipher.
	 */
	private static void setSettings() throws UnsupportedEncodingException,
			InvalidKeyException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeySpecException,
			IllegalBlockSizeException, BadPaddingException {
		DatabaseSettings dbSettings = new DatabaseSettings(0);
		System.out
				.println("\n--------------------------------------------------");
		System.out.println(" JANE + " + VERSION
				+ " -- Access to Annotation Repository");
		System.out
				.println("--------------------------------------------------");

		String location = "";
		String user = "";
		String password = "";

		// read new values
		Scanner in = new Scanner(System.in);

		System.out.print("1. Enter DB location: ");
		location = in.nextLine();
		if (location.equals("c")) {
			System.out.println("   Cancelled. Database settings NOT changed!");
			System.exit(-1);
		}

		System.out.print("2. Enter DB user: ");
		user = in.nextLine();
		if (user.equals("c")) {
			System.out.println("   Cancelled. Database settings NOT changed!");
			System.exit(-1);
		}

		System.out.print("3. Enter DB password: ");
		password = in.nextLine();
		if (password.equals("c")) {
			System.out.println("   Cancelled. Database settings NOT changed!");
			System.exit(-1);
		}

		// test values
		dbSettings.testDatabaseProperties(location, user, password);

		// encrypt password
		dbSettings.initDES();
		String enc_password = dbSettings.encrypt(password);

		// store new values
		Properties newProps = new Properties();
		newProps.setProperty("db.user", user);
		newProps.setProperty("db.password", enc_password);
		newProps.setProperty("db.location", location);

		URL propsURL = dbSettings.getClass().getResource(PROPERTIES_FILE);
		OutputStream out = null;
		try {
			out = new FileOutputStream(new File(propsURL.getFile()), false);
		} catch (FileNotFoundException e) {
			System.err.println("Error: cannot write to properties file ("
					+ PROPERTIES_FILE + ").");
			System.exit(-1);
		}
		try {
			newProps.store(out, "database properties automatically created");
			out.close();
		} catch (IOException e) {
			System.err.println("Error: cannot writing toproperties file ("
					+ PROPERTIES_FILE + ").\n");
			e.printStackTrace();
			System.exit(-1);
		}

		System.out
				.println("\nAccess to annotation repository successfully configured!\n");
	}

	private void testDatabaseProperties(String location, String user,
			String password) {

		System.out.print("\nTesting specified parameters...");

		Connection cn = null;
		try {
			Class.forName(DB_DRIVER).newInstance();
			cn = (Connection) DriverManager.getConnection(location, user,
					password);

		} catch (SQLException e) {
			System.out.println(" ERR");
			System.out
					.println("   The parameters you entered are not correct.\n   "
							+ "Refer to the manual for proper settings or ask you administrator!");
			System.exit(-1);
		} catch (ClassNotFoundException e) {
			System.out.println(" ERR");
			e.printStackTrace();
		} catch (InstantiationException e) {
			System.out.println(" ERR");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			System.out.println(" ERR");
			e.printStackTrace();
		} finally {
			if (cn != null) {
				try {
					cn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		System.out.println(" OK");
	}

	/**
	 * initialize encryption engine use the key defined above and DES ede
	 * encryption
	 */
	private void initDES() throws UnsupportedEncodingException,
			InvalidKeyException, NoSuchAlgorithmException {
		byte[] keyAsBytes = ENCRYPTION_KEY.getBytes(UNICODE_FORMAT);
		keySpec = new DESedeKeySpec(keyAsBytes);
		keyFactory = SecretKeyFactory.getInstance(ENCRYPTION_SCHEME);
	}

	/**
	 * encrypt a plain text
	 */
	private String encrypt(String plainText) throws InvalidKeyException,
			UnsupportedEncodingException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeySpecException,
			IllegalBlockSizeException, BadPaddingException {

		SecretKey key = keyFactory.generateSecret(keySpec);
		Cipher cipher = Cipher.getInstance(ENCRYPTION_SCHEME);
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] cleartext = plainText.getBytes(UNICODE_FORMAT);
		byte[] ciphertext = cipher.doFinal(cleartext);
		BASE64Encoder base64encoder = new BASE64Encoder();
		return base64encoder.encode(ciphertext);
	}

	/**
	 * decrypt a plain text
	 */
	private String decrypt(String cipheredText) throws InvalidKeySpecException,
			NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, IOException, IllegalBlockSizeException,
			BadPaddingException {

		SecretKey key = keyFactory.generateSecret(keySpec);
		Cipher cipher = Cipher.getInstance(ENCRYPTION_SCHEME);
		cipher.init(Cipher.DECRYPT_MODE, key);
		BASE64Decoder base64decoder = new BASE64Decoder();
		byte[] cleartext = base64decoder.decodeBuffer(cipheredText);
		byte[] ciphertext = cipher.doFinal(cleartext);
		return bytes2String(ciphertext);
	}

	/**
	 * helper method for decrypt()
	 */
	private String bytes2String(byte[] bytes) {
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			stringBuffer.append((char) bytes[i]);
		}
		return stringBuffer.toString();
	}

	/**
	 * main method used to change the database properties
	 */
	public static void main(String[] args) throws InvalidKeyException,
			UnsupportedEncodingException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeySpecException,
			IllegalBlockSizeException, BadPaddingException {
		setSettings();
	}

}
