package com.ericsson.mssp.common.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="AppMainSupportActivity")
public class AppMainSupportActivity {
	
	private Integer appMainSupportActivityID;
	private OpportunityComponent opportinityComponent;
	private SupportWindowMatrix supportWindowMatrix;
	private String callVolume;
	private String onCallSupport;
	private float ticketsPerMonth;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "AppMainSupportActivityID", unique = true, nullable = false)
	public Integer getAppMainSupportActivityID() {
		return appMainSupportActivityID;
	}
	public void setAppMainSupportActivityID(Integer appMainSupportActivityID) {
		this.appMainSupportActivityID = appMainSupportActivityID;
	}
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "OpportunityComponentID", nullable = false)
	public OpportunityComponent getOpportinityComponent() {
		return opportinityComponent;
	}
	public void setOpportinityComponent(OpportunityComponent opportinityComponent) {
		this.opportinityComponent = opportinityComponent;
	}
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SupportWindowMatrixID", nullable = false)
	public SupportWindowMatrix getSupportWindowMatrix() {
		return supportWindowMatrix;
	}
	public void setSupportWindowMatrix(SupportWindowMatrix supportWindowMatrix) {
		this.supportWindowMatrix = supportWindowMatrix;
	}
	
	@Column(name="CallVolume")
	public String getCallVolume() {
		return callVolume;
	}
	public void setCallVolume(String callVolume) {
		this.callVolume = callVolume;
	}
	
	@Column(name="OnCallSupport")
	public String getOnCallSupport() {
		return onCallSupport;
	}
	public void setOnCallSupport(String onCallSupport) {
		this.onCallSupport = onCallSupport;
	}
	
	@Column(name="TicketsPerMonth")
	public float getTicketsPerMonth() {
		return ticketsPerMonth;
	}
	public void setTicketsPerMonth(float ticketsPerMonth) {
		this.ticketsPerMonth = ticketsPerMonth;
	}
	
	
	

}
