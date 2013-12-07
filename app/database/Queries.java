package database;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;



public class Queries {
	
	
	public static String createWalking(String name, String descr, Integer level) {
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
