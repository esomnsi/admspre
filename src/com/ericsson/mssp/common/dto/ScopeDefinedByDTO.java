package com.ericsson.mssp.common.dto;

public class ScopeDefinedByDTO {

	private Integer scopeOfServicesDefinedById;
    private String name;
    private String description;
	public Integer getScopeOfServicesDefinedById() {
		return scopeOfServicesDefinedById;
	}
	public void setScopeOfServicesDefinedById(Integer scopeOfServicesDefinedById) {
		this.scopeOfServicesDefinedById = scopeOfServicesDefinedById;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
