package com.ericsson.mssp.common.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="DemandSupport")
public class DemandSupport implements Serializable{

	/**
	 * @author eanujau
	 */
	private static final long serialVersionUID = -7071834121076954287L;
	private Integer demandSupportID;
	private float crAnalysis;
    private float requirementGathering;
    private float techFeasibilityAnalysis;
    private float effCostEstimation;
    private float workPlanFormulation;
    private float totalDemandSupportHours;
    private Solution solution;
    private OpportunityScope opportunityScope;
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "DemandSupportID", unique = true, nullable = false)
	public Integer getDemandSupportID() {
		return demandSupportID;
	}
	public void setDemandSupportID(Integer demandSupportID) {
		this.demandSupportID = demandSupportID;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SolutionID", nullable = false)
	public Solution getSolution() {
		return solution;
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
	
	@Column(name="CRAnalysis")
	public float getCrAnalysis() {
		return crAnalysis;
	}
	public void setCrAnalysis(float crAnalysis) {
		this.crAnalysis = crAnalysis;
	}
	
	@Column(name="RequirementGathering")
	public float getRequirementGathering() {
		return requirementGathering;
	}
	public void setRequirementGathering(float requirementGathering) {
		this.requirementGathering = requirementGathering;
	}
	
	@Column(name="TechFeasibilityAnalysis")
	public float getTechFeasibilityAnalysis() {
		return techFeasibilityAnalysis;
	}
	public void setTechFeasibilityAnalysis(float techFeasibilityAnalysis) {
		this.techFeasibilityAnalysis = techFeasibilityAnalysis;
	}
	
	@Column(name="EffortCostEstimation")
	public float getEffCostEstimation() {
		return effCostEstimation;
	}
	public void setEffCostEstimation(float effCostEstimation) {
		this.effCostEstimation = effCostEstimation;
	}
	
	@Column(name="WorkPlanFormulation")
	public float getWorkPlanFormulation() {
		return workPlanFormulation;
	}
	public void setWorkPlanFormulation(float workPlanFormulation) {
		this.workPlanFormulation = workPlanFormulation;
	}
	
	@Column(name="TotalDemandSupportHours")
	public float getTotalDemandSupportHours() {
		return totalDemandSupportHours;
	}
	public void setTotalDemandSupportHours(float totalDemandSupportHours) {
		this.totalDemandSupportHours = totalDemandSupportHours;
	}
    
    

}
