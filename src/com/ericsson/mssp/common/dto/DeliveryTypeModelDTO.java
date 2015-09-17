package com.ericsson.mssp.common.dto;

public class DeliveryTypeModelDTO {

	private Integer deliveryTypeId;
    private String name;
    private String description;
	public Integer getDeliveryTypeId() {
		return deliveryTypeId;
	}
	public void setDeliveryTypeId(Integer deliveryTypeId) {
		this.deliveryTypeId = deliveryTypeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
