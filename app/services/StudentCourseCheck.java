package services;
import java.util.ArrayList;
import java.util.List;

import models.admission.Course;
import models.admission.StudentCourse;


public class StudentCourseCheck {

	public static List<Boolean> check(Long id) {
		List<Long> coursesOfThisStudent = StudentCourse.findCourseIdsByStudentId(id);

		List<Course> allCourse = Course.all();
		
		ArrayList<Long> allCourseIds = new ArrayList<Long>();
		
		for (int i = 0; i < allCourse.size(); i++) {
			allCourseIds.add(allCourse.get(i).id);
		}
		
		ArrayList<Boolean> isChecked = new ArrayList<Boolean>();
		for (Long courseId : allCourseIds) {
			isChecked.add(coursesOfThisStudent.contains(courseId) ? true : false);
		}
		return isChecked;
		

	}
}
