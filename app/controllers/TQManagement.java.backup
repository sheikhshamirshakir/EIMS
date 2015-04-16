/**Created By Sheikh Shamir Shakir Shomoy Solution
 * Shomoy Solution
 * On 17-Mar-2015
 ***/
package controllers;

import java.util.Date;
import java.util.List;

import models.admission.Department;
import models.admission.TeacherQualifications;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import utils.AppConstants;
import views.html.tq.*;

public class TQManagement extends Controller{

	static Form<TeacherQualifications> tqForm = Form.form(TeacherQualifications.class);
	
//	 public static Result create() {
//	        return ok(create.render(tqForm));
//	    }
//	 
//	 public static Result save() {
//		 Form<TeacherQualifications> filledForm = tqForm.bindFromRequest();
//		 TeacherQualifications tq = filledForm.get();
//		 
//	     TeacherQualifications.create(tq);
//	   	 flash("success", AppConstants.SUCCESS_MESSAGE);
//	     //return ok("");
//	   	return redirect(controllers.routes.TQManagement.list());
//	    }

	 public static Result list(){
	    	List<TeacherQualifications> tqs = TeacherQualifications.all();
	     	return ok(list.render(tqs));
	    }

	 
	 public static Result show(Long id) {
			TeacherQualifications tq = TeacherQualifications.findById(id);
					
		  	if (tq == null) {
				flash("error", AppConstants.ERROR_MESSAGE_ID_NOT_FOUND);
//				return ok("");
				return redirect(controllers.routes.TQManagement.list());
			} else
				return ok(show.render(tq));
		}
	 
	 public static Result edit(Long id) {
		 TeacherQualifications tq = TeacherQualifications.findById(id);
			
		  	if (tq == null) {
				flash("error", AppConstants.ERROR_MESSAGE_ID_NOT_FOUND);
				//return ok("");
				return redirect(controllers.routes.TQManagement.show(id));
			}else
				return ok(edit.render(tqForm.fill(tq)));
		}
	 
	 
	 public static Result update(){
			Form<TeacherQualifications> filledForm = tqForm.bindFromRequest();
			if (filledForm.hasErrors()) {
				return badRequest(edit.render(filledForm));
			} else {
			
			TeacherQualifications tq = filledForm.get();
			TeacherQualifications.update(tq);
			return ok(list.render(TeacherQualifications.all()));
			}
			
		}
	 
	 public static Result delete(Long id){
		 TeacherQualifications.delete(id);
		 flash("success", AppConstants.SUCCESSFUL_DELETE_MESSAGE);
		 return ok(list.render(TeacherQualifications.all()));
	 }
	 
	 
}


