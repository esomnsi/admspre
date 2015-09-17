/**
* -------------------------------------------------------------------------------------------------------
* Copyright (C) 2012 Ericsson India Global Pvt Limited
* All Rights Reserved
* Project         		    :  IT_MSSP
* Module				    :  com.ericsson.mssp.solution.service
* File name       		    :  IReportService.java
* Description				:	<To Do>
* Author, Date & Release	:	Jan 10, 20132013
* Modification history		:
* Number	|Date   	   |Author        |Remark
* -----------------------------------------------------------------------------------------------------
* 1      	| Jan 10, 2013  	   |eruvwyn   	  | Created.
* -----------------------------------------------------------------------------------------------------
**/
 
package com.ericsson.mssp.solution.service;

import java.util.List;
import java.util.Map;

import com.ericsson.mssp.common.dto.SolutionADRDTO;
import com.ericsson.mssp.common.entity.ServiceScope;
import com.ericsson.mssp.common.exception.MSSPException;

/**
 * @author eruvwyn
 *
 */
public interface IReportService {

	List<ServiceScope> getAllServiceScope();

	
	
	Map<String,Map<String,String[]>> getCostSummaryData(Integer solId, Integer oppId, boolean isCalendar);
	
	String[] getMonthYear(Integer oppId,boolean isCalendar);
	
	Map<Integer, String> getServiceScopes(Integer oppId);

	Map<String, Map<String,Map<String,String[]>>> getFTESummaryReportData(Integer solId, String[] interval, Map<Integer, String> serviceScopes,	String location,String subLocation) throws Exception;

	List<SolutionADRDTO> getADRReportDetails(Integer solutionID,
			String adrCategory);

	/**
	 * Returns the Currency for a particular Opportunity
	 * @param oppportunityID
	 * @return String
	 * @throws MSSPException
	 */
	public String getCurrencyCode(Integer oppportunityID) throws MSSPException;
}
