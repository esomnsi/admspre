package com.ericsson.mssp.common.dto;

public class OpportunitySourceDTO {

	 private Integer opportunitySourceId;
	    private String name;
	    private String description;
	    private Integer orderId;
		public Integer getOpportunitySourceId() {
			return opportunitySourceId;
		}
		public void setOpportunitySourceId(Integer opportunitySourceId) {
			this.opportunitySourceId = opportunitySourceId;
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
		public Integer getOrderId() {
			return orderId;
		}
		public void setOrderId(Integer orderId) {
			this.orderId = orderId;
		}
	    
}
