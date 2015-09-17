package com.ericsson.mssp.rate.management.service;

import java.util.List;

import com.ericsson.mssp.common.entity.CurrencyExchange;
import com.ericsson.mssp.rate.management.dto.RateManagementDTO;

public interface IRateManagementService {
	
	public String saveAndUpdate(RateManagementDTO rateManagementDTO);
	public String getUsdValue(String countryName);
	public String updateRateCard(RateManagementDTO rateManagementDTO);
	public String updateCurrencyExchange(String currencyCode, String usdValue, String countryName);
	public List<CurrencyExchange> getCurrencyExchangeList();
	public boolean checkEntry(RateManagementDTO rateManagementDTO);
	public String getCountryName(Integer gscId);
	public String checkDataByCountryID(Integer countryID);

}
