package models;

import play.db.ebean.*;
import play.data.validation.Constraints.*;

import java.util.*;

import javax.persistence.*;

import models.admission.Student;
import be.objectify.deadbolt.core.models.Permission;
import be.objectify.deadbolt.core.models.Subject;


@Entity
@Table(name="UserInfo")
public class User extends Model implements Subject {
	@Id
	@Column(name="id")
	public Integer id;

	//@Required
	@Column(name="user_name")
	public String username;
	

	@Column(name="role_id")
	public Integer roleId;
	
//	@Column(name="PasswordSalt")
//	public String passwordSalt;
//	
	@Column(name="password")
	public String password;
	
	@Column(name="user_id")
	public Integer userId;
	
	
	
	/** implements deadbolt methods */
    @Override
    public List<? extends be.objectify.deadbolt.core.models.Role> getRoles() {
        return UserRole.findRolesByUserId(id);
    }
    
    @Override
    public List<? extends Permission> getPermissions() {
        return UserPermission.findPermissionsByUserId(id);
    }
    
    @Override
    public String getIdentifier() {
        return username;
    }
    /** --------------------------------------- */
	
	private static Finder<Integer, User> find = new Finder(Integer.class, User.class);
	
	public static User get(int id) {
		return find.where()
			.eq("id", id)
			.findUnique();
	}
	
	public static User findByUsername(String username) {
		return find.where()
				.eq("username", username)
				.findUnique();
	}

	public static User findByUsernameAndPassword(String username, String password) {
		return find.where()
				.eq("username", username)
				.eq("password", password)
				.findUnique();
	}
	
	
	 public static int findRoleByUserName(String username){
		 return find.where()
			 .eq("username", username)
			 .findUnique().roleId;
		} 
	 
	
	public static List<User> all() {
		return find.all();
	}

	public static void create(User userInfo) {
		userInfo.save();
	}

	public static void delete(int id) {
		find.ref(id).delete();
	}
	
	    
	    public static void update(User user) {
	    	user.update();
	    }
	    
	    public static Map<String,String> getUsersAsMap() {
	        LinkedHashMap<String,String> users = new LinkedHashMap<String,String>();
	        for(User user: User.find.orderBy("username").findList()) {
	        	users.put(user.id.toString(), user.username);
	        }
	        
	        return users;
	    }
	    
	    
	

}