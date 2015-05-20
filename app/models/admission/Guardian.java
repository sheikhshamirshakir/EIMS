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
@Table (name="guardian")
public class Guardian extends Model {
	
	@Id
    public Long gid;
	
    public String guardianName;            
	public String guardianMobile;
   	public String guardianEmail;
	public String guarEducation; 

	
	public static Finder<Long,Guardian> find =  new Finder(Long.class, Guardian.class);
	
	public static List<Guardian> all(){
		return find.all();
	}
	
	public static void create(Guardian guardian){
		guardian.save();
	}
	
	public static void update(Guardian guardian){
		guardian.update();
	}
	
	public static void delete(Long id){
		delete(id);
	}

	
	public static Long findLastId(){
		List<Guardian> guardians =  Guardian.find.where().orderBy("gid Desc").findList();
		return guardians.get(0).gid;
	}
	
	public static Guardian findById(Long id2) {
		
		Guardian guardian = find.byId(id2);
			
		return guardian;
	}
	
	 public static Map<String,String> getGuardiansAsMap() {
	        LinkedHashMap<String,String> guardians = new LinkedHashMap<String,String>();
	        for(Guardian guardian: Guardian.find.orderBy("name").findList()) {
	        	guardians.put(guardian.gid.toString(), guardian.guardianName);
	        }
	        
	        return guardians;
	    }
	
}
