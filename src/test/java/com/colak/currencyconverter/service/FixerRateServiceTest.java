package com.colak.currencyconverter.service;

import com.colak.currencyconverter.exception.FixerApiException;
import com.colak.currencyconverter.service.client.RestClient;
import com.colak.currencyconverter.service.config.TestConfig;
import com.colak.currencyconverter.service.model.ConvertCurrencyResponse;
import com.colak.currencyconverter.service.model.ExchangeRateResponse;
import com.colak.currencyconverter.service.rate.fixer.FixerRateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

/**
 * @author AhmetColak date 7.07.2022
 **/
@ExtendWith(MockitoExtension.class)
public class FixerRateServiceTest extends TestConfig {


	@Mock
	private RestTemplate restTemplate;

	@InjectMocks
	private FixerRateService fixerRateService;
	@InjectMocks
	private RestClient restClient;

	@Value("${fixer.serviceUrl}")
	private String fixerUrl;

	@Value("${fixer.apiKey}")
	private String apiKey;

	@BeforeEach
	void setUp() {
		restClient = new RestClient(restTemplate);
		fixerRateService = new FixerRateService(restClient);
		ReflectionTestUtils.setField(restClient, "apiKey", apiKey);
		ReflectionTestUtils.setField(fixerRateService, "fixerUrl", fixerUrl);

	}

	@Test
	@DisplayName("Check for getExchangeRateListTest")
	public void getExchangeRateListTest_When_Successfully() {

		List<String> targetList = new ArrayList<>();
		targetList.add("TRY");

		Map bodyMap = new HashMap();
		Map rates = new HashMap();
		rates.put("TRY", 17.0);
		bodyMap.put("rates", rates);
		ResponseEntity<Map> entityResp = new ResponseEntity<>(bodyMap, HttpStatus.OK);


		Mockito.when(restTemplate.exchange(
				any(URI.class),
				any(HttpMethod.class),
				any(HttpEntity.class),
				eq(Map.class))).thenReturn(entityResp);



		ExchangeRateResponse exchangeRateResponse = fixerRateService.getExchangeRateList("EUR", targetList);

		assertNotNull(exchangeRateResponse);
		assertThat(exchangeRateResponse.getExchangeRateList().get("TRY").equals(17.0));
	}


	@Test
	@DisplayName("Check for getExchangeRateListTest when Exception")
	public void getExchangeRateListTest_When_Exception() {
		List<String> targetList = new ArrayList<>();

		Mockito.when(restTemplate.exchange(
				any(URI.class),
				any(HttpMethod.class),
				any(HttpEntity.class),
				eq(Map.class))).thenThrow(RestClientException.class);


		assertThatThrownBy(() -> fixerRateService.getExchangeRateList("EUR", targetList))
				.isInstanceOf(FixerApiException.class)
				.hasMessageContaining("Error occurred when getExchangeRateList transaction");
	}


	@Test
	@DisplayName("Check for convertCurrency")
	public void convertCurrency_When_Successfully() {

		List<String> targetList = new ArrayList<>();
		targetList.add("TRY");

		Map bodyMap = new HashMap();
		Double result  = 170.0;
		bodyMap.put("result", result);
		ResponseEntity<Map> entityResp = new ResponseEntity<>(bodyMap, HttpStatus.OK);

		Mockito.when(restTemplate.exchange(
				any(URI.class),
				any(HttpMethod.class),
				any(HttpEntity.class),
				eq(Map.class))).thenReturn(entityResp);


		ConvertCurrencyResponse convertCurrencyResponse = fixerRateService.convertCurrency("10", "EUR", "TRY");

		assertNotNull(convertCurrencyResponse);
		assertThat(convertCurrencyResponse.getResult().equals(170.0));
	}


	@Test
	@DisplayName("Check for convertCurrency when Exception")
	public void convertCurrency_When_Exception() {
		List<String> targetList = new ArrayList<>();

		Mockito.when(restTemplate.exchange(
				any(URI.class),
				any(HttpMethod.class),
				any(HttpEntity.class),
				eq(Map.class))).thenThrow(RestClientException.class);


		assertThatThrownBy(() -> fixerRateService.convertCurrency("10", "EUR", "TRY"))
				.isInstanceOf(FixerApiException.class)
				.hasMessageContaining("Error occurred when convertCurrency transaction");
	}

}
