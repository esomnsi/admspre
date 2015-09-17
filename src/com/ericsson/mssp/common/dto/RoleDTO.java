package com.ericsson.mssp.common.dto;

import java.util.HashSet;
import java.util.Set;

import com.ericsson.mssp.common.entity.User;

public class RoleDTO {

	private Integer applicationRoleId;
	private String roleName;
	private String displayName;
	private Set<User> users = new HashSet<User>(0);
	
	public Integer getApplicationRoleId() {
		return applicationRoleId;
	}
	public void setApplicationRoleId(Integer applicationRoleId) {
		this.applicationRoleId = applicationRoleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
}
