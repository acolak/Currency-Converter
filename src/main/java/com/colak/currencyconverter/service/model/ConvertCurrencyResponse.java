package com.colak.currencyconverter.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author AhmetColak date 8.07.2022
 **/
@AllArgsConstructor
@Data
public class ConvertCurrencyResponse {

	private Double result;
	private String sourceCurrency;
	private String toCurrency;
	private String sourceAmount;
}
