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
@Table (name="student_to_courses")
public class StudentCourse extends Model{
	
	@Id
    public Long id;    
	
	@ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "sid")
    public Student student;
	  
	@ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    public Course course;
	
	
	public static Finder<Long, StudentCourse> find = new Finder(Long.class, StudentCourse.class);

    public static List<StudentCourse> all() {
        return find.all();
    }
    
    public static StudentCourse findById(Long id) {
        return find.where().eq("id", id).findUnique();
    }

    public static void create(StudentCourse studentCourse) {
    	studentCourse.save();
    }
    
    public static void update(StudentCourse studentCourse) {
    	studentCourse.update();
    }
    
    public static void delete(Long id){
    	delete(id);
    }
    
    
	public static List<Long> findCourseIdsByStudentId(Long id) {
		List<StudentCourse> studentCourses = StudentCourse.find.where().eq("student.sid", id).findList();
		ArrayList<Long> courses = new ArrayList<Long>(studentCourses.size());
		for (int i = 0; i < studentCourses.size(); i++) {
			courses.add(studentCourses.get(i).course.id);
		}
		return courses;
	}
	
    
	public static void deleteStudentCourseByStudent(Long id) {
		List<StudentCourse> studentCourses =StudentCourse.find.where().eq("student.sid", id).findList();

		for (StudentCourse sc : studentCourses) {
			sc.delete();
		}
	}
    
}
