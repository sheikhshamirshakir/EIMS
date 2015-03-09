/**Created By Sheikh Shamir Shakir Shomoy Solution
 * Shomoy Solution
 * On 08-Mar-2015
 ***/
package controllers;

import java.util.Date;
import java.util.List;

import models.admission.Department;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import utils.AppConstants;
import views.html.department.*;

public class DepartmentManagement extends Controller{

	static Form<Department> departmentForm = Form.form(Department.class);
	
	 public static Result create() {
	        return ok(create.render(departmentForm));
	    }
	 
	 public static Result save() {
		 Form<Department> filledForm = departmentForm.bindFromRequest();
		 Department department = filledForm.get();
	     Department.create(department);
	   	 flash("success", AppConstants.SUCCESS_MESSAGE);
	     //return ok("");
	   	return redirect(controllers.routes.DepartmentManagement.list());
	    }

	 public static Result list(){
	    	List<Department> depts = Department.all();
	     	return ok(list.render(depts));
	    }

	 
	 public static Result show(Long id) {
			Department department = Department.findById(id);
					
		  	if (department == null) {
				flash("error", AppConstants.ERROR_MESSAGE_ID_NOT_FOUND);
//				return ok("");
				return redirect(controllers.routes.DepartmentManagement.list());
			} else
				return ok(show.render(department));
		}
	 
	 public static Result edit(Long id) {
		 Department department = Department.findById(id);
			
		  	if (department == null) {
				flash("error", AppConstants.ERROR_MESSAGE_ID_NOT_FOUND);
				//return ok("");
				return redirect(controllers.routes.DepartmentManagement.show(id));
			}else
				return ok(edit.render(departmentForm.fill(department)));
		}
	 
	 
	 public static Result update(){
			Form<Department> filledForm = departmentForm.bindFromRequest();
			if (filledForm.hasErrors()) {
				return badRequest(edit.render(filledForm));
			} else {
			
			Department department = filledForm.get();
			Department.update(department);
			return ok(list.render(Department.all()));
			}
			
		}
	 
	 public static Result delete(Long id){
		 Department.delete(id);
		 flash("success", AppConstants.SUCCESSFUL_DELETE_MESSAGE);
		 return ok(list.render(Department.all()));
	 }
	 
	 
}


