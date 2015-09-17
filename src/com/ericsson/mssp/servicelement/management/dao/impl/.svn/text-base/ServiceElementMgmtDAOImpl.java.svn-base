package com.ericsson.mssp.servicelement.management.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ericsson.mssp.common.dao.impl.BaseDAOImpl;
import com.ericsson.mssp.common.entity.JobRoleStages;
import com.ericsson.mssp.common.entity.JobStage;
import com.ericsson.mssp.common.entity.ServiceElement;
import com.ericsson.mssp.common.entity.ServiceElementJobRoleStages;
import com.ericsson.mssp.servicelement.management.dao.IServiceElementMgmtDAO;

@Service
public class ServiceElementMgmtDAOImpl extends BaseDAOImpl implements IServiceElementMgmtDAO{
	
	

	@Override
	public List<ServiceElement> getServiceElements() {
		String query = "from ServiceElement";
		List<ServiceElement> list = getHibernateTemplate().find(query);
		return list;
	}

	@Override
	public List<ServiceElementJobRoleStages> getServiceElementJobRoleStagesByServEleId(
			Integer serviceElementId) {
		String query = "from ServiceElementJobRoleStages where ServiceElementID = "+serviceElementId;
		List<ServiceElementJobRoleStages> list = (List<ServiceElementJobRoleStages>) getHibernateTemplate().find(query);
		return list;
	}

	@Override
	public List<JobRoleStages> getAllJobRoleStages() {
		String query = "from JobRoleStages";
		List<JobRoleStages> list = (List<JobRoleStages>) getHibernateTemplate().find(query);
		return list;
	}

	@Override
	public void saveServiceElementJobRoleMapping(
			ServiceElementJobRoleStages serviceElementJobRoleStages) {
		saveObject(serviceElementJobRoleStages);
		
	}

	@Override
	public List<JobRoleStages> getAvailableJobRoleStages(List<Integer> mappedJobRoleStages) {
		List<JobRoleStages> list = new ArrayList<JobRoleStages>();
		if(mappedJobRoleStages.size() > 0){
		StringBuilder sb = new StringBuilder();
		for(Integer jobRoleStageId : mappedJobRoleStages){
			sb.append("'"+jobRoleStageId+"',");
		}
		String str = sb.substring(0, sb.length()-1);
		String query = "from JobRoleStages where JobRoleStageID NOT IN ("+str+")";
		logger.info("QUERY = "+query);
		list = getHibernateTemplate().find(query);
		}else{
			String query = "from JobRoleStages";
			list = getHibernateTemplate().find(query);
		}
		return list;
	}

	@Override
	public void updateServiceElementJobRoleMapping(
			ServiceElementJobRoleStages entity) {
		saveObject(entity);
		
	}

}
