package com.colak.currencyconverter.controller;

import com.colak.currencyconverter.repository.entity.ConversionHistory;
import com.colak.currencyconverter.service.manager.ConversionManager;
import com.colak.currencyconverter.service.model.ExchangeRateListResponse;
import com.colak.currencyconverter.service.rate.RateProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author AhmetColak date 7.07.2022
 **/
@RestController
@RequestMapping("/exchange-api")
@RequiredArgsConstructor
public class ExchangeRateController {

	private final RateProviderService rateProviderService;

	private final ConversionManager conversionManager;

	@GetMapping("get-exchange-rate-list/{currency}/{targetList}")
	@ResponseStatus(HttpStatus.OK)
	public ExchangeRateListResponse getExchangeRateList(@PathVariable("currency") String currency, @PathVariable("targetList") List<String> targetList) {

		return rateProviderService.getExchangeRateList(currency, targetList);

	}

	@GetMapping("convert-currency/{sourceAmount}/{sourceCurrency}/{targetCurrency}")
	@ResponseStatus(HttpStatus.OK)
	public void convertCurrency(@PathVariable String sourceAmount, @PathVariable String sourceCurrency, @PathVariable String targetCurrency) {

		conversionManager.convertCurrency(sourceAmount, sourceCurrency, targetCurrency);

	}

	@GetMapping("get-convert-history/{transactionId}/{startDate}/{endDate}")
	@ResponseStatus(HttpStatus.OK)
	public List<ConversionHistory> getConvertHistoryByDateRangeOrId(@PathVariable(required = false) String transactionId, @PathVariable(required = false) String startDate,
			@PathVariable(required = false) String endDate) {

		return conversionManager.getConversionHistory(transactionId, startDate, endDate);

	}

}
