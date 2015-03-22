package models.admission;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import play.data.validation.Constraints.Required;
import play.db.ebean.Model;


@Entity
@Table (name="teachers_to_courses")
public class TeacherCourse extends Model{
	
	@Id
    public Long id;    
	
	
    @ManyToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "tid")
    public Teacher teacher;
	  
	@ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    public Course course;
	
	
	public static Finder<Long, TeacherCourse> find = new Finder(Long.class, TeacherCourse.class);

    public static List<TeacherCourse> all() {
        return find.all();
    }
    
    public static TeacherCourse findById(Long id) {
        return find.where().eq("id", id).findUnique();
    }

    public static void create(TeacherCourse teacherCourse) {
    	teacherCourse.save();
    }
    
    public static void update(TeacherCourse teacherCourse) {
    	teacherCourse.update();
    }
    
    public static void delete(Long id){
    	delete(id);
    }
    
    
	public static List<Long> findCourseIdsByTeacherId(Long id) {
		List<TeacherCourse> teacherCourses = TeacherCourse.find.where().eq("teacher.tid", id).findList();
		ArrayList<Long> courses = new ArrayList<Long>(teacherCourses.size());
		for (int i = 0; i < teacherCourses.size(); i++) {
			courses.add(teacherCourses.get(i).course.id);
		}
		return courses;
	}
	
    
	public static void deleteTeacherCourseByTeacher(Long id) {
		List<TeacherCourse> teacherCourses =TeacherCourse.find.where().eq("teacher.tid", id).findList();

		for (TeacherCourse tc : teacherCourses) {
			tc.delete();
		}
	}
    
}
