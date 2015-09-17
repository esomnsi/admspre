/**
* -------------------------------------------------------------------------------------------------------
* Copyright (C) 2012 Ericsson India Global Pvt Limited
* All Rights Reserved
* Project         		    :  IT_MSSP
* Module				    :  com.ericsson.mssp.solution.controller
* File name       		    :  TTController.java
* Description				:	<To Do>
* Author, Date & Release	:	May 7, 20132013
* Modification history		:
* Number	|Date   	   |Author        |Remark
* -----------------------------------------------------------------------------------------------------
* 1      	| May 7, 2013  	   |egaivij   	  | Created.
* -----------------------------------------------------------------------------------------------------
**/
 
package com.ericsson.mssp.solution.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ericsson.mssp.common.dto.JobRoleDTO;
import com.ericsson.mssp.common.dto.OpportunityDTO;
import com.ericsson.mssp.common.dto.SolutionLeverDTO;
import com.ericsson.mssp.common.dto.TTJobRoleDistributionDTO;
import com.ericsson.mssp.common.dto.TTOnOffRatioDTO;
import com.ericsson.mssp.common.dto.TTPartitionDetailDTO;
import com.ericsson.mssp.common.dto.TTPartitionNameDTO;
import com.ericsson.mssp.common.exception.MSSPException;
import com.ericsson.mssp.common.util.ApplicationPropertiesUtil;
import com.ericsson.mssp.solution.forms.TTDetailForm;
import com.ericsson.mssp.solution.service.ISolutionService;
import com.ericsson.mssp.solution.service.ITTService;



/**
 * @author egaivij
 *
 */
@Controller
public class TTController extends BaseController{
	
	@Autowired
	private ISolutionService solutionService;
	
	@Autowired
	private ITTService ttService;
	
	private final Log log = LogFactory.getLog(TTController.class);
	
	@InitBinder
	private void dateBinder(WebDataBinder binder) {
	            //The date format to parse or output your dates
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
	            //Create a new CustomDateEditor
	    CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
	            //Register it as custom editor for the Date type
	    binder.registerCustomEditor(Date.class, editor);
	}
	
	@RequestMapping(value="/solution/ttDetail")
	public String initPage(Model model, HttpSession session, @RequestParam(value = "isSave", required = false, defaultValue = "")String isSave)
	{
	   
	    try {
	    	
	    	TTDetailForm ttDetailForm = new TTDetailForm();
	    	
	    	Integer solutionId = getSessionSolutionId(session);
	    	Integer opportunityId = getSessionOpportunityId(session);
	    	
	    	ttService.loadPartitionNames(solutionId,ttDetailForm); 
	    	
	    	
	    	OpportunityDTO opportunityDTO= solutionService.getOpportunity(opportunityId);
			model.addAttribute("opportunityDTO",opportunityDTO);
			
			if(opportunityDTO.getOpportunityDetailsDTO().getTsd() == null || "".equals(opportunityDTO.getOpportunityDetailsDTO().getTsd())){
				model.addAttribute("errMessage", "Transformation start date and end date can't not be empty ");
			}
	    	List<String> serviceBucketData = solutionService.loadServiceBucketDataByOppSolutionID(opportunityId, solutionId);
			model.addAttribute("serviceBucketData", serviceBucketData);
			
			model.addAttribute("ttDetailForm", ttDetailForm);
			
			if("true".equalsIgnoreCase(isSave)){
				model.addAttribute("message", ApplicationPropertiesUtil.getProperty("msg.common.datasave.success"));
			}else if("false".equalsIgnoreCase(isSave)){
				model.addAttribute("message", ApplicationPropertiesUtil.getProperty("msg.common.datasave.error"));
			}
			
	    }catch(Exception e){
	    	logger.info("::: " + e.getMessage() + " :::: " + e.getCause() + " ...");
	    }
	    return "ttDetail";
	}
	
	
	/*@RequestMapping(value="/solution/ttJobroleDistribution")
	public String ttJobroleDistribution(Model model, HttpSession session){
	
		return "ttJobroleDistribution";
	}*/
	
	
	@RequestMapping(value="/solution/addTTPartition", method = RequestMethod.GET)
	public String addTTPartition(@RequestParam Integer numOfPartition,@RequestParam Float totalFTE,
			 ModelMap model){
		log.info("inside addTTPartition: "+numOfPartition  +" totalFTE " + totalFTE);
		model.addAttribute("numOfPartition", numOfPartition);
		model.addAttribute("totalFTE", totalFTE);
		return "addTTPartition";
	}
	
	
	@RequestMapping(value="/solution/savePartition", method = RequestMethod.POST)
	public String savePartition(Model model, @ModelAttribute("ttDetailForm") TTDetailForm ttDetailForm, HttpSession session){
		log.info("inside savePartition: ..............");
		
		String isSave = "true";
		  try {
			List<TTPartitionNameDTO> partitionList = ttDetailForm.getPartitionNameList();
			
			
			Integer solutionId = getSessionSolutionId(session);
			
			ttService.savePartitionNames(ttDetailForm, solutionId);
		
			
		  }catch(Exception e){
				isSave="false";
		    	logger.info("::: " + e.getMessage() + " :::: " + e.getCause() + " ...");
		    	e.printStackTrace();
		  }
			return "redirect:ttDetail?isSave="+isSave;
	}
	
	
	
	@RequestMapping(value="/solution/ttFTEBreakup")
	public String ttFTEBreakup(Model model, HttpSession session, @ModelAttribute("ttDetailForm") TTDetailForm ttDetailForm,
			@RequestParam(value = "isSave", required = false, defaultValue = "")String isSave){
		
		log.info("inside ttFTEBreakup: ..............");
		Integer solutionId = getSessionSolutionId(session);
		Integer opportunityId = getSessionOpportunityId(session);
		Integer selPartitionId = ttDetailForm.getSelPartitionId();
		
    	try {
			ttService.loadPartitionNames(solutionId, ttDetailForm);
			
			List<TTPartitionNameDTO> partitionNames = ttDetailForm.getPartitionNameList();
			Float partFTE=null;
			if(partitionNames.size()>0 ){
				
				if(selPartitionId == null){
					partFTE = partitionNames.get(0).getTtpartitionFte();
					selPartitionId = partitionNames.get(0).getTtpartitionNameId();
				}else{
					partFTE = getPartitionFTE(partitionNames, selPartitionId);
				}
			}else{
				model.addAttribute("errMessage", "You can't proceed further, Please create partition for TNT");
			}
			
			TTPartitionDetailDTO ttPartitionDetailDTO = ttService.getPartitionDetailById(selPartitionId);
			
			TTOnOffRatioDTO ttOnOffRatioDTO = ttService.getOnOffRatioByPartitionId(selPartitionId);
			
			OpportunityDTO opportunityDTO= solutionService.getOpportunity(opportunityId);
			model.addAttribute("opportunityDTO",opportunityDTO);
			
			ttDetailForm.setPartitionDetailDTO(ttPartitionDetailDTO);
			ttDetailForm.setTtOnOffRatioDTO(ttOnOffRatioDTO);
			model.addAttribute("partitionNames", partitionNames);
			model.addAttribute("partitionFTE", partFTE);
			model.addAttribute("ttDetailForm", ttDetailForm);
			
			if("true".equalsIgnoreCase(isSave)){
				model.addAttribute("message", ApplicationPropertiesUtil.getProperty("msg.common.datasave.success"));
			}else if("false".equalsIgnoreCase(isSave)){
				model.addAttribute("message", ApplicationPropertiesUtil.getProperty("msg.common.datasave.error"));
			}
			
		} catch (MSSPException e) {
			// TODO Auto-generated catch block
			logger.info("::: " + e.getMessage() + " :::: " + e.getCause() + " ...");
			e.printStackTrace();
		}
    	
    	
		return "ttFTEBreakup";
	}

	
	
	
	@RequestMapping(value="/solution/saveFTEBreakup", method = RequestMethod.POST)
	public String saveFTEBreakup(Model model, @ModelAttribute("ttDetailForm") TTDetailForm ttDetailForm, HttpSession session){
			log.info("inside saveFTEBreakup: ..............");
			String isSave = "true";
		  try {
			//
			Integer selPartitionId = ttDetailForm.getSelPartitionId();
			ttService.saveFTEBreakup(ttDetailForm, selPartitionId);
			
		  }catch(Exception e){
			  isSave="false";
		    	logger.info("::: " + e.getMessage() + " :::: " + e.getCause() + " ...");
		  }
			return "redirect:ttFTEBreakup?isSave="+isSave;
	}
	
	
	
	private Float getPartitionFTE(List<TTPartitionNameDTO> partitionNames,
			Integer selPartitionId) {
		
		for(TTPartitionNameDTO partName : partitionNames){
			if(selPartitionId.equals(partName.getTtpartitionNameId())){
				return partName.getTtpartitionFte();
			}
		}
		return null;
	}
	
	@RequestMapping(value="/solution/tntReviewFTE")
	public String tntRevireFte(Model model, HttpSession session)
	{
		OpportunityDTO opportunityDTO = new OpportunityDTO();
		Integer solutionID = getSessionSolutionId(session);
		Integer opportunityID = getSessionOpportunityId(session);
		Map<String,Object> timeInterval = null;
		
		
		try{
			List<JobRoleDTO> jobRoleDTOList = solutionService.getJobRoleList();
		if (opportunityID != null) {
		    opportunityDTO = solutionService.getOpportunity(opportunityID);
		}
		
		timeInterval = ttService.getTimeInterval(solutionID,ApplicationPropertiesUtil.string2Date(opportunityDTO.getOpportunityDetailsDTO().getTsd()),ApplicationPropertiesUtil.string2Date(opportunityDTO.getOpportunityDetailsDTO().getTed()));
		String []timeLineInterval = (String[])timeInterval.get("dateArray");
		
		List<TTJobRoleDistributionDTO> selectedJobRoleList = ttService.getSelectedJobRoleDistributionList(solutionID);
		
		List<String> serviceBucketData = null;
		serviceBucketData = solutionService.loadServiceBucketDataByOppSolutionID(opportunityID, solutionID);
		
		model.addAttribute("jobRoleDTOList", jobRoleDTOList);
		model.addAttribute("opportunityDTO", opportunityDTO);
		model.addAttribute("timeLineInterval", timeLineInterval);
		model.addAttribute("serviceBucketData", serviceBucketData);
		model.addAttribute("selectedJobRoleList", selectedJobRoleList);
		}
		catch (MSSPException e) {
			// TODO Auto-generated catch block
			logger.info("::: " + e.getMessage() + " :::: " + e.getCause() + " ...");
			e.printStackTrace();
		}
		
		
		return "ttReviewFTE";
	}
}
