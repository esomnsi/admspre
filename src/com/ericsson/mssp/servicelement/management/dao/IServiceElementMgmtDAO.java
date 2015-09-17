package com.ericsson.mssp.servicelement.management.dao;

import java.util.List;

import com.ericsson.mssp.common.entity.JobRoleStages;
import com.ericsson.mssp.common.entity.ServiceElement;
import com.ericsson.mssp.common.entity.ServiceElementJobRoleStages;

public interface IServiceElementMgmtDAO {
	
	public List<ServiceElement> getServiceElements(); 
	public List<ServiceElementJobRoleStages> getServiceElementJobRoleStagesByServEleId(Integer serviceElementId);
	public List<JobRoleStages> getAllJobRoleStages();
	public void saveServiceElementJobRoleMapping(ServiceElementJobRoleStages serviceElementJobRoleStages);
	public List<JobRoleStages> getAvailableJobRoleStages(List<Integer> mappedJobRoleStages);
	public void updateServiceElementJobRoleMapping(ServiceElementJobRoleStages entity);

}
