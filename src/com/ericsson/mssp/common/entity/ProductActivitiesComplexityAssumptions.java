package com.ericsson.mssp.common.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * ProductActivitiesComplexityAssumptions
 */
@Entity
@Table(name = "ProductActivitiesComplexityAssumptions")
public class ProductActivitiesComplexityAssumptions implements
		java.io.Serializable {

	private static final long serialVersionUID = -330614778634165293L;
	private Integer productActivitiesComplexityAssumptionsID;
	private ProductEstimationActivities productEstimationActivities;
	private EstimationComplexityLookup estimationComplexityLookup;
	private Double estimationEffortFactorValue;
	private String estimationEffortFactorUnit;
	private ProductEstimationForSolution productEstimationForSolution;

	public ProductActivitiesComplexityAssumptions() {
	}

	public ProductActivitiesComplexityAssumptions(
			Integer productActivitiesComplexityAssumptionsID,
			ProductEstimationActivities productEstimationActivities,
			EstimationComplexityLookup estimationComplexityLookup,
			Double estimationEffortFactorValue,
			String estimationEffortFactorUnit) {
		this.productActivitiesComplexityAssumptionsID = productActivitiesComplexityAssumptionsID;
		this.productEstimationActivities = productEstimationActivities;
		this.estimationComplexityLookup = estimationComplexityLookup;
		this.estimationEffortFactorValue = estimationEffortFactorValue;
		this.estimationEffortFactorUnit = estimationEffortFactorUnit;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ProductActivitiesComplexityAssumptionsID", unique = true, nullable = false)
	public Integer getProductActivitiesComplexityAssumptionsID() {
		return productActivitiesComplexityAssumptionsID;
	}

	public void setProductActivitiesComplexityAssumptionsID(
			Integer productActivitiesComplexityAssumptionsID) {
		this.productActivitiesComplexityAssumptionsID = productActivitiesComplexityAssumptionsID;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ProductEstimationActivitiesID")
	public ProductEstimationActivities getProductEstimationActivities() {
		return productEstimationActivities;
	}

	public void setProductEstimationActivities(
			ProductEstimationActivities productEstimationActivities) {
		this.productEstimationActivities = productEstimationActivities;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "EstimationComplexityID")
	public EstimationComplexityLookup getEstimationComplexityLookup() {
		return estimationComplexityLookup;
	}

	public void setEstimationComplexityLookup(
			EstimationComplexityLookup estimationComplexityLookup) {
		this.estimationComplexityLookup = estimationComplexityLookup;
	}

	@Column(name = "EstimationEffortFactorValue")
	public Double getEstimationEffortFactorValue() {
		return estimationEffortFactorValue;
	}

	public void setEstimationEffortFactorValue(
			Double estimationEffortFactorValue) {
		this.estimationEffortFactorValue = estimationEffortFactorValue;
	}

	@Column(name = "EstimationEffortFactorUnit")
	public String getEstimationEffortFactorUnit() {
		return estimationEffortFactorUnit;
	}

	public void setEstimationEffortFactorUnit(String estimationEffortFactorUnit) {
		this.estimationEffortFactorUnit = estimationEffortFactorUnit;
	}

	@OneToOne(fetch = FetchType.EAGER, mappedBy = "productActivitiesComplexityAssumptions")
	public ProductEstimationForSolution getProductEstimationForSolution() {
		return productEstimationForSolution;
	}

	public void setProductEstimationForSolution(
			ProductEstimationForSolution productEstimationForSolution) {
		this.productEstimationForSolution = productEstimationForSolution;
	}

}
