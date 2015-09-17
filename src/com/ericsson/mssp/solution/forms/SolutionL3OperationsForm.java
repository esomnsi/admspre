/**
 * -------------------------------------------------------------------------------------------------------
 * Copyright (C) 2012 Ericsson India Global Pvt Limited
 * All Rights Reserved
 * Project         		    :  IT_MSSP
 * Module				    :  com.ericsson.mssp.solution.forms
 * File name       		    :  LeveL3OpsForm.java
 * Description				:	This will be used for Service Level 1 View Data
 * Author, Date & Release	:	Jan 31, 20132013
 * Modification history		:
 * Number	|Date   	   |Author        |Remark
 * -----------------------------------------------------------------------------------------------------
 * 1      	| Jan 31, 2013  	   |edassri   	  | Created.
 * -----------------------------------------------------------------------------------------------------
 **/

package com.ericsson.mssp.solution.forms;


import com.ericsson.mssp.common.dto.SolutionL3AddServicesDTO;
import com.ericsson.mssp.common.dto.SolutionL3OperationsDTO;



/**
 * @author edassri
 * 
 */
public class SolutionL3OperationsForm {
	
	 private SolutionL3OperationsDTO solutionL3OperationsDTO;
	 private SolutionL3AddServicesDTO solutionL3AddServicesDTO;
	 private Integer solutionDimentionAttId;
	 private Integer opportunityScopeId;
	 
	/**
	 * @return the solutionL3OperationsDTO
	 */
	public SolutionL3OperationsDTO getSolutionL3OperationsDTO() {
		return solutionL3OperationsDTO;
	}
	/**
	 * @param solutionL3OperationsDTO the solutionL3OperationsDTO to set
	 */
	public void setSolutionL3OperationsDTO(
			SolutionL3OperationsDTO solutionL3OperationsDTO) {
		this.solutionL3OperationsDTO = solutionL3OperationsDTO;
	}
	/**
	 * @return the solutionL3AddServicesDTO
	 */
	public SolutionL3AddServicesDTO getSolutionL3AddServicesDTO() {
		return solutionL3AddServicesDTO;
	}
	/**
	 * @param solutionL3AddServicesDTO the solutionL3AddServicesDTO to set
	 */
	public void setSolutionL3AddServicesDTO(
			SolutionL3AddServicesDTO solutionL3AddServicesDTO) {
		this.solutionL3AddServicesDTO = solutionL3AddServicesDTO;
	}
	/**
	 * @return the solutionDimentionAttId
	 */
	public Integer getSolutionDimentionAttId() {
		return solutionDimentionAttId;
	}
	/**
	 * @param solutionDimentionAttId the solutionDimentionAttId to set
	 */
	public void setSolutionDimentionAttId(Integer solutionDimentionAttId) {
		this.solutionDimentionAttId = solutionDimentionAttId;
	}
	/**
	 * @return the opportunityScopeId
	 */
	public Integer getOpportunityScopeId() {
		return opportunityScopeId;
	}
	/**
	 * @param opportunityScopeId the opportunityScopeId to set
	 */
	public void setOpportunityScopeId(Integer opportunityScopeId) {
		this.opportunityScopeId = opportunityScopeId;
	}
	
}
