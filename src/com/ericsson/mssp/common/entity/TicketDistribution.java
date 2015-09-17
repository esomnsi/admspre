package com.ericsson.mssp.common.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.SQLInsert;

@Entity
@Table(name = "TicketDistribution", uniqueConstraints = @UniqueConstraint(columnNames = {
		"OpportunityScopeID" }))
@SQLInsert(sql = "insert into TicketDistribution (OpportunityScopeID, percentTicketDistribution)"
		+ " values (?, ?) "
		+ "ON DUPLICATE KEY UPDATE OpportunityScopeID=VALUES(OpportunityScopeID),percentTicketDistribution=VALUES(percentTicketDistribution)")
public class TicketDistribution {

	private Integer ticketDistributionID;
	private Float percentTicketDistribution;
	private OpportunityScope opportunityScope;

	public TicketDistribution() {
		super();
	}

	public TicketDistribution(Integer ticketDistributionID,
			Float percentTicketDistribution,
			OpportunityScope opportunityScope) {
		super();
		this.ticketDistributionID = ticketDistributionID;
		this.percentTicketDistribution = percentTicketDistribution;
		this.opportunityScope = opportunityScope;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "TicketDistributionID", unique = true, nullable = false)
	public Integer getTicketDistributionID() {
		return ticketDistributionID;
	}

	public void setTicketDistributionID(Integer ticketDistributionID) {
		this.ticketDistributionID = ticketDistributionID;
	}

	@Column(name = "PercentTicketDistribution")
	public Float getPercentTicketDistribution() {
		return percentTicketDistribution;
	}

	public void setPercentTicketDistribution(Float percentTicketDistribution) {
		this.percentTicketDistribution = percentTicketDistribution;
	}


	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "OpportunityScopeID")
	public OpportunityScope getOpportunityScope() {
		return opportunityScope;
	}

	public void setOpportunityScope(OpportunityScope opportunityScope) {
		this.opportunityScope = opportunityScope;
	}

}
