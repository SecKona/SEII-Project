package meinRecipe_DB;

import java.sql.*;

/**
 * Connector class to encapsulate operations of connection to data base
 * 
 * @author SecKona
 *
 */
public class DBConnector {

	protected static Connection con;

	/**
	 * Connect operation of DB
	 * 
	 */
	public static void connectToDB() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cookbook", "root", "Sec.Kona233");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Disconnect operation of DB
	 * 
	 */
	public static void disconnectToDB() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
