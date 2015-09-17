package com.ericsson.mssp.servicelement.management.service;

import java.util.List;

import com.ericsson.mssp.common.dto.JobRoleStagesDTO;
import com.ericsson.mssp.common.dto.ServiceElementDTO;
import com.ericsson.mssp.common.dto.ServiceElementJobRoleStagesDTO;

public interface IServiceElementManagementService {
	
	public List<ServiceElementDTO> getServiceElements();
	public List<ServiceElementJobRoleStagesDTO> getServiceElementJobRoleStagesByServEleId(Integer serviceElementId);
	public List<JobRoleStagesDTO> getAllJobRoleStages();
	public void saveServiceElementJobRoleMapping(ServiceElementJobRoleStagesDTO serviceElementJobRoleStagesDTO);
	public List<JobRoleStagesDTO> getAvailableJobRoleStages(List<Integer> mappedJobRoleStages);
	public void updateServiceElementJobRoleMapping(ServiceElementJobRoleStagesDTO serviceElementJobRoleStagesDTO);

}
