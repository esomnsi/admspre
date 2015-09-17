package com.ericsson.mssp.common.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * ProductEstimationForSolution
 */
@Entity
@Table(name = "ProductEstimationForSolution", uniqueConstraints = @UniqueConstraint(columnNames = {
		"SolutionID", "ProductActivitiesComplexityAssumptionsID" }))
/*@SQLInsert(sql = "insert into ProductEstimationForSolution (SolutionID, ProductActivitiesComplexityAssumptionsID, "
		+ " PerMonthEventCountInput, TotalEffortDerived)"
		+ " values (?, ?, ?, ?) "
		+ "ON DUPLICATE KEY UPDATE PerMonthEventCountInput=VALUES(PerMonthEventCountInput),TotalEffortDerived=VALUES(TotalEffortDerived)")*/
public class ProductEstimationForSolution implements java.io.Serializable {

	private static final long serialVersionUID = -330614778634165293L;
	private Integer productEstimationForSolutionID;
	private Double perMonthEventCountInput;
	private Double totalEffortDerived;
	private Solution solution;
	private ProductActivitiesComplexityAssumptions productActivitiesComplexityAssumptions;

	public ProductEstimationForSolution() {
	}

	public ProductEstimationForSolution(
			Integer productEstimationForSolutionID,
			Double perMonthEventCountInput,
			Double totalEffortDerived,
			Solution solution,
			ProductActivitiesComplexityAssumptions productActivitiesComplexityAssumptions) {
		this.productEstimationForSolutionID = productEstimationForSolutionID;
		this.perMonthEventCountInput = perMonthEventCountInput;
		this.totalEffortDerived = totalEffortDerived;
		this.solution = solution;
		this.productActivitiesComplexityAssumptions = productActivitiesComplexityAssumptions;
	}


	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ProductEstimationForSolutionID", unique = true, nullable = false)
	public Integer getProductEstimationForSolutionID() {
		return productEstimationForSolutionID;
	}

	public void setProductEstimationForSolutionID(
			Integer productEstimationForSolutionID) {
		this.productEstimationForSolutionID = productEstimationForSolutionID;
	}

	@Column(name = "PerMonthEventCountInput")
	public Double getPerMonthEventCountInput() {
		return perMonthEventCountInput;
	}

	public void setPerMonthEventCountInput(Double perMonthEventCountInput) {
		this.perMonthEventCountInput = perMonthEventCountInput;
	}

	@Column(name = "TotalEffortDerived")
	public Double getTotalEffortDerived() {
		return totalEffortDerived;
	}

	public void setTotalEffortDerived(Double totalEffortDerived) {
		this.totalEffortDerived = totalEffortDerived;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SolutionID")
	public Solution getSolution() {
		return solution;
	}

	public void setSolution(Solution solution) {
		this.solution = solution;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ProductActivitiesComplexityAssumptionsID")
	public ProductActivitiesComplexityAssumptions getProductActivitiesComplexityAssumptions() {
		return productActivitiesComplexityAssumptions;
	}

	public void setProductActivitiesComplexityAssumptions(
			ProductActivitiesComplexityAssumptions productActivitiesComplexityAssumptions) {
		this.productActivitiesComplexityAssumptions = productActivitiesComplexityAssumptions;
	}
}
