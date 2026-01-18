package planning.webapp.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import planning.webapp.UserAccounts;
import planning.webapp.encryption.Encrypt;

public class DBUtils {
	public static UserAccounts findUser(Connection conn, String userName, String password) throws Exception {

		// Test sql with new encryption
		String testSQL = "SELECT password, userDescription, saltKey FROM new_table WHERE username=?";
		PreparedStatement testQuery = conn.prepareStatement(testSQL);
		testQuery.setString(1, userName);
		try (ResultSet testRS = testQuery.executeQuery()) {
			if (testRS.next()) {
				String passwordDB = testRS.getNString("password");
				String testDescription = testRS.getString("userDescription");
				String saltKeyDB = testRS.getString("saltKey");
				boolean isSame = Encrypt.isExpectedPassword(passwordDB, password, saltKeyDB);
				if (isSame) {
					UserAccounts testUser = new UserAccounts();
					testUser.setUserName(userName);
					testUser.setPassword(passwordDB);
					testUser.setDescription(testDescription);
					return testUser;
				}
			}
		}

		return null;
	}

	public static UserAccounts findUser(Connection conn, String userName) throws SQLException {
		String sql = "SELECT * from new_table WHERE username=?";
		PreparedStatement query = conn.prepareStatement(sql);
		query.setString(1, userName);
		ResultSet rs = query.executeQuery();
		if (rs.next()) {
			String password = rs.getString("password");
			String firstName = rs.getString("firstName");
			String Lastname = rs.getString("lastName");
			String description = rs.getString("userDescription");
			UserAccounts user = new UserAccounts();
			user.setUserName(userName);
			user.setPassword(password);
			user.setDescription(description);
			return user;
		}

		// test with
		return null;

	}

	public static UserAccounts addUser(Connection conn, String firstName, String lastName, String userName,
			String password) throws Exception {

		// Test scripts with new encryption
		String testSQL = "INSERT INTO new_table (`username`, `password`, `firstName`, `lastName`, `saltKey`) VALUES(?,?,?,?,?)";
		PreparedStatement testQuery = conn.prepareStatement(testSQL);
		testQuery.setString(1, userName);
		String saltKey = Encrypt.getNextSalt();
		String encryptedPassword = Encrypt.encrypt(password, saltKey);
		testQuery.setString(2, encryptedPassword);
		testQuery.setString(3, firstName);
		testQuery.setString(4, lastName);
		testQuery.setString(5, saltKey);
		int TestRowInserted = testQuery.executeUpdate();
		System.out.println("Rows Inserted:" + TestRowInserted);
		UserAccounts testUser = new UserAccounts();
		testUser.setFirstName(firstName);
		testUser.setLastName(lastName);
		testUser.setUserName(userName);
		testUser.setPassword(encryptedPassword);
		return testUser;

	}

	public static void updateDescription(Connection conn, String userName, String description) throws SQLException {
		// TODO Auto-generated method stub

		// Test Script
		String testSQL = "UPDATE new_table SET userDescription = ? WHERE username = ?";
		PreparedStatement testQuery = conn.prepareStatement(testSQL);
		testQuery.setString(1, description);
		testQuery.setString(2, userName);
		int testRows = testQuery.executeUpdate();
		if (testRows > 0) {
			System.out.println("User Description updated");
		}

	}
}
