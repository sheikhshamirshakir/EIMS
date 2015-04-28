package models;

import play.db.ebean.*;
import play.data.validation.Constraints.*;

import java.util.*;

import javax.persistence.*;


@Entity
@Table(name="PermissionsCriteria")
public class Permission extends Model implements be.objectify.deadbolt.core.models.Permission{
	@Id
	@Column(name="permission_id")
	public Integer id;

	//@Required
	@Column(name="permission_name")
	public String permissionName;
	
//	@Column(name="ViewOrder")
//	public Integer viewOrder;
	
//	@Column(name="ModuleID")
//	public Integer moduleId;
	
	/** implements deadbolt method */
	public String getValue() {
        return permissionName;
    }
	/** --------------------------- */

	private static Finder<Integer, Permission> find = new Finder(Integer.class, Permission.class);
	
	public static Permission get(int id) {
		return find.where()
			.eq("id", id)
			.findUnique();
	}
	
	public static List<Permission> all() {
		return find.all();
	}
	
	public static Permission findByPermissionName(String permissionName) {
		if(permissionName==null || permissionName.isEmpty())
			return null;
		
		return find.where()
			.eq("permissionName", permissionName)
			.findUnique();
	}
	
	
	 public static void create(Permission permission) {
	    	permission.save();
	    }
	    
	    public static void update(Permission permission) {
	    	permission.update();
	    }
	    
	    public static void delete(Integer id){
	    	delete(id);
	    }
	    
	    public static Map<String,String> getPermissionsAsMap() {
	        LinkedHashMap<String,String> permissions = new LinkedHashMap<String,String>();
	        for(Permission permission: Permission.find.orderBy("permissionName").findList()) {
	        	permissions.put(permission.id.toString(), permission.permissionName);
	        }
	        
	        return permissions;
	    }
	
}