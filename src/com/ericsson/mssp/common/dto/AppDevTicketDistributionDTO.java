package com.ericsson.mssp.common.dto;

public class AppDevTicketDistributionDTO extends BaseDTO {

	private Integer ticketDistributionID;
	private Integer opportunityID;
	private Integer solutionID;
	private String serviceFunctionFlag;
	private String serviceFunctionName;
	private Integer serviceScopeID;
	private String serviceElementName;
	private Integer opportunityScopeID;
	private String selected;
	private Float defaultValue;
	private Float percentDistribution;
	private Float editablePercentDistribution;
	private Double effortForServiceElement;

	public AppDevTicketDistributionDTO() {
		super();
	}

	public AppDevTicketDistributionDTO(Integer ticketDistributionID,
			Integer opportunityID, Integer solutionID,
			String serviceFunctionFlag, String serviceFunctionName,
			Integer serviceScopeID, String serviceElementName,
			Integer opportunityScopeID, String selected, Float defaultValue,
			Float percentDistribution, Float editablePercentDistribution,
			Double effortForServiceElement) {
		super();
		this.ticketDistributionID = ticketDistributionID;
		this.opportunityID = opportunityID;
		this.solutionID = solutionID;
		this.serviceFunctionFlag = serviceFunctionFlag;
		this.serviceFunctionName = serviceFunctionName;
		this.serviceScopeID = serviceScopeID;
		this.serviceElementName = serviceElementName;
		this.opportunityScopeID = opportunityScopeID;
		this.selected = selected;
		this.defaultValue = defaultValue;
		this.percentDistribution = percentDistribution;
		this.editablePercentDistribution = editablePercentDistribution;
		this.effortForServiceElement = effortForServiceElement;
	}


	public Integer getTicketDistributionID() {
		return ticketDistributionID;
	}

	public void setTicketDistributionID(Integer ticketDistributionID) {
		this.ticketDistributionID = ticketDistributionID;
	}

	public Integer getOpportunityID() {
		return opportunityID;
	}

	public void setOpportunityID(Integer opportunityID) {
		this.opportunityID = opportunityID;
	}

	public Integer getSolutionID() {
		return solutionID;
	}

	public void setSolutionID(Integer solutionID) {
		this.solutionID = solutionID;
	}

	public String getServiceFunctionFlag() {
		return serviceFunctionFlag;
	}

	public void setServiceFunctionFlag(String serviceFunctionFlag) {
		this.serviceFunctionFlag = serviceFunctionFlag;
	}

	public String getServiceFunctionName() {
		return serviceFunctionName;
	}

	public void setServiceFunctionName(String serviceFunctionName) {
		this.serviceFunctionName = serviceFunctionName;
	}

	public Integer getServiceScopeID() {
		return serviceScopeID;
	}

	public void setServiceScopeID(Integer serviceScopeID) {
		this.serviceScopeID = serviceScopeID;
	}

	public String getServiceElementName() {
		return serviceElementName;
	}

	public void setServiceElementName(String serviceElementName) {
		this.serviceElementName = serviceElementName;
	}

	public String getSelected() {
		return selected;
	}

	public void setSelected(String selected) {
		this.selected = selected;
	}

	public Float getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(Float defaultValue) {
		this.defaultValue = defaultValue;
	}

	public Float getPercentDistribution() {
		return percentDistribution;
	}

	public void setPercentDistribution(Float percentDistribution) {
		this.percentDistribution = percentDistribution;
	}

	public Float getEditablePercentDistribution() {
		return editablePercentDistribution;
	}

	public void setEditablePercentDistribution(Float editablePercentDistribution) {
		this.editablePercentDistribution = editablePercentDistribution;
	}

	public Integer getOpportunityScopeID() {
		return opportunityScopeID;
	}

	public void setOpportunityScopeID(Integer opportunityScopeID) {
		this.opportunityScopeID = opportunityScopeID;
	}

	public Double getEffortForServiceElement() {
		return effortForServiceElement;
	}

	public void setEffortForServiceElement(Double effortForServiceElement) {
		this.effortForServiceElement = effortForServiceElement;
	}
}
