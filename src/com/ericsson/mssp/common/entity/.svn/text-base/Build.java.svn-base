package com.ericsson.mssp.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Build")
public class Build {
	
	private Integer buildId;
	private Integer createPlan;
	private Integer writeScript;
	private Integer identifyDependency;
	private Integer buildBinary;
	private Integer totalBuildHours;
	private Solution solution;
	private OpportunityScope opportunityScope;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="BuildID")
	public Integer getBuildId() {
		return buildId;
	}
	public void setBuildId(Integer buildId) {
		this.buildId = buildId;
	}
	
	@Column(name="CreatePlan")
	public Integer getCreatePlan() {
		return createPlan;
	}
	public void setCreatePlan(Integer createPlan) {
		this.createPlan = createPlan;
	}
	
	@Column(name="WriteScript")
	public Integer getWriteScript() {
		return writeScript;
	}
	public void setWriteScript(Integer writeScript) {
		this.writeScript = writeScript;
	}
	
	@Column(name="IdentifyDependency")
	public Integer getIdentifyDependency() {
		return identifyDependency;
	}
	public void setIdentifyDependency(Integer identifyDependency) {
		this.identifyDependency = identifyDependency;
	}
	
	@Column(name="BuildBinary")
	public Integer getBuildBinary() {
		return buildBinary;
	}
	public void setBuildBinary(Integer buildBinary) {
		this.buildBinary = buildBinary;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SolutionID")
	public Solution getSolution() {
		return solution;
	}
	public void setSolution(Solution solution) {
		this.solution = solution;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="OpportunityScopeID")
	public OpportunityScope getOpportunityScope() {
		return opportunityScope;
	}
	public void setOpportunityScope(OpportunityScope opportunityScope) {
		this.opportunityScope = opportunityScope;
	}
	
	@Column(name = "TotalBuildHours")
	public Integer getTotalBuildHours() {
		return totalBuildHours;
	}
	public void setTotalBuildHours(Integer totalBuildHours) {
		this.totalBuildHours = totalBuildHours;
	}
	
	

}
