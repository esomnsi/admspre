/**
 * -------------------------------------------------------------------------------------------------------
 * Copyright (C) 2012 Ericsson India Global Pvt Limited
 * All Rights Reserved
 * Project         		    :  IT_MSSP
 * Module				    :  com.ericsson.mssp.solution.forms
 * File name       		    :  LeveL2OpsForm.java
 * Description				:	This will be used for Service Level 1 View Data
 * Author, Date & Release	:	Jan 31, 20132013
 * Modification history		:
 * Number	|Date   	   |Author        |Remark
 * -----------------------------------------------------------------------------------------------------
 * 1      	| Jan 31, 2013  	   |edassri   	  | Created.
 * -----------------------------------------------------------------------------------------------------
 **/

package com.ericsson.mssp.solution.forms;

import com.ericsson.mssp.common.dto.SolutionL2AddServicesDTO;
import com.ericsson.mssp.common.dto.SolutionL2OperationsDTO;

/**
 * @author edassri
 * 
 */
public class SolutionL2OperationsForm {
	
    private Integer solutionDimentionAttId;
    private SolutionL2OperationsDTO solutionL2OperationsDTO;
    private SolutionL2AddServicesDTO solutionL2AddServicesDTO;
    private Integer opportunityScopeId;
    
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
	/**
     * @return the solutionL2OperationsDTO
     */
    public SolutionL2OperationsDTO getSolutionL2OperationsDTO() {
        return solutionL2OperationsDTO;
    }
    /**
     * @param solutionL2OperationsDTO the solutionL2OperationsDTO to set
     */
    public void setSolutionL2OperationsDTO(
    	SolutionL2OperationsDTO solutionL2OperationsDTO) {
        this.solutionL2OperationsDTO = solutionL2OperationsDTO;
    }
    /**
     * @return the solutionL2AddServicesDTO
     */
    public SolutionL2AddServicesDTO getSolutionL2AddServicesDTO() {
        return solutionL2AddServicesDTO;
    }
    /**
     * @param solutionL2AddServicesDTO the solutionL2AddServicesDTO to set
     */
    public void setSolutionL2AddServicesDTO(
    	SolutionL2AddServicesDTO solutionL2AddServicesDTO) {
        this.solutionL2AddServicesDTO = solutionL2AddServicesDTO;
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
    

}
