/**Created By Sheikh Shamir Shakir Shomoy Solution
 * Shomoy Solution
 * On 25-Mar-2015
 ***/
package controllers;

import java.util.Date;
import java.util.List;
import java.util.Map;

import dummymodels.DummyStudentCourse;
import models.admission.Course;
import models.admission.Department;
import models.admission.Student;
import models.admission.StudentCourse;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import services.UpdatePermission;
import utils.AppConstants;
import views.html.studentcourse.*;

public class StudentCourseManagement extends Controller{

	static Form<DummyStudentCourse> studentCourseForm = Form.form(DummyStudentCourse.class);
	
	public static Result createWithStudent(String studentId) {
		Long tId = Long.parseLong(studentId);
		List<Boolean> isChecked = UpdatePermission.viewUpdatePermission(tId);
		String studentName = Student.findById(tId).name;
		return ok(createWithoutList.render(tId, studentName, isChecked));
	}


    public static Result create() {
        return ok(createWithList.render(studentCourseForm));
	}

	
    public static Result insert() {
		Form<DummyStudentCourse> filledForm = studentCourseForm.bindFromRequest();
		if (filledForm.hasErrors()) {
			System.out.println(filledForm.errors().toString());
			
			return badRequest(createWithList.render(filledForm));

		} else {

			DummyStudentCourse dtc = filledForm.get();
			StudentCourse.deleteStudentCourseByStudent(dtc.studentId);

			Map<String, String[]> reqBody = request().body().asFormUrlEncoded();
			String[] courses = reqBody.get("courseId");
			Student student = Student.findById(dtc.studentId);

			if(courses!=null){
			for (String c : courses) {
				Long courseId = Long.parseLong(c);
				StudentCourse studentCourse = new StudentCourse();
				studentCourse.student = student;
				studentCourse.course = Course.findById(courseId);
				StudentCourse.create(studentCourse);
			}
			}
		}
		return redirect(routes.StudentCourseManagement.list());
	}


	public static Result list() {
		return ok(list.render(StudentCourse.all()));
	}

	public static Result show(Long id) {
		StudentCourse studentCourse = StudentCourse.findById(id);
		return ok(show.render(studentCourse));
	}

	public static Result edit(Long id) {
		return ok("");
	}

	public static Result update() {
		return ok("");
	}



	 
}


