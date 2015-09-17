/**
* -------------------------------------------------------------------------------------------------------
* Copyright (C) 2012 Ericsson India Global Pvt Limited
* All Rights Reserved
* Project         		    :  IT_MSSP
* Module				    :  com.ericsson.mssp.common.dto
* File name       		    :  ChangeManagementDTO.java
* Description				:	<To Do>
* Author, Date & Release	:	May 07, 2014 &2014
* Modification history		:
* Number	|Date   	   |Author        |Remark
* -----------------------------------------------------------------------------------------------------
* 1      	|  May 07, 2014  	   |ekanpah   	  | Created.
* -----------------------------------------------------------------------------------------------------
**/
 
package com.ericsson.mssp.common.dto;

import java.io.Serializable;


/**
 * @author eanujau
 *
 */
public class ChangeManagementDTO implements Serializable{
	
	private static final long serialVersionUID = 6179434180303840833L;
	private Integer changeManagementID;
	private float reqGatheringAndAnalysis;
	private float feasibilityImpactAndCostAnalysis;
	private float approveChangesAndPlanning;
	private float ensureStandards;
	private float manageTraceability;
	private float totalChangeManagementHours;
	
	public Integer getChangeManagementID() {
		return changeManagementID;
	}
	public void setChangeManagementID(Integer changeManagementID) {
		this.changeManagementID = changeManagementID;
	}
	public float getReqGatheringAndAnalysis() {
		return reqGatheringAndAnalysis;
	}
	public void setReqGatheringAndAnalysis(float reqGatheringAndAnalysis) {
		this.reqGatheringAndAnalysis = reqGatheringAndAnalysis;
	}
	public float getFeasibilityImpactAndCostAnalysis() {
		return feasibilityImpactAndCostAnalysis;
	}
	public void setFeasibilityImpactAndCostAnalysis(
			float feasibilityImpactAndCostAnalysis) {
		this.feasibilityImpactAndCostAnalysis = feasibilityImpactAndCostAnalysis;
	}
	public float getApproveChangesAndPlanning() {
		return approveChangesAndPlanning;
	}
	public void setApproveChangesAndPlanning(float approveChangesAndPlanning) {
		this.approveChangesAndPlanning = approveChangesAndPlanning;
	}
	public float getEnsureStandards() {
		return ensureStandards;
	}
	public void setEnsureStandards(float ensureStandards) {
		this.ensureStandards = ensureStandards;
	}
	public float getManageTraceability() {
		return manageTraceability;
	}
	public void setManageTraceability(float manageTraceability) {
		this.manageTraceability = manageTraceability;
	}
	public float getTotalChangeManagementHours() {
		return totalChangeManagementHours;
	}
	public void setTotalChangeManagementHours(float totalChangeManagementHours) {
		this.totalChangeManagementHours = totalChangeManagementHours;
	}
	
	
	
}
