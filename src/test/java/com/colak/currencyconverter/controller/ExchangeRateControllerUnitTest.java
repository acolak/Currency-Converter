package com.colak.currencyconverter.controller;

import com.colak.currencyconverter.mapper.ConversionHistoryMapper;
import com.colak.currencyconverter.mapper.ExchangeRateResponseMapper;
import com.colak.currencyconverter.service.manager.ConversionManager;
import com.colak.currencyconverter.service.model.ExchangeRateResponse;
import com.colak.currencyconverter.service.rate.RateProviderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author AhmetColak date 8.07.2022
 **/

@WebMvcTest
@AutoConfigureMockMvc
public class ExchangeRateControllerUnitTest {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private RateProviderService rateProviderService;

	@MockBean
	private ConversionManager conversionManager;

	@MockBean
	private ExchangeRateResponseMapper exchangeRateResponseMapper;

	@MockBean
	private ConversionHistoryMapper conversionHistoryMapper;

	@MockBean
	private RestTemplateBuilder restTemplateBuilder;

	@MockBean
	private RestTemplate restTemplate;

	@Test
	@DisplayName("get-exchange-rate-list controller unit test")
	public void getExchangeRateListUnitTest() throws Exception {

		ExchangeRateResponse exchangeRateResponse = new ExchangeRateResponse();
		HashMap hashMap = new HashMap();
		hashMap.put("TRY", 1.0);
		exchangeRateResponse.setExchangeRateList(hashMap);
		when(rateProviderService.getExchangeRateList(anyString(), anyList())).thenReturn(exchangeRateResponse);

		MvcResult mvcResult = mockMvc.perform(get("/exchange-api/get-exchange-rate-list/{currency}/{targetList}", "EUR", "TRY,USD"))
				.andExpect(status().isOk()).andReturn();

		assertNotNull(mvcResult.getResponse().getContentAsString());
	}

	@Test
	@DisplayName("convert-currency controller unit test")
	public void convertCurrencyUnitTest() throws Exception {

		MvcResult mvcResult = mockMvc.perform(get("/exchange-api/convert-currency/{sourceAmount}/{sourceCurrency}/{targetCurrency}", "10", "EUR", "TRY,USD"))
				.andExpect(status().isOk()).andReturn();

		assertNotNull(mvcResult.getResponse().getContentAsString());
	}

	@Test
	@DisplayName("get-conversion-historycontroller unit test")
	public void getConversionHistory() throws Exception {


		LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
		requestParams.add("transactionId", null);
		requestParams.add("startDate", "2022-07-07T00:01:00.00");
		requestParams.add("endDate", "2022-07-12T23:59:00.00");

		MvcResult mvcResult = mockMvc.perform(get("/exchange-api/get-conversion-history/").params(requestParams))
				.andExpect(status().isOk()).andReturn();

		assertNotNull(mvcResult.getResponse().getContentAsString());
	}




}
