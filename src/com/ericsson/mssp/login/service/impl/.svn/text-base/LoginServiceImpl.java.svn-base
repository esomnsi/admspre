package com.ericsson.mssp.login.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ericsson.mssp.common.dao.ILoginDAO;
import com.ericsson.mssp.common.dto.LoginDTO;
import com.ericsson.mssp.common.dto.RoleDTO;
import com.ericsson.mssp.common.dto.UserDTO;
import com.ericsson.mssp.common.entity.ApplicationRole;
import com.ericsson.mssp.login.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService{

	public static Logger logger = Logger.getLogger(LoginServiceImpl.class);
	
	@Autowired
	ILoginDAO loginDAO;
	
	@Override
	public String authenticateUser(LoginDTO loginDTO) {
		logger.info("inside authenticate user login service impl");
		return null;
	}
	@Transactional
	public List<UserDTO> getUser(String userName) {
		logger.info("inside getUser : " + userName);
		List<UserDTO> userDTOList = new ArrayList<UserDTO>();
		userDTOList = loginDAO.getUser(userName);
		return userDTOList;
	}
	@Override
	public List<RoleDTO> getRoles() {
		List<ApplicationRole> rolList = loginDAO.getRoles();
		List<RoleDTO> roleDtoList = new ArrayList<RoleDTO>();
		RoleDTO rd = null;
		for(int counter = 0;counter<rolList.size();counter++)
		{
			 rd = new RoleDTO();
			rd.setApplicationRoleId(rolList.get(counter).getApplicationRoleId());
			rd.setDisplayName(rolList.get(counter).getDisplayName());
			rd.setRoleName(rolList.get(counter).getRoleName());
			roleDtoList.add(rd);
		}
		return roleDtoList;
	}

}
