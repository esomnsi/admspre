/**
* -------------------------------------------------------------------------------------------------------
* Copyright (C) 2012 Ericsson India Global Pvt Limited
* All Rights Reserved
* Project         		    :  IT_MSSP
* Module				    :  com.ericsson.mssp.solution.forms
* File name       		    :  AvailabilityManagementForm.java
* Description				:	<To Do>
* Author, Date & Release	:	May 22,20142014
* Modification history		:
* Number	|Date   	   |Author        |Remark
* -----------------------------------------------------------------------------------------------------
* 1      	| May 22, 2014 	   |ekanpah   	  | Created.
* -----------------------------------------------------------------------------------------------------
**/
 
package com.ericsson.mssp.volumetric.forms;

import com.ericsson.mssp.common.dto.AvailabilityManagementDTO;;


/**
 * @author ekanpah
 *
 */
public class AvailabilityManagementForm {
	
	private AvailabilityManagementDTO availabilityManagementDTO;
	private Integer opportunityScopeId;
	
	public AvailabilityManagementDTO getAvailabilityManagementDTO() {
		return availabilityManagementDTO;
	}

	public void setAvailabilityManagementDTO(AvailabilityManagementDTO availabilityManagementDTO) {
		this.availabilityManagementDTO = availabilityManagementDTO;
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

