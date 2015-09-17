/**
 * -------------------------------------------------------------------------------------------------------
 * Copyright (C) 2012 Ericsson India Global Pvt Limited
 * All Rights Reserved
 * Project         		    :  IT_MSSP
 * Module				    :  com.ericsson.mssp.solution.action
 * File name       		    :  ManageADRAction.java
 * Description				:	<To Do>
 * Author, Date & Release	:	Dec 7, 20122012
 * Modification history		:
 * Number	|Date   	   |Author        |Remark
 * -----------------------------------------------------------------------------------------------------
 * 1      	| Dec 7, 2012  	   |Sripati   	  | Created.
 * -----------------------------------------------------------------------------------------------------
 **/

package com.ericsson.mssp.solution.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ericsson.mssp.common.constant.MSSPConstants;
import com.ericsson.mssp.common.dto.OpportunityDTO;
import com.ericsson.mssp.common.exception.MSSPException;
import com.ericsson.mssp.solution.forms.ManageADRForm;
import com.ericsson.mssp.solution.service.ISolutionService;

@Controller
public class ManageADRGridController extends BaseController implements
	MSSPConstants {
    public static Logger logger = Logger
	    .getLogger(ManageADRGridController.class);

    Integer solutionID = 1;// Solution ID
    String opMode = "E";// Mode of Operation E/V

    @Autowired
    private ISolutionService solutionService;

    /**
     * 
     * Description : For initial page request and searches of ADR Method Name :
     * initForm Input& Output:
     * 
     * @param model
     * @param mngADRForm
     * @param sArea
     * @param sType
     * @param sImpact
     * @param session
     * @return String
     * @throws MSSPException
     */
    @RequestMapping("/solution/manageADRGrid")
    public String initForm(
	    ModelMap model,
	    @ModelAttribute("manageADRForm") ManageADRForm manageADRForm,
	    @RequestParam(value = "area", required = false, defaultValue = "NONE") String sArea,
	    @RequestParam(value = "type", required = false, defaultValue = "NONE") String sType,
	    @RequestParam(value = "impact", required = false, defaultValue = "NONE") String sImpact,
	    HttpSession session) throws MSSPException {

	String sessSolId = (null != session.getAttribute(SOLUTION_ID)) ? session
		.getAttribute(SOLUTION_ID).toString() : null;
	logger.info("sArea=" + sArea + " sType=" + sType + " sImpact="
		+ sImpact + " sessSolId=" + sessSolId);
	if (null != sessSolId) {
	    try {
		solutionID = Integer.valueOf(sessSolId);
	    } catch (NumberFormatException nfe) {
		logger.info("Session solution ID not found for key:"
			+ SOLUTION_ID + " Returned value:" + sessSolId);
	    }
	} else {
	    manageADRForm.setSolutionID(null);
	    model.addAttribute("manageADRForm", manageADRForm);
	    return "manageADRGrid";
	}

	// Operation mode to be set down here
	manageADRForm.setOpMode(opMode);
	manageADRForm.setSolutionID("" + solutionID);
	model.addAttribute("manageADRForm", manageADRForm);
	// Opportunity display data
	OpportunityDTO opportunityDTO = new OpportunityDTO();
	if (getSessionOpportunityId(session) != null) {
	    opportunityDTO = solutionService
		    .getOpportunity(getSessionOpportunityId(session));
	}
	model.addAttribute("opportunityDTO", opportunityDTO);
	// Service Bucket table data
	List<String> serviceBucketData = solutionService
		.loadServiceBucketDataByOppSolutionID(
			getSessionOpportunityId(session), solutionID);
	model.addAttribute("serviceBucketData", serviceBucketData);
	Object changeApproverList = getSessionValueFromKey(session,
		APPROVER_LIST_CHANGE);
	if (changeApproverList == (Object) true) {
	    session.removeAttribute(APPROVER_LIST_CHANGE);
	}
	// check if user has edit access
	String hasEditSolAccess = (String) getSessionValueFromKey(session,
		HAS_EDIT_SOL_ACCESS);
	model.addAttribute("hasEditSolAccess", hasEditSolAccess);
	// return form view
	return "manageADRGrid";
    }

}
