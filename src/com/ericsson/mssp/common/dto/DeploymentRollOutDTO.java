package com.ericsson.mssp.common.dto;



public class DeploymentRollOutDTO {

	/**
	 * @author eanujau
	 */
	
	private Integer deploymentRollOutId;
	private float opsBusinessTraining;
	private float deploySoftwareInstln;
	private float deployInstlnPlanExec;
	private float dataMigration;
	private float legacySwitchOff;
	private float totalDeploymentRollOutHours;
	
	public Integer getDeploymentRollOutId() {
		return deploymentRollOutId;
	}
	public void setDeploymentRollOutId(Integer deploymentRollOutId) {
		this.deploymentRollOutId = deploymentRollOutId;
	}
	public float getOpsBusinessTraining() {
		return opsBusinessTraining;
	}
	public void setOpsBusinessTraining(float opsBusinessTraining) {
		this.opsBusinessTraining = opsBusinessTraining;
	}
	public float getDeploySoftwareInstln() {
		return deploySoftwareInstln;
	}
	public void setDeploySoftwareInstln(float deploySoftwareInstln) {
		this.deploySoftwareInstln = deploySoftwareInstln;
	}
	public float getDeployInstlnPlanExec() {
		return deployInstlnPlanExec;
	}
	public void setDeployInstlnPlanExec(float deployInstlnPlanExec) {
		this.deployInstlnPlanExec = deployInstlnPlanExec;
	}
	public float getDataMigration() {
		return dataMigration;
	}
	public void setDataMigration(float dataMigration) {
		this.dataMigration = dataMigration;
	}
	public float getLegacySwitchOff() {
		return legacySwitchOff;
	}
	public void setLegacySwitchOff(float legacySwitchOff) {
		this.legacySwitchOff = legacySwitchOff;
	}
	public float getTotalDeploymentRollOutHours() {
		return totalDeploymentRollOutHours;
	}
	public void setTotalDeploymentRollOutHours(float totalDeploymentRollOutHours) {
		this.totalDeploymentRollOutHours = totalDeploymentRollOutHours;
	}
	
	
	
}
