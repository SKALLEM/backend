package utils;

import play.libs.Crypto;
import play.mvc.Http.Request;

public class UserUtils {

	public static String getUsername(Request r) {
		String h = r.getHeader("x-auth");
    	if (h!=null) {
    		String [] user = h.split(":");
    		if(user[1].equals(Crypto.sign(user[0])))return user[0];
    	}
    	return null;
	}
}
