/**Created By Sheikh Shamir Shakir Shomoy Solution
 * Shomoy Solution
 * On 28-Apr-2015
 ***/
package controllers;

import java.util.Date;
import java.util.List;
import java.util.Map;

import models.Role;
import models.User;
import models.UserPermission;
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
import services.RolePermissionCheck;
import services.UpdatePermission;
import services.UserRoleCheck;
import utils.AppConstants;
import views.html.userpermission.*;

public class UserPermissionManagement extends Controller{

	static Form<UserPermission> userPermissionForm = Form.form(UserPermission.class);
	
	
	public static Result createWithRole(String roleId) {
		Long rId = Long.parseLong(roleId);
		
		Integer rID = Integer.parseInt(roleId);
		List<Boolean> isChecked = RolePermissionCheck.check(rID);
		//List<Boolean> isChecked = UpdatePermission.viewUpdatePermission(rId);
		
		int rid = Integer.parseInt(rId.toString());
		
		String roleName = Role.get(rid).roleName;

		return ok(createWithoutList.render(rid, roleName, isChecked));
	}


    public static Result create() {
        return ok(createWithList.render(userPermissionForm));
	}
    
       
    
	 public static Result save() {
		 Form<UserPermission> filledForm = userPermissionForm.bindFromRequest();
		 if (filledForm.hasErrors()) {
				return badRequest(createWithList.render(filledForm));

			} else {
		 UserPermission userPermission = filledForm.get();
	     
		 UserPermission.deleteUserPermissionsByRole(userPermission.roleId);

			Map<String, String[]> reqBody = request().body().asFormUrlEncoded();
			String[] permissions = reqBody.get("permissionId");
			Role role = Role.get(userPermission.roleId);

			if(permissions!=null){
			for (String p : permissions) {
				Integer permissionId = Integer.parseInt(p);
				UserPermission userPermission2 = new UserPermission();
				userPermission2.permissionId = permissionId;
				userPermission2.roleId = role.id;
				
				List<Integer> users = UserRole.finduserIdsByRoleId(role.id);
				for(int user: users){
				userPermission2.userId = user;
				UserPermission.create(userPermission2);
				}
				
			}
			}
		 

	   	 flash("success", AppConstants.SUCCESS_MESSAGE);
	     //return ok("");
	   	return redirect(routes.UserPermissionManagement.create());
	    }
	 }
	 
	 
//	 
//	 public static Result list() {
//			return ok(list.render(UserPermission.all()));
//		}

//		public static Result show(int id) {
//			UserPermission userPermission = UserPermission.get(id);
//			return ok(show.render(userPermission));
//		}

//		public static Result edit(Long id) {
//			return ok("");
//		}

//		public static Result update() {
//			return ok("");
//		}
	 
}


