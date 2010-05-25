/** 
 * SQLFunctions.java
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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

import com.mysql.jdbc.PreparedStatement;

import de.julielab.annoenv.db.annodata.AnnoCore;
import de.julielab.annoenv.db.annodata.AnnoFull;
import de.julielab.annoenv.db.annodata.AnnoLight;
import de.julielab.annoenv.db.annodata.BaseDataFull;
import de.julielab.annoenv.db.annodata.BaseDataLight;
import de.julielab.annoenv.db.annodata.Markable;
import de.julielab.annoenv.db.annodata.MmaxData;
import de.julielab.annoenv.db.annodata.User;
import de.julielab.annoenv.statistics.ALTimes;
import de.julielab.annoenv.statistics.DisValue;
import de.julielab.annoenv.utils.AnnoEnvLogger;
import de.julielab.annoenv.utils.Constants;
import de.julielab.annoenv.utils.IOUtils;
import de.julielab.annoenv.utils.PolynomialFit;

/**
 * This class stores all functions for accessing the DB via SQL. Mostly, these
 * functions are called from the classes in de.julielab.annoenv.db.*
 */
public class SQLFunctions {

	private static Logger logger = AnnoEnvLogger
			.getLogger("de.julielab.annoenv.db.sql.SQLFunctions");

	final private SQLDatabaseManager sdm;

	public SQLFunctions(final SQLDatabaseManager sdm) {
		this.sdm = sdm;
	}

	public SQLDatabaseManager getSQLDatabaseManager() {
		return sdm;
	}

	private static final String MARKABLE_DTD = "<!ELEMENT markables (markable*)>\n"
			+ "<!ATTLIST markable id ID #REQUIRED>";

	private static final String WORD_DTD = "<!ELEMENT words (word*)>\n"
			+ "<!ELEMENT word (#PCDATA)>\n" + "<!ATTLIST word id ID #REQUIRED>";

	/*
	 * User functions
	 */

	/**
	 * Executes a login and returns an user_id. Further, it sets last logged in
	 * to the current date/time and makes an entry to the log table.
	 * 
	 * @param user
	 *            Database User Name
	 * @param passwd
	 *            Database User Password
	 */
	public int login(final String login_name, final String passwd) {
		int userID = -1;
		sdm.setConnection();
		if (sdm.getConnection() != null) {
			final User myUser = retrieveUser(login_name);
			if (myUser != null && myUser.getPasswd().equals(passwd)) {
				if (!myUser.isLoggedIn()) {
					// accept user
					userID = myUser.getUserID();
					myUser.setLastLogin(getDBSystemTime());
					myUser.setLoggedIn(true);
					myUser.update();
					logger.info("User " + userID + " (" + myUser.getFullName()
							+ ") logged in.");
				} else {
					// reject user when already logged in
					throw new IllegalStateException("User already logged in.");
				}
			} else {
				// reject user when user name or password wrong
				throw new IllegalStateException(
						"User name does not exist or password wrong.");
			}
		}

		return userID;
	}

	/**
	 * logout the user given his userID logged_in is set to 0 in DB
	 * 
	 * @param userID
	 */
	public void logout(int userID) {
		int status = 0;
		String query = "UPDATE user SET logged_in = " + status
				+ " WHERE user_id = " + userID;
		PreparedStatement st = null;
		try {
			st = (PreparedStatement) sdm.getConnection()
					.prepareStatement(query);
			st.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("User logout failed", e);
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		if (status == 1) {
			logger.info("User " + userID + " logged in.");
		} else if (status == 0) {
			logger.info("User " + userID + " logged out.");
		}
	}

	/**
	 * Selects the username with respect to a user_id
	 * 
	 * @param user_id
	 * @return userName
	 */
	public String getUserName(int user_id) {
		String userName = null;
		ResultSet rs = null;
		try {
			final String query = "SELECT complete_name FROM user WHERE user_id='"
					+ user_id + "'";
			rs = sdm.executeQuery(query);
			boolean success = rs.first(); 
			if (success == false)
				throw new IllegalArgumentException(
						"No user data with id " + user_id + " found in database");
			
			
			while (rs.next()) {
				userName = rs.getString("complete_name");
			}
		} catch (SQLException e) {
			throw new RuntimeException("Retrieving username has failed.", e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return userName;
	}

	/**
	 * gets all users from the DB. Sort users by their supervisor flag so that
	 * normal users are shown first and supervisors are at the end of the list
	 * 
	 * @return an ArrayList with user objects
	 */
	public ArrayList<User> retrieveAllUsers() {
		ArrayList<User> users = new ArrayList<User>();

		ResultSet rs = null;
		try {
			String query = "SELECT * FROM user order by supervisor ASC";
			rs = sdm.executeQuery(query);
			while (rs.next()) {
				int user_id = rs.getInt("user_id");
				String login_name = rs.getString("login_name");
				String full_name = rs.getString("complete_name");
				Timestamp last_login = rs.getTimestamp("last_login");
				boolean supervisor = rs.getBoolean("supervisor");
				String passwd = rs.getString("passwd");

				User current = new User(sdm);
				current.setFullName(full_name);
				current.setLoginName(login_name);
				current.setPasswd(passwd);
				current.setSupervisor(supervisor);
				current.setUserId(user_id);
				current.setLastLogin(last_login);
				users.add(current);
			}
		} catch (SQLException e) {
			throw new RuntimeException("Retrieving users failed.", e);

		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return users;
	}

	public User retrieveUser(String login_name) {
		ResultSet rs = null;
		try {
			String query = "SELECT * FROM user WHERE login_name='" + login_name
					+ "'";
			rs = sdm.executeQuery(query);

			if (rs.first()) {
				int user_id = rs.getInt("user_id");
				String full_name = rs.getString("complete_name");

				Timestamp last_login = rs.getTimestamp("last_login");
				boolean supervisor = rs.getBoolean("supervisor");
				String passwd = rs.getString("passwd");
				boolean login_status = (rs.getInt("logged_in") == 1) ? true
						: false;

				User myUser = new User(sdm);
				myUser.setLastLogin(last_login);
				myUser.setFullName(full_name);
				myUser.setLoginName(login_name);
				myUser.setPasswd(passwd);
				myUser.setSupervisor(supervisor);
				myUser.setUserId(user_id);
				myUser.setLoggedIn(login_status);

				return myUser;
			} else {
				throw new IllegalArgumentException(
						"No user data for login name " + login_name + " found in database");
			}
		} catch (SQLException e) {
			throw new RuntimeException("Retrieving user failed (user name: "
					+ login_name + ")", e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public boolean updateUser(User user) {

		int login_status = (user.isLoggedIn()) ? 1 : 0;

		boolean success = true;
		PreparedStatement st = null;
		try {
			String updateQuery = "UPDATE user SET login_name='"
					+ user.getLoginName() + "', last_login='"
					+ user.getLastLoginDate() + "', logged_in='" + login_status
					+ "' WHERE user_id=" + user.getUserID();
			st = (PreparedStatement) sdm.getConnection().prepareStatement(
					updateQuery);
			st.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Updating user data failed", e);
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return success;

	}

	/**
	 * writes user data to db
	 */
	public int writeUser(User user) {

		PreparedStatement st = null;
		try {
			st = (PreparedStatement) sdm
					.getConnection()
					.prepareStatement(
							"INSERT INTO user "
									+ "(login_name, passwd, complete_name, last_login, supervisor) "
									+ "VALUES "
									+ "('"
									+ user.getLoginName()
									+ "','"
									+ user.getPasswd()
									+ "','"
									+ user.getFullName()
									+ "','"
									+ (new Date(System.currentTimeMillis()))
											.toString() + "',"
									+ ((user.getSupervisor()) ? 1 : 0) + ")");

			st.executeUpdate();

			int user_id = (int) st.getLastInsertID();

			logger.info("User " + user_id + " created.");

			return user_id;

		} catch (SQLException e) {
			throw new RuntimeException(
					"User could not be inserted to database", e);
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/*
	 * Project functions
	 */

	public AnnoCore getCoreProject(int project_id) {
		ResultSet rs = null;
		try {
			String query1 = "SELECT * FROM project WHERE project_id='"
					+ project_id + "'";
			rs = sdm.executeQuery(query1);
			boolean success = rs.first();
			if (success == false) {
				throw new IllegalArgumentException(
						"No data for project " + project_id + " found in database.");
			}
			AnnoCore P = new AnnoCore();
			P.setProjectID(project_id);
			P.setName(rs.getString("name"));
			P.setDescription(rs.getString("description"));
			P.setCreationDate(rs.getDate("creation_date"));
			P.setMode(rs.getString("mode"));
			P.setStatus(rs.getString("status"));
			P.setUserID(rs.getInt("user_id"));

			if (rs.getString("mode").equals("AL")) {
				// an AL project
				P.setAlBatchsize(rs.getInt("AL_batchsize"));
				P.setAlCorpusFraction(rs.getFloat("AL_corpusfraction"));
				P.setAlIterations(rs.getInt("AL_iterations"));
				P.setAlStatus(rs.getString("AL_status"));
				P.setAlCommittee(rs.getString("AL_committee"));
				P.setAlTrainprop(rs.getFloat("AL_trainprop"));
			}
			return P;
		} catch (SQLException e) {
			throw new RuntimeException(
					"failed obtaining project data from database (PID="
							+ project_id + ")", e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// get all projects of specified user without basedata and files
	public ArrayList<AnnoCore> getCoreProjects(int user_id) {
		ArrayList<AnnoCore> projects = new ArrayList<AnnoCore>();
		ResultSet rs = null;
		try {
			String query = "SELECT project_id FROM project WHERE user_id='"
					+ user_id + "' AND status!='locked'";
			rs = sdm.executeQuery(query);
			while (rs.next()) {
				int project_id = rs.getInt("project_id");
				AnnoCore P = getCoreProject(project_id);
				projects.add(P);
			}
		} catch (SQLException e) {
			throw new RuntimeException("could not get projects from database",
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
		return projects;
	}

	public AnnoLight getLightProject(int project_id) {
		ResultSet rs = null;
		try {
			String query1 = "SELECT * FROM project WHERE project_id='"
					+ project_id + "'";
			rs = sdm.executeQuery(query1);
			boolean success = rs.first();
			if (success == false) {
				throw new IllegalArgumentException(
						"No data for project " + project_id + " found in database.");
			}
			AnnoLight P = new AnnoLight();
			P.setProjectID(project_id);
			P.setName(rs.getString("name"));
			P.setDescription(rs.getString("description"));
			P.setCreationDate(rs.getDate("creation_date"));
			P.setMode(rs.getString("mode"));
			P.setStatus(rs.getString("status"));
			P.setUserID(rs.getInt("user_id"));

			if (rs.getString("mode").equals("AL")) {
				// an AL project
				P.setAlBatchsize(rs.getInt("AL_batchsize"));
				P.setAlCorpusFraction(rs.getFloat("AL_corpusfraction"));
				P.setAlIterations(rs.getInt("AL_iterations"));
				P.setAlStatus(rs.getString("AL_status"));
				P.setAlCommittee(rs.getString("AL_committee"));
				P.setAlTrainprop(rs.getFloat("AL_trainprop"));
				P.setAlCorpusPos(rs.getString("AL_corpusPOS"));
				P.setAlCorpusBin(rs.getString("AL_corpusBIN"));
				P.setAlCorpusTok(rs.getString("AL_corpusTOK"));
			}

			ArrayList<BaseDataLight> basedata = getAllBaseDataLight(P
					.getProjectID());
			P.setBaseDataLight(basedata);

			return P;
		} catch (SQLException e) {
			throw new RuntimeException("Making mmaxdata failed (PID="
					+ project_id + ").", e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// get all projects of specified user with files and BaseDataLight
	public ArrayList<AnnoLight> getLightProjects(int user_id) {
		ArrayList<AnnoLight> projects = new ArrayList<AnnoLight>();

		String query = "SELECT project_id FROM project WHERE user_id='"
				+ user_id + "' AND status!='locked'";
		ResultSet rs = null;
		try {
			rs = sdm.executeQuery(query);
			while (rs.next()) {
				int project_id = rs.getInt("project_id");
				AnnoLight P = getLightProject(project_id);
				projects.add(P);
			}
		} catch (SQLException e) {
			throw new RuntimeException("getting project data failed.", e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return projects;
	}

	// get all projects of specified user with all files, mmaxdata and basedata
	public ArrayList<AnnoFull> getFullProjects(int user_id) {
		ArrayList<AnnoFull> projects = new ArrayList<AnnoFull>();
		String query = "SELECT * FROM project WHERE user_id='" + user_id
				+ "' AND status!='locked'";
		ResultSet rs = null;
		try {
			rs = sdm.executeQuery(query);
			while (rs.next()) {
				int project_id = rs.getInt("project_id");
				AnnoFull P = getFullProject(project_id);
				projects.add(P);
			}
		} catch (SQLException e) {
			throw new RuntimeException("could not get projects from database",
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
		return projects;
	}

	public AnnoFull getFullProject(int project_id) {
		ResultSet rs = null;
		try {
			String query1 = "SELECT * FROM project WHERE project_id='"
					+ project_id + "'";
			rs = sdm.executeQuery(query1);
			boolean success = rs.first(); // should only be one ResultSet
			if (success == false) {
				throw new IllegalArgumentException(
						"No data retrieved from database.\nGuess: project PID="
								+ project_id + " does not exists in database.");
			}

			if (rs.getString("mode").equals("AL")) {
				// an AL project
				AnnoFull P = new AnnoFull();
				P.setProjectID(project_id);
				P.setName(rs.getString("name"));
				P.setDescription(rs.getString("description"));
				P.setCreationDate(rs.getDate("creation_date"));
				P.setMode(rs.getString("mode"));
				P.setStatus(rs.getString("status"));
				P.setUserID(rs.getInt("user_id"));

				P.setAlBatchsize(rs.getInt("AL_batchsize"));
				P.setAlCorpusFraction(rs.getFloat("AL_corpusfraction"));
				P.setAlIterations(rs.getInt("AL_iterations"));
				P.setAlStatus(rs.getString("AL_status"));
				P.setAlCommittee(rs.getString("AL_committee"));
				P.setAlTrainprop(rs.getFloat("AL_trainprop"));
				P.setAlCorpusBin(rs.getString("AL_corpusBIN"));
				P.setAlCorpusPos(rs.getString("AL_corpusPOS"));
				P.setAlCorpusTok(rs.getString("AL_corpusTOK"));

				rs.close();
				// get mmax data
				ArrayList<MmaxData> mmaxdata = new ArrayList<MmaxData>();
				String query2 = "SELECT mmaxdata_id FROM mmaxdata WHERE project_id='"
						+ project_id + "'";
				rs = sdm.executeQuery(query2);
				while (rs.next()) {
					int mmaxdata_id = rs.getInt("mmaxdata_id");
					MmaxData m = getMmaxData(mmaxdata_id);
					mmaxdata.add(m);
				}
				P.setMmaxdata(mmaxdata);
				rs.close();

				// get basedata
				ArrayList<BaseDataFull> basedata = new ArrayList<BaseDataFull>();
				String query3 = "SELECT basedata_id FROM basedata WHERE "
						+ "project_id='" + project_id
						+ "' ORDER BY basedata_id DESC";
				rs = sdm.executeQuery(query3);

				// take only first basedata, i.e. the current one for AL
				boolean succ = rs.first();
				if (succ == false) {
					throw new IllegalArgumentException(
							"No basedata for project " + project_id + " found in database.");
				}
				try {
					int basedata_id = rs.getInt("basedata_id");
					basedata.add(getBaseDataFull(basedata_id));
					P.setBaseDataFull(basedata);
				} catch (SQLException e) {
					throw new RuntimeException(
							"Retrieving Basedata failed for AL project, projectID = "
									+ project_id);
				}
				rs.close();
				return P;

			}

			// a default project
			AnnoFull P = new AnnoFull();
			P.setProjectID(project_id);
			P.setName(rs.getString("name"));
			P.setDescription(rs.getString("description"));
			P.setCreationDate(rs.getDate("creation_date"));
			P.setMode(rs.getString("mode"));
			P.setStatus(rs.getString("status"));
			P.setUserID(rs.getInt("user_id"));

			// get mmax data
			ArrayList<MmaxData> mmaxdata = new ArrayList<MmaxData>();
			String query2 = "SELECT mmaxdata_id FROM mmaxdata WHERE project_id='"
					+ project_id + "'";
			rs = sdm.executeQuery(query2);
			while (rs.next()) {
				int mmaxdata_id = rs.getInt("mmaxdata_id");
				MmaxData m = getMmaxData(mmaxdata_id);
				mmaxdata.add(m);
			}
			P.setMmaxdata(mmaxdata);
			rs.close();

			// get basedata
			ArrayList<BaseDataFull> basedata = new ArrayList<BaseDataFull>();
			String query3 = "SELECT basedata_id FROM basedata WHERE project_id='"
					+ project_id + "' ORDER BY basedata_id DESC";
			rs = sdm.executeQuery(query3);
			while (rs.next()) { // add all basedata
				int basedata_id = rs.getInt("basedata_id");
				BaseDataFull b = getBaseDataFull(basedata_id);
				basedata.add(b);
			}
			P.setBaseDataFull(basedata);

			rs.close();
			return P;

		} catch (SQLException e) {
			throw new RuntimeException(
					"error retrieving project data from database (PID="
							+ project_id + ")", e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * get all default projects of type AnnoCore (for all users)
	 */
	public ArrayList<AnnoCore> getDefaultCoreProjects() {
		ArrayList<AnnoCore> projects = new ArrayList<AnnoCore>();

		String query = "SELECT * FROM project WHERE mode='"
				+ AnnoCore.DEFAULTPROJECT + "' AND status!='locked'";
		ResultSet rs = null;
		try {
			rs = sdm.executeQuery(query);
			while (rs.next()) {
				int project_id = rs.getInt("project_id");
				AnnoCore P = getCoreProject(project_id);
				projects.add(P);
			}
			return projects;
		} catch (SQLException e) {
			throw new RuntimeException("could not retrieve default projects", e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * write a default project entry to db
	 */
	public int writeDefaultProject(int user_id, Date creation_date,
			String name, String description, String status, String mode,
			File priolist_file) {

		PreparedStatement st = null;
		try {
			st = (PreparedStatement) sdm
					.getConnection()
					.prepareStatement(
							"INSERT project (user_id, creation_date, name, description, status, mode, priolist_file) "
									+ "VALUES ("
									+ user_id
									+ ",'"
									+ creation_date
									+ "','"
									+ name
									+ "','"
									+ description
									+ "','"
									+ status
									+ "','"
									+ mode + "', ?)");

			FileInputStream fis1 = new FileInputStream(priolist_file);
			st.setAsciiStream(1, fis1, (int) priolist_file.length());

			st.executeUpdate();
			fis1.close();
			long project_id = st.getLastInsertID();

			logger.info("Default project " + project_id + " created.");

			return (int) project_id;

		} catch (SQLException e) {
			throw new RuntimeException("error inserting project to database", e);
		} catch (IOException e) {
			throw new RuntimeException("error inserting project to database", e);
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * write an AL project entry to db
	 */
	public int writeALProject(int user_id, Date creation_date, String name,
			String description, String status, String mode,
			String al_corpusBin, String al_corpusTok, String al_corpusPos,
			int al_batchsize, String al_status, float al_corpusfraction,
			int al_iterations, String al_committee, float al_trainprop,
			File al_t2_file, File priolist_file, File al_tags_file) {

		PreparedStatement st = null;
		try {
			st = (PreparedStatement) sdm
					.getConnection()
					.prepareStatement(
							"INSERT project (user_id, creation_date, name, description, status, mode,"
									+ "AL_corpusBIN, AL_corpusTOK, AL_corpusPOS, AL_batchsize,"
									+ "AL_status, AL_corpusfraction, AL_iterations, AL_committee, AL_trainprop, AL_t2_file, priolist_file, AL_tags_file) "
									+ "VALUES (" + user_id + ",'"
									+ creation_date + "','" + name + "','"
									+ description + "','" + status + "','"
									+ mode + "','" + al_corpusBin + "','"
									+ al_corpusTok + "','" + al_corpusPos
									+ "'," + al_batchsize + ",'" + al_status
									+ "'," + al_corpusfraction + ","
									+ al_iterations + ",'" + al_committee
									+ "'," + al_trainprop + ", ?,?,?)");

			// t2 file might not be set, check this!
			if (al_t2_file == null)
				st.setString(1, "");
			else {
				FileInputStream fis1 = new FileInputStream(al_t2_file);
				st.setAsciiStream(1, fis1, (int) al_t2_file.length());
			}

			FileInputStream fis2 = new FileInputStream(priolist_file);
			st.setAsciiStream(2, fis2, (int) priolist_file.length());
			FileInputStream fis3 = new FileInputStream(al_tags_file);
			st.setAsciiStream(3, fis3, (int) al_tags_file.length());
			st.executeUpdate();

			fis2.close();
			fis3.close();
			long project_id = ((com.mysql.jdbc.PreparedStatement) st)
					.getLastInsertID();

			logger.info("AL project " + project_id + " created.");

			return (int) project_id;

		} catch (SQLException e) {
			throw new RuntimeException(
					"writing new AL project to database failed", e);
		} catch (IOException e) {
			throw new RuntimeException(
					"writing new AL project to database failed", e);
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * updates a Core Project
	 */
	public boolean updateProjectCore(int project_id, String name,
			String description, String status, int AL_batchsize,
			float AL_corpusfraction, int AL_iterations, String al_status,
			String al_committee, double al_trainprop, int user_id) {

		boolean success = true;
		PreparedStatement st = null;
		try {
			String updateQuery = "UPDATE project SET name='" + name
					+ "', user_id='" + user_id + "', description='"
					+ description + "', status='" + status + "', "
					+ "AL_batchsize=" + AL_batchsize + ", AL_corpusfraction="
					+ AL_corpusfraction + ", " + "AL_iterations="
					+ AL_iterations + ", AL_status='" + al_status
					+ "', AL_committee='" + al_committee + "', AL_trainprop='"
					+ al_trainprop + "' WHERE project_id=" + project_id;

			logger.fine(updateQuery);
			st = (PreparedStatement) sdm.getConnection().prepareStatement(
					updateQuery);

			st.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("Updating project failed", e);
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return success;
	}

	/**
	 * update light project
	 * 
	 * updates only alT2File and priolist (only if not set to null). alTagsFile
	 * is (and should not) be updateable.
	 * 
	 * BDLight is updated
	 */
	public boolean updateProjectLight(int project_id, String name,
			String description, String status, int user_id, int AL_batchsize,
			float AL_corpusfraction, int AL_iterations, String al_status,
			String al_committee, float al_trainprop,
			ArrayList<BaseDataLight> basedata, File ALt2File, File priolistFile) {

		boolean success = true;
		PreparedStatement st = null;
		try {

			String ALt2FileQuery = "";
			if (ALt2File != null) {
				ALt2FileQuery = ", AL_t2_file=?";
			}

			String priolistFileQuery = "";
			if (priolistFile != null) {
				priolistFileQuery = ", priolist_file=?";
			}

			String updateQuery = "UPDATE project SET name='" + name
					+ "', user_id='" + user_id + "', description='"
					+ description + "', status='" + status + "', "
					+ "AL_batchsize=" + AL_batchsize + ", AL_corpusfraction="
					+ AL_corpusfraction + ", " + "AL_iterations="
					+ AL_iterations + ", AL_status='" + al_status
					+ "', AL_committee='" + al_committee + "', AL_trainprop='"
					+ al_trainprop + "'" + ALt2FileQuery + priolistFileQuery
					+ " WHERE project_id=" + project_id;

			logger.fine(updateQuery);

			st = (PreparedStatement) sdm.getConnection().prepareStatement(
					updateQuery);

			int pos = 1;
			if (ALt2File != null) {
				FileInputStream fis = new FileInputStream(ALt2File);
				st.setAsciiStream(pos, fis, (int) ALt2File.length());
				pos++;
			}

			if (priolistFile != null) {
				FileInputStream fis = new FileInputStream(priolistFile);
				st.setAsciiStream(pos, fis, (int) priolistFile.length());
				pos++;
			}

			st.executeUpdate();

			// now update all basedata
			for (BaseDataLight b : basedata) {
				b.updateBaseData(sdm);
			}

		} catch (SQLException e) {
			throw new RuntimeException("updating project failed (PID="
					+ project_id + ")", e);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(
					"updating project failed because some files were unavailable (PID="
							+ project_id + ")", e);
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return success;
	}

	/**
	 * update a full project
	 * 
	 * updates only alT2File and priolist (only if not set to null). alTagsFile
	 * is (and should not) be updateable.
	 * 
	 * BDFull is updated
	 * 
	 * 
	 */
	public boolean updateProjectFull(int project_id, String name,
			String description, String status, int user_id, int AL_batchsize,
			float AL_corpusfraction, int AL_iterations, String al_status,
			String al_committee, float al_trainprop,
			ArrayList<BaseDataFull> basedata, File ALt2File, File priolistFile) {

		boolean success = true;
		PreparedStatement st = null;
		try {

			String ALt2FileQuery = "";
			if (ALt2File != null) {
				ALt2FileQuery = ", AL_t2_file=?";
			}

			String priolistFileQuery = "";
			if (priolistFile != null) {
				priolistFileQuery = ", priolist_file=?";
			}

			String updateQuery = "UPDATE project SET name='" + name
					+ "', user_id='" + user_id + "', description='"
					+ description + "', status='" + status + "', "
					+ "AL_batchsize=" + AL_batchsize + ", AL_corpusfraction="
					+ AL_corpusfraction + ", " + "AL_iterations="
					+ AL_iterations + ", AL_status='" + al_status
					+ "', AL_committee='" + al_committee + "', AL_trainprop='"
					+ al_trainprop + "'" + ALt2FileQuery + priolistFileQuery
					+ " WHERE project_id=" + project_id;

			logger.fine(updateQuery);

			st = (PreparedStatement) sdm.getConnection().prepareStatement(
					updateQuery);

			int pos = 1;
			if (ALt2File != null) {
				FileInputStream fis = new FileInputStream(ALt2File);
				st.setAsciiStream(pos, fis, (int) ALt2File.length());
				pos++;
			}

			if (priolistFile != null) {
				FileInputStream fis = new FileInputStream(priolistFile);
				st.setAsciiStream(pos, fis, (int) priolistFile.length());
				pos++;
			}

			st.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("updating project failed (PID="
					+ project_id + ")", e);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(
					"updating project failed because some files were unavailable  (PID="
							+ project_id + ")", e);
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return success;
	}

	/**
	 * hard copy of a project, i.e. project is copied with all its annotations
	 * (basedata and markables)
	 * 
	 * @param projectId
	 *            id of project to be copied
	 * @param userId
	 *            if who will own the new project
	 * @param newProjectName
	 *            name of the new project
	 * @param mode
	 *            "default" or "AL" for the respective project mode
	 */
	public void copyProject(int projectID, int userID, String newProjectName,
			String mode) {

		/*
		 * copy the project
		 */
		String queryProjCopy = "INSERT INTO project "
				+ "(user_id, creation_date, description, status, mode, name, priolist_file, "
				+ "AL_batchsize, AL_corpusfraction, AL_status, AL_iterations, AL_committee, AL_trainprop, AL_corpusPOS, AL_corpusTOK, AL_corpusBIN, AL_t2_file, AL_tags_file) "
				+ "SELECT  '"
				+ userID
				+ "', creation_date, description, status, '"
				+ mode
				+ "', '"
				+ newProjectName
				+ "', priolist_file, "
				+ "AL_batchsize, AL_corpusfraction, AL_status, AL_iterations, AL_committee, AL_trainprop, AL_corpusPOS, AL_corpusTOK, AL_corpusBIN, AL_t2_file, AL_tags_file "
				+ "FROM project WHERE project_id='" + projectID + "'";
		PreparedStatement st = null;
		int newProjectID;
		try {
			st = (PreparedStatement) sdm.getConnection().prepareStatement(
					queryProjCopy);
			st.executeUpdate();

			newProjectID = (int) ((com.mysql.jdbc.PreparedStatement) st)
					.getLastInsertID();

			st.close();
			/*
			 * copy the statistics table
			 */

			if (mode.equals("AL")) {
				String queryStatsCopy = "INSERT INTO AL_statistics "
						+ "(project_id, sentences, tokens, timeEntered, corpus_fraction, seldis, alldis, nodis, seltime, traintime, traindatatime, testtime, testdatatime) "
						+ "SELECT '"
						+ newProjectID
						+ "', sentences, tokens, timeEntered, corpus_fraction, seldis, alldis, nodis, seltime, traintime, traindatatime, testtime, testdatatime "
						+ "FROM AL_statistics WHERE project_id='" + projectID
						+ "'";

				st = (PreparedStatement) sdm.getConnection().prepareStatement(
						queryStatsCopy);

				st.executeUpdate();
				st.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException("copying project failed (PID="
					+ projectID + ")", e);
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		/*
		 * copy the basedata
		 */
		ArrayList<Integer> bdList = new ArrayList<Integer>();

		String queryBD = "SELECT basedata_id FROM basedata WHERE project_id='"
				+ projectID + "' ORDER BY basedata_id ASC";
		ResultSet rs = null;
		try {
			rs = sdm.executeQuery(queryBD);

			HashMap<Integer, Integer> bdMapping = new HashMap<Integer, Integer>();

			while (rs.next()) {
				// go through each bd and copy it
				int basedataID = rs.getInt("basedata_id");

				String queryBDCopy = "INSERT INTO basedata "
						+ "(project_id, basedata_file, uri, status, annotator,style_file, annotation_time, AL_TIDSID_file, description) "
						+ "SELECT '"
						+ newProjectID
						+ "', basedata_file, uri, status, annotator,style_file, annotation_time, AL_TIDSID_file, description "
						+ "FROM basedata " + "WHERE basedata_id='" + basedataID
						+ "'";
				st = (PreparedStatement) sdm.getConnection().prepareStatement(
						queryBDCopy);

				st.executeUpdate();

				int newBasedataID = (int) ((com.mysql.jdbc.PreparedStatement) st)
						.getLastInsertID();

				bdMapping.put(basedataID, newBasedataID);
				bdList.add(basedataID);
				st.close();
			}
			rs.close();
			/*
			 * copy the mmaxdata
			 */

			String queryMMAX = "SELECT mmaxdata_id FROM mmaxdata WHERE project_id='"
					+ projectID + "' ORDER BY mmaxdata_id";
			rs = sdm.executeQuery(queryMMAX);

			HashMap<Integer, Integer> mmaxMapping = new HashMap<Integer, Integer>();

			while (rs.next()) {
				// go through each mmax and copy it
				int mmaxID = rs.getInt("mmaxdata_id");

				String queryMMAXCopy = "INSERT INTO mmaxdata "
						+ "(project_id, schema_file, custom_file, level_name, main_level) "
						+ "SELECT '"
						+ newProjectID
						+ "', schema_file, custom_file, level_name, main_level "
						+ "FROM mmaxdata " + "WHERE mmaxdata_id='" + mmaxID
						+ "'";

				st = (PreparedStatement) sdm.getConnection().prepareStatement(
						queryMMAXCopy);

				st.executeUpdate();

				int newMmaxID = (int) ((com.mysql.jdbc.PreparedStatement) st)
						.getLastInsertID();

				mmaxMapping.put(mmaxID, newMmaxID);
				st.close();
			}

			/*
			 * copy all markables
			 */

			for (int basedataID : bdList) {
				String queryMARK = "SELECT markable_id, mmaxdata_id, basedata_id FROM markable WHERE basedata_id='"
						+ basedataID
						+ "' AND autosave='0' ORDER BY markable_id ASC, creation_date ASC";
				rs = sdm.executeQuery(queryMARK);

				while (rs.next()) {

					// ones
					int markableID = rs.getInt("markable_id");
					int myOldMmaxdataID = rs.getInt("mmaxdata_id");
					int myOldBasedataID = rs.getInt("basedata_id");

					int myNewMmaxdataID = 0;
					int myNewBasedataID = 0;
					if (mmaxMapping.containsKey(myOldMmaxdataID)
							&& bdMapping.containsKey(myOldBasedataID)) {
						myNewMmaxdataID = mmaxMapping.get(myOldMmaxdataID);
						myNewBasedataID = bdMapping.get(myOldBasedataID);

					} else {
						throw new IllegalStateException(
								"copying project failed: markables could not be resolved!");
					}

					String queryMARKCopy = "INSERT INTO markable "
							+ "(basedata_id, mmaxdata_id, creation_date, markable_file, autosave) "
							+ "SELECT '" + myNewBasedataID + "', '"
							+ myNewMmaxdataID
							+ "', creation_date, markable_file, autosave "
							+ "FROM markable " + "WHERE markable_id='"
							+ markableID + "'";

					st = (PreparedStatement) sdm.getConnection()
							.prepareStatement(queryMARKCopy);

					st.executeUpdate();
					st.close();
				}
				rs.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException("copying project failed (PID="
					+ projectID + ")", e);
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * returns true if AL is running for this project
	 * 
	 * @param projectID
	 * @return
	 */
	public String getProjectALStatus(int projectID) {
		String sql = "SELECT project.AL_status FROM project WHERE project_id='"
				+ projectID + "'";
		ResultSet rs = null;
		try {
			rs = sdm.executeQuery(sql);
			boolean success = rs.first();
			if (success == false) {
				throw new IllegalArgumentException(
						"No data for project " + projectID + " found in database.");
			}
			return rs.getString("AL_status");
		} catch (SQLException e) {
			throw new RuntimeException("getting project status failed (PID="
					+ projectID + ")", e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * sets the status for this AL project, i.e. whether AL is running or not
	 * 
	 * @param projectID
	 * @param ALstatus
	 */
	public void setProjectALStatus(int projectID, String al_status) {
		try {
			String update = "UPDATE project SET AL_status='" + al_status
					+ "' WHERE project_id=" + projectID;
			logger.fine("setting ALstatus on project: " + projectID);
			logger.fine(update);
			sdm.executeUpdate(update);
		} catch (SQLException e) {
			throw new RuntimeException("setting AL status failed (PID="
					+ projectID + ")", e);
		}
	}

	/**
	 * check whether a project with the given ID exists and is in AL mode
	 * 
	 * @return true, if AL project exists
	 */
	public boolean isALProject(int projectID) {
		String sql = "SELECT count(*) as chk FROM project WHERE (project_id='"
				+ projectID + "' AND mode='AL')";
		ResultSet rs = null;
		try {
			rs = sdm.executeQuery(sql);
			boolean success = rs.first();
			if (success == false) {
				throw new IllegalArgumentException(
						"No data for project " + projectID + " found in database.");
			}
			if (rs.getInt("chk") == 1) {
				return true;
			}
		} catch (SQLException e) {
			throw new RuntimeException(
					"Checking project type (AL/def) failed (PID=" + projectID
							+ ")", e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	/**
	 * deletes the whole projects...
	 */
	public void deleteProject(int projectID) {

		String query = "";
		ResultSet rs = null;

		try {
			// delete from project table
			query = "DELETE FROM project WHERE project_id='" + projectID + "'";
			sdm.executeUpdate(query);

			// delete schemata
			query = "DELETE FROM mmaxdata WHERE project_id='" + projectID + "'";
			sdm.executeUpdate(query);

			// delete statistics
			query = "DELETE FROM AL_statistics WHERE project_id='" + projectID
					+ "'";
			sdm.executeUpdate(query);

			// delete markables
			query = "SELECT basedata_id FROM basedata  WHERE project_id='"
					+ projectID + "'";
			rs = sdm.executeQuery(query);
			while (rs.next()) {
				int BD = rs.getInt("basedata_id");
				query = "DELETE FROM markable WHERE basedata_id='" + BD + "'";
				sdm.executeUpdate(query);
			}

			// delete basedata
			query = "DELETE FROM basedata  WHERE project_id='" + projectID
					+ "'";
			sdm.executeUpdate(query);

			logger.info("Project " + projectID + " deleted.");

		} catch (SQLException e) {
			throw new RuntimeException("failed deleting project (PID="
					+ projectID + ")", e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * wrapper function to reset a project. Switches between resetDefaultProject
	 * and resetALProject.
	 */
	public void resetProject(int project_id) {
		if (isALProject(project_id)) {
			resetALProject(project_id);
		} else {
			resetDefaultProject(project_id);
		}
		logger.info("Project " + project_id + " resetted.");
	}

	/**
	 * reset a default project. Basedata are kept (if there was basedata
	 * editing, this cannot be resetted!). For each basedata the initial
	 * markables are kept, the others are deleted.
	 */
	private void resetDefaultProject(int project_id) {

		// logger.info("resetting default project...");

		String query = "";
		ResultSet rs = null;

		// make sure that given project is an AL project
		query = "SELECT mode FROM project " + "WHERE project_id='" + project_id
				+ "'";
		try {
			rs = sdm.executeQuery(query);
			String mode = "";
			while (rs.next()) {
				mode = rs.getString("mode");
			}
			if (!mode.equals("default")) {
				throw new IllegalArgumentException(
						"Specified project is not a default project. Not resetting!");
			}

			rs.close();
			// get nr of schemata for this project
			query = "SELECT COUNT(mmaxdata_id)  FROM mmaxdata "
					+ "WHERE project_id='" + project_id + "'";
			rs = sdm.executeQuery(query);
						boolean success = rs.first();
			if (success == false) {
				throw new IllegalArgumentException(
						"No data for project " + project_id + " found in database.");
			}
			int schemaCount = rs.getInt(1);

			rs.close();
			// get all basedata
			query = "SELECT basedata_id FROM basedata " + "WHERE project_id='"
					+ project_id + "'";
			rs = sdm.executeQuery(query);

			ArrayList<Integer> bd = new ArrayList<Integer>();
			while (rs.next()) {
				bd.add(rs.getInt("basedata_id"));
			}

			rs.close();
			// reset BD and remove new markables
			for (Integer i : bd) {

				int bdID = i.intValue();

				// reset bd
				query = "UPDATE basedata SET status='raw', annotator='0',annotation_time='0', description='' "
						+ "WHERE basedata_id='" + bdID + "'";
				sdm.executeUpdate(query);

				// get markables of current basedata
				query = "SELECT markable_id FROM markable "
						+ "WHERE basedata_id='" + bdID
						+ "' ORDER BY markable_id ASC LIMIT " + schemaCount;

				rs = sdm.executeQuery(query);
				ArrayList<Integer> initMK = new ArrayList<Integer>();
				while (rs.next()) {
					initMK.add(rs.getInt("markable_id"));
				}
				if (initMK.size() != schemaCount) {
					throw new IllegalStateException(
							"Error: number of initial basedata markables doesn't match number of schemata. Resetting default project failed! (PID="
									+ project_id + ")");
				}
				rs.close();

				query = "SELECT markable_id FROM markable "
						+ "WHERE basedata_id='" + bdID
						+ "' ORDER BY markable_id ASC";
				rs = sdm.executeQuery(query);

				while (rs.next()) {
					int mkID = rs.getInt("markable_id");
					if (!initMK.contains(mkID)) {
						// delete this markable
						query = "DELETE FROM markable WHERE markable_id='"
								+ mkID + "'";
						sdm.executeUpdate(query);
					}
				}
				rs.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException("could not reset default project (PID="
					+ project_id + ")", e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * reset an AL project. All basedata except the first one is delete (along
	 * with its markables). Only the initial markables of the first basedata are
	 * kept. Also reset some entries in the project table.
	 * 
	 */
	private void resetALProject(int project_id) {

		// logger.info("resetting AL project...");

		String query = "";
		ResultSet rs = null;

		try {
			// make sure that given project is an AL project
			query = "SELECT mode FROM project where project_id='" + project_id
					+ "'";
			rs = sdm.executeQuery(query);

			if (!rs.last()) {
				throw new SQLException("There are no rows in the result set!");
			}

			String mode = rs.getString("mode");
			if (!mode.equals("AL"))
				throw new IllegalStateException(
						"specified project is not an AL project. Not resetting.");

			// reset entries in project table
			query = "UPDATE project "
					+ "SET AL_iterations='0', AL_t2_file='', AL_status='" + Constants.AL_PROJECT_STATUS_IDLE + "'"
					+ "WHERE project_id='" + project_id + "'";
			sdm.executeUpdate(query);

			// reset statistics
			query = "DELETE FROM AL_statistics WHERE project_id='" + project_id
					+ "'";
			sdm.executeUpdate(query);

			// get all basedata
			query = "SELECT basedata_id FROM basedata " + "WHERE project_id='"
					+ project_id + "' ORDER BY basedata_id ASC";

			rs.close();
			rs = sdm.executeQuery(query);

			int initBDID = 0; // the initial BaseData
			ArrayList<Integer> consecBD = new ArrayList<Integer>(); // consecutive
			// BaseDatas

			while (rs.next()) {
				if (rs.isFirst()) {
					initBDID = rs.getInt("basedata_id");
				} else {
					consecBD.add(rs.getInt("basedata_id"));
				}
			}

			// remove consecutive basedata and markables
			for (Integer i : consecBD) {
				int BD = i.intValue();
				query = "DELETE FROM markable WHERE basedata_id='" + BD + "'";
				sdm.executeUpdate(query);
				query = "DELETE FROM basedata WHERE basedata_id='" + BD + "'";
				sdm.executeUpdate(query);
			}

			rs.close();
			// get nr of schemata for this project
			query = "SELECT COUNT(mmaxdata_id)  FROM mmaxdata "
					+ "WHERE project_id='" + project_id + "'";
			rs = sdm.executeQuery(query);
			boolean success = rs.first();
			if (success == false) {
				throw new IllegalArgumentException(
						"No data for project " + project_id + " found in database.");
			}
			int schemaCount = rs.getInt(1);

			rs.close();
			// get ids of initial markables (nr depends on schema nr)
			query = "SELECT markable_id FROM markable " + "WHERE basedata_id='"
					+ initBDID + "' ORDER BY markable_id ASC LIMIT "
					+ schemaCount;
			rs = sdm.executeQuery(query);
			ArrayList<Integer> initMKs = new ArrayList<Integer>();
			while (rs.next()) {
				initMKs.add(rs.getInt("markable_id"));
			}

			if (initMKs.size() != schemaCount) {
				throw new IllegalStateException(
						"Error: number of initial basedata markables doesn't match number of schemata. Resetting default project failed! (PID="
								+ project_id + ")");
			}

			rs.close();
			// reset markables of inital basedata
			query = "SELECT markable_id FROM markable " + "WHERE basedata_id='"
					+ initBDID + "' ORDER BY markable_id ASC";
			rs = sdm.executeQuery(query);

			// make changes to initial BD
			query = "UPDATE basedata SET annotation_time='0', annotator='0', status='raw', description='' "
					+ "WHERE basedata_id='" + initBDID + "'";
			sdm.executeUpdate(query);

			while (rs.next()) {
				int mkID = rs.getInt("markable_id");
				if (initMKs.contains(mkID)) {
					// keep it
				} else {
					query = "DELETE FROM markable WHERE markable_id='" + mkID
							+ "'";
					sdm.executeUpdate(query);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("could not reset AL project (PID="
					+ project_id + ")", e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/*
	 * functions for MmaxData
	 */

	/**
	 * retrieves an mmaxdata entry from the db
	 */
	public MmaxData getMmaxData(int mmaxdata_id) {
		MmaxData newM = null;
		ResultSet rs = null;
		try {
			String query = "SELECT mmaxdata_id, project_id, level_name, main_level FROM mmaxdata WHERE mmaxdata_id='"
					+ mmaxdata_id + "'";
			rs = sdm.executeQuery(query);
			while (rs.next()) {
				newM = new MmaxData();
				newM.setMmaxdataID(rs.getInt("mmaxdata_id"));
				newM.setProjectID(rs.getInt("project_id"));
				newM.setLevelName(rs.getString("level_name"));
				newM.setMainLevel(rs.getBoolean("main_level"));
			}
		} catch (SQLException e) {
			throw new RuntimeException("retrieving mmaxdata failed.", e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return newM;
	}

	/**
	 * writes a new mmaxdata entry to the db
	 */
	public int writeMmaxData(int project_id, String level_name,
			boolean main_level, File schema_file, File custom_file) {

		int main_level_int = (main_level) ? 1 : 0; // transform boolean to tiny
		// int, needed when we are
		// writing to db, otherwise
		// only 0 is written!

		PreparedStatement st = null;
		try {
			st = (PreparedStatement) sdm
					.getConnection()
					.prepareStatement(
							"INSERT mmaxdata (project_id, level_name, main_level, schema_file, custom_file) "
									+ "VALUES ("
									+ project_id
									+ ",'"
									+ level_name
									+ "', '"
									+ main_level_int
									+ "', ? , ? )");
			FileInputStream fis1 = new FileInputStream(schema_file);
			st.setAsciiStream(1, fis1, (int) schema_file.length());
			FileInputStream fis2 = new FileInputStream(custom_file);
			st.setAsciiStream(2, fis2, (int) custom_file.length());
			st.executeUpdate();
			fis1.close();
			fis2.close();

			long mmaxdata_id = ((com.mysql.jdbc.PreparedStatement) st)
					.getLastInsertID();
			return (int) mmaxdata_id;

		} catch (SQLException e) {
			throw new RuntimeException("writing new MmaxData failed (PID="
					+ project_id + ")", e);
		} catch (IOException e) {
			throw new RuntimeException("writing new MmaxData failed (PID="
					+ project_id + ")", e);
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * updates only the priolist of a project
	 * 
	 * @param projectID
	 * @param priolist
	 */
	public boolean updateMmaxData(int mmaxdata_id, String level_name,
			boolean main_level, File schema_file, File custom_file) {
		boolean success = true;
		int main_level_int = (main_level) ? 1 : 0; // transform boolean to tiny
		PreparedStatement st = null;
		try {
			String updateQuery = "UPDATE mmaxdata SET " + "level_name='"
					+ level_name + "', " + "main_level='" + main_level_int
					+ "', " + "schema_file=?, custom_file=? WHERE mmaxdata_id="
					+ mmaxdata_id;

			logger.fine(updateQuery);

			st = (PreparedStatement) sdm.getConnection().prepareStatement(
					updateQuery);

			FileInputStream fis1 = new FileInputStream(schema_file);
			st.setAsciiStream(1, fis1, (int) schema_file.length());

			FileInputStream fis2 = new FileInputStream(custom_file);
			st.setAsciiStream(2, fis2, (int) custom_file.length());

			st.executeUpdate();
			fis1.close();
			fis2.close();

		} catch (SQLException e) {
			success = false;
			throw new RuntimeException("Updating mmaxdata failed.", e);
		} catch (IOException e) {
			success = false;
			throw new RuntimeException("Updating mmaxdata failed.", e);
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return success;
	}

	/*
	 * functions for Markable
	 */

	/**
	 * gets all markables which are stored in the database
	 */
	public ResultSet getAllMarkablesInDatabase() {
		try {
			ResultSet rs = sdm
					.executeQuery("SELECT markable_file, markable_id FROM markable");
			return rs;
		} catch (SQLException e) {
			throw new RuntimeException("error retrieving all markable data", e);
		}
	}

	/**
	 * retrieves a markable entry from the db
	 */
	public Markable getMarkable(int markable_id) {
		Markable newM = null;
		ResultSet rs = null;
		try {
			String query = "SELECT markable.basedata_id, markable.markable_id, markable.mmaxdata_id, "
					+ "markable.creation_date, mmaxdata.level_name, mmaxdata.main_level "
					+ "FROM markable, mmaxdata WHERE markable.mmaxdata_id=mmaxdata.mmaxdata_id "
					+ "AND markable_id='" + markable_id + "'";

			rs = sdm.executeQuery(query);
			boolean success = rs.first();
			if (success == false) {
				throw new IllegalArgumentException(
						"No data for markable " + markable_id + " found in database.");
			}
			newM = new Markable();
			newM.set_markable_id(rs.getInt("markable_id"));
			newM.setBasedataID(rs.getInt("basedata_id"));
			newM.setCreationDate(rs.getTimestamp("creation_date"));
			newM.setMmaxdataID(rs.getInt("mmaxdata_id"));
			newM.setLevelName(rs.getString("level_name"));
			newM.setMainLevel(rs.getBoolean("main_level"));

		} catch (SQLException e) {
			throw new RuntimeException("retrieving markable failed", e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return newM;
	}

	/**
	 * writes markable entry to the db
	 */
	public int writeMarkable(int basedataId, Timestamp creation_date,
			File markableFile, int mmaxdataId, int autosave) {

		PreparedStatement st = null;
		try {
			st = (PreparedStatement) sdm.getConnection().prepareStatement(
					"INSERT markable (basedata_id, creation_date, "
							+ "markable_file, mmaxdata_id, autosave) "
							+ "VALUES (" + basedataId + ",'" + creation_date
							+ "', ?," + mmaxdataId + ", " + autosave + ")");

			FileReader fr = new FileReader(markableFile);
			st.setCharacterStream(1, fr, (int) markableFile.length());

			st.executeUpdate();
			fr.close(); // important! don't forget to close FileInputStream

			long markable_id = ((com.mysql.jdbc.PreparedStatement) st)
					.getLastInsertID();
			return (int) markable_id;

		} catch (SQLException e) {
			throw new RuntimeException("writing new markable failed", e);
		} catch (IOException e) {
			throw new RuntimeException("writing new markable failed", e);
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * delete the markables that were created during autosave
	 */
	public void removeAutosaveMarkables(int basedata_id) {
		PreparedStatement st = null;
		try {
			st = (PreparedStatement) sdm.getConnection().prepareStatement(
					"DELETE FROM markable " + "WHERE basedata_id = "
							+ basedata_id + " " + "AND autosave = 1;");
			st.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("deleting autosaved markable failed.", e);
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	/*
	 * functions for basedata
	 */

	/**
	 * get base data light
	 * 
	 */
	public BaseDataLight getBaseDataLight(int basedata_id) {
		BaseDataLight newBD = null;
		ResultSet rs = null;
		try {
			String query1 = "SELECT basedata_id, description, project_id, uri, status, annotator, annotation_time FROM basedata WHERE basedata_id='"
					+ basedata_id + "'";
			rs = sdm.executeQuery(query1);
			boolean success = rs.first();
			if (success == false) {
				throw new IllegalArgumentException(
						"No data for basedata " + basedata_id + " found in database.");
			}

			newBD = new BaseDataLight();
			int project_id = rs.getInt("project_id");
			newBD.setBasedataID(rs.getInt("basedata_id"));
			newBD.setProjectID(project_id);
			String desc = rs.getString("description");
			if (desc == null) {
				desc = "";
			}
			newBD.setDescription(desc);
			newBD.setUri(rs.getString("uri"));
			newBD.setStatus(rs.getString("status"));
			newBD.setUser(rs.getInt("annotator"));
			newBD.setAnnotationTime(rs.getInt("annotation_time"));

		} catch (SQLException e) {
			throw new RuntimeException("Retrieving basedata failed.", e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return newBD;
	}

	/**
	 * get all basedata light for project
	 * 
	 * basedata is sorted in descending order by basedata id, so that the newest
	 * one is on top (this is needed by AL)
	 * 
	 */
	public ArrayList<BaseDataLight> getAllBaseDataLight(int projectID) {
		ArrayList<BaseDataLight> basedata = new ArrayList<BaseDataLight>();
		String query3 = "SELECT basedata_id FROM basedata WHERE project_id='"
				+ projectID + "'" + "ORDER BY basedata_id DESC";
		ResultSet rs = null;
		try {
			rs = sdm.executeQuery(query3);
			while (rs.next()) { // add all basedata
				int basedata_id = rs.getInt("basedata_id");
				BaseDataLight b = getBaseDataLight(basedata_id);
				basedata.add(b);
			}
		} catch (SQLException e) {
			throw new RuntimeException("getting basedata failed (PID="
					+ projectID + ").", e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return basedata;
	}

	/**
	 * get base data full
	 */
	public BaseDataFull getBaseDataFull(int basedata_id) {
		BaseDataFull newBD = null;
		ResultSet rs = null;
		try {
			String query1 = "SELECT basedata_id, description, project_id, uri, status, annotator, annotation_time FROM basedata WHERE basedata_id='"
					+ basedata_id + "'";
			rs = sdm.executeQuery(query1);
			boolean success = rs.first();
			if (success == false) {
				throw new IllegalArgumentException(
						"No data for basedata " + basedata_id + " found in database.");
			}

			newBD = new BaseDataFull();
			int project_id = rs.getInt("project_id");
			newBD.setBasedataID(rs.getInt("basedata_id"));
			newBD.setProjectID(project_id);
			String desc = rs.getString("description");
			if (desc == null) {
				desc = "";
			}
			newBD.setDescription(desc);
			newBD.setUri(rs.getString("uri"));
			newBD.setStatus(rs.getString("status"));
			newBD.setUser(rs.getInt("annotator"));
			newBD.setAnnotationTime(rs.getInt("annotation_time"));

			ArrayList<Markable> markables = new ArrayList<Markable>();

			rs.close();
			// get the number of schemata for this project
			String query2 = "SELECT count(*) as 'count' FROM mmaxdata WHERE project_id='"
					+ project_id + "'";
			rs = sdm.executeQuery(query2);
			boolean succ = rs.first();
			if (succ == false) {
				throw new IllegalArgumentException(
						"No data for project " + project_id + " found in database.");
			}
			
			int countSchemata = rs.getInt("count");

			rs.close();
			// get the current markables for this basedata
			// and get one markable for each schema
			String query3 = "SELECT markable_id FROM markable "
					+ "WHERE basedata_id='" + basedata_id + "' "
					+ "ORDER by markable_id DESC, creation_date DESC";
			rs = sdm.executeQuery(query3);

			while (rs.next() && countSchemata > 0) {
				int markable_id = rs.getInt("markable_id");
				Markable newM = getMarkable(markable_id);
				markables.add(newM);
				countSchemata--;
			}

			// now set the markables to the basedata object
			newBD.setMarkables(markables);

		} catch (SQLException e) {
			throw new RuntimeException("Retrieving basedata failed (BID="
					+ basedata_id + ")", e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return newBD;
	}

	/**
	 * update basedata with files
	 * 
	 * files are only updates if not null
	 */
	public boolean updateBaseDataFull(int basedataID, String description,
			String uri, String status, int userID, int annotationTime,
			File basedataFile, File styleFile, File alTidSidFile) {
		boolean success = true;

		String bdFileQuery = (basedataFile != null) ? ", basedata_file=?" : "";

		String styleFileQuery = (styleFile != null) ? ", style_file=?" : "";

		String alTidSidFileQuery = (alTidSidFile != null) ? ", AL_TIDSID_file=?"
				: "";

		PreparedStatement st = null;
		try {
			String updateQuery = "UPDATE basedata SET description='"
					+ description + "', uri='" + uri + "', status='" + status
					+ "', " + "annotator=" + userID + bdFileQuery
					+ styleFileQuery + alTidSidFileQuery
					+ " WHERE basedata_id=" + basedataID;

			st = (PreparedStatement) sdm.getConnection().prepareStatement(
					updateQuery);

			int pos = 1;
			if (basedataFile != null) {
				FileInputStream fis = new FileInputStream(basedataFile);
				st.setAsciiStream(pos, fis, (int) basedataFile.length());
				pos++;
			}

			if (styleFile != null) {
				FileInputStream fis = new FileInputStream(styleFile);
				st.setAsciiStream(pos, fis, (int) styleFile.length());
				pos++;
			}

			if (alTidSidFile != null) {
				FileInputStream fis = new FileInputStream(alTidSidFile);
				st.setAsciiStream(pos, fis, (int) alTidSidFile.length());
				pos++;
			}

			st.executeUpdate();
		} catch (SQLException e) {
			success = false;
			throw new RuntimeException("updating basedata failed", e);
		} catch (FileNotFoundException e) {
			success = false;
			throw new RuntimeException("updating basedata failed", e);
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return success;
	}

	/**
	 * update basedata (uri, status, (user_id), description)
	 */
	public boolean updateBaseDataLight(int basedataID, String description,
			String uri, String status, int userID, int annotationTime) {
		boolean success = true;
		PreparedStatement st = null;
		try {
			String updateQuery = "UPDATE basedata SET description='"
					+ description + "', uri='" + uri + "', status='" + status
					+ "', " + "annotator=" + userID + ", annotation_time='"
					+ annotationTime + "'" + " WHERE basedata_id=" + basedataID;
			st = (PreparedStatement) sdm.getConnection().prepareStatement(
					updateQuery);
			st.executeUpdate();

		} catch (SQLException e) {
			success = false;
			throw new RuntimeException("updating basedata failed", e);
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return success;
	}

	public int writeBaseDataFull(int project_id, String description,
			String uri, String status, int annotator, int annotation_time,
			File basedata_file, File style_file, File TIDSID_file) {
		long basedata_id;
		PreparedStatement st = null;
		try {
			st = (PreparedStatement) sdm
					.getConnection()
					.prepareStatement(
							"INSERT basedata (project_id, description, uri, status, annotator, annotation_time, basedata_file, style_file, AL_TIDSID_file) "
									+ "VALUES ("
									+ project_id
									+ ",'"
									+ description
									+ "','"
									+ uri
									+ "', '"
									+ status
									+ "','"
									+ annotator
									+ "','"
									+ annotation_time + "', ? , ? , ?)");

			logger.fine(st.toString());

			FileInputStream fis1 = new FileInputStream(basedata_file);
			st.setAsciiStream(1, fis1, (int) basedata_file.length());
			FileInputStream fis2 = new FileInputStream(style_file);
			st.setAsciiStream(2, fis2, (int) style_file.length());

			// avoid mysql insert error for not setting TIDSIDfile
			if (!(TIDSID_file == null)) {
				FileInputStream fis3 = new FileInputStream(TIDSID_file);
				st.setAsciiStream(3, fis3, (int) TIDSID_file.length());
			} else
				st.setString(3, "");
			st.executeUpdate();

			basedata_id = ((com.mysql.jdbc.PreparedStatement) st)
					.getLastInsertID();
			// !!!!!!!!!!!!!!!!!!!!!!!!!!!!
			fis1.close();
			fis2.close();
			// !!!!!!!!!!!!!!!!!!!!!!!!!!!!
			return (int) basedata_id;

		} catch (SQLException e) {
			throw new RuntimeException("Writing basedata failed.", e);
		} catch (IOException e) {
			throw new RuntimeException("Writing basedata failed.", e);
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/*
	 * misc functions
	 */

	/**
	 * shows the log entries for the al process, this is to be shown in the
	 * annoclient log window startTime describes when AL has been started, all
	 * entries in log table >= this time, where loglevel >= 800 (info), and
	 * where message starts with <projectID># are taken
	 */
	public String getALLog(int projectID, Timestamp startTime) {

		String alLog = "";

		/*
		 * show max. the last 40 entries, only where loglevel >= 800 (info), do
		 * not show fine level and where message starts with <projectID>#
		 */
		String query = "SELECT level, timeEntered, message, sourceClass FROM log "
				+ "WHERE "
				+ "(logger LIKE 'de.julielab.al%' OR logger LIKE 'de.julielab.annoenv.al%') "
				+ "AND level>=800 "
				+ "AND message LIKE '"
				+ projectID
				+ "#%' "
				+ "AND timeEntered>='"
				+ startTime
				+ "' "
				+ "ORDER BY timeEntered DESC, sequence DESC LIMIT 40";
		//System.out.println(query);//TODO remove
		ResultSet rs = null;
		try {
			rs = sdm.executeQuery(query);

			while (rs.next()) {
				int level = rs.getInt("level");
				String level_info = "";
				switch (level) {
				case 1000:
					level_info = "ERR";
					break;
				case 900:
					level_info = "WARN";
					break;
				case 800:
					level_info = "INF";
					break;
				case 500:
					level_info = "FNE";
					break;
				}

				Timestamp time = rs.getTimestamp("timeEntered");
				String message = rs.getString("message");
				String[] message_parts = message.split("#");
				String line = "";
				if (message_parts.length == 2) {
					message = message_parts[1];
					line = time + "  " + level_info + "  " + message;// +
				} else {
					line = "";
				}
				//alLog = line + "\n";
				alLog += line + "\n";
			}
			//System.out.println("alLog:" + alLog);//TODO remove
		} catch (SQLException e) {
			throw new RuntimeException("retrieving AL log entries failed.", e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return alLog;
	}

	/**
	 * gets us the current DB system time...
	 */
	public Timestamp getDBSystemTime() {
		String query = "SELECT NOW();";
		ResultSet rs = null;
		Timestamp now;
		try {
			rs = sdm.executeQuery(query);
			rs.first();
			now = rs.getTimestamp(1);
		} catch (SQLException e) {
			throw new RuntimeException("getting DB system time failed", e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return now;
	}

	/*
	 * ------------------------------------------------------------ functions
	 * for annotation analysis, statistics and deployment
	 * ------------------------------------------------------------
	 */

	/**
	 * read the disagreement values for one project
	 * 
	 * @return an arraylist of DisValue objects
	 */
	public ArrayList<DisValue> getDisagreementData(int projectID) {
		ArrayList<DisValue> data = new ArrayList<DisValue>();
		String query = "SELECT * from AL_statistics where project_id='"
				+ projectID + "' ORDER BY timeEntered ASC";
		ResultSet rs = null;
		try {
			rs = sdm.executeQuery(query);
			while (rs.next()) {
				int sents = rs.getInt("sentences");
				int toks = rs.getInt("tokens");
				double seldis = rs.getDouble("seldis");
				double alldis = rs.getDouble("alldis");
				double nodis = rs.getDouble("nodis");
				Timestamp time = rs.getTimestamp("timeEntered");
				float frac = rs.getFloat("corpus_fraction");
				data.add(new DisValue(projectID, sents, toks, seldis, alldis,
						nodis, time, frac));
			}
		} catch (SQLException e) {
			throw new RuntimeException(
					"failed obtaining disagreement data for current project (pid="
							+ projectID + ")", e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return data;
	}

	/**
	 * read the active learning time values for one project
	 * 
	 * @return an arraylist of ALTimes objects
	 */
	public ArrayList<ALTimes> getALTimes(int projectID) {
		ArrayList<ALTimes> data = new ArrayList<ALTimes>();
		String query = "SELECT * from AL_statistics where project_id='"
				+ projectID + "' ORDER BY timeEntered ASC";
		ResultSet rs = null;
		try {
			rs = sdm.executeQuery(query);
			while (rs.next()) {
				int seltime = rs.getInt("seltime");
				int traintime = rs.getInt("traintime");
				int traindatatime = rs.getInt("traindatatime");
				int testtime = rs.getInt("testtime");
				int testdatatime = rs.getInt("testdatatime");
				data.add(new ALTimes(projectID, seltime, traintime,
						traindatatime, testtime, testdatatime));
			}
			return data;
		} catch (SQLException e) {
			throw new RuntimeException(
					"could not retrieve AL times from database", e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * function to add a statistics calculated during AL to DB
	 */
	public void addALStatistics(int projectID, double seldis, double alldis,
			double nodis, int sentences, int tokens, int traintime,
			int traindatatime, int testtime, int testdatatime, int seltime) {

		String query = "SELECT AL_corpusfraction FROM project WHERE project_id='"
				+ projectID + "'";
		ResultSet rs = null;
		try {
			rs = sdm.executeQuery(query);
			boolean succ = rs.first();
			if (succ == false) {
				throw new IllegalArgumentException(
						"No data for project " + projectID + " found in database.");
			}
			float frac = rs.getFloat("AL_corpusfraction");

			// make entry to db
			Timestamp time = getDBSystemTime();
			query = "INSERT into AL_statistics "
					+ "(project_id, sentences, tokens, timeEntered, corpus_fraction, "
					+ "seldis, alldis, nodis, traintime, traindatatime, testtime, testdatatime, seltime) "
					+ "VALUES " + "('" + projectID + "','" + sentences + "','"
					+ tokens + "','" + time + "','" + frac + "','" + seldis
					+ "','" + alldis + "','" + nodis + "','" + traintime
					+ "','" + traindatatime + "','" + testtime + "','"
					+ testdatatime + "','" + seltime + "')";

			sdm.executeUpdate(query);
		} catch (SQLException e) {
			throw new RuntimeException("adding new AL statistics failed", e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * gets the annotation time for each basedata returned times are: minutes !
	 * db stores: seconds!
	 * 
	 * @param projectID
	 * @return arraylist with Integers
	 */
	public ArrayList<Integer> getAnnotationTimes(int projectID) {
		String query = "SELECT annotation_time FROM basedata WHERE project_id='"
				+ projectID + "'";
		ResultSet rs = null;
		try {
			rs = sdm.executeQuery(query);
			ArrayList<Integer> times = new ArrayList<Integer>();
			while (rs.next()) {
				times.add(rs.getInt("annotation_time") / 60);
			}
			rs.close();
			return times;
		} catch (SQLException e) {
			throw new RuntimeException(
					"annotation times could not be retrieved.", e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * get the average annotation time for all basedata which have already been
	 * worked on (i.e. which have an annotation time > 0)
	 * 
	 * @param projectID
	 */
	public float getAverageAnnotationTime(int projectID) {
		ArrayList<Integer> times = getAnnotationTimes(projectID);
		float avg = 0;
		int count = 0;
		for (Integer i : times) {
			int curr = i.intValue();
			if (curr > 0) {
				avg += curr;
				count++;
			}
		}
		if (avg != 0) {
			avg = avg / count;
		}
		return avg;
	}

	/**
	 * downloads the annotated data in T2 format from AL projects
	 * 
	 * @param projectID
	 * @param outFile
	 */
	public void downloadT2File(int projectID, File outFile) {

		if (!isALProject(projectID)) {
			throw new IllegalArgumentException(
					"Error: This is not an AL project, cannot retrieve T2 for it.");
		}
		sdm.getUnicodeText("project", "AL_t2_file", "project_id='" + projectID
				+ "'", outFile.toString());

	}

	public void exportIOB(int projectID, File exportDir) {
		if (!exportDir.isDirectory() || !exportDir.canWrite()) {
			throw new RuntimeException(
					"Error: specified directory does not exist or is not writeable!");
		}
	}

	/**
	 * gets the contents for the words.dtd and writes it to the specified file
	 */
	public void writeWordDTDFile(File outFile) {
		IOUtils.writeFile(WORD_DTD, outFile);
	}

	/**
	 * gets the contents for the markables.dtd and writes it to the specified
	 * file
	 */
	public void writeMarkableDTDFile(File outFile) {
		IOUtils.writeFile(MARKABLE_DTD, outFile);
	}

	/**
	 * get log entries this is basically called from class LogEntryReader.java
	 */

	public ArrayList<LogEntry> getLogEntries(String whereClause,
			String sortClause, String limitClause) {
		ArrayList<LogEntry> logs = new ArrayList<LogEntry>();

		String query = "SELECT "
				+ "level, message, sourceClass, sourceMethod, timeEntered, sequence "
				+ "FROM log " + whereClause + " " + sortClause + " "
				+ limitClause;
		ResultSet rs = null;
		try {
			rs = sdm.executeQuery(query);
			while (rs.next()) {
				logs.add(new LogEntry(rs.getString("level"), rs
						.getString("message"), rs.getString("sourceClass"), rs
						.getString("sourceMethod"), rs.getTimestamp(
						"timeEntered").toString(), rs.getInt("sequence")));
			}
			rs.close();

			return logs;
		} catch (SQLException e) {
			throw new RuntimeException("error getting log entries", e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * estimated the time that will be needed to run AL selection this is to be
	 * shown in AnnoClient, when AL button was pressed. This estimation is based
	 * on the last selection times needed by AL.
	 * 
	 * @return time in minutes
	 */
	public int estimateSelectionTime(int projectID) {

		if (!isALProject(projectID))
			throw new IllegalArgumentException(
					"can only estimate selection time for AL projects.");

		// get corpus fraction
		String query = "SELECT AL_corpusfraction FROM project WHERE project_id='"
				+ projectID + "'";
		ResultSet rs = null;
		try {
			rs = sdm.executeQuery(query);
			boolean success = rs.first(); 
			if (success == false)
				throw new IllegalArgumentException(
						"No data retrieved from database (PID="
								+ projectID + ")");
			
			double corpusFraction = rs.getDouble("AL_corpusfraction");
			corpusFraction *= 1.01; // hack as mysql does not understand '='
			// operator
			query = "SELECT sentences, seltime FROM AL_statistics"
					+ " WHERE project_id='" + projectID
					+ "' AND corpus_fraction<=" + corpusFraction
					+ " ORDER BY stat_id ASC";
			rs = sdm.executeQuery(query);

			ArrayList<Integer> x = new ArrayList<Integer>();
			ArrayList<Integer> y = new ArrayList<Integer>();

			while (rs.next()) {
				x.add(rs.getInt("sentences"));
				y.add(rs.getInt("seltime"));
			}

			double[] xValues = new double[x.size()];
			double[] yValues = new double[y.size()];
			for (int i = 0; i < xValues.length; i++) {
				xValues[i] = (double) i + 1; // which round
				yValues[i] = (double) y.get(i); // selection time needed
			}

			double new_x = xValues.length + 1; // next round
			double new_y = PolynomialFit.extrapolate(xValues, yValues, new_x);

			int estimation = (int) (new_y / 60);
			return estimation;

		} catch (SQLException e) {
			throw new RuntimeException("estimating selection time failed", e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public ArrayList<MmaxData> getMmaxDataList(int projectID) {
		ArrayList<MmaxData> mmaxdata = new ArrayList<MmaxData>();
		ResultSet rs = null;
		try {
			String query2 = "SELECT mmaxdata_id FROM mmaxdata WHERE project_id='"
					+ projectID + "' ORDER BY main_level DESC, at_startup ASC";
			rs = sdm.executeQuery(query2);
			while (rs.next()) {
				int mmaxdata_id = rs.getInt("mmaxdata_id");
				MmaxData m = getMmaxData(mmaxdata_id);
				mmaxdata.add(m);
			}
		} catch (SQLException e) {
			throw new RuntimeException("Retrieving MMAX data list failed (PID="
					+ projectID + ")", e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return mmaxdata;
	}

	public ResultSet getProjectInfo(String uri) {
		ResultSet rs = null;
		try {
			rs = sdm
					.executeQuery("SELECT name, user_id FROM project p WHERE p.project_id IN "
							+ "(SELECT project_id FROM basedata WHERE uri = "
							+ uri + ")");
		} catch (SQLException e) {
			throw new RuntimeException("getting project data failed", e);
		}
		return rs;
	}

	public ResultSet getCompleteUserName(String userID) {
		try {
			ResultSet userRS = sdm.executeQuery("SELECT complete_name "
					+ "FROM user u WHERE u.user_id = " + userID);
			return userRS;
		} catch (SQLException e) {
			throw new RuntimeException("getting complete user name failed", e);
		}
	}

	/**
	 * this runs arbitrary SQL statements. Should rather be avoided! Use it only
	 * when you really know what you are doing or when it is only for a small
	 * test.
	 * 
	 * @param query your SQL statement
	 */
	public ResultSet runSQLQuery(String query) {
		try {
			ResultSet rs = sdm.executeQuery(query);
			return rs;
		} catch (SQLException e) {
			throw new RuntimeException("sql query failed", e);
		}
	}
}
