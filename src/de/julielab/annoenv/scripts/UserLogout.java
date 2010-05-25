/** 
 * UserLogout.java
 * 
 * Copyright (c) 2007, JULIE Lab. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0 
 *
 * Author: tomanek
 * 
 * Current version: 2.0
 * Since version:   1.0
 *
 * Creation date: Jan 22, 2007 
 * 
 **/

package de.julielab.annoenv.scripts;

import java.util.Scanner;

import de.julielab.annoenv.db.annodata.User;
import de.julielab.annoenv.db.sql.SQLDatabaseManager;
import de.julielab.annoenv.db.sql.SQLFunctions;

/**
 * This is a stand-alone programm to logout a user.
 */
public class UserLogout {

	public void logout(String username) {

		try {
			SQLDatabaseManager sdm = new SQLDatabaseManager();
			sdm.setConnection();
			SQLFunctions sf = new SQLFunctions(sdm);

			User myUser = sf.retrieveUser(username);
			if (myUser == null) {
				System.out.println("ERR: user '" + username
						+ "' does not exits.");
				System.exit(-1);
			} else {
				sf.logout(myUser.getUserID());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		System.out.print("Enter user name to log out (press 'c' to cancel): ");
		String user = in.nextLine();
		if (user.equals("c")) {
			System.out.println("User logout cancelled.");
			System.exit(-1);
		}

		UserLogout userLogout = new UserLogout();
		userLogout.logout(user);
		System.out.println("User '" + user + "' logged out successfully.");
	}

}
