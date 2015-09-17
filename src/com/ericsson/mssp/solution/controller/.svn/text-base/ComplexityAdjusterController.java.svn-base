/**
 * -------------------------------------------------------------------------------------------------------
 * Copyright (C) 2012 Ericsson India Global Pvt Limited
 * All Rights Reserved
 * Project         		    :  IT_MSSP
 * Module				    :  com.ericsson.mssp.solution.controller
 * File name       		    :  ComplexityAdjusterController.java
 * Description				:	<To Do>
 * Author, Date & Release	:	Dec 11, 20122012
 * Modification history		:
 * Number	|Date   	   |Author        |Remark
 * -----------------------------------------------------------------------------------------------------
 * 1      	| Dec 11, 2012  	   |ekoukap   	  | Created.
 * -----------------------------------------------------------------------------------------------------
 **/

package com.ericsson.mssp.solution.controller;

import java.util.ArrayList;
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

import com.ericsson.mssp.common.dto.OpportunityDTO;
import com.ericsson.mssp.common.dto.ServiceScopeDTO;
import com.ericsson.mssp.common.entity.Apacomplexity;
import com.ericsson.mssp.common.entity.SolutionComplexity;
import com.ericsson.mssp.common.exception.MSSPException;
import com.ericsson.mssp.common.util.ApplicationPropertiesUtil;
import com.ericsson.mssp.solution.forms.ComplexityAdjusterForm;
import com.ericsson.mssp.solution.service.IComplexityAdjuster;
import com.ericsson.mssp.solution.service.ISolutionService;

/**
 * @author ekoukap 
 * 
 */
@Controller
public class ComplexityAdjusterController extends BaseController{
	
	@Autowired
	//@Qualifier("complexityAdjusterServiceImpl")
	private IComplexityAdjuster complexityAdjusterService;
	
	@Autowired
	private ISolutionService ISolutionService;
	
	public static Logger logger = Logger.getLogger(ComplexityAdjusterController.class);
	
	public String nextPage(){
		return "";
	}
	
	@RequestMapping(value = "/solution/complexityAdjuster")
	public String loadComplexityAdjuster(ModelMap model,@ModelAttribute("complexityAdjusterForm") ComplexityAdjusterForm complexityAdjusterForm,HttpSession session) throws MSSPException {
		try
		{	
			String hasEditAccess = (String) getSessionValueFromKey(session, HAS_EDIT_SOL_ACCESS);
			Integer oppId = getSessionOpportunityId(session);
			Integer solutionId = getSessionSolutionId(session);
			if(oppId != null && solutionId != null) {
				OpportunityDTO opportunityDTO= ISolutionService.getOpportunity(oppId);
				List<ServiceScopeDTO> listOfServiceScope = ISolutionService.loadAllSelectedServiceScopes(oppId);
				SolutionComplexity solutionComplexity = new SolutionComplexity();
				List<Apacomplexity> apacomplexityList = new ArrayList<Apacomplexity>();
				complexityAdjusterForm = complexityAdjusterService.loadComplexityAdjusterForm(solutionComplexity, solutionId, apacomplexityList, listOfServiceScope,oppId);
				if(listOfServiceScope != null && listOfServiceScope.size() != 0){
					if(complexityAdjusterForm.getApacomplexityList() != null && complexityAdjusterForm.getApacomplexityList().size() != 0){
						if(listOfServiceScope.size() != complexityAdjusterForm.getApacomplexityList().size()){
							model.addAttribute("messageError",
									"Please check for Application Portfolio Analysis,"
											+ "APA  data not found for All Selected Service Scope!");
						}
					}else{
						model.addAttribute("messageError",
								"Please check for Application Portfolio Analysis,"
										+ "APA  data not found!");
					}
				}else{
					model.addAttribute("messageError",
							"Please check for Define Scope,"
									+ "Selected Scope data not found!</font>");
				}
				List<String> serviceBucketData = ISolutionService.loadServiceBucketDataByOppSolutionID(oppId, solutionId);
				model.addAttribute("serviceBucketData", serviceBucketData);
				model.addAttribute("opportunityDTO",opportunityDTO);
				model.addAttribute("listOfServiceScope", listOfServiceScope);
				model.addAttribute("complexityAdjusterForm", complexityAdjusterForm);
				model.addAttribute("hasEditSolAccess", hasEditAccess);
			}
		} catch (MSSPException e) {
			logger.error(e.getMessage() + " |  " + e.getCause());
		    throw new MSSPException(e.getMessage() + " |  " + e.getCause());
		}
		return "complexityAdjuster";
	}
	
	//@AuthorizeUserPermissionMarker(permissions = { ApplicationConstant.ROLE_ADMIN })
	@RequestMapping(value = "/solution/savePageComplexityAdjuster", method = RequestMethod.POST)
	public String savePage(Model model,@ModelAttribute("complexityAdjusterForm") ComplexityAdjusterForm complexityAdjusterForm, HttpSession session)throws MSSPException,Exception{
		try
		{
			Integer SolutionId = getSessionSolutionId(session);
			Integer opportunityId = getSessionOpportunityId(session);
			if(opportunityId != null && SolutionId != null) {
			SolutionComplexity solutionComplexity = new SolutionComplexity();
			solutionComplexity = complexityAdjusterForm.getSolutionComplexity();
			List<Apacomplexity> apacomplexityList = complexityAdjusterForm.getApacomplexityList();
			complexityAdjusterService.saveComplexityAdjuster(solutionComplexity, SolutionId, apacomplexityList, opportunityId);
			model.addAttribute("message", ApplicationPropertiesUtil.getProperty("msg.common.datasave.success"));
			}
		}catch (MSSPException e) {
			e.printStackTrace();
			logger.error(e.getMessage() + " |  " + e.getCause());
			model.addAttribute("messageError", ApplicationPropertiesUtil.getProperty("msg.common.datasave.error"));
		    //throw new MSSPException(e.getMessage() + " |  " + e.getCause());
		}
		return "forward:./complexityAdjuster";
	}
	
	/**
     * 
     * Description : Impact list drop down values Method Name :
     * populateImpactList Input& Output:
     * 
     * @return Map<String, String>
     */
    @ModelAttribute("complexityList")
    public Map<String, String> populateImpactList() {
	Map<String, String> complexities = ApplicationPropertiesUtil
		.getMapConfigKeyValue(SOLUTION_COMPLEXITY, true);
	return complexities;
    }
    
    @RequestMapping(value="/solution/saveComplexityAdjusterNext", method = RequestMethod.POST)
	public String savePageAndNext(ModelMap model,@ModelAttribute("complexityAdjusterForm") ComplexityAdjusterForm complexityAdjusterForm,HttpSession session)throws MSSPException,Exception{
    	try
		{
    		Integer SolutionId = getSessionSolutionId(session);
			Integer opportunityId = getSessionOpportunityId(session);
			if(opportunityId != null && SolutionId != null) {
			SolutionComplexity solutionComplexity = new SolutionComplexity();
			solutionComplexity = complexityAdjusterForm.getSolutionComplexity();
			List<Apacomplexity> apacomplexityList = complexityAdjusterForm.getApacomplexityList();
			complexityAdjusterService.saveComplexityAdjuster(solutionComplexity, SolutionId, apacomplexityList, opportunityId);
			}
		}catch (MSSPException e) {
			logger.error(e.getMessage() + " |  " + e.getCause());
			model.addAttribute("message", ApplicationPropertiesUtil.getProperty("msg.common.datasave.error"));
		    throw new MSSPException(e.getMessage() + " |  " + e.getCause());
		}
		return "redirect:../solution/productivityLevers";
	}
}
