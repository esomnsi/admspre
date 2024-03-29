package com.ericsson.mssp.common.entity;

// Generated Feb 7, 2013 2:57:51 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * SolutionOtherMisc generated by hbm2java
 */
@Entity
@Table(name = "SolutionOtherMisc")
public class SolutionOtherMisc implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 575963209509281197L;
    private Integer solutionOtherMiscId;
    private Solution solution;
    private SolutionApproachDimension solutionApproachDimension;
    private JobRole jobRole;
    private Float fte;
    private String skill;
    private String remarks;
	private OpportunityScope opportunityScope;

    public SolutionOtherMisc() {
    }

    public SolutionOtherMisc(Solution solution,
	    SolutionApproachDimension solutionApproachDimension,
	    JobRole jobRole, Float fte, String skill, String remarks) {
	this.solution = solution;
	this.solutionApproachDimension = solutionApproachDimension;
	this.jobRole = jobRole;
	this.fte = fte;
	this.skill = skill;
	this.remarks = remarks;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "SolutionOtherMiscID", unique = true, nullable = false)
    public Integer getSolutionOtherMiscId() {
	return this.solutionOtherMiscId;
    }

    public void setSolutionOtherMiscId(Integer solutionOtherMiscId) {
	this.solutionOtherMiscId = solutionOtherMiscId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SolutionID")
    public Solution getSolution() {
	return this.solution;
    }

    public void setSolution(Solution solution) {
	this.solution = solution;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SolutionApproachDimensionID")
    public SolutionApproachDimension getSolutionApproachDimension() {
	return this.solutionApproachDimension;
    }

    public void setSolutionApproachDimension(
	    SolutionApproachDimension solutionApproachDimension) {
	this.solutionApproachDimension = solutionApproachDimension;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "JobRoleID")
    public JobRole getJobRole() {
	return this.jobRole;
    }

    public void setJobRole(JobRole jobRole) {
	this.jobRole = jobRole;
    }

    @Column(name = "FTE", precision = 10)
    public Float getFte() {
	return this.fte;
    }

    public void setFte(Float fte) {
	this.fte = fte;
    }

    @Column(name = "Skill", length = 1000)
    public String getSkill() {
	return this.skill;
    }

    public void setSkill(String skill) {
	this.skill = skill;
    }

    @Column(name = "Remarks", length = 500)
    public String getRemarks() {
	return this.remarks;
    }

    public void setRemarks(String remarks) {
	this.remarks = remarks;
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
}
