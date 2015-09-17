package com.ericsson.mssp.common.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
/**
 *PeopleStackResourceDetailDTO is mapped with "PEOPLE_STACK" table.
 *PEOPLE_STACK has a composite primary key(SOL_ID,RESOURCE_ID) 
 *@EmbeddedId identifies  PeopleStackResourceDetailPK representing the primary key (composite)
 */
@Entity
@Table(name = "PEOPLE_STACK")
public class PeopleStackResourceDetail implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private PeopleStackResourceDetailPK dtoPK;
	private String name;
	private String jobPosition;
	private String category;
	private int reportingMgr;
	private String department;
	private int inScope;
	private String scope;
	private String baseLocation;
	private double annualHrs;
	private double yearlyUtilization;
	private double annualSummary;
	private String cuurency;
	private float yearsOfEmployment;
	private double annualSalaryUSSD;
	@EmbeddedId
	public PeopleStackResourceDetailPK getDtoPK() {
		return dtoPK;
	}
	public void setDtoPK(PeopleStackResourceDetailPK dtoPK) {
		this.dtoPK = dtoPK;
	}
	@Column(name = "NAME", length = 45)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "ROLE", length = 45)
	public String getJobPosition() {
		return jobPosition;
	}
	public void setJobPosition(String jobPosition) {
		this.jobPosition = jobPosition;
	}
	@Column(name = "CATEGORY", length = 45)
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	@Column(name = "REPORTING_MGR",  length = 1)
	public int getReportingMgr() {
		return reportingMgr;
	}
	public void setReportingMgr(int reportingMgr) {
		this.reportingMgr = reportingMgr;
	}
	@Column(name = "DEPARTMENT", length = 45)
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	@Column(name = "IN_SCOPE", length = 1)
	public int getInScope() {
		return inScope;
	}
	public void setInScope(int inScope) {
		this.inScope = inScope;
	}
	@Column(name = "SCOPE", length = 45)
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	@Column(name = "BASE_LOCATION", length = 45)
	public String getBaseLocation() {
		return baseLocation;
	}
	public void setBaseLocation(String baseLocation) {
		this.baseLocation = baseLocation;
	}
	@Column(name = "ANNUAL_HRS")
	public double getAnnualHrs() {
		return annualHrs;
	}
	public void setAnnualHrs(double annualHrs) {
		this.annualHrs = annualHrs;
	}
	@Column(name = "YEARLY_UTIL")
	public double getYearlyUtilization() {
		return yearlyUtilization;
	}
	public void setYearlyUtilization(double yearlyUtilization) {
		this.yearlyUtilization = yearlyUtilization;
	}
	@Column(name = "ANNUAL_SALARY")
	public double getAnnualSummary() {
		return annualSummary;
	}
	public void setAnnualSummary(double annualSummary) {
		this.annualSummary = annualSummary;
	}
	@Column(name = "CURRENCY", length = 10)
	public String getCuurency() {
		return cuurency;
	}
	public void setCuurency(String cuurency) {
		this.cuurency = cuurency;
	}
	@Column(name = "YEARS_EMPLOYMENT")
	public float getYearsOfEmployment() {
		return yearsOfEmployment;
	}
	public void setYearsOfEmployment(float yearsOfEmployment) {
		this.yearsOfEmployment = yearsOfEmployment;
	}
	@Column(name = "ANNUAL_SALARY_USSD")
	public double getAnnualSalaryUSSD() {
		return annualSalaryUSSD;
	}
	public void setAnnualSalaryUSSD(double annualSalaryUSSD) {
		this.annualSalaryUSSD = annualSalaryUSSD;
	}
}
