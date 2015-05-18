/**Created By Sheikh Shamir Shakir Shomoy Solution
 * Shomoy Solution
 * On 23-Apr-2015
 ***/
package controllers;

import java.util.Date;
import java.util.List;

import models.Role;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import utils.AppConstants;
import views.html.role.*;

public class RoleManagement extends Controller{

	static Form<Role> roleForm = Form.form(Role.class);
	
	 public static Result create() {
	        return ok(create.render(roleForm));
	    }
	 
	 public static Result save() {
		 Form<Role> filledForm = roleForm.bindFromRequest();
		 if (filledForm.hasErrors()) {
				return badRequest(create.render(filledForm));

			} else {
		 Role role = filledForm.get();
	     Role.create(role);
	   	 flash("success", AppConstants.SUCCESS_MESSAGE);
	     //return ok("");
	   	return redirect(controllers.routes.RoleManagement.list());
	    }
	 }
	 public static Result list(){
	    	List<Role> roles = Role.all();
	     	return ok(list.render(roles));
	    }

	 
	 public static Result show(int id) {
			Role role = Role.get(id);
					
		  	if (role == null) {
				flash("error", AppConstants.ERROR_MESSAGE_ID_NOT_FOUND);
//				return ok("");
				return redirect(controllers.routes.RoleManagement.list());
			} else
				return ok(show.render(role));
		}
	 
	 public static Result edit(int id) {
		 Role role = Role.get(id);
			
		  	if (role == null) {
				flash("error", AppConstants.ERROR_MESSAGE_ID_NOT_FOUND);
				//return ok("");
				return redirect(controllers.routes.RoleManagement.show(id));
			}else
				return ok(edit.render(roleForm.fill(role)));
		}
	 
	 
	 public static Result update(){
			Form<Role> filledForm = roleForm.bindFromRequest();
			if (filledForm.hasErrors()) {
				return badRequest(edit.render(filledForm));
			} else {
			
			Role role = filledForm.get();
			Role.update(role);
			return ok(list.render(Role.all()));
			}
			
		}
	 
	 public static Result delete(int id){
		 Role.delete(id);
		 flash("success", AppConstants.SUCCESSFUL_DELETE_MESSAGE);
		 return ok(list.render(Role.all()));
	 }
	 
	 
}


