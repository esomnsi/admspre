/**
 * -------------------------------------------------------------------------------------------------------
 * Copyright (C) 2012 Ericsson India Global Pvt Limited
 * All Rights Reserved
 * Project         		    :  IT_MSSP
 * Module				    :  com.ericsson.mssp.solution.controller
 * File name       		    :  StaffAugController.java
 * Description				:	<To Do>
 * Author, Date & Release	:	Dec 20, 20122012
 * Modification history		:
 * Number	|Date   	   |Author        |Remark
 * -----------------------------------------------------------------------------------------------------
 * 1      	| Dec 20, 2012  	   |eruvwyn   	  | Created.
 * -----------------------------------------------------------------------------------------------------
 **/

package com.ericsson.mssp.solution.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ericsson.mssp.common.dto.OpportunityDTO;
import com.ericsson.mssp.common.entity.JobRole;
import com.ericsson.mssp.common.exception.MSSPException;
import com.ericsson.mssp.common.util.ApplicationPropertiesUtil;
import com.ericsson.mssp.solution.forms.StaffAugForm;
import com.ericsson.mssp.solution.service.ISolutionService;

/**
 * @author eruvwyn
 * 
 */
@Controller
public class StaffAugController extends BaseController {
    @Autowired
    private ISolutionService solutionService;
    private final Log log = LogFactory.getLog(StaffAugController.class);

    String message = "";

    @RequestMapping(value = "/solution/staffAugmentation")
    public String getstaffAugmentation(Model model, HttpSession session) {
	// int solutionID = 21;
	/*
	 * List<StaffAugDTO> staffAugDTOList = null; int solutionID =
	 * getSessionSolutionId(session);
	 */
	String mes = "";
	StaffAugForm staffAugForm = new StaffAugForm();
	try {
	    if (getSessionSolutionId(session) != null) {
		int solutionID = getSessionSolutionId(session);
		staffAugForm = solutionService
			.getStaffAugWithSolutionID(solutionID);

		// staffAugForm.setListJobRole(listJobRole);
	    } else
		log.info(" :::getSessionSolutionId(session) is NULL:::");

	} catch (MSSPException e) {
	    log.info(
		    "::: " + e.getMessage() + " :::: " + e.getCause() + " ...",
		    new MSSPException("Exception in getstaffAugmentation "));
	}

	// staffAugForm.setStaffAugDTO(staffAugDTOList);
	model.addAttribute("staffAugForm", staffAugForm);
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
			getSessionOpportunityId(session),
			getSessionSolutionId(session));
	mes = message;
	message = "";
	model.addAttribute("message", mes);
	model.addAttribute("serviceBucketData", serviceBucketData);
	return "staffAugmentation";

    }

    @ModelAttribute("jobroleList")
    public Map<Integer, String> getAllJobRole() {
	List<JobRole> listJobRole = new ArrayList<JobRole>();
	listJobRole = solutionService.getAllJobRole();
	Map<Integer, String> mapJobRole = new HashMap<Integer, String>();
	for (JobRole jobRole : listJobRole) {
	    mapJobRole.put(jobRole.getJobRoleId(), jobRole.getRole());

	}
	return mapJobRole;
    }

    @RequestMapping(value = "/solution/saveStaffAug", method = RequestMethod.POST)
    public String saveStaffAug(
	    @ModelAttribute("staffAugForm") StaffAugForm staffAugForm,
	    HttpSession session, ModelMap model) {
	// int solutionID = 21;
	try {
	    if (getSessionSolutionId(session) != null) {
		int solutionID = getSessionSolutionId(session);
		solutionService.saveStaffAug(staffAugForm, solutionID);
		message = ApplicationPropertiesUtil
			.getProperty("msg.common.datasave.success");
	    } else
		log.info(" :::getSessionSolutionId(session) is NULL:::");
	} catch (Exception e) {
	    log.info(
		    "::: " + e.getMessage() + " :::: " + e.getCause() + " ...",
		    new MSSPException("Exception in saveStaffAug"));
	}
	// check if user has edit access
	String hasEditSolAccess = (String) getSessionValueFromKey(session,
		HAS_EDIT_SOL_ACCESS);
	model.addAttribute("hasEditSolAccess", hasEditSolAccess);
	return "redirect:/solution/staffAugmentation";
    }

    @RequestMapping(value = "/solution/saveNextStaffAug", method = RequestMethod.POST)
    public String saveNextStaffAug(
	    @ModelAttribute("staffAugForm") StaffAugForm staffAugForm,
	    HttpSession session, ModelMap model) {
	// int solutionID = 21;
	try {
	    if (getSessionSolutionId(session) != null) {
		int solutionID = getSessionSolutionId(session);
		solutionService.saveStaffAug(staffAugForm, solutionID);
	    } else
		log.info(" :::getSessionSolutionId(session) is NULL:::");
	} catch (Exception e) {
	    log.info(
		    "::: " + e.getMessage() + " :::: " + e.getCause() + " ...",
		    new MSSPException("Exception in saveStaffAug"));
	}

	return "redirect:/solution/reviewFTE";
    }

    @RequestMapping(value = "/solution/createStaffingPlan")
    public String getStaffingPlan(Model model, HttpSession session) {

	String mes = "";
	// int solutionID = 41;
	/*
	 * List<StaffAugDTO> staffAugDTOList = null; int solutionID =
	 * getSessionSolutionId(session);
	 */
	StaffAugForm staffAugForm = new StaffAugForm();
	try {
	    if (getSessionSolutionId(session) != null) {
		int solutionID = getSessionSolutionId(session);
		staffAugForm = solutionService
			.getStaffAugWithSolutionID(solutionID);

		// staffAugForm.setListJobRole(listJobRole);
	    } else
		log.info(" :::getSessionSolutionId(session) is NULL:::");

	} catch (MSSPException e) {
	    log.info(
		    "::: " + e.getMessage() + " :::: " + e.getCause() + " ...",
		    new MSSPException("Exception in getstaffAugmentation "));
	}
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
			getSessionOpportunityId(session),
			getSessionSolutionId(session));
	model.addAttribute("serviceBucketData", serviceBucketData);
	// staffAugForm.setStaffAugDTO(staffAugDTOList);
	mes = message;
	message = "";
	model.addAttribute("message", mes);
	model.addAttribute("staffAugForm", staffAugForm);
	// check if user has edit access
	String hasEditSolAccess = (String) getSessionValueFromKey(session,
		HAS_EDIT_SOL_ACCESS);
	model.addAttribute("hasEditSolAccess", hasEditSolAccess);
	return "createStaffingPlan";

	/*
	 * // StaffingPlanForm staffingPlanForm = new StaffingPlanForm(); try {
	 * // int solutionID = 1; if(getSessionSolutionId(session) != null){ int
	 * solutionID = getSessionSolutionId(session); staffingPlanForm =
	 * solutionService.getStaffingPlan(solutionID); } else
	 * log.info(" :::getSessionSolutionId(session) is NULL:::"); } catch
	 * (MSSPException e) { // TODO Auto-generated catch block log.info(
	 * "::: " + e.getMessage() + " :::: " + e.getCause() + " ...", new
	 * MSSPException("Exception in getStaffingPlan Method")); }
	 * 
	 * // staffAugForm.setStaffAugDTO(staffAugDTOList);
	 * model.addAttribute("staffingPlanForm", staffingPlanForm); return
	 * "createStaffingPlan";
	 */
    }

    @RequestMapping(value = "/solution/saveStaffingPlan", method = RequestMethod.POST)
    public String saveStaffingPlan(
	    // @ModelAttribute("staffingPlanForm") StaffingPlanForm
	    // staffingPlanForm ,HttpSession session) {

	    @ModelAttribute("staffAugForm") StaffAugForm staffAugForm,
	    HttpSession session) {

	// int solutionID = 41;
	try {
	    if (getSessionSolutionId(session) != null) {
		int solutionID = getSessionSolutionId(session);
		solutionService.saveStaffAug(staffAugForm, solutionID);
		message = ApplicationPropertiesUtil
			.getProperty("msg.common.datasave.success");
	    } else
		log.info(" :::getSessionSolutionId(session) is NULL:::");
	} catch (Exception e) {
	    log.info(
		    "::: " + e.getMessage() + " :::: " + e.getCause() + " ...",
		    new MSSPException("Exception in saveStaffAug"));
	}

	// return "createStaffingPlan";
	return "redirect:/solution/createStaffingPlan";

	/*
	 * try { // int solutionID = 1; if(getSessionSolutionId(session) !=
	 * null){ int solutionID = getSessionSolutionId(session); //
	 * solutionService.saveStaffAug(staffingPlanForm);
	 * solutionService.saveStaffingPlan(staffingPlanForm ,solutionID); }
	 * else log.info(" :::getSessionSolutionId(session) is NULL:::"); }
	 * catch (Exception e) { e.printStackTrace(); log.info( "::: " +
	 * e.getMessage() + " :::: " + e.getCause() + " ...", new
	 * MSSPException("Exception in saveStaffingPlan Method")); }
	 * 
	 * return "createStaffingPlan";
	 */
    }

    @RequestMapping(value = "/solution/saveNextStaffPlan", method = RequestMethod.POST)
    public String saveNextStaffPlan(
	    // @ModelAttribute("staffingPlanForm") StaffingPlanForm
	    // staffingPlanForm ,HttpSession session) {

	    @ModelAttribute("staffAugForm") StaffAugForm staffAugForm,
	    HttpSession session) {

	// int solutionID = 41;
	try {
	    if (getSessionSolutionId(session) != null) {
		int solutionID = getSessionSolutionId(session);
		solutionService.saveStaffAug(staffAugForm, solutionID);
	    } else
		log.info(" :::getSessionSolutionId(session) is NULL:::");
	} catch (Exception e) {
	    log.info(
		    "::: " + e.getMessage() + " :::: " + e.getCause() + " ...",
		    new MSSPException("Exception in saveStaffAug"));
	}

	// return "createStaffingPlan";
	return "redirect:/solution/labourCost";

	/*
	 * try { // int solutionID = 1; if(getSessionSolutionId(session) !=
	 * null){ int solutionID = getSessionSolutionId(session); //
	 * solutionService.saveStaffAug(staffingPlanForm);
	 * solutionService.saveStaffingPlan(staffingPlanForm ,solutionID); }
	 * else log.info(" :::getSessionSolutionId(session) is NULL:::"); }
	 * catch (Exception e) { e.printStackTrace(); log.info( "::: " +
	 * e.getMessage() + " :::: " + e.getCause() + " ...", new
	 * MSSPException("Exception in saveStaffingPlan Method")); }
	 * 
	 * return "createStaffingPlan";
	 */
    }

}
