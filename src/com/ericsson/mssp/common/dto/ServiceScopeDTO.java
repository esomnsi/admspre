/**
* -------------------------------------------------------------------------------------------------------
* Copyright (C) 2012 Ericsson India Global Pvt Limited
* All Rights Reserved
* Project         		    :  IT_MSSP
* Module				    :  com.ericsson.mssp.common.dto
* File name       		    :  ServiceScopeDTO.java
* Description				:	<To Do>
* Author, Date & Release	:	Dec 11, 20122012
* Modification history		:
* Number	|Date   	   |Author        |Remark
* -----------------------------------------------------------------------------------------------------
* 1      	| Dec 11, 2012  	   |egaivij   	  | Created.
* -----------------------------------------------------------------------------------------------------
**/
 
package com.ericsson.mssp.common.dto;

import com.ericsson.mssp.common.entity.ServiceElement;

/**
 * @author egaivij
 *
 */
public class ServiceScopeDTO extends BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer serviceScopeId;
	private String serviceScopeCategory;
	private String serviceScopeName;
	private String serviceElementName;
	private Integer serviceElementId;
	private String serviceScopeDescription;
	private Integer serviceElementDefaultValue;
	
	
	
	/**
	 * @return the serviceScopeCategory
	 */
	public String getServiceScopeCategory() {
		return serviceScopeCategory;
	}
	/**
	 * @param serviceScopeCategory the serviceScopeCategory to set
	 */
	public void setServiceScopeCategory(String serviceScopeCategory) {
		this.serviceScopeCategory = serviceScopeCategory;
	}
	/**
	 * @return the serviceScopeName
	 */
	public String getServiceScopeName() {
		return serviceScopeName;
	}
	/**
	 * @param serviceScopeName the serviceScopeName to set
	 */
	public void setServiceScopeName(String serviceScopeName) {
		this.serviceScopeName = serviceScopeName;
	}
	/**
	 * @return the serviceScopeId
	 */
	public Integer getServiceScopeId() {
		return serviceScopeId;
	}
	/**
	 * @param serviceScopeId the serviceScopeId to set
	 */
	public void setServiceScopeId(Integer serviceScopeId) {
		this.serviceScopeId = serviceScopeId;
	}
	public String getServiceElementName() {
		return serviceElementName;
	}
	public void setServiceElementName(String serviceElementName) {
		this.serviceElementName = serviceElementName;
	}
	public String getServiceScopeDescription() {
		return serviceScopeDescription;
	}
	public void setServiceScopeDescription(String serviceScopeDescription) {
		this.serviceScopeDescription = serviceScopeDescription;
	}
	public Integer getServiceElementId() {
		return serviceElementId;
	}
	public void setServiceElementId(Integer serviceElementId) {
		this.serviceElementId = serviceElementId;
	}
	public Integer getServiceElementDefaultValue() {
		return serviceElementDefaultValue;
	}
	public void setServiceElementDefaultValue(Integer serviceElementDefaultValue) {
		this.serviceElementDefaultValue = serviceElementDefaultValue;
	}	
	
}
