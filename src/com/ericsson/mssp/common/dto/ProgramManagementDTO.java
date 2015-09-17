/**
* -------------------------------------------------------------------------------------------------------
* Copyright (C) 2012 Ericsson India Global Pvt Limited
* All Rights Reserved
* Project         		    :  IT_MSSP
* Module				    :  com.ericsson.mssp.common.dto
* File name       		    :  ProgramManagementDTO.java
* Description				:	<To Do>
* Author, Date & Release	:	Apr 29, 2014 &2014
* Modification history		:
* Number	|Date   	   |Author        |Remark
* -----------------------------------------------------------------------------------------------------
* 1      	| Apr 29, 2014  	   |ekanpah   	  | Created.
* -----------------------------------------------------------------------------------------------------
**/
 
package com.ericsson.mssp.common.dto;

import java.io.Serializable;


/**
 * @author ekanpah
 *
 */
public class ProgramManagementDTO implements Serializable{

	    private static final long serialVersionUID = -5591137464178334254L;
	    private Integer programManagementID;
	    private float coordinateProjects;
		private float ensureAchievement;
		private float manageClient;
		private float manageQualityRiskIssues;
		private float measureADMServiceKPI;
		private float totalProgramManagementHours;
		
		public Integer getProgramManagementID() {
			return programManagementID;
		}
		public void setProgramManagementID(Integer programManagementID) {
			this.programManagementID = programManagementID;
		}
		public float getCoordinateProjects() {
			return coordinateProjects;
		}
		public void setCoordinateProjects(float coordinateProjects) {
			this.coordinateProjects = coordinateProjects;
		}
		public float getEnsureAchievement() {
			return ensureAchievement;
		}
		public void setEnsureAchievement(float ensureAchievement) {
			this.ensureAchievement = ensureAchievement;
		}
		public float getManageClient() {
			return manageClient;
		}
		public void setManageClient(float manageClient) {
			this.manageClient = manageClient;
		}
		public float getManageQualityRiskIssues() {
			return manageQualityRiskIssues;
		}
		public void setManageQualityRiskIssues(float manageQualityRiskIssues) {
			this.manageQualityRiskIssues = manageQualityRiskIssues;
		}
		public float getMeasureADMServiceKPI() {
			return measureADMServiceKPI;
		}
		public void setMeasureADMServiceKPI(float measureADMServiceKPI) {
			this.measureADMServiceKPI = measureADMServiceKPI;
		}
		public float getTotalProgramManagementHours() {
			return totalProgramManagementHours;
		}
		public void setTotalProgramManagementHours(float totalProgramManagementHours) {
			this.totalProgramManagementHours = totalProgramManagementHours;
		}
		
		
		   
}
