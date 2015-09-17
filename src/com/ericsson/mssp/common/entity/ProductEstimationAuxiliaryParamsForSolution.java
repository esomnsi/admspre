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
 * ProductEstimationAuxiliaryParamsForSolution
 */
@Entity
@Table(name = "ProductEstimationAuxiliaryParamsForSolution", uniqueConstraints = @UniqueConstraint(columnNames = {
		"SolutionID", "ParamTypeCode" }))
/*@SQLInsert(sql = "insert into ProductEstimationAuxiliaryParamsForSolution (SolutionID, ProductEstimationAuxiliaryParamsID, ParamTypeCode)"
		+ " values (?, ?, ?) "
		+ "ON DUPLICATE KEY UPDATE SolutionID=VALUES(SolutionID),ProductEstimationAuxiliaryParamsID=VALUES(ProductEstimationAuxiliaryParamsID),ParamTypeCode=VALUES(ParamTypeCode)")*/
public class ProductEstimationAuxiliaryParamsForSolution implements java.io.Serializable {

	private static final long serialVersionUID = -330614778634165293L;
	private Integer productEstimationAuxiliaryParamsForSolutionID;
	private Solution solution;
	private ProductEstimationAuxiliaryParams productEstimationAuxiliaryParams;
	private String paramTypeCode;
	
	
	public ProductEstimationAuxiliaryParamsForSolution() {
		super();
	}
	
	public ProductEstimationAuxiliaryParamsForSolution(
			Integer productEstimationAuxiliaryParamsForSolutionID,
			Solution solution,
			ProductEstimationAuxiliaryParams productEstimationAuxiliaryParams) {
		super();
		this.productEstimationAuxiliaryParamsForSolutionID = productEstimationAuxiliaryParamsForSolutionID;
		this.solution = solution;
		this.productEstimationAuxiliaryParams = productEstimationAuxiliaryParams;
	}
	
	public ProductEstimationAuxiliaryParamsForSolution(
			Integer productEstimationAuxiliaryParamsForSolutionID,
			Solution solution,
			ProductEstimationAuxiliaryParams productEstimationAuxiliaryParams,
			String paramTypeCode) {
		super();
		this.productEstimationAuxiliaryParamsForSolutionID = productEstimationAuxiliaryParamsForSolutionID;
		this.solution = solution;
		this.productEstimationAuxiliaryParams = productEstimationAuxiliaryParams;
		this.paramTypeCode = paramTypeCode;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ProductEstimationAuxiliaryParamsForSolutionID", unique = true, nullable = false)
	public Integer getProductEstimationAuxiliaryParamsForSolutionID() {
		return productEstimationAuxiliaryParamsForSolutionID;
	}
	
	public void setProductEstimationAuxiliaryParamsForSolutionID(
			Integer productEstimationAuxiliaryParamsForSolutionID) {
		this.productEstimationAuxiliaryParamsForSolutionID = productEstimationAuxiliaryParamsForSolutionID;
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
	@JoinColumn(name = "ProductEstimationAuxiliaryParamsID")
	public ProductEstimationAuxiliaryParams getProductEstimationAuxiliaryParams() {
		return productEstimationAuxiliaryParams;
	}

	public void setProductEstimationAuxiliaryParams(
			ProductEstimationAuxiliaryParams productEstimationAuxiliaryParams) {
		this.productEstimationAuxiliaryParams = productEstimationAuxiliaryParams;
	}

	@Column(name = "ParamTypeCode")
	public String getParamTypeCode() {
		return paramTypeCode;
	}

	public void setParamTypeCode(String paramTypeCode) {
		this.paramTypeCode = paramTypeCode;
	}
	
}
