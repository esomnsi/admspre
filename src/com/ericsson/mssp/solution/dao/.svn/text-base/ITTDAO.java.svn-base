/**
* -------------------------------------------------------------------------------------------------------
* Copyright (C) 2012 Ericsson India Global Pvt Limited
* All Rights Reserved
* Project         		    :  IT_MSSP
* Module				    :  com.ericsson.mssp.solution.dao
* File name       		    :  ITTDAO.java
* Description				:	<To Do>
* Author, Date & Release	:	May 8, 20132013
* Modification history		:
* Number	|Date   	   |Author        |Remark
* -----------------------------------------------------------------------------------------------------
* 1      	| May 8, 2013  	   |egaivij   	  | Created.
* -----------------------------------------------------------------------------------------------------
**/
 
package com.ericsson.mssp.solution.dao;

import java.util.List;

import com.ericsson.mssp.common.dao.IBaseDAO;
import com.ericsson.mssp.common.entity.TTJobRoleDistribution;
import com.ericsson.mssp.common.entity.TTOnOffRatio;
import com.ericsson.mssp.common.entity.TTPartitionDetail;
import com.ericsson.mssp.common.entity.TTPartitionName;
import com.ericsson.mssp.common.entity.TTPlanning;
import com.ericsson.mssp.common.entity.TTSummaryStaging;
import com.ericsson.mssp.common.exception.MSSPException;
import com.ericsson.mssp.solution.forms.TTLaborCostForm;

/**
 * @author egaivij
 *
 */
public interface ITTDAO extends IBaseDAO{

	void savePlanning(TTPlanning ttPlanning)throws MSSPException;

	TTPlanning loadPartitionNames(Integer solutionId)throws MSSPException;

	void savePartitionDetail(TTPartitionDetail partitionDetail, TTOnOffRatio ttOnOffRatio) throws MSSPException;;
	
	 public List<TTPartitionName> getPartitionNames(Integer solutionID)throws MSSPException;
	    
	    public TTPartitionDetail getTtPartitionDetails(Integer partitionNameID)throws MSSPException;
	    
	    public List<TTJobRoleDistribution> getJobRoleDistributions(Integer solutionID)throws MSSPException;
	    
	    public List<TTSummaryStaging> getTtSummaryStagingData(String partitionNameIds)throws MSSPException;
	
		TTOnOffRatio getOnOffRatioByPartitionId(Integer selPartitionId)throws MSSPException;

	
		void saveSummaryStagingList(List<Object> summaryStagingList)throws MSSPException;

		void saveJobroleDistribution(List<Object> jobroleDistList)throws MSSPException;

		void deleteJobRoleDistributionByPlanningId(Integer ttPlanningId)throws MSSPException;

		void deletePlanningByPlanningId(Integer planningId)throws MSSPException;

		void saveTTLaborCost(TTLaborCostForm ttLaborCostForm,Integer solutionID,Integer opportunityID)throws MSSPException;

		void deleteSummaryStagingByPartitionId(Integer selPartitionId)throws MSSPException;

	
}
