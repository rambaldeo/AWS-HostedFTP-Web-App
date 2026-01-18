import java.sql.Connection;
import java.sql.DriverManager;

public class testDB {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url = "jdbc:mysql://localhost:3306/testdb";
		String user = "root";
		String password = "201059737";

		try (Connection conn = DriverManager.getConnection(url, user, password)) {
			System.out.println("Connection to MySQL was successful");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
