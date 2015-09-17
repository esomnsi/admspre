package com.ericsson.mssp.common.dto;

import com.ericsson.mssp.common.entity.OpportunityDetail;

public class OpportunityLocationDTO {
	
	private Integer opportunityLocationId;
	private OpportunityDetailDTO opportunityDetailDTO;
	private Integer customerCountry;
	private Integer nearShore;
	private Integer gsc1;
	private Integer gsc2;
	
	public Integer getOpportunityLocationId() {
		return opportunityLocationId;
	}
	public void setOpportunityLocationId(Integer opportunityLocationId) {
		this.opportunityLocationId = opportunityLocationId;
	}
	
	public Integer getCustomerCountry() {
		return customerCountry;
	}
	public void setCustomerCountry(Integer customerCountry) {
		this.customerCountry = customerCountry;
	}
	public Integer getNearShore() {
		return nearShore;
	}
	public void setNearShore(Integer nearShore) {
		this.nearShore = nearShore;
	}
	public Integer getGsc1() {
		return gsc1;
	}
	public void setGsc1(Integer gsc1) {
		this.gsc1 = gsc1;
	}
	public Integer getGsc2() {
		return gsc2;
	}
	public void setGsc2(Integer gsc2) {
		this.gsc2 = gsc2;
	}
	public OpportunityDetailDTO getOpportunityDetailDTO() {
		return opportunityDetailDTO;
	}
	public void setOpportunityDetailDTO(OpportunityDetailDTO opportunityDetailDTO) {
		this.opportunityDetailDTO = opportunityDetailDTO;
	}
}