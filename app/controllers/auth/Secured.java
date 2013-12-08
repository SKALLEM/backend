package controllers.auth;

import play.mvc.Http.Context;
import play.mvc.Result;
import play.mvc.Security;
import utils.UserUtils;


public class Secured extends Security.Authenticator {
    
    @Override
    public String getUsername(Context ctx) {
    	return UserUtils.getUsername(ctx.request());
    }
    
    @Override
    public Result onUnauthorized(Context ctx) {
        return unauthorized("{\"err\":\"unauthorized\"}");
    }
    
    
}