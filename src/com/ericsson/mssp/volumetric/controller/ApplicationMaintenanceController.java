package com.ericsson.mssp.volumetric.controller;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpSession;
import javax.swing.text.html.FormSubmitEvent.MethodType;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ericsson.mssp.common.constant.MSSPConstants;
import com.ericsson.mssp.common.dto.AccessManagementDTO;
import com.ericsson.mssp.common.dto.AppMainSlaDTO;
import com.ericsson.mssp.common.dto.AppMainSupportActivityDTO;
import com.ericsson.mssp.common.dto.ConfigurationManagementDTO;
import com.ericsson.mssp.common.dto.E2EProcessQualityDTO;
import com.ericsson.mssp.common.dto.OpportunityComponentDTO;
import com.ericsson.mssp.common.dto.OpportunityDTO;
import com.ericsson.mssp.common.dto.OpportunityScopeDTO;
import com.ericsson.mssp.common.dto.ProductEstimationBaseEffortForSolutionDTO;
import com.ericsson.mssp.common.dto.ServiceAssuranceDTO;
import com.ericsson.mssp.common.dto.ServiceScopeAppMainDataBean;
import com.ericsson.mssp.common.dto.SolutionApproachDimensionDTO;
import com.ericsson.mssp.common.dto.SolutionServiceDeskDTO;
import com.ericsson.mssp.common.dto.SupportWindowMatrixDTO;
import com.ericsson.mssp.common.dto.TicketDistributionDTO;
import com.ericsson.mssp.common.entity.OpportunityDetail;
import com.ericsson.mssp.common.entity.ProductEstimationBaseEffortForSolution;
import com.ericsson.mssp.common.exception.MSSPException;
import com.ericsson.mssp.common.util.ApplicationPropertiesUtil;
import com.ericsson.mssp.solution.controller.BaseController;
import com.ericsson.mssp.solution.forms.ServiceDeskForm;
import com.ericsson.mssp.solution.forms.SolutionL1OperationsForm;
import com.ericsson.mssp.solution.forms.SolutionL2OperationsForm;
import com.ericsson.mssp.solution.forms.SolutionL3OperationsForm;
import com.ericsson.mssp.solution.forms.VolumetricSolutionForm;
import com.ericsson.mssp.solution.service.ISolutionService;
import com.ericsson.mssp.solution.service.ITaasService;
import com.ericsson.mssp.solution.service.IVolumetricSolutionService;
import com.ericsson.mssp.taas.dto.EffLeverDTO;
import com.ericsson.mssp.volumetric.forms.AccessManagementForm;
import com.ericsson.mssp.volumetric.forms.AppMainSlaForm;
import com.ericsson.mssp.volumetric.forms.ApplicationMaintenenceOutput;
import com.ericsson.mssp.volumetric.forms.ServiceAssuranceForm;
import com.ericsson.mssp.volumetric.forms.SupportActivityForm;
import com.ericsson.mssp.volumetric.forms.TicketDistributionForm;
import com.ericsson.mssp.volumetric.service.ProductVolumetricService;
import com.ericsson.mssp.volumetric.service.VolumetricService;

/**
 * @author egaivij
 *
 */
@Controller
public class ApplicationMaintenanceController extends BaseController implements MSSPConstants{
	
	@Autowired
	private ISolutionService solutionService;
	
	@Autowired
	private IVolumetricSolutionService volumetricSolutionService;
	
	@Autowired
	private ITaasService itaasService;
	
	@Autowired
	private VolumetricService volumetricService;
	
	@Autowired
	private ProductVolumetricService productEstimationService;
	
	private static final Log logger = LogFactory.getLog(ApplicationMaintenanceController.class);
	
	private static final String serviceElement = "APP_MAIN";
	
	private Map<String,Double> totalBaseHrsMap = null;
	private Map<String,Double> totalBaseFteMap = null;
	
	@InitBinder
	 public void initBinder(WebDataBinder binder)
	 {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    dateFormat.setLenient(false);

	    // true passed to CustomDateEditor constructor means convert empty String to null
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	 }
	
	@RequestMapping(value="/solution/applicationMaintenance")
	public String applicationMaintenance(Model model, HttpSession session, 
			@ModelAttribute("volumetricSolutionForm") VolumetricSolutionForm volumetricSolutionForm)
	{	
	
		Integer oppId = getSessionOpportunityId(session);
		Integer solId = getSessionSolutionId(session);
		
		String utilizationPerYear = (String)session.getAttribute(UTILIZATION_PER_YEAR);
		
		ServiceDeskForm serviceDeskForm = new ServiceDeskForm();
		SolutionL1OperationsForm solutionL1OperationsForm = new SolutionL1OperationsForm();
		SolutionL2OperationsForm solutionL2OperationsForm = new SolutionL2OperationsForm();
		SolutionL3OperationsForm solutionL3OperationsForm = new SolutionL3OperationsForm();
		E2EProcessQualityDTO  e2EProcessQualityDTO = new E2EProcessQualityDTO();
		AccessManagementForm accessManagementForm = new AccessManagementForm();
	    ServiceAssuranceForm serviceAssuranceForm = new ServiceAssuranceForm();  
		//TaasForm taasForm = new TaasForm();
		
		OpportunityDTO opportunityDTO= solutionService.getOpportunity(oppId);
		
		try {
			//Map<String, List<SolutionInputDefinitionDTO>> solInputFieldsListMap = new HashMap<String, List<SolutionInputDefinitionDTO>>();
			Integer solDimentionAttId = volumetricSolutionForm.getDimensionAttributeId();
		
 			//List <OpportunityScopeDTO> selServiceScopeList = solutionService.loadAllOpportunityScopesByOppotunityId(oppId);
			
			List<OpportunityScopeDTO> selServiceScopeList = volumetricService.getServiceScopeByServiceElement((Integer)session.getAttribute(OPPORTUNITY_ID),serviceElement);
		       
 		
 			List <SolutionApproachDimensionDTO> dimList = solutionService.getDimentionListBySolutionId(solId);
			
			if(dimList.size()>0 && solDimentionAttId == null){
				solDimentionAttId=dimList.get(0).getSolutionApproachDimensionId();
			}

			for (OpportunityScopeDTO oppScope : selServiceScopeList) {
				Integer serviceScopeID = oppScope.getServiceScopeDTO().getServiceScopeId();
				switch (serviceScopeID) {
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
				case 17:
					//Access Management
					accessManagementForm.setOpportunityScopeId(oppScope.getOpportunityScopeId());
					AccessManagementDTO accessManagementDTO = volumetricService
							.loadAccessManagementBySolutionId(solId);
					accessManagementForm.setAccessManagementDTO(accessManagementDTO);
									
					break;
				
				case 23:
					//Service Assurance
					serviceAssuranceForm.setOpportunityScopeId(oppScope.getOpportunityScopeId());
					ServiceAssuranceDTO serviceAssuranceDTO = volumetricService.loadServiceAssurance(solId,serviceScopeID);
					serviceAssuranceForm.setServiceAssuranceDTO(serviceAssuranceDTO);
									
					break;
	    
				case 24:
					
					e2EProcessQualityDTO = volumetricSolutionService
							.loadE2EProcessQualityDTO(solId);
					//solutionL2OperationsForm.setOpportunityScopeId(oppScope.getOpportunityScopeId());
					OpportunityScopeDTO opDTO = new OpportunityScopeDTO();
					opDTO.setOpportunityScopeId(oppScope.getOpportunityScopeId());
					e2EProcessQualityDTO.setOpportunityScopeDTO(opDTO);
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
			
			model.addAttribute("utilizationPerYear", utilizationPerYear);
			
			model.addAttribute("accessManagementForm", accessManagementForm);
			model.addAttribute("serviceAssuranceForm",serviceAssuranceForm);
			model.addAttribute("solutionL1OperationsForm",solutionL1OperationsForm);
			model.addAttribute("solutionL2OperationsForm", solutionL2OperationsForm);
			model.addAttribute("solutionL3OperationsForm", solutionL3OperationsForm);
			model.addAttribute("e2EProcessQualityDTO", e2EProcessQualityDTO);
			
			model.addAttribute("opportunityDTO",opportunityDTO);
			   
		    
		    
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
		
		
		model.addAttribute("selectedTab", serviceElement);
		return "volumetric";
	
		
	
	
	}
	
	@RequestMapping(value="/solution/applicationMaintenanceNew")
	public String applicationMaintenanceNew(Model model, HttpSession session, 
			@ModelAttribute("volumetricSolutionForm") VolumetricSolutionForm volumetricSolutionForm)
	{
		
		logger.info("!!!Welcome to Application Maintenance!!!");
		// 1. get the components for this opportunity from OpportunityComponent table and display them as module name list.
		try{
		boolean isAppMainSelected = true;
		Integer oppId = getSessionOpportunityId(session);
		Integer solId = getSessionSolutionId(session);
		logger.info("OpportunityId = "+oppId);
		logger.info("SolutionId = "+solId);
		
		//check if user has edit access 
		String hasEditSolAccess = (String) getSessionValueFromKey(session,
				HAS_EDIT_SOL_ACCESS);
		model.addAttribute("hasEditSolAccess",
				hasEditSolAccess == null ? true : hasEditSolAccess);
				
		// Service Bucket Data.
		List<String> serviceBucketData = solutionService.loadServiceBucketDataByOppSolutionID(oppId,solId);
		model.addAttribute("serviceBucketData", serviceBucketData);
		List<OpportunityScopeDTO> opportunityScopeList = volumetricService.getServiceScopeByServiceElement((Integer)session.getAttribute(OPPORTUNITY_ID), serviceElement);
		if(null != opportunityScopeList && opportunityScopeList.size() > 0){
		
		// -------------For Ticket Distribution Start --------------//
		
			List<TicketDistributionDTO> tktDistList = volumetricService.getTktDistByOppScopeIds(opportunityScopeList, serviceElement);
			
			TicketDistributionForm tktDistForm = new TicketDistributionForm();
			tktDistForm.setTktDistList(tktDistList);
		
		
		// -------------For Ticket Distribution End --------------//
		
		// ---------- For SLA Data Start ------------- //
		List<AppMainSlaDTO> appMainSlaDtoList = volumetricService.getAppMainSlaDtoByOppId(oppId);
		
		AppMainSlaForm appMainSlaForm = new AppMainSlaForm();
		appMainSlaForm.setList(appMainSlaDtoList);
		// ---------- For SLA Data End ------------- //
		
	
		
		// ------------- For Support Activity Start ---------------//
		List<OpportunityComponentDTO> components = solutionService.getComponentsByOpportunityID(oppId);
		List<AppMainSupportActivityDTO> suppActList = volumetricService.getSuppActListByOppCompIds(components);
		List<SupportWindowMatrixDTO> suppWindMatrixList = volumetricService.getAllSupportWindowMatrix();
		
		SupportActivityForm supportActivityForm = new SupportActivityForm();
		supportActivityForm.setAppMainActivityDTOList(suppActList);
		// -------------- For Support Activity End ---------------// 
		
		
		
		
		model.addAttribute("appMainSlaForm",appMainSlaForm);
		model.addAttribute("tktDistForm",tktDistForm);
		model.addAttribute("supportActivityForm",supportActivityForm);
		model.addAttribute("suppWindMatrixList", suppWindMatrixList);
		model.addAttribute("selectedTab", serviceElement);
		model.addAttribute("selectedInnerTab", "APP_MAIN_INPUT");
		model.addAttribute("oppID",oppId);
		model.addAttribute("isAppMainSelected", isAppMainSelected);
		
		// For Common Input part.
		LinkedHashMap<String, LinkedHashMap<String, Integer>> paramMap = productEstimationService.getProductEstimationAuxiliaryParamsWrapper(solId);
		OpportunityDetail opportunityDetail = volumetricService.getOpportunityDetail(oppId);
		model.addAttribute("steadyStateDuration",(opportunityDetail != null ? opportunityDetail.getSteadyStateDuration() : 0));
		paramMap = productEstimationService.getProductEstimationAuxiliaryParamsWrapper(solId);
		model.addAttribute("paramMap", paramMap);
		
		}else{
			model.addAttribute("isAppMainSelected", false);
			model.addAttribute("selectedTab", serviceElement);
		}
		
		}catch(MSSPException e){
			logger.error("Error in /solution/applicationMaintenanceNew::", e);
			e.printStackTrace();
		}
		
		return "volumetric";
	}
	
	@RequestMapping(value = "/solution/saveAppMainSupportActivity")
	public String saveAppMainSupportActivity(Model model, @ModelAttribute("supportActivityForm") SupportActivityForm form){
		logger.info("Saving Support Activity Data");
		try {
			List<AppMainSupportActivityDTO> list = form.getAppMainActivityDTOList();
			for(AppMainSupportActivityDTO dto : list){
				volumetricService.saveAppMainSupportActivity(dto);
			}
			model.addAttribute("message", ApplicationPropertiesUtil.getProperty("msg.common.datasave.success"));
		} catch (MSSPException e) {
			logger.error("Error in /solution/saveAppMainSupportActivity::", e);
			model.addAttribute("errorMessage", ApplicationPropertiesUtil.getProperty("msg.common.datasave.error"));
			e.printStackTrace();
		}
		return "forward:/solution/applicationMaintenanceNew";
	}
	
	@RequestMapping(value = "/solution/saveAppMainSLA")
	public String saveAppMainSLA(Model model, @ModelAttribute("appMainSlaForm")AppMainSlaForm form) {
		logger.info("Saving SLA Data");
		try {
			List<AppMainSlaDTO> list = form.getList();
			for(AppMainSlaDTO dto : list){
				volumetricService.saveAppMainSLA(dto);
			}
			model.addAttribute("message", ApplicationPropertiesUtil.getProperty("msg.common.datasave.success"));
		} catch (MSSPException e) {
			logger.error("Error in /solution/saveAppMainSLA::", e);
			model.addAttribute("errorMessage", ApplicationPropertiesUtil.getProperty("msg.common.datasave.error"));
			e.printStackTrace();
		}
		return "forward:/solution/applicationMaintenanceNew";
	}
	
	
	@RequestMapping(value = "/solution/saveTicketDist")
	public String saveTicketDistribution(Model model, @ModelAttribute("tktDistForm") TicketDistributionForm form){
		logger.info("Saving Ticket Distribution Data");
		try {
			List<TicketDistributionDTO> myList = form.getTktDistList();
			for(TicketDistributionDTO dto : myList){
				volumetricService.saveTicketDistribution(dto);
			}
			model.addAttribute("message", ApplicationPropertiesUtil.getProperty("msg.common.datasave.success"));
		} catch (MSSPException e) {
			logger.error("Error in /solution/saveTicketDist::", e);
			model.addAttribute("errorMessage", ApplicationPropertiesUtil.getProperty("msg.common.datasave.error"));
			e.printStackTrace();
		}
		return "forward:/solution/applicationMaintenanceNew";
		
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
	
	
	
    /**
     * 
     * Description	: TODO
     * Method Name	: getSupportWindowList
     * Input& Output:
     * 	@return
     * 	@throws MSSPException
     */
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
	}
    
    @RequestMapping(value = "/solution/saveE2EProcessQuality", method = RequestMethod.POST)
	public String saveE2EProcessQuality(
			Model model,
			@ModelAttribute("e2EProcessQualityDTO") E2EProcessQualityDTO e2EProcessQualityDTO,
			HttpSession session) {
		
		try {	
			Integer solutionId = getSessionSolutionId(session);
			OpportunityScopeDTO opDTO  = e2EProcessQualityDTO.getOpportunityScopeDTO();
			
			volumetricSolutionService.saveE2EProcessQuality(solutionId,e2EProcessQualityDTO);
			
			e2EProcessQualityDTO = volumetricSolutionService
					.loadE2EProcessQualityDTO(solutionId);
			e2EProcessQualityDTO.setOpportunityScopeDTO(opDTO);
			
			
		}catch(Exception e){
			logger.error(
					"There is a error while saving E2EProcessQualityForm data into database",
					e);
			e.printStackTrace();
		}
		return "e2EProcessQuality";
    }
    
    
    @RequestMapping(value = "/solution/saveAccessManagement", method = RequestMethod.POST)
   	public String saveAccessManagement(Model model,
   			@ModelAttribute("accessManagement") AccessManagementForm accessManagementForm,
   			HttpSession session) {
   		try {
   			
   			Integer solutionId = getSessionSolutionId(session);
   			Integer solDimentionAttId = accessManagementForm
   					.getSolutionDimentionAttId();
   			AccessManagementDTO accessManagementDTO = accessManagementForm
   					.getAccessManagementDTO();
   			Integer OppScopeId = accessManagementForm.getOpportunityScopeId();
   			
   			volumetricService.saveAccessManagement(accessManagementDTO,
   					solutionId,solDimentionAttId,OppScopeId);

   			accessManagementDTO = volumetricService
   					.loadAccessManagementBySolutionId(solutionId);
   		
   			accessManagementForm.setAccessManagementDTO(accessManagementDTO);
   		
   			model.addAttribute("accessManagementForm", accessManagementForm);

   		} catch (MSSPException e) {
   			// TODO Auto-generated catch block
   			logger.error(
   					"There is a error while saving define scope data into database",
   					e);
   			e.printStackTrace();
   		}
   		return "accessManagement";

   	}
	
	@RequestMapping(value = "/solution/saveServiceAssurance", method = RequestMethod.POST)	
    public String saveServiceAssurance(Model model, @ModelAttribute("serviceAssuranceForm") ServiceAssuranceForm serviceAssuranceForm,
    		HttpSession session){
		try {
			
			Integer solutionId = getSessionSolutionId(session);
			Integer OppScopeId = serviceAssuranceForm.getOpportunityScopeId();
			ServiceAssuranceDTO serviceAssuranceDTO  = serviceAssuranceForm.getServiceAssuranceDTO();
			volumetricService.saveServiceAssurance(serviceAssuranceDTO,solutionId,OppScopeId);
			
			serviceAssuranceDTO = volumetricService.loadServiceAssurance(solutionId, null);
			serviceAssuranceForm.setServiceAssuranceDTO(serviceAssuranceDTO);
			model.addAttribute("serviceAssuranceForm", serviceAssuranceForm);

		} catch (MSSPException e) {
			logger.error("There is a error while saving Service Assurance data into database",
					e);
			e.printStackTrace();
		}
		return "serviceAssurance";

	}
	
	
	@RequestMapping("/solution/applicationMaintenanceView")
	public String reviewApplicationMaintenance(ModelMap model, HttpSession session){
		try {
			logger.info("Review Application Maintenance Data");	
			Integer oppId = getSessionOpportunityId(session);
			Integer solutionId = getSessionSolutionId(session);
			// Service Bucket Data.
			List<String> serviceBucketData = solutionService.loadServiceBucketDataByOppSolutionID(oppId,solutionId);
			model.addAttribute("serviceBucketData", serviceBucketData);
			
			List<ServiceScopeAppMainDataBean> servScopeData = new ArrayList<ServiceScopeAppMainDataBean>();
			// getting data from tables //
			
			List<AppMainSlaDTO> appMainSlaDtoList = volumetricService.getAppMainSlaDtoByOppId(oppId);
			
			List<OpportunityComponentDTO> components = solutionService.getComponentsByOpportunityID(oppId);
			List<AppMainSupportActivityDTO> suppActList = volumetricService.getSuppActListByOppCompIds(components);
			
			List<OpportunityScopeDTO> opportunityScopeList = volumetricService.getServiceScopeByServiceElement((Integer)session.getAttribute(OPPORTUNITY_ID), serviceElement);
			List<TicketDistributionDTO> tktDistList = volumetricService.getTktDistByOppScopeIds(opportunityScopeList, serviceElement);
			
			Double customerBaseFactor = volumetricService.getFactor(solutionId,"CustomerBase");
			Double cbioImpacted3ppNodesFactor = volumetricService.getFactor(solutionId,"CBIOImpacted3PPNodes");
			
			// Steady State Duration
			OpportunityDetail opportunityDetail = volumetricService.getOpportunityDetail(oppId);
			Integer steadyStateDuration = Integer.parseInt((opportunityDetail != null ? opportunityDetail.getSteadyStateDuration() : "0"));
			System.out.println("steadyStateDuration = "+steadyStateDuration);
			model.addAttribute("steadyStateDuration",steadyStateDuration);
			
			Map<String,List<Map<String,List<ApplicationMaintenenceOutput>>>> componentMap = createAppMainOutputObjects(appMainSlaDtoList,suppActList,tktDistList,customerBaseFactor,cbioImpacted3ppNodesFactor,steadyStateDuration);
			//displayMap(componentMap);
			logger.info(totalBaseHrsMap);
			logger.info(totalBaseFteMap);
			// saving data in ProductEstimationBaseEffortForSolution table.
			updateProductEstimationBaseEffortForSolution(solutionId);
			// populating data from ProductEstimationBaseEffortForSolution table.
			servScopeData = getProductEstimationBaseEffortForSolution(solutionId);
			
			
			model.addAttribute("componentMap",componentMap);
			model.addAttribute("selectedTab", serviceElement);
			model.addAttribute("selectedInnerTab", "APP_MAIN_REVIEW");
			model.addAttribute("servScopeData", servScopeData);
			model.addAttribute("isAppMainSelected", true);
			
			// For Common Input part.
			LinkedHashMap<String, LinkedHashMap<String, Integer>> paramMap = productEstimationService.getProductEstimationAuxiliaryParamsWrapper(solutionId);
			model.addAttribute("paramMap", paramMap);
			
			//check if user has edit access 
			String hasEditSolAccess = (String) getSessionValueFromKey(session,
					HAS_EDIT_SOL_ACCESS);
			model.addAttribute("hasEditSolAccess",
					hasEditSolAccess == null ? true : hasEditSolAccess);
			
		} catch (MSSPException e) {
			logger.error("Error in /solution/applicationMaintenanceView::", e);
			e.printStackTrace();
		}	
		
		return "volumetric";
	
	}
	
public Map<String,List<Map<String,List<ApplicationMaintenenceOutput>>>> createAppMainOutputObjects(List<AppMainSlaDTO> appMainSlaDtoList, List<AppMainSupportActivityDTO> suppActList, List<TicketDistributionDTO> tktDistList, Double customerBaseFactor , Double cbioImpacted3ppNodesFactor , Integer steadyStateDuration) throws MSSPException{
		
		totalBaseHrsMap = new HashMap<String,Double>();
		totalBaseFteMap = new HashMap<String,Double>();
		Map<String,List<Map<String,List<ApplicationMaintenenceOutput>>>> componentMap = new HashMap<String,List<Map<String,List<ApplicationMaintenenceOutput>>>>();
				
		for(AppMainSupportActivityDTO suppActDto : suppActList){
			double total = 0;
			//double totalBaseHrs = 0;
			//double totalBaseFte = 0;
			//String customerBase = "Above 5";
			//String cbioImpacted3ppNodes = "Above 3";
			List<Map<String,List<ApplicationMaintenenceOutput>>> mapList = new ArrayList<Map<String,List<ApplicationMaintenenceOutput>>>();
			String componentName = suppActDto.getOpportinityComponent().getComponent().getComponentName();
			float tktPerMonth = suppActDto.getTicketsPerMonth();
			for(TicketDistributionDTO tktDistDto : tktDistList){
				double servEleTotalBaseHrs = 0;
				double servEleTotalBaseFte = 0;
				Map<String,List<ApplicationMaintenenceOutput>> servScopeMap = new HashMap<String,List<ApplicationMaintenenceOutput>>();
				List<ApplicationMaintenenceOutput> outputList = new ArrayList<ApplicationMaintenenceOutput>();
				String servScopeName = tktDistDto.getOpportunityScope().getServiceScope().getServiceScopeName();
				float tktDist = tktDistDto.getPercentTicketDistribution();
				Integer servScopeId = tktDistDto.getOpportunityScope().getServiceScope().getServiceScopeId();
				Integer oppScopeId = tktDistDto.getOpportunityScope().getOpportunityScopeId();
				
				for(AppMainSlaDTO slaDto : appMainSlaDtoList){
					ApplicationMaintenenceOutput obj = new ApplicationMaintenenceOutput();
					float responseTime;
					if(servScopeId == 20){responseTime=slaDto.getLOneResponse();}
					else if (servScopeId == 21){responseTime=slaDto.getLTwoResponse();}
					else {responseTime=slaDto.getLThreeResponse();}
					String severity = slaDto.getSeverity();
					switch(severity){
					
					case Priority1 :  	obj.setPriority(Priority1);
									float totYearlyTktP1 = tktPerMonth*steadyStateDuration*(tktDist/100)*(slaDto.getTicketDistribution()/100);
									float baseEffhrsP1 = totYearlyTktP1*(responseTime);
									float baseEffPdP1 = baseEffhrsP1/8;
									double customerBaseFactorP1 = customerBaseFactor;//("Above 5".equals(customerBase)) ? 1.25 : 1;
									double nodeFactorP1 = cbioImpacted3ppNodesFactor;//("Above 3".equals(cbioImpacted3ppNodes)) ? 1.25 : 1;
									double callVolfactorP1 = ("Above 25".equals(suppActDto.getCallVolume())) ? 1.25 : 1; 
									double totalPdP1 = baseEffPdP1*customerBaseFactorP1*nodeFactorP1*callVolfactorP1;
									double baseFteP1 = baseEffhrsP1*customerBaseFactorP1*nodeFactorP1*callVolfactorP1;
							     	obj.setTotalYearlyTicket(Float.toString(roundFloat(totYearlyTktP1)));
							     	obj.setBaseEffortHrs(Float.toString(roundFloat(baseEffhrsP1)));
							     	obj.setBaseEffortPd(Float.toString(roundFloat(baseEffPdP1)));
							     	obj.setCustomerBaseFactor(Double.toString(roundDecimals(customerBaseFactorP1)));
							     	obj.setNodesFactor(Double.toString(roundDecimals(nodeFactorP1)));
							     	obj.setCallVolumeFactor(Double.toString(roundDecimals(callVolfactorP1)));
							     	obj.setTotalPD(Double.toString(roundDecimals(totalPdP1)));
							     	total = total + totalPdP1;
							     	servEleTotalBaseHrs = servEleTotalBaseHrs+baseEffhrsP1;
							     	servEleTotalBaseFte = servEleTotalBaseFte+baseFteP1;
							     	outputList.add(obj);
							     	break;
							     
					case Priority2 : 	obj.setPriority(Priority2);
									float totYearlyTktP2 = tktPerMonth*steadyStateDuration*(tktDist/100)*(slaDto.getTicketDistribution()/100);
									float baseEffhrsP2 = totYearlyTktP2*(responseTime);
									float baseEffPdP2 = baseEffhrsP2/8;
									double customerBaseFactorP2 = customerBaseFactor;//("Above 5".equals(customerBase)) ? 1.25 : 1;
									double nodeFactorP2 = cbioImpacted3ppNodesFactor;//("Above 3".equals(cbioImpacted3ppNodes)) ? 1.25 : 1;
									double callVolfactorP2 = ("Above 25".equals(suppActDto.getCallVolume())) ? 1.25 : 1; 
									double totalPdP2 = baseEffPdP2*customerBaseFactorP2*nodeFactorP2*callVolfactorP2;
									double baseFteP2 = baseEffhrsP2*customerBaseFactorP2*nodeFactorP2*callVolfactorP2;
							     	obj.setTotalYearlyTicket(Float.toString(roundFloat(totYearlyTktP2)));
							     	obj.setBaseEffortHrs(Float.toString(roundFloat(baseEffhrsP2)));
							     	obj.setBaseEffortPd(Float.toString(roundFloat(baseEffPdP2)));
							     	obj.setCustomerBaseFactor(Double.toString(roundDecimals(customerBaseFactorP2)));
							     	obj.setNodesFactor(Double.toString(roundDecimals(nodeFactorP2)));
							     	obj.setCallVolumeFactor(Double.toString(roundDecimals(callVolfactorP2)));
							     	obj.setTotalPD(Double.toString(roundDecimals(totalPdP2)));
							     	total = total + totalPdP2;
							     	servEleTotalBaseHrs = servEleTotalBaseHrs+baseEffhrsP2;
							     	servEleTotalBaseFte = servEleTotalBaseFte+baseFteP2;
							     	outputList.add(obj);
							     	break;
				     				
					case Priority3 :    obj.setPriority(Priority3);
									float totYearlyTktP3 = tktPerMonth*steadyStateDuration*(tktDist/100)*(slaDto.getTicketDistribution()/100);
									float baseEffhrsP3 = totYearlyTktP3*(responseTime);
									float baseEffPdP3 = baseEffhrsP3/8;
									double customerBaseFactorP3 = customerBaseFactor;//("Above 5".equals(customerBase)) ? 1.25 : 1;
									double nodeFactorP3 = cbioImpacted3ppNodesFactor;//("Above 3".equals(cbioImpacted3ppNodes)) ? 1.25 : 1;
									double callVolfactorP3 = ("Above 25".equals(suppActDto.getCallVolume())) ? 1.25 : 1; 
									double totalPdP3 = baseEffPdP3*customerBaseFactorP3*nodeFactorP3*callVolfactorP3;
									double baseFteP3 = baseEffhrsP3*customerBaseFactorP3*nodeFactorP3*callVolfactorP3;
							     	obj.setTotalYearlyTicket(Float.toString(roundFloat(totYearlyTktP3)));
							     	obj.setBaseEffortHrs(Float.toString(roundFloat(baseEffhrsP3)));
							     	obj.setBaseEffortPd(Float.toString(roundFloat(baseEffPdP3)));
							     	obj.setCustomerBaseFactor(Double.toString(roundDecimals(customerBaseFactorP3)));
							     	obj.setNodesFactor(Double.toString(roundDecimals(nodeFactorP3)));
							     	obj.setCallVolumeFactor(Double.toString(roundDecimals(callVolfactorP3)));
							     	obj.setTotalPD(Double.toString(roundDecimals(totalPdP3)));
							     	total = total + totalPdP3;
							     	servEleTotalBaseHrs = servEleTotalBaseHrs+baseEffhrsP3;
							     	servEleTotalBaseFte = servEleTotalBaseFte+baseFteP3;
							     	outputList.add(obj);
							     	break;
							     	
					case Priority4 : 	obj.setPriority(Priority4);
									float totYearlyTktP4 = tktPerMonth*steadyStateDuration*(tktDist/100)*(slaDto.getTicketDistribution()/100);
									float baseEffhrsP4 = totYearlyTktP4*(responseTime);
									float baseEffPdP4 = baseEffhrsP4/8;
									double customerBaseFactorP4 = customerBaseFactor;//("Above 5".equals(customerBase)) ? 1.25 : 1;
									double nodeFactorP4 = cbioImpacted3ppNodesFactor;//("Above 3".equals(cbioImpacted3ppNodes)) ? 1.25 : 1;
									double callVolfactorP4 = ("Above 25".equals(suppActDto.getCallVolume())) ? 1.25 : 1; 
									double totalPdP4 = baseEffPdP4*customerBaseFactorP4*nodeFactorP4*callVolfactorP4;
									double baseFteP4 = baseEffhrsP4*customerBaseFactorP4*nodeFactorP4*callVolfactorP4;
							     	obj.setTotalYearlyTicket(Float.toString(roundFloat(totYearlyTktP4)));
							     	obj.setBaseEffortHrs(Float.toString(roundFloat(baseEffhrsP4)));
							     	obj.setBaseEffortPd(Float.toString(roundFloat(baseEffPdP4)));
							     	obj.setCustomerBaseFactor(Double.toString(roundDecimals(customerBaseFactorP4)));
							     	obj.setNodesFactor(Double.toString(roundDecimals(nodeFactorP4)));
							     	obj.setCallVolumeFactor(Double.toString(roundDecimals(callVolfactorP4)));
							     	obj.setTotalPD(Double.toString(roundDecimals(totalPdP4)));
							     	total = total + totalPdP4;
							     	servEleTotalBaseHrs = servEleTotalBaseHrs+baseEffhrsP4;
							     	servEleTotalBaseFte = servEleTotalBaseFte+baseFteP4;
							     	outputList.add(obj);
							     	break;
						
					}
					
				}
				upadteTotalBaseHrsMap(oppScopeId+"_"+servScopeName,servEleTotalBaseHrs);
				upadteTotalBaseFteMap(oppScopeId+"_"+servScopeName,servEleTotalBaseFte);
		     	servScopeMap.put(servScopeName/*+"_"+roundDecimals(servEleTotalBaseHrs)+"_"+roundDecimals(servEleTotalBaseFte)*/, outputList);
				mapList.add(servScopeMap);
			}
			
			componentMap.put(componentName+"_"+roundDecimals(total), mapList);
		}
      return componentMap;
	}

private void displayMap(Map<String,List<Map<String,List<ApplicationMaintenenceOutput>>>> componentMap){
	System.out.println("---MAP---");
	Iterator entries = componentMap.entrySet().iterator();
	while (entries.hasNext()) {
		  Entry thisEntry = (Entry) entries.next();
		  String key = (String) thisEntry.getKey();
		  System.out.println(key+":");
		  List<Map> listMap = (List) thisEntry.getValue();
		  
		  for(Map map : listMap){
			  Iterator entries2 = map.entrySet().iterator();
			  while(entries2.hasNext()){
				  Entry entry2 = (Entry) entries2.next();
				  String key2 = (String) entry2.getKey();
				  System.out.println("\t"+key2+":");
				  
				  List<ApplicationMaintenenceOutput> listOutput = (List) entry2.getValue();
				  for(ApplicationMaintenenceOutput out : listOutput){
					  System.out.println("\t\t Priority = "+out.getPriority());
					  System.out.println("\t\t TotalYearlyTicket = "+out.getTotalYearlyTicket());
					  System.out.println("\t\t BaseEffortHrs = "+out.getBaseEffortHrs());
					  System.out.println("\t\t BaseEffortPd = "+out.getBaseEffortPd());
					  System.out.println("\t\t CustomerBaseFactor = "+out.getCustomerBaseFactor());
					  System.out.println("\t\t NodesFactor = "+out.getNodesFactor());
					  System.out.println("\t\t CallVolumeFactor = "+out.getCallVolumeFactor());
					  System.out.println("\t\t TotalPD = "+out.getTotalPD());
					  System.out.println("\n\n");
				  }
			  }
		  }
		  
		  
		
	}
	
	
}


private float roundFloat(float d) {
    BigDecimal bd = new BigDecimal(Float.toString(d));
    bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
    return bd.floatValue();
}


private double roundDecimals(double d) {
	  DecimalFormat twoDForm = new DecimalFormat("#.##");
	  return Double.valueOf(twoDForm.format(d));
	}

private void upadteTotalBaseHrsMap(String idName, Double servEleTotalBaseHrs){
	double totalBaseHrs = 0;
	if(null != totalBaseHrsMap.get(idName)){
		totalBaseHrs = servEleTotalBaseHrs + totalBaseHrsMap.get(idName);
		totalBaseHrsMap.put(idName, roundDecimals(totalBaseHrs));
	}else{
		totalBaseHrsMap.put(idName, roundDecimals(servEleTotalBaseHrs));
	}
}

private void upadteTotalBaseFteMap(String idName, Double servEleTotalBasefte){
	double totalBasefte = 0;
	if(null != totalBaseFteMap.get(idName)){
		totalBasefte = servEleTotalBasefte + totalBaseFteMap.get(idName);
		totalBaseFteMap.put(idName, roundDecimals(totalBasefte));
	}else{
		totalBaseFteMap.put(idName, roundDecimals(servEleTotalBasefte));
	}
}
	
	private void updateProductEstimationBaseEffortForSolution(Integer solId)
			throws MSSPException {
		if (totalBaseHrsMap.size() != totalBaseFteMap.size()) {
			logger.info("maps not equal.");
		} else {
			logger.info("Maps equal");
		}
		
		List<ServiceScopeAppMainDataBean> dtoList = new ArrayList<ServiceScopeAppMainDataBean>();
		Iterator entries = totalBaseHrsMap.entrySet().iterator();
		while (entries.hasNext()) {
			Entry thisEntry = (Entry) entries.next();
			String idName = (String) thisEntry.getKey();
			String[] arr = idName.split("_");
			Integer oppScopeId = Integer.valueOf(arr[0]);
			String servScopeName = arr[1];
			Double servEleTotalBaseHrs = (Double) thisEntry.getValue();
			Double servEleTotalBaseFte = (Double) totalBaseFteMap.get(idName);
			ServiceScopeAppMainDataBean bean = new ServiceScopeAppMainDataBean();
			bean.setSolutionId(solId);
			bean.setOppScopeId(oppScopeId);
			bean.setServScopeName(servScopeName);
			bean.setTotalBaseFTE(servEleTotalBaseFte);
			bean.setTotalBaseHours(servEleTotalBaseHrs);
			dtoList.add(bean);
		}
		productEstimationService.saveProductEstimationBaseEffortForSolution(dtoList);
		
	}
	
	private List<ServiceScopeAppMainDataBean> getProductEstimationBaseEffortForSolution(Integer solId) throws MSSPException{
		List<ServiceScopeAppMainDataBean> myList = productEstimationService.getProductEstimationBaseEffortForSolution(solId,serviceElement);
		return myList;
		
	}
		
}
