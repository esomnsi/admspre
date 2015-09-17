package com.ericsson.mssp.login.service;

import java.util.List;

import com.ericsson.mssp.common.dto.LoginDTO;
import com.ericsson.mssp.common.dto.RoleDTO;
import com.ericsson.mssp.common.dto.UserDTO;

public interface LoginService {

	public String authenticateUser(LoginDTO loginDTO);
	public List<UserDTO> getUser(String userName);
	public List<RoleDTO> getRoles();
}
