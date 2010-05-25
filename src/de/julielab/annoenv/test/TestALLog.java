package de.julielab.annoenv.test;

import java.sql.Timestamp;

import de.julielab.annoenv.db.sql.SQLDatabaseManager;
import de.julielab.annoenv.db.sql.SQLFunctions;

public class TestALLog {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String pid = "162";
		SQLDatabaseManager sdm = new SQLDatabaseManager();
		SQLFunctions sf = new SQLFunctions(sdm);
		Timestamp startTime = sf.getDBSystemTime();
		System.out.println(startTime);
		startTime.setHours(10);
		System.out.println(startTime);
		sf.getALLog(162, startTime);

	}

}
