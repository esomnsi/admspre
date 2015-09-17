package com.ericsson.mssp.serviceelement.management.form;

import java.util.List;

import com.ericsson.mssp.common.dto.ServiceElementJobRoleStagesDTO;

public class ServiceElementManagementForm {
	
	private Integer serviceElementId;
	private List<ServiceElementJobRoleStagesDTO> listServiceElementJobRoleStagesDTO;
	private Integer selected;

	public Integer getServiceElementId() {
		return serviceElementId;
	}

	public void setServiceElementId(Integer serviceElementId) {
		this.serviceElementId = serviceElementId;
	}

	public List<ServiceElementJobRoleStagesDTO> getListServiceElementJobRoleStagesDTO() {
		return listServiceElementJobRoleStagesDTO;
	}

	public void setListServiceElementJobRoleStagesDTO(
			List<ServiceElementJobRoleStagesDTO> listServiceElementJobRoleStagesDTO) {
		this.listServiceElementJobRoleStagesDTO = listServiceElementJobRoleStagesDTO;
	}

	public Integer getSelected() {
		return selected;
	}

	public void setSelected(Integer selected) {
		this.selected = selected;
	}

	

	

	
	

}
