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
import models.Fees.DiscountCategory;
import models.Fees.StudentCollectionType;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;


@Entity
@Table (name="student")
public class Student extends Model {
	
	@Id
    public Long sid;
    
    public String name;
    
	public String gender;
	public Date dateOfBirth;
	public String placeOfBirth; 
	
	public String nationality;
	public String presentAddress;
    public String permanentAddress;
    
    public String religion;
    public String bloodGroup;
    
    public String maritalStatus;  
    public String mobile;
    public String email;
    
    public String levelOfEducation1;
    public String degree1;
    public String majorOrGroup1;
    public String instituteName1;
    public String board1;
    public String year1;
    public String result1;
    
    public String levelOfEducation2;
    public String degree2;
    public String majorOrGroup2;
    public String instituteName2;
    public String board2;
    public String year2;
    public String result2;
    
    public String levelOfEducation3;
    public String degree3;
    public String majorOrGroup3;
    public String instituteName3;
    public String board3;
    public String year3;
    public String result3;
    
    public String levelOfEducation4;
    public String degree4;
    public String majorOrGroup4;
    public String instituteName4;
    public String board4;
    public String year4;
    public String result4;
    
    public Long parentId;
    public Long guardianId;
	
    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    public Department department;

    @ManyToOne
    @JoinColumn(name = "degree_id", referencedColumnName = "id")
    public Degree degree;
    
    @ManyToOne
    @JoinColumn(name = "faculty_id", referencedColumnName = "id")
    public Faculty faculty;
    
//    @ManyToOne
//    @JoinColumn(name = "section_id", referencedColumnName = "id")
//    public SectionSemester sectionSemester;
  
   
    @ManyToOne
    @JoinColumn(name = "divisionId", referencedColumnName = "id")
    public Division division;
    
    
    
    @OneToMany(mappedBy = "student")
    public List<StudentCollectionType> studentCollectionTypes;
    
    // public String division;  
    public String district;
    
    public Double atleastCredit;
  public Double completecredit;
    

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
	public static Long findLastId(){
		List<Student> students =  Student.find.where().orderBy("sid Desc").findList();
		return students.get(0).sid;
	}
	
	
	 public static Map<String,String> getStudentsAsMap() {
	        LinkedHashMap<String,String> students = new LinkedHashMap<String,String>();
	        for(Student student: Student.find.orderBy("name").findList()) {
	        	students.put(student.sid.toString(), student.name);
	        }
	        
	        return students;
	    }
	
}
