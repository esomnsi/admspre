package com.ericsson.mssp.common.dto;

import java.util.Date;


public class RateCardDTO {

	private Double 				rate;
	private CountryDTO 			country;
	private String 				location;
	private String 				createdBy;
	private String 				updatedBy;
	private Integer 			rateCardId;
	private Date 				createdTimestamp;
	private Date 				updatedTimestamp;
	private JobRoleStagesDTO 	jobRoleStages;
	private String 				gsc;
	private String 				subLocation;
	private String				statusFlag;
	
	
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	public CountryDTO getCountry() {
		return country;
	}
	public void setCountry(CountryDTO country) {
		this.country = country;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Integer getRateCardId() {
		return rateCardId;
	}
	public void setRateCardId(Integer rateCardId) {
		this.rateCardId = rateCardId;
	}
	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}
	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}
	public Date getUpdatedTimestamp() {
		return updatedTimestamp;
	}
	public void setUpdatedTimestamp(Date updatedTimestamp) {
		this.updatedTimestamp = updatedTimestamp;
	}
	
	public String getGsc() {
		return gsc;
	}
	public void setGsc(String gsc) {
		this.gsc = gsc;
	}
	public String getSubLocation() {
		return subLocation;
	}
	public void setSubLocation(String subLocation) {
		this.subLocation = subLocation;
	}
	public String getStatusFlag() {
		return statusFlag;
	}
	public void setStatusFlag(String statusFlag) {
		this.statusFlag = statusFlag;
	}
	public JobRoleStagesDTO getJobRoleStages() {
		return jobRoleStages;
	}
	public void setJobRoleStages(JobRoleStagesDTO jobRoleStages) {
		this.jobRoleStages = jobRoleStages;
	}
	
}
