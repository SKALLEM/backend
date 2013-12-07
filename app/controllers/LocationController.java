package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

public class LocationController extends Controller {

    public static Result location(String lat, String lon) {
        return ok("ciao");
    }
	
}
