/**
* -------------------------------------------------------------------------------------------------------
* Copyright (C) 2012 Ericsson India Global Pvt Limited
* All Rights Reserved
* Project         		    :  IT_MSSP
* Module				    :  com.ericsson.mssp.solution.forms
* File name       		    :  StaffingPlanForm.java
* Description				:	<To Do>
* Author, Date & Release	:	Dec 27, 20122012
* Modification history		:
* Number	|Date   	   |Author        |Remark
* -----------------------------------------------------------------------------------------------------
* 1      	| Dec 27, 2012  	   |eruvwyn   	  | Created.
* -----------------------------------------------------------------------------------------------------
**/
 
package com.ericsson.mssp.solution.forms;

/**
 * @author eruvwyn
 *
 */
public class StaffingPlanForm extends StaffBaseForm {

	private String[] region;
	private String[] vertical;
	private String[] egibu;
	private String[] client;
	private String[] opportunity;
	private String[] projectType;
	/**
	 * @return the region
	 */
	public String[] getRegion() {
		return region;
	}
	/**
	 * @param region the region to set
	 */
	public void setRegion(String[] region) {
		this.region = region;
	}
	/**
	 * @return the vertical
	 */
	public String[] getVertical() {
		return vertical;
	}
	/**
	 * @param vertical the vertical to set
	 */
	public void setVertical(String[] vertical) {
		this.vertical = vertical;
	}
	/**
	 * @return the egibu
	 */
	public String[] getEgibu() {
		return egibu;
	}
	/**
	 * @param egibu the egibu to set
	 */
	public void setEgibu(String[] egibu) {
		this.egibu = egibu;
	}
	/**
	 * @return the client
	 */
	public String[] getClient() {
		return client;
	}
	/**
	 * @param client the client to set
	 */
	public void setClient(String[] client) {
		this.client = client;
	}
	/**
	 * @return the opportunity
	 */
	public String[] getOpportunity() {
		return opportunity;
	}
	/**
	 * @param opportunity the opportunity to set
	 */
	public void setOpportunity(String[] opportunity) {
		this.opportunity = opportunity;
	}
	/**
	 * @return the projectType
	 */
	public String[] getProjectType() {
		return projectType;
	}
	/**
	 * @param projectType the projectType to set
	 */
	public void setProjectType(String[] projectType) {
		this.projectType = projectType;
	}

}
