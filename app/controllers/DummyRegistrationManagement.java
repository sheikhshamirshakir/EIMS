/**Created By Sheikh Shamir Shakir Shomoy Solution
 * Shomoy Solution
 * On 09-Mar-2015
 ***/
package controllers;

import java.util.Date;
import java.util.List;

import dummymodels.DummyStudent;
import models.admission.DummyRegistration;
import models.admission.Student;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import utils.AppConstants;
import views.html.validation.*;

public class DummyRegistrationManagement extends Controller{

	static Form<DummyRegistration> dummyForm = Form.form(DummyRegistration.class);
	static Form<DummyStudent> dStudentForm = Form.form(DummyStudent.class);
	
	

	 public static Result create() {
	        return ok(approve.render(dummyForm));
	    }
	 
	 public static Result dummyCheck() {
		 Form<DummyRegistration> filledForm = dummyForm.bindFromRequest();
		 if (filledForm.hasErrors()) {
				return badRequest(approve.render(filledForm));

			} else {
		 DummyRegistration dummyRegistration = filledForm.get();
		 
		DummyRegistration dr = DummyRegistration.getStudent(dummyRegistration);
		 if(dr!=null){
			 flash("success", AppConstants.SUCCESS_MESSAGE);
		        return ok(admission.render(dStudentForm));
		 }
		 
	     //DummyRegistration.create(dummyRegistration);
	   	 
	     //return ok("not ok");
	  	 return redirect(controllers.routes.DummyRegistrationManagement.create());
	    }
	 }
	 
	 
}


