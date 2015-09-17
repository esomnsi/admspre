/**
* -------------------------------------------------------------------------------------------------------
* Copyright (C) 2012 Ericsson India Global Pvt Limited
* All Rights Reserved
* Project         		    :  IT_MSSP
* Module				    :  com.ericsson.mssp.common.dto
* File name       		    :  SecurityManagementDTO.java
* Description				:	<To Do>
* Author, Date & Release	:	May 22, 2014 &2014
* Modification history		:
* Number	|Date   	   |Author        |Remark
* -----------------------------------------------------------------------------------------------------
* 1      	|  May 22, 2014  	   |ekanpah   	  | Created.
* -----------------------------------------------------------------------------------------------------
**/
 
package com.ericsson.mssp.common.dto;

import java.io.Serializable;

import com.ericsson.mssp.common.entity.OpportunityScope;
import com.ericsson.mssp.common.entity.Solution;


/**
 * @author eanujau
 *
 */
public class SecurityManagementDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8103817758330399675L;
	private Integer securityManagementID;
	private float manageSecurity;
	private float fteSecurityManagement;
	
	public Integer getSecurityManagementID() {
		return securityManagementID;
	}
	public void setSecurityManagementID(Integer securityManagementID) {
		this.securityManagementID = securityManagementID;
	}
	public float getManageSecurity() {
		return manageSecurity;
	}
	public void setManageSecurity(float manageSecurity) {
		this.manageSecurity = manageSecurity;
	}
	public float getFteSecurityManagement() {
		return fteSecurityManagement;
	}
	public void setFteSecurityManagement(float fteSecurityManagement) {
		this.fteSecurityManagement = fteSecurityManagement;
	} 
	
	
}

