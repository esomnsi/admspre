package com.ericsson.mssp.common.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * EstimationComplexityLookup
 */
@Entity
@Table(name = "EstimationComplexityLookup")
public class EstimationComplexityLookup implements java.io.Serializable {

	private static final long serialVersionUID = -330614778634165293L;
	private Integer estimationComplexityID;
	private String estimationComplexityName;

	public EstimationComplexityLookup() {
	}

	public EstimationComplexityLookup(Integer estimationComplexityID,
			String estimationComplexityName) {
		this.estimationComplexityID = estimationComplexityID;
		this.estimationComplexityName = estimationComplexityName;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "EstimationComplexityID", unique = true, nullable = false)
	public Integer getEstimationComplexityID() {
		return estimationComplexityID;
	}

	public void setEstimationComplexityID(Integer estimationComplexityID) {
		this.estimationComplexityID = estimationComplexityID;
	}

	@Column(name = "EstimationComplexityName", length = 45)
	public String getEstimationComplexityName() {
		return estimationComplexityName;
	}

	public void setEstimationComplexityName(String estimationComplexityName) {
		this.estimationComplexityName = estimationComplexityName;
	}

}
