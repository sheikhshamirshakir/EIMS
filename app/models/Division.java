package models;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import play.db.ebean.Model;
@Entity
@Table(name="DIVISION")
public class Division extends Model {
	
	@Id
    public Long id;
	
	public String name;
	
	public static Finder<Long,Division> find =  new Finder(Long.class, Division.class);
	
	@OneToMany(mappedBy = "division")
    public List<District> districts; 
	
	public static List<Division> all()
	{
		return find.all();
	}
	
	public static Map<String,String> getDivisionsAsMap() {
		LinkedHashMap<String,String> divisions = new LinkedHashMap<String,String>();
			for(Division division: Division.find.orderBy("name").findList()) {
				divisions.put(division.id.toString(), division.name);
		}
	        
	 return divisions;
	}
	
	
}
