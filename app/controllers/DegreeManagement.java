/**Created By Sheikh Shamir Shakir Shomoy Solution
 * Shomoy Solution
 * On 09-Mar-2015
 ***/
package controllers;

import java.util.Date;
import java.util.List;

import models.admission.Degree;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import utils.AppConstants;
import views.html.degree.*;

public class DegreeManagement extends Controller{

	static Form<Degree> degreeForm = Form.form(Degree.class);
	
	 public static Result create() {
	        return ok(create.render(degreeForm));
	    }
	 
	 public static Result save() {
		 Form<Degree> filledForm = degreeForm.bindFromRequest();
		 Degree degree = filledForm.get();
	     Degree.create(degree);
	   	 flash("success", AppConstants.SUCCESS_MESSAGE);
	     //return ok("");
	   	return redirect(controllers.routes.DegreeManagement.list());
	    }

	 public static Result list(){
	    	List<Degree> degrees = Degree.all();
	     	return ok(list.render(degrees));
	    }

	 
	 public static Result show(Long id) {
			Degree degree = Degree.findById(id);
					
		  	if (degree == null) {
				flash("error", AppConstants.ERROR_MESSAGE_ID_NOT_FOUND);
//				return ok("");
				return redirect(controllers.routes.DegreeManagement.list());
			} else
				return ok(show.render(degree));
		}
	 
	 public static Result edit(Long id) {
		 Degree degree = Degree.findById(id);
			
		  	if (degree == null) {
				flash("error", AppConstants.ERROR_MESSAGE_ID_NOT_FOUND);
				//return ok("");
				return redirect(controllers.routes.DegreeManagement.show(id));
			}else
				return ok(edit.render(degreeForm.fill(degree)));
		}
	 
	 
	 public static Result update(){
			Form<Degree> filledForm = degreeForm.bindFromRequest();
			if (filledForm.hasErrors()) {
				return badRequest(edit.render(filledForm));
			} else {
			
			Degree degree = filledForm.get();
			Degree.update(degree);
			return ok(list.render(Degree.all()));
			}
			
		}
	 
	 public static Result delete(Long id){
		 Degree.delete(id);
		 flash("success", AppConstants.SUCCESSFUL_DELETE_MESSAGE);
		 return ok(list.render(Degree.all()));
	 }
	 
	 
}


