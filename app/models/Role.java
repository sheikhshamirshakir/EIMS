package models;

import play.db.ebean.*;
import play.data.validation.Constraints.*;

import java.util.*;

import javax.persistence.*;

import models.admission.Course;


@Entity
@Table(name="Roles")
public class Role extends Model implements be.objectify.deadbolt.core.models.Role {
	@Id
	@Column(name="role_id")
	public Integer id;

	@Column(name="role_name")
	public String roleName;
	
	/** implements deadbolt method */
    public String getName() {
        return roleName;
    }
    /** --------------------------- */
	
	private static Finder<Integer, Role> find = new Finder(Integer.class, Role.class);
	
	public static Role get(int id) {
		return find.where()
			.eq("id", id)
			.findUnique();
	}
	
	public static List<Role> all() {
		return find.all();
	}
	
    public static void create(Role role) {
    	role.save();
    }
    
    public static void update(Role role) {
    	role.update();
    }
    
    public static void delete(int id){
    	delete(id);
    }
    
    public static Map<String,String> getRolesAsMap() {
        LinkedHashMap<String,String> roles = new LinkedHashMap<String,String>();
        for(Role role: Role.find.orderBy("roleName").findList()) {
        	roles.put(role.id.toString(), role.roleName);
        }
        
        return roles;
    }
}