package models.admission;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import play.data.validation.Constraints.Required;
import play.db.ebean.Model;


@Entity
@Table (name="faculty")
public class Faculty extends Model{
	
	@Id
    public Long id;
	
	@Required
	public String name;

    @Required
    @ManyToOne
    @JoinColumn(name = "univ_id", referencedColumnName = "id")
    public University university;
	
    
    
	public static Finder<Long, Faculty> find = new Finder(Long.class, Faculty.class);

    public static List<Faculty> all() {
        return find.all();
    }
    
    public static Faculty findById(Long id) {
        return find.where().eq("id", id).findUnique();
    }

    public static void create(Faculty faculty) {
    	faculty.save();
    }
    
    public static void update(Faculty faculty) {
    	faculty.update();
    }
    
    public static void delete(Long id){
    	delete(id);
    }
    

    
    

}
