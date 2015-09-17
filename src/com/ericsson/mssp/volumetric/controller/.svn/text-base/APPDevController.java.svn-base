package com.ericsson.mssp.volumetric.controller;

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

import com.ericsson.mssp.common.dto.BuildDTO;
import com.ericsson.mssp.common.dto.DemandSupportDTO;
import com.ericsson.mssp.common.dto.DeploymentRollOutDTO;
import com.ericsson.mssp.common.dto.DesignAndBuildDTO;
import com.ericsson.mssp.common.dto.DesignDTO;
import com.ericsson.mssp.common.dto.OpportunityScopeDTO;
import com.ericsson.mssp.common.dto.SolutionDTO;
import com.ericsson.mssp.common.dto.PostReleaseActivitiesDTO;
import com.ericsson.mssp.common.exception.MSSPException;
import com.ericsson.mssp.solution.controller.BaseController;
import com.ericsson.mssp.volumetric.dao.VolumetricDAO;
import com.ericsson.mssp.solution.service.ISolutionService;
import com.ericsson.mssp.volumetric.forms.BuildForm;
import com.ericsson.mssp.volumetric.forms.DemandSupportForm;
import com.ericsson.mssp.volumetric.forms.DeploymentRollOutForm;
import com.ericsson.mssp.volumetric.forms.DesignAndBuildForm;
import com.ericsson.mssp.volumetric.forms.DesignForm;
import com.ericsson.mssp.volumetric.forms.PostReleaseActivitiesForm;
import com.ericsson.mssp.volumetric.service.VolumetricService;

@Controller
public class APPDevController extends BaseController{
	
	@Autowired
	private VolumetricService volumetricService;
	
	@Autowired
	private ISolutionService solutionService;
	
	public static Logger logger = Logger.getLogger(APPDevController.class);

	private static final String serviceElement = "APP_DEV";
	
	@RequestMapping(value = "/solution/volumetricAppDev")
    public String serviceFunctions(ModelMap model, HttpSession session){
		logger.info("TESTING volumetric service functions for ["+serviceElement+"]");
		
		DemandSupportForm demandSupportForm = new DemandSupportForm();
		DesignAndBuildForm designBuildForm = new DesignAndBuildForm();
		/*DesignForm designForm = new DesignForm();
		BuildForm buildForm = new BuildForm();*/
		DeploymentRollOutForm deploymentRollOutForm = new DeploymentRollOutForm();
		PostReleaseActivitiesForm postRelActForm = new PostReleaseActivitiesForm();
		
		Integer oppId = getSessionOpportunityId(session);
		
		Integer solutionId = getSessionSolutionId(session);
		String utilizationPerYear = (String) session.getAttribute(UTILIZATION_PER_YEAR);
		try {
			
			List<OpportunityScopeDTO> serviceScopeList = volumetricService.getServiceScopeByServiceElement((Integer)session.getAttribute(OPPORTUNITY_ID), serviceElement);
			for (OpportunityScopeDTO oppScope : serviceScopeList) {
				Integer serviceScopeID = oppScope.getServiceScopeDTO().getServiceScopeId();
				switch (serviceScopeID) {				
				case 18:
					//Demand Support
					demandSupportForm.setOpportunityScopeId(oppScope.getOpportunityScopeId());
					DemandSupportDTO demandSupportDTO  = volumetricService.loadDemandSupport(solutionId,serviceScopeID);
					demandSupportForm.setDemandSupportDTO(demandSupportDTO);
					break;
					
				case 19:
					// DesignAndBuild
					DesignAndBuildDTO designBuildDTO = volumetricService.loadDesignAndBuild(solutionId,serviceScopeID);
					designBuildForm.setDesignBuildDTO(designBuildDTO);
					designBuildForm.setOpportunityScopeID(oppScope.getOpportunityScopeId());
					break;
					
				case 20: break;
				
				case 21:
					// Deployment Roll Out
					deploymentRollOutForm.setOpportunityScopeID(oppScope.getOpportunityScopeId());
					DeploymentRollOutDTO deploymentRollOutDTO = volumetricService.loadDeploymentRollOutDTO(solutionId,serviceScopeID);					
					deploymentRollOutForm.setDeploymentRollOutDTO(deploymentRollOutDTO);
					break;
					
				case 22:
					// Post Release Activities
					postRelActForm.setOpportunityScopeID(oppScope.getOpportunityScopeId());
					PostReleaseActivitiesDTO postRelActDTO = volumetricService.loadPostRelActDTO(solutionId,serviceScopeID);	
					postRelActForm.setPostRelActivityDTO(postRelActDTO);
					break;
					
				}
			}
			
			// Service Bucket table data
			List<String> serviceBucketData = solutionService
				.loadServiceBucketDataByOppSolutionID(oppId, solutionId);
			model.addAttribute("serviceBucketData", serviceBucketData);
			
			model.addAttribute("demandSupportForm",demandSupportForm);
			model.addAttribute("designAndBuildForm", designBuildForm);
			/*model.addAttribute("designForm", designForm);
			model.addAttribute("buildForm", buildForm);*/
			model.addAttribute("deploymentRollOutForm",deploymentRollOutForm);
			model.addAttribute("postReleaseActivitiesForm",postRelActForm);
			
			model.put("selectedTab", serviceElement);
			model.put("serviceScopeList",serviceScopeList);
			model.put(UTILIZATION_PER_YEAR, utilizationPerYear);
			System.out.println("UTILIZATION_PER_YEAR = "+ utilizationPerYear);
			
		} catch (MSSPException e) {
			logger.error("There is a error while loading Adm Support data",e);
			e.printStackTrace();
		}
    	return "volumetric";
    }
	
	@RequestMapping(value = "/solution/saveDemandSupport", method = RequestMethod.POST)
	public String saveDemandSupport(Model model, @ModelAttribute("demandSupportForm") DemandSupportForm demandSupportForm, 
						HttpSession session){
		
		try{
			
			Integer solutionId = getSessionSolutionId(session);
			Integer OppScopeId = demandSupportForm.getOpportunityScopeId();
			DemandSupportDTO demandSupportDTO = demandSupportForm.getDemandSupportDTO();
			System.out.println("demandSupportID = "+demandSupportDTO.getDemandSupportID());
			volumetricService.saveDemandSupport(demandSupportDTO,solutionId,OppScopeId);
			
			demandSupportDTO = volumetricService.loadDemandSupport(solutionId, null);
			demandSupportForm.setDemandSupportDTO(demandSupportDTO);
			model.addAttribute("demandSupportForm",demandSupportForm);
			
		}catch(Exception e){
			logger.error("There is a error while saving Demand Support data into database",e);
			e.printStackTrace();
		}
		return "demandSupport";
	}
	
	@RequestMapping(value="/solution/saveDesignBuild", method=RequestMethod.POST)
	public String saveDesignBuild(Model model, @ModelAttribute("designAndBuildForm") DesignAndBuildForm designBuildForm,
										HttpSession session){
		try {
		DesignAndBuildDTO designAndBuildDTO = designBuildForm.getDesignBuildDTO();
		Integer solutionID = getSessionSolutionId(session);
		Integer oppScopeID = designBuildForm.getOpportunityScopeID();		
		volumetricService.saveDesignBuild(solutionID,oppScopeID,designAndBuildDTO);
		
		designAndBuildDTO = volumetricService.loadDesignAndBuild(solutionID, null);
		designBuildForm.setDesignBuildDTO(designAndBuildDTO);
		model.addAttribute("designAndBuildForm", designBuildForm);
		
		} catch (MSSPException e) {
			logger.error("There is a error while saving DesignAndBuild Data into database",e);
			e.printStackTrace();
		}
		return "designBuild";
	}
	
	/*@RequestMapping(value="/solution/saveDesign", method = RequestMethod.POST)
	public String saveDesign(Model model, @ModelAttribute("designForm") DesignForm designForm,
								HttpSession session){
		
		
		try {
			DesignDTO designDTO = designForm.getDesignDTO();
			volumetricService.saveDesign(designDTO);
		} catch (MSSPException e) {
			logger.error("There is a error while saving Design Data into database",e);
			e.printStackTrace();
		
		}
		
		return "design";
	}
	
	@RequestMapping(value="/solution/saveBuild", method = RequestMethod.POST)
	public String saveBuild(@ModelAttribute("buildForm") BuildForm buildForm , HttpSession session){
		
		Integer solutionId = getSessionSolutionId(session);
		BuildDTO buildDTO = buildForm.getBuildDTO();
		
		try {
			volumetricService.saveBuild(buildDTO,solutionId);
		} catch (MSSPException e) {
			logger.error("There is a error while saving Build Data into database",e);
			e.printStackTrace();
		}
		return "build";
	}*/
	
	@RequestMapping(value="/solution/saveDeploymentRollOut", method=RequestMethod.POST)
	public String saveDeploymentRollOut(Model model,@ModelAttribute("deploymentRollOutForm") DeploymentRollOutForm deploymentAttributeForm,
									HttpSession session){
		try {
		Integer solutionID = getSessionSolutionId(session);
		Integer oppScopeID = deploymentAttributeForm.getOpportunityScopeID();
		DeploymentRollOutDTO deploymentRollOutDTO = deploymentAttributeForm.getDeploymentRollOutDTO();
		volumetricService.saveDeploymentRollOut(deploymentRollOutDTO, solutionID, oppScopeID);
		
		deploymentRollOutDTO = volumetricService.loadDeploymentRollOutDTO(solutionID, null);
		deploymentAttributeForm.setDeploymentRollOutDTO(deploymentRollOutDTO);
		model.addAttribute("deploymentRollOutForm",deploymentAttributeForm);
		
		} catch (MSSPException e) {
			logger.error("There is a error while saving Deployment Roll Out Data into database",e);
			e.printStackTrace();
		}
		return "deploymentRollOut";
	}
	
	@RequestMapping(value="/solution/savePostReleaseActivity", method=RequestMethod.POST)
	public String savePostReleaseActivity(Model model, @ModelAttribute("postReleaseActivitiesForm") PostReleaseActivitiesForm postRelActForm,
									HttpSession session){
		try {
		Integer solutionID = getSessionSolutionId(session);
		Integer oppScopeID = postRelActForm.getOpportunityScopeID();
		PostReleaseActivitiesDTO postRelActDTO = postRelActForm.getPostRelActivityDTO();		
		volumetricService.savePostReleaseActivity(postRelActDTO, solutionID, oppScopeID);
		
		postRelActDTO = volumetricService.loadPostRelActDTO(solutionID, null);
		postRelActForm.setPostRelActivityDTO(postRelActDTO);
		model.addAttribute("postReleaseActivitiesForm",postRelActForm);
		} catch (MSSPException e) {
			logger.error("There is a error while saving Post Release Activity Data into database",e);
			e.printStackTrace();
		}
		return "postReleaseActivity";
	}
										
}
