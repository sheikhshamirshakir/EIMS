/**Created By Sheikh Shamir Shakir Shomoy Solution
 * Shomoy Solution
 * On 15-Mar-2015
 ***/
package controllers;

import java.util.Date;
import java.util.List;

import be.objectify.deadbolt.java.actions.Dynamic;
import models.book.Publisher;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import services.deadbolt.DeadboltHandler;
import utils.AppConstants;
import views.html.publisher.*;

public class PublisherManagement extends Controller{

	static Form<Publisher> publisherForm = Form.form(Publisher.class);
	
//	@Dynamic(value = "Entry Employee Publisher", handler = DeadboltHandler.class)
	 public static Result create() {
	        return ok(create.render(publisherForm));
	    }
	 
//	@Dynamic(value = "Entry Employee Publisher", handler = DeadboltHandler.class)
	 public static Result save() {
		 Form<Publisher> filledForm = publisherForm.bindFromRequest();
		 if (filledForm.hasErrors()) {
				return badRequest(create.render(filledForm));

			} else {
 
		 Publisher publisher = filledForm.get();
	     Publisher.create(publisher);
	   	 flash("success", AppConstants.SUCCESS_MESSAGE);
	     //return ok("");
	   	return redirect(controllers.routes.PublisherManagement.list());
	    }
	 }
//	@Dynamic(value = "List of Employee Publisher", handler = DeadboltHandler.class)
	 public static Result list(){
	    	List<Publisher> publishers = Publisher.all();
	     	return ok(list.render(publishers));
	    }

	 
	 public static Result show(Long id) {
			Publisher publisher = Publisher.findById(id);
					
		  	if (publisher == null) {
				flash("error", AppConstants.ERROR_MESSAGE_ID_NOT_FOUND);
//				return ok("");
				return redirect(controllers.routes.PublisherManagement.list());
			} else
				return ok(show.render(publisher));
		}
	 
	 public static Result edit(Long id) {
		 Publisher publisher = Publisher.findById(id);
			
		  	if (publisher == null) {
				flash("error", AppConstants.ERROR_MESSAGE_ID_NOT_FOUND);
				//return ok("");
				return redirect(controllers.routes.PublisherManagement.show(id));
			}else
				return ok(edit.render(publisherForm.fill(publisher)));
		}
	 
	 
	 public static Result update(){
			Form<Publisher> filledForm = publisherForm.bindFromRequest();
			if (filledForm.hasErrors()) {
				return badRequest(edit.render(filledForm));
			} else {
			
			Publisher publisher = filledForm.get();
			Publisher.update(publisher);
			return ok(list.render(Publisher.all()));
			}
			
		}
	 
	 public static Result delete(Long id){
		 Publisher.delete(id);
		 flash("success", AppConstants.SUCCESSFUL_DELETE_MESSAGE);
		 return ok(list.render(Publisher.all()));
	 }
	 
	 
}


