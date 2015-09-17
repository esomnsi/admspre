package com.ericsson.mssp.solution.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ericsson.mssp.common.dao.impl.BaseDAOImpl;
import com.ericsson.mssp.common.entity.JobRoleStages;
import com.ericsson.mssp.common.exception.DAOException;
import com.ericsson.mssp.solution.dao.IJobRoleStagesDAO;

@Repository
public class JobRoleStagesDAOImpl extends BaseDAOImpl implements IJobRoleStagesDAO{

	@Override
	@SuppressWarnings("unchecked")
	public JobRoleStages getJobRoleStagesByjobRoleStagesId(Integer jobRoleStagesId) throws DAOException{
		JobRoleStages jobRoleStages = null;
		List<JobRoleStages> list = null;
		list = getHibernateTemplate().find(" from JobRoleStages where jobRoleStagesId="+ jobRoleStagesId );
		if (list!=null && list.size() > 0) {
			jobRoleStages = list.get(0);
		}
		return jobRoleStages;
	}

}
