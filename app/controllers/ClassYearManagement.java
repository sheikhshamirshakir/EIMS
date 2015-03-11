/**Created By Ashfak SHAON Shomoy Solution
 * Shomoy Solution
 * On 10-Mar-2015
 ***/
package controllers;

import java.util.Date;
import java.util.List;

import models.admission.ClassYear;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import utils.AppConstants;
import views.html.classyear.*;

public class ClassYearManagement extends Controller{

	static Form<ClassYear> classYearForm = Form.form(ClassYear.class);
	
	 public static Result create() {
	        return ok(create.render(classYearForm));
	    }
	 
	 public static Result save() {
		 Form<ClassYear> filledForm = classYearForm.bindFromRequest();
		 ClassYear classYear = filledForm.get();
	     ClassYear.create(classYear);
	   	 flash("success", AppConstants.SUCCESS_MESSAGE);
	     //return ok("");
	   	return redirect(controllers.routes.ClassYearManagement.list());
	    }

	 public static Result list(){
	    	List<ClassYear> classYears = ClassYear.all();
	     	return ok(list.render(classYears));
	    }

	 
	 public static Result show(Long id) {
			ClassYear classYear = ClassYear.findById(id);
					
		  	if (classYear == null) {
				flash("error", AppConstants.ERROR_MESSAGE_ID_NOT_FOUND);
//				return ok("");
				return redirect(controllers.routes.ClassYearManagement.list());
			} else
				return ok(show.render(classYear));
		}
	 
	 public static Result edit(Long id) {
		 ClassYear classYear = ClassYear.findById(id);
			
		  	if (classYear == null) {
				flash("error", AppConstants.ERROR_MESSAGE_ID_NOT_FOUND);
				//return ok("");
				return redirect(controllers.routes.ClassYearManagement.show(id));
			}else
				return ok(edit.render(classYearForm.fill(classYear)));
		}
	 
	 
	 public static Result update(){
			Form<ClassYear> filledForm = classYearForm.bindFromRequest();
			if (filledForm.hasErrors()) {
				return badRequest(edit.render(filledForm));
			} else {
			
			ClassYear classYear = filledForm.get();
			ClassYear.update(classYear);
			return ok(list.render(ClassYear.all()));
			}
			
		}
	 
	 public static Result delete(Long id){
		 ClassYear.delete(id);
		 flash("success", AppConstants.SUCCESSFUL_DELETE_MESSAGE);
		 return ok(list.render(ClassYear.all()));
	 }
	 
	 
}


