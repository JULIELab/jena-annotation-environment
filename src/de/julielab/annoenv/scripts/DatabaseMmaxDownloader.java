/** 
 * DatabaseMmaxDownloader.java
 * 
 * Copyright (c) 2009, JULIE Lab. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0 
 *
 * Author: kampe
 * 
 * Current version: 2.0
 * Since version:   1.6
 *
 * Creation date: 14.12.2009 
 **/

package de.julielab.annoenv.scripts;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeMap;

import javax.xml.parsers.ParserConfigurationException;

import de.julielab.annoenv.conversions.DownloadMMaxData;
import de.julielab.annoenv.conversions.ExportIOB;
import de.julielab.annoenv.db.annodata.AnnoFull;
import de.julielab.annoenv.db.annodata.AnnoLight;
import de.julielab.annoenv.db.sql.SQLDatabaseManager;
import de.julielab.annoenv.db.sql.SQLFunctions;

/**
 * stand alone tool which was used to download all MMAX data of all projects
 * 
 * @author kampe
 */
public class DatabaseMmaxDownloader {

	public static void main(String[] args) throws InstantiationException,
			IllegalAccessException, SQLException, ClassNotFoundException,
			IOException, ParserConfigurationException {
		DownloadMMaxData down = new DownloadMMaxData();
		SQLDatabaseManager sdm = new SQLDatabaseManager();
		sdm.setConnection();
		SQLFunctions sf = new SQLFunctions(sdm);

		//TODO all SQL statements have to be made in the package sql
		ResultSet projectRS = sf.runSQLQuery("SELECT user_id, login_name FROM user WHERE LEFT(login_name,1) = 'X'");
		projectRS.beforeFirst();
		TreeMap<String, String> userList = new TreeMap<String, String>();
		while (projectRS.next()) {
			userList.put(projectRS.getString(1), projectRS.getString(2));
			System.out.println(projectRS.getString(2));
		}
		projectRS.close();
		String project = "SELECT project_id, name FROM project where user_id = ";

		String baseDir = "/data/data_corpora/JulieAnnotatedCorpora/Biology/";

		// Download über alles
		for (String user : userList.keySet()) {
			projectRS = sf.runSQLQuery(project + user);
			projectRS.beforeFirst();
			while (projectRS.next()) {
				// TODO: Sollte später in eine Unterfunktion ausgelagert werden
				String fullPath = baseDir + userList.get(user) + File.separator
						+ projectRS.getString(2) + File.separator + "mmax";
				File dir = new File(fullPath);
				if (!dir.exists()) {
					if (!dir.mkdirs()) {
						throw new IOException("Could not create directory "
								+ fullPath);
					}
				}

				{
					AnnoLight p = sf.getLightProject(projectRS.getInt(1));
					down.downloadData(sdm, dir, p);
				}

				fullPath = baseDir + userList.get(user) + File.separator
						+ projectRS.getString(2) + File.separator + "iob";

				dir = new File(fullPath);
				if (!dir.exists()) {
					if (!dir.mkdirs()) {
						throw new IOException("Could not create directory "
								+ fullPath);
					}
				}

				AnnoFull p = sf.getFullProject(projectRS.getInt(1));
				ExportIOB exporter = new ExportIOB(p, dir);
				exporter.run(sf);
			}
			System.out.println("Fertig mit User: " + user);
			projectRS.close();
		}

	}
}
