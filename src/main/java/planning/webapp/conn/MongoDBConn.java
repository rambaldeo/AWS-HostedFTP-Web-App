package planning.webapp.conn;

import org.bson.Document;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDBConn {

	private static MongoClient mongoClient;
	private static MongoDatabase database;
	private static String username = System.getenv("DB_USER"); 
	private static String password = System.getenv("DB_PASSWORD"); 

	static {
		try {
			String uri = "mongodb+srv://" + username + ":" + password
					+ "@testcluster.c9idvlz.mongodb.net/?retryWrites=true&w=majority&appName=TestCluster";

			// Server API versioning (recommended for Atlas)
			ServerApi serverApi = ServerApi.builder().version(ServerApiVersion.V1).build();

			MongoClientSettings settings = MongoClientSettings.builder()
					.applyConnectionString(new ConnectionString(uri)).serverApi(serverApi).build();

			mongoClient = MongoClients.create(settings);
			database = mongoClient.getDatabase("JavaWebApp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static MongoDatabase getDatabase() {
		return database;
	}

	public static void closeQuietly() {
		try {
			if (mongoClient != null) {
				mongoClient.close();
			}
		} catch (Exception e) {

		}
	}

	public static void main(String[] args) {
		MongoCollection<Document> users = database.getCollection("users");

	}
}
