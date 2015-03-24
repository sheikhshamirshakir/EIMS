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
@Table (name="section_or_semester")
public class SectionSemester extends Model{
	
    @Id
    public Long id;    

    
	
	@Required
	public String name;
	
	public String code;
	
    @ManyToOne
    @JoinColumn(name = "class_id", referencedColumnName = "id")
    public ClassYear classYear;

    
	@OneToMany(mappedBy = "sectionSemester")
    public List<Student> students;
    
	public static Finder<Long, SectionSemester> find = new Finder(Long.class, SectionSemester.class);

    public static List<SectionSemester> all() {
        return find.all();
    }
    
    public static SectionSemester findById(Long id) {
        return find.where().eq("id", id).findUnique();
    }

    public static void create(SectionSemester sectionSemester) {
    	sectionSemester.save();
    }
    
    public static void update(SectionSemester sectionSemester) {
    	sectionSemester.update();
    }
    
    public static void delete(Long id){
    	delete(id);
    }
    
    
	
	 public static Map<String,String> getSectionSemestersAsMap() {
	        LinkedHashMap<String,String> sectionSemesters = new LinkedHashMap<String,String>();
	        for(SectionSemester sectionSemester: SectionSemester.find.orderBy("name").findList()) {
	        	sectionSemesters.put(sectionSemester.id.toString(), sectionSemester.name);
	        }
	        
	        return sectionSemesters;
	    }
	    

}
