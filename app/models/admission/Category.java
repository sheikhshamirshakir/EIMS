package models.admission;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import dummymodels.DummyEmployee;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;


@Entity
@Table (name="job_category")
public class Category extends Model{
	
	@Id
    public Long id;
	
	@Required
	public String name;
	
	  @OneToMany(mappedBy = "category")
	    public List<Employee> employees;

    
    public static Finder<Long, Category> find = new Finder(Long.class, Category.class);

    public static List<Category> all() {
        return find.all();
    }
    
    public static Category findById(Long id) {
        return find.where().eq("id", id).findUnique();
    }

    public static void create(Category category) {
    	category.save();
    }
    
    public static void update(Category category) {
    	category.update();
    }
    
    public static void delete(Long id){
    	delete(id);
    }
    

    public static Map<String,String> getCategoriesAsMap() {
        LinkedHashMap<String,String> categories = new LinkedHashMap<String,String>();
        for(Category category: Category.find.orderBy("name").findList()) {
        	categories.put(category.id.toString(), category.name);
        }
        
        return categories;
    }
    

}
