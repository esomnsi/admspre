/**
* -------------------------------------------------------------------------------------------------------
* Copyright (C) 2012 Ericsson India Global Pvt Limited
* All Rights Reserved
* Project         		    :  IT_MSSP
* Module				    :  com.ericsson.mssp.login.service
* File name       		    :  IManageUserAccess.java
* Description				:	<To Do>
* Author, Date & Release	:	Jan 15, 20132013
* Modification history		:
* Number	|Date   	   |Author        |Remark
* -----------------------------------------------------------------------------------------------------
* 1      	| Jan 15, 2013  	   |eshtgar   	  | Created.
* -----------------------------------------------------------------------------------------------------
**/
 
package com.ericsson.mssp.common.dao;

import java.util.List;

import com.ericsson.mssp.common.dto.UserAccessDTO;
import com.ericsson.mssp.common.entity.ApplicationRole;
import com.ericsson.mssp.common.entity.User;
import com.ericsson.mssp.common.exception.MSSPException;

/**
 * @author eshtgar
 *
 */
public interface IManageUserAccessDAO{	
	public List<UserAccessDTO> searchLDAPUsers(UserAccessDTO userAccessDTO, String allUsers);
	public List<String> getRoles();
	public boolean saveAssignedRole(UserAccessDTO userAccessDTO) throws MSSPException;
	public List<UserAccessDTO> getUsersList() throws MSSPException;
	public List<User> getRolesByUserSignum(String signum) throws MSSPException;
}
