package com.ericsson.mssp.currency.exchange.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ericsson.mssp.common.constant.MSSPConstants;
import com.ericsson.mssp.common.dao.impl.BaseDAOImpl;
import com.ericsson.mssp.common.entity.CurrencyExchange;
import com.ericsson.mssp.currency.exchange.dao.ICurrencyExchangeDAO;

@Repository
public class CurrencyExchangeDAOImpl extends BaseDAOImpl implements ICurrencyExchangeDAO,
MSSPConstants{

	@Override
	public String saveAndUpdateLatestCurrencyValues(
			CurrencyExchange currencyExchange) {
		String result;
		try{
		saveObject(currencyExchange);
		result = "success";
		}
		catch(Exception e){
			result = "failure";
		}
		
		return result;
	}

	@Override
	public String getUsdValue(String currencyCode) {
		
		String query = "from CurrencyExchange where currencyCode='"+currencyCode+"'";
		logger.info("currency query  :" + query);
		String usdValue = null;
		List<CurrencyExchange> currencyExchanegList = new ArrayList<CurrencyExchange>();
		
		currencyExchanegList = getHibernateTemplate().find(query);
		
		for(CurrencyExchange exchange : currencyExchanegList)
		{
			usdValue = exchange.getUsdValue();
		}
		
		return usdValue;
	}

}
