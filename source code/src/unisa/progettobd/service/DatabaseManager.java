package unisa.progettobd.service;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public abstract class DatabaseManager {

	public static MongoCollection<Document> getIscritti() {
		return getDB().getCollection(iscritti);
	}

	public static MongoCollection<Document> getLaureati() {
		return getDB().getCollection(laureati);

	}

	public static void close() {
		if (client != null) client.close();
		client = null;
	}
	
	private static MongoDatabase getDB() {
		if(client == null) client = new MongoClient("localhost", 27017);
		return client.getDatabase(dbName);
	}

	private static MongoClient client = null;
	private static final String dbName = "ProgettoBD2";
	private static final String iscritti = "coll_iscritti";
	private static final String laureati = "coll_laureati";
}
