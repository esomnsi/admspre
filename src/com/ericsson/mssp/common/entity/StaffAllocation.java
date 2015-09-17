package com.ericsson.mssp.common.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="STAFF_CATEGORY_ALLOCATION")
//@JoinColumn(table="STAFF_CATEGORY",name="ID",referencedColumnName="STAFF_CATEGORY_ID")
public class StaffAllocation implements Serializable{
	
	private double percentageEffort;
	private double annualUtil;
	private double fte;
	private double effort;
	private StaffAllocationPK primaryKey;
	
	private StaffCategory staffCategory;
	
	@ManyToOne
	@JoinColumn(name="STAFF_CATEGORY_ID",referencedColumnName="ID",insertable=false,updatable=false)
	public StaffCategory getStaffCategory() {
		return staffCategory;
	}
	public void setStaffCategory(StaffCategory staffCategory) {
		this.staffCategory = staffCategory;
	}
	@EmbeddedId
	public StaffAllocationPK getPrimaryKey() {
		return primaryKey;
	}
	public void setPrimaryKey(StaffAllocationPK primaryKey) {
		this.primaryKey = primaryKey;
	}
	@Column(name="PERCENTAGE_EFFORT")
	public double getPercentageEffort() {
		return percentageEffort;
	}
	public void setPercentageEffort(double percentageEffort) {
		this.percentageEffort = percentageEffort;
	}
	@Column(name="ANNUAL_UTILIZATION")
	public double getAnnualUtil() {
		return annualUtil;
	}
	public void setAnnualUtil(double annualUtil) {
		this.annualUtil = annualUtil;
	}
	@Column(name="FTE")
	public double getFte() {
		return fte;
	}
	public void setFte(double fte) {
		this.fte = fte;
	}
	@Column(name="EFFORT")
	public double getEffort() {
		return effort;
	}
	public void setEffort(double effort) {
		this.effort = effort;
	}
}
