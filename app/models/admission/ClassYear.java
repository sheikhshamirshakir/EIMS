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
@Table (name="ClassYear")
public class ClassYear extends Model {
	
	@Id
    public Long id;
	
	public String name;
	
	
    @ManyToOne
    @JoinColumn(name = "degree_id", referencedColumnName = "id")
    public Degree degree;
	
	
	@OneToMany(mappedBy = "classYear")
    public List<SectionSemester> sectionSemesters;
	
	
	
	@OneToMany(mappedBy = "classYear")
    public List<Student> students;

	
	public static Finder<Long,ClassYear> find =  new Finder(Long.class, ClassYear.class);
	
	public static List<ClassYear> all(){
		return find.all();
	}
	
	public static void create(ClassYear classYear){
		classYear.save();
	}
	
	public static void update(ClassYear classYear){
		classYear.update();
	}
	
	public static void delete(Long id){
		delete(id);
	}

	public static ClassYear findById(Long id2) {
		
		ClassYear classYear = find.byId(id2);
			
		return classYear;
	}
	
	 public static Map<String,String> getClassYearsAsMap() {
	        LinkedHashMap<String,String> classYears = new LinkedHashMap<String,String>();
	        for(ClassYear classYear: ClassYear.find.orderBy("name").findList()) {
	        	classYears.put(classYear.id.toString(), classYear.name);
	        }
	        
	        return classYears;
	    }
	    
	
}
