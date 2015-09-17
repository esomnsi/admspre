package com.ericsson.mssp.currency.exchange.dto;

public class CurrencyExchangeDTO {
	
	private Integer jobRoleID;
	private Integer jobStageID;
	private String  jobRole;
	private String  jobStage;
	private Double  rate;
	private Integer countryID;
	private String selected;
	
	
	public String getSelected() {
		return selected;
	}
	public void setSelected(String selected) {
		this.selected = selected;
	}
	public Integer getCountryID() {
		return countryID;
	}
	public void setCountryID(Integer countryID) {
		this.countryID = countryID;
	}
	public String getJobRole() {
		return jobRole;
	}
	public void setJobRole(String jobRole) {
		this.jobRole = jobRole;
	}
	public String getJobStage() {
		return jobStage;
	}
	public void setJobStage(String jobStage) {
		this.jobStage = jobStage;
	}
	public Integer getJobRoleID() {
		return jobRoleID;
	}
	public void setJobRoleID(Integer jobRoleID) {
		this.jobRoleID = jobRoleID;
	}
	public Integer getJobStageID() {
		return jobStageID;
	}
	public void setJobStageID(Integer jobStageID) {
		this.jobStageID = jobStageID;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	
	
	
}
