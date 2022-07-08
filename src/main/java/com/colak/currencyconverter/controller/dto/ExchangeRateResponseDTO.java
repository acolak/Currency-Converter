package com.colak.currencyconverter.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

/**
 * @author AhmetColak date 8.07.2022
 **/
@Data
@AllArgsConstructor
public class ExchangeRateResponseDTO {

	private Map<String, Double> exchangeRateList;

}
