package com.ericsson.mssp.rate.management.dao;

import java.util.List;

import com.ericsson.mssp.common.entity.CurrencyExchange;
import com.ericsson.mssp.common.entity.RateCard;
import com.ericsson.mssp.rate.management.dto.RateManagementDTO;

public interface IRateManagementDAO {

	public String saveAndUpdate(RateCard rateCard);
	public String updateRateCard(RateManagementDTO rateManagementDTO);
	public Integer getJobRoleStageID(Integer jobRoleID, Integer jobStageID);
	public Integer getJobRoleStageID(String jobRole, String jobStage,Integer ccmFlag);
	public String getUsdValue(String currencyCode);
	public String updateCurrencyExchange(String currencyCode, String usdValue, String countryName);
	public List<CurrencyExchange> getCurrencyExchangeList();
	public boolean checkEntry(RateManagementDTO rateManagementDTO);
	public String getCountryName(Integer gscId);
	public String checkDataByCountryID(Integer countryID);
}
