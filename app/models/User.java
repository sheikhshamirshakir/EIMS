package models;

import play.db.ebean.*;
import play.data.validation.Constraints.*;

import java.util.*;

import javax.persistence.*;

import be.objectify.deadbolt.core.models.Permission;
import be.objectify.deadbolt.core.models.Subject;


@Entity
@Table(name="UserInfo")
public class User extends Model implements Subject {
	@Id
	@Column(name="UserID")
	public Integer id;

	//@Required
	@Column(name="UserName")
	public String username;
	
	@Column(name="FullName")
	public String fullName;
	
	@Column(name="RoleID")
	public Integer roleId;
	
	@Column(name="PasswordSalt")
	public String passwordSalt;
	
	@Column(name="SaltedHash")
	public String hashedPassword;
	
	@Column(name="IsActive")
	public boolean isActive;

	@Column(name="IsLocked")
	public boolean isLocked;
	
	@Column(name="IsApproved")
	public boolean isApproved;
	
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
				.eq("hashedPassword", password)
				.findUnique();
	}
	
	
	 public static int findRoleByUserName(String userName){
		 return find.where()
			 .eq("userName", userName)
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

}