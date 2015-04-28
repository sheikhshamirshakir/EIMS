/**Created By Sheikh Shamir Shakir Shomoy Solution
 * Shomoy Solution
 * On 23-Apr-2015
 ***/
package controllers;

import java.util.Date;
import java.util.List;

import models.Permission;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import utils.AppConstants;
import views.html.permission.*;

public class PermissionManagement extends Controller{

	static Form<Permission> permissionForm = Form.form(Permission.class);
	
	 public static Result create() {
	        return ok(create.render(permissionForm));
	    }
	 
	 public static Result save() {
		 Form<Permission> filledForm = permissionForm.bindFromRequest();
		 Permission permission = filledForm.get();
	     Permission.create(permission);
	   	 flash("success", AppConstants.SUCCESS_MESSAGE);
	     //return ok("");
	   	return redirect(controllers.routes.PermissionManagement.list());
	    }

	 public static Result list(){
	    	List<Permission> permissions = Permission.all();
	     	return ok(list.render(permissions));
	    }

	 
	 public static Result show(Integer id) {
			Permission permission = Permission.get(id);
					
		  	if (permission == null) {
				flash("error", AppConstants.ERROR_MESSAGE_ID_NOT_FOUND);
//				return ok("");
				return redirect(controllers.routes.PermissionManagement.list());
			} else
				return ok(show.render(permission));
		}
	 
	 public static Result edit(Integer id) {
		 Permission permission = Permission.get(id);
			
		  	if (permission == null) {
				flash("error", AppConstants.ERROR_MESSAGE_ID_NOT_FOUND);
				//return ok("");
				return redirect(controllers.routes.PermissionManagement.show(id));
			}else
				return ok(edit.render(permissionForm.fill(permission)));
		}
	 
	 
	 public static Result update(){
			Form<Permission> filledForm = permissionForm.bindFromRequest();
			if (filledForm.hasErrors()) {
				return badRequest(edit.render(filledForm));
			} else {
			
			Permission permission = filledForm.get();
			Permission.update(permission);
			return ok(list.render(Permission.all()));
			}
			
		}
	 
	 public static Result delete(Integer id){
		 Permission.delete(id);
		 flash("success", AppConstants.SUCCESSFUL_DELETE_MESSAGE);
		 return ok(list.render(Permission.all()));
	 }
	 
	 
}


