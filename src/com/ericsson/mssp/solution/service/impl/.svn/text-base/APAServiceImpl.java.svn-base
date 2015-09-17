package com.ericsson.mssp.solution.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ericsson.mssp.common.dto.APADTO;
import com.ericsson.mssp.common.entity.Apacomplexity;
import com.ericsson.mssp.common.entity.Apaweightage;
import com.ericsson.mssp.common.entity.OpportunityScope;
import com.ericsson.mssp.common.entity.Solution;
import com.ericsson.mssp.common.entity.SolutionAPA;
import com.ericsson.mssp.common.entity.SolutionLever;
import com.ericsson.mssp.common.exception.MSSPException;
import com.ericsson.mssp.solution.dao.APADAO;
import com.ericsson.mssp.solution.service.APAService;

@Service
public class APAServiceImpl implements APAService {
	
	public static Logger logger = Logger.getLogger(APAServiceImpl.class);
	
	@Autowired
    private APADAO apaDAO;

	@Override
	public List<OpportunityScope> getOppourtunityScopeList(Integer oppId,
			Integer solnId) throws MSSPException {

		return apaDAO.getOppourtunityScopeList(oppId, solnId);
	}

	@Override
	@Transactional
	public void saveAPA(APADTO apaDTO) throws MSSPException {
		
		Solution soln = new Solution();
		soln.setSolutionId(apaDTO.getSolutionId());
		
		int m = apaDAO.deleteAPAWeightage(apaDTO.getSolutionId());
				
		Apaweightage apaw = apaDTO.getApaWeightage();	
		apaw.setApaweightageId(null);
		apaw.setSolution(soln);
		
		// Save APA Score Card Weightage
		if(apaw != null) {
			logger.info("APAWeightage Id to be saved:"+apaw.getApaweightageId());
			apaDAO.saveAPAWeightage(apaw);	
			logger.info("APAWeightage Id generated:"+apaw.getApaweightageId());
		}
		
logger.info("Total number of Solution APA :"+apaDTO.getSolutionAPAList().size());

		// Changed to incorporate delete functionality

		int n = apaDAO.deleteSolutionAPA(apaDTO.getSolutionId());
logger.info("Total number of Solution APA deleted :"+n);		
		
		for(SolutionAPA solnAPA : apaDTO.getSolutionAPAList()) {		
			//Always fresh insert
			solnAPA.setSolutionApaid(null);
			
			solnAPA.setSolution(soln);
logger.info("Score: "+solnAPA.getComputedRiskFactor()+"\tRisk: "+solnAPA.getComputedRiskExposure());
logger.info("Opportunity Scope: "+solnAPA.getOpportunityScope()+"\tPercentage Share: "+solnAPA.getPercentageShare());
			if(solnAPA.getOpportunityScope() != null && solnAPA.getPercentageShare() != null) {
				apaDAO.saveSolutionAPA(solnAPA);
			}
		}		
		
		// Changed to incorporate delete functionality
		
		int c = apaDAO.deleteAPAComplexity(apaDTO.getSolutionId());
		logger.info("Total number of APA Complexity deleted :"+c);	
		
		for(Apacomplexity apaComplexity : apaDTO.getApaComplexityList()) {	
			//Always fresh insert
			apaComplexity.setApacomplexityId(null);
			
			apaComplexity.setSolution(soln);
//System.out.println("OpScope List check: "+apaDTO.getOppScopeList());
logger.info("FTE: "+apaComplexity.getFtepercentage()+"\tComplexity: "+apaComplexity.getComplexityAssessed());
logger.info("APAComplexity Opportunity Scope: "+apaComplexity.getOpportunityScope());
			if(apaComplexity.getOpportunityScope() != null && apaComplexity.getComplexityAssessed() != null) {
				apaDAO.saveAPAComplexity(apaComplexity);
			}
		}
				
	}

	@Override
	public void loadAPA(APADTO apaDTO) throws MSSPException {
		
		apaDTO.setSolutionAPAList(apaDAO.getSolutionAPAList(apaDTO.getSolutionId()));
		apaDTO.setApaComplexityList(apaDAO.getAPAComplexityList(apaDTO.getSolutionId()));
		apaDTO.setApaWeightage(apaDAO.getAPAWeightage(apaDTO.getSolutionId()));
	}

	@Override
	public Map<Long,Integer> createScopeAPACountMap(Integer oppId, Integer solnId) throws MSSPException {
		Map<Long,Integer> scopeAPACountMap = new HashMap<Long, Integer>();
		for(OpportunityScope os: apaDAO.getOppourtunityScopeList(oppId, solnId)) {
			scopeAPACountMap.put(Long.valueOf(String.valueOf(os.getOpportunityScopeId())), 1);
		}
		logger.info("scopeAPACountMap before DB call: "+scopeAPACountMap);
		Map<Long,Integer>scopeAPACountMapDB = apaDAO.createScopeAPACountMap(solnId);
		logger.info("scopeAPACountMap from DB call: "+scopeAPACountMapDB);
		
		scopeAPACountMap.putAll(scopeAPACountMapDB);
		logger.info("scopeAPACountMap after DB call: "+scopeAPACountMap);
		/*if(scopeAPACountMap != null && !(scopeAPACountMap.isEmpty())) {
			
			return scopeAPACountMap;
		}*/
		
		return scopeAPACountMap;
	}

}
