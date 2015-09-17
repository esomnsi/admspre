package com.ericsson.mssp.common.dto;

/**
 * ProductEstimationBaseEffortForSolutionDTO
 * 
 * @author EGI
 * 
 */
public class ProductEstimationBaseEffortForSolutionDTO {
	private Integer productEstimationBaseEffortForSolutionID;
	private Integer opportunityScopeID;
	private Integer solutionID;
	private Double totalBaseFTE;
	private Double totalBaseHours;

	public ProductEstimationBaseEffortForSolutionDTO() {
		super();
	}

	public ProductEstimationBaseEffortForSolutionDTO(
			Integer productEstimationBaseEffortForSolutionID,
			Integer opportunityScopeID, Integer solutionID,
			Double totalBaseFTE, Double totalBaseHours) {
		super();
		this.productEstimationBaseEffortForSolutionID = productEstimationBaseEffortForSolutionID;
		this.opportunityScopeID = opportunityScopeID;
		this.solutionID = solutionID;
		this.totalBaseFTE = totalBaseFTE;
		this.totalBaseHours = totalBaseHours;
	}


	public Integer getProductEstimationBaseEffortForSolutionID() {
		return productEstimationBaseEffortForSolutionID;
	}

	public void setProductEstimationBaseEffortForSolutionID(
			Integer productEstimationBaseEffortForSolutionID) {
		this.productEstimationBaseEffortForSolutionID = productEstimationBaseEffortForSolutionID;
	}

	public Integer getOpportunityScopeID() {
		return opportunityScopeID;
	}

	public void setOpportunityScopeID(Integer opportunityScopeID) {
		this.opportunityScopeID = opportunityScopeID;
	}

	public Integer getSolutionID() {
		return solutionID;
	}

	public void setSolutionID(Integer solutionID) {
		this.solutionID = solutionID;
	}

	public Double getTotalBaseFTE() {
		return totalBaseFTE;
	}

	public void setTotalBaseFTE(Double totalBaseFTE) {
		this.totalBaseFTE = totalBaseFTE;
	}

	public Double getTotalBaseHours() {
		return totalBaseHours;
	}

	public void setTotalBaseHours(Double totalBaseHours) {
		this.totalBaseHours = totalBaseHours;
	}
}
