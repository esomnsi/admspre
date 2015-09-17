package com.ericsson.mssp.currency.exchange.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ericsson.mssp.common.entity.CurrencyExchange;
import com.ericsson.mssp.currency.exchange.dao.ICurrencyExchangeDAO;
import com.ericsson.mssp.currency.exchange.dto.CurrencyExchangeDTO;
import com.ericsson.mssp.currency.exchange.service.ICurrencyExchangeService;

@Service
public class CurrencyExchangeServiceImpl implements ICurrencyExchangeService {

	@Autowired
	ICurrencyExchangeDAO currencyExchangeDAO;
	
	@Override
	public String saveAndUpdateLatestCurrencyValues(CurrencyExchangeDTO currencyExchangeDTO) {
	
		CurrencyExchange currencyExchange = new CurrencyExchange();
		
		BeanUtils.copyProperties(currencyExchangeDTO, currencyExchange);
		
		
		return currencyExchangeDAO.saveAndUpdateLatestCurrencyValues(currencyExchange);
		
	}

	@Override
	public String getUsdValue(String currencyCode) {
		
		return currencyExchangeDAO.getUsdValue(currencyCode);
		
	}

}
