/**Created By Sheikh Shamir Shakir Shomoy Solution
 * Shomoy Solution
 * On 23-Apr-2015
 ***/
package controllers;

import java.util.Date;
import java.util.List;

import models.Menu;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import utils.AppConstants;
import views.html.menu.*;

public class MenuManagement extends Controller{

	static Form<Menu> menuForm = Form.form(Menu.class);
	
	 public static Result create() {
	        return ok(create.render(menuForm));
	    }
	 
	 public static Result save() {
		 Form<Menu> filledForm = menuForm.bindFromRequest();
		 Menu menu = filledForm.get();
	     Menu.create(menu);
	   	 flash("success", AppConstants.SUCCESS_MESSAGE);
	     //return ok("");
	   	return redirect(controllers.routes.MenuManagement.list());
	    }

	 public static Result list(){
	    	List<Menu> menus = Menu.all();
	     	return ok(list.render(menus));
	    }

	 
	 public static Result show(Integer id) {
			Menu menu = Menu.get(id);
					
		  	if (menu == null) {
				flash("error", AppConstants.ERROR_MESSAGE_ID_NOT_FOUND);
//				return ok("");
				return redirect(controllers.routes.MenuManagement.list());
			} else
				return ok(show.render(menu));
		}
	 
	 public static Result edit(Integer id) {
		 Menu menu = Menu.get(id);
			
		  	if (menu == null) {
				flash("error", AppConstants.ERROR_MESSAGE_ID_NOT_FOUND);
				//return ok("");
				return redirect(controllers.routes.MenuManagement.show(id));
			}else
				return ok(edit.render(menuForm.fill(menu)));
		}
	 
	 
	 public static Result update(){
			Form<Menu> filledForm = menuForm.bindFromRequest();
			if (filledForm.hasErrors()) {
				return badRequest(edit.render(filledForm));
			} else {
			
			Menu menu = filledForm.get();
			Menu.update(menu);
			return ok(list.render(Menu.all()));
			}
			
		}
	 
	 public static Result delete(Integer id){
		 Menu.delete(id);
		 flash("success", AppConstants.SUCCESSFUL_DELETE_MESSAGE);
		 return ok(list.render(Menu.all()));
	 }
	 
	 
}


