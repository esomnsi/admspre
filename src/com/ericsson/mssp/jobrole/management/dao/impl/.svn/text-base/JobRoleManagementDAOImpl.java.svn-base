package com.ericsson.mssp.jobrole.management.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Service;

import com.ericsson.mssp.common.dao.impl.BaseDAOImpl;
import com.ericsson.mssp.common.dto.JobRoleDTO;
import com.ericsson.mssp.common.dto.JobRoleStagesDTO;
import com.ericsson.mssp.common.dto.JobStageDTO;
import com.ericsson.mssp.common.entity.AccessManagement;
import com.ericsson.mssp.common.entity.JobRole;
import com.ericsson.mssp.common.entity.JobRoleStages;
import com.ericsson.mssp.common.entity.JobStage;
import com.ericsson.mssp.jobrole.management.dao.IJobRoleManagementDAO;

@Service
public class JobRoleManagementDAOImpl extends BaseDAOImpl implements IJobRoleManagementDAO {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<JobRole> getJobRoles(JobRoleDTO jobRoleDTO) {
		List<JobRole> listJobRoles = new ArrayList<JobRole>();
		
		String usedByFA = "";
		String HRMSObject = "";
		String role = "";
		String jobRoleFamily = "";
		String jobRoleFamilyNumber = "";
		String jobRoleId = "";
		
		jobRoleId = (jobRoleDTO.getJobRoleId()!=null && !Integer.toString(jobRoleDTO.getJobRoleId()).equals("")) ? "%"+Integer.toString(jobRoleDTO.getJobRoleId())+"%" :"%" ;
		role = (checkNullOrBlank(jobRoleDTO.getRole())) ? wrapValue(jobRoleDTO.getRole()) : "%";
		HRMSObject = (checkNullOrBlank(jobRoleDTO.getHRMSObject())) ? wrapValue(jobRoleDTO.getHRMSObject()) : "%";
		jobRoleFamily = (checkNullOrBlank(jobRoleDTO.getJobRoleFamily())) ? wrapValue(jobRoleDTO.getJobRoleFamily()) : "%";
		jobRoleFamilyNumber = (checkNullOrBlank(jobRoleDTO.getJobRoleFamilyNumber())) ? wrapValue(jobRoleDTO.getJobRoleFamilyNumber()) : "%";
		usedByFA = (checkNullOrBlank(jobRoleDTO.getUsedByFA())) ? wrapValue(jobRoleDTO.getUsedByFA()) : "%";
		
		String query = "from JobRole where JobRoleID like ? and RoleName like ? and HRMSObject like ? and JobRoleFamily like ? and JobRoleFamilyNumber like ? and UsedByFA like ? order by JobRoleID";
		Object[] values = {jobRoleId,role,HRMSObject,jobRoleFamily,jobRoleFamilyNumber,usedByFA};
		logger.info("query : " + query);
		listJobRoles  = getHibernateTemplate().find(query,values);
		return listJobRoles;
	}
	
	private boolean checkNullOrBlank(String value){
		if (value != null && !value.equals("")){
			return true;
		}else{
			return false;
		}
	}
	
	private String wrapValue(String value){
		return "%"+value+"%";
	}

	@Override
	public List<JobStage> getJobStagesList() {
		String query = "from JobStage";
		List<JobStage> list = (List<JobStage>) getHibernateTemplate().find(query);
		return list;
	}

	@Override
	public void saveJobRole(JobRole jobRole, String[] jobStageIdList) {
		// saving data in JobRole Table
		saveObject(jobRole);
		Integer id = jobRole.getJobRoleId();
		logger.info("Job Role Id = "+id);
		
		// Saving data in JobRoleStages table.
		// 1. first we delete any old jobrolestage if present in DB.
		final String query  = "delete from JobRoleStages where JobRoleID = "+id;
		Integer deltdCount = (Integer) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						SQLQuery sq = session.createSQLQuery(query);
						int counts = sq.executeUpdate();
						return Integer.valueOf(counts);
					}

				});
		logger.info(deltdCount + " records deleted");
		
		// 2. Now if the user has input any new Job Stage to be added, only then we insert in table.
		if(jobStageIdList != null && jobStageIdList.length>0){
			
			for(String jobStageId : jobStageIdList ){
				JobRoleStages jobRoleStages = new JobRoleStages();
				JobStage jobStage = new JobStage();
				jobStage.setJobStageID(Integer.parseInt(jobStageId));
				jobRoleStages.setJobStage(jobStage);
				jobRoleStages.setJobRole(jobRole);
				saveObject(jobRoleStages);
			}
		}
	}

	@Override
	public JobRole viewJobRole(Integer jobRoleID) {
		String query = "from JobRole where JobRoleID="+jobRoleID;
		List<JobRole> list = getHibernateTemplate().find(query);		
		return list.get(0);
	}
	
	@Override
	public List<JobRoleStages> getJobStagesByJobRoleID(Integer jobRoleID) {
		String query = "from JobRoleStages where JobRoleID="+jobRoleID;
		List<JobRoleStages> list = getHibernateTemplate().find(query);		
		return list;
	}

	@Override
	public List<JobStage> getSelectedJobStageList(List<Integer> listJobStageId) {
		StringBuilder sb = new StringBuilder();
		for(Integer jobStageId : listJobStageId){
			sb.append("'"+jobStageId+"',");
		}
		String str = sb.substring(0, sb.length()-1);
		String query = "from JobStage where JobStageId IN ("+str+")";
		List<JobStage> list = getHibernateTemplate().find(query);
		return list;
	}

}


 
 