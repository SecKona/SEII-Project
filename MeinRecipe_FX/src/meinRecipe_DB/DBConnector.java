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
	public static boolean connectToDB() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cookbook", "root", "Sec.Kona233");
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	/**
	 * Disconnect operation of DB
	 * 
	 */
	public static boolean disconnectToDB() {
		try {
			con.close();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
}
