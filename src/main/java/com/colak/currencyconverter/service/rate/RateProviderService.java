package com.colak.currencyconverter.service.rate;

import com.colak.currencyconverter.service.model.ConvertQueryResponse;
import com.colak.currencyconverter.service.model.ExchangeRateListResponse;

import java.util.List;

/**
 * @author AhmetColak date 7.07.2022 Copyright © TurkcellTech
 **/
public interface RateProviderService {


	ExchangeRateListResponse getExchangeRateList(String currency, List<String> targetList);

	ConvertQueryResponse convertCurrency(String sourceAmount, String sourceCurrency, String targetCurrency);
}
