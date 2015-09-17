/**
* -------------------------------------------------------------------------------------------------------
* Copyright (C) 2012 Ericsson India Global Pvt Limited
* All Rights Reserved
* Project         		    :  IT_MSSP
* Module				    :  com.ericsson.mssp.solution.forms
* File name       		    :  ChangeManagementForm.java
* Description				:	<To Do>
* Author, Date & Release	:	May 07,20142014
* Modification history		:
* Number	|Date   	   |Author        |Remark
* -----------------------------------------------------------------------------------------------------
* 1      	| May 07, 2014 	   |ekanpah   	  | Created.
* -----------------------------------------------------------------------------------------------------
**/
 
package com.ericsson.mssp.volumetric.forms;

import com.ericsson.mssp.common.dto.ChangeManagementDTO;


/**
 * @author ekanpah
 *
 */
public class ChangeManagementForm {
	
	private ChangeManagementDTO changeManagementDTO;
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
	 * @return the changeManagementDTO
	 */
	public ChangeManagementDTO getChangeManagementDTO() {
		return this.changeManagementDTO;
	}

	/**
	 * @param changeManagementDTO the changeManagementDTO to set
	 */
	public void setChangeManagementDTO(ChangeManagementDTO changeManagementDTO) {
		this.changeManagementDTO = changeManagementDTO;
	}

}



