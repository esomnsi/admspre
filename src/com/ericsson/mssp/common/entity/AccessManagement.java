package com.ericsson.mssp.common.entity;

//Generated May 22, 2014 11:53:09 AM by Hibernate Tools 3.4.0.CR1

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

/**
* AccessManagement generated by hbm2java
*/
@Entity
@Table(name = "AccessManagement")
public class AccessManagement implements java.io.Serializable {

/**
* 
*/
private static final long serialVersionUID = -7676448561170657940L;
private Integer accessManagementID;
private Solution solution;
private OpportunityScope opportunityScope;
private float manageUsersRights;
private float manageUsers;
private float totalHours;


public AccessManagement() {
}

public AccessManagement(Solution solution) {
	this.solution = solution;
}


@Id
@GeneratedValue(strategy = IDENTITY)
@Column(name = "AccessManagementID", unique = true, nullable = false)
public Integer getAccessManagementID() {
	return this.accessManagementID;
}

public void setAccessManagementID(Integer accessManagementID) {
	this.accessManagementID = accessManagementID;
}

@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "SolutionID", nullable = false)
public Solution getSolution() {
	return this.solution;
}

public void setSolution(Solution solution) {
	this.solution = solution;
}


/**
	 * @return the opportunityScope
	 */
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "OpportunityScopeID")
	public OpportunityScope getOpportunityScope() {
		return opportunityScope;
	}

	/**
	 * @param opportunityScope the opportunityScope to set
	 */
	public void setOpportunityScope(OpportunityScope opportunityScope) {
		this.opportunityScope = opportunityScope;
	}

	/**
	 * @return the manageUsersRights
	 */
	@JoinColumn(name = "ManageUsersRights")
	public float getManageUsersRights() {
		return manageUsersRights;
	}

	/**
	 * @param manageUsersRights the manageUsersRights to set
	 */
	public void setManageUsersRights(float manageUsersRights) {
		this.manageUsersRights = manageUsersRights;
	}

	/**
	 * @return the manageUsers
	 */
	@JoinColumn(name = "ManageUsers")
	public float getManageUsers() {
		return manageUsers;
	}

	/**
	 * @param manageUsers the manageUsers to set
	 */
	public void setManageUsers(float manageUsers) {
		this.manageUsers = manageUsers;
	}

	/**
	 * @return the totalHours
	 */
	@JoinColumn(name = "TotalHours")
	public float getTotalHours() {
		return totalHours;
	}

	/**
	 * @param totalHours the totalHours to set
	 */
	public void setTotalHours(float totalHours) {
		this.totalHours = totalHours;
	}




}


