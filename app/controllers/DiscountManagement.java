/**Created By Sheikh Shamir Shakir Shomoy Solution
 * Shomoy Solution
 * On 03-May-2015
 ***/
package controllers;

import java.util.Date;
import java.util.List;

import models.Fees.DiscountCategory;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import utils.AppConstants;
import views.html.discount.*;

public class DiscountManagement extends Controller{

	static Form<DiscountCategory> discountForm = Form.form(DiscountCategory.class);
	
	 public static Result create() {
	        return ok(create.render(discountForm));
	    }
	 
	 public static Result save() {
		 Form<DiscountCategory> filledForm = discountForm.bindFromRequest();
		 if (filledForm.hasErrors()) {
				return badRequest(create.render(filledForm));

			} else {
		 DiscountCategory discount = filledForm.get();
	     DiscountCategory.create(discount);
	   	 flash("success", AppConstants.SUCCESS_MESSAGE);
	    // return ok("");
	     return redirect(controllers.routes.DiscountManagement.list());
	    }
	 }
	 public static Result list(){
	    	List<DiscountCategory> faculties = DiscountCategory.all();
	     	return ok(list.render(faculties));
	    }

	 
	 public static Result show(Long id) {
			DiscountCategory discount = DiscountCategory.findById(id);
					
		  	if (discount == null) {
				flash("error", AppConstants.ERROR_MESSAGE_ID_NOT_FOUND);
				//return ok("");
				 return redirect(controllers.routes.DiscountManagement.list());
			} else
				return ok(show.render(discount));
		}
	 
	 public static Result edit(Long id) {
		 DiscountCategory discount = DiscountCategory.findById(id);
			
		  	if (discount == null) {
				flash("error", AppConstants.ERROR_MESSAGE_ID_NOT_FOUND);
//				return ok("");
				 return redirect(controllers.routes.DiscountManagement.show(id));
			}else
				return ok(edit.render(discountForm.fill(discount)));
		}
	 
	 
	 public static Result update(){
			Form<DiscountCategory> filledForm = discountForm.bindFromRequest();
			if (filledForm.hasErrors()) {
				return badRequest(edit.render(filledForm));
			} else {
			
			DiscountCategory discount = filledForm.get();
			DiscountCategory.update(discount);
			return ok(list.render(DiscountCategory.all()));
			}
			
		}
	 
	 public static Result delete(Long id){
		 DiscountCategory.delete(id);
		 flash("success", AppConstants.SUCCESSFUL_DELETE_MESSAGE);
		 return ok(list.render(DiscountCategory.all()));
	 }
	 
	 
}


