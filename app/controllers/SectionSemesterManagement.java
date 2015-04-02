/**Created By Sheikh Shamir Shakir Shomoy Solution
 * Shomoy Solution
 * On 10-Mar-2015
 ***/
package controllers;

import java.util.Date;
import java.util.List;

import models.admission.SectionSemester;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import utils.AppConstants;
import views.html.sectionsemester.*;

public class SectionSemesterManagement extends Controller{

	static Form<SectionSemester> sectionSemesterForm = Form.form(SectionSemester.class);
	
	 public static Result create() {
	        return ok(create.render(sectionSemesterForm));
	    }
	 
	 
	 public static Result save() {
		 Form<SectionSemester> filledForm = sectionSemesterForm.bindFromRequest();
		 SectionSemester sectionSemester = filledForm.get();
	     SectionSemester.create(sectionSemester);
	   	 flash("success", AppConstants.SUCCESS_MESSAGE);
	     //return ok("");
	   	return redirect(controllers.routes.SectionSemesterManagement.list());
	    }

	 public static Result list(){
	    	List<SectionSemester> sectionSemesters = SectionSemester.all();
	     	return ok(list.render(sectionSemesters));
	    }

	 
	 public static Result show(Long id) {
			SectionSemester sectionSemester = SectionSemester.findById(id);
					
		  	if (sectionSemester == null) {
				flash("error", AppConstants.ERROR_MESSAGE_ID_NOT_FOUND);
//				return ok("");
				return redirect(controllers.routes.SectionSemesterManagement.list());
			} else
				return ok(show.render(sectionSemester));
		}
	 
	 public static Result edit(Long id) {
		 SectionSemester sectionSemester = SectionSemester.findById(id);
			
		  	if (sectionSemester == null) {
				flash("error", AppConstants.ERROR_MESSAGE_ID_NOT_FOUND);
				//return ok("");
				return redirect(controllers.routes.SectionSemesterManagement.show(id));
			}else
				return ok(edit.render(sectionSemesterForm.fill(sectionSemester)));
		}
	 
	 
	 public static Result update(){
			Form<SectionSemester> filledForm = sectionSemesterForm.bindFromRequest();
			if (filledForm.hasErrors()) {
				return badRequest(edit.render(filledForm));
			} else {
			
			SectionSemester sectionSemester = filledForm.get();
			SectionSemester.update(sectionSemester);
			return ok(list.render(SectionSemester.all()));
			}
			
		}
	 
	 public static Result delete(Long id){
		 SectionSemester.delete(id);
		 flash("success", AppConstants.SUCCESSFUL_DELETE_MESSAGE);
		 return ok(list.render(SectionSemester.all()));
	 }
	 
	 
}


