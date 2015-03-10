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
@Table (name="degree")
public class Degree extends Model{
	
	@Id
    public Long id;    
	
	
	@Required
	public String name;
	
	@Required
    @ManyToOne
    @JoinColumn(name = "dept_id", referencedColumnName = "id")
    public Department department;

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
    

}
