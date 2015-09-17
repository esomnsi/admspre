package com.ericsson.mssp.rate.management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ericsson.mssp.common.entity.Country;
import com.ericsson.mssp.common.entity.CurrencyExchange;
import com.ericsson.mssp.common.entity.JobRoleStages;
import com.ericsson.mssp.common.entity.RateCard;
import com.ericsson.mssp.rate.management.dao.IRateManagementDAO;
import com.ericsson.mssp.rate.management.dto.RateManagementDTO;
import com.ericsson.mssp.rate.management.service.IRateManagementService;

@Service
public class RateManagementServiceImpl implements IRateManagementService {

	@Autowired
	IRateManagementDAO rateManagementDAO;
	
	@Override
	public String updateRateCard(RateManagementDTO rateManagementDTO){
		return rateManagementDAO.updateRateCard(rateManagementDTO);
	}
	
	@Override
	public String saveAndUpdate(RateManagementDTO rateManagementDTO) {
	
		RateCard rateCard = new RateCard();
		
		JobRoleStages jobRoleStages = new JobRoleStages();
		Integer jobRoleStageID = rateManagementDAO.getJobRoleStageID(rateManagementDTO.getJobRoleID(),rateManagementDTO.getJobStageID());
		jobRoleStages.setJobRoleStagesId(jobRoleStageID);
		
		Country country = new Country();
		country.setCountryId(rateManagementDTO.getCountryID());
				
		rateCard.setCountry(country);
		rateCard.setJobRoleStages(jobRoleStages);
		rateCard.setRate(rateManagementDTO.getRate());
		rateCard.setGsc(rateManagementDTO.getGsc());
		rateCard.setSubLocation(rateManagementDTO.getSubLocation());
		rateCard.setLocation(rateManagementDTO.getLocation());
		rateCard.setCreatedTimestamp(new java.sql.Timestamp(rateManagementDTO.getDate().getTime()));
		rateCard.setUpdatedTimestamp(new java.sql.Timestamp(rateManagementDTO.getUpdatedDate().getTime()));
		rateCard.setStatusFlag("L");
	
		return rateManagementDAO.saveAndUpdate(rateCard);
	}

	@Override
	public String getUsdValue(String currencyCode) {
		
		return rateManagementDAO.getUsdValue(currencyCode);
		
	}

	@Override
	public String updateCurrencyExchange(String currencyCode, String usdValue, String countryName) {
		return rateManagementDAO.updateCurrencyExchange(currencyCode, usdValue, countryName);
	}

	@Override
	public List<CurrencyExchange> getCurrencyExchangeList() {
		return rateManagementDAO.getCurrencyExchangeList();
	}

	@Override
	public boolean checkEntry(RateManagementDTO rateManagementDTO) {
		return rateManagementDAO.checkEntry(rateManagementDTO);
	}

	@Override
	public String getCountryName(Integer gscId) {
		return rateManagementDAO.getCountryName(gscId);
	}

	@Override
	public String checkDataByCountryID(Integer countryID) {
		return rateManagementDAO.checkDataByCountryID(countryID);
	}

}
