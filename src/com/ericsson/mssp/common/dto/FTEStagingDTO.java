/**
* -------------------------------------------------------------------------------------------------------
* Copyright (C) 2012 Ericsson India Global Pvt Limited
* All Rights Reserved
* Project         		    :  IT_MSSP
* Module				    :  com.ericsson.mssp.common.dto
* File name       		    :  FTEStagingDTO.java
* Description				:	<To Do>
* Author, Date & Release	:	Dec 12, 20122012
* Modification history		:
* Number	|Date   	   |Author        |Remark
* -----------------------------------------------------------------------------------------------------
* 1      	| Dec 12, 2012  	   |eruvwyn   	  | Created.
* -----------------------------------------------------------------------------------------------------
**/
 
package com.ericsson.mssp.common.dto;

import java.util.Date;

/**
 * @author eruvwyn
 *
 */
public class FTEStagingDTO extends BaseDTO {

	private Long ftestagingId;
	private SolutionDTO solutionDTO;
	private OpportunityScopeDTO opportunityScopeDTO;
	private JobRoleStagesDTO jobRoleStagesDTO;
	private Date monthYear;
	private String location;
	private Float ftecount;
	private String subLocation;
	
		
	public Long getFtestagingId() {
		return ftestagingId;
	}
	public void setFtestagingId(Long ftestagingId) {
		this.ftestagingId = ftestagingId;
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
	public JobRoleStagesDTO getJobRoleStagesDTO() {
		return jobRoleStagesDTO;
	}
	public void setJobRoleStagesDTO(JobRoleStagesDTO jobRoleStagesDTO) {
		this.jobRoleStagesDTO = jobRoleStagesDTO;
	}
	public Date getMonthYear() {
		return monthYear;
	}
	public void setMonthYear(Date monthYear) {
		this.monthYear = monthYear;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Float getFtecount() {
		return ftecount;
	}
	public void setFtecount(Float ftecount) {
		this.ftecount = ftecount;
	}
	public String getSubLocation() {
		return subLocation;
	}
	public void setSubLocation(String subLocation) {
		this.subLocation = subLocation;
	}
}
