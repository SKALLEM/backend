package controllers;

import play.mvc.Controller;
import play.mvc.Result;

public class LocationController extends Controller {

    public static Result location(String lat, String lon) {
        return ok("ciao");
    }
	
}
