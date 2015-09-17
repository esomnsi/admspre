/**
* -------------------------------------------------------------------------------------------------------
* Copyright (C) 2012 Ericsson India Global Pvt Limited
* All Rights Reserved
* Project         		    :  IT_MSSP
* Module				    :  com.ericsson.mssp.solution.service
* File name       		    :  ITTService.java
* Description				:	<To Do>
* Author, Date & Release	:	May 8, 20132013
* Modification history		:
* Number	|Date   	   |Author        |Remark
* -----------------------------------------------------------------------------------------------------
* 1      	| May 8, 2013  	   |egaivij   	  | Created.
* -----------------------------------------------------------------------------------------------------
**/
 
package com.ericsson.mssp.solution.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ericsson.mssp.common.dto.TTJobRoleDistributionDTO;
import com.ericsson.mssp.common.dto.TTLaborCostDTO;
import com.ericsson.mssp.common.dto.TTOnOffRatioDTO;
import com.ericsson.mssp.common.dto.TTPartitionDetailDTO;
import com.ericsson.mssp.common.dto.TTPartitionNameDTO;
import com.ericsson.mssp.common.dto.TTSummaryStagingDTO;
import com.ericsson.mssp.common.exception.MSSPException;
import com.ericsson.mssp.solution.forms.TTDetailForm;
import com.ericsson.mssp.solution.forms.TTJobRoleDistributionForm;
import com.ericsson.mssp.solution.forms.TTLaborCostForm;

/**
 * @author egaivij
 *
 */
public interface ITTService {

	void savePartitionNames(TTDetailForm ttDetailForm, Integer solutionId) throws MSSPException;

	void loadPartitionNames(Integer solutionId, TTDetailForm ttDetailForm) throws MSSPException;

	void saveFTEBreakup(TTDetailForm form,
			Integer selPartitionId) throws MSSPException;
	

	

	public List<TTPartitionNameDTO> getPartitionNames(Integer solutionID)throws MSSPException;
    
    public Map<String,Object> getTimeInterval(Integer solutionID, Date transformationStartDate, Date transformationEndDate)throws MSSPException;
    
    public List<TTJobRoleDistributionDTO> getSelectedJobRoleDistributionList(Integer solutionID)throws MSSPException;
    
    public List<TTSummaryStagingDTO> getTtSummaryStagingData(String partitionNameIds)throws MSSPException;

	public TTPartitionDetailDTO getPartitionDetailById(Integer selPartitionId)throws MSSPException;

	TTOnOffRatioDTO getOnOffRatioByPartitionId(Integer selPartitionId)throws MSSPException;

	void saveJobroleDistribution(TTJobRoleDistributionForm distributionForm) throws MSSPException;
	
	void saveTTLaborCost(TTLaborCostForm ttLaborCostForm,Integer solutionID, Integer opportunityID)throws MSSPException;
}
