package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Your new application is read."));
    }
    
    public static Result helloWord() {
    	return ok("Hello Word!");
    }

}
