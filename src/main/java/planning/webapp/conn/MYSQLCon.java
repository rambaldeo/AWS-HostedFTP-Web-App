package planning.webapp.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MYSQLCon {
	public static Connection getMySQLConnection() throws ClassNotFoundException, SQLException {
		String hostName = System.getenv("SQL_hostName");
		String dbName = System.getenv("SQL_dbName");
		String userName = System.getenv("SQL_userName");
		String password = System.getenv("SQL_password"); 

		return getMySQLConnection(hostName, dbName, userName, password);
	}

	public static Connection getMySQLConnection(String hostName, String dbName, String userName, String password)
			throws SQLException, ClassNotFoundException {

		Class.forName("com.mysql.cj.jdbc.Driver");

		String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;
		// mongodb+srv://RamBaldeo:<db_password>@testcluster.c9idvlz.mongodb.net/?retryWrites=true&w=majority&appName=TestCluster

		Connection conn = DriverManager.getConnection(connectionURL, userName, password);
		System.out.println("MYSQLCON: Connection has been made");
		return conn;
	}
}
