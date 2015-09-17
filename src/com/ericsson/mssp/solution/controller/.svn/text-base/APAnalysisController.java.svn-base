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
import org.springframework.util.AutoPopulatingList;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ericsson.mssp.common.dto.APADTO;
import com.ericsson.mssp.common.dto.OpportunityDTO;
import com.ericsson.mssp.common.entity.Apacomplexity;
import com.ericsson.mssp.common.entity.OpportunityScope;
import com.ericsson.mssp.common.entity.SolutionAPA;
import com.ericsson.mssp.common.exception.MSSPException;
import com.ericsson.mssp.common.util.ApplicationPropertiesUtil;
import com.ericsson.mssp.solution.service.APAService;
import com.ericsson.mssp.solution.service.ISolutionService;

@Controller
public class APAnalysisController extends BaseController {
	
	public static Logger logger = Logger.getLogger(APAnalysisController.class);
	
	@Autowired
	private APAService apaService;
	@Autowired
	private ISolutionService solutionService;
	
	/*@InitBinder
	public void initBinder(WebDataBinder binder){
	    binder.setAutoGrowNestedPaths(false);
	}*/
	
	@ModelAttribute("apaDTO")
	public APADTO getAPADTO() {
		APADTO apaDTO = new APADTO();
		apaDTO.setSolutionAPAList(new AutoPopulatingList<SolutionAPA>(SolutionAPA.class));
		apaDTO.setApaComplexityList(new AutoPopulatingList<Apacomplexity>(Apacomplexity.class));
		return apaDTO;
	}
	
	@RequestMapping(value = "/solution/APAnalysis")
	public String loadAPAnalysis(ModelMap model, @ModelAttribute("apaDTO") APADTO apaDTO, HttpSession session) {
		
		//model.addAttribute("message", "aPAnalysis");
		List<String> ComplexityFactorList = populateComplexityFactorList();
		model.addAttribute("ComplexityFactorList", ComplexityFactorList);
		List<String> paramScope = populateParamList4rSelectedScope();
		model.addAttribute("paramScope", paramScope);
		Integer opportunityId = getSessionOpportunityId(session);
		Integer solutionId = getSessionSolutionId(session);
//		model.addAttribute("solnAPANumber", 0);
		/*
		try
		{				
			SolutionAPADTO solutionAPADTO = new SolutionAPADTO();
			List<SolutionAPA> solutionAPAList = new ArrayList<SolutionAPA>();
			
			List<ServiceScopeDTO> listOfServiceScope = solutionService.loadAllSelectedServiceScopes(opportunityId);
			
			for(ServiceScopeDTO scd : listOfServiceScope){
				solutionService.getAllSolutionAPABySolId(solutionId,opportunityId,scd.getServiceScopeId(),solutionAPADTO,solutionAPAList);
			}
			model.addAttribute("listOfServiceScope", listOfServiceScope);
			model.addAttribute("solutionAPADTO", solutionAPADTO.getSolutionAPAListCN());
			
		} 
		*/
		// Service Bucket table data
		List<String> serviceBucketData = solutionService.loadServiceBucketDataByOppSolutionID(opportunityId, solutionId);
		model.addAttribute("serviceBucketData", serviceBucketData);
        	// Opportunity display data
        	OpportunityDTO opportunityDTO = new OpportunityDTO();
        	if (opportunityId != null) {
        	    opportunityDTO = solutionService.getOpportunity(opportunityId);
        	}
        	model.addAttribute("opportunityDTO", opportunityDTO);
		try {
			List<OpportunityScope> oppScopeList = apaService.getOppourtunityScopeList(opportunityId, solutionId);			
//			List<Apacomplexity> apaComplexityList = new ArrayList<Apacomplexity>(oppScopeList.size());
//			APADTO apaDTO = new APADTO();
			apaDTO.setSolutionId(solutionId);
			apaDTO.setOppScopeList(oppScopeList);
//			apaDTO.setApaComplexityList(apaComplexityList);
//			model.addAttribute("apaDTO", apaDTO);
		}
		catch (MSSPException e) {
			logger.error("opportunityId: "+opportunityId+", solutionId: "+solutionId+" loadAPAnalysis Exception "+e.getMessage());
		}
		
		
// The following section has been added for retrieving saved data and send them back to the view
		Map<Long,Integer> scopeAPACountMap = null;
		try {
			apaService.loadAPA(apaDTO);
			scopeAPACountMap = apaService.createScopeAPACountMap(opportunityId, solutionId);
		} catch (MSSPException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.error("opportunityId: "+opportunityId+", solutionId: "+solutionId+" loadAPA Exception "+e.getMessage()); 
		}
		
		model.addAttribute("apaDTO", apaDTO);
		if(scopeAPACountMap != null) {
			logger.debug("scopeAPACountMap added to model "+scopeAPACountMap);
			model.addAttribute("scopeAPACountMap",scopeAPACountMap);
		}
		return "aPAnalysis";
	}
	@RequestMapping(method = RequestMethod.GET, value="/solution/appendSolnAPARow")
	protected String appendSolnAPARow(@RequestParam Integer rowId, @RequestParam Integer oppScopeId, 
			@ModelAttribute("apaDTO") APADTO apaDTO, ModelMap model)
	{
logger.info("Solution APA Record Number: "+rowId);	
logger.info("Opportunity Scope Id: "+oppScopeId);
	//	SolutionAPA solnApa = new SolutionAPA();
	//	AutoPopulatingList<SolutionAPA> list = apaDTO.getSolutionAPAList();
	//	list.add(solnApa);
	//	apaDTO.setSolutionAPAList(new AutoPopulatingList<SolutionAPA>(SolutionAPA.class));
//apaDTO.getSolutionAPAList().add(new SolutionAPA());
		model.addAttribute("solnAPANumber", rowId);
		model.addAttribute("opsId", oppScopeId);
//		model.addAttribute("apaDTO", apaDTO);
		return "addSolnAPA";
	}
	/**
	 * 
	 * Description : Save APAnalysisController :
	 * @return
	 */
	@RequestMapping(value="/solution/saveAPAnalysis", method = RequestMethod.POST)
	public String savePage(Model model, @ModelAttribute("apaDTO") APADTO apaDTO, HttpSession session) {
			
			Integer oppId = getSessionOpportunityId(session);
			Integer solutionId = getSessionSolutionId(session);
			apaDTO.setSolutionId(solutionId);
//System.out.println("Soln APA Size: "+apaDTO.getSolutionAPAList().size());
			logger.info("Soln APA Size: "+apaDTO.getSolutionAPAList().size());
//			model.addAttribute("apaDTO", apaDTO);
			if(oppId != null && solutionId != null) {
				try {
					apaService.saveAPA(apaDTO);
					//solutionService.saveAPAComplexity(listOfServiceScope,oppId,solutionId);
					model.addAttribute("message", ApplicationPropertiesUtil.getProperty("msg.common.datasave.success"));
				} catch (MSSPException e){
					logger.error("There is an error while saving APAnalysis data into database",e);
					e.printStackTrace();
					model.addAttribute("message", ApplicationPropertiesUtil.getProperty("msg.common.datasave.error"));
				} 
			}
			return "forward:./APAnalysis";
	}
	/**
	 * 
	 * Description : Impact list drop down values Method Name :
	 * populateImpactList Input& Output:
	 * 
	 * @return
	 */
	@ModelAttribute("complexityFactorList")
	public List<String> populateComplexityFactorList() {
		List<String> apaComplexityList = new ArrayList<String>();
		Map<String, String> complexitiesFactorListMap = ApplicationPropertiesUtil
				.getMapConfigKeyValue(APA_COMPLEXITYFACTORS, true);
		//System.out.println(complexitiesFactorListMap.get("2")+"<---------populateComplexityFactorList---------->"+complexitiesFactorListMap.isEmpty());
		for (Map.Entry<String, String> entry : complexitiesFactorListMap.entrySet()) {
		    //System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
		    apaComplexityList.add(entry.getValue());
		}
		return apaComplexityList;
	}
	@ModelAttribute("complexityFactorList")
	public List<String> populateParamList4rSelectedScope() {
		List<String> apaScopeList = new ArrayList<String>();
		Map<String, String> complexitiesFactorListMap = ApplicationPropertiesUtil
				.getMapConfigKeyValue(APA_SCOPE_FACTORS, true);
		//System.out.println(complexitiesFactorListMap.get("2")+"<------------------->"+complexitiesFactorListMap.isEmpty());
		
		for (Map.Entry<String, String> entry : complexitiesFactorListMap.entrySet()) {
		    //System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
		    apaScopeList.add(entry.getValue());
		}
		return apaScopeList;
	}
	
/*	@ModelAttribute("solutionAPADTO")
	public SolutionAPADTO populateSolutionAPA(SolutionAPADTO solutionAPADTO) {
		
		// Don't forget to initialize the solutionAPADTO list or else it won't work
	    List<SolutionAPAClone> solutionAPACloneList = new ArrayList<SolutionAPAClone>();
	    for(int i=0; i<5; i++) {//
	    	solutionAPACloneList.add(new SolutionAPAClone());
	    }
	    solutionAPADTO.setSolutionAPAList(solutionAPACloneList);
	    return solutionAPADTO;
	}*/
	
/*	@RequestMapping(value="/solution/saveAPAnalysisNext", method = RequestMethod.POST)
	public String defineScopeNext(Model model,@ModelAttribute("solutionAPADTO") SolutionAPADTO solutionAPADTO, HttpSession session){
		savePage(model,solutionAPADTO, session);
		return "redirect:../opportunity/defineScope";
	}*/
}