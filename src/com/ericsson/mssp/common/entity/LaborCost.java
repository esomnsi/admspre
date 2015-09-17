package com.ericsson.mssp.common.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the LaborCost database table.
 * 
 */
@Entity
@Table(name="LaborCost")
public class LaborCost implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name="LaborCostID", unique = true, nullable = false)
	private int laborCostID;

	@Column(name="Cost")
	private float cost;

	@Column(name="CurrencyCode")
	private String currencyCode;

	@Column(name="JobRoleID")
	private int jobRoleID;

	@Column(name="Location")
	private String location;

	@Column(name="Month")
	private int month;

	@Column(name="ServiceScopeID")
	private int serviceScopeID;

	@Column(name="SolutionID")
	private int solutionID;

	@Column(name="Year")
	private int year;

    public LaborCost() {
    }

	public int getLaborCostID() {
		return this.laborCostID;
	}

	public void setLaborCostID(int laborCostID) {
		this.laborCostID = laborCostID;
	}

	public float getCost() {
		return this.cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public String getCurrencyCode() {
		return this.currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public int getJobRoleID() {
		return this.jobRoleID;
	}

	public void setJobRoleID(int jobRoleID) {
		this.jobRoleID = jobRoleID;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getMonth() {
		return this.month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getServiceScopeID() {
		return this.serviceScopeID;
	}

	public void setServiceScopeID(int serviceScopeID) {
		this.serviceScopeID = serviceScopeID;
	}

	public int getSolutionID() {
		return this.solutionID;
	}

	public void setSolutionID(int solutionID) {
		this.solutionID = solutionID;
	}

	public int getYear() {
		return this.year;
	}

	public void setYear(int year) {
		this.year = year;
	}

}