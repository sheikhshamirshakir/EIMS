package controllers;

import models.admission.Employee;

import models.admission.Student;
import models.admission.Teacher;
import be.objectify.deadbolt.java.actions.Dynamic;
import services.deadbolt.DeadboltHandler;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.dashboard.*;
public class Dashboard extends Controller {

	//@Dynamic(value = "Dashboard", handler = DeadboltHandler.class)
	public static Result viewDashboard(){
		int allStudentNumber = Student.all().size();
		int allTeacherNumber = Teacher.all().size();
		int allStuffNumber = Employee.find.where().eq("employeeType", "Stuff").findList().size();
		return ok(dashboard.render(allStudentNumber,allTeacherNumber,allStuffNumber));
//		return ok("logged in");
		
	}
}
