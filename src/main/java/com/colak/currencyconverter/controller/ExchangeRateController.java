package com.colak.currencyconverter.controller;

import com.colak.currencyconverter.controller.dto.ConversionHistoryDTO;
import com.colak.currencyconverter.controller.dto.ConversionHistoryResponseDTO;
import com.colak.currencyconverter.controller.dto.ConvertCurrencyResponseDTO;
import com.colak.currencyconverter.controller.dto.ExchangeRateResponseDTO;
import com.colak.currencyconverter.mapper.ConversionHistoryMapper;
import com.colak.currencyconverter.mapper.ExchangeRateResponseMapper;
import com.colak.currencyconverter.repository.entity.ConversionHistory;
import com.colak.currencyconverter.service.manager.ConversionManager;
import com.colak.currencyconverter.service.model.ExchangeRateResponse;
import com.colak.currencyconverter.service.rate.RateProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
	private final ExchangeRateResponseMapper exchangeRateResponseMapper;
	private final ConversionHistoryMapper conversionHistoryMapper;

	@GetMapping("get-exchange-rate-list/{currency}/{targetList}")
	@ResponseStatus(HttpStatus.OK)
	public ExchangeRateResponseDTO getExchangeRateList(@PathVariable("currency") String currency, @PathVariable("targetList") List<String> targetList) {

		ExchangeRateResponse exchangeRateResponse = rateProviderService.getExchangeRateList(currency, targetList);
		return exchangeRateResponseMapper.toExchangeRateListDTO(exchangeRateResponse);

	}

	@GetMapping("convert-currency/{sourceAmount}/{sourceCurrency}/{targetCurrency}")
	@ResponseStatus(HttpStatus.OK)
	public ConvertCurrencyResponseDTO convertCurrency(@PathVariable String sourceAmount, @PathVariable String sourceCurrency,  @PathVariable("targetCurrency") List<String> targetCurrency) {

		return conversionManager.convertCurrencyAndSaveHistory(sourceAmount, sourceCurrency, targetCurrency);
	}

	@GetMapping("get-conversion-history")
	@ResponseStatus(HttpStatus.OK)
	public ConversionHistoryResponseDTO getConvertHistoryByDateRangeOrId(@RequestParam(required = false) String transactionId, @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	LocalDateTime startDate, @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {

		List<ConversionHistory> conversionHistoryList =  conversionManager.getConversionHistory(transactionId, startDate, endDate);

		List<ConversionHistoryDTO> conversionHistoryDTOList = conversionHistoryMapper.toConversionHistoryDTOList(conversionHistoryList);

		return new ConversionHistoryResponseDTO(conversionHistoryDTOList);

	}

}
