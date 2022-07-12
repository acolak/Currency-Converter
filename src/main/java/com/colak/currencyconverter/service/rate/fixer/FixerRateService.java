package com.colak.currencyconverter.service.rate.fixer;

import com.colak.currencyconverter.constant.ErrorCodes;
import com.colak.currencyconverter.exception.FixerApiException;
import com.colak.currencyconverter.service.client.RestClient;
import com.colak.currencyconverter.service.model.ConvertCurrencyResponse;
import com.colak.currencyconverter.service.model.ExchangeRateResponse;
import com.colak.currencyconverter.service.rate.RateProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author AhmetColak date 7.07.2022
 **/
@Service
@RequiredArgsConstructor
public class FixerRateService implements RateProviderService {

	private final String LATEST = "latest";
	private final String CONVERT = "convert";
	@Value("${fixer.serviceUrl}")
	private String fixerUrl;
	private final RestClient restClient;

	@Override
	public ExchangeRateResponse getExchangeRateList(String currency, List<String> targetList) {

		try {

			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(fixerUrl + LATEST).queryParam("symbols", targetList.stream().collect(Collectors.joining(","))).queryParam("base", currency);

			Map<String, Double> rates = (Map<String, Double>) restClient.get(builder).get("rates");

			return new ExchangeRateResponse(rates);
		} catch (Exception e) {
			throw new FixerApiException(ErrorCodes.UNKNOWN_ERROR, String.format("Error occurred when getExchangeRateList transaction, Currency:%s. Message: %s", currency, e.getMessage()), e);
		}
	}

	@Override
	public ConvertCurrencyResponse convertCurrency(String sourceAmount, String sourceCurrency, String targetCurrency) {

		try {

			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(fixerUrl + CONVERT).queryParam("amount", sourceAmount).queryParam("from", sourceCurrency).queryParam("to", targetCurrency);
			Double result = (Double) restClient.get(builder).get("result");

			return new ConvertCurrencyResponse(result, sourceCurrency, targetCurrency, sourceAmount);
		} catch (Exception e) {
			throw new FixerApiException(ErrorCodes.UNKNOWN_ERROR, String.format("Error occurred when convertCurrency transaction, Currency:%s. Message: %s", sourceCurrency, e.getMessage()), e);

		}

	}
}
