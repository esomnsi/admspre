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
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.SQLInsert;

import com.ericsson.mssp.common.constant.MSSPConstants;

/**
 * ProductEstimationBaseEffortForSolution
 */
@Entity
@Table(name = "ProductEstimationBaseEffortForSolution", uniqueConstraints = @UniqueConstraint(columnNames = {
		"SolutionID","OpportunityScopeID"}))
@SQLInsert(sql = "insert into ProductEstimationBaseEffortForSolution (SolutionID, OpportunityScopeID, TotalBaseFTE, TotalBaseHours)"
		+ " values (?, ?, ?, ?) "
		+ "ON DUPLICATE KEY UPDATE SolutionID=VALUES(SolutionID),OpportunityScopeID=VALUES(OpportunityScopeID),TotalBaseFTE=VALUES(TotalBaseFTE),TotalBaseHours=VALUES(TotalBaseHours)")
public class ProductEstimationBaseEffortForSolution implements java.io.Serializable {

	private static final long serialVersionUID = -330614778634165293L;
	private Integer productEstimationBaseEffortForSolutionID;
	private Solution solution;
	private OpportunityScope opportunityScope;
	private Double totalBaseFTE;
	private Double totalBaseHours;

	public ProductEstimationBaseEffortForSolution() {
		super();
	}
	
	public ProductEstimationBaseEffortForSolution(
			Integer productEstimationBaseEffortForSolutionID,
			Solution solution, OpportunityScope opportunityScope,
			Double totalBaseFTE, Double totalBaseHours) {
		super();
		this.productEstimationBaseEffortForSolutionID = productEstimationBaseEffortForSolutionID;
		this.solution = solution;
		this.opportunityScope = opportunityScope;
		this.totalBaseFTE = totalBaseFTE;
		this.totalBaseHours = totalBaseHours;
	}
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ProductEstimationBaseEffortForSolutionID", unique = true, nullable = false)
	public Integer getProductEstimationBaseEffortForSolutionID() {
		return productEstimationBaseEffortForSolutionID;
	}

	public void setProductEstimationBaseEffortForSolutionID(
			Integer productEstimationBaseEffortForSolutionID) {
		this.productEstimationBaseEffortForSolutionID = productEstimationBaseEffortForSolutionID;
	}

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SolutionID")
	public Solution getSolution() {
		return solution;
	}

	public void setSolution(Solution solution) {
		this.solution = solution;
	}

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "OpportunityScopeID")
	public OpportunityScope getOpportunityScope() {
		return opportunityScope;
	}

	public void setOpportunityScope(OpportunityScope opportunityScope) {
		this.opportunityScope = opportunityScope;
	}

	@Column(name = "TotalBaseFTE")
	public Double getTotalBaseFTE() {
		return totalBaseFTE;
	}

	public void setTotalBaseFTE(Double totalBaseFTE) {
		this.totalBaseFTE = totalBaseFTE;
	}

	@Column(name = "TotalBaseHours")
	public Double getTotalBaseHours() {
		return totalBaseHours;
	}

	public void setTotalBaseHours(Double totalBaseHours) {
		this.totalBaseHours = totalBaseHours;
		//setTotalBaseFTE(totalBaseHours / Double.parseDouble(MSSPConstants.DEFAULT_UTILIZATION_PER_YEAR));
	}
}
