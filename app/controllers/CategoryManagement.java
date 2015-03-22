/**Created By Sheikh Shamir Shakir Shomoy Solution
 * Shomoy Solution
 * On 15-Mar-2015
 ***/
package controllers;

import java.util.Date;
import java.util.List;

import models.admission.Category;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import utils.AppConstants;
import views.html.category.*;

public class CategoryManagement extends Controller{

	static Form<Category> categoryForm = Form.form(Category.class);
	
	 public static Result create() {
	        return ok(create.render(categoryForm));
	    }
	 
	 public static Result save() {
		 Form<Category> filledForm = categoryForm.bindFromRequest();
		 Category category = filledForm.get();
	     Category.create(category);
	   	 flash("success", AppConstants.SUCCESS_MESSAGE);
	     //return ok("");
	   	return redirect(controllers.routes.CategoryManagement.list());
	    }

	 public static Result list(){
	    	List<Category> categories = Category.all();
	     	return ok(list.render(categories));
	    }

	 
	 public static Result show(Long id) {
			Category category = Category.findById(id);
					
		  	if (category == null) {
				flash("error", AppConstants.ERROR_MESSAGE_ID_NOT_FOUND);
//				return ok("");
				return redirect(controllers.routes.CategoryManagement.list());
			} else
				return ok(show.render(category));
		}
	 
	 public static Result edit(Long id) {
		 Category category = Category.findById(id);
			
		  	if (category == null) {
				flash("error", AppConstants.ERROR_MESSAGE_ID_NOT_FOUND);
				//return ok("");
				return redirect(controllers.routes.CategoryManagement.show(id));
			}else
				return ok(edit.render(categoryForm.fill(category)));
		}
	 
	 
	 public static Result update(){
			Form<Category> filledForm = categoryForm.bindFromRequest();
			if (filledForm.hasErrors()) {
				return badRequest(edit.render(filledForm));
			} else {
			
			Category category = filledForm.get();
			Category.update(category);
			return ok(list.render(Category.all()));
			}
			
		}
	 
	 public static Result delete(Long id){
		 Category.delete(id);
		 flash("success", AppConstants.SUCCESSFUL_DELETE_MESSAGE);
		 return ok(list.render(Category.all()));
	 }
	 
	 
}


