package models.admission;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import play.db.ebean.Model;
  
/*
 * 
 * AUTHOR: SHAON   
 * CREATE DATE: 4th MARCH 2015
 * 
 */

@Entity
@Table (name="University")
public class University extends Model {
	
	@Id
    public Long id;
	
	public String name;
	public String email;
	public String address;
	public String personDetails;
	public String contactNo;
	    
    @OneToMany(mappedBy = "university")
    public List<Faculty> faculties;  // TOREAD;
    
	
	public static Finder<Long,University> find =  new Finder(Long.class, University.class);
	
	public static List<University> all(){
		return find.all();
	}
	
	public static void create(University university){
		university.save();
	}
	
	public static void update(University university){
		university.update();
	}
	
	public static void delete(Long id){
		delete(id);
	}

	public static University findById(Long id2) {
		
		University university = find.byId(id2);
			
		return university;
	}
	
	  public static Map<String,String> getUnivsAsMap() {
	        LinkedHashMap<String,String> universities = new LinkedHashMap<String,String>();
	        for(University univ: University.find.orderBy("name").findList()) {
	        	universities.put(univ.id.toString(), univ.name);
	        }
	        
	        return universities;
	    }
	    
	
}
