package models.admission;

import java.util.List;

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

    
//	@OneToMany(mappedBy = "faculty")
//  public List<Department> departments;
	
	@Required
	public String name;
	
//	  @Required
//    @ManyToOne
//    @JoinColumn(name = "dept_id", referencedColumnName = "id")
//    public Department department;

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
    

}
