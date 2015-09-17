package com.ericsson.mssp.jobrole.management.form;

import com.ericsson.mssp.common.dto.JobRoleDTO;

public class JobRoleForm {

	private JobRoleDTO jobRoleDTO;
	private String[] listJobStageId;

	public String[] getListJobStageId() {
		return listJobStageId;
	}

	public void setListJobStageId(String[] listJobStageId) {
		this.listJobStageId = listJobStageId;
	}
	public JobRoleDTO getJobRoleDTO() {
		return jobRoleDTO;
	}

	public void setJobRoleDTO(JobRoleDTO jobRoleDTO) {
		this.jobRoleDTO = jobRoleDTO;
	}

}
