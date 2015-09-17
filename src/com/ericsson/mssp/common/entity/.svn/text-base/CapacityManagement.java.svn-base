package com.ericsson.mssp.common.entity;

//Generated May 19, 2014 11:53:09 AM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "CapacityManagement")
@NamedQueries({
	@NamedQuery(
	name = "get.CapacityManagement.bySolutionID",
	query = "from CapacityManagement where SolutionId = ?"
	)
})
public class CapacityManagement implements java.io.Serializable {

/**
* 
*/
private static final long serialVersionUID = -7676448561170657940L;
private Integer capacityManagementID;
private Solution solution;
private OpportunityScope opportunityScope;
private float prepareAndUseModels;
private float fteCapacityManagement;


@Id
@GeneratedValue(strategy = IDENTITY)
@Column(name = "CapacityManagementID", unique = true, nullable = false)
public Integer getCapacityManagementID() {
	return this.capacityManagementID;
}

public void setCapacityManagementID(Integer capacityManagementID) {
	this.capacityManagementID = capacityManagementID;
}

@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "SolutionID", nullable = false)
public Solution getSolution() {
	return this.solution;
}

public void setSolution(Solution solution) {
	this.solution = solution;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "OpportunityScopeID")
	public OpportunityScope getOpportunityScope() {
		return opportunityScope;
	}


	public void setOpportunityScope(OpportunityScope opportunityScope) {
		this.opportunityScope = opportunityScope;
	}

	@Column(name="PrepareAndUseModels")
	public float getPrepareAndUseModels() {
		return prepareAndUseModels;
	}

	public void setPrepareAndUseModels(float prepareAndUseModels) {
		this.prepareAndUseModels = prepareAndUseModels;
	}

	@Column(name="FteCapacityManagement")
	public float getFteCapacityManagement() {
		return fteCapacityManagement;
	}

	public void setFteCapacityManagement(float fteCapacityManagement) {
		this.fteCapacityManagement = fteCapacityManagement;
	}

	


}

