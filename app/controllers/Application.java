package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok("08 Dec 2013 - RHoK Trento: Little explorer!");
    }

}
