/**
* -------------------------------------------------------------------------------------------------------
* Copyright (C) 2012 Ericsson India Global Pvt Limited
* All Rights Reserved
* Project         		    :  IT_MSSP
* Module				    :  com.ericsson.mssp.common.dto
* File name       		    :  AvailabilityManagementDTO.java
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
 * @author ekanpah
 *
 */
public class AvailabilityManagementDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5558978922105169187L;
	private Integer availabilityManagementID;
	private float manageAvailability;
	private float manageOutageAndRisks;
	private float fteAvailabilityManagement;
	
	public Integer getAvailabilityManagementID() {
		return availabilityManagementID;
	}
	public void setAvailabilityManagementID(Integer availabilityManagementID) {
		this.availabilityManagementID = availabilityManagementID;
	}
	public float getManageAvailability() {
		return manageAvailability;
	}
	public void setManageAvailability(float manageAvailability) {
		this.manageAvailability = manageAvailability;
	}
	public float getManageOutageAndRisks() {
		return manageOutageAndRisks;
	}
	public void setManageOutageAndRisks(float manageOutageAndRisks) {
		this.manageOutageAndRisks = manageOutageAndRisks;
	}
	public float getFteAvailabilityManagement() {
		return fteAvailabilityManagement;
	}
	public void setFteAvailabilityManagement(float fteAvailabilityManagement) {
		this.fteAvailabilityManagement = fteAvailabilityManagement;
	}
	
	
}

