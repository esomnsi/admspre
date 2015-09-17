package com.ericsson.mssp.common.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "OpportunityComponent")
public class OpportunityComponent {
	
	private int OpportunityComponentID;
	private Opportunity opportunity;
	private Component component;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "OpportunityComponentID", unique = true, nullable = false)
	public int getOpportunityComponentID() {
		return OpportunityComponentID;
	}
	public void setOpportunityComponentID(int opportunityComponentID) {
		OpportunityComponentID = opportunityComponentID;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "OpportunityID", nullable = false)
	public Opportunity getOpportunity() {
		return opportunity;
	}
	public void setOpportunity(Opportunity opportunity) {
		this.opportunity = opportunity;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ComponentID", nullable = false)
	public Component getComponent() {
		return component;
	}
	public void setComponent(Component component) {
		this.component = component;
	}
	
	
	
	

}
