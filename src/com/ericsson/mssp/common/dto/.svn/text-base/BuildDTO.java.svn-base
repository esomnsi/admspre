package com.ericsson.mssp.common.dto;

import java.io.Serializable;

import javax.persistence.Column;

public class BuildDTO implements Serializable{
	
	/**
	 * @author eanujau
	 */
	private static final long serialVersionUID = 6868605569128259251L;
	
	private Integer buildId;
	private Integer createPlan;
	private Integer writeScript;
	private Integer identifyDependency;
	private Integer buildBinary;
	private Integer totalBuildHours;
	
	@Column(name="TotalBuildHours")
	public Integer getTotalBuildHours() {
		return totalBuildHours;
	}
	public void setTotalBuildHours(Integer totalBuildHours) {
		this.totalBuildHours = totalBuildHours;
	}
	private SolutionDTO solutionDTO;
	private OpportunityScopeDTO opportunityScopeDTO;
	
	public Integer getBuildId() {
		return buildId;
	}
	public void setBuildId(Integer buildId) {
		this.buildId = buildId;
	}
	public Integer getCreatePlan() {
		return createPlan;
	}
	public void setCreatePlan(Integer createPlan) {
		this.createPlan = createPlan;
	}
	public Integer getWriteScript() {
		return writeScript;
	}
	public void setWriteScript(Integer writeScript) {
		this.writeScript = writeScript;
	}
	public Integer getIdentifyDependency() {
		return identifyDependency;
	}
	public void setIdentifyDependency(Integer identifyDependency) {
		this.identifyDependency = identifyDependency;
	}
	public Integer getBuildBinary() {
		return buildBinary;
	}
	public void setBuildBinary(Integer buildBinary) {
		this.buildBinary = buildBinary;
	}
	public SolutionDTO getSolutionDTO() {
		return solutionDTO;
	}
	public void setSolutionDTO(SolutionDTO solutionDTO) {
		this.solutionDTO = solutionDTO;
	}
	public OpportunityScopeDTO getOpportunityScopeDTO() {
		return opportunityScopeDTO;
	}
	public void setOpportunityScopeDTO(OpportunityScopeDTO opportunityScopeDTO) {
		this.opportunityScopeDTO = opportunityScopeDTO;
	}
	
	

}
