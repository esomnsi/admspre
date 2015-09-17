/**
* -------------------------------------------------------------------------------------------------------
* Copyright (C) 2012 Ericsson India Global Pvt Limited
* All Rights Reserved
* Project         		    :  IT_MSSP
* Module				    :  com.ericsson.mssp.common.dto
* File name       		    :  SupportWindowMatrixDTO.java
* Description				:	<To Do>
* Author, Date & Release	:	Jan 23, 20132013
* Modification history		:
* Number	|Date   	   |Author        |Remark
* -----------------------------------------------------------------------------------------------------
* 1      	| Jan 23, 2013  	   |eruvwyn   	  | Created.
* -----------------------------------------------------------------------------------------------------
**/
 
package com.ericsson.mssp.common.dto;

/**
 * @author eruvwyn
 *
 */
public class SupportWindowMatrixDTO {
    private Integer supportWindowMatrixId;
    private String supportWindow;
    private Float derivedFactor;
	/**
	 * @return the supportWindowMatrixId
	 */
	public Integer getSupportWindowMatrixId() {
		return supportWindowMatrixId;
	}
	/**
	 * @param supportWindowMatrixId the supportWindowMatrixId to set
	 */
	public void setSupportWindowMatrixId(Integer supportWindowMatrixId) {
		this.supportWindowMatrixId = supportWindowMatrixId;
	}
	/**
	 * @return the supportWindow
	 */
	public String getSupportWindow() {
		return supportWindow;
	}
	/**
	 * @param supportWindow the supportWindow to set
	 */
	public void setSupportWindow(String supportWindow) {
		this.supportWindow = supportWindow;
	}
	/**
	 * @return the derivedFactor
	 */
	public Float getDerivedFactor() {
		return derivedFactor;
	}
	/**
	 * @param derivedFactor the derivedFactor to set
	 */
	public void setDerivedFactor(Float derivedFactor) {
		this.derivedFactor = derivedFactor;
	}

}
