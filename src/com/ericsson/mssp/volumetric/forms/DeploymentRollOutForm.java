package com.ericsson.mssp.volumetric.forms;

import com.ericsson.mssp.common.dto.DeploymentRollOutDTO;

public class DeploymentRollOutForm {
	
	private DeploymentRollOutDTO deploymentRollOutDTO;
	private Integer opportunityScopeID;
	
	
	public DeploymentRollOutDTO getDeploymentRollOutDTO() {
		return deploymentRollOutDTO;
	}
	public void setDeploymentRollOutDTO(DeploymentRollOutDTO deploymentRollOutDTO) {
		this.deploymentRollOutDTO = deploymentRollOutDTO;
	}
	public Integer getOpportunityScopeID() {
		return opportunityScopeID;
	}
	public void setOpportunityScopeID(Integer opportunityScopeID) {
		this.opportunityScopeID = opportunityScopeID;
	}
	
	

}
