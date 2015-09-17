package com.ericsson.mssp.common.dto;


public class JobRoleStagesDTO {

	private Integer jobRoleStagesId;
	private JobRoleDTO jobRoleDTO;
	private JobStageDTO jobStageDTO;
	
	
	public Integer getJobRoleStagesId() {
		return jobRoleStagesId;
	}
	public void setJobRoleStagesId(Integer jobRoleStagesId) {
		this.jobRoleStagesId = jobRoleStagesId;
	}
	public JobRoleDTO getJobRoleDTO() {
		return jobRoleDTO;
	}
	public void setJobRoleDTO(JobRoleDTO jobRoleDTO) {
		this.jobRoleDTO = jobRoleDTO;
	}
	public JobStageDTO getJobStageDTO() {
		return jobStageDTO;
	}
	public void setJobStageDTO(JobStageDTO jobStageDTO) {
		this.jobStageDTO = jobStageDTO;
	}
	
	
}
