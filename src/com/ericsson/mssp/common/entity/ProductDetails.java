package com.ericsson.mssp.common.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "ProductDetails")
public class ProductDetails {
	
	private Integer productID;
	private String productName;
	private String productDescription;
	private String isActive;
	
	
	public ProductDetails() {
		super();
	}
	
	public ProductDetails(Integer productID, String productName,
			String productDescription, String isActive) {
		super();
		this.productID = productID;
		this.productName = productName;
		this.productDescription = productDescription;
		this.isActive = isActive;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ProductID", unique = true, nullable = false)
	public Integer getProductID() {
		return productID;
	}
	public void setProductID(Integer productID) {
		this.productID = productID;
	}
	
	@Column(name="ProductName")
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	@Column(name="ProductDescription")
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	
	@Column(name="IsActive")
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

}
