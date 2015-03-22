package controllers;

import java.util.List;

import models.admission.University;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import utils.AppConstants;  
import views.html.university.*;
/*
 * 
 * AUTHOR: SHAON 
 * CREATE DATE: 4th MARCH 2015
 * 
 */

public class UniversityManagement extends Controller{
	
	static Form<University> universityForm = Form.form(University.class);
	
	public static Result create(){
		
		return ok(create.render(universityForm));
	}
	
	public static Result save(){
		Form<University> filledForm = universityForm.bindFromRequest();
		University university = filledForm.get();
		University.create(university);
		flash("success",AppConstants.SUCCESS_MESSAGE);
		//return ok("");
		return redirect(controllers.routes.UniversityManagement.list());
	}
	
	public static Result show(Long id){
		University university = University.findById(id);
		if(university==null){
			flash("error",AppConstants.ERROR_MESSAGE_ID_NOT_FOUND);
			return redirect(controllers.routes.UniversityManagement.list());
		}
		else
			return ok(show.render(university));
	}
	public static Result list(){
		List<University> allUniversity = University.all();
		return ok(list.render(allUniversity));
	}
	
	public static Result edit(Long id){
		University university = University.findById(id);
		if(university==null){
			flash("error",AppConstants.ERROR_MESSAGE_ID_NOT_FOUND);
			return redirect(controllers.routes.UniversityManagement.show(id));
		}else{
			return ok(edit.render(universityForm.fill(university)));
		}
	}
	
	
	 public static Result update(){
         Form<University> filledForm = universityForm.bindFromRequest();
         if (filledForm.hasErrors()) {
             return badRequest(edit.render(filledForm));
         } else {
         
         University university = filledForm.get();
         University.update(university);
         return ok(list.render(University.all()));
         }
         
     }
 
	  public static Result delete(Long id){
		      University.delete(id);
		      flash("success", AppConstants.SUCCESSFUL_DELETE_MESSAGE);
		      return ok(list.render(University.all()));
	  }	
	
}
