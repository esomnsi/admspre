/**
* -------------------------------------------------------------------------------------------------------
* Copyright (C) 2012 Ericsson India Global Pvt Limited
* All Rights Reserved
* Project         		    :  IT_MSSP
* Module				    :  com.ericsson.mssp.login.service
* File name       		    :  ManageUserAccess.java
* Description				:	<To Do>
* Author, Date & Release	:	Jan 15, 20132013
* Modification history		:
* Number	|Date   	   |Author        |Remark
* -----------------------------------------------------------------------------------------------------
* 1      	| Jan 15, 2013  	   |eshtgar   	  | Created.
* -----------------------------------------------------------------------------------------------------
**/
 
package com.ericsson.mssp.login.service;

import java.util.List;
import java.util.Map;

import org.springframework.aop.ThrowsAdvice;

import com.ericsson.mssp.common.dto.UserAccessDTO;
import com.ericsson.mssp.common.entity.ApplicationRole;
import com.ericsson.mssp.common.exception.MSSPException;

/**
 * @author eshtgar
 *
 */
public interface IManageUserAccessService {	
	public List<UserAccessDTO> searchLDAPUsers(UserAccessDTO userAccessDTO,String allUsers) throws MSSPException; 
	public List<String> getRoles();
	public boolean saveAssignedRole(String roleData) throws MSSPException;
	public List<UserAccessDTO> getUsersList() throws MSSPException;
	public Map<Integer,String> getRolesByUserSignum(String signum) throws MSSPException;	
}
