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
@Table(name="DeploymentRollOut")
public class DeploymentRollOut {
	
	private Integer deploymentRollOutId;
	private float opsBusinessTraining;
	private float deploySoftwareInstln;
	private float deployInstlnPlanExec;
	private float dataMigration;
	private float legacySwitchOff;
	private float totalDeploymentRollOutHours;
	private Solution solution;
	private OpportunityScope opportunityScope;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="DeploymentRollOutID")
	public Integer getDeploymentRollOutId() {
		return deploymentRollOutId;
	}
	public void setDeploymentRollOutId(Integer deploymentRollOutId) {
		this.deploymentRollOutId = deploymentRollOutId;
	}
	
	@Column(name="OpsBusinessTraining")	
	public float getOpsBusinessTraining() {
		return opsBusinessTraining;
	}
	public void setOpsBusinessTraining(float opsBusinessTraining) {
		this.opsBusinessTraining = opsBusinessTraining;
	}
	
	@Column(name="DeploySoftwareInstln")
	public float getDeploySoftwareInstln() {
		return deploySoftwareInstln;
	}
	public void setDeploySoftwareInstln(float deploySoftwareInstln) {
		this.deploySoftwareInstln = deploySoftwareInstln;
	}
	
	@Column(name="DeployInstlnPlanExec")
	public float getDeployInstlnPlanExec() {
		return deployInstlnPlanExec;
	}
	public void setDeployInstlnPlanExec(float deployInstlnPlanExec) {
		this.deployInstlnPlanExec = deployInstlnPlanExec;
	}
	
	@Column(name="DataMigration")
	public float getDataMigration() {
		return dataMigration;
	}
	public void setDataMigration(float dataMigration) {
		this.dataMigration = dataMigration;
	}
	
	@Column(name="LegacySwitchOff")
	public float getLegacySwitchOff() {
		return legacySwitchOff;
	}
	public void setLegacySwitchOff(float legacySwitchOff) {
		this.legacySwitchOff = legacySwitchOff;
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
	
	@Column(name="TotalDeploymentRollOutHours")
	public float getTotalDeploymentRollOutHours() {
		return totalDeploymentRollOutHours;
	}
	public void setTotalDeploymentRollOutHours(float totalDeploymentRollOutHours) {
		this.totalDeploymentRollOutHours = totalDeploymentRollOutHours;
	}
	
	

}
