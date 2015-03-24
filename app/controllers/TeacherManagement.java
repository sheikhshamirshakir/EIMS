/**Created By Ashfak SHAON Shomoy Solution
 * Shomoy Solution
 * On 10-Mar-2015
 ***/
package controllers;

import java.util.Date;
import java.util.List;

import models.admission.Department;
import models.admission.Employee;
import models.admission.Teacher;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import utils.AppConstants;
import views.html.teacher.*;

public class TeacherManagement extends Controller{

	static Form<Teacher> teacherForm = Form.form(Teacher.class);
	
//	 public static Result create() {
//	        return ok(create.render(teacherForm));
//	    }
//	 
//	 public static Result save() {
//		 Form<Teacher> filledForm = teacherForm.bindFromRequest();
//		 Teacher teacher = filledForm.get();
//		 
//	     Teacher.create(teacher);
//	   	 flash("success", AppConstants.SUCCESS_MESSAGE);
//	     //return ok("");
//	   	return redirect(controllers.routes.TeacherManagement.list());
//	    }

	 public static Result list(){
	    	List<Teacher> teachers = Teacher.all();
	     	return ok(list.render(teachers));
	    }

	 
	 public static Result show(Long id) {
			Teacher teacher = Teacher.findById(id);
					
		  	if (teacher == null) {
				flash("error", AppConstants.ERROR_MESSAGE_ID_NOT_FOUND);
//				return ok("");
				return redirect(controllers.routes.TeacherManagement.list());
			} else
				return ok(show.render(teacher));
		}
	 
	 public static Result edit(Long id) {
		 Teacher teacher = Teacher.findById(id);
			
		  	if (teacher == null) {
				flash("error", AppConstants.ERROR_MESSAGE_ID_NOT_FOUND);
				//return ok("");
				return redirect(controllers.routes.TeacherManagement.show(id));
			}else
				return ok(edit.render(teacherForm.fill(teacher)));
		}
	 
	 
	 public static Result update(){
			Form<Teacher> filledForm = teacherForm.bindFromRequest();
			if (filledForm.hasErrors()) {
				return badRequest(edit.render(filledForm));
			} else {
			
			Teacher teacher = filledForm.get();
			
			Long teacherId = teacher.tid;
			Teacher teacher2 = Teacher.findById(teacherId);
			Employee employee = Employee.findById(teacher2.employeeId);
			employee.name = teacher.name;
			
			Employee.update(employee);
			
			
			Teacher.update(teacher);
			return ok(list.render(Teacher.all()));
			}
			
		}
	 
	 public static Result delete(Long id){
		 Teacher.delete(id);
		 flash("success", AppConstants.SUCCESSFUL_DELETE_MESSAGE);
		 return ok(list.render(Teacher.all()));
	 }
	 
	 
}


