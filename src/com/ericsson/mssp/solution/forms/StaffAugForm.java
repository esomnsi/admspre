/**
* -------------------------------------------------------------------------------------------------------
* Copyright (C) 2012 Ericsson India Global Pvt Limited
* All Rights Reserved
* Project         		    :  IT_MSSP
* Module				    :  com.ericsson.mssp.solution.forms
* File name       		    :  StaffAugForm.java
* Description				:	<To Do>
* Author, Date & Release	:	Dec 20, 20122012
* Modification history		:
* Number	|Date   	   |Author        |Remark
* -----------------------------------------------------------------------------------------------------
* 1      	| Dec 20, 2012  	   |eruvwyn   	  | Created.
* -----------------------------------------------------------------------------------------------------
**/
 
package com.ericsson.mssp.solution.forms;

import java.util.Date;
import java.util.List;

import com.ericsson.mssp.common.dto.StaffAugDTO;
import com.ericsson.mssp.common.entity.JobRole;

/**
 * @author eruvwyn
 *
 */
public class StaffAugForm extends StaffBaseForm{
	

	private String region;
	private String vertical;
	private String client;
	private String opportunity;
	private List<JobRole> listJobRole;
	/**
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}
	/**
	 * @param region the region to set
	 */
	public void setRegion(String region) {
		this.region = region;
	}
	/**
	 * @return the vertical
	 */
	public String getVertical() {
		return vertical;
	}
	/**
	 * @param vertical the vertical to set
	 */
	public void setVertical(String vertical) {
		this.vertical = vertical;
	}
	/**
	 * @return the client
	 */
	public String getClient() {
		return client;
	}
	/**
	 * @param client the client to set
	 */
	public void setClient(String client) {
		this.client = client;
	}
	/**
	 * @return the opportunity
	 */
	public String getOpportunity() {
		return opportunity;
	}
	/**
	 * @param opportunity the opportunity to set
	 */
	public void setOpportunity(String opportunity) {
		this.opportunity = opportunity;
	}
	/**
	 * @return the listJobRole
	 */
	public List<JobRole> getListJobRole() {
		return listJobRole;
	}
	/**
	 * @param listJobRole the listJobRole to set
	 */
	public void setListJobRole(List<JobRole> listJobRole) {
		this.listJobRole = listJobRole;
	}
}
