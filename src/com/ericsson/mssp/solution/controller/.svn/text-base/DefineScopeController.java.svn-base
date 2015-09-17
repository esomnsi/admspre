/**
* -------------------------------------------------------------------------------------------------------
* Copyright (C) 2012 Ericsson India Global Pvt Limited
* All Rights Reserved
* Project         		    :  IT_MSSP
* Module				    :  com.ericsson.mssp.solution.action
* File name       		    :  DefineScope.java
* Description				:	<To Do>
* Author, Date & Release	:	Dec 7, 20122012
* Modification history		:
* Number	|Date   	   |Author        |Remark
* -----------------------------------------------------------------------------------------------------
* 1      	| Dec 7, 2012  	   |egaivij   	  | Created.
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ericsson.mssp.common.constant.MSSPConstants;
import com.ericsson.mssp.common.dto.ComponentDTO;
import com.ericsson.mssp.common.dto.OpportunityComponentDTO;
import com.ericsson.mssp.common.dto.OpportunityDTO;
import com.ericsson.mssp.common.dto.OpportunityScopeDTO;
import com.ericsson.mssp.common.dto.ProductDetailsDTO;
import com.ericsson.mssp.common.dto.ServiceElementDTO;
import com.ericsson.mssp.common.dto.ServiceScopeDTO;
import com.ericsson.mssp.common.dto.SolutionDTO;
import com.ericsson.mssp.common.exception.MSSPException;
import com.ericsson.mssp.common.util.ApplicationPropertiesUtil;
import com.ericsson.mssp.solution.forms.DefineScopeForm;
import com.ericsson.mssp.solution.service.ISolutionService;

/**
 * @author egaivij
 *
 */
@Controller
public class DefineScopeController extends BaseController{
	
	@Autowired
	private ISolutionService solutionService;
	
	
	
	private final Log logger = LogFactory.getLog(DefineScopeController.class);
	
	private boolean saveNext = false;
		
	@RequestMapping(value="/opportunity/defineScope")
	public String initPage(Model model, HttpSession session, @RequestParam(value = "serviceType", required = false, defaultValue = "") String serviceType, @ModelAttribute("defineScopeForm") DefineScopeForm defineScopeForm)
	{
	   
	    try {
	    	
	    	Integer solId = getSessionSolutionId(session);
	    	Integer oppId = getSessionOpportunityId(session);
	    	//DefineScopeForm defineScopeForm = 	new DefineScopeForm();
	    	//String[]selServiceScope ;
	    	List<OpportunityScopeDTO> opportunityScopes = new ArrayList<OpportunityScopeDTO>();
	    	SolutionDTO solutionDTO = new SolutionDTO();
	    	OpportunityDTO opportunityDTO = new OpportunityDTO();
	    	
	    	List<ComponentDTO> componentsList = new ArrayList<ComponentDTO>();
	    	List<OpportunityComponentDTO> oppCompList = new ArrayList<OpportunityComponentDTO>();
	    	
	    	if(solId != null){
	    		//opportunityScopes = solutionService.getOpportunityScopes(oppId);
	    		solutionDTO = solutionService.getSolutionDetail(solId);
	    		model.addAttribute("createNew","true");
	    	}
	    	if(oppId != null){
	    		opportunityScopes = solutionService.getOpportunityScopes(oppId);
	    		opportunityDTO= solutionService.getOpportunity(oppId);
	    		
	    		if(!"".equalsIgnoreCase(serviceType)){
	    			opportunityDTO.getOpportunityDetailsDTO().setServiceType(serviceType);
	    			//solutionService.updateServiceType(opportunityDTO.getOpportunityDetailsDTO());
	    			
	    		}else if("".equalsIgnoreCase(serviceType) &&  opportunityDTO.getOpportunityDetailsDTO().getServiceType() == null ){
		    		serviceType = MSSPConstants.ServiceType_Product;
		    	}else if("".equalsIgnoreCase(serviceType) && opportunityDTO.getOpportunityDetailsDTO().getServiceType() != null) {
		    		serviceType = opportunityDTO.getOpportunityDetailsDTO().getServiceType();
		    	}
	    		model.addAttribute("serviceType",serviceType);
	    		
	    		// code for Component.		
	    		// getting list of all the components.
	    		componentsList = solutionService.getAllComponents();
			    
	    		// getting list of selected components.
	    		oppCompList = solutionService.getComponentsByOpportunityID(oppId);
	    		
	    		if(oppCompList != null && oppCompList.size() > 0){
	    			String[] componentIDs = new String[oppCompList.size()];
	    			for(int i=0;i<oppCompList.size();i++){
	    				componentIDs[i] = Integer.toString(oppCompList.get(i).getComponent().getComponentID());
	    			}
	    			defineScopeForm.setComponentIDs(componentIDs);
	    		}		    
			    
			    
	    		
	    	}
	    	setServiceScopes(opportunityScopes,defineScopeForm);
	    	solutionDTO.setSolutionId(solId);
	    	
	    	//List<ServiceScopeDTO> serviceScopeList = solutionService.getAllServiceScope();
	    	
	    	
	    	HashMap<String,List<ServiceElementDTO>> serviceElementDTOList = solutionService.getAllServiceElement(serviceType);
		    
	    	defineScopeForm.setSolutionDTO(solutionDTO);
		    defineScopeForm.setOpportunityScopes(opportunityScopes);
		    defineScopeForm.setServiceType(serviceType);
		    //model.addAttribute("serviceScopeList",serviceScopeList);
		    
		    
		    model.addAttribute("componentsList",componentsList);
		    
		    model.addAttribute("serviceElementDTOMap",serviceElementDTOList);
		    model.addAttribute("opportunityDTO",opportunityDTO);
		  
		    
		    model.addAttribute("defineScopeForm",defineScopeForm);
		    String message ="";
		    String solMsg =(String)getSessionValueFromKey(session,SOLUTION_NOTEXISTS);
		    if(solMsg != null  && !"".equals(solMsg)){
		    	message =  (String)getSessionValueFromKey(session,SOLUTION_NOTEXISTS);
		    	 model.addAttribute("message", message);
			}
		    
		    //check if user has edit access 
		    String hasEditSolAccess = (String) getSessionValueFromKey(session, HAS_EDIT_SOL_ACCESS);
			model.addAttribute("hasEditSolAccess", hasEditSolAccess);
			  
		    session.removeAttribute(SOLUTION_NOTEXISTS);
		 
		  
		} catch (Exception e) {
			logger.info("Error while loading define scope::: " + e.getMessage() + " :::: " + e.getCause() + " ...");
			// TODO: handle exception
		}
		return "defineScope";
	}
	
	@ModelAttribute("dimensionList")
	public Map<String, String> populateDimensionList() {
		Map<String, String> dimension = ApplicationPropertiesUtil
				.getMapConfigKeyValue(DEFINESCOPE_DIMENSION_NAME);
		return dimension;
	}
	
	@ModelAttribute("solApproachList")
	public Map<String, String> solApproachList() {
		Map<String, String> map = ApplicationPropertiesUtil
				.getMapConfigKeyValue(DEFINESCOPE_SOULUTION_APPROACH);
		return map;
	}
	
	@ModelAttribute("solTypeList")
	public Map<String, String> solTypeList() {
		Map<String, String> map = ApplicationPropertiesUtil
				.getMapConfigKeyValue(DEFINESCOPE_SOULUTION_TYPE);
		return map;
	}
	
		
	@RequestMapping(value="/opportunity/saveSolution", method = RequestMethod.POST)
	public String saveSolution(Model model, @ModelAttribute("defineScopeForm") DefineScopeForm defineScopeForm, HttpSession session){
			Integer oppId = getSessionOpportunityId(session);
			Integer ssolId = getSessionSolutionId(session);
			SolutionDTO solutionDTO = defineScopeForm.getSolutionDTO();
					
			try {
				if(ssolId != null){
					solutionDTO.setSolutionId(ssolId);
				}
				solutionDTO.setCreatedBy((String)getSessionValueFromKey(session, USER_NAME));
				Integer solId = solutionService.saveSolutionDetail(solutionDTO,oppId);
				//saving solutionId in session 
				setSessionSolutionId(session, solId);
			
				model.addAttribute("message", ApplicationPropertiesUtil.getProperty("msg.common.datasave.success"));
			} catch (MSSPException e) {
				// TODO Auto-generated catch block
				logger.error("There is a error while saving define scope data into database",e);
				e.printStackTrace();
				
				model.addAttribute("message", ApplicationPropertiesUtil.getProperty("msg.common.datasave.error"));
			}
		
			//String page = "forward:../opportunity/defineScope";
		
		 //return "solutionApproach";
			return "forward:../opportunity/defineScope";
	}
	
	
	@RequestMapping(value="/opportunity/saveServiceScope", method = RequestMethod.POST)
	public @ResponseBody String saveServiceScope(Model model, @ModelAttribute("defineScopeForm") DefineScopeForm defineScopeForm, HttpSession session){
			logger.info("Save Service Scope Form");
			Integer oppId = getSessionOpportunityId(session);
			String newServiceScopeIds = defineScopeForm.getNewServiceScopeIds();
			String deleletedOppScopeIds = defineScopeForm.getDeleletedOppScopeIds();
			String serviceType = defineScopeForm.getServiceType();
			String  selOpportunityScopes="";		
			
			try {
				if(defineScopeForm.getComponentIDs() != null && defineScopeForm.getComponentIDs().length > 0){
					logger.info("Saving data into OpportunityComponent");
					solutionService.saveOpportunityComponent(oppId,defineScopeForm.getComponentIDs());
				}
				 
				 solutionService.saveOpportunityScopes(oppId,newServiceScopeIds,deleletedOppScopeIds);
				
				 selOpportunityScopes = solutionService.getSelOpportunityScopes(oppId);
				 //updating serviceScope in opportunityDetail table
				 solutionService.updateServiceType(oppId,serviceType);
			
				model.addAttribute("message", ApplicationPropertiesUtil.getProperty("msg.common.datasave.success"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error("There is a error while saving define scope data into database",e);
				e.printStackTrace();
				model.addAttribute("message", ApplicationPropertiesUtil.getProperty("msg.common.datasave.error"));
			}
		
			
	
			return selOpportunityScopes;
	}
	
	@RequestMapping(value="/opportunity/defineScopeNext", method = RequestMethod.POST)
	public String defineScopeNext(Model model, @ModelAttribute("defineScopeForm") DefineScopeForm defineScopeForm, HttpSession session){
		saveNext= true;
		//saveSolution(model,defineScopeForm,session);
		return "redirect:../solution/productEstimation";
	}
	
	
	@RequestMapping(value="/opportunity/defineScopePrev", method = RequestMethod.POST)
	public String defineScopePrev(@ModelAttribute("defineScopeForm") DefineScopeForm defineScopeForm, HttpSession session){
		//savePage(defineScopeForm,session);
		return "redirect:../opportunity/displayOpportunity";
	}
	
	@RequestMapping(value="/opportunity/createNewSolution", method = RequestMethod.POST)
	public String createNewSolution(@ModelAttribute("defineScopeForm") DefineScopeForm defineScopeForm, HttpSession session){
		//savePage(defineScopeForm,session);
		session.removeAttribute(SOLUTION_ID);
		session.removeAttribute(HAS_EDIT_SOL_ACCESS);
		return "redirect:../opportunity/defineScope";
	}
	
	public void setServiceScopes(List<OpportunityScopeDTO> opportunityScopes, DefineScopeForm defineScopeForm ) {
		String serviceScopes[] = new String[opportunityScopes.size()];
		String serviceElements[] = new String[opportunityScopes.size()];
		String selServiceScopes = "";
		String selServiceElements = "";
		int i=0;
		int countElement=0;
		
		for(OpportunityScopeDTO oppScope :opportunityScopes ){
			Integer id = oppScope.getServiceScopeDTO().getServiceScopeId();
			if(id != null){
				selServiceScopes +=   id +"_"+oppScope.getOpportunityScopeId()+"_"+oppScope.getServiceScopeDTO().getServiceScopeName()+";";
				serviceScopes[i++]=id.toString();
				
				selServiceElements +=   oppScope.getServiceScopeDTO().getServiceElementId() +"_"+oppScope.getOpportunityScopeId()+"_"+oppScope.getServiceScopeDTO().getServiceElementName()+";";
				serviceElements[countElement++]=oppScope.getServiceScopeDTO().getServiceElementId().toString();
			}
		}
		if(!"".equals(selServiceScopes)){
			selServiceScopes = selServiceScopes.substring(0, selServiceScopes.length()-1);
		}
		
		if(!"".equals(selServiceElements)){
			selServiceElements = selServiceElements.substring(0, selServiceElements.length()-1);
		}
	
		defineScopeForm.setSelServiceScopes(selServiceScopes);
		defineScopeForm.setSelServiceElements(selServiceElements);
		defineScopeForm.setServiceScopes(serviceScopes);
		defineScopeForm.setServiceElements(serviceElements);
	}
	
	@RequestMapping(value="/opportunity/getServiceScopeByServiceElement", method = RequestMethod.POST)
	public @ResponseBody HashMap<String, List<ServiceScopeDTO>> getServiceScopeByServiceElement(@ModelAttribute("defineScopeForm") DefineScopeForm defineScopeForm, 
																				HttpSession session){
		
			String[] selectedServiceElements = defineScopeForm.getServiceElements();
			HashMap<String, List<ServiceScopeDTO>> serviceScopeMap = null;
			List<OpportunityScopeDTO> opportunityScopes = new ArrayList<OpportunityScopeDTO>();
			Integer oppId = getSessionOpportunityId(session);
			
			try {
				serviceScopeMap = solutionService.getServiceScopeByServiceElement(selectedServiceElements);
				
				if(oppId != null){
		    		opportunityScopes = solutionService.getOpportunityScopes(oppId);
		    	}
		    	setServiceScopes(opportunityScopes,defineScopeForm);
		    	
			} catch (MSSPException e) {
				// TODO Auto-generated catch block
				logger.error("There is a error while saving define scope data into database",e);
				e.printStackTrace();
			}
			return serviceScopeMap;
	}
	
	@RequestMapping(value="/opportunity/searchLdap" ,method=RequestMethod.POST)
	public @ResponseBody String getUserFromSignum(String searchUserVar){
			String resultUserRecord = "";
			resultUserRecord = solutionService.searchLDAPUsers(searchUserVar);
			return resultUserRecord;
		
	}
	
	/*@RequestMapping(value="/opportunity/saveOpportunityComponent")
	public String saveOpportunityComponent(Model model,@ModelAttribute("defineScopeForm") DefineScopeForm defineScopeForm, HttpSession session){
		System.out.println("Saving OpportunityComponent");
		// write code here to add data in OpportunityComponent table.
		 String[] componentsIDs = defineScopeForm.getComponentIDs();
		 for(String str : componentsIDs){
			 System.out.println(str);
		 }
		 
		 return "defineScope";
	}*/
	
	@RequestMapping(value = "/opportunity/products", method = RequestMethod.POST)
	public @ResponseBody
	List<ProductDetailsDTO> fetchProductList(
			Model model,
			HttpSession session) {
		List<ProductDetailsDTO> response = new ArrayList<>();
		
		try {
			response = solutionService.fetchProductList();
		} catch (MSSPException e) {
			logger.error("Error in /solution/activities::", e);
			model.addAttribute("message", ApplicationPropertiesUtil
					.getProperty("msg.common.datasave.error"));
		}
		return response;
	}
	
	@RequestMapping(value = "/opportunity/components", method = RequestMethod.POST)
	public @ResponseBody
	List<ComponentDTO> getComponentList(
			Model model,
			HttpSession session,
			@RequestParam("productID") Integer productID) {
		Integer oppId = getSessionOpportunityId(session);
		List<ComponentDTO> response = new ArrayList<>();
		List<Integer> oppCompList = new ArrayList<>();
		
		try {
			response = solutionService.getComponentByProduct(productID);
			
			// getting list of selected components.
    		oppCompList = solutionService.getOpportunityComponents(oppId);
    		
    		for(ComponentDTO eachObject: response){
    			if(oppCompList.contains(eachObject.getComponentID())){
    				eachObject.setChecked(true);
    			}
    		}
    		
		} catch (MSSPException e) {
			logger.error("Error in /solution/activities::", e);
			model.addAttribute("message", ApplicationPropertiesUtil
					.getProperty("msg.common.datasave.error"));
		}
		return response;
	}
	
	@RequestMapping(value = "/opportunity/saveOpportunityComponent", method = RequestMethod.POST)
	public @ResponseBody
	String saveOpportunityComponent(Model model,
			@RequestParam("componentIDs")  String[] componentIDs,
			@RequestParam(value = "serviceType", required = false, defaultValue = "") String serviceType,
			HttpSession session) {
		logger.info("Save Opportunity Component");
		Integer oppId = getSessionOpportunityId(session);
		String message = ApplicationPropertiesUtil
				.getProperty("msg.common.datasave.success");
		
		try {
			solutionService.updateServiceType(oppId,serviceType);
			
			if (componentIDs != null && componentIDs.length > 0) {
				solutionService.saveOpportunityComponent(oppId,
						componentIDs);
			}

			model.addAttribute("message", message);
		} catch (Exception e) {
			logger.error(
					"There is a error while persisting Opportunity Component data",
					e);
			e.printStackTrace();
			message = ApplicationPropertiesUtil
					.getProperty("msg.common.datasave.error");
			model.addAttribute("message", message);
		}
		return message;
	}

	@RequestMapping(value = "/opportunity/saveServices", method = RequestMethod.POST)
	public @ResponseBody
	String saveServices(Model model,
			@RequestParam("selectedServiceElements") String[] selectedServiceElements,
			HttpSession session) {
		logger.info("Save Service Scope Form");
		Integer oppId = getSessionOpportunityId(session);
		String message = ApplicationPropertiesUtil
				.getProperty("msg.common.datasave.success");

		try {
			solutionService.saveOpportunityScopesFromServiceElements(oppId,
					selectedServiceElements);
			model.addAttribute("message", message);
		} catch (Exception e) {
			logger.error(
					"There is a error while saving define scope data into database",
					e);
			message = ApplicationPropertiesUtil
					.getProperty("msg.common.datasave.error");
			model.addAttribute("message", message);
		}
		return message;
	}
}
