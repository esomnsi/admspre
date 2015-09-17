package com.ericsson.mssp.common.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ProductEstimationAuxiliaryParams
 */
@Entity
@Table(name = "ProductEstimationAuxiliaryParams")
public class ProductEstimationAuxiliaryParams implements java.io.Serializable {

	private static final long serialVersionUID = -330614778634165293L;
	private Integer productEstimationAuxiliaryParamsID;
	private String paramType;
	private String paramTypeCode;
	private String paramName;
	private Double paramValue;

	public ProductEstimationAuxiliaryParams() {
		super();
	}

	public ProductEstimationAuxiliaryParams(
			Integer productEstimationAuxiliaryParamsID, String paramType,
			String paramName, Double paramValue) {
		super();
		this.productEstimationAuxiliaryParamsID = productEstimationAuxiliaryParamsID;
		this.paramType = paramType;
		this.paramName = paramName;
		this.paramValue = paramValue;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ProductEstimationAuxiliaryParamsID", unique = true, nullable = false)
	public Integer getProductEstimationAuxiliaryParamsID() {
		return productEstimationAuxiliaryParamsID;
	}

	public void setProductEstimationAuxiliaryParamsID(
			Integer productEstimationAuxiliaryParamsID) {
		this.productEstimationAuxiliaryParamsID = productEstimationAuxiliaryParamsID;
	}

	@Column(name = "ParamType", columnDefinition = "enum('CBIO Version','Customer  Base','CBIO Impacted 3PP nodes')")
	public String getParamType() {
		return paramType;
	}

	public void setParamType(String paramType) {
		this.paramType = paramType;
	}

	@Column(name = "ParamName")
	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	@Column(name = "ParamValue")
	public Double getParamValue() {
		return paramValue;
	}

	public void setParamValue(Double paramValue) {
		this.paramValue = paramValue;
	}

	@Column(name = "ParamTypeCode", columnDefinition = "enum('CBIOVersion','CustomerBase','CBIOImpacted3PPNodes')")
	public String getParamTypeCode() {
		return paramTypeCode;
	}

	public void setParamTypeCode(String paramTypeCode) {
		this.paramTypeCode = paramTypeCode;
	}

}
