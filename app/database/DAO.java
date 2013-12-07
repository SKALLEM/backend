package database;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;


public class DAO {

	private static DAO instance = null;
	Mongo m;
	DB db;
	DBCollection users;
	DBCollection walkings;
	DBCollection pois;

	private DAO() {
		try {
			m = new Mongo("127.0.0.1");
		} catch (Exception e) {
			e.printStackTrace();
		} 
		db = m.getDB("explorer");
		users = db.getCollection("users");
		
		walkings = db.getCollection("walkings");
		pois = db.getCollection("pois");
		
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
