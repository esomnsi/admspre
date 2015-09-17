package com.ericsson.mssp.common.dto;

import com.ericsson.mssp.common.entity.ProductDetails;

public class ComponentDTO {
	
	private int componentID;
	private String componentName;
	private String componentType;
	private ProductDetails productDetails;
	private Integer productID;
	private boolean checked = false;
	
	public ComponentDTO() {
		super();
	}

	
	public ComponentDTO(int componentID, String componentName,
			String componentType, ProductDetails productDetails,
			Integer productID, boolean checked) {
		super();
		this.componentID = componentID;
		this.componentName = componentName;
		this.componentType = componentType;
		this.productDetails = productDetails;
		this.productID = productID;
		this.checked = checked;
	}


	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public int getComponentID() {
		return componentID;
	}
	public void setComponentID(int componentID) {
		this.componentID = componentID;
	}
	public String getComponentName() {
		return componentName;
	}
	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}
	public String getComponentType() {
		return componentType;
	}
	public void setComponentType(String componentType) {
		this.componentType = componentType;
	}
	public ProductDetails getProductDetails() {
		return productDetails;
	}
	public void setProductDetails(ProductDetails productDetails) {
		this.productDetails = productDetails;
	}
	public Integer getProductID() {
		return productID;
	}
	public void setProductID(Integer productID) {
		this.productID = productID;
	}

}
