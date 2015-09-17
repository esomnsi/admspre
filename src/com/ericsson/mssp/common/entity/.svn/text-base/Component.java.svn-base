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


@Entity
@Table(name = "Component")
public class Component {
	
	private int componentID;
	private String componentName;
	private String componentType;
	private ProductDetails productDetails;
	
	
	public Component() {
		super();
	}
	public Component(int componentID, String componentName,
			String componentType, ProductDetails productDetails) {
		super();
		this.componentID = componentID;
		this.componentName = componentName;
		this.componentType = componentType;
		this.productDetails = productDetails;
	}
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ComponentID", unique = true, nullable = false)
	public int getComponentID() {
		return componentID;
	}
	public void setComponentID(int componentID) {
		this.componentID = componentID;
	}
	
	@Column(name="ComponentName")
	public String getComponentName() {
		return componentName;
	}
	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}
	
	@Column(name="ComponentType")
	public String getComponentType() {
		return componentType;
	}
	public void setComponentType(String componentType) {
		this.componentType = componentType;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "productID")
	public ProductDetails getProductDetails() {
		return productDetails;
	}
	public void setProductDetails(ProductDetails productDetails) {
		this.productDetails = productDetails;
	}
	
}
