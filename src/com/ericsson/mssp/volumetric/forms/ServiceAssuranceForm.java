package com.ericsson.mssp.volumetric.forms;

import com.ericsson.mssp.common.dto.ServiceAssuranceDTO;

public class ServiceAssuranceForm {
	
	private ServiceAssuranceDTO serviceAssuranceDTO;
	private Integer opportunityScopeId;
	
	public ServiceAssuranceDTO getServiceAssuranceDTO() {
		return serviceAssuranceDTO;
	}
	public void setServiceAssuranceDTO(ServiceAssuranceDTO serviceAssuranceDTO) {
		this.serviceAssuranceDTO = serviceAssuranceDTO;
	}
	public Integer getOpportunityScopeId() {
		return opportunityScopeId;
	}
	public void setOpportunityScopeId(Integer opportunityScopeId) {
		this.opportunityScopeId = opportunityScopeId;
	}
	
	

}
