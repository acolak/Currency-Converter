package com.colak.currencyconverter.controller.dto;

import lombok.Data;

import java.util.List;

/**
 * @author AhmetColak date 8.07.2022
 **/
@Data
public class ConvertCurrencyResponseDTO {


	private List<ConvertedCurrencyDTO> convertedCurrencyList;
	private String transactionId;

	@Data
	public static class ConvertedCurrencyDTO {
		private Double result;
		private String sourceCurrency;
		private String toCurrency;
		private String sourceAmount;
	}
}


