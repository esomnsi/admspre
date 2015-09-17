/**
* -------------------------------------------------------------------------------------------------------
* Copyright (C) 2012 Ericsson India Global Pvt Limited
* All Rights Reserved
* Project         		    :  IT_MSSP
* Module				    :  com.ericsson.mssp.solution.forms
* File name       		    :  StaffBaseForm.java
* Description				:	<To Do>
* Author, Date & Release	:	Dec 27, 20122012
* Modification history		:
* Number	|Date   	   |Author        |Remark
* -----------------------------------------------------------------------------------------------------
* 1      	| Dec 27, 2012  	   |eruvwyn   	  | Created.
* -----------------------------------------------------------------------------------------------------
**/
 
package com.ericsson.mssp.solution.forms;

import java.util.List;

import com.ericsson.mssp.common.dto.StaffAugDTO;

/**
 * @author eruvwyn
 *
 */
public class StaffBaseForm {
	private String[] demandRaisedBy;
	private String[] demandCreateddate;
	private String[] status;
	private String[] egibu;
	private String[] projectType;
	private String[] gttopportunity;
	private String[] noDemandedPositions;
	private String[] acceptSubcontractors;
	private String[] winOddsProbability;
	private String[] primaryLocation;
	private String[] secondaryLocation;
	private String[] onshoreLocation;
	private String[] positionStartdate;
	private String[] positionEnddate;
	private String[] onshoreStartdate;
	private String[] onshoreEnddate;
	private String[] gradeLow;
	private String[] gradeHigh;
	private String[] jobFamily;
	private Integer[] jobRole;
	private String[] competency;
	private String[] practice;
	private String[] practiceArea;
	private String[] primarySkillSet;
	private String[] secondarySkillSet;
	private String[] typeofRequest;
	private String[] resourceManager;
	private String[] additionalInformation;
	/**
	 * @return the demandRaisedBy
	 */
	public String[] getDemandRaisedBy() {
		return demandRaisedBy;
	}
	/**
	 * @param demandRaisedBy the demandRaisedBy to set
	 */
	public void setDemandRaisedBy(String[] demandRaisedBy) {
		this.demandRaisedBy = demandRaisedBy;
	}
	/**
	 * @return the demandCreateddate
	 */
	public String[] getDemandCreateddate() {
		return demandCreateddate;
	}
	/**
	 * @param demandCreateddate the demandCreateddate to set
	 */
	public void setDemandCreateddate(String[] demandCreateddate) {
		this.demandCreateddate = demandCreateddate;
	}
	/**
	 * @return the status
	 */
	public String[] getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String[] status) {
		this.status = status;
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
	private List<StaffAugDTO> staffAugDTOList;
	/**
	 * @return the gttopportunity
	 */
	public String[] getGttopportunity() {
		return gttopportunity;
	}
	/**
	 * @param gttopportunity the gttopportunity to set
	 */
	public void setGttopportunity(String[] gttopportunity) {
		this.gttopportunity = gttopportunity;
	}
	/**
	 * @return the noDemandedPositions
	 */
	public String[] getNoDemandedPositions() {
		return noDemandedPositions;
	}
	/**
	 * @param noDemandedPositions the noDemandedPositions to set
	 */
	public void setNoDemandedPositions(String[] noDemandedPositions) {
		this.noDemandedPositions = noDemandedPositions;
	}
	/**
	 * @return the acceptSubcontractors
	 */
	public String[] getAcceptSubcontractors() {
		return acceptSubcontractors;
	}
	/**
	 * @param acceptSubcontractors the acceptSubcontractors to set
	 */
	public void setAcceptSubcontractors(String[] acceptSubcontractors) {
		this.acceptSubcontractors = acceptSubcontractors;
	}
	/**
	 * @return the winOddsProbability
	 */
	public String[] getWinOddsProbability() {
		return winOddsProbability;
	}
	/**
	 * @param winOddsProbability the winOddsProbability to set
	 */
	public void setWinOddsProbability(String[] winOddsProbability) {
		this.winOddsProbability = winOddsProbability;
	}
	/**
	 * @return the primaryLocation
	 */
	public String[] getPrimaryLocation() {
		return primaryLocation;
	}
	/**
	 * @param primaryLocation the primaryLocation to set
	 */
	public void setPrimaryLocation(String[] primaryLocation) {
		this.primaryLocation = primaryLocation;
	}
	/**
	 * @return the secondaryLocation
	 */
	public String[] getSecondaryLocation() {
		return secondaryLocation;
	}
	/**
	 * @param secondaryLocation the secondaryLocation to set
	 */
	public void setSecondaryLocation(String[] secondaryLocation) {
		this.secondaryLocation = secondaryLocation;
	}
	/**
	 * @return the onshoreLocation
	 */
	public String[] getOnshoreLocation() {
		return onshoreLocation;
	}
	/**
	 * @param onshoreLocation the onshoreLocation to set
	 */
	public void setOnshoreLocation(String[] onshoreLocation) {
		this.onshoreLocation = onshoreLocation;
	}
	/**
	 * @return the positionStartdate
	 */
	public String[] getPositionStartdate() {
		return positionStartdate;
	}
	/**
	 * @param positionStartdate the positionStartdate to set
	 */
	public void setPositionStartdate(String[] positionStartdate) {
		this.positionStartdate = positionStartdate;
	}
	/**
	 * @return the positionEnddate
	 */
	public String[] getPositionEnddate() {
		return positionEnddate;
	}
	/**
	 * @param positionEnddate the positionEnddate to set
	 */
	public void setPositionEnddate(String[] positionEnddate) {
		this.positionEnddate = positionEnddate;
	}
	/**
	 * @return the onshoreStartdate
	 */
	public String[] getOnshoreStartdate() {
		return onshoreStartdate;
	}
	/**
	 * @param onshoreStartdate the onshoreStartdate to set
	 */
	public void setOnshoreStartdate(String[] onshoreStartdate) {
		this.onshoreStartdate = onshoreStartdate;
	}
	/**
	 * @return the onshoreEnddate
	 */
	public String[] getOnshoreEnddate() {
		return onshoreEnddate;
	}
	/**
	 * @param onshoreEnddate the onshoreEnddate to set
	 */
	public void setOnshoreEnddate(String[] onshoreEnddate) {
		this.onshoreEnddate = onshoreEnddate;
	}
	/**
	 * @return the gradeLow
	 */
	public String[] getGradeLow() {
		return gradeLow;
	}
	/**
	 * @param gradeLow the gradeLow to set
	 */
	public void setGradeLow(String[] gradeLow) {
		this.gradeLow = gradeLow;
	}
	/**
	 * @return the gradeHigh
	 */
	public String[] getGradeHigh() {
		return gradeHigh;
	}
	/**
	 * @param gradeHigh the gradeHigh to set
	 */
	public void setGradeHigh(String[] gradeHigh) {
		this.gradeHigh = gradeHigh;
	}
	/**
	 * @return the jobFamily
	 */
	public String[] getJobFamily() {
		return jobFamily;
	}
	/**
	 * @param jobFamily the jobFamily to set
	 */
	public void setJobFamily(String[] jobFamily) {
		this.jobFamily = jobFamily;
	}
	/**
	 * @return the jobRole
	 */
	public Integer[] getJobRole() {
		return jobRole;
	}
	/**
	 * @param jobRole the jobRole to set
	 */
	public void setJobRole(Integer[] jobRole) {
		this.jobRole = jobRole;
	}
	/**
	 * @return the competency
	 */
	public String[] getCompetency() {
		return competency;
	}
	/**
	 * @param competency the competency to set
	 */
	public void setCompetency(String[] competency) {
		this.competency = competency;
	}
	/**
	 * @return the practice
	 */
	public String[] getPractice() {
		return practice;
	}
	/**
	 * @param practice the practice to set
	 */
	public void setPractice(String[] practice) {
		this.practice = practice;
	}
	/**
	 * @return the practiceArea
	 */
	public String[] getPracticeArea() {
		return practiceArea;
	}
	/**
	 * @param practiceArea the practiceArea to set
	 */
	public void setPracticeArea(String[] practiceArea) {
		this.practiceArea = practiceArea;
	}
	/**
	 * @return the primarySkillSet
	 */
	public String[] getPrimarySkillSet() {
		return primarySkillSet;
	}
	/**
	 * @param primarySkillSet the primarySkillSet to set
	 */
	public void setPrimarySkillSet(String[] primarySkillSet) {
		this.primarySkillSet = primarySkillSet;
	}
	/**
	 * @return the secondarySkillSet
	 */
	public String[] getSecondarySkillSet() {
		return secondarySkillSet;
	}
	/**
	 * @param secondarySkillSet the secondarySkillSet to set
	 */
	public void setSecondarySkillSet(String[] secondarySkillSet) {
		this.secondarySkillSet = secondarySkillSet;
	}
	/**
	 * @return the typeofRequest
	 */
	public String[] getTypeofRequest() {
		return typeofRequest;
	}
	/**
	 * @param typeofRequest the typeofRequest to set
	 */
	public void setTypeofRequest(String[] typeofRequest) {
		this.typeofRequest = typeofRequest;
	}
	/**
	 * @return the resourceManager
	 */
	public String[] getResourceManager() {
		return resourceManager;
	}
	/**
	 * @param resourceManager the resourceManager to set
	 */
	public void setResourceManager(String[] resourceManager) {
		this.resourceManager = resourceManager;
	}
	/**
	 * @return the additionalInformation
	 */
	public String[] getAdditionalInformation() {
		return additionalInformation;
	}
	/**
	 * @param additionalInformation the additionalInformation to set
	 */
	public void setAdditionalInformation(String[] additionalInformation) {
		this.additionalInformation = additionalInformation;
	}
	/**
	 * @return the staffAugDTOList
	 */
	public List<StaffAugDTO> getStaffAugDTOList() {
		return staffAugDTOList;
	}
	/**
	 * @param staffAugDTOList the staffAugDTOList to set
	 */
	public void setStaffAugDTOList(List<StaffAugDTO> staffAugDTOList) {
		this.staffAugDTOList = staffAugDTOList;
	}

}
