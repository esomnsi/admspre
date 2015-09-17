/**
* -------------------------------------------------------------------------------------------------------
* Copyright (C) 2012 Ericsson India Global Pvt Limited
* All Rights Reserved
* Project         		    :  IT_MSSP
* Module				    :  com.ericsson.mssp.login.service.impl
* File name       		    :  ManageUserAccessImpl.java
* Description				:	<To Do>
* Author, Date & Release	:	Jan 15, 20132013
* Modification history		:
* Number	|Date   	   |Author        |Remark
* -----------------------------------------------------------------------------------------------------
* 1      	| Jan 15, 2013  	   |eshtgar   	  | Created.
* -----------------------------------------------------------------------------------------------------
**/
 
package com.ericsson.mssp.login.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.mvel2.optimizers.impl.refl.nodes.ArrayLength;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ericsson.mssp.common.dao.IManageUserAccessDAO;
import com.ericsson.mssp.common.dto.UserAccessDTO;
import com.ericsson.mssp.common.entity.ApplicationRole;
import com.ericsson.mssp.common.entity.User;
import com.ericsson.mssp.common.exception.MSSPException;
import com.ericsson.mssp.login.service.IManageUserAccessService;


/**
 * @author eshtgar
 *
 */
@Service
public class ManageUserAccessServiceImpl implements IManageUserAccessService{
	
public static Logger logger = Logger.getLogger(ManageUserAccessServiceImpl.class);
	
	@Autowired
	private IManageUserAccessDAO manageUserAccessDAO;

	@Override
	public List<UserAccessDTO> searchLDAPUsers(UserAccessDTO userAccessDTO,String allUsers) {
		// TODO Auto-generated method stub
		List<UserAccessDTO> resultSet = manageUserAccessDAO.searchLDAPUsers(userAccessDTO,allUsers);	
		
		return resultSet;
	}

	@Override
	public List<String> getRoles() {
		List<String> roleList = manageUserAccessDAO.getRoles();
		return roleList;
	}

	
	@Override
	public boolean saveAssignedRole(String roleData) throws MSSPException {
		UserAccessDTO userAccessDTO = new UserAccessDTO();	
		List<String> userPersonalInfo = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(roleData,"$");
		List<String> data = new ArrayList<String>();
		List<ApplicationRole> roles = new ArrayList<ApplicationRole>();
		
		while (st.hasMoreElements()) {
			data.add((String)st.nextElement());
		}
		
		StringTokenizer st1 = new StringTokenizer(data.get(0),"|");	
		
		while(st1.hasMoreElements()){
			userPersonalInfo.add((String) st1.nextElement());
		}
		
		
		userAccessDTO.setFirstName(userPersonalInfo.get(0));
		userAccessDTO.setLastName(userPersonalInfo.get(1));
		userAccessDTO.setSignumId(userPersonalInfo.get(2));
		userAccessDTO.setEmailId(userPersonalInfo.get(3));
		userAccessDTO.setContactNumber(userPersonalInfo.get(4));
		List<ApplicationRole> r = null;
		if(data.size()>1 && data.get(1)!=null && !data.isEmpty()){
			r =  splitRoles(data.get(1));
		}
		
		userAccessDTO.setRoleList(r);
		
	
		
		//StringTokenizer st2 = new StringTokenizer(data.get(1),"$");	
		
		/*String[] r = data.get(1).split("$");
		
		System.out.println(r.length);*/
		
		//System.out.println(data.get(1));
		/*while(st2.hasMoreTokens()){
			System.out.println(st2.nextToken());
			role  = new ApplicationRole();	
			if("superUser".equalsIgnoreCase((String) st2.nextElement())){
				role.setApplicationRoleId(Integer.valueOf(1));
			}else if("solManager".equalsIgnoreCase((String) st2.nextElement())){
				role.setApplicationRoleId(Integer.valueOf(1));
			}else if("approver".equalsIgnoreCase((String) st2.nextElement())){
				role.setApplicationRoleId(Integer.valueOf(1));
			}else if("guest".equalsIgnoreCase((String) st2.nextElement())){
				role.setApplicationRoleId(Integer.valueOf(1));
			}
			role.setApplicationRoleId(Integer.valueOf((String) st2.nextElement()));
			roles.add(role);
		}*/
		
		boolean rolesAssigned = manageUserAccessDAO.saveAssignedRole(userAccessDTO);	
		return rolesAssigned;
	}

	@Override
	public List<UserAccessDTO> getUsersList() throws MSSPException {
		// TODO Auto-generated method stub
		List<UserAccessDTO> usersList = manageUserAccessDAO.getUsersList();
		return usersList;
	}

	@Override
	public Map<Integer,String> getRolesByUserSignum(String signum)
			throws MSSPException {
		List<User> user  = manageUserAccessDAO.getRolesByUserSignum(signum);
		Map<Integer, String> roles = new HashMap<Integer, String>();
		
		if(user!=null){		
			for(User u: user){		
				logger.info("Retriving roles for:: " + u.getFirstName() + u.getLastName());
				List<ApplicationRole> applicationRoles = u.getApplicationRoles();				
				for(ApplicationRole ap: applicationRoles){
					roles.put(ap.getApplicationRoleId(),ap.getDisplayName());
				}			
			}
		}else{
			return null;
		}
		logger.info("Roles retrived :: " + roles.size());
		return roles;
	}
	
	public List<ApplicationRole> splitRoles(String roleStr) throws MSSPException{
		List<ApplicationRole> roles = new ArrayList<ApplicationRole>();
		ApplicationRole role = null;
		String [] str = roleStr.split(",");
		
		for(String s:str){
			role = new ApplicationRole();
			if("superUser".equalsIgnoreCase(s)){
				role.setApplicationRoleId(Integer.valueOf(1));
			}else if("solManager".equalsIgnoreCase(s)){
				role.setApplicationRoleId(Integer.valueOf(3));
			}else if("approver".equalsIgnoreCase(s)){
				role.setApplicationRoleId(Integer.valueOf(2));
			}else if("guest".equalsIgnoreCase(s)){
				role.setApplicationRoleId(Integer.valueOf(4));
			}
			
			roles.add(role);
		}
		
		return roles;
	}
}
