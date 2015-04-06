package controllers;

import java.util.List;
import java.util.Map;

import models.District;
import play.Routes;
import play.mvc.Controller;
import play.mvc.Result;

import com.google.gson.Gson;

public class DistrictFunctions extends Controller {
	
	public static Result getDistByDiv(Long id){
		Map<Long,String> listDistrict =  District.getDistrictByDivId(id);
		Gson gson = new Gson();
		String toJSON = gson.toJson(listDistrict);
		return ok(toJSON);
		
	}
	
	public static Result javascriptRoutes() {
	    response().setContentType("text/javascript");
	    return ok(
	        Routes.javascriptRouter("jsRoute",routes.javascript.DistrictFunctions.getDistByDiv())
	    );
	}


}