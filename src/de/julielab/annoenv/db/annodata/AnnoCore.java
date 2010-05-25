/** 
 * AnnoCore.java
 * 
 * Copyright (c) 2007, JULIE Lab. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0 
 *
 * Author: tomanek, klaue
 * 
 * Current version: 2.0
 * Since version:   1.2
 *
 * Creation date: Januar, 2008 
 * 
 **/

package de.julielab.annoenv.db.annodata;

import java.sql.Date;

import java.util.logging.Level;
import java.util.logging.Logger;

import de.julielab.annoenv.db.sql.SQLFunctions;

import de.julielab.annoenv.ui.GUIMessages;
import de.julielab.annoenv.utils.AnnoEnvLogger;
import de.julielab.annoenv.utils.Constants;

/**
 * slim representation of AnnoEnv database entity: project
 */
public class AnnoCore {

	public static Logger logger = AnnoEnvLogger
			.getLogger("de.julielab.annoenv.db.annodata.AnnoCore");

	public static final String ALPROJECT = "AL";

	public static final String DEFAULTPROJECT = "default";

	public static final int DEFAULT_DISPLAY = 0;

	public static final int NICE_DISPLAY = 1;

	protected int displayType = DEFAULT_DISPLAY;

	protected int projectID = -1;

	protected int userID = -1;

	protected String name = "";

	protected String mode = "";

	protected String status = "unlocked";

	protected Date creationDate = null;

	protected String description = "";

	// constructor
	public AnnoCore() {
	}

	// from AnnoProject
	public int getProjectID() {
		return this.projectID;
	}

	public int getUserID() {
		return this.userID;
	}

	public String getUserName(SQLFunctions sf) {
		return sf.getUserName(getUserID());
	}

	public String getName() {
		return this.name;
	}

	public String getStatus() {
		return this.status;
	}

	public String getMode() {
		return this.mode;
	}

	public Date getCreationDate() {
		return this.creationDate;
	}

	public String getDescription() {
		return this.description;
	}

	public int getDisplayType() {
		return displayType;
	}

	public void setDisplayType(int i, SQLFunctions sf) {
		try {
			String s = name;
			if (i == NICE_DISPLAY) {

				s = name + " (" + getUserName(sf) + ")";
			}
			displayType = i;
			name = s;
		} catch (RuntimeException e) {
			GUIMessages.exceptionMessage(e);
			logger.log(Level.SEVERE, "", e);
		}
	}

	public void setProjectID(int id) {
		this.projectID = id;
	}

	public void setUserID(int id) {
		this.userID = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStatus(String s) {
		this.status = s;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public void setCreationDate(Date d) {
		this.creationDate = d;
	}

	public void setDescription(String d) {
		this.description = d;
	}

	@Override
	public String toString() {
		return name;
	}

	// from DefaultProject
	public String getInfoText() {
		String info = description + "(project type:" + mode + ")";
		return info;
	}

	public boolean updateProject(SQLFunctions sf){
		boolean success = false;
		success = sf.updateProjectCore(projectID, name, description, status,
				alBatchsize, alCorpusFraction, alIterations, alStatus,
				alCommittee, alTrainprop, userID);

		return success;
	}

	public boolean isAl() {
		return mode.equals(AnnoCore.ALPROJECT);
	}

	// AL fields and methods

	protected int alBatchsize = -1;

	protected double alTrainprop = -1;

	protected String alCommittee = "";

	protected float alCorpusFraction = -1;

	protected int alIterations = 0;

	protected String alStatus = Constants.AL_PROJECT_STATUS_IDLE;

	// getter methods
	public int getAlBatchsize() {
		return this.alBatchsize;
	}

	public float getAlCorpusFraction() {
		if (!this.isAl())
			throw new IllegalStateException("this is not an AL project");
		return this.alCorpusFraction;
	}

	public int getAlIterations() {
		if (!this.isAl())
			throw new IllegalStateException("this is not an AL project");
		return this.alIterations;
	}

	public String getAlStatus() {
		if (!this.isAl())
			throw new IllegalStateException("this is not an AL project");
		return this.alStatus;
	}

	public String getAlCommittee() {
		if (!this.isAl())
			throw new IllegalStateException("this is not an AL project");
		return this.alCommittee;
	}

	public double getAlTrainprop() {
		if (!this.isAl())
			throw new IllegalStateException("this is not an AL project");
		return this.alTrainprop;
	}

	// setter methods
	public void setAlBatchsize(int b) {
		if (!this.isAl())
			throw new IllegalStateException("this is not an AL project");
		this.alBatchsize = b;
	}

	public void setAlCorpusFraction(float f) {
		if (!this.isAl())
			throw new IllegalStateException("this is not an AL project");

		this.alCorpusFraction = f;
	}

	public void setAlIterations(int i) {
		if (!this.isAl())
			throw new IllegalStateException("this is not an AL project");
		this.alIterations = i;
	}

	public void setAlStatus(String s) {
		if (!this.isAl())
			throw new IllegalStateException("this is not an AL project");
		this.alStatus = s;
	}

	public void setAlCommittee(String c) {
		if (!this.isAl())
			throw new IllegalStateException("this is not an AL project");
		this.alCommittee = c;
	}

	public void setAlTrainprop(double p) {
		if (!this.isAl())
			throw new IllegalStateException("this is not an AL project");
		this.alTrainprop = p;
	}

}
