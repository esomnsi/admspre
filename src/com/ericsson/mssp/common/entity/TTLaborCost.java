package com.ericsson.mssp.common.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="TTLaborCost")
public class TTLaborCost {
	
	private Integer tTLaborCostID;
	private Opportunity opportunity;
	private Solution solution;
	private Date weekDate;
	private Float rate;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="TTLaborCostID", unique = true, nullable = false)
	public Integer gettTLaborCostID() {
		return tTLaborCostID;
	}
	public void settTLaborCostID(Integer tTLaborCostID) {
		this.tTLaborCostID = tTLaborCostID;
	}
	
	@Column(name="WeekDate")
	public Date getWeekDate() {
		return weekDate;
	}
	public void setWeekDate(Date weekDate) {
		this.weekDate = weekDate;
	}
	
	@Column(name="Rate")
	public Float getRate() {
		return rate;
	}
	public void setRate(Float rate) {
		this.rate = rate;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OpportunityID")
	public Opportunity getOpportunity() {
		return opportunity;
	}
	public void setOpportunity(Opportunity opportunity) {
		this.opportunity = opportunity;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SolutionID")
	public Solution getSolution() {
		return solution;
	}
	public void setSolution(Solution solution) {
		this.solution = solution;
	}

}
