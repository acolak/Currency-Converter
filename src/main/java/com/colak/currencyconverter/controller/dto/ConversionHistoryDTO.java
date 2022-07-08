package com.colak.currencyconverter.controller.dto;

import com.colak.currencyconverter.service.model.ConvertCurrencyResponse;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author AhmetColak date 8.07.2022
 **/
@Data
public class ConversionHistoryDTO {

	private String conversionHistoryId;
	private LocalDateTime createDate = LocalDateTime.now();
	private List<String> targetCurrency;
	private String sourceCurrency;
	private String sourceAmount;
	private List<ConvertCurrencyResponse> calculatedAmount;
}
