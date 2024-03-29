package com.ericsson.mssp.common.entity;

// Generated Mar 8, 2013 4:10:13 PM by Hibernate Tools 3.4.0.CR1

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
 * SolutionGovAndPmo generated by hbm2java
 */
@Entity
@Table(name = "SolutionGovAndPMO")
public class SolutionGovAndPmo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6405707335818425075L;
	private Integer solutionGovAndPmoid;
	private Solution solution;
	private SolutionApproachDimension solutionApproachDimension;
	private JobRole jobRole;
	private Float fte;
	private String remarks;
	private OpportunityScope opportunityScope;

	public SolutionGovAndPmo() {
	}

	public SolutionGovAndPmo(Solution solution,
			SolutionApproachDimension solutionApproachDimension,
			JobRole jobRole, Float fte, String remarks) {
		this.solution = solution;
		this.solutionApproachDimension = solutionApproachDimension;
		this.jobRole = jobRole;
		this.fte = fte;
		this.remarks = remarks;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "SolutionGovAndPMOID", unique = true, nullable = false)
	public Integer getSolutionGovAndPmoid() {
		return this.solutionGovAndPmoid;
	}

	public void setSolutionGovAndPmoid(Integer solutionGovAndPmoid) {
		this.solutionGovAndPmoid = solutionGovAndPmoid;
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

	@ManyToOne(fetch = FetchType.LAZY)
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
