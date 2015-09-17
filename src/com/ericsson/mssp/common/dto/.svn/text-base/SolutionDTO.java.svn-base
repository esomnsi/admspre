/**
* -------------------------------------------------------------------------------------------------------
* Copyright (C) 2012 Ericsson India Global Pvt Limited
* All Rights Reserved
* Project         		    :  IT_MSSP
* Module				    :  com.ericsson.mssp.common.dto
* File name       		    :  DefineScope.java
* Description				:	<To Do>
* Author, Date & Release	:	Dec 11, 20122012
* Modification history		:
* Number	|Date   	   |Author        |Remark
* -----------------------------------------------------------------------------------------------------
* 1      	| Dec 11, 2012  	   |egaivij   	  | Created.
* -----------------------------------------------------------------------------------------------------
**/
 
package com.ericsson.mssp.common.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.ericsson.mssp.common.constant.MSSPConstants;
import com.ericsson.mssp.common.entity.AdditionalFTE;
import com.ericsson.mssp.common.entity.FTEStaging;
import com.ericsson.mssp.common.entity.NonLabourCost;
import com.ericsson.mssp.common.entity.Opportunity;
import com.ericsson.mssp.common.entity.SolutionADR;
import com.ericsson.mssp.common.entity.SolutionAPA;
import com.ericsson.mssp.common.entity.SolutionComplexity;
import com.ericsson.mssp.common.entity.SolutionLever;
import com.ericsson.mssp.common.entity.StaffingPlan;
import com.ericsson.mssp.common.entity.Status;

/**
 * @author egaivij
 *
 */
public class SolutionDTO extends BaseDTO{
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer solutionId;
	private Status status;
	private Opportunity opportunity;
	private Date submissionDate;
	private Date updatedDate;
	private String submittedBy;
	private String solutionApproach;
	private String solutionType;
	private String stepCompleted;
	private Integer solutionVersion;
	
	//for dimention attribute handling 
	private String dimentionAttributesString;
	private String  deleltedDimAttributesIds;
	private String newDimAttributesList;
	
	private String jobRoleModel="CCM";
	private String utilizationPerYear=MSSPConstants.DEFAULT_UTILIZATION_PER_YEAR;
	
	private SolutionApproachDimensionDTO solutionApproachDimensionDTO = new SolutionApproachDimensionDTO();
	
	private SolutionServiceDeskDTO serviceDeskDTO = new SolutionServiceDeskDTO();
	
	private Set<SolutionLever> solutionLevers = new HashSet<SolutionLever>(0);
	private Set<FTEStaging> ftestagings = new HashSet<FTEStaging>(0);
	private Set<SolutionComplexity> solutionComplexities = new HashSet<SolutionComplexity>(0);
	private Set<SolutionApproachDimensionDTO> solutionApproachDimensionDTOs = new HashSet<SolutionApproachDimensionDTO>(0);
	private Set<SolutionADR> solutionAdrs = new HashSet<SolutionADR>(0);
	private Set<NonLabourCost> nonLabourCosts = new HashSet<NonLabourCost>(0);
	private Set<AdditionalFTE> additionalFtes = new HashSet<AdditionalFTE>(0);
	private Set<StaffingPlan> staffingPlans = new HashSet<StaffingPlan>(0);
	private Set<SolutionAPA> solutionApas = new HashSet<SolutionAPA>(0);
	/**
	 * @return the solutionId
	 */
	public Integer getSolutionId() {
		return solutionId;
	}
	/**
	 * @param solutionId the solutionId to set
	 */
	public void setSolutionId(Integer solutionId) {
		this.solutionId = solutionId;
	}
	/**
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}
	/**
	 * @return the opportunity
	 */
	public Opportunity getOpportunity() {
		return opportunity;
	}
	/**
	 * @param opportunity the opportunity to set
	 */
	public void setOpportunity(Opportunity opportunity) {
		this.opportunity = opportunity;
	}
	/**
	 * @return the submissionDate
	 */
	public Date getSubmissionDate() {
		return submissionDate;
	}
	/**
	 * @param submissionDate the submissionDate to set
	 */
	public void setSubmissionDate(Date submissionDate) {
		this.submissionDate = submissionDate;
	}
	/**
	 * @return the submittedBy
	 */
	public String getSubmittedBy() {
		return submittedBy;
	}
	/**
	 * @param submittedBy the submittedBy to set
	 */
	public void setSubmittedBy(String submittedBy) {
		this.submittedBy = submittedBy;
	}
	/**
	 * @return the solutionApproach
	 */
	public String getSolutionApproach() {
		return solutionApproach;
	}
	/**
	 * @param solutionApproach the solutionApproach to set
	 */
	public void setSolutionApproach(String solutionApproach) {
		this.solutionApproach = solutionApproach;
	}
	/**
	 * @return the solutionType
	 */
	public String getSolutionType() {
		return solutionType;
	}
	/**
	 * @param solutionType the solutionType to set
	 */
	public void setSolutionType(String solutionType) {
		this.solutionType = solutionType;
	}
	/**
	 * @return the stepCompleted
	 */
	public String getStepCompleted() {
		return stepCompleted;
	}
	/**
	 * @param stepCompleted the stepCompleted to set
	 */
	public void setStepCompleted(String stepCompleted) {
		this.stepCompleted = stepCompleted;
	}
	/**
	 * @return the solutionVersion
	 */
	public Integer getSolutionVersion() {
		return solutionVersion;
	}
	/**
	 * @param solutionVersion the solutionVersion to set
	 */
	public void setSolutionVersion(Integer solutionVersion) {
		this.solutionVersion = solutionVersion;
	}
	/**
	 * @return the solutionLevers
	 */
	public Set<SolutionLever> getSolutionLevers() {
		return solutionLevers;
	}
	/**
	 * @param solutionLevers the solutionLevers to set
	 */
	public void setSolutionLevers(Set<SolutionLever> solutionLevers) {
		this.solutionLevers = solutionLevers;
	}
	/**
	 * @return the ftestagings
	 */
	public Set<FTEStaging> getFtestagings() {
		return ftestagings;
	}
	/**
	 * @param ftestagings the ftestagings to set
	 */
	public void setFtestagings(Set<FTEStaging> ftestagings) {
		this.ftestagings = ftestagings;
	}
	
	/**
	 * @return the solutionComplexities
	 */
	public Set<SolutionComplexity> getSolutionComplexities() {
		return solutionComplexities;
	}
	/**
	 * @param solutionComplexities the solutionComplexities to set
	 */
	public void setSolutionComplexities(Set<SolutionComplexity> solutionComplexities) {
		this.solutionComplexities = solutionComplexities;
	}
	
	/**
	 * @return the solutionAdrs
	 */
	public Set<SolutionADR> getSolutionAdrs() {
		return solutionAdrs;
	}
	/**
	 * @param solutionAdrs the solutionAdrs to set
	 */
	public void setSolutionAdrs(Set<SolutionADR> solutionAdrs) {
		this.solutionAdrs = solutionAdrs;
	}
	/**
	 * @return the nonLabourCosts
	 */
	public Set<NonLabourCost> getNonLabourCosts() {
		return nonLabourCosts;
	}
	/**
	 * @param nonLabourCosts the nonLabourCosts to set
	 */
	public void setNonLabourCosts(Set<NonLabourCost> nonLabourCosts) {
		this.nonLabourCosts = nonLabourCosts;
	}
	/**
	 * @return the additionalFtes
	 */
	public Set<AdditionalFTE> getAdditionalFtes() {
		return additionalFtes;
	}
	/**
	 * @param additionalFtes the additionalFtes to set
	 */
	public void setAdditionalFtes(Set<AdditionalFTE> additionalFtes) {
		this.additionalFtes = additionalFtes;
	}
	/**
	 * @return the staffingPlans
	 */
	public Set<StaffingPlan> getStaffingPlans() {
		return staffingPlans;
	}
	/**
	 * @param staffingPlans the staffingPlans to set
	 */
	public void setStaffingPlans(Set<StaffingPlan> staffingPlans) {
		this.staffingPlans = staffingPlans;
	}
	/**
	 * @return the solutionApas
	 */
	public Set<SolutionAPA> getSolutionApas() {
		return solutionApas;
	}
	/**
	 * @param solutionApas the solutionApas to set
	 */
	public void setSolutionApas(Set<SolutionAPA> solutionApas) {
		this.solutionApas = solutionApas;
	}
	/**
	 * @return the solutionApproachDimensionDTOs
	 */
	public Set<SolutionApproachDimensionDTO> getSolutionApproachDimensionDTOs() {
		return solutionApproachDimensionDTOs;
	}
	/**
	 * @param solutionApproachDimensionDTOs the solutionApproachDimensionDTOs to set
	 */
	public void setSolutionApproachDimensionDTOs(
			Set<SolutionApproachDimensionDTO> solutionApproachDimensionDTOs) {
		this.solutionApproachDimensionDTOs = solutionApproachDimensionDTOs;
	}
	/**
	 * @return the solutionApproachDimensionDTO
	 */
	public SolutionApproachDimensionDTO getSolutionApproachDimensionDTO() {
		return solutionApproachDimensionDTO;
	}
	/**
	 * @param solutionApproachDimensionDTO the solutionApproachDimensionDTO to set
	 */
	public void setSolutionApproachDimensionDTO(
			SolutionApproachDimensionDTO solutionApproachDimensionDTO) {
		this.solutionApproachDimensionDTO = solutionApproachDimensionDTO;
	}
	/**
	 * @return the dimentionAttributesString
	 */
	public String getDimentionAttributesString() {
		return dimentionAttributesString;
	}
	/**
	 * @param dimentionAttributesString the dimentionAttributesString to set
	 */
	public void setDimentionAttributesString(String dimentionAttributesString) {
		this.dimentionAttributesString = dimentionAttributesString;
	}
	/**
	 * @return the deleltedDimAttributesIds
	 */
	public String getDeleltedDimAttributesIds() {
		return deleltedDimAttributesIds;
	}
	/**
	 * @param deleltedDimAttributesIds the deleltedDimAttributesIds to set
	 */
	public void setDeleltedDimAttributesIds(String deleltedDimAttributesIds) {
		this.deleltedDimAttributesIds = deleltedDimAttributesIds;
	}
	/**
	 * @return the newDimAttributesList
	 */
	public String getNewDimAttributesList() {
		return newDimAttributesList;
	}
	/**
	 * @param newDimAttributesList the newDimAttributesList to set
	 */
	public void setNewDimAttributesList(String newDimAttributesList) {
		this.newDimAttributesList = newDimAttributesList;
	}
	/**
	 * @return the serviceDeskDTO
	 */
	public SolutionServiceDeskDTO getServiceDeskDTO() {
		return serviceDeskDTO;
	}
	/**
	 * @param serviceDeskDTO the serviceDeskDTO to set
	 */
	public void setServiceDeskDTO(SolutionServiceDeskDTO serviceDeskDTO) {
		this.serviceDeskDTO = serviceDeskDTO;
	}
	public String getJobRoleModel() {
		return jobRoleModel;
	}
	public void setJobRoleModel(String jobRoleModel) {
		this.jobRoleModel = jobRoleModel;
	}
	/**
	 * @return the utilizationPerYear
	 */
	public String getUtilizationPerYear() {
		return utilizationPerYear;
	}
	/**
	 * @param utilizationPerYear the utilizationPerYear to set
	 */
	public void setUtilizationPerYear(String utilizationPerYear) {
		this.utilizationPerYear = utilizationPerYear;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	
	
	
}
