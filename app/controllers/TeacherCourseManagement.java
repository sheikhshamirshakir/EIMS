/**Created By Sheikh Shamir Shakir Shomoy Solution
 * Shomoy Solution
 * On 18-Mar-2015
 ***/
package controllers;

import java.util.Date;
import java.util.List;
import java.util.Map;

import dummymodels.DummyTeacherCourse;
import models.admission.Course;
import models.admission.Department;
import models.admission.Teacher;
import models.admission.TeacherCourse;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import services.UpdatePermission;
import utils.AppConstants;
import views.html.teachercourse.*;

public class TeacherCourseManagement extends Controller{

	static Form<DummyTeacherCourse> teacherCourseForm = Form.form(DummyTeacherCourse.class);
	
	public static Result createWithTeacher(String teacherId) {
		Long tId = Long.parseLong(teacherId);
		List<Boolean> isChecked = UpdatePermission.viewUpdatePermission(tId);
		String teacherName = Teacher.findById(tId).name;
		return ok(createWithoutList.render(tId, teacherName, isChecked));
	}


    public static Result create() {
        return ok(createWithList.render(teacherCourseForm));
	}

	
    public static Result insert() {
		Form<DummyTeacherCourse> filledForm = teacherCourseForm.bindFromRequest();
		if (filledForm.hasErrors()) {
			System.out.println(filledForm.errors().toString());
			
			return badRequest(createWithList.render(filledForm));

		} else {

			DummyTeacherCourse dtc = filledForm.get();
			TeacherCourse.deleteTeacherCourseByTeacher(dtc.teacherId);

			Map<String, String[]> reqBody = request().body().asFormUrlEncoded();
			String[] courses = reqBody.get("courseId");
			Teacher teacher = Teacher.findById(dtc.teacherId);

			if(courses!=null){
			for (String c : courses) {
				Long courseId = Long.parseLong(c);
				TeacherCourse teacherCourse = new TeacherCourse();
				teacherCourse.teacher = teacher;
				teacherCourse.course = Course.findById(courseId);
				TeacherCourse.create(teacherCourse);
			}
			}
			
			
		}
		return redirect(routes.TeacherCourseManagement.list());
	}


	public static Result list() {
		return ok(list.render(TeacherCourse.all()));
	}

	public static Result show(Long id) {
		TeacherCourse teacherCourse = TeacherCourse.findById(id);
		return ok(show.render(teacherCourse));
	}

	public static Result edit(Long id) {
		return ok("");
	}

	public static Result update() {
		return ok("");
	}



	 
}


