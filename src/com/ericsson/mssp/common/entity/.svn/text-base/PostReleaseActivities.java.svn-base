package com.ericsson.mssp.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="PostReleaseActivities")
public class PostReleaseActivities {
	
	private Integer postRelActivityID;
	private float postInstRelIssues;
	private float suppBusinessSimulations;
	private float manageStaggeredAct;
	private float tunePerformance;
	private float postInstallationComm;	
	private float totalPostRelActHours;
	private Solution solution;
	private OpportunityScope opportunityScope;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PostRelActivityID")
	public Integer getPostRelActivityID() {
		return postRelActivityID;
	}
	public void setPostRelActivityID(Integer postRelActivityID) {
		this.postRelActivityID = postRelActivityID;
	}	
	
	@Column(name="PostInstRelIssues")
	public float getPostInstRelIssues() {
		return postInstRelIssues;
	}
	public void setPostInstRelIssues(float postInstRelIssues) {
		this.postInstRelIssues = postInstRelIssues;
	}
	
	@Column(name="SuppBusinessSimulations")
	public float getSuppBusinessSimulations() {
		return suppBusinessSimulations;
	}
	public void setSuppBusinessSimulations(float suppBusinessSimulations) {
		this.suppBusinessSimulations = suppBusinessSimulations;
	}
	
	@Column(name="ManageStaggeredAct")
	public float getManageStaggeredAct() {
		return manageStaggeredAct;
	}
	public void setManageStaggeredAct(float manageStaggeredAct) {
		this.manageStaggeredAct = manageStaggeredAct;
	}
	
	@Column(name="TunePerformance")
	public float getTunePerformance() {
		return tunePerformance;
	}
	public void setTunePerformance(float tunePerformance) {
		this.tunePerformance = tunePerformance;
	}
	
	@Column(name="PostInstallationComm")
	public float getPostInstallationComm() {
		return postInstallationComm;
	}
	public void setPostInstallationComm(float postInstallationComm) {
		this.postInstallationComm = postInstallationComm;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SolutionID")
	public Solution getSolution() {
		return solution;
	}
	public void setSolution(Solution solution) {
		this.solution = solution;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="OpportunityScopeID")
	public OpportunityScope getOpportunityScope() {
		return opportunityScope;
	}
	public void setOpportunityScope(OpportunityScope opportunityScope) {
		this.opportunityScope = opportunityScope;
	}
	
	@Column(name="TotalPostRelActHours")
	public float getTotalPostRelActHours() {
		return totalPostRelActHours;
	}
	public void setTotalPostRelActHours(float totalPostRelActHours) {
		this.totalPostRelActHours = totalPostRelActHours;
	}
	
	

}
