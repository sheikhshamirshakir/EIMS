/**Created By Sheikh Shamir Shakir Shomoy Solution
 * Shomoy Solution
 * On 23-Apr-2015
 ***/
package controllers;

import java.util.Date;
import java.util.List;
import java.util.Map;

import models.Role;
import models.User;
import models.UserRole;
import models.admission.Course;
import models.admission.Parent;
import models.admission.Student;
import models.admission.StudentCourse;
import models.admission.Teacher;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import services.UpdatePermission;
import services.UserRoleCheck;
import utils.AppConstants;
import views.html.userrole.*;

public class UserRoleManagement extends Controller{

	static Form<UserRole> userRoleForm = Form.form(UserRole.class);
	
	
	public static Result createWithUser(String userId) {
		Long uId = Long.parseLong(userId);
		Integer uID = Integer.parseInt(userId);
		List<Boolean> isChecked = UserRoleCheck.check(uID);
		int uid = Integer.parseInt(uId.toString());
		
		
		int role = User.get(uid).roleId;
		String roleName = Role.get(role).roleName;
		String userName="";
		if(roleName=="Student"){
			userName = Student.findById(Long.parseLong(Integer.toString(User.get(uid).userId))).name;
		}
		else if(roleName=="Teacher"){
			userName = Teacher.findById(Long.parseLong(Integer.toString(User.get(uid).userId))).name;
		}
		else if(roleName=="Parent"){
			userName = Parent.findById(Long.parseLong(Integer.toString(User.get(uid).userId))).name;
		}
		else if(roleName=="Admin"){
			userName="Admin";
		}

		return ok(createWithoutList.render(uid, userName, isChecked));
	}


    public static Result create() {
        return ok(createWithList.render(userRoleForm));
	}
    
       
    
	 public static Result save() {
		 Form<UserRole> filledForm = userRoleForm.bindFromRequest();
		 UserRole userRole = filledForm.get();
	     
		 UserRole.deleteUserRolesByUser(userRole.userId);

			Map<String, String[]> reqBody = request().body().asFormUrlEncoded();
			String[] roles = reqBody.get("roleId");
			User user = User.get(userRole.userId);

			if(roles!=null){
			for (String r : roles) {
				Integer roleId = Integer.parseInt(r);
				UserRole userRole2 = new UserRole();
				userRole2.userId = user.id;
				userRole2.roleId = roleId;
				UserRole.create(userRole2);
			}
			}
		 

	   	 flash("success", AppConstants.SUCCESS_MESSAGE);
	     //return ok("");
	  	return redirect(routes.UserRoleManagement.create());
	    }

	 
	 
	 
//	 public static Result list() {
//			return ok(list.render(UserRole.all()));
//		}

//		public static Result show(int id) {
//			UserRole userRole = UserRole.get(id);
//			return ok(show.render(userRole));
//		}

//		public static Result edit(Long id) {
//			return ok("");
//		}

//		public static Result update() {
//			return ok("");
//		}
	 
}


