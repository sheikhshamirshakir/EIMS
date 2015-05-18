/**Created By Sheikh Shamir Shakir Shomoy Solution
 * Shomoy Solution
 * On 03-May-2015
 ***/
package controllers;

import java.util.Date;
import java.util.List;

import models.Fees.StudentCollectionType;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import utils.AppConstants;
import views.html.sct.*;

public class StudentCollectionTypeManagement extends Controller{

	static Form<StudentCollectionType> sctForm = Form.form(StudentCollectionType.class);
	
	 public static Result create() {
	        return ok(create.render(sctForm));
	    }
	 
	 public static Result save() {
		 Form<StudentCollectionType> filledForm = sctForm.bindFromRequest();
		 if (filledForm.hasErrors()) {
				return badRequest(create.render(filledForm));

			} else {
		 StudentCollectionType sct = filledForm.get();
	     StudentCollectionType.create(sct);
	   	 flash("success", AppConstants.SUCCESS_MESSAGE);
	    // return ok("");
	     return redirect(controllers.routes.StudentCollectionTypeManagement.list());
	    }
	 }
	 public static Result list(){
	    	List<StudentCollectionType> faculties = StudentCollectionType.all();
	     	return ok(list.render(faculties));
	    }

	 
	 public static Result show(Long id) {
			StudentCollectionType sct = StudentCollectionType.findById(id);
					
		  	if (sct == null) {
				flash("error", AppConstants.ERROR_MESSAGE_ID_NOT_FOUND);
				//return ok("");
				 return redirect(controllers.routes.StudentCollectionTypeManagement.list());
			} else
				return ok(show.render(sct));
		}
	 
	 public static Result edit(Long id) {
		 StudentCollectionType sct = StudentCollectionType.findById(id);
			
		  	if (sct == null) {
				flash("error", AppConstants.ERROR_MESSAGE_ID_NOT_FOUND);
//				return ok("");
				 return redirect(controllers.routes.StudentCollectionTypeManagement.show(id));
			}else
				return ok(edit.render(sctForm.fill(sct)));
		}
	 
	 
	 public static Result update(){
			Form<StudentCollectionType> filledForm = sctForm.bindFromRequest();
			if (filledForm.hasErrors()) {
				return badRequest(edit.render(filledForm));
			} else {
			
			StudentCollectionType sct = filledForm.get();
			StudentCollectionType.update(sct);
			return ok(list.render(StudentCollectionType.all()));
			}
			
		}
	 
	 public static Result delete(Long id){
		 StudentCollectionType.delete(id);
		 flash("success", AppConstants.SUCCESSFUL_DELETE_MESSAGE);
		 return ok(list.render(StudentCollectionType.all()));
	 }
	 
	 
}


