/** 
 * BaseDataLight.java
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
 * Creation date: Sep 10, 2008
 * 
 */
package de.julielab.annoenv.db.annodata;


import de.julielab.annoenv.db.sql.SQLDatabaseManager;
import de.julielab.annoenv.db.sql.SQLFunctions;

public class BaseDataLight {

	public static String STATUS_RAW = "raw";
	public static String STATUS_PROGRESS = "progress";
	public static String STATUS_DONE = "done";
	
	protected int basedata_id;

	protected int project_id;

	protected String uri;

	protected String status;

 	protected int userID;
	
 	protected int annotationTime; // in seconds

 	protected String description = "-";

	public BaseDataLight() {
		basedata_id = -1;
		project_id = -1;
		uri = "";
		status = "";
		annotationTime = 0;
		description = "-";
		userID = 0; // TODO: one day we need to set the user who did the
						// annotation
	}
	
	public void setUser(int user) {
		this.userID = user;
	}

	public int getUser() {
		return userID;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String d) {
		this.description = d;
	}
	
	public void setBasedataID(int basedata_id) {
		this.basedata_id = basedata_id;
	}

	/**
	 * @return basedata_id Identification of basedata in DB
	 */
	public int getBasedataID() {
		return basedata_id;
	}

	public void setProjectID(int project_id) {
		this.project_id = project_id;
	}

	/**
	 * @return project_id Identification of project in DB
	 */
	public int getProjectID() {
		return project_id;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return status Status of document: done, progress, raw
	 */
	public String getStatus() {
		return status;
	}
	
	public void setUri(String uri) {
		this.uri = uri;
	}

	/**
	 * @return uri Identification of file in repository
	 */
	public String getUri() {
		return uri;
	}

	public int getAnnotationTime() {
		return annotationTime;
	}

	public void setAnnotationTime(int t) {
		// t in seconds
		this.annotationTime = t;
	}

	public void addAnnotationTime(int delta) {
		// delta in seconds
		this.annotationTime += delta;
	}

	public String toString() {
		return (uri + "  (" + status + ")");
	}

	public String getInfoText() {
		return ("status: " + status);
	}

	/**
	 * updates the base data table in the database without files
	 */
	public boolean updateBaseData(SQLDatabaseManager sdm)  {
		final SQLFunctions sf = new SQLFunctions(sdm);
		boolean success = false;
		success = sf.updateBaseDataLight(basedata_id, description, uri, status, userID,
				annotationTime);
		return success;
	}

}
