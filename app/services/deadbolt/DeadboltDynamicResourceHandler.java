package services.deadbolt;

import be.objectify.deadbolt.core.DeadboltAnalyzer;
import be.objectify.deadbolt.core.models.Permission;
import be.objectify.deadbolt.core.models.Subject;
import be.objectify.deadbolt.java.DeadboltHandler;
import be.objectify.deadbolt.java.DynamicResourceHandler;
import play.Logger;
import play.mvc.Http;

import java.security.Permissions;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import models.*;
import services.Constants;


public class DeadboltDynamicResourceHandler implements DynamicResourceHandler {

	/** This method is called to check whethere user has permission to the resourece */
	//public boolean isAllowed(String menuId, String meta, DeadboltHandler deadboltHandler, Http.Context context) {
	public boolean isAllowed(String permissionId, String meta, DeadboltHandler deadboltHandler, Http.Context context) {
		/*
		if (menuId == null || menuId.isEmpty())
			return false;

		int mnId;
		try { mnId = Integer.parseInt(menuId); }
		catch (Exception ex) { return false; }
		
		Menu menu = Menu.get(mnId);
		if (menu == null) { return false; }
		*/
		
		if (permissionId == null || permissionId.isEmpty())
			return false;
		
		models.Permission permission = models.Permission.findByPermissionName(permissionId);
		if (permission == null) { return false; }
		
		String username = context.session().get(Constants.SESN_USERNAME);
		if(username == null || username.isEmpty())
			return false;
		
		User user = User.findByUsername(username);
		if (user==null)
			return false;

		List<UserPermission> userPermissions = UserPermission.findListByUserIdAndPermissionId(user.id, permission.id);
		if (userPermissions == null || userPermissions.size() == 0)
			return false;
		
		return true;
	}

	
	public boolean checkPermission(String permissionValue, DeadboltHandler deadboltHandler, Http.Context ctx) {
		boolean permissionOk = false;
		Subject subject = deadboltHandler.getSubject(ctx);

		if (subject != null) {
			List<? extends Permission> permissions = subject.getPermissions();
			for (Iterator<? extends Permission> iterator = permissions.iterator(); !permissionOk && iterator.hasNext();) {
				Permission permission = iterator.next();
				permissionOk = permission.getValue().contains(permissionValue);
			}
		}

		return permissionOk;
	}
}
