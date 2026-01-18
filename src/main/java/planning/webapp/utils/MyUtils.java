package planning.webapp.utils;

import java.sql.Connection;

import com.mongodb.client.MongoDatabase;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpSession;
import planning.webapp.UserAccounts;

public class MyUtils {

	public static final String ATT_NAME_CONNECTION = "ATTRIBUTE_FOR_CONNECTION";
	private static final String ATT_NAME_USER_NAME = "ATTRIBUTE_FOR_STORE_USER_NAME_IN_COOKIE";

	public static void storeConnection(ServletRequest request, Connection conn) {
		request.setAttribute(ATT_NAME_CONNECTION, conn);
	}

	public static Connection getStoredConnection(ServletRequest request) {
		Connection conn = (Connection) request.getAttribute(ATT_NAME_CONNECTION);
		return conn;
	}

	public static void storeLoginUser(HttpSession session, UserAccounts loginedUser) {
		session.setAttribute("loginedUser", loginedUser);
	}

	public static UserAccounts getLoginedUser(HttpSession session) {
		UserAccounts loginedUser = (UserAccounts) session.getAttribute("loginedUser");
		return loginedUser;
	}

	public static void storeMongoConnection(ServletRequest request, MongoDatabase db) {
		// TODO Auto-generated method stub
		request.setAttribute(ATT_NAME_CONNECTION, db);
	}

	public static MongoDatabase getMongoConnection(ServletRequest request) {
		MongoDatabase db = (MongoDatabase) request.getAttribute(ATT_NAME_CONNECTION);
		return db;
	}

}
