package models.admission;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import models.Division;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;


@Entity
@Table (name="student")
public class Student extends Model {
	
	@Id
    public Long sid;
    
    public String name;
    
	public Date dateOfBirth;
	
	public boolean gender;
    
    public Long parentId;
	
    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    public Department department;

    @ManyToOne
    @JoinColumn(name = "degree_id", referencedColumnName = "id")
    public Degree degree;
    
    @ManyToOne
    @JoinColumn(name = "class_id", referencedColumnName = "id")
    public ClassYear classYear;
    
    
    @ManyToOne
    @JoinColumn(name = "section_id", referencedColumnName = "id")
    public SectionSemester sectionSemester;
  
    public String placeOfBirth; 
    public String maritalStatus;  
    public String citizen;
    public String presentAddress;
    
    @ManyToOne
    @JoinColumn(name = "divisionId", referencedColumnName = "id")
    public Division division;
    
    // public String division;  
    public String district;
    
    public String permanentAddress;
	public String mobile;
    public String email;           

	public static Finder<Long,Student> find =  new Finder(Long.class, Student.class);
	
	public static List<Student> all(){
		return find.all();
	}
	
	public static void create(Student student){
		student.save();
	}
	
	public static void update(Student student){
		student.update();
	}
	
	public static void delete(Long id){
		delete(id);
	}

	public static Student findById(Long id2) {
		
		Student student = find.byId(id2);
			
		return student;
	}
	
	 public static Map<String,String> getStudentsAsMap() {
	        LinkedHashMap<String,String> students = new LinkedHashMap<String,String>();
	        for(Student student: Student.find.orderBy("name").findList()) {
	        	students.put(student.sid.toString(), student.name);
	        }
	        
	        return students;
	    }
	
}
