package controllers;

import java.util.List;

import models.District;
import play.Routes;
import play.mvc.Controller;
import play.mvc.Result;

public class DistrictFunctions extends Controller {
	
	public static Result getDistrictByDivId(Long id){
		//return District.getDistrictByDivId(id);
		return ok(""+id);
		
	}
	
	public static Result javascriptRoutes() {
	    response().setContentType("text/javascript");
	    return ok(
	        Routes.javascriptRouter("getDistrictByDivId",routes.javascript.DistrictFunctions.getDistrictByDivId())
	    );
	}


}
