package com.ericsson.mssp.volumetric.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.ericsson.mssp.common.constant.MSSPConstants;
import com.ericsson.mssp.common.dto.OpportunityComponentDTO;
import com.ericsson.mssp.common.dto.OpportunityDTO;
import com.ericsson.mssp.common.dto.OpportunityScopeDTO;
import com.ericsson.mssp.common.dto.ProductVolumetricDTO;
import com.ericsson.mssp.common.dto.SolutionApproachDimensionDTO;
import com.ericsson.mssp.common.dto.SolutionTestingAsAserviceDTO;
import com.ericsson.mssp.common.dto.SupportWindowMatrixDTO;
import com.ericsson.mssp.common.dto.TaasServiceDTO;
import com.ericsson.mssp.common.entity.GenericTestingInputs;
import com.ericsson.mssp.common.entity.GenericTestingOverhead;
import com.ericsson.mssp.common.entity.JobRole;
import com.ericsson.mssp.common.entity.OpportunityDetail;
import com.ericsson.mssp.common.entity.RegressionLever;
import com.ericsson.mssp.common.exception.MSSPException;
import com.ericsson.mssp.common.util.ApplicationPropertiesUtil;
import com.ericsson.mssp.solution.controller.BaseController;
import com.ericsson.mssp.solution.forms.TaasForm;
import com.ericsson.mssp.solution.forms.TaasGenericInputForm;
import com.ericsson.mssp.solution.forms.TaasOutputForm;
import com.ericsson.mssp.solution.forms.VolumetricSolutionForm;
import com.ericsson.mssp.solution.service.ISolutionService;
import com.ericsson.mssp.solution.service.ITaasService;
import com.ericsson.mssp.solution.service.IVolumetricSolutionService;
import com.ericsson.mssp.taas.dto.EffLeverDTO;
import com.ericsson.mssp.taas.objects.TaasOutput;
import com.ericsson.mssp.taas.objects.TaasSecondaryOutput;
import com.ericsson.mssp.volumetric.forms.ProductAppTestingResponse;
import com.ericsson.mssp.volumetric.service.ProductVolumetricService;
import com.ericsson.mssp.volumetric.service.VolumetricService;

/**
 * @author ekanpah
 *
 */
@Controller
public class ApplicationTestingController extends BaseController{
	
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
	
	private static final String serviceElement = "APP_TEST";
	
	private Integer oppScopeID;
	private SolutionTestingAsAserviceDTO dto;
	private TaasOutputForm taasOutputForm = null;
	
	
	@InitBinder
	 public void initBinder(WebDataBinder binder)
	 {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    dateFormat.setLenient(false);

	    // true passed to CustomDateEditor constructor means convert empty String to null
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	 }
	
	@RequestMapping(value="/solution/volumetricApplicationTesting")
	public String volumetricApplicationTesting(Model model, HttpSession session, 
			@ModelAttribute("volumetricSolutionForm") VolumetricSolutionForm volumetricSolutionForm)
	{	
		logger.info("volumetric service functions for ["+serviceElement+"]");
	
		Integer oppId = getSessionOpportunityId(session);
		Integer solId = getSessionSolutionId(session);
		Integer defaultValForServiceElementEffort = 0;
	
		TaasForm taasForm = new TaasForm();
		
		OpportunityDTO opportunityDTO= solutionService.getOpportunity(oppId);
		
		try {
				Integer solDimentionAttId = volumetricSolutionForm.getDimensionAttributeId();
		
			List<OpportunityScopeDTO> selServiceScopeList = volumetricService.getServiceScopeByServiceElement((Integer)session.getAttribute(OPPORTUNITY_ID),serviceElement);
		     
 			List <SolutionApproachDimensionDTO> dimList = solutionService.getDimentionListBySolutionId(solId);
			
			if(dimList.size()>0 && solDimentionAttId == null){
				solDimentionAttId=dimList.get(0).getSolutionApproachDimensionId();
			}
		
			for (OpportunityScopeDTO oppScope : selServiceScopeList) {
				switch (oppScope.getServiceScopeDTO().getServiceScopeId()) {
				case MSSPConstants.SERVICE_SCOPE_TAAS:
					taasForm.setOppScopeID(oppScope.getOpportunityScopeId());
					defaultValForServiceElementEffort = oppScope.getServiceScopeDTO().getServiceElementDefaultValue();
				break;

			}
			}
 		
			

			model.addAttribute("serviceScopeList",selServiceScopeList);
			model.addAttribute("solutionDimentionList",dimList);
			 
			model.addAttribute("supportWindowList",getSupportWindowList());
	
			model.addAttribute("opportunityDTO",opportunityDTO);
			   
		    model.addAttribute("jobRoleDTOList", getJobRoleList());
		
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
		/*Added for TAAS*/
		TaasGenericInputForm taasGenIPForm = new TaasGenericInputForm();
		//TaasForm taasForm = new TaasForm();
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
		taasForm.setMajEffLeverList(majEffLevList);
		taasForm.setMinEffLeverList(minEffLevList);
		taasGenIPForm.setSolution(solId);
		taasGenIPForm.setTestServList(testServList);
		taasForm.setServiceList(serviceList);
		
		if((null != taasForm.getServiceList()) &&
				(taasForm.getServiceList().size() != 0) &&
				(taasForm.getServiceList().get(0).getPercentOfServiceElementEffort()) == 0){
			taasForm.getServiceList().get(0).setPercentOfServiceElementEffort(defaultValForServiceElementEffort);
		}
		
		taasForm.setSolution(solId);
		taasForm.setGenTestInputList(genTestIPList);
		taasForm.setGenTestOverheadList(testOverheadList);
		taasForm.setRegLeverList(regLevList);
		model.addAttribute("taasGenIPForm", taasGenIPForm);
		model.addAttribute("taasForm", taasForm);
		if(taasOutputForm == null){
			model.addAttribute("taasOPForm", new TaasOutputForm());
		}else {
			model.addAttribute("taasOPForm", taasOutputForm);
		}
		model.addAttribute("selectedTab", serviceElement);
		return "volumetric";
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
	
	
		@RequestMapping(value = "/solution/savetaas", method = RequestMethod.POST)
		public @ResponseBody String saveTaasTable(@ModelAttribute("taasGenIPForm") TaasGenericInputForm taasGenIPForm)
		{
			try 
			{
				itaasService.updateGenericInputs(taasGenIPForm);
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			return "success";
		}
		
		@RequestMapping(value = "/save/taasdetails", method = RequestMethod.POST)
		public String saveTaasDetails(@ModelAttribute TaasForm taasForm)
		{
			oppScopeID = taasForm.getOppScopeID();
			dto = taasForm.getServiceList().get(0);
			itaasService.updateTaasDetails(taasForm);
			return "redirect:/taas/output";
		}
		
		@RequestMapping(value = "/taas/output", method = RequestMethod.GET)
		public String showTaasOutput(HttpSession session)
		{
			Integer solutionId = getSessionSolutionId(session);
			List<TaasOutput> list = itaasService.getMajTaasOPList(solutionId);
			TaasSecondaryOutput obj = list.get(0).getTaasSecondaryOPList().get(0);
			itaasService.saveOutput(solutionId, oppScopeID, obj, dto);
			oppScopeID = null;
			dto = null;
			TaasOutputForm outTaasOutputForm = new TaasOutputForm();
			outTaasOutputForm.setMajTaasOpList(list.get(0).getMajTaasOPList());
			outTaasOutputForm.setMinTaasOpList(list.get(0).getMinTaasOPList());
			outTaasOutputForm.setYearlyOPObj(list.get(0).getYearlyOPObj());
			outTaasOutputForm.setYearlyTaasOpList(list.get(0).getTaasSecondaryOPList());
			taasOutputForm = outTaasOutputForm;
			return "redirect:/solution/volumetricApplicationTesting";
		
		}
		
		/*@RequestMapping(value = "/solution/taas", method = RequestMethod.GET)
		public ModelAndView showTaasTable(HttpSession session)
		{
			Integer solutionId = 1;
			TaasGenericInputForm taasGenIPForm = new TaasGenericInputForm();
			TaasForm taasForm = new TaasForm();
			Map<String, Object> objectMap = new HashMap<String, Object>();
			List<TaasServiceDTO> testServList = itaasService.getGenericInputList(solutionId);
			List<GenericTestingInputs> genTestIPList = itaasService.getGenericTestingInputList(solutionId);
			List<GenericTestingOverhead> testOverheadList = itaasService.getTestingOverheadList(solutionId);
			HashMap<String, Object> effLeverMap = itaasService.getEffLeverValues(solutionId);
			List<EffLeverDTO> majEffLevList = (List<EffLeverDTO>) effLeverMap.get("Major");
			List<EffLeverDTO> minEffLevList = (List<EffLeverDTO>) effLeverMap.get("Minor");
			majEffLevList = createDtoList(majEffLevList);
			minEffLevList = createDtoList(minEffLevList);
			List<RegressionLever> regLevList = itaasService.getRegLeverValues(solutionId);
			List<TestEffReduction> testEffRedList = itaasService.getTestEffRedValues(solutionId);
			taasForm.setMajEffLeverList(majEffLevList);
			taasForm.setMinEffLeverList(minEffLevList);
			taasGenIPForm.setSolution(solutionId);
			taasGenIPForm.setTestServList(testServList);
			taasForm.setGenTestInputList(genTestIPList);
			taasForm.setGenTestOverheadList(testOverheadList);
			taasForm.setRegLeverList(regLevList);
			taasForm.setTestEffRedList(testEffRedList);
			objectMap.put("taasGenIPForm", taasGenIPForm);
			objectMap.put("taasForm", taasForm);
			objectMap.put("taasOPForm", new TaasOutputForm());
			return new ModelAndView("taas", objectMap);
		}*/
		
		/**
		 * This method creates the dto list to be pushed in the TaasForm object based on released type.
		 * @param list The list containing the EffLeverDTO objects 
		 * @param type The type of released based on which the Major & Minor efficiency lever list will be created.
		 * @return The list containing the EffLeverDTO objects.
		 */
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
    
		@RequestMapping(value = "/solution/productAppTesting")
		public String initProductAppTesting(Model model, HttpSession session) {
			ProductAppTestingResponse response = new ProductAppTestingResponse();
			Integer solutionId = getSessionSolutionId(session);
			Integer opportunityId = getSessionOpportunityId(session);
			LinkedHashMap<String, LinkedHashMap<String, Integer>> paramMap = null;
			OpportunityDetail opportunityDetail = null;
			boolean hasServiceElements = false;
			List<String> serviceBucketData = null;

			try {
				response = productEstimationService.initProductAppTesting(solutionId);
				model.addAttribute("selectedTab", serviceElement);
				model.addAttribute("appTestResponse", response);
				
				opportunityDetail = volumetricService
						.getOpportunityDetail(opportunityId);
				model.addAttribute(
						"steadyStateDuration",
						(opportunityDetail != null ? opportunityDetail
								.getSteadyStateDuration() : 0));

				paramMap = productEstimationService
						.getProductEstimationAuxiliaryParamsWrapper(solutionId);
				model.addAttribute("paramMap", paramMap);

				String hasEditSolAccess = (String) getSessionValueFromKey(session,
						HAS_EDIT_SOL_ACCESS);
				model.addAttribute("hasEditSolAccess",
						hasEditSolAccess == null ? true : hasEditSolAccess);
				
				List<OpportunityScopeDTO> opportunityScopeList = volumetricService.getServiceScopeByServiceElement(opportunityId, serviceElement);
				if(null != opportunityScopeList && opportunityScopeList.size() > 0){
					hasServiceElements = true;
				}
				model.addAttribute("hasServiceElements",hasServiceElements);
				
				serviceBucketData = solutionService
						.loadServiceBucketDataByOppSolutionID(opportunityId,
								solutionId);
				model.addAttribute("serviceBucketData", serviceBucketData);

			} catch (MSSPException e) {
				logger.error("Error in /solution/productAppTestingEffort::", e);
				model.addAttribute("message", ApplicationPropertiesUtil
						.getProperty("msg.common.datasave.error"));
			}
			return "volumetric";
		}
    	  	
}

