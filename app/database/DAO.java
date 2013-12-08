package database;

import play.Play;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;


public class DAO {

	private static DAO instance = null;
	Mongo m;
	DB db;
	DBCollection users;
	DBCollection walkings;
	DBCollection pois;

	private DAO() {
		String url = Play.application().configuration().getString("mongourl");
		try {
			m = new MongoClient( new MongoClientURI(url) );
		} catch (Exception e) {
			e.printStackTrace();
		} 

		db = m.getDB(url.substring(1+url.lastIndexOf('/')));
		users = db.getCollection("users");
		
		walkings = db.getCollection("walkings");
		pois = db.getCollection("pois");
		
		
		users.ensureIndex(new BasicDBObject("coords", "2d"));
		
	}
	public static DAO get() {

		if (instance == null)
			instance = new DAO();
		return instance;
	}
	
	public DB db() {
		return db;
	}
	
	public DBCollection getCollection(String name) {
		return get().getCollection(name);
	}
	
	public DBCollection getUsers() {
		return users;
	}

	public DBCollection getPois() {
		return pois;
	}
	public DBCollection getWalkings() {
		return walkings;
	}
	
}
