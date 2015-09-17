package com.ericsson.mssp.jobrole.management.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ericsson.mssp.common.dto.JobRoleDTO;
import com.ericsson.mssp.common.dto.JobRoleStagesDTO;
import com.ericsson.mssp.common.dto.JobStageDTO;
import com.ericsson.mssp.common.entity.JobRole;
import com.ericsson.mssp.common.entity.JobRoleStages;
import com.ericsson.mssp.common.entity.JobStage;
import com.ericsson.mssp.jobrole.management.dao.IJobRoleManagementDAO;
import com.ericsson.mssp.jobrole.management.service.IJobRoleManagementService;

@Service
public class JobRoleManagementServiceImpl implements IJobRoleManagementService {

	@Autowired
	IJobRoleManagementDAO jobRoleManagementDAO;
	
	@Override
	public List<JobRoleDTO> getJobRoles(JobRoleDTO searchJobRoleDTO) {
		 List<JobRole> listJobRoles = jobRoleManagementDAO.getJobRoles(searchJobRoleDTO);
		 List<JobRoleDTO> listJobRoleDTO = new ArrayList<JobRoleDTO>();
		 for(JobRole jobRole : listJobRoles){
			 JobRoleDTO jobRoleDTO = new JobRoleDTO();
			 BeanUtils.copyProperties(jobRole,jobRoleDTO);
			 listJobRoleDTO.add(jobRoleDTO);
		 }
		return listJobRoleDTO;
	}

	@Override
	public List<JobStageDTO> getJobStageList() {
		 List<JobStage> list = jobRoleManagementDAO.getJobStagesList();
		 List<JobStageDTO> listJobStageDTO = new ArrayList<JobStageDTO>();
		 for(JobStage jobStage : list){
				JobStageDTO jobStageDTO = new JobStageDTO();
				BeanUtils.copyProperties(jobStage,jobStageDTO);
				listJobStageDTO.add(jobStageDTO);
			}
		return listJobStageDTO;
	}

	@Override
	public void saveJobRole(JobRoleDTO jobRoleDTO, String[] jobStageIdList) {
		JobRole jobRole = new JobRole();
		BeanUtils.copyProperties(jobRoleDTO, jobRole);
		jobRoleManagementDAO.saveJobRole(jobRole, jobStageIdList);
		
	}

	@Override
	public JobRoleDTO viewJobRole(Integer JobRoleID) {
		JobRoleDTO jobRoleDTO = new JobRoleDTO();
		JobRole jobRole = new JobRole();
		jobRole = jobRoleManagementDAO.viewJobRole(JobRoleID);
		BeanUtils.copyProperties(jobRole,jobRoleDTO);
		return jobRoleDTO;
	}

	@Override
	public List<JobRoleStagesDTO> getJobRoleStagesByJobRoleId(Integer jobRoleID) {
		List<JobRoleStages> listJobRoleStages = jobRoleManagementDAO.getJobStagesByJobRoleID(jobRoleID);
		List<JobRoleStagesDTO> listJobRoleStagesDTO = new ArrayList<JobRoleStagesDTO>();
		listJobRoleStagesDTO = convertListJobRoleStagesToListJobRoleStagesDTO(listJobRoleStages);
		
		return listJobRoleStagesDTO;
	}
	
	public List<JobRoleStagesDTO> convertListJobRoleStagesToListJobRoleStagesDTO(List<JobRoleStages> listJobRoleStages){
		List<JobRoleStagesDTO> listJobRoleStagesDTO = new ArrayList<JobRoleStagesDTO>();
		for(JobRoleStages jobRoleStages : listJobRoleStages){
			JobRoleStagesDTO jobRoleStagesDTO = new JobRoleStagesDTO();
			jobRoleStagesDTO = convertJobRoleStagesToJobRoleStagesDTO(jobRoleStages);
			listJobRoleStagesDTO.add(jobRoleStagesDTO);
		}
		
		return listJobRoleStagesDTO;
	}
	private JobRoleStagesDTO convertJobRoleStagesToJobRoleStagesDTO(JobRoleStages jobRoleStages){
		JobRoleStagesDTO jobRoleStagesDTO = new JobRoleStagesDTO();
		JobRoleDTO jobRoleDTO = new JobRoleDTO();
		JobStageDTO jobStageDTO = new JobStageDTO();
		BeanUtils.copyProperties(jobRoleStages.getJobRole(),jobRoleDTO);
		BeanUtils.copyProperties(jobRoleStages.getJobStage(), jobStageDTO);
		jobRoleStagesDTO.setJobRoleDTO(jobRoleDTO);
		jobRoleStagesDTO.setJobStageDTO(jobStageDTO);
		jobRoleStagesDTO.setJobRoleStagesId(jobRoleStages.getJobRoleStagesId());
		return jobRoleStagesDTO;
	}
	@Override
	public List<JobStageDTO> getSelectedJobStageList(List<Integer> listJobStageId) {
		List<JobStage> list = jobRoleManagementDAO.getSelectedJobStageList(listJobStageId);
		List<JobStageDTO> listJobStageDTO = new ArrayList<JobStageDTO>();
		for(JobStage jobStage : list){
			JobStageDTO jobStageDTO = new JobStageDTO();
			BeanUtils.copyProperties(jobStage,jobStageDTO);
			listJobStageDTO.add(jobStageDTO);
		}
		
		return listJobStageDTO;
	}

}
