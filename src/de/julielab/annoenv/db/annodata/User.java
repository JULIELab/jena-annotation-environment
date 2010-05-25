/** 
 * User.java
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

package de.julielab.annoenv.db.annodata;


import java.sql.Timestamp;

import de.julielab.annoenv.db.sql.SQLDatabaseManager;
import de.julielab.annoenv.db.sql.SQLFunctions;

/**
 * Object for the user data.
 */
public class User implements Comparable {

	private int userID;

	private String fullName;

	private String loginName;

	private String passwd;

	private boolean supervisor;

	private Timestamp lastLoginDate;

	private boolean loggedIn;

	public SQLDatabaseManager sdm;

	public User(SQLDatabaseManager sdm) {
		this.sdm = sdm;
	}

	public String getFullName() {
		return fullName;
	}

	public String getLoginName() {
		return loginName;
	}

	public int getUserID() {
		return userID;
	}

	public boolean isSupervisor() {
		return supervisor;
	}

	public Timestamp getLastLoginDate() {
		return lastLoginDate;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setFullName(String full_name) {
		this.fullName = full_name;
	}

	public void setLoggedIn(boolean b) {
		loggedIn = b;
	}

	public void setLoginName(String login_name) {
		this.loginName = login_name;

	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getPasswd() {
		return this.passwd;
	}

	public void setSupervisor(boolean supervisor) {
		this.supervisor = supervisor;
	}

	public boolean getSupervisor() {
		return this.supervisor;

	}

	public void setLastLogin(Timestamp last_login) {
		this.lastLoginDate = last_login;
	}

	public void setUserId(int user_id) {
		this.userID = user_id;

	}

	public int write() {
		final SQLFunctions sf = new SQLFunctions(sdm);
		return sf.writeUser(this);
	}

	public boolean update() {
		final SQLFunctions sqf = new SQLFunctions(sdm);
		return sqf.updateUser(this);
	}

	public String toString() {
		return loginName;
	}

	public int hashCode() {
		return this.userID;
	}

	public boolean equals(Object o) {
		if (o instanceof User) {
			return o.hashCode() == this.hashCode();
		}
		return false;
	}

	public int compareTo(Object o) {
		if (o instanceof User) {
			String login = ((User) o).loginName.toLowerCase();
			String thislogin = this.loginName.toLowerCase();
			return thislogin.compareTo(login);
		}
		throw new ClassCastException();
	}

}
