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
	 * @throws SQLException
	 * 
	 */
	public static void connectToDB() throws SQLException {
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cookbook", "root", "Sec.Kona233");
	}

	/**
	 * Disconnect operation of DB
	 * 
	 * @throws SQLException
	 * 
	 */
	public static void disconnectToDB() throws SQLException {
		con.close();
	}
}
