package com.ericsson.mssp.common.dto;

import java.util.Date;

public class TTLaborCostDTO {
	
	private Integer tTLaborCostID;
	private OpportunityDTO opportunityDto;
	private SolutionDTO solutionDto;
	private Date weekDate;
	private String rate;
	
	public Integer gettTLaborCostID() {
		return tTLaborCostID;
	}
	public void settTLaborCostID(Integer tTLaborCostID) {
		this.tTLaborCostID = tTLaborCostID;
	}
	public OpportunityDTO getOpportunityDto() {
		return opportunityDto;
	}
	public void setOpportunityDto(OpportunityDTO opportunityDto) {
		this.opportunityDto = opportunityDto;
	}
	public SolutionDTO getSolutionDto() {
		return solutionDto;
	}
	public void setSolutionDto(SolutionDTO solutionDto) {
		this.solutionDto = solutionDto;
	}
	
	public Date getWeekDate() {
		return weekDate;
	}
	public void setWeekDate(Date weekDate) {
		this.weekDate = weekDate;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
}
