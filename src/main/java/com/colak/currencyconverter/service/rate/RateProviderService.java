package com.colak.currencyconverter.service.rate;

import com.colak.currencyconverter.service.model.ConvertCurrencyResponse;
import com.colak.currencyconverter.service.model.ExchangeRateResponse;

import java.util.List;

/**
 * @author AhmetColak date 7.07.2022 Copyright © TurkcellTech
 **/
public interface RateProviderService {

	ExchangeRateResponse getExchangeRateList(String currency, List<String> targetList);

	ConvertCurrencyResponse convertCurrency(String sourceAmount, String sourceCurrency, String targetCurrency);
}
