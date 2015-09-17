/**
* -------------------------------------------------------------------------------------------------------
* Copyright (C) 2012 Ericsson India Global Pvt Limited
* All Rights Reserved
* Project         		    :  IT_MSSP
* Module				    :  com.ericsson.mssp.dao.impl
* File name       		    :  LoginDAOImpl.java
* Description				:	<To Do>
* Author, Date & Release	:	Dec 6, 20122012
* Modification history		:
* Number	|Date   	   |Author        |Remark
* -----------------------------------------------------------------------------------------------------
* 1      	| Dec 6, 2012  	   |egaivij   	  | Created.
* -----------------------------------------------------------------------------------------------------
**/
 
package com.ericsson.mssp.common.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ericsson.mssp.common.dao.ILoginDAO;
import com.ericsson.mssp.common.dto.UserDTO;
import com.ericsson.mssp.common.entity.ApplicationRole;
import com.ericsson.mssp.common.entity.User;

/**
 * @author egaivij
 *
 */

@Repository
public class LoginDAOImpl extends BaseDAOImpl implements ILoginDAO{

	@Transactional
	public List<UserDTO> getUser(String userName) {
		logger.info("inside get User Dao Impl : " + userName);
		String query = "from User where SignumId='"+userName+"'";
		List<User> user = null;
		user = getHibernateTemplate().find(query);
		List<UserDTO> userDTOList = new ArrayList<UserDTO>();
		for(int i=0;i<user.size();i++)
		{
			UserDTO userDTO = new UserDTO();
			userDTO.setApplicationRoles(user.get(i).getApplicationRoles());
			userDTO.setSignumId(user.get(i).getSignumId());
			userDTOList.add(userDTO);
		}
		logger.info("list size retrieved : " + userDTOList.size());
		return userDTOList;
	}

	@Override
	public List<ApplicationRole> getRoles() {
		return getObjects(ApplicationRole.class);
	}
}