package com.depthspace.user.model;

public class UserPermissions {
	private Integer userId; 
	private Integer permissionsId;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getPermissionsId() {
		return permissionsId;
	}
	public void setPermissionsId(Integer permissionsId) {
		this.permissionsId = permissionsId;
	} 
}
