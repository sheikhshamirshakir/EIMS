package models;

import play.db.ebean.*;
import play.data.validation.Constraints.*;

import java.util.*;

import javax.persistence.*;

import models.admission.StudentCourse;

@Entity
@Table(name = "UsersInRoles")
public class UserRole extends Model {
	@Id
	@Column(name = "ur_id")
	public Integer id;

	// @Required
	@Column(name = "user_id")
	public Integer userId;

	@Column(name = "role_id")
	public Integer roleId;

	private static Finder<Integer, UserRole> find = new Finder(Integer.class, UserRole.class);

	public static UserRole get(int id) {
		return find.where().eq("id", id).findUnique();
	}

	public static List<UserRole> all() {
		return find.all();
	}

	public static List<UserRole> findListByUserId(int userId) {
		return find.where().eq("userId", userId).findList();
	}

	public static List<UserRole> findListByRoleId(int roleId) {
		return find.where().eq("roleId", roleId).findList();
	}

	public static List<Role> findRolesByUserId(int userId) {
		List<UserRole> userRole = findListByUserId(userId);
		List<Role> roles = new ArrayList<Role>();
		
		for(UserRole ur: userRole){
			if(ur.roleId != null){
				Role r = Role.get(ur.roleId);
				roles.add(r);
			}
		}
		
		return roles;
	}
	
	public static void delete(int id) {
		find.ref(id).delete();
	}
	  
    public static void update(UserRole userRole) {
    	userRole.update();
    }
    
    
    public static void create(UserRole userRole) {
    	userRole.save();
    }
    
   
    
	public static List<Integer> findRoleIdsByUserId(Integer id) {
		List<UserRole> userRoles = UserRole.find.where().eq("userId", id).findList();
		ArrayList<Integer> roles = new ArrayList<Integer>(userRoles.size());
		for (int i = 0; i < userRoles.size(); i++) {
			roles.add(userRoles.get(i).roleId);
		}
		return roles;
	}
	
    
	
	public static List<Integer> finduserIdsByRoleId(Integer roleId) {
		List<UserRole> userRoles = UserRole.find.where().eq("roleId", roleId).findList();
		ArrayList<Integer> users = new ArrayList<Integer>(userRoles.size());
		for (int i = 0; i < userRoles.size(); i++) {
			users.add(userRoles.get(i).userId);
		}
		return users;
	}
	
	
	public static void deleteUserRolesByUser(Integer id) {
		List<UserRole> userRoles =UserRole.find.where().eq("userId", id).findList();

		for (UserRole ur : userRoles) {
			ur.delete();
		}
	}

}