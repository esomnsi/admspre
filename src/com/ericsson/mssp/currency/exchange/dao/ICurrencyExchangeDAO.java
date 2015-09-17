package com.ericsson.mssp.currency.exchange.dao;

import com.ericsson.mssp.common.entity.CurrencyExchange;

public interface ICurrencyExchangeDAO {

	public String saveAndUpdateLatestCurrencyValues(CurrencyExchange currencyExchange);
	public String getUsdValue(String currencyCode);
}
