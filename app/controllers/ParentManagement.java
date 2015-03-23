/**Created By Ashfak SHAON Shomoy Solution
 * Shomoy Solution
 * On 10-Mar-2015
 ***/
package controllers;

import java.util.Date;
import java.util.List;

import models.admission.Department;
import models.admission.Parent;
import models.admission.Parent;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import utils.AppConstants;
import views.html.parent.*;

public class ParentManagement extends Controller{

	static Form<Parent> parentForm = Form.form(Parent.class);


	 public static Result list(){
	    	List<Parent> parents = Parent.all();
	     	return ok(list.render(parents));
	    }

	 
	 public static Result show(Long id) {
			Parent parent = Parent.findById(id);
					
		  	if (parent == null) {
				flash("error", AppConstants.ERROR_MESSAGE_ID_NOT_FOUND);
//				return ok("");
				return redirect(controllers.routes.ParentManagement.list());
			} else
				return ok(show.render(parent));
		}
	 
	 public static Result edit(Long id) {
		 Parent parent = Parent.findById(id);
			
		  	if (parent == null) {
				flash("error", AppConstants.ERROR_MESSAGE_ID_NOT_FOUND);
				//return ok("");
				return redirect(controllers.routes.ParentManagement.show(id));
			}else
				return ok(edit.render(parentForm.fill(parent)));
		}
	 
	 
	 public static Result update(){
			Form<Parent> filledForm = parentForm.bindFromRequest();
			if (filledForm.hasErrors()) {
				return badRequest(edit.render(filledForm));
			} else {
			
			Parent parent = filledForm.get();
			Parent.update(parent);
			return ok(list.render(Parent.all()));
			}
			
		}
	 
	 public static Result delete(Long id){
		 Parent.delete(id);
		 flash("success", AppConstants.SUCCESSFUL_DELETE_MESSAGE);
		 return ok(list.render(Parent.all()));
	 }
	 
	 
}


