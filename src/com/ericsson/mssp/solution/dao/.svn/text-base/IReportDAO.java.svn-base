/**
* -------------------------------------------------------------------------------------------------------
* Copyright (C) 2012 Ericsson India Global Pvt Limited
* All Rights Reserved
* Project         		    :  IT_MSSP
* Module				    :  com.ericsson.mssp.solution.dao
* File name       		    :  IReportDAO.java
* Description				:	<To Do>
* Author, Date & Release	:	Jan 10, 20132013
* Modification history		:
* Number	|Date   	   |Author        |Remark
* -----------------------------------------------------------------------------------------------------
* 1      	| Jan 10, 2013  	   |eruvwyn   	  | Created.
* -----------------------------------------------------------------------------------------------------
**/
 
package com.ericsson.mssp.solution.dao;

import java.util.List;

import com.ericsson.mssp.common.dao.IBaseDAO;
import com.ericsson.mssp.common.dto.FTEStagingDTO;
import com.ericsson.mssp.common.dto.SolutionADRDTO;
import com.ericsson.mssp.common.entity.ServiceScope;
import com.ericsson.mssp.common.exception.DAOException;

/**
 * @author eruvwyn
 *
 */
public interface IReportDAO extends IBaseDAO{
	List<ServiceScope> getAllServiceScope();

	List<Object[]> getAllFTEStagingDetailBySolId(Integer solId, String location ,String subLocation);

	List<SolutionADRDTO> getADRReportDetails(Integer solutionID,
			String adrCategory);

	public String getCurrencyCode(Integer opportunityID) throws DAOException;
	
	public List<FTEStagingDTO> getFTEStaingDataForContractYear(Integer solId,Integer serviceScopeId,String startDate, String endDate);
}
