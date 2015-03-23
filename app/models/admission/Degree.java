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
@Table (name="degree")
public class Degree extends Model{
	
	@Id
    public Long id;    
	
	@OneToMany(mappedBy = "degree")
    public List<ClassYear> classYears;
	
	@Required
	public String name;
	
	
    @ManyToOne
    @JoinColumn(name = "dept_id", referencedColumnName = "id")
    public Department department;
    
    @OneToMany(mappedBy = "degree")
    public List<Student> students;
    

	public static Finder<Long, Degree> find = new Finder(Long.class, Degree.class);

    public static List<Degree> all() {
        return find.all();
    }
    
    public static Degree findById(Long id) {
        return find.where().eq("id", id).findUnique();
    }

    public static void create(Degree degree) {
    	degree.save();
    }
    
    public static void update(Degree degree) {
    	degree.update();
    }
    
    public static void delete(Long id){
    	delete(id);
    }
    
    public static Map<String,String> getDegreesAsMap() {
        LinkedHashMap<String,String> degrees = new LinkedHashMap<String,String>();
        for(Degree degree: Degree.find.orderBy("name").findList()) {
        	degrees.put(degree.id.toString(), degree.name);
        }
        
        return degrees;
    }
    

}
