/**Created By Sheikh Shamir Shakir Shomoy Solution
 * Shomoy Solution
 * On 15-Mar-2015
 ***/
package controllers;

import java.util.Date;
import java.util.List;

import be.objectify.deadbolt.java.actions.Dynamic;
import models.book.Author;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import services.deadbolt.DeadboltHandler;
import utils.AppConstants;
import views.html.author.*;

public class AuthorManagement extends Controller{

	static Form<Author> authorForm = Form.form(Author.class);
	
//	@Dynamic(value = "Entry Employee Author", handler = DeadboltHandler.class)
	 public static Result create() {
	        return ok(create.render(authorForm));
	    }
	 
//	@Dynamic(value = "Entry Employee Author", handler = DeadboltHandler.class)
	 public static Result save() {
		 Form<Author> filledForm = authorForm.bindFromRequest();
		 if (filledForm.hasErrors()) {
				return badRequest(create.render(filledForm));

			} else {
 
		 Author author = filledForm.get();
	     Author.create(author);
	   	 flash("success", AppConstants.SUCCESS_MESSAGE);
	     //return ok("");
	   	return redirect(controllers.routes.AuthorManagement.list());
	    }
	 }
//	@Dynamic(value = "List of Employee Author", handler = DeadboltHandler.class)
	 public static Result list(){
	    	List<Author> authors = Author.all();
	     	return ok(list.render(authors));
	    }

	 
	 public static Result show(Long id) {
			Author author = Author.findById(id);
					
		  	if (author == null) {
				flash("error", AppConstants.ERROR_MESSAGE_ID_NOT_FOUND);
//				return ok("");
				return redirect(controllers.routes.AuthorManagement.list());
			} else
				return ok(show.render(author));
		}
	 
	 public static Result edit(Long id) {
		 Author author = Author.findById(id);
			
		  	if (author == null) {
				flash("error", AppConstants.ERROR_MESSAGE_ID_NOT_FOUND);
				//return ok("");
				return redirect(controllers.routes.AuthorManagement.show(id));
			}else
				return ok(edit.render(authorForm.fill(author)));
		}
	 
	 
	 public static Result update(){
			Form<Author> filledForm = authorForm.bindFromRequest();
			if (filledForm.hasErrors()) {
				return badRequest(edit.render(filledForm));
			} else {
			
			Author author = filledForm.get();
			Author.update(author);
			return ok(list.render(Author.all()));
			}
			
		}
	 
	 public static Result delete(Long id){
		 Author.delete(id);
		 flash("success", AppConstants.SUCCESSFUL_DELETE_MESSAGE);
		 return ok(list.render(Author.all()));
	 }
	 
	 
}


