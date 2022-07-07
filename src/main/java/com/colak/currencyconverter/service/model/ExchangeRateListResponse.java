package com.colak.currencyconverter.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Map;

/**
 * @author AhmetColak date 7.07.2022
 **/
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ExchangeRateListResponse {

	private Map<String, Double> exchangeRateList;

}
