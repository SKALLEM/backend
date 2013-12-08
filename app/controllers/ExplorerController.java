package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import utils.KV;
import utils.ResultObj;
import utils.UserUtils;
import controllers.auth.Secured;
import data.POI;
import database.Queries;


@Security.Authenticated(Secured.class)
public class ExplorerController extends Controller {

	public final static double radius = 10d/3959;
	
    public static Result location(String lat, String lon) {

    	String user = UserUtils.getUsername(request());

    	POI p = null;
		try {
			String currentWalking = Queries.getCurrentWalking(user);
			p = Queries.getClosePois(currentWalking, Double.valueOf(lat), Double.valueOf(lon), radius);
		} catch (Exception e) {
			return internalServerError(e.getClass()+": "+e.getMessage());
		}
    	
    	if(p==null)
    		return ok();
    	else    	
    		return ok(p.toJson());
    }
    
	public static Result joinWalking(String walkingId) {
		String user = UserUtils.getUsername(request());
		
		try {
			Queries.joinWalking(user, walkingId);
			return ok(ResultObj.json(new KV("success", true)));
		} catch (Exception e) {
			return internalServerError(ResultObj.json(new KV("err", e
					.getClass())));
		}

	}
	
}
