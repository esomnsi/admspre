/**
* -------------------------------------------------------------------------------------------------------
* Copyright (C) 2012 Ericsson India Global Pvt Limited
* All Rights Reserved
* Project         		    :  IT_MSSP
* Module				    :  com.ericsson.mssp.solution.service
* File name       		    :  IComplexityAdjuster.java
* Description				:	<To Do>
* Author, Date & Release	:	Dec 11, 20122012
* Modification history		:
* Number	|Date   	   |Author        |Remark
* -----------------------------------------------------------------------------------------------------
* 1      	| Dec 11, 2012  	   |ekoukap   	  | Created.
* -----------------------------------------------------------------------------------------------------
**/
 
package com.ericsson.mssp.solution.service;

import java.util.List;
import java.util.Map;

import com.ericsson.mssp.common.dto.ServiceScopeDTO;
import com.ericsson.mssp.common.entity.Apacomplexity;
import com.ericsson.mssp.common.entity.SolutionComplexity;
import com.ericsson.mssp.common.exception.MSSPException;
import com.ericsson.mssp.solution.forms.ComplexityAdjusterForm;

/**
 * This interface contains the contract methods needed for business logic to
 * maintain Logged in user profile.
 * 
 * @author Ambition Team
 * 
 */
public interface IComplexityAdjuster {
	public ComplexityAdjusterForm loadComplexityAdjusterForm(SolutionComplexity solutionComplexity,Integer solutionId,List<Apacomplexity> apacomplexityList,List<ServiceScopeDTO> listOfServiceScope,Integer opportunityId)throws MSSPException;
	public SolutionComplexity loadSolutionComplexity(Integer solutionId,SolutionComplexity solutionComplexity,Integer opportunityId) throws MSSPException;
	public List<Apacomplexity> loadApacomplexity(Integer solutionId,Integer opportunityId) throws MSSPException;
	public Map<String,String> startUpFTE(SolutionComplexity solutionComplexity,Integer SolutionId,Integer opportunityId,List<ServiceScopeDTO> listOfServiceScope,Integer serviceDeliveryYear) throws MSSPException;
	public int serviceDeliveryYear(Integer SolutionId,Integer OpportunityId) throws MSSPException;
	public void saveComplexityAdjuster(SolutionComplexity solutionComplexity,Integer SolutionId,List<Apacomplexity> apacomplexityList,Integer opportunityId)throws MSSPException;
}
