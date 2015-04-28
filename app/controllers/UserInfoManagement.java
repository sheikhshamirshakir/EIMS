/**Created By Sheikh Shamir Shakir Shomoy Solution
 * Shomoy Solution
 * On 23-Apr-2015
 ***/
package controllers;

import java.util.Date;
import java.util.List;

import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import utils.AppConstants;
import views.html.user.*;

public class UserInfoManagement extends Controller{

	static Form<User> userForm = Form.form(User.class);
	
	 public static Result create() {
	        return ok(create.render(userForm));
	    }
	 
	 public static Result save() {
		 Form<User> filledForm = userForm.bindFromRequest();
		 User user = filledForm.get();
	     User.create(user);
	   	 flash("success", AppConstants.SUCCESS_MESSAGE);
	     //return ok("");
	   	return redirect(controllers.routes.UserInfoManagement.list());
	    }

	 public static Result list(){
	    	List<User> users = User.all();
	     	return ok(list.render(users));
	    }

	 
	 public static Result show(int id) {
			User user = User.get(id);
					
		  	if (user == null) {
				flash("error", AppConstants.ERROR_MESSAGE_ID_NOT_FOUND);
//				return ok("");
				return redirect(controllers.routes.UserInfoManagement.list());
			} else
				return ok(show.render(user));
		}
	 
	 public static Result edit(int id) {
		 User user = User.get(id);
			
		  	if (user == null) {
				flash("error", AppConstants.ERROR_MESSAGE_ID_NOT_FOUND);
				//return ok("");
				return redirect(controllers.routes.UserInfoManagement.show(id));
			}else
				return ok(edit.render(userForm.fill(user)));
		}
	 
	 
	 public static Result update(){
			Form<User> filledForm = userForm.bindFromRequest();
			if (filledForm.hasErrors()) {
				return badRequest(edit.render(filledForm));
			} else {
			
			User user = filledForm.get();
			User.update(user);
			return ok(list.render(User.all()));
			}
			
		}
	 
	 public static Result delete(int id){
		 User.delete(id);
		 flash("success", AppConstants.SUCCESSFUL_DELETE_MESSAGE);
		 return ok(list.render(User.all()));
	 }
	 
	 
}


