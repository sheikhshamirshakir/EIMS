/**Created By Sheikh Shamir Shakir Shomoy Solution
 * Shomoy Solution
 * On 03-May-2015
 ***/
package controllers;

import java.util.Date;
import java.util.List;

import models.Fees.FeesHead;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import utils.AppConstants;
import views.html.feeshead.*;

public class FeesHeadManagement extends Controller{

	static Form<FeesHead> feesHeadForm = Form.form(FeesHead.class);
	
	 public static Result create() {
	        return ok(create.render(feesHeadForm));
	    }
	 
	 public static Result save() {
		 Form<FeesHead> filledForm = feesHeadForm.bindFromRequest();
		 FeesHead feesHead = filledForm.get();
	     FeesHead.create(feesHead);
	   	 flash("success", AppConstants.SUCCESS_MESSAGE);
	     return redirect(controllers.routes.FeesHeadManagement.list());
	    }

	 public static Result list(){
	    	List<FeesHead> faculties = FeesHead.all();
	     	return ok(list.render(faculties));
   	    }

	 
	 public static Result show(Long id) {
			FeesHead feesHead = FeesHead.findById(id);
					
		  	if (feesHead == null) {
				flash("error", AppConstants.ERROR_MESSAGE_ID_NOT_FOUND);
				//return ok("");
				 return redirect(controllers.routes.FeesHeadManagement.list());
			} else
				return ok(show.render(feesHead));
		}
	 
	 public static Result edit(Long id) {
		 FeesHead feesHead = FeesHead.findById(id);
			
		  	if (feesHead == null) {
				flash("error", AppConstants.ERROR_MESSAGE_ID_NOT_FOUND);
//				return ok("");
				 return redirect(controllers.routes.FeesHeadManagement.show(id));
			}else
				return ok(edit.render(feesHeadForm.fill(feesHead)));
		}
	 
	 
	 public static Result update(){
		Form<FeesHead> filledForm = feesHeadForm.bindFromRequest();
		if (filledForm.hasErrors()) {
			return badRequest(edit.render(filledForm));
		} else {
		
		FeesHead feesHead = filledForm.get();
		FeesHead.update(feesHead);
		return ok(list.render(FeesHead.all()));
		}
	}

	 public static Result delete(Long id){
		 FeesHead.delete(id);
		 flash("success", AppConstants.SUCCESSFUL_DELETE_MESSAGE);
		 return ok(list.render(FeesHead.all()));
	 }
}


