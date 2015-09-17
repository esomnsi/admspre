/**
* -------------------------------------------------------------------------------------------------------
* Copyright (C) 2012 Ericsson India Global Pvt Limited
* All Rights Reserved
* Project         		    :  IT_MSSP
* Module				    :  com.ericsson.mssp.solution.controller
* File name       		    :  VolumetricSolutionController.java
* Description				:	<To Do>
* Author, Date & Release	:	Jan 3, 20132013
* Modification history		:
* Number	|Date   	   |Author        |Remark
* -----------------------------------------------------------------------------------------------------
* 1      	| Jan 3, 2013  	   |egaivij   	  | Created.
* -----------------------------------------------------------------------------------------------------
**/
 
package com.ericsson.mssp.solution.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ericsson.mssp.common.constant.MSSPConstants;
import com.ericsson.mssp.common.dto.OpportunityDTO;
import com.ericsson.mssp.common.dto.OpportunityScopeDTO;
import com.ericsson.mssp.common.dto.SolutionApproachDimensionDTO;
import com.ericsson.mssp.common.dto.SolutionOtherMiscDTO;
import com.ericsson.mssp.common.dto.SolutionServiceDeskDTO;
import com.ericsson.mssp.common.dto.SolutionTestingAsAserviceDTO;
import com.ericsson.mssp.common.dto.SupportWindowMatrixDTO;
import com.ericsson.mssp.common.dto.TaasServiceDTO;
import com.ericsson.mssp.common.entity.GenericTestingInputs;
import com.ericsson.mssp.common.entity.GenericTestingOverhead;
import com.ericsson.mssp.common.entity.JobRole;
import com.ericsson.mssp.common.entity.RegressionLever;
import com.ericsson.mssp.common.entity.Solution;
import com.ericsson.mssp.common.entity.TestEffReduction;
import com.ericsson.mssp.common.entity.TestingService;
import com.ericsson.mssp.common.exception.MSSPException;
import com.ericsson.mssp.common.util.ApplicationPropertiesUtil;
import com.ericsson.mssp.solution.forms.EnhancementAndDevForm;
import com.ericsson.mssp.solution.forms.ServiceDeskForm;
import com.ericsson.mssp.solution.forms.SolutionL1OperationsForm;
import com.ericsson.mssp.solution.forms.SolutionL2OperationsForm;
import com.ericsson.mssp.solution.forms.SolutionL3OperationsForm;
import com.ericsson.mssp.solution.forms.SolutionOtherMiscForm;
import com.ericsson.mssp.solution.forms.TaasForm;
import com.ericsson.mssp.solution.forms.TaasGenericInputForm;
import com.ericsson.mssp.solution.forms.TaasOutputForm;
import com.ericsson.mssp.solution.forms.VolumetricSolutionForm;
import com.ericsson.mssp.solution.service.ISolutionService;
import com.ericsson.mssp.solution.service.ITaasService;
import com.ericsson.mssp.solution.service.IVolumetricSolutionService;
import com.ericsson.mssp.taas.dto.EffLeverDTO;

/**
 * @author egaivij
 *
 */
@Controller
public class VolumetricSolutionController extends BaseController implements MSSPConstants{
	
	/*@Autowired
	private ISolutionService solutionService;
	
	@Autowired
	private IVolumetricSolutionService volumetricSolutionService;
	
	@Autowired
	private ITaasService itaasService;
	
	
	private static final Log logger = LogFactory.getLog(VolumetricSolutionController.class);
	
	@InitBinder
	 public void initBinder(WebDataBinder binder)
	 {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    dateFormat.setLenient(false);

	    // true passed to CustomDateEditor constructor means convert empty String to null
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	 }
	
	@RequestMapping(value="/solution/volumetricSolution")
	public String initVolumetricSolution(Model model, HttpSession session, 
			@ModelAttribute("volumetricSolutionForm") VolumetricSolutionForm volumetricSolutionForm)
	{	
	
		Integer oppId = getSessionOpportunityId(session);
		Integer solId = getSessionSolutionId(session);
		
		ServiceDeskForm serviceDeskForm = new ServiceDeskForm();
		SolutionL1OperationsForm solutionL1OperationsForm = new SolutionL1OperationsForm();
		SolutionL2OperationsForm solutionL2OperationsForm = new SolutionL2OperationsForm();
		SolutionL3OperationsForm solutionL3OperationsForm = new SolutionL3OperationsForm();
		EnhancementAndDevForm enhancementAndDevForm = new EnhancementAndDevForm();
		
		SolutionOtherMiscForm solutionGovPMOForm = new SolutionOtherMiscForm();
		SolutionOtherMiscForm solutionOtherMiscForm = new SolutionOtherMiscForm();
		TaasForm taasForm = new TaasForm();
		
		OpportunityDTO opportunityDTO= solutionService.getOpportunity(oppId);
		
		try {
			//Map<String, List<SolutionInputDefinitionDTO>> solInputFieldsListMap = new HashMap<String, List<SolutionInputDefinitionDTO>>();
			Integer solDimentionAttId = volumetricSolutionForm.getDimensionAttributeId();
		
 			List <OpportunityScopeDTO> selServiceScopeList = solutionService.loadAllOpportunityScopesByOppotunityId(oppId);
			
 		
 			List <SolutionApproachDimensionDTO> dimList = solutionService.getDimentionListBySolutionId(solId);
			
			if(dimList.size()>0 && solDimentionAttId == null){
				solDimentionAttId=dimList.get(0).getSolutionApproachDimensionId();
			}
		
			for (OpportunityScopeDTO oppScope : selServiceScopeList) {

				switch (oppScope.getServiceScopeDTO().getServiceScopeId()) {
				case 1:
					serviceDeskForm.setOpportunityScopeId(oppScope.getOpportunityScopeId());
					
					List<SolutionServiceDeskDTO> serviceDeskList = volumetricSolutionService
							.loadServiceDeskListBySolutionId(solId,
									solDimentionAttId);
					if (serviceDeskList.size() > 0) {
						serviceDeskForm.setServiceDeskDTOList(serviceDeskList);
						serviceDeskForm
								.setSupportWindowMatrixId(serviceDeskList
										.get(0).getSupportWindowMatrixDTO()
										.getSupportWindowMatrixId());
					}
					break;

				case 2:
					
					solutionL1OperationsForm = volumetricSolutionService
							.loadSolutionL1OperationsDetails(solId,
									solDimentionAttId);
					solutionL1OperationsForm.setOpportunityScopeId(oppScope.getOpportunityScopeId());
					break;
				case 3:
					
					solutionL2OperationsForm = volumetricSolutionService
							.loadSolutionL2OperationsDetails(solId,
									solDimentionAttId);
					solutionL2OperationsForm.setOpportunityScopeId(oppScope.getOpportunityScopeId());
					break;

				case 4:
					
					solutionL3OperationsForm = volumetricSolutionService
							.loadSolutionL3OperationsDetails(solId,
									solDimentionAttId);
					solutionL3OperationsForm.setOpportunityScopeId(oppScope.getOpportunityScopeId());
					break;
				case 5:
				
					enhancementAndDevForm = volumetricSolutionService
							.loadSolutionEnhancementAndDevDetails(solId,
									solDimentionAttId);
					enhancementAndDevForm.setOpportunityScopeId(oppScope.getOpportunityScopeId());
					break;
				case 6:
					taasForm.setOppScopeID(oppScope.getOpportunityScopeId());
					break;
				case 7:
					
					List<SolutionOtherMiscDTO> GovAndPMODTOList = volumetricSolutionService
							.loadGovAndPMODTO(solId, solDimentionAttId);
					if (GovAndPMODTOList.size() > 0) {
						solutionGovPMOForm
								.setSolutionOtherMiscList(GovAndPMODTOList);
					}
					solutionGovPMOForm.setOpportunityScopeId(oppScope.getOpportunityScopeId());
					break;
				case 9:
					
					List<SolutionOtherMiscDTO> otherMiscDTOList = volumetricSolutionService
							.loadOtherMiscDTO(solId, solDimentionAttId);
					if (otherMiscDTOList.size() > 0) {
						solutionOtherMiscForm
								.setSolutionOtherMiscList(otherMiscDTOList);
					}
					solutionOtherMiscForm.setOpportunityScopeId(oppScope.getOpportunityScopeId());
					break;
				}

			}
 			
 			Map<String, String> unitL3List = ApplicationPropertiesUtil
					.getMapConfigKeyValue(VOLSOL_UNITL3_List,true);
			Map<String, String> scopeList = ApplicationPropertiesUtil
					.getMapConfigKeyValue(VOLSOL_SCOPE_List);
			Map<String, String> duritionUnitList = ApplicationPropertiesUtil
					.getMapConfigKeyValue(VOLSOL_DURUNIT_List);
			
			Map<String, String> unitFCList = ApplicationPropertiesUtil
					.getMapConfigKeyValue(VOLSOL_UNIT_FC_LIST,true);
			
			model.addAttribute("serviceScopeList",selServiceScopeList);
			model.addAttribute("solutionDimentionList",dimList);
			 
			model.addAttribute("supportWindowList",getSupportWindowList());
			model.addAttribute("unitL3List", unitL3List);
			model.addAttribute("scopeList", scopeList);
			model.addAttribute("duritionUnitList", duritionUnitList);
			model.addAttribute("unitFCList", unitFCList);
			
			model.addAttribute("serviceDeskForm", serviceDeskForm);
			

			model.addAttribute("solutionL1OperationsForm",
					solutionL1OperationsForm);
			model.addAttribute("solutionL2OperationsForm",
					solutionL2OperationsForm);
			model.addAttribute("solutionL3OperationsForm",
					solutionL3OperationsForm);
			model.addAttribute("enhancementAndDevForm",
					enhancementAndDevForm);
			
			model.addAttribute("opportunityDTO",opportunityDTO);
			   
		    model.addAttribute("jobRoleDTOList", getJobRoleList());
			model.addAttribute("solutionGovPMOForm", solutionGovPMOForm);
		    model.addAttribute("solutionOtherMiscForm", solutionOtherMiscForm);
		    
		 // Service Bucket table data
			List<String> serviceBucketData = solutionService
				.loadServiceBucketDataByOppSolutionID(oppId, solId);
			model.addAttribute("serviceBucketData", serviceBucketData);
			
			 //check if user has edit access 
		    String hasEditSolAccess = (String) getSessionValueFromKey(session, HAS_EDIT_SOL_ACCESS);
			model.addAttribute("hasEditSolAccess", hasEditSolAccess);
			
		} catch (MSSPException e) {
			// TODO Auto-generated catch block
			logger.error("There is a error while loading volumetric solution data",e);
			e.printStackTrace();
		}
		
		Added for TAAS
		TaasGenericInputForm taasGenIPForm = new TaasGenericInputForm();
//		TaasForm taasForm = new TaasForm();
		List<TaasServiceDTO> testServList = itaasService.getGenericInputList(solId);
		List<GenericTestingInputs> genTestIPList = itaasService.getGenericTestingInputList(solId);
		List<GenericTestingOverhead> testOverheadList = itaasService.getTestingOverheadList(solId);
		HashMap<String, Object> effLeverMap = itaasService.getEffLeverValues(solId);
		List<EffLeverDTO> majEffLevList = (List<EffLeverDTO>) effLeverMap.get(RELEASE_TYPE_MAJOR);
		List<EffLeverDTO> minEffLevList = (List<EffLeverDTO>) effLeverMap.get(RELEASE_TYPE_MINOR);
		majEffLevList = createDtoList(majEffLevList);
		minEffLevList = createDtoList(minEffLevList);
		List<RegressionLever> regLevList = itaasService.getRegLeverValues(solId);
		List<SolutionTestingAsAserviceDTO> serviceList = itaasService.getTaasOutput(solId);
//		List<TestEffReduction> testEffRedList = itaasService.getTestEffRedValues(solId);
		taasForm.setMajEffLeverList(majEffLevList);
		taasForm.setMinEffLeverList(minEffLevList);
		taasGenIPForm.setSolution(solId);
		taasGenIPForm.setTestServList(testServList);
		taasForm.setServiceList(serviceList);
		taasForm.setSolution(solId);
		taasForm.setGenTestInputList(genTestIPList);
		taasForm.setGenTestOverheadList(testOverheadList);
		taasForm.setRegLeverList(regLevList);
//		taasForm.setTestEffRedList(testEffRedList);
		model.addAttribute("taasGenIPForm", taasGenIPForm);
		model.addAttribute("taasForm", taasForm);
		model.addAttribute("taasOPForm", new TaasOutputForm());
		objectMap.put("taasGenIPForm", taasGenIPForm);
		objectMap.put("taasForm", taasForm);
		objectMap.put("taasOPForm", new TaasOutputForm());
		return "volumetricSolution";
	}
	
	@RequestMapping(value = "/solution/refreshServiceBucket",  method = RequestMethod.POST)
	public String refreshServiceBucket(Model model, HttpSession session) {
		Integer oppId = getSessionOpportunityId(session);
		Integer solId = getSessionSolutionId(session);

		// Service Bucket table data
		List<String> serviceBucketData = solutionService
				.loadServiceBucketDataByOppSolutionID(oppId, solId);
		model.addAttribute("serviceBucketData", serviceBucketData);
		
		return "serviceBucket";
	}
	
	@RequestMapping(value = "/solution/saveServiceDesk", method = RequestMethod.POST)
	public String saveServiceDesk(Model model,
			@ModelAttribute("serviceDeskForm") ServiceDeskForm serviceDeskForm,
			HttpSession session) {

		try {
			Integer solutionId = getSessionSolutionId(session);
			Integer solDimentionAttId = serviceDeskForm
					.getSolutionDimentionAttId();
			List<SolutionServiceDeskDTO> serviceDeskList = serviceDeskForm
					.getServiceDeskDTOList();
			Integer OppScopeId = serviceDeskForm.getOpportunityScopeId();
			volumetricSolutionService.saveServiceDesk(serviceDeskList,
					solutionId, serviceDeskForm.getSupportWindowMatrixId(),
					solDimentionAttId,OppScopeId);

			serviceDeskList = volumetricSolutionService
					.loadServiceDeskListBySolutionId(solutionId,
							solDimentionAttId);
			if (serviceDeskList.size() > 0) {
				serviceDeskForm.setServiceDeskDTOList(serviceDeskList);
				serviceDeskForm
						.setSupportWindowMatrixId(serviceDeskList.get(0)
								.getSupportWindowMatrixDTO()
								.getSupportWindowMatrixId());
			}

			model.addAttribute("supportWindowList", getSupportWindowList());
			model.addAttribute("serviceDeskForm", serviceDeskForm);

		} catch (MSSPException e) {
			// TODO Auto-generated catch block
			logger.error(
					"There is a error while saving define scope data into database",
					e);
			e.printStackTrace();
			// model.addAttribute("message",
			// ApplicationPropertiesUtil.getProperty("msg.common.datasave.error"));
		}

		return "serviceDesk";

	}
	
	@RequestMapping(value = "/solution/loadDefaultValuesForServiceScopes", method = RequestMethod.POST)
	public @ResponseBody
	String loadDefaultValuesForServiceScopes(Model model,
			@RequestParam("serviceId") String serviceId) {
		String defaultValuesStr = "";
		try {
			defaultValuesStr = volumetricSolutionService
					.loadDefaultValuesByServiceId(new Integer(serviceId));

		} catch (MSSPException e) {
			// TODO Auto-generated catch block
			logger.error("There is a error while loading data from database", e);
			e.printStackTrace();
		}

		return defaultValuesStr;

	}
	
	
	@RequestMapping(value = "/solution/saveSolutionL1Operations", method = RequestMethod.POST)
	public String saveSolutionL1Operations(
			Model model,
			@ModelAttribute("solutionL1OperationsForm") SolutionL1OperationsForm solutionL1OperationsForm,
			HttpSession session) {
			
		try {
			Integer solutionId = getSessionSolutionId(session);
			Integer solDimentionAttId =solutionL1OperationsForm.getSolutionDimentionAttId();
			Integer oppScopeId = solutionL1OperationsForm.getOpportunityScopeId();
			
			volumetricSolutionService.saveSolutionL1Operations(solutionId,
						solutionL1OperationsForm, solDimentionAttId, oppScopeId);
			
			solutionL1OperationsForm = volumetricSolutionService
					.loadSolutionL1OperationsDetails(solutionId,
							solDimentionAttId);
			solutionL1OperationsForm.setOpportunityScopeId(oppScopeId);
	
			model.addAttribute("scopeList",ApplicationPropertiesUtil
					.getMapConfigKeyValue(VOLSOL_SCOPE_List));
			model.addAttribute("supportWindowList",getSupportWindowList());
			model.addAttribute("solutionL1OperationsForm", solutionL1OperationsForm);
		

		} catch (MSSPException e) {
			// TODO Auto-generated catch block
			logger.error(
					"There is a error while saving data into database",
					e);
			e.printStackTrace();
		}
		return "solutionL1Operations";

	}
	
	@RequestMapping(value = "/solution/saveSolutionL2Operations", method = RequestMethod.POST)
	public String saveSolutionL2Operations(
			Model model,
			@ModelAttribute("solutionL2OperationsForm") SolutionL2OperationsForm solutionL2OperationsForm,
			HttpSession session) {
		
		try {
			Integer solDimentionAttId =solutionL2OperationsForm.getSolutionDimentionAttId();
			Integer solId = getSessionSolutionId(session);
			Integer oppScopeId = solutionL2OperationsForm.getOpportunityScopeId();
			
			volumetricSolutionService.saveSolutionL2Operations(solId,
						solutionL2OperationsForm, solDimentionAttId, oppScopeId);
			
			solutionL2OperationsForm = volumetricSolutionService
					.loadSolutionL2OperationsDetails(solId,
							solDimentionAttId);
			solutionL2OperationsForm.setOpportunityScopeId(oppScopeId);
				
			model.addAttribute("supportWindowList",getSupportWindowList());
			model.addAttribute("scopeList",ApplicationPropertiesUtil
					.getMapConfigKeyValue(VOLSOL_SCOPE_List));
			model.addAttribute("solutionL2OperationsForm", solutionL2OperationsForm);
		} catch (MSSPException e) {
			// TODO Auto-generated catch block
			logger.error(
					"There is a error while saving define scope data into database",
					e);
			e.printStackTrace();
		}

		return "solutionL2Operations";

	}
    
    
	@RequestMapping(value = "/solution/saveSolutionL3Operations", method = RequestMethod.POST)
	public String saveSolutionL3Operations(
			Model model,
			@ModelAttribute("solutionL3OperationsForm") SolutionL3OperationsForm solutionL3OperationsForm,
			HttpSession session) {
		
		try {	
			Integer solDimentionAttId =solutionL3OperationsForm.getSolutionDimentionAttId();
			Integer solutionId = getSessionSolutionId(session);
			Integer oppScopeId = solutionL3OperationsForm.getOpportunityScopeId();
			
			volumetricSolutionService.saveSolutionL3Operations(solutionId,
						solutionL3OperationsForm, solDimentionAttId, oppScopeId);
			
			solutionL3OperationsForm = volumetricSolutionService
					.loadSolutionL3OperationsDetails(solutionId,
							solDimentionAttId);
			solutionL3OperationsForm.setOpportunityScopeId(oppScopeId);
			
			model.addAttribute("supportWindowList",getSupportWindowList());
			model.addAttribute("scopeList",ApplicationPropertiesUtil
					.getMapConfigKeyValue(VOLSOL_SCOPE_List));
		
			model.addAttribute("unitL3List",ApplicationPropertiesUtil
					.getMapConfigKeyValue(VOLSOL_UNITL3_List,true));
			
			model.addAttribute("solutionL3OperationsForm", solutionL3OperationsForm);
			
		} catch (MSSPException e) {
			// TODO Auto-generated catch block
			logger.error(
					"There is a error while saving define scope data into database",
					e);
			e.printStackTrace();
		}

		return "solutionL3Operations";

	}
	
	@RequestMapping(value = "/solution/saveSolutionEnhanDev", method = RequestMethod.POST)
	public String saveSolutionEnhanDev(
			Model model,
			@ModelAttribute("enhancementAndDevForm") EnhancementAndDevForm enhancementAndDevForm,
			HttpSession session) {
			
		Integer dimensionId = enhancementAndDevForm.getSolutionDimentionAttId();
		
		try {
			Integer solutionId = getSessionSolutionId(session);
			Integer oppScopeId = enhancementAndDevForm.getOpportunityScopeId();
			
			volumetricSolutionService.saveSolutionEnhanDev(solutionId,
					enhancementAndDevForm, dimensionId, oppScopeId);
			
			enhancementAndDevForm = volumetricSolutionService
					.loadSolutionEnhancementAndDevDetails(solutionId,
							dimensionId);
			enhancementAndDevForm.setOpportunityScopeId(oppScopeId);
			model.addAttribute("duritionUnitList", ApplicationPropertiesUtil
					.getMapConfigKeyValue(VOLSOL_DURUNIT_List));
			model.addAttribute("enhancementAndDevForm", enhancementAndDevForm);
			
		} catch (MSSPException e) {
			// TODO Auto-generated catch block
			logger.error(
					"There is a error while saving define scope data into database",
					e);
			e.printStackTrace();
		}

		return "solutionRICEFW";

	}
	
	@RequestMapping(value = "/solution/saveSolutionEnhanDevFC", method = RequestMethod.POST)
	public String saveSolutionEnhanDevFC(
			Model model,
			@ModelAttribute("enhancementAndDevForm") EnhancementAndDevForm enhancementAndDevForm,
			HttpSession session) {
			
		Integer dimensionId = enhancementAndDevForm.getSolutionDimentionAttId();
		
		try {
			Integer solutionId = getSessionSolutionId(session);
			Integer oppScopeId = enhancementAndDevForm.getOpportunityScopeId();
			
			volumetricSolutionService.saveSolutionEnhanDev(solutionId,
					enhancementAndDevForm, dimensionId, oppScopeId);
			
			enhancementAndDevForm = volumetricSolutionService
					.loadSolutionEnhancementAndDevDetails(solutionId,
							dimensionId);
			enhancementAndDevForm.setOpportunityScopeId(oppScopeId);
			model.addAttribute("duritionUnitList", ApplicationPropertiesUtil
					.getMapConfigKeyValue(VOLSOL_DURUNIT_List));
			model.addAttribute("enhancementAndDevForm", enhancementAndDevForm);
			
			model.addAttribute("unitFCList", ApplicationPropertiesUtil
					.getMapConfigKeyValue(VOLSOL_UNIT_FC_LIST,true));
			
		} catch (MSSPException e) {
			// TODO Auto-generated catch block
			logger.error(
					"There is a error while saving define scope data into database",
					e);
			e.printStackTrace();
		}

		return "solutionFixedCapacity";

	}

   
	@RequestMapping(value = "/solution/saveMiscellaneous")
	public String saveMiscellaneous(
			HttpSession session,
			Model model,
			@ModelAttribute("solutionOtherMiscForm") SolutionOtherMiscForm solutionOtherMiscForm) {
		logger.info("saving miscelaneous data");
		try {
			Integer solId = getSessionSolutionId(session);
			Integer dimensionId = solutionOtherMiscForm.getSolutionDimentionAttId();
			Integer oppScopeId = solutionOtherMiscForm.getOpportunityScopeId();
			
				volumetricSolutionService.saveMiscData(solutionOtherMiscForm,
						solId, dimensionId, oppScopeId);
				
				List<SolutionOtherMiscDTO> otherMiscDTOList = volumetricSolutionService
						.loadOtherMiscDTO(solId, dimensionId);
				if (otherMiscDTOList.size() > 0) {
					solutionOtherMiscForm
							.setSolutionOtherMiscList(otherMiscDTOList);
				}
				solutionOtherMiscForm.setOpportunityScopeId(oppScopeId);
				
				   model.addAttribute("jobRoleDTOList", getJobRoleList());
				   model.addAttribute("solutionOtherMiscForm", solutionOtherMiscForm);
				   
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(
					"There is a error while saving define scope data into database",
					e);
			e.printStackTrace();
		}
		logger.info("misc data saved successfuly : ");
		return "miscellaneous";
	}
    
	 @RequestMapping(value="/solution/saveGovernanceAndPmo")
	    public String saveGovernancePmo(
				HttpSession session,
				Model model,
				@ModelAttribute("solutionOtherMiscForm") SolutionOtherMiscForm solutionGovPMOForm)
	    {
	    	logger.info("inside save governance");
	    	try {
				Integer solId = getSessionSolutionId(session);
				Integer dimensionId = solutionGovPMOForm.getSolutionDimentionAttId();
				Integer oppScopeId = solutionGovPMOForm.getOpportunityScopeId();
				
					volumetricSolutionService.saveGovernancePmo(solutionGovPMOForm,
							solId, dimensionId, oppScopeId);
					
					List<SolutionOtherMiscDTO> GovAndPMODTOList = volumetricSolutionService
							.loadGovAndPMODTO(solId, dimensionId);
					if (GovAndPMODTOList.size() > 0) {
						solutionGovPMOForm
								.setSolutionOtherMiscList(GovAndPMODTOList);
					}
					solutionGovPMOForm.setOpportunityScopeId(oppScopeId);
					   model.addAttribute("jobRoleDTOList", getJobRoleList());
					   
					   model.addAttribute("solutionGovPMOForm", solutionGovPMOForm);
						 
					logger.info("governance data saved successfuly : ");	
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error(
						"There is a error while saving define scope data into database",
						e);
			}
			
	    	return "projGovernancePMO";
	    }
	public Map< Integer, String> getJobRoleList()
    {
	List<JobRole> listJobRole = new ArrayList<JobRole>();
			listJobRole = solutionService.getAllJobRole();
			Map< Integer, String> mapJobRole = new HashMap<Integer, String>();
			for (JobRole jobRole : listJobRole) {
				mapJobRole.put(jobRole.getJobRoleId(), jobRole.getRole());
				
			}
  return mapJobRole;
    }
	
    public List<JobRoleDTO> getJobRoleList()
    {
    	List<JobRoleDTO> jobRoleDTOs = new ArrayList<JobRoleDTO>();
    	jobRoleDTOs =  solutionService.getJobRoleList();
    	return jobRoleDTOs;
    }
	
    *//**
     * 
     * Description	: TODO
     * Method Name	: getSupportWindowList
     * Input& Output:
     * 	@return
     * 	@throws MSSPException
     *//*
    private List <SupportWindowMatrixDTO> getSupportWindowList() throws MSSPException {
    	List<SupportWindowMatrixDTO> supportWindowList = volumetricSolutionService.getAllSupportWindowMatrix();
    	return supportWindowList;
    }
	
    @RequestMapping(value = "/solution/proceesAPA")
	public String saveMiscellaneous(
			HttpSession session,
			Model model){
    
    
    	return "redirect:../solution/APAnalysis";
    }
    
    private List<EffLeverDTO> createDtoList(List<EffLeverDTO> list) 
	{
		List<EffLeverDTO> dtoList = new ArrayList<EffLeverDTO>();
		for(EffLeverDTO obj : list)
		{
			while(obj.getTestType().equalsIgnoreCase("Regression"))
			{
				dtoList.add(obj);
				break;
			}
			while(obj.getTestType().equalsIgnoreCase("New_Functionality"))
			{
				dtoList.add(obj);
				break;
			}
			while(obj.getTestType().equalsIgnoreCase("UAT_Prod_Go_Live"))
			{
				dtoList.add(obj);
				break;
			}
		}
		return dtoList;
	}*/
}
