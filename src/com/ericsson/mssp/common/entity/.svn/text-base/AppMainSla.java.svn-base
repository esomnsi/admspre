package com.ericsson.mssp.common.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="AppMainSLA")
public class AppMainSla {
	
	private int appMainSlaID;
	private Opportunity opportunity;
	private String severity;
	private float LOneResponse;
	private float LTwoResponse;
	private float LThreeResponse;
	private float ticketDistribution;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "AppMainSlaID", unique = true, nullable = false)
	public int getAppMainSlaID() {
		return appMainSlaID;
	}
	public void setAppMainSlaID(int appMainSlaID) {
		this.appMainSlaID = appMainSlaID;
	}	
	
	@Column(name="Severity")
	public String getSeverity() {
		return severity;
	}
	public void setSeverity(String severity) {
		this.severity = severity;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OpportunityID", nullable = false)
	public Opportunity getOpportunity() {
		return opportunity;
	}	
	public void setOpportunity(Opportunity opportunity) {
		this.opportunity = opportunity;
	}
	
	
	
	
	@Column(name="L1Response")
	public float getLOneResponse() {
		return LOneResponse;
	}
	public void setLOneResponse(float lOneResponse) {
		LOneResponse = lOneResponse;
	}
	
	@Column(name="L2Response")
	public float getLTwoResponse() {
		return LTwoResponse;
	}
	public void setLTwoResponse(float lTwoResponse) {
		LTwoResponse = lTwoResponse;
	}
	
	@Column(name="L3Response")
	public float getLThreeResponse() {
		return LThreeResponse;
	}
	public void setLThreeResponse(float lThreeResponse) {
		LThreeResponse = lThreeResponse;
	}
	
	@Column(name="TicketDistribution")
	public float getTicketDistribution() {
		return ticketDistribution;
	}
	public void setTicketDistribution(float ticketDistribution) {
		this.ticketDistribution = ticketDistribution;
	}
	
	
}
