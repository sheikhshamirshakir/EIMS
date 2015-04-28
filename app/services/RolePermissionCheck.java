package services;
import java.util.ArrayList;
import java.util.List;

import models.Permission;
import models.UserPermission;;


public class RolePermissionCheck{

	public static List<Boolean> check(Integer id) {
		List<Integer> permissionOfThisRole = UserPermission.findPermissionsByRoleId(id);

		List<Permission> allPermission = Permission.all();
		
		ArrayList<Integer> allPermissionIds = new ArrayList<Integer>();
		
		for (int i = 0; i < allPermission.size(); i++) {
			allPermissionIds.add(allPermission.get(i).id);
		}
		
		ArrayList<Boolean> isChecked = new ArrayList<Boolean>();
		for (Integer permissionId : allPermissionIds) {
			isChecked.add(permissionOfThisRole.contains(permissionId) ? true : false);
		}
		return isChecked;
		

	}
}
