package com.colak.currencyconverter.service.fixer;

import com.colak.currencyconverter.service.model.ConvertQueryResponse;
import com.colak.currencyconverter.service.model.ExchangeRateListResponse;
import com.colak.currencyconverter.service.rate.RateProviderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author AhmetColak date 7.07.2022
 **/
@Service
public class FixerRateService implements RateProviderService {

	private final String API_KEY_HEADER_KEY = "apikey";
	private final String LATEST = "latest";
	private final String CONVERT = "convert";
	@Value("${fixer.serviceUrl}")
	private String fixerUrl;
	@Value("${fixer.apiKey}")
	private String apiKey;

	@Override
	public ExchangeRateListResponse getExchangeRateList(String currency, List<String> targetList) {

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set(API_KEY_HEADER_KEY, apiKey);

		HttpEntity<List<String>> entity = new HttpEntity<>(targetList, headers);

		UriComponentsBuilder builder = UriComponentsBuilder.
				fromHttpUrl(fixerUrl + LATEST).
				queryParam("symbols", targetList.stream().collect(Collectors.joining(","))).
				queryParam("base", currency);

		ResponseEntity<Map> response = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, entity, Map.class);
		Map<String, Double> rates = (Map<String, Double>) response.getBody().get("rates");

		return new ExchangeRateListResponse(rates);
	}

	@Override
	public ConvertQueryResponse convertCurrency(String sourceAmount, String sourceCurrency, String targetCurrency) {

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set(API_KEY_HEADER_KEY, apiKey);

		HttpEntity<String> entity = new HttpEntity<>(headers);

		UriComponentsBuilder builder = UriComponentsBuilder.
				fromHttpUrl(fixerUrl + CONVERT).
				queryParam("amount", sourceAmount).
				queryParam("from", sourceCurrency).
				queryParam("to", targetCurrency);

		ResponseEntity<Map> response  = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, entity, Map.class);
		Double result = (Double) response.getBody().get("result");

		return new ConvertQueryResponse(result);

	}
}
