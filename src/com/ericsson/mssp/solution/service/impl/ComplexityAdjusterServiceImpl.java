/**
* -------------------------------------------------------------------------------------------------------
* Copyright (C) 2012 Ericsson India Global Pvt Limited
* All Rights Reserved
* Project         		    :  IT_MSSP
* Module				    :  com.ericsson.mssp.solution.service.impl
* File name       		    :  ComplexityAdjusterServiceImpl.java
* Description				:	<To Do>
* Author, Date & Release	:	Dec 11, 20122012
* Modification history		:
* Number	|Date   	   |Author        |Remark
* -----------------------------------------------------------------------------------------------------
* 1      	| Dec 11, 2012  	   |ekoukap   	  | Created.
* -----------------------------------------------------------------------------------------------------
**/
 
package com.ericsson.mssp.solution.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ericsson.mssp.common.dao.impl.BaseDAOImpl;
import com.ericsson.mssp.common.dto.ServiceScopeDTO;
import com.ericsson.mssp.common.entity.Apacomplexity;
import com.ericsson.mssp.common.entity.Solution;
import com.ericsson.mssp.common.entity.SolutionComplexity;
import com.ericsson.mssp.common.exception.MSSPException;
import com.ericsson.mssp.solution.dao.ISolutionComplexityDAO;
import com.ericsson.mssp.solution.forms.ComplexityAdjusterForm;
import com.ericsson.mssp.solution.service.IComplexityAdjuster;
import com.ericsson.mssp.solution.service.ISolutionService;

/**
 * @author ekoukap
 *
 */
@Service("complexityAdjusterServiceImpl")
//@Scope("prototype")
public class ComplexityAdjusterServiceImpl extends BaseDAOImpl implements IComplexityAdjuster {

	final static Logger logger = LoggerFactory.getLogger(ComplexityAdjusterServiceImpl.class);
	
	public ComplexityAdjusterServiceImpl(){
		super();
	}
	
	 // Initiate complexity adjuster dao impl
	 
	@Autowired
	//@Qualifier("solutionComplexityDAOImpl")
	private ISolutionComplexityDAO persistancImpl;
	
	@Autowired
	private ISolutionService solutionService;
	
	@Override
	public SolutionComplexity loadSolutionComplexity(Integer solutionId,SolutionComplexity solutionComplexity,Integer opportunityId)
			throws MSSPException {
		Solution solution = (Solution)solutionService.getSolutionById(solutionId);
		solutionComplexity.setSolution(solution);
		SolutionComplexity solutionComplexitytest = persistancImpl.load(solutionId,solutionComplexity,opportunityId);
		return solutionComplexitytest;
	}

	@Override
	public List<Apacomplexity> loadApacomplexity(Integer solutionId,Integer opportunityId)
			throws MSSPException {
		return persistancImpl.loadApacomplexity(solutionId,opportunityId);
	}

	@Override
	public int serviceDeliveryYear(Integer SolutionId, Integer OpportunityId)throws MSSPException {
		int serviceDeliveryYear = 0;
		try {
			serviceDeliveryYear = persistancImpl.getServiceDeliveryYearByOpportunityID(OpportunityId);
		} catch (MSSPException e) {
			logger.error(e.getMessage() + " |  " + e.getCause());
		    throw new MSSPException(e.getMessage() + " |  " + e.getCause());
		}
		return serviceDeliveryYear;
	}

	@Override
	public void saveComplexityAdjuster(SolutionComplexity solutionComplexity,
			Integer SolutionId, List<Apacomplexity> apacomplexityList,
			Integer opportunityId) throws MSSPException {
				
		int updateId;
		try {
			Solution solution = (Solution)solutionService.getSolutionById(SolutionId);
			solutionComplexity.setSolution(solution);
			updateId = persistancImpl.update(solutionComplexity);
			solutionComplexity.setSolutionComplexityId(updateId);
			if(apacomplexityList != null){
				for(Apacomplexity apacomplexity : apacomplexityList){
					//System.out.println(apacomplexity.getApacomplexityId()+" saveComplexityAdjuster "+apacomplexity.getAdjustedHrs());
					apacomplexity.setSolution(solution);
				}
				persistancImpl.updateAPA(apacomplexityList, opportunityId, SolutionId);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage() + " |  " + e.getCause());
		    throw new MSSPException(e.getMessage() + " |  " + e.getCause());
		}
	}

	@Override
	public Map<String, String> startUpFTE(SolutionComplexity solutionComplexity,
			Integer SolutionId, Integer opportunityId,
			List<ServiceScopeDTO> listOfServiceScope,
			Integer serviceDeliveryYear) throws MSSPException {
			Map<String,String> STFTMap = new HashMap<String,String>();
	        try {
	            STFTMap = persistancImpl.startUpFTEDAOImpl(solutionComplexity, opportunityId, SolutionId, listOfServiceScope,serviceDeliveryYear);
	        } catch (Exception e) {
	            logger.error(e.getMessage() + " |  " + e.getCause());
	            throw new MSSPException(e.getMessage() + " |  " + e.getCause());
	        }
	        return STFTMap;
	}

	@Override
	public ComplexityAdjusterForm loadComplexityAdjusterForm(
			SolutionComplexity solutionComplexity, Integer solutionId,
			List<Apacomplexity> apacomplexityList,
			List<ServiceScopeDTO> listOfServiceScope, Integer opportunityId)
			throws MSSPException {
		ComplexityAdjusterForm complexityAdjusterForm = new ComplexityAdjusterForm();
		try {
			complexityAdjusterForm = persistancImpl.loadComplexityAdjusterForm(solutionId, solutionComplexity, opportunityId, listOfServiceScope);
		} catch (MSSPException e) {
			logger.error(e.getMessage() + " |  " + e.getCause());
		    throw new MSSPException(e.getMessage() + " |  " + e.getCause());
		}
		return complexityAdjusterForm;
	}

}
