package com.ericsson.mssp.jobrole.management.service;

import java.util.List;

import com.ericsson.mssp.common.dto.JobRoleDTO;
import com.ericsson.mssp.common.dto.JobRoleStagesDTO;
import com.ericsson.mssp.common.dto.JobStageDTO;

public interface IJobRoleManagementService {
	
	public List<JobRoleDTO> getJobRoles(JobRoleDTO jobRoleDTO);
	public List<JobStageDTO> getJobStageList();
	public void saveJobRole(JobRoleDTO jobRoleDTO , String[] jobStageIdList);
	public JobRoleDTO viewJobRole(Integer jobRoleID);
	public List<JobRoleStagesDTO> getJobRoleStagesByJobRoleId(Integer jobRoleID);
	public List<JobStageDTO> getSelectedJobStageList(List<Integer> listJobStageId);
}
