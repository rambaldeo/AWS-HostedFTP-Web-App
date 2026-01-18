package planning.webapp.conn;

import java.sql.SQLException;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import planning.webapp.AboutPageDescriptions;
import planning.webapp.UserAccounts;
import planning.webapp.encryption.Encrypt;

public class MongoDBUtils {

	public static UserAccounts addUserAccount(String firstName, String lastName, String userName, String password)
			throws Exception {

		MongoDatabase db = MongoDBConn.getDatabase();
		MongoCollection<Document> users = db.getCollection("users");

		// check if user exists
		Document existing = users.find(new Document("userName", userName)).first();
		if (existing != null) {
			throw new Exception("Username already taken.");
		}
		String saltKey = Encrypt.getNextSalt();
		String encryptedPassword = Encrypt.encrypt(password, saltKey);
		// Create new user
		Document newUser = new Document("firstName", firstName).append("lastName", lastName)
				.append("userName", userName).append("password", encryptedPassword).append("saltKey", saltKey)
				.append("userDescription", "");
		users.insertOne(newUser);

		UserAccounts user = new UserAccounts();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setUserName(userName);
		user.setPassword(encryptedPassword);

		return user;
	}

	public static UserAccounts findUser(String userName, String password) throws ClassNotFoundException, SQLException {
		MongoDatabase db = MongoDBConn.getDatabase();
		MongoCollection<Document> users = db.getCollection("users");
		Document doc = users.find(new Document("userName", userName)).first();
		if (doc != null) {
			String storedPassword = doc.getString("password");
			String saltKey = doc.getString("saltKey");
			if (Encrypt.isExpectedPassword(storedPassword, password, saltKey)) {
				UserAccounts user = new UserAccounts();
				user.setUserName(userName);
				user.setFirstName(doc.getString("firstName"));
				user.setLastName(doc.getString("lastName"));
				user.setDescription(doc.getString("userDescription"));
				return user;
			}
		}

		return null;
	}

	public static UserAccounts findUser(String userName) throws Exception {
		MongoDatabase db = MongoDBConn.getDatabase();
		MongoCollection<Document> users = db.getCollection("users");

		Document doc = users.find(new Document("userName", userName)).first();
		if (doc != null) {
			UserAccounts user = new UserAccounts();
			user.setUserName(userName);
			user.setFirstName(doc.getString("firstName"));
			user.setLastName(doc.getString("lastName"));
			user.setDescription(doc.getString("userDescription"));
			return user;
		}

		return null;
	}

	public static void updateDescription(String userName, String userDescription) {
		MongoDatabase db = MongoDBConn.getDatabase();
		MongoCollection<Document> users = db.getCollection("users");
		users.updateOne(new Document("userName", userName),
				new Document("$set", new Document("userDescription", userDescription)));
	}

	public static AboutPageDescriptions getDescrtiption(String option) {
		MongoDatabase db = MongoDBConn.getDatabase();
		MongoCollection<Document> selectedDescriptions = db.getCollection("AboutDescription");

		Document doc = selectedDescriptions.find(new Document("value", option)).first();
		if(doc != null) {
			AboutPageDescriptions description = new AboutPageDescriptions();
			description.setOptionName(option);
			description.setDescription(doc.getString("value"));
			return description;
		}

		return null;
	}

}
