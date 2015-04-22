package models;

import play.db.ebean.*;
import play.data.validation.Constraints.*;

import java.util.*;

import javax.persistence.*;

@Entity
@Table(name = "UsersInRoles")
public class UserRole extends Model {
	@Id
	@Column(name = "urID")
	public Integer id;

	// @Required
	@Column(name = "UserID")
	public Integer userId;

	@Column(name = "RoleID")
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

}