/**Created By Sheikh Shamir Shakir Shomoy Solution
 * Shomoy Solution
 * On 17-Mar-2015
 ***/
package controllers;

import java.util.Date;
import java.util.List;

import models.admission.Course;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import utils.AppConstants;
import views.html.course.*;

public class CourseManagement extends Controller{

	static Form<Course> courseForm = Form.form(Course.class);
	
	 public static Result create() {
	        return ok(create.render(courseForm));
	    }
	 
	 public static Result save() {
		 Form<Course> filledForm = courseForm.bindFromRequest();
		 Course course = filledForm.get();
	     Course.create(course);
	   	 flash("success", AppConstants.SUCCESS_MESSAGE);
	     //return ok("");
	   	return redirect(controllers.routes.CourseManagement.list());
	    }

	 public static Result list(){
	    	List<Course> courses = Course.all();
	     	return ok(list.render(courses));
	    }

	 
	 public static Result show(Long id) {
			Course course = Course.findById(id);
					
		  	if (course == null) {
				flash("error", AppConstants.ERROR_MESSAGE_ID_NOT_FOUND);
//				return ok("");
				return redirect(controllers.routes.CourseManagement.list());
			} else
				return ok(show.render(course));
		}
	 
	 public static Result edit(Long id) {
		 Course course = Course.findById(id);
			
		  	if (course == null) {
				flash("error", AppConstants.ERROR_MESSAGE_ID_NOT_FOUND);
				//return ok("");
				return redirect(controllers.routes.CourseManagement.show(id));
			}else
				return ok(edit.render(courseForm.fill(course)));
		}
	 
	 
	 public static Result update(){
			Form<Course> filledForm = courseForm.bindFromRequest();
			if (filledForm.hasErrors()) {
				return badRequest(edit.render(filledForm));
			} else {
			
			Course course = filledForm.get();
			Course.update(course);
			return ok(list.render(Course.all()));
			}
			
		}
	 
	 public static Result delete(Long id){
		 Course.delete(id);
		 flash("success", AppConstants.SUCCESSFUL_DELETE_MESSAGE);
		 return ok(list.render(Course.all()));
	 }
	 
	 
}


