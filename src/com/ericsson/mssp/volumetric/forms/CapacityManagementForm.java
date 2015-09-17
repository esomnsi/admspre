/**
* -------------------------------------------------------------------------------------------------------
* Copyright (C) 2012 Ericsson India Global Pvt Limited
* All Rights Reserved
* Project         		    :  IT_MSSP
* Module				    :  com.ericsson.mssp.solution.forms
* File name       		    :  CapacityManagementForm.java
* Description				:	<To Do>
* Author, Date & Release	:	May 19,20142014
* Modification history		:
* Number	|Date   	   |Author        |Remark
* -----------------------------------------------------------------------------------------------------
* 1      	| May 19, 2014 	   |ekanpah   	  | Created.
* -----------------------------------------------------------------------------------------------------
**/
 
package com.ericsson.mssp.volumetric.forms;

import com.ericsson.mssp.common.dto.CapacityManagementDTO;;


/**
 * @author eanujau
 *
 */
public class CapacityManagementForm {
	
	private CapacityManagementDTO capacityManagementDTO;
	private Integer opportunityScopeId;
	
	/**
	 * @return the opportunityScopeId
	 */
	public Integer getOpportunityScopeId() {
		return this.opportunityScopeId;
	}

	/**
	 * @param opportunityScopeId the opportunityScopeId to set
	 */
	public void setOpportunityScopeId(Integer opportunityScopeId) {
		this.opportunityScopeId = opportunityScopeId;
	}

	/**
	 * @return the capacityManagementDTO
	 */
	public CapacityManagementDTO getCapacityManagementDTO() {
		return this.capacityManagementDTO;
	}

	/**
	 * @param capacityManagementDTO the capacityManagementDTO to set
	 */
	public void setCapacityManagementDTO(CapacityManagementDTO capacityManagementDTO) {
		this.capacityManagementDTO = capacityManagementDTO;
	}

	
}

