package com.ericsson.mssp.common.dto;

public class DeliveryModelDTO {

	 private Integer deliveryModelId;
	    private String name;
	    private String description;
		public Integer getDeliveryModelId() {
			return deliveryModelId;
		}
		public void setDeliveryModelId(Integer deliveryModelId) {
			this.deliveryModelId = deliveryModelId;
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
