package com.colak.currencyconverter.controller;

import com.colak.currencyconverter.mapper.ExchangeRateResponseMapper;
import com.colak.currencyconverter.service.manager.ConversionManager;
import com.colak.currencyconverter.service.model.ExchangeRateResponse;
import com.colak.currencyconverter.service.rate.RateProviderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * @author AhmetColak date 8.07.2022
 **/

@SpringBootTest
@AutoConfigureMockMvc
public class ExchangeRateControllerUnitTest {

	@Autowired
	private MockMvc mvc;
	@Autowired
	private ObjectMapper objectMapper;

	@Mock
	private RateProviderService rateProviderService;

	@Mock
	private ExchangeRateResponseMapper exchangeRateResponseMapper;

	@Test
	public void getExchangeRateListUnitTest() throws Exception {

		ExchangeRateResponse exchangeRateResponse = new ExchangeRateResponse();
		HashMap hashMap = new HashMap();
		hashMap.put("TRY", 1.0);
		exchangeRateResponse.setExchangeRateList(hashMap);
		when(rateProviderService.getExchangeRateList(anyString(), anyList())).thenReturn(exchangeRateResponse);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/exchange-api/get-exchange-rate-list/{currency}/{targetList}")
				.content(objectMapper.writeValueAsString("request")).contentType(MediaType.APPLICATION_JSON);
		mvc.perform(requestBuilder)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
	}


}
