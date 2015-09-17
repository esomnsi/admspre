package com.ericsson.mssp.common.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="ServiceAssurance")
@NamedQueries(value = { @NamedQuery(name = "get.ServiceAssurance.bySolutionID", query = "from ServiceAssurance where SolutionID = ?")	})
public class ServiceAssurance implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4676559741667436807L;
	private Integer serviceAssuranceID;
	private Solution solution;
	private OpportunityScope opportunityScope;
	private float helpDeskSupport;
	private float incidentManagement;
	private float problemManagement;
	private float impactAnalysis;
	private float dataAlignment;
	private float incidentReporting;
	private float bugFixing;
	private float totalServiceAssuranceHours;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ServiceAssuranceID")
	public Integer getServiceAssuranceID() {
		return serviceAssuranceID;
	}
	public void setServiceAssuranceID(Integer serviceAssuranceID) {
		this.serviceAssuranceID = serviceAssuranceID;
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
	
	@Column(name="HelpDeskSupport")
	public float getHelpDeskSupport() {
		return helpDeskSupport;
	}
	public void setHelpDeskSupport(float helpDeskSupport) {
		this.helpDeskSupport = helpDeskSupport;
	}
	
	@Column(name="IncidentManagement")
	public float getIncidentManagement() {
		return incidentManagement;
	}
	public void setIncidentManagement(float incidentManagement) {
		this.incidentManagement = incidentManagement;
	}
	
	@Column(name="ProblemManagement")
	public float getProblemManagement() {
		return problemManagement;
	}
	public void setProblemManagement(float problemManagement) {
		this.problemManagement = problemManagement;
	}
	
	@Column(name="ImpactAnalysis")
	public float getImpactAnalysis() {
		return impactAnalysis;
	}
	public void setImpactAnalysis(float impactAnalysis) {
		this.impactAnalysis = impactAnalysis;
	}
	
	@Column(name="DataAlignment")
	public float getDataAlignment() {
		return dataAlignment;
	}
	public void setDataAlignment(float dataAlignment) {
		this.dataAlignment = dataAlignment;
	}
	
	@Column(name="IncidentReporting")
	public float getIncidentReporting() {
		return incidentReporting;
	}
	public void setIncidentReporting(float incidentReporting) {
		this.incidentReporting = incidentReporting;
	}
	
	@Column(name="BugFixing")
	public float getBugFixing() {
		return bugFixing;
	}
	public void setBugFixing(float bugFixing) {
		this.bugFixing = bugFixing;
	}
	
	@Column(name="TotalServiceAssuranceHours")
	public float getTotalServiceAssuranceHours() {
		return totalServiceAssuranceHours;
	}
	public void setTotalServiceAssuranceHours(float totalServiceAssuranceHours) {
		this.totalServiceAssuranceHours = totalServiceAssuranceHours;
	}
	

}
