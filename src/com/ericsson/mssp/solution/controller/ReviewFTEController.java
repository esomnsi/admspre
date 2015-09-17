/**
 * -------------------------------------------------------------------------------------------------------
 * Copyright (C) 2012 Ericsson India Global Pvt Limited
 * All Rights Reserved
 * Project         		    :  IT_MSSP
 * Module				    :  com.ericsson.mssp.solution.controller
 * File name       		    :  ReviewFTEController.java
 * Description				:	This will be controller for Review FTE page view interactions
 * Author, Date & Release	:	Dec 21, 20122012
 * Modification history		:
 * Number	|Date   	   |Author        |Remark
 * -----------------------------------------------------------------------------------------------------
 * 1      	| Dec 21, 2012  	   |edassri   	  | Created.
 * -----------------------------------------------------------------------------------------------------
 **/

package com.ericsson.mssp.solution.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ericsson.mssp.common.constant.MSSPConstants;
import com.ericsson.mssp.common.dto.JobStageDTO;
import com.ericsson.mssp.common.dto.OpportunityDTO;
import com.ericsson.mssp.common.dto.SolutionDTO;
import com.ericsson.mssp.common.entity.AdditionalFTE;
import com.ericsson.mssp.common.entity.JobRoleStages;
import com.ericsson.mssp.common.entity.OpportunityDetail;
import com.ericsson.mssp.common.entity.ServiceElement;
import com.ericsson.mssp.common.entity.Solution;
import com.ericsson.mssp.common.exception.MSSPException;
import com.ericsson.mssp.common.util.ApplicationPropertiesUtil;
import com.ericsson.mssp.solution.forms.DefineScopeForm;
import com.ericsson.mssp.solution.forms.ReviewFTEForm;
import com.ericsson.mssp.solution.service.ISolutionFTEService;
import com.ericsson.mssp.solution.service.ISolutionService;

/**
 * @author edassri
 * 
 */
@Controller
public class ReviewFTEController extends BaseController implements
	MSSPConstants {

    private static final Logger logger = Logger
	    .getLogger(ReviewFTEController.class);
    private Integer solutionID = 1;
    private String opMode = "E";// Operation mode E/V
    @Autowired
    private ISolutionFTEService solutionFTEService;

    @Autowired
    private ISolutionService solutionService;

    @RequestMapping("/solution/reviewFTE")
    public String initForm(
	    ModelMap model,
	    @ModelAttribute("reviewFTEForm") ReviewFTEForm reviewFTEForm,
	    @RequestParam(value = "sServiceScope", required = false, defaultValue = "-1") Integer sServiceScope,
	    HttpSession session) throws MSSPException {
	System.out.println("sServiceScope=" + sServiceScope);
	logger.info("Reached........session:" + session + " sServiceScope="
		+ sServiceScope);
	Integer sessSolId = null;
	// Drop down service scope values
	Map<Integer, String> sScope = null;
	Integer opportunityID = null;
	try {
	    sessSolId = (null != session.getAttribute(SOLUTION_ID)) ? (Integer) session
		    .getAttribute(SOLUTION_ID) : null;

	    if (null != sessSolId) {
		solutionID = sessSolId;
	    } else {
		reviewFTEForm.setOpMode("V");
		reviewFTEForm.setSolutionID(null);
		model.addAttribute("reviewFTEForm", reviewFTEForm);
		logger.info("Session solution ID not found so in view mode");
		return "reviewFTE";
	    }

	    Solution solutionObject = solutionService.getSolutionById(solutionID);
	    reviewFTEForm.setSolutionJobRoleModel(solutionObject.getJobRoleModel());
	    
		if(sServiceScope != -1){
		    Integer serviceElementId = 0;

		    if(MSSPConstants.CCM_JOB_ROLE_MODEL.toLowerCase().equals(reviewFTEForm.getSolutionJobRoleModel().toLowerCase())){
			    serviceElementId = (solutionFTEService.getServiceElementIdByOppScopeId(sServiceScope)).getServiceElementID();
		    }else{
		    	serviceElementId = MSSPConstants.SERVICE_ELEMENT_FOR_CCM_JOB_ROLES;
		    }
	
			reviewFTEForm.setDropDownJobRoles(solutionFTEService
					.getJobRoleDropdownByServiceElementId(serviceElementId));
		}else{
			int ccmFlag = MSSPConstants.JOB_ROLE_NON_CCM_FLAG;
			if(MSSPConstants.CCM_JOB_ROLE_MODEL.toLowerCase().equals(reviewFTEForm.getSolutionJobRoleModel().toLowerCase())){
				ccmFlag = MSSPConstants.JOB_ROLE_CCM_FLAG;
			}
			reviewFTEForm.setDropDownJobRoles(solutionFTEService.getJobRolesDropdown(ccmFlag));
	    }
		
	    // reviewFTEForm.setMonthYears("");
	    model.addAttribute("reviewFTEForm", reviewFTEForm);
	    Integer sessOppId = (null != session.getAttribute(OPPORTUNITY_ID)) ? (Integer) session
		    .getAttribute(OPPORTUNITY_ID) : null;
	    if (null != sessOppId) {
		opportunityID = sessOppId;
	    }
	    sScope = (null == opportunityID) ? solutionFTEService
		    .getAllServiceScopeInMapBySolId(solutionID)
		    : solutionFTEService
			    .getAllServiceScopeInMapByOppID(opportunityID);
	    reviewFTEForm.setDropDownSS(sScope);
	    // Drop downs end here
	    // mode of operation
	    String sessOpMode = (null != session.getAttribute(OPERATION_MODE)) ? session
		    .getAttribute(OPERATION_MODE).toString() : null;
	    logger.info("solutionID=" + solutionID + " sessOpMode ="
		    + sessOpMode);
	    if (null != sessOpMode && sessOpMode.length() == 1) {
		opMode = sessOpMode.toUpperCase();
	    }
	    reviewFTEForm.setOpMode(opMode);
	    reviewFTEForm.setSolutionID(solutionID);
	    logger.debug("Session solution ID found for key:"
		    + SOLUTION_ID
		    + " Returned value:"
		    + sessSolId
		    + " solutionID="
		    + solutionID
		    + " Month Years="
		    + solutionFTEService
			    .getOpportunityTransStartSteadyEndMonthYearsString(solutionID));
	    // Reset Additional FTE old values
	    reviewFTEForm.setAdditionalFteid(-1);
	    reviewFTEForm.setaFTE(0d);
	    reviewFTEForm.setJobrole("-1");
	    reviewFTEForm.setSite("");
	    reviewFTEForm.setfDate("");
	    reviewFTEForm.settDate("");
	    reviewFTEForm
		    .setMonthYears(solutionFTEService
			    .getOpportunityTransStartSteadyEndMonthYearsString(solutionID));
	    reviewFTEForm.setsServiceScope(sServiceScope);

	    if (sServiceScope >= 0) {
		// Set additional FTE values
		reviewFTEForm = solutionFTEService
			.getAddionalFTE(reviewFTEForm);
	    }
	    // Data picking for report view based on applay solution lever
	    // reset old values
	    reviewFTEForm.setNonEmptyRoles("");
	    reviewFTEForm.setOnShoreDataList(null);
	    reviewFTEForm.setOffShoreDataList(null);
	    reviewFTEForm.setNearShoreDataList(null);
	    reviewFTEForm.setOnShoreLocalDataList(null);
	    reviewFTEForm.setOnShoreGsciDataList(null);
	    reviewFTEForm.setOnShore3ppDataList(null);
	    reviewFTEForm = solutionFTEService.loadFTETableDisplayValues(
		    reviewFTEForm, false);
	    logger.debug("Service scopes-" + reviewFTEForm.getNonEmptyRoles());

	    // reviewFTEForm.setMonthYears("");
	    model.addAttribute("reviewFTEForm", reviewFTEForm);
	    // Opportunity display data
	    OpportunityDTO opportunityDTO = new OpportunityDTO();
	    if (opportunityID != null) {
		opportunityDTO = solutionService.getOpportunity(opportunityID);
	    }
	    model.addAttribute("opportunityDTO", opportunityDTO);
	    // check if user has edit access
	    String hasEditSolAccess = (String) getSessionValueFromKey(session,
		    HAS_EDIT_SOL_ACCESS);
	    model.addAttribute("hasEditSolAccess", hasEditSolAccess);
	    // Service Bucket table data
	    List<String> serviceBucketData = solutionService
		    .loadServiceBucketDataByOppSolutionID(opportunityID,
			    solutionID);
	    model.addAttribute("serviceBucketData", serviceBucketData);
	} catch (Exception e) {
	    logger.error("Error while initForm user:"
		    + session.getAttribute(USER_NAME) + " Solution ID:"
		    + solutionID + " Error:" + e);
	}
	logger.debug("Done");
	// return form view
	return "reviewFTE";
    }

    @RequestMapping("/solution/saveAdditionalFTE")
    public @ResponseBody
    String saveAdditionalFTE(
	    ModelMap model,
	    @ModelAttribute("reviewFTEForm") ReviewFTEForm reviewFTEForm,
	    @RequestParam(value = "sServiceScope", required = false, defaultValue = "-1") Integer sServiceScope,
	    @RequestParam(value = "jobrole", required = false, defaultValue = "-1") Integer jobRoleId,
	    @RequestParam(value = "site", required = false, defaultValue = "NONE") String site,
	    @RequestParam(value = "aFTE", required = false, defaultValue = "-1.0") Double aFTE,
	    @RequestParam(value = "fDate", required = false, defaultValue = "NONE") String fDate,
	    @RequestParam(value = "tDate", required = false, defaultValue = "NONE") String tDate,
	    @RequestParam(value = "additionalFteid", required = false, defaultValue = "-1") Integer additionalFteid,
	    @RequestParam(value = "action", required = false, defaultValue = "NONE") String action,
	    HttpSession session) throws MSSPException {
	logger.debug("FTE save method:sServiceScope=" + sServiceScope
		+ " fDate=" + fDate + " action=" + action);
	// System.out.println("additionalFteid="+additionalFteid);
	// float aFTE = Float.parseFloat(adFTE);
	String returnString = "";
	Integer addFTEId = null;
	try {
	    //
	    // String formattedDate = sdf.format(date);
	    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	    Integer sessSolId = (null != session.getAttribute(SOLUTION_ID)) ? (Integer) session
		    .getAttribute(SOLUTION_ID) : null;

	    if (null != sessSolId) {
		solutionID = sessSolId;
	    }
	    OpportunityDetail oppDetail = solutionFTEService
		    .getOpportunityBySolId(solutionID).getOpportunityDetail();
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(oppDetail.getSteadyStateStartDate());
	    Date startDate = df.parse("01/" + (cal.get(Calendar.MONTH) + 1)
		    + "/" + cal.get(Calendar.YEAR));
	    cal.setTime(oppDetail.getSteadyStateEndDate());
	    Date endDate = df.parse(cal.getActualMaximum(Calendar.DAY_OF_MONTH)
		    + "/" + (cal.get(Calendar.MONTH) + 1) + "/"
		    + cal.get(Calendar.YEAR));
	    System.out
		    .println("startDate=" + startDate + " endDate:" + endDate);
	    if ((!fDate.equals("") && !tDate.equals("")
		    && !fDate.equals("NONE") && !tDate.equals("NONE"))
		    && (!startDate.equals(df.parse(fDate))
			    && !startDate.before(df.parse(fDate)) || (!endDate
			    .after(df.parse(tDate)) && !endDate.equals(df
			    .parse(tDate))))) {
		logger.info("Not in timeline range solutionID:" + solutionID
			+ " additionalFte date from:" + fDate + " to:" + tDate
			+ " not in Opportunity timeline: Start:" + startDate
			+ " and end:" + endDate);
		return "-1";
	    }
	    if (sServiceScope >= 0 && jobRoleId >= 0 && aFTE >= 0
		    && !site.equals("NONE") && !fDate.equals("NONE")
		    && !tDate.equals("NONE")) {
		try {
		    AdditionalFTE additionalFTE = new AdditionalFTE();
		    if (null != additionalFteid && additionalFteid >= 0) {
			additionalFTE.setAdditionalFteid(additionalFteid);
		    }
		    // additionalFTEDTO.setCreatedTimestamp(date);
		    additionalFTE.setSolution(solutionFTEService
			    .getSolutionById(solutionID));
		    additionalFTE.setOpportunityScopeId(sServiceScope);
		    //additionalFTE.setJobRoleStageID(jobRoleId);
		    JobRoleStages jobRoleStage = new JobRoleStages();
		    jobRoleStage.setJobRoleStagesId(jobRoleId);
		    additionalFTE.setJobRoleStageID(jobRoleStage);
		    
		    if(LOCATION_ONSHORE_LOCAL.equals(site)){
		    	additionalFTE.setLocation(LOCATION_ONSHORE);
		    	additionalFTE.setSubLocation(SUBLOC_LOCAL);
		    } else if(LOCATION_ONSHORE_GSCI.equals(site)){
		    	additionalFTE.setLocation(LOCATION_ONSHORE);
		    	additionalFTE.setSubLocation(SUBLOC_GSCI);
		    } else if(LOCATION_ONSHORE_3PP.equals(site)){
		    	additionalFTE.setLocation(LOCATION_ONSHORE);
		    	additionalFTE.setSubLocation(SUBLOC_3PP);
		    } else{
		    	additionalFTE.setLocation(site);
		    }
		    
		    additionalFTE.setNoofFte(Math.round(aFTE.floatValue()
			    * DECIMAL_POINTS_NUM)
			    / DECIMAL_POINTS_NUM);
		    additionalFTE.setFromMonthYear(df.parse(fDate));
		    additionalFTE.setToMonthYear(df.parse(tDate));
		    solutionFTEService.updateIntoFTEStaging(additionalFTE,
			    false);
		    addFTEId = solutionFTEService
			    .saveAdditionalFTE(additionalFTE);
		    reviewFTEForm.setAdditionalFteid(addFTEId);
		    // Update it into FTEStaging table data for reflection
		    reviewFTEForm.setSolutionID(solutionID);
		    logger.debug("solutionID:" + solutionID + " addFTEId="
			    + addFTEId + " saveAdditionalFTE sServiceScope="
			    + sServiceScope + " fDate=" + fDate + " formatted:"
			    + df.parse(fDate) + " to:" + df.parse(tDate)
			    + " jobrole=" + jobRoleId);
		} catch (ParseException e) {
		    logger.error("user:" + session.getAttribute(USER_NAME)
			    + " Solution ID:" + solutionID
			    + "Date parsing exception : from Date=" + fDate
			    + " to Date:" + tDate);
		}
	    } else {
		if (null != additionalFteid && additionalFteid >= 0) {
		    AdditionalFTE additionalFTE = new AdditionalFTE();
		    additionalFTE.setAdditionalFteid(additionalFteid);
		    additionalFTE.setNoofFte(0f);
		    additionalFTE.setSolution(solutionFTEService
			    .getSolutionById(solutionID));
		    solutionFTEService
			    .updateIntoFTEStaging(additionalFTE, true);
		    solutionFTEService.deleteAdditionalFTE(additionalFTE);
		    addFTEId = -1;
		}
		logger.info("solutionID:"
			+ solutionID
			+ " additionalFteid:"
			+ additionalFteid
			+ " Additional FTE non of the variable value found to be saved in system if exist removed");
	    }

	} catch (Exception e) {
	    logger.error("Error while saving user:"
		    + session.getAttribute(USER_NAME) + " Solution ID:"
		    + solutionID + " Error:" + e);
	    // e.printStackTrace();
	}
	// reviewFTEForm.setaFTE(0f);
	// model.addAttribute("reviewFTEForm", reviewFTEForm);
	if (!action.equals("NONE")) {
	    returnString = "./" + action;
	} else {
	    returnString += null == addFTEId ? STATUS_FAILURE : addFTEId;
	}
	return returnString;
	// return returnString;
    }

    @RequestMapping("/solution/generateFTE")
    String generateFTE(
	    ModelMap model,
	    @ModelAttribute("reviewFTEForm") ReviewFTEForm reviewFTEForm,
	    @RequestParam(value = "sServiceScope", required = false, defaultValue = "-1") Integer sServiceScope,
	    HttpSession session) {
	Integer sessSolId = null;
	try {
	    sessSolId = (null != session.getAttribute(SOLUTION_ID)) ? (Integer) session
		    .getAttribute(SOLUTION_ID) : null;	    

	    if (null != sessSolId) {
		solutionID = sessSolId;
	    } else {
		reviewFTEForm.setOpMode("V");
		reviewFTEForm.setSolutionID(null);
		model.addAttribute("reviewFTEForm", reviewFTEForm);
		logger.info("Session solution ID not found so in view mode");
		return "forward:./reviewFTE";
	    }
	    
	    Solution solutionObject = solutionService.getSolutionById(solutionID);
	    reviewFTEForm.setSolutionJobRoleModel(solutionObject.getJobRoleModel());
		if(sServiceScope != -1){
		    Integer serviceElementId = 0;

		    if(MSSPConstants.CCM_JOB_ROLE_MODEL.toLowerCase().equals(reviewFTEForm.getSolutionJobRoleModel().toLowerCase())){
			    serviceElementId = (solutionFTEService.getServiceElementIdByOppScopeId(sServiceScope)).getServiceElementID();
		    }else{
		    	serviceElementId = MSSPConstants.SERVICE_ELEMENT_FOR_CCM_JOB_ROLES;
		    }
	
			reviewFTEForm.setDropDownJobRoles(solutionFTEService
					.getJobRoleDropdownByServiceElementId(serviceElementId));
		}else{
			int ccmFlag = MSSPConstants.JOB_ROLE_NON_CCM_FLAG;
			if(MSSPConstants.CCM_JOB_ROLE_MODEL.toLowerCase().equals(reviewFTEForm.getSolutionJobRoleModel().toLowerCase())){
				ccmFlag = MSSPConstants.JOB_ROLE_CCM_FLAG;
			}
			reviewFTEForm.setDropDownJobRoles(solutionFTEService.getJobRolesDropdown(ccmFlag));
	    }
		
	    // Data picking for report view based on apply solution lever
	    // reset old values
	    reviewFTEForm.setSolutionID(solutionID);
	    reviewFTEForm
		    .setMonthYears(solutionFTEService
			    .getOpportunityTransStartSteadyEndMonthYearsString(solutionID));
	    reviewFTEForm.setsServiceScope(sServiceScope);
	    reviewFTEForm.setNonEmptyRoles("");
	    reviewFTEForm.setOnShoreDataList(null);
	    reviewFTEForm.setOffShoreDataList(null);
	    reviewFTEForm.setNearShoreDataList(null);
	    reviewFTEForm.setOnShoreLocalDataList(null);
	    reviewFTEForm.setOnShoreGsciDataList(null);
	    reviewFTEForm.setOnShore3ppDataList(null);
	    reviewFTEForm = solutionFTEService.loadFTETableDisplayValues(
		    reviewFTEForm, true);
	} catch (Exception e) {
	    logger.error("Error while generating review FTE user:"
		    + session.getAttribute(USER_NAME) + " Solution ID:"
		    + solutionID + " Error:" + e);
	}

	return "redirect:./reviewFTE";
    }
    
    @RequestMapping(value="/solution/jobStages", method = RequestMethod.POST)
	public @ResponseBody Map<Integer, String> getJobStages(
		    ModelMap model,
		    @ModelAttribute("reviewFTEForm") ReviewFTEForm reviewFTEForm, HttpSession session){
    	Integer sessSolId = null;
    	String jobRoleStageId = null;
    	Map<Integer, String> jobStagesMap = null;
    	
			try {
				sessSolId = (null != session.getAttribute(SOLUTION_ID)) ? (Integer) session
					    .getAttribute(SOLUTION_ID) : null;
				jobRoleStageId = reviewFTEForm.getJobrole();
				jobStagesMap = solutionFTEService.getJobStages(jobRoleStageId);
				
			} catch (Exception e) {
			    logger.error("Error while saving user:"
					    + session.getAttribute(USER_NAME) + " Solution ID:"
					    + solutionID + " Error:" + e);
				}
		
		 return jobStagesMap;
	}
}
