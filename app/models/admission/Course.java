package models.admission;

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
@Table (name="course")
public class Course extends Model{
	
	@Id
    public Long id;    
	
	
	@Required
	public String name;
	
	public String code;
	
	public String credit;
	
	
	public static Finder<Long, Course> find = new Finder(Long.class, Course.class);

    public static List<Course> all() {
        return find.all();
    }
    
    public static Course findById(Long id) {
        return find.where().eq("id", id).findUnique();
    }

    public static void create(Course course) {
    	course.save();
    }
    
    public static void update(Course course) {
    	course.update();
    }
    
    public static void delete(Long id){
    	delete(id);
    }
    
    public static Map<String,String> getCoursesAsMap() {
        LinkedHashMap<String,String> courses = new LinkedHashMap<String,String>();
        for(Course course: Course.find.orderBy("name").findList()) {
        	courses.put(course.id.toString(), course.name);
        }
        
        return courses;
    }
    

}
