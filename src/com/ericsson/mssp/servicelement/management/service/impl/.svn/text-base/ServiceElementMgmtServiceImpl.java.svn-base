package com.ericsson.mssp.servicelement.management.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ericsson.mssp.common.dto.JobRoleStagesDTO;
import com.ericsson.mssp.common.dto.ServiceElementDTO;
import com.ericsson.mssp.common.dto.ServiceElementJobRoleStagesDTO;
import com.ericsson.mssp.common.entity.JobRoleStages;
import com.ericsson.mssp.common.entity.ServiceElement;
import com.ericsson.mssp.common.entity.ServiceElementJobRoleStages;
import com.ericsson.mssp.jobrole.management.service.impl.JobRoleManagementServiceImpl;
import com.ericsson.mssp.servicelement.management.dao.IServiceElementMgmtDAO;
import com.ericsson.mssp.servicelement.management.service.IServiceElementManagementService;

@Service
public class ServiceElementMgmtServiceImpl implements IServiceElementManagementService{

	@Autowired
	IServiceElementMgmtDAO serviceElementMgmtDAO;
	
	@Override
	public List<ServiceElementDTO> getServiceElements(){
		List<ServiceElementDTO> listServiceElementsDTO = new ArrayList<ServiceElementDTO>();
		List<ServiceElement> listServiceElementsEntity = serviceElementMgmtDAO.getServiceElements();
		listServiceElementsDTO = convertServEleEntityListToDTOServEleList(listServiceElementsEntity);
		return listServiceElementsDTO;
	}
	
	private List<ServiceElementDTO> convertServEleEntityListToDTOServEleList(List<ServiceElement> listServiceElementsEntity){
		List<ServiceElementDTO> listServiceElementsDTO = new ArrayList<ServiceElementDTO>();
			for(ServiceElement servEle : listServiceElementsEntity){
				ServiceElementDTO servEleDto = new ServiceElementDTO();
				servEleDto.setServiceElementID(servEle.getServiceElementID());
				servEleDto.setServiceElementName(servEle.getServiceElementName());
				listServiceElementsDTO.add(servEleDto);
			}
		return listServiceElementsDTO;
	}

	@Override
	public List<ServiceElementJobRoleStagesDTO> getServiceElementJobRoleStagesByServEleId(
			Integer serviceElementId) {
		List<ServiceElementJobRoleStages> entityList = serviceElementMgmtDAO.getServiceElementJobRoleStagesByServEleId(serviceElementId);
		List<ServiceElementJobRoleStagesDTO> dtoList = convertSEJRSEntityListToSEJRSDTOList(entityList);
		return dtoList;
	}
	
	private ServiceElementJobRoleStagesDTO convertServEleJobRoleStagesEntityToServEleJobRoleStagesDTO(ServiceElementJobRoleStages entity){
		ServiceElementJobRoleStagesDTO dto = new ServiceElementJobRoleStagesDTO();
		BeanUtils.copyProperties(entity, dto);
		return dto;
	}
	
	private List<ServiceElementJobRoleStagesDTO> convertSEJRSEntityListToSEJRSDTOList(List<ServiceElementJobRoleStages> entityList){
		List<ServiceElementJobRoleStagesDTO> dtoList = new ArrayList<ServiceElementJobRoleStagesDTO>();
		for(ServiceElementJobRoleStages entity : entityList){
			ServiceElementJobRoleStagesDTO dto = new ServiceElementJobRoleStagesDTO();
			BeanUtils.copyProperties(entity, dto);
			dtoList.add(dto);
		}		
		return dtoList;
	}

	@Override
	public List<JobRoleStagesDTO> getAllJobRoleStages() {
		List<JobRoleStages> entityList = serviceElementMgmtDAO.getAllJobRoleStages();
		JobRoleManagementServiceImpl obj = new JobRoleManagementServiceImpl();
		List<JobRoleStagesDTO> dtoList = obj.convertListJobRoleStagesToListJobRoleStagesDTO(entityList);
		return dtoList;
	}

	@Override
	public void saveServiceElementJobRoleMapping(ServiceElementJobRoleStagesDTO serviceElementJobRoleStagesDTO) {
		ServiceElementJobRoleStages entity = convertServEleJobRoleStagesDTOToEntity(serviceElementJobRoleStagesDTO);
		serviceElementMgmtDAO.saveServiceElementJobRoleMapping(entity);
		
	}
	
	private ServiceElementJobRoleStages convertServEleJobRoleStagesDTOToEntity(ServiceElementJobRoleStagesDTO dto){
		ServiceElementJobRoleStages entity = new ServiceElementJobRoleStages();
		
		BeanUtils.copyProperties(dto,entity);
		return entity;
	}

	@Override
	public List<JobRoleStagesDTO> getAvailableJobRoleStages(List<Integer> mappedJobRoleStages) {
		List<JobRoleStages> entityList = serviceElementMgmtDAO.getAvailableJobRoleStages(mappedJobRoleStages);
		JobRoleManagementServiceImpl obj = new JobRoleManagementServiceImpl();
		List<JobRoleStagesDTO> dtoList = obj.convertListJobRoleStagesToListJobRoleStagesDTO(entityList);
		return dtoList;
	}

	@Override
	public void updateServiceElementJobRoleMapping(
			ServiceElementJobRoleStagesDTO serviceElementJobRoleStagesDTO) {
		ServiceElementJobRoleStages entity = convertServEleJobRoleStagesDTOToEntity(serviceElementJobRoleStagesDTO);
		serviceElementMgmtDAO.updateServiceElementJobRoleMapping(entity);
	}

}
