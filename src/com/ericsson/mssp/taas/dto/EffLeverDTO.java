package com.ericsson.mssp.taas.dto;

import java.io.Serializable;

import com.ericsson.mssp.common.entity.Solution;

public class EffLeverDTO implements Serializable 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5884397141422070629L;
	private Integer efficiencyLeverId;
	private Solution solution;
	private Float avgNoTestCases;
	private String releaseType;
	private String testType;
	private Float pcautomationDesign;
	private Float testDesignReuse;
	private Float testExecutionCycles;
	private Float asistestExecutionCycles;
	
	public Integer getEfficiencyLeverId() {
		return efficiencyLeverId;
	}
	public void setEfficiencyLeverId(Integer efficiencyLeverId) {
		this.efficiencyLeverId = efficiencyLeverId;
	}
	public Solution getSolution() {
		return solution;
	}
	public void setSolution(Solution solution) {
		this.solution = solution;
	}
	public Float getAvgNoTestCases() {
		return avgNoTestCases;
	}
	public void setAvgNoTestCases(Float avgNoTestCases) {
		this.avgNoTestCases = avgNoTestCases;
	}
	public String getReleaseType() {
		return releaseType;
	}
	public void setReleaseType(String releaseType) {
		this.releaseType = releaseType;
	}
	public String getTestType() {
		return testType;
	}
	public void setTestType(String testType) {
		this.testType = testType;
	}
	public Float getPcautomationDesign() {
		return pcautomationDesign;
	}
	public void setPcautomationDesign(Float pcautomationDesign) {
		this.pcautomationDesign = pcautomationDesign;
	}
	public Float getTestDesignReuse() {
		return testDesignReuse;
	}
	public void setTestDesignReuse(Float testDesignReuse) {
		this.testDesignReuse = testDesignReuse;
	}
	public Float getTestExecutionCycles() {
		return testExecutionCycles;
	}
	public void setTestExecutionCycles(Float testExecutionCycles) {
		this.testExecutionCycles = testExecutionCycles;
	}
	public Float getAsistestExecutionCycles() {
		return asistestExecutionCycles;
	}
	public void setAsistestExecutionCycles(Float asistestExecutionCycles) {
		this.asistestExecutionCycles = asistestExecutionCycles;
	}
}
