package services;
import java.util.ArrayList;
import java.util.List;

import models.admission.Course;
import models.admission.TeacherCourse;

public class UpdatePermission {

	public static List<Boolean> viewUpdatePermission(Long id) {
		List<Long> coursesOfThisTeacher = TeacherCourse.findCourseIdsByTeacherId(id);

		List<Course> allCourse = Course.all();
		
		ArrayList<Long> allCourseIds = new ArrayList<Long>();
		
		for (int i = 0; i < allCourse.size(); i++) {
			allCourseIds.add(allCourse.get(i).id);
		}
		
		ArrayList<Boolean> isChecked = new ArrayList<Boolean>();
		for (Long courseId : allCourseIds) {
			isChecked.add(coursesOfThisTeacher.contains(courseId) ? true : false);
		}
		return isChecked;
		

	}
}
