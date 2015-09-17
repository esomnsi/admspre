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
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ericsson.mssp.common.constant.MSSPConstants;
import com.ericsson.mssp.common.dto.SolutionADRDTO;
import com.ericsson.mssp.common.exception.MSSPException;
import com.ericsson.mssp.common.util.ApplicationPropertiesUtil;
import com.ericsson.mssp.solution.forms.ManageADRForm;
import com.ericsson.mssp.solution.service.ISolutionService;

@Controller
public class ManageADRController implements MSSPConstants {
    public static Logger logger = Logger.getLogger(ManageADRController.class);

 
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
    @RequestMapping("/solution/manageADR")
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
	    return "manageADR";
	}

	// mode of operation
	String sessOpMode = (null != session.getAttribute(OPERATION_MODE)) ? session
		.getAttribute(OPERATION_MODE).toString() : null;
	if (null != sessOpMode && sessOpMode.length() == 1) {
	    opMode = sessOpMode.toUpperCase();
	}
	Map<String, List<SolutionADRDTO>> aDRsListMap = solutionService
		.getAllADRInADRListsMap(sArea, sType, sImpact, solutionID);

	// solutionService
	// .getAllADRInADRListsMapBySolId(solId);

	// solutionService .getAllADRInADRListsMap();
	List<SolutionADRDTO> assumpList = aDRsListMap.get(ADR_ASSUM_CATEGORY);
	// solutionService
	// .getSolutionAllAssumptions();
	List<SolutionADRDTO> dependList = aDRsListMap.get(ADR_DEPEND_CATEGORY);
	// solutionService
	// .getSolutionAllDependencies();
	List<SolutionADRDTO> riskList = aDRsListMap.get(ADR_RISK_CATEGORY);// solutionService.getSolutionAllRisks();
	// ManageADRForm manageADRForm = new ManageADRForm();
	manageADRForm.setAssumpList(assumpList);
	manageADRForm.setDependList(dependList);
	manageADRForm.setRiskList(riskList);
	// Operation mode to be set down here
	manageADRForm.setOpMode(opMode);
	manageADRForm.setSolutionID(""+solutionID);
	model.addAttribute("manageADRForm", manageADRForm);
	logger.info("opMode=" + opMode + " assumpList="
		+ (null == assumpList ? assumpList : assumpList.size())
		+ " dependList="
		+ (null == dependList ? dependList : dependList.size())
		+ " riskList="
		+ (null == riskList ? riskList : riskList.size()));
	// return form view
	return "manageADR";
    }

    /**
     * 
     * Description : Saving ADR Method Name : saveADR Input& Output:
     * 
     * @param mngADRForm
     * @param aArea
     * @param aStatement
     * @param aWeightage
     * @param aType
     * @param aImpact
     * @param aSolutionAdrid
     * @param action
     * @param session
     * @return String
     * @throws MSSPException
     */
    @RequestMapping(value = { "/solution/saveADR" }, method = RequestMethod.POST)
    public @ResponseBody
    String saveADR(@ModelAttribute("manageADRForm") ManageADRForm mngADRForm,
	    @RequestParam(value = "aArea") String[] aArea,
	    @RequestParam(value = "aStatement") String[] aStatement,
	    @RequestParam(value = "aWeightage") String[] aWeightage,
	    @RequestParam(value = "aType") String[] aType,
	    @RequestParam(value = "aImpact") String[] aImpact,
	    @RequestParam(value = "aSolutionAdrid") String[] aSolutionAdrid,
	    @RequestParam(value = "act") String action, HttpSession session)
	    throws MSSPException {
	if (aWeightage.length < 1) {
	    aWeightage = new String[] { "" };
	}
	if (aStatement.length < 1) {
	    aStatement = new String[] { "" };
	}
	if (aSolutionAdrid.length < 1) {
	    aSolutionAdrid = new String[] { "" };
	}

	for (int count = 0; count < aArea.length; count++) {

	    aWeightage[count] = (aWeightage[count] == null || aWeightage[count]
		    .equals("")) ? "0" : aWeightage[count];
	    logger.info(" aStatement=" + aStatement[count].trim() + " aArea="
		    + aArea[count] + " aType=" + aType[count] + " aImpact="
		    + aImpact[count] + " aWeightage=" + aWeightage[count]
		    + " aSolutionAdrid=" + aSolutionAdrid[count] + " Act="
		    + action);

	    SolutionADRDTO solutionADRDTO = new SolutionADRDTO();
	    solutionADRDTO.setAdrStatement(aStatement[count]);
	    solutionADRDTO.setAdrArea(aArea[count]);
	    solutionADRDTO.setAdrType(aType[count]);
	    solutionADRDTO.setAdrImpact(Integer.parseInt(aImpact[count]));
	    try {
		solutionADRDTO
			.setAdrWeightage(Float.valueOf(aWeightage[count]));
	    } catch (NumberFormatException nfe) {
		solutionADRDTO.setAdrWeightage(0f);
		logger.info("Number format is not correct:" + aWeightage[count]);
	    }

	    solutionADRDTO
		    .setAdrCategory(action.equals("aForm") ? ADR_ASSUM_CATEGORY
			    : action.equals("dForm") ? ADR_DEPEND_CATEGORY
				    : ADR_RISK_CATEGORY);
	    if (!aSolutionAdrid[count].equals("")
		    && aSolutionAdrid[count] != null) {
		solutionADRDTO.setSolutionAdrid(Integer
			.valueOf(aSolutionAdrid[count]));
	    }
	    String sessSolId = (null != session.getAttribute(SOLUTION_ID)) ? session
		    .getAttribute(SOLUTION_ID).toString() : null;
	    if (null != sessSolId) {
		try {
		    solutionID = Integer.valueOf(sessSolId);
		} catch (NumberFormatException nfe) {
		    logger.info("Session solution ID not found for key:"
			    + SOLUTION_ID + " Returned value:" + sessSolId);
		}
	    } else {
		return "Solution ID not found so action rejected by the system";
	    }
	    solutionADRDTO.setSolutionId(solutionID);
	    if (null == aStatement[count]
		    || aStatement[count].trim().equals("")) {
		if (null != aSolutionAdrid[count]
			&& !aSolutionAdrid[count].equals("")) {
		    // delete code here....
		    solutionService.deleteSolutionADR(solutionADRDTO);
		}

	    } else {
		solutionService.saveSolutionADR(solutionADRDTO);
	    }
	}

	return "";
    }

    /**
     * 
     * Description : areaList drop down values Method Name : populateAreaList
     * Input& Output:
     * 
     * @return Map<String, String>
     */
    @ModelAttribute("areaList")
    public Map<String, String> populateAreaList() {
	Map<String, String> area = ApplicationPropertiesUtil
		.getMapConfigKeyValue(ADR_AREAS);// solutionService.getAllAreas();
	return area;
    }

    /**
     * 
     * Description : Type list drop down values Method Name : populateTypeList
     * Input& Output:
     * 
     * @return Map<String, String>
     */
    @ModelAttribute("typeList")
    public Map<String, String> populateTypeList() {
	Map<String, String> area = ApplicationPropertiesUtil
		.getMapConfigKeyValue(ADR_TYPES);
	return area;
    }

    /**
     * 
     * Description : Impact list drop down values Method Name :
     * populateImpactList Input& Output:
     * 
     * @return Map<String, String>
     */
    @ModelAttribute("impactList")
    public Map<String, String> populateImpactList() {
	Map<String, String> complexities = ApplicationPropertiesUtil
		.getMapConfigKeyValue(ADR_COMPLEXITIES, true);
	return complexities;
    }

}
