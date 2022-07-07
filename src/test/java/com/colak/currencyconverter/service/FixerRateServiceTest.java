package com.colak.currencyconverter.service;

import com.colak.currencyconverter.service.fixer.FixerRateService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author AhmetColak date 7.07.2022
 **/
@ExtendWith(MockitoExtension.class)
public class FixerRateServiceTest {

	@Spy
	@InjectMocks
	private FixerRateService fixerRateService;

	@Test
	public void getExchangeRateListTest() {

		List<String> targetList = new ArrayList<>();
		targetList.add("TRY");

		fixerRateService.getExchangeRateList("EUR", targetList);

		//assertNotNull(responseEntity);

	}

}
