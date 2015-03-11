/**Created By Sheikh Shamir Shakir Shomoy Solution
 * Shomoy Solution
 * On 11-Mar-2015
 ***/
package controllers;

import java.util.Date;
import java.util.List;

import models.admission.Designation;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import utils.AppConstants;
import views.html.designation.*;

public class DesignationManagement extends Controller{

	static Form<Designation> designationForm = Form.form(Designation.class);
	
	 public static Result create() {
	        return ok(create.render(designationForm));
	    }
	 
	 public static Result save() {
		 Form<Designation> filledForm = designationForm.bindFromRequest();
		 Designation designation = filledForm.get();
	     Designation.create(designation);
	   	 flash("success", AppConstants.SUCCESS_MESSAGE);
	     //return ok("");
	   	return redirect(controllers.routes.DesignationManagement.list());
	    }

	 public static Result list(){
	    	List<Designation> designations = Designation.all();
	     	return ok(list.render(designations));
	    }

	 
	 public static Result show(Long id) {
			Designation designation = Designation.findById(id);
					
		  	if (designation == null) {
				flash("error", AppConstants.ERROR_MESSAGE_ID_NOT_FOUND);
//				return ok("");
				return redirect(controllers.routes.DesignationManagement.list());
			} else
				return ok(show.render(designation));
		}
	 
	 public static Result edit(Long id) {
		 Designation designation = Designation.findById(id);
			
		  	if (designation == null) {
				flash("error", AppConstants.ERROR_MESSAGE_ID_NOT_FOUND);
				//return ok("");
				return redirect(controllers.routes.DesignationManagement.show(id));
			}else
				return ok(edit.render(designationForm.fill(designation)));
		}
	 
	 
	 public static Result update(){
			Form<Designation> filledForm = designationForm.bindFromRequest();
			if (filledForm.hasErrors()) {
				return badRequest(edit.render(filledForm));
			} else {
			
			Designation designation = filledForm.get();
			Designation.update(designation);
			return ok(list.render(Designation.all()));
			}
			
		}
	 
	 public static Result delete(Long id){
		 Designation.delete(id);
		 flash("success", AppConstants.SUCCESSFUL_DELETE_MESSAGE);
		 return ok(list.render(Designation.all()));
	 }
	 
	 
}


