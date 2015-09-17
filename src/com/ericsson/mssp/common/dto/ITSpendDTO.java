/**
* -------------------------------------------------------------------------------------------------------
* Copyright (C) 2012 Ericsson India Global Pvt Limited
* All Rights Reserved
* Project         		    :  IT_MSSP
* Module				    :  com.ericsson.mssp.common.dto
* File name       		    :  ITSpendDTO.java
* Description				:	<To Do>
* Author, Date & Release	:	Dec 18, 20122012
* Modification history		:
* Number	|Date   	   |Author        |Remark
* -----------------------------------------------------------------------------------------------------
* 1      	| Dec 18, 2012  	   |eruvwyn   	  | Created.
* -----------------------------------------------------------------------------------------------------
**/
 
package com.ericsson.mssp.common.dto;

/**
 * @author eruvwyn
 *
 */
public class ITSpendDTO {
	//CAPEX Spend Analysis
	private String itemDesc;
	private String capInScope;
	private String category;
	private String capBudgetLocalCurr;
	private String capBudgetUSD;
	private String capSpendForecast;
	private String capLocalCurrency;
	private String capManDays;
	private String capFrom;
	private String capTo;
	private String capRemark;
	
	
	//OPEX Spend Analysis
	/**
	 * @return the itemDesc
	 */
	public String getItemDesc() {
		return itemDesc;
	}
	/**
	 * @param itemDesc the itemDesc to set
	 */
	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}
	/**
	 * @return the capInScope
	 */
	public String getCapInScope() {
		return capInScope;
	}
	/**
	 * @param capInScope the capInScope to set
	 */
	public void setCapInScope(String capInScope) {
		this.capInScope = capInScope;
	}
	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}
	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	/**
	 * @return the capBudgetLocalCurr
	 */
	public String getCapBudgetLocalCurr() {
		return capBudgetLocalCurr;
	}
	/**
	 * @param capBudgetLocalCurr the capBudgetLocalCurr to set
	 */
	public void setCapBudgetLocalCurr(String capBudgetLocalCurr) {
		this.capBudgetLocalCurr = capBudgetLocalCurr;
	}
	/**
	 * @return the capBudgetUSD
	 */
	public String getCapBudgetUSD() {
		return capBudgetUSD;
	}
	/**
	 * @param capBudgetUSD the capBudgetUSD to set
	 */
	public void setCapBudgetUSD(String capBudgetUSD) {
		this.capBudgetUSD = capBudgetUSD;
	}
	/**
	 * @return the capSpendForecast
	 */
	public String getCapSpendForecast() {
		return capSpendForecast;
	}
	/**
	 * @param capSpendForecast the capSpendForecast to set
	 */
	public void setCapSpendForecast(String capSpendForecast) {
		this.capSpendForecast = capSpendForecast;
	}
	/**
	 * @return the capLocalCurrency
	 */
	public String getCapLocalCurrency() {
		return capLocalCurrency;
	}
	/**
	 * @param capLocalCurrency the capLocalCurrency to set
	 */
	public void setCapLocalCurrency(String capLocalCurrency) {
		this.capLocalCurrency = capLocalCurrency;
	}
	/**
	 * @return the capManDays
	 */
	public String getCapManDays() {
		return capManDays;
	}
	/**
	 * @param capManDays the capManDays to set
	 */
	public void setCapManDays(String capManDays) {
		this.capManDays = capManDays;
	}
	/**
	 * @return the capFrom
	 */
	public String getCapFrom() {
		return capFrom;
	}
	/**
	 * @param capFrom the capFrom to set
	 */
	public void setCapFrom(String capFrom) {
		this.capFrom = capFrom;
	}
	/**
	 * @return the capTo
	 */
	public String getCapTo() {
		return capTo;
	}
	/**
	 * @param capTo the capTo to set
	 */
	public void setCapTo(String capTo) {
		this.capTo = capTo;
	}
	/**
	 * @return the capRemark
	 */
	public String getCapRemark() {
		return capRemark;
	}
	/**
	 * @param capRemark the capRemark to set
	 */
	public void setCapRemark(String capRemark) {
		this.capRemark = capRemark;
	}

	
	
}
