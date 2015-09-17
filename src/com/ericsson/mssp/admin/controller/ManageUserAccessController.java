/**
* -------------------------------------------------------------------------------------------------------
* Copyright (C) 2012 Ericsson India Global Pvt Limited
* All Rights Reserved
* Project         		    :  IT_MSSP
* Module				    :  com.ericsson.mssp.admin.controller
* File name       		    :  ManageUserAccessController.java
* Description				:	<To Do>
* Author, Date & Release	:	Jan 15, 20132013
* Modification history		:
* Number	|Date   	   |Author        |Remark
* -----------------------------------------------------------------------------------------------------
* 1      	| Jan 15, 2013  	   |eshtgar   	  | Created.
* -----------------------------------------------------------------------------------------------------
**/
 
package com.ericsson.mssp.admin.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ericsson.mssp.common.dto.UserAccessDTO;
import com.ericsson.mssp.common.dto.UserDTO;
import com.ericsson.mssp.common.exception.MSSPException;
import com.ericsson.mssp.login.service.IManageUserAccessService;
import com.ericsson.mssp.solution.controller.BaseController;

/**
 * @author eshtgar
 *
 */
@Controller
public class ManageUserAccessController extends BaseController{
	
	@Autowired
	private IManageUserAccessService manageUserAccessService;
	
	private final Log log = LogFactory.getLog(ManageUserAccessController.class);
	
	
	
	@RequestMapping(value="/admin/manageUserAccess")
	public String initPage(Model model, HttpSession session){
		
	   try {
	    	UserAccessDTO userAccessDTO = new UserAccessDTO();
	    	//userAccessDTO.setRoleList(roleList);
	    	/*List<UserAccessDTO> uad = manageUserAccessService.getUsersList();
	    	model.addAttribute("userList", uad);*/
	    	model.addAttribute("userAccessDTO",userAccessDTO);
	    	

		} catch (Exception e) {
			log.info("::: " + e.getMessage() + " :::: " + e.getCause() + " ...");
		}
		return "manageUserAccess";
	}
	
	
	@RequestMapping(value="/admin/searchUser", method = RequestMethod.POST)
	public String searchUser(Model model,@ModelAttribute("userAccessDTO") UserAccessDTO userAccessDTO, HttpSession session){
		
		try {
			List<String> roleList = manageUserAccessService.getRoles();
			List<UserAccessDTO>searchResult = manageUserAccessService.searchLDAPUsers(userAccessDTO,null);		
			model.addAttribute("roleList",roleList);
			model.addAttribute("searchResult", searchResult);
			/*List<UserAccessDTO> uad = manageUserAccessService.getUsersList();
	    	model.addAttribute("userList", uad);*/
		} catch (Exception	 e) {
			e.printStackTrace();
		}
		
		return "manageUserAccess";		
	}
	
	@RequestMapping(value="/admin/saveAssignedRole",method = RequestMethod.POST)
	public String saveAssignedRole(Model model, @RequestParam("roleData") String roleData){
		String returnString = null;
		try {
			boolean rolesAssigned = manageUserAccessService.saveAssignedRole(roleData);
			logger.info("Assgning roles return with: " + rolesAssigned);
			returnString = "redirect:/admin/manageUserAccess";
		} catch (MSSPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		return returnString;
	}
	
	@RequestMapping(value="/admin/getRoles",method = RequestMethod.GET)
	public @ResponseBody List<Integer> getRolesByUserSignum(Model model,@RequestParam("signum") String signum,HttpServletResponse response){
		String returnString = null;
		String message = "";
		List<Integer> rolesList = null;
		try {
			Map<Integer,String> rolesAssigned = manageUserAccessService.getRolesByUserSignum(signum);
			rolesList = new ArrayList<Integer>();
			UserAccessDTO userAccessDTO = new UserAccessDTO();			
			if(rolesAssigned!=null){
				rolesAssigned.values();
				rolesList.addAll(rolesAssigned.keySet());							
				message = "Roles found for user :" + signum;
			}else{
				message = "Error in retriving roles";
			}
			logger.info("Assgning roles return with: " + rolesAssigned);
			
			model.addAttribute("message", message);
			model.addAttribute("rolesList", rolesList);
			model.addAttribute("userAccessDTO",userAccessDTO);
			//returnString = "redirect:/admin/manageUserAccess";
		} catch (MSSPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		return rolesList;			
	}
	
	
	@RequestMapping(value="/admin/loadUserList",method = RequestMethod.POST)
	public String getUserList(Model model, HttpSession session){
		List<UserAccessDTO> uad = null;
		UserAccessDTO userAccessDTO = new UserAccessDTO();
		try {
			uad = manageUserAccessService.getUsersList();
		} catch (MSSPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	model.addAttribute("userList", uad);
    	model.addAttribute("userAccessDTO",userAccessDTO);
		
		return "userList";	
	}
}
