package controllers;

import java.util.List;

import be.objectify.deadbolt.java.actions.Dynamic;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;
import services.deadbolt.DeadboltHandler;
import models.User;
import models.UserPermission;
import dummymodels.Login;
import services.Password;
import views.html.loginRequest;
import services.Constants;


public class UserManagement extends Controller {
	
	private static Form<Login> loginForm = Form.form(Login.class);
	
	
	public static Result login() {
		flash("error", "Sorry, either the page does not exists or you do not have permission to this page.");
		return ok(loginRequest.render());
	}
	
	
	public static Result authenticate() {
		Form<Login> filledForm = loginForm.bindFromRequest();
		if (filledForm.hasErrors()) {
			flash("error", "Error in login, please provide all information correctly");
			return ok(loginRequest.render());
		}

		Login login = filledForm.get();
		User user = User.findByUsername(login.username);

		if (user == null) {
			flash("error", "Username does not exist.");
			return ok(loginRequest.render());
		}

//		if (!user.isApproved) {
//			flash("error", "User is not approved yet, please contact the admin.");
//			return ok(loginRequest.render());
//		}

//		if (!user.isActive) {
//			flash("error", "User is not active, please contact the admin.");
//			return ok(loginRequest.render());
//		}

//		if (user.isLocked) {
//			flash("error", "The account is locked, please contact the admin.");
//			return ok(loginRequest.render());
//		}

//		int pwdSalt = Integer.parseInt(user.passwordSalt);
//		Password pwd = new Password(login.password, pwdSalt);
		String hashedPwd = login.password;

		if (!user.password.equals(hashedPwd)) {
			flash("error", "Password does not match.");
			return ok(loginRequest.render());
		}

		session(Constants.SESN_USERNAME, login.username);
//		 int role = User.findRoleByUserName(login.username);
		
//		if(role == 134){
//			return redirect(controllers.routes.LiveManagement.showAllRequestToAdmin());
//		}
		return redirect(routes.Dashboard.viewDashboard());

//		return ok("");
	}
	
	
	public static Result logout() {
		session().clear();
		return redirect(controllers.routes.UserManagement.login());
	}
	
	
//	@Dynamic(value = "63001", handler = DeadboltHandler.class)
	public static Result dummy() {
		return ok("This is a restricted url");
	}

}
