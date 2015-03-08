package models.admission;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;


@Entity
@Table (name="department")
public class Department extends Model{
	
	@Id
    public Long id;
	
	@Required
	public String name;

	public static Finder<Long, Department> find = new Finder(Long.class, Department.class);

    public static List<Department> all() {
        return find.all();
    }
    
    public static Department findById(Long id) {
        return find.where().eq("id", id).findUnique();
    }

    public static void create(Department Department) {
    	Department.save();
    }
    
    public static void update(Department Department) {
    	Department.update();
    }
    
    public static void delete(Long id){
    	delete(id);
    }

}
