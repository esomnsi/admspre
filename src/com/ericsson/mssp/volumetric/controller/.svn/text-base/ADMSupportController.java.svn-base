package com.ericsson.mssp.volumetric.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ericsson.mssp.common.constant.MSSPConstants;
import com.ericsson.mssp.common.dto.AccessManagementDTO;
import com.ericsson.mssp.common.dto.AvailabilityManagementDTO;
import com.ericsson.mssp.common.dto.CapacityManagementDTO;
import com.ericsson.mssp.common.dto.ChangeManagementDTO;
import com.ericsson.mssp.common.dto.ConfigurationManagementDTO;
import com.ericsson.mssp.common.dto.OpportunityScopeDTO;
import com.ericsson.mssp.common.dto.ReleaseManagementDTO;
import com.ericsson.mssp.common.dto.ProgramManagementDTO;
import com.ericsson.mssp.common.dto.SecurityManagementDTO;
import com.ericsson.mssp.common.dto.ServiceScopeDTO;
import com.ericsson.mssp.common.exception.MSSPException;
import com.ericsson.mssp.solution.controller.BaseController;
import com.ericsson.mssp.solution.service.ISolutionService;
import com.ericsson.mssp.volumetric.forms.AccessManagementForm;
import com.ericsson.mssp.volumetric.forms.AvailabilityManagementForm;
import com.ericsson.mssp.volumetric.forms.CapacityManagementForm;
import com.ericsson.mssp.volumetric.forms.ChangeManagementForm;
import com.ericsson.mssp.volumetric.forms.ConfigurationManagementForm;
import com.ericsson.mssp.volumetric.forms.ProgramManagementForm;
import com.ericsson.mssp.volumetric.forms.ReleaseManagementForm;
import com.ericsson.mssp.volumetric.forms.SecurityManagementForm;
import com.ericsson.mssp.volumetric.service.VolumetricService;


@Controller
public class ADMSupportController extends BaseController{
	
	@Autowired
	private VolumetricService volumetricService;
	
	@Autowired
	private ISolutionService solutionService;
	
	public static Logger logger = Logger.getLogger(ADMSupportController.class);

	private static final String serviceElement = "ADM_SUPP";
	
	@RequestMapping(value = "/solution/volumetricAdmSupp")
    public String serviceFunctions(ModelMap model, HttpSession session){
		logger.info("volumetric service functions for ["+serviceElement+"]");
		boolean allSelectedFlag = false;
		boolean oneServiceElementSelectedFlag = false;
		
		ProgramManagementForm programManagementForm = new ProgramManagementForm();
		ReleaseManagementForm releaseManagementForm = new ReleaseManagementForm();
		ChangeManagementForm changeManagementForm = new ChangeManagementForm();
		CapacityManagementForm capacityManagementForm = new CapacityManagementForm();
		ConfigurationManagementForm configurationManagementForm = new ConfigurationManagementForm();
		SecurityManagementForm securityManagementForm = new SecurityManagementForm();
		AvailabilityManagementForm availabilityManagementForm = new AvailabilityManagementForm();
		  
		Integer oppId = getSessionOpportunityId(session);
		Integer solId = getSessionSolutionId(session);
		/*List<Integer> serviceScopeIDList = new ArrayList<Integer>();*/
		
		try {
    	List<OpportunityScopeDTO> serviceScopeList = volumetricService.getServiceScopeByServiceElement((Integer)session.getAttribute(OPPORTUNITY_ID),serviceElement);
        /*List<ServiceScopeDTO> allServiceScopeList = volumetricService.getAllServiceScopeByServiceElement(serviceElement);
    	if((null != serviceScopeList) && (null != allServiceScopeList) && (serviceScopeList.size() == allServiceScopeList.size())){
    		allSelectedFlag = true;
    	}else if((null != serviceScopeList) && (serviceScopeList.size() == 1)){
    		oneServiceElementSelectedFlag = true;
    	}
    	
    	Float totalFteYear1 = volumetricService
    			.loadServiceBucketDataByOppSolutionID(oppId, solId);*/
    	
    	
    	for (OpportunityScopeDTO oppScope : serviceScopeList) {
    		
    		/*serviceScopeIDList.add(oppScope.getServiceScopeDTO().getServiceScopeId());*/
    		Integer serviceScopeID = oppScope.getServiceScopeDTO().getServiceScopeId();
			switch (serviceScopeID) {
			
			case 10:
				//Program Management
				programManagementForm.setOpportunityScopeId(oppScope.getOpportunityScopeId());
				ProgramManagementDTO programManagementDTO = volumetricService.loadProgramManagement(solId,serviceScopeID);
				programManagementForm.setProgramManagementDTO(programManagementDTO);
				break;
				
			case 11:
				//Release Management
				releaseManagementForm.setOpportunityScopeId(oppScope.getOpportunityScopeId());
				ReleaseManagementDTO releaseManagementDTO = volumetricService.loadReleaseManagement(solId,serviceScopeID);
				releaseManagementForm.setReleaseManagementDTO(releaseManagementDTO);				
				break;
				
			case 12:
				//Change Management
				changeManagementForm.setOpportunityScopeId(oppScope.getOpportunityScopeId());
				ChangeManagementDTO changeManagementDTO = volumetricService.loadChangeManagement(solId,serviceScopeID);
				changeManagementForm.setChangeManagementDTO(changeManagementDTO);
				
				break;
				
			case 13:
				//Capacity Management
				capacityManagementForm.setOpportunityScopeId(oppScope.getOpportunityScopeId());
				CapacityManagementDTO capacityManagementDTO = volumetricService.loadCapacityManagement(solId,serviceScopeID);
				System.out.println("DefaultValue for input = "+capacityManagementDTO.getPrepareAndUseModels());
				capacityManagementForm.setCapacityManagementDTO(capacityManagementDTO);
								
				break;
				
			case 14:
				//Configuration Management
				configurationManagementForm.setOpportunityScopeId(oppScope.getOpportunityScopeId());
				ConfigurationManagementDTO configurationManagementDTO = volumetricService.loadConfigurationManagement(solId,serviceScopeID);
				configurationManagementForm.setConfigurationManagementDTO(configurationManagementDTO);
								
				break;
				
			case 15:
				//Security Management
				securityManagementForm.setOpportunityScopeId(oppScope.getOpportunityScopeId());
				SecurityManagementDTO securityManagementDTO = volumetricService.loadSecurityManagement(solId,serviceScopeID);
				securityManagementForm.setSecurityManagementDTO(securityManagementDTO);
								
				break;
				
			case 16:
				//Availability Management
				availabilityManagementForm.setOpportunityScopeId(oppScope.getOpportunityScopeId());
				AvailabilityManagementDTO availabilityManagementDTO = volumetricService.loadAvailabilityManagement(solId,serviceScopeID);
				availabilityManagementForm.setAvailabilityManagementDTO(availabilityManagementDTO);
								
				break;
				
		
			}
    	}
			model.addAttribute("programManagementForm", programManagementForm);
			model.addAttribute("releaseManagementForm", releaseManagementForm);
			model.addAttribute("changeManagementForm", changeManagementForm);	
			model.addAttribute("capacityManagementForm", capacityManagementForm);	
			model.addAttribute("configurationManagementForm", configurationManagementForm);
			model.addAttribute("securityManagementForm", securityManagementForm);
			model.addAttribute("availabilityManagementForm", availabilityManagementForm);
			
    	
			
		model.put("selectedTab", serviceElement);
    	model.put("serviceScopeList",serviceScopeList);
    	/*model.put("serviceScopeIDList", serviceScopeIDList);*/
    	
     	// Service Bucket table data
    	List<String> serviceBucketData = solutionService
				.loadServiceBucketDataByOppSolutionID(oppId, solId);
			model.addAttribute("serviceBucketData", serviceBucketData);
    	
		} catch (MSSPException e) {
			// TODO Auto-generated catch block
			logger.error("There is a error while loading Adm Support data",e);
			e.printStackTrace();
		}
    	return "volumetric";
    }
    	
	
	@RequestMapping(value = "/solution/saveProgramManagement", method = RequestMethod.POST)
	public String saveProgramManagement(Model model,
			@ModelAttribute("programManagementForm") ProgramManagementForm programManagementForm,
			HttpSession session) {
		try {
			Integer solutionId = getSessionSolutionId(session);
			Integer OppScopeId = programManagementForm.getOpportunityScopeId();
			ProgramManagementDTO programManagementDTO = programManagementForm.getProgramManagementDTO();
			volumetricService.saveProgramManagement(programManagementDTO,solutionId,OppScopeId);
			
			programManagementDTO = volumetricService.loadProgramManagement(solutionId, null);
			programManagementForm.setProgramManagementDTO(programManagementDTO);
			model.addAttribute("programManagementForm", programManagementForm);
		} catch (Exception e) {
			logger.error("There is a error while saving Program Management data into database",e);
			e.printStackTrace();			
		}

		return "programManagement";

	}
    
    @RequestMapping(value = "/solution/saveReleaseManagement", method = RequestMethod.POST)
   	public String saveReleaseanagement(Model model,
   			@ModelAttribute("releaseManagementForm") ReleaseManagementForm releaseManagementForm,
   			HttpSession session) {
   		try {
   			Integer solutionId = getSessionSolutionId(session);
   			Integer OppScopeId = releaseManagementForm.getOpportunityScopeId();
   			ReleaseManagementDTO releaseManagementDTO = releaseManagementForm.getReleaseManagementDTO();
   			volumetricService.saveReleaseManagement(releaseManagementDTO,solutionId,OppScopeId);
   			
   			releaseManagementDTO = volumetricService.loadReleaseManagement(solutionId, null);
   			releaseManagementForm.setReleaseManagementDTO(releaseManagementDTO);
   			model.addAttribute("releaseManagementForm", releaseManagementForm);
   		} catch (MSSPException e) {
   			// TODO Auto-generated catch block
   			logger.error(
   					"There is a error while saving Release Management data into database",
   					e);
   			e.printStackTrace();
   			
   		}

   		return "releaseManagement";
   	}
    
    @RequestMapping(value = "/solution/saveChangeManagement", method = RequestMethod.POST)
   	public String saveChangeManagement(Model model,
   			@ModelAttribute("changeManagementForm") ChangeManagementForm changeManagementForm,
   			HttpSession session) {

   		try {
   			Integer solutionId = getSessionSolutionId(session);
   			Integer OppScopeId = changeManagementForm.getOpportunityScopeId();
   			ChangeManagementDTO changeManagementDTO = changeManagementForm.getChangeManagementDTO();   			
   			volumetricService.saveChangeManagement(changeManagementDTO,solutionId,OppScopeId);   
   			
   			changeManagementDTO = volumetricService.loadChangeManagement(solutionId, null);
   			changeManagementForm.setChangeManagementDTO(changeManagementDTO);
   			model.addAttribute("changeManagementForm", changeManagementForm);
   		} catch (MSSPException e) {
   			logger.error("There is a error while saving Change Management data into database",e);
   			e.printStackTrace();
   			
   		}

   		return "changeManagement";

   	}
    
    @RequestMapping(value = "/solution/saveCapacityManagement", method = RequestMethod.POST)
	public String saveCapacityManagement(Model model,
			@ModelAttribute("capacityManagementForm") CapacityManagementForm capacityManagementForm,
			HttpSession session) {
		try {
			Integer solutionId = getSessionSolutionId(session);
			Integer OppScopeId = capacityManagementForm.getOpportunityScopeId();
		    CapacityManagementDTO capacityManagementDTO = capacityManagementForm.getCapacityManagementDTO();
			volumetricService.saveCapacityManagement(capacityManagementDTO,solutionId,OppScopeId);
			
			capacityManagementDTO = volumetricService.loadCapacityManagement(solutionId, null);
			capacityManagementForm.setCapacityManagementDTO(capacityManagementDTO);
			model.addAttribute("capacityManagementForm", capacityManagementForm);
			
		} catch (MSSPException e) {
			logger.error(
					"There is a error while saving Capacity Management data into database",	e);
			e.printStackTrace();
			
		}

		return "capacityManagement";

	}
    
    @RequestMapping(value = "/solution/saveConfigurationManagement", method = RequestMethod.POST)
	public String saveConfigurationManagement(Model model,
			@ModelAttribute("configurationManagementForm") ConfigurationManagementForm configurationManagementForm,
			HttpSession session) {
		try {
			
			Integer solutionId = getSessionSolutionId(session);
			Integer OppScopeId = configurationManagementForm.getOpportunityScopeId();
			ConfigurationManagementDTO configurationManagementDTO = configurationManagementForm.getConfigurationManagementDTO();
			volumetricService.saveConfigurationManagement(configurationManagementDTO,solutionId,OppScopeId);
			
			configurationManagementDTO = volumetricService.loadConfigurationManagement(solutionId, null);
			configurationManagementForm.setConfigurationManagementDTO(configurationManagementDTO);
			model.addAttribute("configurationManagementForm", configurationManagementForm);

		} catch (MSSPException e) {
			logger.error("There is a error while saving Configuration Management data into database",
					e);
			e.printStackTrace();
		}
		return "configurationManagement";

	}
    
    @RequestMapping(value = "/solution/saveSecurityManagement", method = RequestMethod.POST)
	public String saveSecurityManagement(Model model,
			@ModelAttribute("securityManagementForm") SecurityManagementForm securityManagementForm,
			HttpSession session) {
		try {
			
			Integer solutionId = getSessionSolutionId(session);
			Integer OppScopeId = securityManagementForm.getOpportunityScopeId();
			SecurityManagementDTO securityManagementDTO = securityManagementForm.getSecurityManagementDTO();			
			volumetricService.saveSecurityManagement(securityManagementDTO,solutionId,OppScopeId);
			
			securityManagementDTO = volumetricService.loadSecurityManagement(solutionId, null);
			securityManagementForm.setSecurityManagementDTO(securityManagementDTO);
			model.addAttribute("securityManagementForm", securityManagementForm);

		} catch (MSSPException e) {
			// TODO Auto-generated catch block
			logger.error(
					"There is a error while saving Security Management data into database",
					e);
			e.printStackTrace();
		}
		return "securityManagement";

	}
    
    @RequestMapping(value = "/solution/saveAvailabilityManagement", method = RequestMethod.POST)
	public String saveAvailabilityManagement(Model model,
			@ModelAttribute("availabilityManagementForm") AvailabilityManagementForm availabilityManagementForm,
			HttpSession session) {
		try {
			
			Integer solutionId = getSessionSolutionId(session);
			Integer OppScopeId = availabilityManagementForm.getOpportunityScopeId();
			AvailabilityManagementDTO availabilityManagementDTO = availabilityManagementForm.getAvailabilityManagementDTO();			
			volumetricService.saveAvailabilityManagement(availabilityManagementDTO,solutionId,OppScopeId);
			
			availabilityManagementDTO = volumetricService.loadAvailabilityManagement(solutionId, null);
			availabilityManagementForm.setAvailabilityManagementDTO(availabilityManagementDTO);
			model.addAttribute("availabilityManagementForm", availabilityManagementForm);

		} catch (MSSPException e) {
			// TODO Auto-generated catch block
			logger.error(
					"There is a error while saving Availability Management data into database",
					e);
			e.printStackTrace();
		}
		return "availabilityManagement";

	}
    
   
	
    	  	
}
