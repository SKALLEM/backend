package controllers;

import database.Queries;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import utils.KV;
import utils.Req;
import utils.ResultObj;
import views.html.index;

public class WalkingController extends Controller {

    public static Result createWalking() {  
    	
    	try {
        	String name = Req.get(request(), "name");
        	String descr = Req.get(request(), "description");
        	Integer level = Integer.valueOf(Req.get(request(), "level"));
        	
        	String id = Queries.createWalking(name, descr, level);
        	
            return ok(ResultObj.json(new KV("id", id)));
    	}catch (Exception e){
    		return internalServerError(ResultObj.json(new KV("err", e.getMessage())));
    	}
    	

    }
    
    
    public static Result createPoi() {  
    	
    	String name = Req.get(request(), "name");
    	String descr = Req.get(request(), "description");
    	Integer level = Integer.valueOf(Req.get(request(), "level"));
    	
    	String id = Queries.createWalking(name, descr, level);
    	
        return ok(ResultObj.json(new KV("id", id)));
    }
	
}
