package database;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;

import play.libs.Json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import data.POI;
import data.Walking;



public class Queries {
	
	static ObjectMapper mapper = new ObjectMapper();
	
	public static String createPoi(String walkingId, POI p) throws Exception {
		
		Map<String,Object> poi = mapper.readValue(p.toJson(), Map.class);
		poi.put("walkingId", walkingId);
		
		DBCollection pois = DAO.get().getPois();
		DBObject pObj = new BasicDBObject(poi);
		pois.save(pObj);
		return pObj.get("_id").toString();
	}
	
	
	public static String createWalking(Walking walking) throws Exception {
		
		Map<String,Object> wMap = mapper.readValue(walking.toJson(), Map.class);
		
		DBCollection walkings = DAO.get().getWalkings();
		DBObject w = new BasicDBObject(wMap);
		walkings.save(w);
		return w.get("_id").toString();
	}
	
	
	
	
	public static Walking getWalking(String walkingId) throws Exception {
		DBCollection walkings = DAO.get().getWalkings();
		DBObject q = new BasicDBObject("_id", new ObjectId(walkingId));
		DBObject w = walkings.findOne(q);
		w.put("id", w.get("_id").toString());
		JsonNode jsn = Json.toJson(w);
		Walking walking = Walking.fromJson(jsn.toString());
		
		DBCollection pois = DAO.get().getPois();
		DBCursor c = pois.find(new BasicDBObject("walkingId", walkingId)).sort(new BasicDBObject("_id", 1));
		for (DBObject u : c) {
			u.put("id", u.get("_id").toString());
			walking.getPois().add(
						POI.fromJson(Json.toJson(u).toString())
					);
		}
		walking.setCount(c.size());
		return walking;
	}
	
	
	public static POI getPoi(String id) throws Exception {
		DBCollection pois = DAO.get().getPois();
		DBObject p = new BasicDBObject("_id", new ObjectId(id));
		DBObject out = pois.findOne(p);
		out.put("id", out.get("_id").toString());
		
		POI poi = POI.fromJson(Json.toJson(out).toString());
		
		return poi;
	}
	
	
	
	public static boolean login(String username, String password) {
		DBCollection users = DAO.get().getUsers();
		
		DBObject q = new BasicDBObject("username", username);
		q.put("password", password);
		return (users.findOne(q)!=null);
		
	}
	
	public static boolean registerUser(String name, String username, String password) {
		DBCollection users = DAO.get().getUsers();

		DBObject q = new BasicDBObject("username", username);
		if(users.findOne(q)==null) {
			q.put("name", name);
			q.put("password", password);
			users.save(q);
			return true;
		}
		return false;
		
	}
	
	public static void joinWalking(String user, String id) {
		DBCollection users = DAO.get().getUsers();

		DBObject q = new BasicDBObject("username", user);
		DBObject u = users.findOne(q);
		u.put("currentWalking", id);
		users.save(u);
		
	}
	
	public static POI getClosePois(String walkingId, Double lat, Double lon, Double radius) throws Exception {
		DBCollection pois = DAO.get().getPois();
		
		DBObject q = new BasicDBObject("loc", new BasicDBObject("$geoWithin", new BasicDBObject("$centerSphere", new Object[]{new Double[]{lat,lon},radius})));
		q.put("walkingId", walkingId);
		DBObject dbo = pois.findOne(q);
		if(dbo==null)return null;
		dbo.put("id", dbo.get("_id").toString());
		return POI.fromJson(Json.toJson(dbo).toString());
	}
	
	public static String getCurrentWalking(String user) throws Exception {
		DBCollection users = DAO.get().getUsers();

		DBObject q = new BasicDBObject("username", user);
		DBObject u = users.findOne(q);
		
		return (u.get("currentWalking")!=null)?u.get("currentWalking").toString():"";
	}
	
	
	
	public static List<Walking> getWalking() throws Exception {
		DBCollection pois = DAO.get().getPois();
		List<Walking> result = new ArrayList<Walking>();
		DBCollection walkings = DAO.get().getWalkings();
		DBCursor c = walkings.find();
		for (DBObject w : c) {
			w.put("id", w.get("_id").toString());
			JsonNode jsn = Json.toJson(w);
			Walking walking = Walking.fromJson(jsn.toString());
			
			
			DBCursor pc = pois.find(new BasicDBObject("walkingId", w.get("id")));
			walking.setCount(pc.size());
			
			result.add(walking);
		}

		return result;
	}
	
	
	
	
}
