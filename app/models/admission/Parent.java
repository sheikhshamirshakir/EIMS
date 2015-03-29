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
	
	@Id
    public Long pid;
    
    public String name;
    
	public String email;
	

    public String fathersName;            
	public String fatherProfession;
	public String workAddress;
	public String nationalID;        
	public String fatherTelephone; 
	public String fatherMobile;
	public String fatherFax;    
	public String fatherEmail;
	public String fatherEduQual; // Fathers educational Qualification 
	public String mothersName; 
	public String motherProfession;
	public String motherMobile; 
	public String motherMail; 
	public String mothersEduQual; // Mother Educational Qualification
	
	
	public String phone;
	public String profession; 
	public String telephone;
    public String mobile; 
    public String fax;  
	public String guardian_edu; 
    public String relationship; 
    public String degreeName; 
    public String instituteName;
    public String board;
    public String result;  
    public String passingYear; 

	
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
		List<Parent> parents =  Parent.find.where().orderBy("pid Desc").findList();
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
