package controllers;

import com.mongodb.DBObject;

import play.mvc.Controller;
import play.mvc.Result;

public class LocationController extends Controller {

	public final double radius = 10d/3959;
	
    public static Result location(String lat, String lon) {


        return ok();
    }
	
}
