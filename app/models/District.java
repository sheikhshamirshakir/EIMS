package models;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import play.db.ebean.Model;

@Entity
@Table(name="DISTRICT")
public class District extends Model{
	@Id
    public Long id;
	
	public String name;
	
	@ManyToOne
    @JoinColumn(name = "division_id", referencedColumnName = "id")  // TOREAD;
    public Division division;
	
	public static Finder<Long,District> find =  new Finder(Long.class, District.class);
	
	public static List<District> all(){
		return find.all();
	}
	
	public static Map<Long,String> getDistrictByDivId(Long divId){
		List<District> listDistrict = find.where().eq("division_id", divId).findList();
		Map<Long,String> listDist = new HashMap<Long, String>();
		
		for(District dist:listDistrict){
			Long distId = dist.id;
			String distName = dist.name;
			listDist.put(distId, distName);
		}
		return listDist;
	}
	
	public static District findById(Long id) {
		
	District district = find.byId(id);
			
		return district;
	}
	
	public static Map<String,String> getDistrictsAsMap() {
		LinkedHashMap<String,String> districts = new LinkedHashMap<String,String>();
			for(District dist: District.find.orderBy("name").findList()) {
				districts.put(dist.id.toString(), dist.name);
		}
	        
	 return districts;
	}
	
}
