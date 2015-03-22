/**Created By Sheikh Shamir Shakir Shomoy Solution
 * Shomoy Solution
 * On 04-Mar-2015
 ***/
package controllers;

import java.util.Date;
import java.util.List;

import models.admission.Faculty;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import utils.AppConstants;
import views.html.faculty.*;

public class FacultyManagement extends Controller{

	static Form<Faculty> facultyForm = Form.form(Faculty.class);
	
	 public static Result create() {
	        return ok(create.render(facultyForm));
	    }
	 
	 public static Result save() {
		 Form<Faculty> filledForm = facultyForm.bindFromRequest();
		 Faculty faculty = filledForm.get();
	     Faculty.create(faculty);
	   	 flash("success", AppConstants.SUCCESS_MESSAGE);
	    // return ok("");
	     return redirect(controllers.routes.FacultyManagement.list());
	    }

	 public static Result list(){
	    	List<Faculty> faculties = Faculty.all();
	     	return ok(list.render(faculties));
	    }

	 
	 public static Result show(Long id) {
			Faculty faculty = Faculty.findById(id);
					
		  	if (faculty == null) {
				flash("error", AppConstants.ERROR_MESSAGE_ID_NOT_FOUND);
				//return ok("");
				 return redirect(controllers.routes.FacultyManagement.list());
			} else
				return ok(show.render(faculty));
		}
	 
	 public static Result edit(Long id) {
		 Faculty faculty = Faculty.findById(id);
			
		  	if (faculty == null) {
				flash("error", AppConstants.ERROR_MESSAGE_ID_NOT_FOUND);
//				return ok("");
				 return redirect(controllers.routes.FacultyManagement.show(id));
			}else
				return ok(edit.render(facultyForm.fill(faculty)));
		}
	 
	 
	 public static Result update(){
			Form<Faculty> filledForm = facultyForm.bindFromRequest();
			if (filledForm.hasErrors()) {
				return badRequest(edit.render(filledForm));
			} else {
			
			Faculty faculty = filledForm.get();
			Faculty.update(faculty);
			return ok(list.render(Faculty.all()));
			}
			
		}
	 
	 public static Result delete(Long id){
		 Faculty.delete(id);
		 flash("success", AppConstants.SUCCESSFUL_DELETE_MESSAGE);
		 return ok(list.render(Faculty.all()));
	 }
	 
	 
}


