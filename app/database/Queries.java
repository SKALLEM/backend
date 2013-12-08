package database;

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
	
	/*
	
	public static DBObject getPoi(String id) {
		DBCollection pois = DAO.get().getPois();
		DBObject p = new BasicDBObject("_id", new ObjectId(id));
		DBObject out = pois.findOne(p);
		
		
		out.put("id", out.get("_id").toString());
		out.removeField("_id");
		return out;
	}
	
	
	public static List<DBObject> getPOIsByWalkingId(String walkingId) {
		DBCollection pois = DAO.get().getPois();
		DBObject q = new BasicDBObject("_id", new ObjectId(walkingId));

		return pois.find(q).sort(new BasicDBObject("_id", 1)).toArray();


	}
	
	
	
	
	
	public static boolean registerUser(String name, String username, String password) {
		DBCollection users = DAO.get().getUsers();

		DBObject q = new BasicDBObject("username", username);
		if(users.findOne(q)==null) {
			q.put("name", name);
			q.put("password", password);
			return true;
		}
		return false;
		
	}
	
	public static boolean login(String username, String password) {
		DBCollection users = DAO.get().getUsers();
		
		DBObject q = new BasicDBObject("username", username);
		q.put("password", password);
		return (users.findOne(q)!=null);
		
	}
	
	
	
	public static DBObject getClosePois(Double lat, Double lon, Double radius) {
		DBCollection pois = DAO.get().getPois();
		
		DBObject q = new BasicDBObject("loc", new BasicDBObject("$geoWithin", new BasicDBObject("$centerSphere", new Object[]{new Double[]{lat,lon},radius})));

		return pois.findOne(q);
		
	}
	
	
	
	/*public static String createWalking(String name, String descr, Long level) {
		DBCollection walkings = DAO.get().getWalkings();

		DBObject w = new BasicDBObject("name", name);
		w.put("description", descr);
		w.put("level", level);
		
		walkings.save(w);

		
		return w.get("_id").toString();
	}
	
	/*
	
	public static String createConversation(String owner, String title, List<String> users) {
		DBCollection conv = DAO.get().getConversations();

		users.add(owner);
		
		DBObject c = new BasicDBObject("owner", owner);
		c.put("title", title);
		c.put("users", users);
		c.put("seq", 0);
		
		conv.save(c);
		
		return c.get("_id").toString();
	}
	
	public static String addImage(String user, String conversation, String original, String thumb) {
		DBCollection images = DAO.get().getImages();

		
		
		DBObject i = new BasicDBObject("original", original);

		i.put("thumb", thumb);
		i.put("conversation", conversation);
		i.put("date", System.currentTimeMillis());
		i.put("user", user);

		images.save(i);
		
		return i.get("_id").toString();
	}
	
	
	
	public static List<Message> get(String conversation, String from) {
		DBCollection images = DAO.get().getImages();

		
		
		DBObject q = new BasicDBObject("conversation", conversation);
		q.put("_id", new BasicDBObject("$gt", new ObjectId(from)));
		
		DBCursor dbc = images.find(q).sort(new BasicDBObject("time", 1));
		List<Message> out = new ArrayList<Message>();
		for (DBObject o : dbc) {
			Message m = new Message(o.get("_id").toString(), 
									o.get("original").toString(), 
									o.get("thumb").toString(), 
									o.get("user").toString(), 
									conversation, 
									(Long)o.get("date"));
			out.add(m);
		}
		return out;
	}
	
	
	public static Conversation getConversation(String id) {
		DBCollection conv = DAO.get().getConversations();

		
		
		DBObject q = new BasicDBObject("_id", new ObjectId(id));

		DBObject dbo = conv.findOne(q);
		
		Conversation c = new Conversation(dbo.get("_id").toString(), dbo.get("title").toString(), (List<String>)dbo.get("users"), ((ObjectId)dbo.get("_id")).getTime());
		
		
		return c;
	}

	
	@SuppressWarnings("unchecked")
	public static List<DBObject> getUsersInConversation(String id) {
		DBCollection conv = DAO.get().getConversations();

		BasicDBObject q = new BasicDBObject("_id", new ObjectId(id));
		DBObject res = conv.findOne(q);
		List<String> users = (List<String>) res.get("users");

		List<DBObject> out = new ArrayList<DBObject>();
		
		DBCollection usersCollection = DAO.get().getUsers();
		
		DBCursor c = usersCollection.find(new BasicDBObject("id", new BasicDBObject("$in", users)));
		
		for (DBObject u : c) {
			out.add(u);
		}
		
		
		return out;
	}
	
	public static List<Conversation> getConversationsList(String user) {
		DBCollection conv = DAO.get().getConversations();

		BasicDBObject q = new BasicDBObject("users", user);
		
		DBCursor dbc = conv.find(q);
		List<Conversation> out = new ArrayList<Conversation>();
		for (DBObject dbo : dbc) {
			@SuppressWarnings("unchecked")
			Conversation c = new Conversation(dbo.get("_id").toString(), dbo.get("title").toString(), (List<String>)dbo.get("users"), ((ObjectId)dbo.get("_id")).getTime());
			out.add(c);
		}
		

		return out;
	}
	
	
	
	public static void saveUserInfo(User user, String type, String pushId) {
		DBCollection users = DAO.get().getUsers();

		DBObject u = getUserById(user.getId());
		if (u==null)u = new BasicDBObject();
		u.put("id", user.getId());
		u.put("name", user.getName());
		u.put("bio", user.getBio());
		u.put("email", user.getEmail());
		u.put("timezone", user.getTimezone());
		u.put("username", user.getUsername());
		u.put("birthday", user.getBirthdayAsDate());
		u.put("interestedIn", user.getInterestedIn());		
		u.put("about", user.getAbout());
		
		u.put("type", type);
		u.put("pushId", pushId);
		
		users.save(u);
	}
	
	
	
	public static DBObject getUserById(String id) {
		DBCollection users = DAO.get().getUsers();

		DBObject q = new BasicDBObject("id", id);
		return users.findOne(q);
	
	}
	
	
	
	
	
	public static List<Message> getMessages(String user, String from) {
		DBCollection conversations = DAO.get().getConversations();
		DBObject q = new BasicDBObject("users", user);
		List<String> conv = new ArrayList<String>();
		for (DBObject o : conversations.find(q)) 
			conv.add(o.get("_id").toString());
		
		
		q = new BasicDBObject("conversation", new BasicDBObject("$in", conv));
		q.put("id", new BasicDBObject("$gt", new ObjectId(from)));
		
		DBCursor c = conversations.find(q);
		
		List<Message> out = new ArrayList<Message>();
		for (DBObject o : c) {
			Message m = new Message(o.get("_id").toString(), 
									o.get("original").toString(), 
									o.get("thumb").toString(), 
									o.get("fuser").toString(), 
									o.get("user").toString(), 
									(Long)o.get("date"));
			out.add(m);
		}
		return out;
		
		
	}
		*/
	
	
}
