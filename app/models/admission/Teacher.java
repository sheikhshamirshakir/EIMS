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
  
/*
 * 
 * AUTHOR: SHAON   
 * CREATE DATE: 10th MARCH 2015
 * 
 */

@Entity
@Table (name="Teacher")
public class Teacher extends Model {
	
    public Long id;
    
    public String name;
	
    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    public Department department;

    @ManyToOne
    @JoinColumn(name = "designation_id", referencedColumnName = "id")
    public Designation designation;
    
//	public String department;
//	public String designation;
	
	public static Finder<Long,Teacher> find =  new Finder(Long.class, Teacher.class);
	
	public static List<Teacher> all(){
		return find.all();
	}
	
	public static void create(Teacher teacher){
		teacher.save();
	}
	
	public static void update(Teacher teacher){
		teacher.update();
	}
	
	public static void delete(Long id){
		delete(id);
	}

	public static Teacher findById(Long id2) {
		
		Teacher teacher = find.byId(id2);
			
		return teacher;
	}
	
	 public static Map<String,String> getTeachersAsMap() {
	        LinkedHashMap<String,String> teachers = new LinkedHashMap<String,String>();
	        for(Teacher teacher: Teacher.find.orderBy("name").findList()) {
	        	teachers.put(teacher.id.toString(), teacher.name);
	        }
	        
	        return teachers;
	    }
	
	    
	
}
