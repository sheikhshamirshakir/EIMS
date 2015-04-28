package services;
import java.util.ArrayList;
import java.util.List;

import models.Role;
import models.UserRole;


public class UserRoleCheck{

	public static List<Boolean> check(Integer id) {
		List<Integer> roleOfThisUser = UserRole.findRoleIdsByUserId(id);

		List<Role> allRoles = Role.all();
		
		ArrayList<Integer> allRoleIds = new ArrayList<Integer>();
		
		for (int i = 0; i < allRoles.size(); i++) {
			allRoleIds.add(allRoles.get(i).id);
		}
		
		ArrayList<Boolean> isChecked = new ArrayList<Boolean>();
		for (Integer roleId : allRoleIds) {
			isChecked.add(roleOfThisUser.contains(roleId) ? true : false);
		}
		return isChecked;
		

	}
}
