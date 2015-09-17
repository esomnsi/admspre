package com.ericsson.mssp.common.dto;

import com.ericsson.mssp.common.entity.OpportunityComponent;
import com.ericsson.mssp.common.entity.OpportunityScope;
import com.ericsson.mssp.common.entity.SupportWindowMatrix;

public class AppMainSupportActivityDTO {
	
	private Integer appMainSupportActivityID;
	private OpportunityComponent opportinityComponent;
	/*
	private OpportunityScope opportunityScope;*/
	private SupportWindowMatrix supportWindowMatrix;
	private String callVolume;
	private String onCallSupport;
	private float ticketsPerMonth;
	
	
	public Integer getAppMainSupportActivityID() {
		return appMainSupportActivityID;
	}
	public void setAppMainSupportActivityID(Integer appMainSupportActivityID) {
		this.appMainSupportActivityID = appMainSupportActivityID;
	}
	/*public OpportunityScope getOpportunityScope() {
		return opportunityScope;
	}
	public void setOpportunityScope(OpportunityScope opportunityScope) {
		this.opportunityScope = opportunityScope;
	}*/
	public SupportWindowMatrix getSupportWindowMatrix() {
		return supportWindowMatrix;
	}
	public void setSupportWindowMatrix(SupportWindowMatrix supportWindowMatrix) {
		this.supportWindowMatrix = supportWindowMatrix;
	}

	public OpportunityComponent getOpportinityComponent() {
		return opportinityComponent;
	}
	public void setOpportinityComponent(OpportunityComponent opportinityComponent) {
		this.opportinityComponent = opportinityComponent;
	}
	public String getCallVolume() {
		return callVolume;
	}
	public void setCallVolume(String callVolume) {
		this.callVolume = callVolume;
	}
	public String getOnCallSupport() {
		return onCallSupport;
	}
	public void setOnCallSupport(String onCallSupport) {
		this.onCallSupport = onCallSupport;
	}
	public float getTicketsPerMonth() {
		return ticketsPerMonth;
	}
	public void setTicketsPerMonth(float ticketsPerMonth) {
		this.ticketsPerMonth = ticketsPerMonth;
	}
	
	

}
