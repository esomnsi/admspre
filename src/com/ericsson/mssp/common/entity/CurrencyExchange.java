package com.ericsson.mssp.common.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CurrencyExchange")
public class CurrencyExchange implements Serializable{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4142525953306510649L;

	private Integer currencyExchangeID;
	private String currencyCode;
	
	private String countryName;
	private String usdValue;
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "CurrencyExchangeID", unique = true, nullable = false)
	public Integer getCurrencyExchangeID() {
		return currencyExchangeID;
	}
	public void setCurrencyExchangeID(Integer currencyExchangeID) {
		this.currencyExchangeID = currencyExchangeID;
	}
	
	@Column(name = "CountryName", length = 100)
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	
	@Column(name="USDValue" , length=20)
	public String getUsdValue() {
		return usdValue;
	}
	public void setUsdValue(String usdValue) {
		this.usdValue = usdValue;
	}
	
	@Column(name="CurrencyCode", length=50)
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	
}
