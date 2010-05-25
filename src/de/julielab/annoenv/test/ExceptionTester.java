package de.julielab.annoenv.test;

import java.io.IOException;
import java.sql.SQLException;

import de.julielab.annoenv.utils.ExceptionUtils;


public class ExceptionTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		try {
			throw new IOException("EX1");
		} catch (IOException e) {
			try {
				throw new SQLException("EX2",e);
			} catch (SQLException e1) {
				try {
					throw new SQLException("Ex3",e1);
				} catch (SQLException e2) {
					//e2.printStackTrace();
					String t = ExceptionUtils.getCausesFromException(e2);
					System.out.println("text:" + t);
				}
				
			}
		}
	}

}
