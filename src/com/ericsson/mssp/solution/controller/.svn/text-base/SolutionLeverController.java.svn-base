
package com.ericsson.mssp.solution.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.AutoPopulatingList;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ericsson.mssp.common.dto.OpportunityUtilizationPerYearDTO;
import com.ericsson.mssp.common.dto.SolutionDTO;
import com.ericsson.mssp.common.dto.SolutionLeverDTO;
import com.ericsson.mssp.common.entity.JobRoleStages;
import com.ericsson.mssp.common.entity.LocationBreakup;
import com.ericsson.mssp.common.entity.LocationBreakupDefault;
import com.ericsson.mssp.common.entity.LocationPyramid;
import com.ericsson.mssp.common.entity.OpportunityDetail;
import com.ericsson.mssp.common.entity.OpportunityScope;
import com.ericsson.mssp.common.entity.ProductivityLever;
import com.ericsson.mssp.common.entity.ServiceElementJobRoleStages;
import com.ericsson.mssp.common.exception.MSSPException;
import com.ericsson.mssp.common.util.ApplicationPropertiesUtil;
import com.ericsson.mssp.solution.service.APAService;
import com.ericsson.mssp.solution.service.ISolutionService;
import com.ericsson.mssp.solution.service.SolutionLeverService;

@Controller
public class SolutionLeverController extends BaseController {

	/*@Autowired
	private APAService apaService;
	@Autowired
	private SolutionLeverService solutionLeverService;
	@Autowired
	private ISolutionService solutionService;
	
	private final Log log = LogFactory.getLog(SolutionLeverController.class);
	
	List<OpportunityScope> oppScopeList;
	List<ServiceElementJobRoleStages> jobRoleStageList;
	
	private final String JOB_ROLE_MODEL_CCM="CCM";
	
	@ModelAttribute("solnLeverDTO")
	public SolutionLeverDTO getSolutionLeverDTO() {
		SolutionLeverDTO solnLeverDTO = new SolutionLeverDTO();
		solnLeverDTO.setProdLeverList(new AutoPopulatingList<ProductivityLever>(ProductivityLever.class));
		solnLeverDTO.setLocBreakupList(new AutoPopulatingList<LocationBreakup>(LocationBreakup.class));
		
		solnLeverDTO.setOnLocalLocListForSave(new AutoPopulatingList<LocationPyramid>(LocationPyramid.class));
		solnLeverDTO.setOnGSCiLocListForSave(new AutoPopulatingList<LocationPyramid>(LocationPyramid.class));
		solnLeverDTO.setOn3PPLocListForSave(new AutoPopulatingList<LocationPyramid>(LocationPyramid.class));
		solnLeverDTO.setNearLocListForSave(new AutoPopulatingList<LocationPyramid>(LocationPyramid.class));
		solnLeverDTO.setOffLocListForSave(new AutoPopulatingList<LocationPyramid>(LocationPyramid.class));
		return solnLeverDTO;
	}
	
	@InitBinder
	private void dateBinder(WebDataBinder binder) {
	            //The date format to parse or output your dates
	    SimpleDateFormat dateFormat = new SimpleDateFormat("MMM-yyyy");
	            //Create a new CustomDateEditor
	    CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
	            //Register it as custom editor for the Date type
	    binder.registerCustomEditor(Date.class, editor);
	}
	
	@RequestMapping("/solution/productivityLevers")
	public String loadSolnLevers(ModelMap model, @ModelAttribute("solnLeverDTO") SolutionLeverDTO solnLeverDTO, HttpSession session,  
			@RequestParam(value = "isSave", required = false, defaultValue = "")String isSave) {
		Integer opportunityId = getSessionOpportunityId(session);
		Integer solutionId = getSessionSolutionId(session);
		//System.out.println("Opp Id: "+opportunityId);
		log.info("Opp Id: "+opportunityId);
		//System.out.println("Soln Id: "+getSessionSolutionId(session));
		log.info("Soln Id: "+solutionId);
		// Service Bucket table data
		
		List<String> serviceBucketData = solutionService.loadServiceBucketDataByOppSolutionID(opportunityId, solutionId);
		model.addAttribute("serviceBucketData", serviceBucketData);
		try {
			oppScopeList = apaService.getOppourtunityScopeList(opportunityId, solutionId);
			solnLeverDTO.setSolutionId(solutionId);
			solnLeverDTO.setOppScopeList(oppScopeList);
			
			OpportunityDetail opDetail = solutionLeverService.getOpportunityDetail(opportunityId);
			SolutionDTO solutionDTO = solutionService.getSolutionDetail(solutionId);
			
			
			OpportunityUtilizationPerYearDTO utilPerYearDTO = new OpportunityUtilizationPerYearDTO();
			utilPerYearDTO.setOnshoreLocal(opDetail.getOnshoreLocal());
			utilPerYearDTO.setOnshoreGSC(opDetail.getOnshoreGSC());
			utilPerYearDTO.setOnshore3PP(opDetail.getOnshore3PP());
			utilPerYearDTO.setOffshore(opDetail.getOffShore());
			utilPerYearDTO.setNearshore(opDetail.getNearShore());
			
			
			if(opDetail != null ){
				solnLeverDTO.setSteadyStateStartDate(opDetail.getSteadyStateStartDate());
				solnLeverDTO.setSteadyStateEndDate(opDetail.getSteadyStateEndDate());
			}
			//System.out.println("In Controller - Steady State Start Date: "+solutionLeverService.getSteadyStateStartDate(opportunityId));
		//	log.info("Steady State Start Date: "+solutionLeverService.getSteadyStateStartDate(opportunityId));
		//solnLeverDTO.setSteadyStateStartDate(solutionLeverService.getSteadyStateStartDate(opportunityId));
			//System.out.println("In Controller - Steady State End Date: "+solutionLeverService.getSteadyStateEndDate(opportunityId));
		//	log.info("Steady State End Date: "+solutionLeverService.getSteadyStateEndDate(opportunityId));
		//	solnLeverDTO.setSteadyStateEndDate(solutionLeverService.getSteadyStateEndDate(opportunityId));
		
		
			// The following sections have been added for retrieving saved data and send them back to the view
			Map<Long,Integer> scopePLCountMap = null;
			Integer lbRowCount = 1;
			Map<Long,Integer> scopePyramidCountMap = null;
			Map<Long,Integer> scopeOffPyrCountMap = null;
			
			Integer oppScopeId=solnLeverDTO.getOppScopeId();
			
			if(oppScopeId == null){	
				oppScopeId = oppScopeList.get(0).getOpportunityScopeId();
			}
			
			Integer serviceElementId = solutionLeverService.getServiceElementIdByOppScopeId(oppScopeId);
			
			boolean ccmFlag = true;
			
			if(!JOB_ROLE_MODEL_CCM.equalsIgnoreCase(solutionDTO.getJobRoleModel())){
				ccmFlag = false;
			}
			//jobRoleList = solutionLeverService.getJobRoleByServiceElementId(serviceElementId, ccmFLag);
			//solnLeverDTO.setJobRoleStagesList(jobRoleList);
			
			List<ServiceElementJobRoleStages> jobRoleStageList = solutionLeverService.getJobRoleSerEleByServiceElementId(serviceElementId, ccmFlag);
			
			solnLeverDTO.setSerEleJobRoleStagesList(jobRoleStageList);
			
			
			solutionLeverService.loadSolutionLevers(solnLeverDTO,oppScopeId);
			
			LocationBreakupDefault locationBreakupDefault =solutionLeverService.loadDefaultValuesByServiceElementId(new Integer(serviceElementId));
			
			model.addAttribute("locationBreakupDefault", locationBreakupDefault);
			
			
			//scopePLCountMap = solutionLeverService.createScopePLCountMap(opportunityId, solutionId);
			//lbRowCount = solutionLeverService.getLBRowCount(solutionId);
			scopePyramidCountMap = solutionLeverService.createScopePyramidCountMap(opportunityId, solutionId);
			scopeOffPyrCountMap = solutionLeverService.createScopeOffPyrCountMap(opportunityId, solutionId);
			
			Float startupFTE = solutionLeverService.getStartupFTE(getSessionSolutionId(session), oppScopeId);
			
			model.addAttribute("startupFTE",startupFTE.toString());
			
			
			model.addAttribute("utilPerYearDTO",utilPerYearDTO);
			
			model.addAttribute("serviceElementId",serviceElementId);
			model.addAttribute("ccmFlag",ccmFlag);
			
			if("true".equalsIgnoreCase(isSave)){
				model.addAttribute("message", ApplicationPropertiesUtil.getProperty("msg.common.datasave.success"));
			}else if("false".equalsIgnoreCase(isSave)){
				model.addAttribute("message", ApplicationPropertiesUtil.getProperty("msg.common.datasave.error"));
			}
			
			if(opDetail == null || opDetail.getSteadyStateStartDate() == null){
				model.addAttribute("message","Steady start/end date cann't be empty for opportunity  ");
			}
			
			  //check if user has edit access 
		    String hasEditSolAccess = (String) getSessionValueFromKey(session, HAS_EDIT_SOL_ACCESS);
			model.addAttribute("hasEditSolAccess", hasEditSolAccess);
			
		} catch (MSSPException e) {			
			log.error("opportunityId: "+opportunityId+", solutionId: "+solutionId+"\tDB Error in loadSolutionLevers\n"+e.getMessage());
		}		
		
		model.addAttribute("solnLeverDTO", solnLeverDTO);
		
	
		return "productivityLevers";
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/solution/appendProdLeverRow")
	protected String appendProdLeverRow(@RequestParam Integer rowId,
			@ModelAttribute("solnLeverDTO") SolutionLeverDTO solnLeverDTO, ModelMap model)
	{
		log.info("Productivity Lever Record Number: "+rowId);	
		
		model.addAttribute("prodLeverNumber", rowId);
	
//		model.addAttribute("opsId", oppScopeId);
		return "addProdLever";
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value="/solution/appendOnPcRow")
	protected String appendOnPcRow(@RequestParam Integer rowId, ModelMap model)
	{
		log.info("Onshore Percentage Record Number: "+rowId);
		model.addAttribute("onPcNumber", rowId);
	//	model.addAttribute("allScopes", oppScopeList);
		return "addOnPc";
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/solution/appendOnPyrRow")
	protected String appendOnPyrRow(@RequestParam Integer rowId, @RequestParam String location,
			@RequestParam String sublocation,@RequestParam String listName, @RequestParam Integer serviceElementId,
			@RequestParam boolean ccmFlag, ModelMap model)
	{
		
		List<ServiceElementJobRoleStages> jobRoleStageList;
		try {
			jobRoleStageList = solutionLeverService.getJobRoleSerEleByServiceElementId(serviceElementId, ccmFlag);
	
		
		model.addAttribute("onPyrNumber", rowId);
		model.addAttribute("location", location);
		model.addAttribute("sublocation", sublocation);
		model.addAttribute("listName", listName);
		model.addAttribute("allRoles", jobRoleStageList);
		
		
		
		} catch (MSSPException e) {
			log.info("Error while adding row : "+e.getMessage());
			e.printStackTrace();
		}
		return "addOnPyr";
	}
		
	
	
	@RequestMapping(method = RequestMethod.GET, value="/solution/getFTEVal")
	protected @ResponseBody String getFTE(@RequestParam Integer osid, HttpSession session)
	{
		log.info("Opportunity Scope ID: "+osid);		
		try {
			return solutionLeverService.getStartupFTE(getSessionSolutionId(session), osid).toString();
		} catch (MSSPException e) {
			// TODO Auto-generated catch block
			log.error("Error in getStartupFTE "+e.getMessage());
		}
		return "0";
	}
	
	@RequestMapping(value="/solution/saveSolnLevers", method = RequestMethod.POST)
	public String savePage(Model model, @ModelAttribute("solnLeverDTO") SolutionLeverDTO solnLeverDTO, HttpSession session){
			
			Integer oppId = getSessionOpportunityId(session);
			Integer solutionId = getSessionSolutionId(session);
			solnLeverDTO.setSolutionId(solutionId);
			String isSave = "true";
			log.info("Productivity Lever Size: "+solnLeverDTO.getProdLeverList().size());
			if(oppId != null && solutionId != null) {
				try {
					solutionLeverService.saveSolutionLevers(solnLeverDTO);
					//model.addAttribute("message", ApplicationPropertiesUtil.getProperty("msg.common.datasave.success"));
				} catch (MSSPException e){
					isSave="false";
					log.error("There is an error while saving Solution Lever data into the database\n"+e.getMessage());
					e.printStackTrace();
					//odel.addAttribute("message", ApplicationPropertiesUtil.getProperty("msg.common.datasave.error"));
				} 
			}
			//return "forward:./productivityLevers";
			return "redirect:../solution/productivityLevers?isSave="+isSave;
	}
	*/
	/*@RequestMapping(value = "/solution/loadDefaultValuesLocPyramid", method = RequestMethod.POST)
	public @ResponseBody
	String loadDefaultValuesLocPyramid(Model model,
			@RequestParam("oppScopeId") String oppScopeId) {
		String defaultValuesStr = "";
		
		try {
			
			Integer serviceElementId = solutionLeverService.getServiceElementIdByOppScopeId(new Integer(oppScopeId));
			
			defaultValuesStr = solutionLeverService
					.loadDefaultValuesByServi
					ceElementId(new Integer(serviceElementId));

		} catch (MSSPException e) {
			// TODO Auto-generated catch block
			logger.error("There is a error while loading data from database", e);
			e.printStackTrace();
		}

		return defaultValuesStr;

	}*/
	
}
