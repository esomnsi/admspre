package com.ericsson.mssp.jobrole.management.dao;

import java.util.List;

import com.ericsson.mssp.common.dto.JobRoleDTO;
import com.ericsson.mssp.common.entity.JobRole;
import com.ericsson.mssp.common.entity.JobRoleStages;
import com.ericsson.mssp.common.entity.JobStage;

public interface IJobRoleManagementDAO {
	
	public List<JobRole> getJobRoles(JobRoleDTO jobRoleDTO);
	public List<JobStage> getJobStagesList();
	public void saveJobRole(JobRole jobRole, String[] jobStageIdList);		
	public JobRole viewJobRole(Integer jobRoleID);
	public List<JobRoleStages> getJobStagesByJobRoleID(Integer jobRoleID);
	public List<JobStage> getSelectedJobStageList(List<Integer> listJobStageId);
}
