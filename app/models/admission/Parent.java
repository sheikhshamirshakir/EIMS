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

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;


@Entity
@Table (name="parent")
public class Parent extends Model {
	
    public Long pid;
    
    public String name;
    
	public String email;
	
	public String phone;
	
	public static Finder<Long,Parent> find =  new Finder(Long.class, Parent.class);
	
	public static List<Parent> all(){
		return find.all();
	}
	
	public static void create(Parent parent){
		parent.save();
	}
	
	public static void update(Parent parent){
		parent.update();
	}
	
	public static void delete(Long id){
		delete(id);
	}

	
	public static Long findLastId(){
		List<Parent> parents =  Parent.find.where().orderBy("id Desc").findList();
		return parents.get(0).pid;
	}
	
	public static Parent findById(Long id2) {
		
		Parent parent = find.byId(id2);
			
		return parent;
	}
	
	 public static Map<String,String> getParentsAsMap() {
	        LinkedHashMap<String,String> parents = new LinkedHashMap<String,String>();
	        for(Parent parent: Parent.find.orderBy("name").findList()) {
	        	parents.put(parent.pid.toString(), parent.name);
	        }
	        
	        return parents;
	    }
	
}
