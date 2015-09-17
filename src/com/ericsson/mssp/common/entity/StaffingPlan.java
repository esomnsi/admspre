package com.ericsson.mssp.common.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * StaffingPlan generated by hbm2java
 */
@Entity
@Table(name = "StaffingPlan")
public class StaffingPlan implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -507310462111226547L;
	private String demandRaisedBy;
	private Date demandCreateddate;
	private String status;
	private Integer staffingPlanId;
	private Solution solution;
	private JobRole jobRole;
	private String region;
	private String vertical;
	private String egibu;
	private String client;
	private String opportunity;
	private String projectType;
	private String gttopportunity;
	private String noDemandedPositions;
	private String acceptSubcontractors;
	private String winOddsProbability;
	private String primaryLocation;
	private String secondaryLocation;
	private String onshoreLocation;
	private Date positionStartdate;
	private Date positionEnddate;
	private Date onshoreStartdate;
	private Date onshoreEnddate;
	private String gradeLow;
	private String gradeHigh;
	private String jobFamily;
	private String competency;
	private String practice;
	private String practiceArea;
	private String primarySkillSet;
	private String secondarySkillSet;
	private String typeofRequest;
	private String resourceManager;
	private String additionalInformation;

	public StaffingPlan() {
	}

	public StaffingPlan(Solution solution, JobRole jobRole) {
		this.solution = solution;
		this.jobRole = jobRole;
	}

	public StaffingPlan(String demandRaisedBy,Date demandCreateddate,String status,Solution solution, JobRole jobRole, String region,
			String vertical, String egibu, String client, String opportunity,
			String projectType, String gttopportunity,
			String noDemandedPositions, String acceptSubcontractors,
			String winOddsProbability, String primaryLocation,
			String secondaryLocation, String onshoreLocation,
			Date positionStartdate, Date positionEnddate,
			Date onshoreStartdate, Date onshoreEnddate, String gradeLow,
			String gradeHigh, String jobFamily, String competency,
			String practice, String practiceArea, String primarySkillSet,
			String secondarySkillSet, String typeofRequest,
			String resourceManager, String additionalInformation) {
		this.demandRaisedBy = demandRaisedBy;
		this.demandCreateddate = demandCreateddate;
		this.status = status;
		this.solution = solution;
		this.jobRole = jobRole;
		this.region = region;
		this.vertical = vertical;
		this.egibu = egibu;
		this.client = client;
		this.opportunity = opportunity;
		this.projectType = projectType;
		this.gttopportunity = gttopportunity;
		this.noDemandedPositions = noDemandedPositions;
		this.acceptSubcontractors = acceptSubcontractors;
		this.winOddsProbability = winOddsProbability;
		this.primaryLocation = primaryLocation;
		this.secondaryLocation = secondaryLocation;
		this.onshoreLocation = onshoreLocation;
		this.positionStartdate = positionStartdate;
		this.positionEnddate = positionEnddate;
		this.onshoreStartdate = onshoreStartdate;
		this.onshoreEnddate = onshoreEnddate;
		this.gradeLow = gradeLow;
		this.gradeHigh = gradeHigh;
		this.jobFamily = jobFamily;
		this.competency = competency;
		this.practice = practice;
		this.practiceArea = practiceArea;
		this.primarySkillSet = primarySkillSet;
		this.secondarySkillSet = secondarySkillSet;
		this.typeofRequest = typeofRequest;
		this.resourceManager = resourceManager;
		this.additionalInformation = additionalInformation;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "StaffingPlanID", unique = true, nullable = false)
	public Integer getStaffingPlanId() {
		return this.staffingPlanId;
	}

	public void setStaffingPlanId(Integer staffingPlanId) {
		this.staffingPlanId = staffingPlanId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SolutionID", nullable = false)
	public Solution getSolution() {
		return this.solution;
	}

	public void setSolution(Solution solution) {
		this.solution = solution;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "JobRoleID", nullable = false)
	public JobRole getJobRole() {
		return this.jobRole;
	}

	public void setJobRole(JobRole jobRole) {
		this.jobRole = jobRole;
	}
	@Column(name = "DemandRaisedBy" , length = 100)
	public String getDemandRaisedBy() {
		return demandRaisedBy;
	}

	
	public void setDemandRaisedBy(String demandRaisedBy) {
		this.demandRaisedBy = demandRaisedBy;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DemandCreateddate" , length = 19)
	public Date getDemandCreateddate() {
		return demandCreateddate;
	}

	
	public void setDemandCreateddate(Date demandCreateddate) {
		this.demandCreateddate = demandCreateddate;
	}
	@Column(name = "Status" , length = 40)
	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "Region", length = 4)
	public String getRegion() {
		return this.region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	@Column(name = "Vertical", length = 100)
	public String getVertical() {
		return this.vertical;
	}

	public void setVertical(String vertical) {
		this.vertical = vertical;
	}

	@Column(name = "EGIBU", length = 100)
	public String getEgibu() {
		return this.egibu;
	}

	public void setEgibu(String egibu) {
		this.egibu = egibu;
	}

	@Column(name = "Client", length = 100)
	public String getClient() {
		return this.client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	@Column(name = "Opportunity", length = 100)
	public String getOpportunity() {
		return this.opportunity;
	}

	public void setOpportunity(String opportunity) {
		this.opportunity = opportunity;
	}

	@Column(name = "ProjectType", length = 100)
	public String getProjectType() {
		return this.projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	@Column(name = "GTTOpportunity", length = 1)
	public String getGttopportunity() {
		return this.gttopportunity;
	}

	public void setGttopportunity(String gttopportunity) {
		this.gttopportunity = gttopportunity;
	}

	@Column(name = "NoDemandedPositions", length = 100)
	public String getNoDemandedPositions() {
		return this.noDemandedPositions;
	}

	public void setNoDemandedPositions(String noDemandedPositions) {
		this.noDemandedPositions = noDemandedPositions;
	}

	@Column(name = "AcceptSubcontractors", length = 1)
	public String getAcceptSubcontractors() {
		return this.acceptSubcontractors;
	}

	public void setAcceptSubcontractors(String acceptSubcontractors) {
		this.acceptSubcontractors = acceptSubcontractors;
	}

	@Column(name = "WinOddsProbability", length = 100)
	public String getWinOddsProbability() {
		return this.winOddsProbability;
	}

	public void setWinOddsProbability(String winOddsProbability) {
		this.winOddsProbability = winOddsProbability;
	}

	@Column(name = "PrimaryLocation", length = 100)
	public String getPrimaryLocation() {
		return this.primaryLocation;
	}

	public void setPrimaryLocation(String primaryLocation) {
		this.primaryLocation = primaryLocation;
	}

	@Column(name = "SecondaryLocation", length = 100)
	public String getSecondaryLocation() {
		return this.secondaryLocation;
	}

	public void setSecondaryLocation(String secondaryLocation) {
		this.secondaryLocation = secondaryLocation;
	}

	@Column(name = "OnshoreLocation", length = 100)
	public String getOnshoreLocation() {
		return this.onshoreLocation;
	}

	public void setOnshoreLocation(String onshoreLocation) {
		this.onshoreLocation = onshoreLocation;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PositionStartdate", length = 19)
	public Date getPositionStartdate() {
		return this.positionStartdate;
	}

	public void setPositionStartdate(Date positionStartdate) {
		this.positionStartdate = positionStartdate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PositionEnddate", length = 19)
	public Date getPositionEnddate() {
		return this.positionEnddate;
	}

	public void setPositionEnddate(Date positionEnddate) {
		this.positionEnddate = positionEnddate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "OnshoreStartdate", length = 19)
	public Date getOnshoreStartdate() {
		return this.onshoreStartdate;
	}

	public void setOnshoreStartdate(Date onshoreStartdate) {
		this.onshoreStartdate = onshoreStartdate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "OnshoreEnddate", length = 19)
	public Date getOnshoreEnddate() {
		return this.onshoreEnddate;
	}

	public void setOnshoreEnddate(Date onshoreEnddate) {
		this.onshoreEnddate = onshoreEnddate;
	}

	@Column(name = "GradeLow", length = 100)
	public String getGradeLow() {
		return this.gradeLow;
	}

	public void setGradeLow(String gradeLow) {
		this.gradeLow = gradeLow;
	}

	@Column(name = "GradeHigh", length = 100)
	public String getGradeHigh() {
		return this.gradeHigh;
	}

	public void setGradeHigh(String gradeHigh) {
		this.gradeHigh = gradeHigh;
	}

	@Column(name = "JobFamily", length = 100)
	public String getJobFamily() {
		return this.jobFamily;
	}

	public void setJobFamily(String jobFamily) {
		this.jobFamily = jobFamily;
	}

	@Column(name = "Competency", length = 100)
	public String getCompetency() {
		return this.competency;
	}

	public void setCompetency(String competency) {
		this.competency = competency;
	}

	@Column(name = "Practice", length = 100)
	public String getPractice() {
		return this.practice;
	}

	public void setPractice(String practice) {
		this.practice = practice;
	}

	@Column(name = "PracticeArea", length = 100)
	public String getPracticeArea() {
		return this.practiceArea;
	}

	public void setPracticeArea(String practiceArea) {
		this.practiceArea = practiceArea;
	}

	@Column(name = "PrimarySkillSet", length = 100)
	public String getPrimarySkillSet() {
		return this.primarySkillSet;
	}

	public void setPrimarySkillSet(String primarySkillSet) {
		this.primarySkillSet = primarySkillSet;
	}

	@Column(name = "SecondarySkillSet", length = 100)
	public String getSecondarySkillSet() {
		return this.secondarySkillSet;
	}

	public void setSecondarySkillSet(String secondarySkillSet) {
		this.secondarySkillSet = secondarySkillSet;
	}

	@Column(name = "TypeofRequest", length = 100)
	public String getTypeofRequest() {
		return this.typeofRequest;
	}

	public void setTypeofRequest(String typeofRequest) {
		this.typeofRequest = typeofRequest;
	}

	@Column(name = "ResourceManager", length = 100)
	public String getResourceManager() {
		return this.resourceManager;
	}

	public void setResourceManager(String resourceManager) {
		this.resourceManager = resourceManager;
	}

	@Column(name = "AdditionalInformation")
	public String getAdditionalInformation() {
		return this.additionalInformation;
	}

	public void setAdditionalInformation(String additionalInformation) {
		this.additionalInformation = additionalInformation;
	}

}