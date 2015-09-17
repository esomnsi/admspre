/**
* -------------------------------------------------------------------------------------------------------
* Copyright (C) 2012 Ericsson India Global Pvt Limited
* All Rights Reserved
* Project         		    :  IT_MSSP
* Module				    :  com.ericsson.mssp.myactionapproval.controller
* File name       		    :  InitiateApprovalProcess.java
* Description				:	<To Do>
* Author, Date & Release	:	20-Feb-20132013
* Modification history		:
* Number	|Date   	   |Author        |Remark
* -----------------------------------------------------------------------------------------------------
* 1      	| 20-Feb-2013  	   |eshtgar   	  | Created.
* -----------------------------------------------------------------------------------------------------
**/
 
package com.ericsson.mssp.myactionapproval.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ericsson.mssp.approval.service.IApprovalService;
import com.ericsson.mssp.common.constant.MSSPConstants;
import com.ericsson.mssp.common.dto.InitiateApprovalProcessDTO;
import com.ericsson.mssp.common.dto.UserAccessDTO;
import com.ericsson.mssp.common.exception.MSSPException;
import com.ericsson.mssp.myactionapproval.service.IInitiateApprovalService;
import com.ericsson.mssp.solution.controller.BaseController;

/**
 * @author eshtgar
 *
 */

@Controller
public class InitiateApprovalProcess extends BaseController implements MSSPConstants{
	
	
	@Autowired
	private IInitiateApprovalService initiateApprovalProcess;
	@Autowired
	private IApprovalService approvalService;
	
	private final Log log = LogFactory.getLog(InitiateApprovalProcess.class);

	@RequestMapping(value="/action/initiateApproval")
	public String initPage(Model model,@ModelAttribute("initiateApprovalProcessDTO") InitiateApprovalProcessDTO initiateApprovalProcessDTO, HttpSession session){
		
	   try {
	    	log.info("Initiating Approval process");	 	    	
	    	String selectedRole = (String)session.getAttribute("ROLE");
	       	if(selectedRole.equalsIgnoreCase("ROLE_APPROVER")){ 
	    		//Both super user and approver can perform approval action.
	    		model.addAttribute("mode", MSSPConstants.LOGGED_IN_MODE_APPRROVER);
	    	}else if(selectedRole.equalsIgnoreCase("ROLE_SOLUTION_MANAGER")){
	    		model.addAttribute("mode", MSSPConstants.LOGGED_IN_MODE_SOL_MANAGER);
	    	}else if(selectedRole.equalsIgnoreCase("ROLE_SUPER_USER")){
	    		model.addAttribute("mode", MSSPConstants.LOGGED_IN_MODE_SUPER_USER);
	    	}
	    	String loggedInUser=getSessionValueFromKey(session, USER_NAME)!=null?getSessionValueFromKey(session, USER_NAME).toString():null;
	    	List<InitiateApprovalProcessDTO>searchResult=initiateApprovalProcess.inbox(loggedInUser,selectedRole);
	    	model.addAttribute("searchResult", searchResult);
	    	log.debug("Fetching information for approval process..");
	    	
	    	

		} catch (Exception e) {
			log.info("::: " + e.getMessage() + " :::: " + e.getCause() + " ...");
		}
		return "initiateApproval";
	}
	
	@RequestMapping(value="/action/submitApprovalData")
	public String submitApprovalData(Model model,@RequestParam("oppId") String oppId,@RequestParam("solId") String solId,HttpSession session){
		String returnString = null;
		System.out.println("Submitting........."+oppId + " "+ solId);
		logger.debug("Opportunity ID:="+oppId + " Id:=Solution"+ solId);
		setSessionOpportunityId(session, Integer.parseInt(oppId));
		if(!solId.equals("0"))
			setSessionSolutionId(session, Integer.parseInt(solId));
		try {
			returnString = "redirect:../opportunity/defineScope";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		return returnString;
	}
}
