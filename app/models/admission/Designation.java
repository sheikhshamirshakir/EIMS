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
@Table (name="designation")
public class Designation extends Model{
	
	@Id
    public Long id;
	
	@Required
	public String name;

    
//    @ManyToOne
//    @JoinColumn(name = "univ_id", referencedColumnName = "id")  // TOREAD;
//    public University university;
	
    @OneToMany(mappedBy = "designation")
    public List<Teacher> teachers;  // TOREAD;
    
    
	public static Finder<Long, Designation> find = new Finder(Long.class, Designation.class);

    public static List<Designation> all() {
        return find.all();
    }
    
    public static Designation findById(Long id) {
        return find.where().eq("id", id).findUnique();
    }

    public static void create(Designation designation) {
    	designation.save();
    }
    
    public static void update(Designation designation) {
    	designation.update();
    }
    
    public static void delete(Long id){
    	delete(id);
    }
    

    public static Map<String,String> getDesignationsAsMap() {
        LinkedHashMap<String,String> designations = new LinkedHashMap<String,String>();
        for(Designation designation: Designation.find.orderBy("name").findList()) {
        	designations.put(designation.id.toString(), designation.name);
        }
        
        return designations;
    }
    

}
