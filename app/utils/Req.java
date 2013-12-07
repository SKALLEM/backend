package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import play.libs.Crypto;
import play.mvc.Http.Request;


public class Req {

	public static String get(Request req, String key) {
		if (req.body().asFormUrlEncoded()==null)return null;
		String[] val = req.body().asFormUrlEncoded().get(key);
		if (val==null)return null;
		String out = "";
		for (int i = 0; i < val.length ; i++) out+=val[i];
		return out;
	}
	
	public static List<String> getList(Request req, String key) {
		if (req.body().asFormUrlEncoded()==null)return null;
		String[] val = req.body().asFormUrlEncoded().get(key);
		if (val==null)return new ArrayList<String>();
		return Arrays.asList(val);
	}
	
	public static Set<String> getSet(Request req, String key) {
		if (req.body().asFormUrlEncoded()==null)return null;
		String[] val = req.body().asFormUrlEncoded().get(key);
		if (val==null)return new HashSet<String>();
		return new HashSet<String>(Arrays.asList(val));
	}
	
	
	
	
	public static String getUserId(Request req) {
		//System.out.println("COOKIE: "+req.cookie("uid"));
		if (req.cookie("uid")!=null) {
	    	String uid = req.cookie("uid").value();
	    	int pos = uid.indexOf("-");
	    	String crypted = uid.substring(0, pos);
	    	String id = uid.substring(pos+1, uid.length());
	    	
	    	if(Crypto.sign(id).equals(crypted))
	    		return id;
    	}
		return null;
	}
	
	
	
}
