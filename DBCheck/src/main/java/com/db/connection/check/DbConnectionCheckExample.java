
package com.db.connection.check;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author APURVA MAHAJAN
 *
 */
public class DbConnectionCheckExample {

	public static void main(String[] args) {

		// local variables
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		// Step 1: Loading or
		// registering IBM DB2 JDBC driver class
		try {
			Class.forName("com.ibm.db2.jcc.DB2Driver");
		} catch (ClassNotFoundException cnfex) {
			System.out.println("Problem in" + " loading or registering IBM DB2 JDBC driver");
			cnfex.printStackTrace();
		}

		// Step 2: Opening database connection
		try {

			// Step 2.A: Create and
			// get connection using DriverManager class
			connection = DriverManager.getConnection("jdbc:db2://localhost:50002/benchresources", "test", "test@123");

			// Step 2.B: Creating JDBC Statement
			statement = connection.createStatement();

			// Step 2.C: Executing SQL & retrieve data into ResultSet
			resultSet = statement.executeQuery("SELECT * FROM PLAYER");

			System.out.println("ID\tName\t\t\tAge\tMatches");
			System.out.println("==\t================\t===\t=======");

			// processing returned data and printing into console
			while (resultSet.next()) {
				System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getInt(3)
						+ "\t" + resultSet.getInt(4));
			}

		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
		} finally {
			// Step 3: Closing database connection
			try {
				if (null != connection) {
					// cleanup resources, once after processing
					resultSet.close();
					statement.close();

					// and then finally close connection
					connection.close();
				}
			} catch (SQLException sqlex) {
				sqlex.printStackTrace();
			}
		}
	}
} 
