package com.colak.currencyconverter.service.manager;

import com.colak.currencyconverter.repository.ConversionHistoryRepository;
import com.colak.currencyconverter.repository.entity.ConversionHistory;
import com.colak.currencyconverter.service.model.ConvertQueryResponse;
import com.colak.currencyconverter.service.rate.RateProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author AhmetColak date 8.07.2022
 **/
@Service
@RequiredArgsConstructor
public class ConversionManager {

	private final RateProviderService rateProviderService;
	private final ConversionHistoryRepository conversionHistoryRepository;

	public ConvertQueryResponse convertCurrency(String fromCurrency, String toCurrency, String amount) {

		Double rate = rateProviderService.convertCurrency(amount, fromCurrency, toCurrency).getResult();

		ConversionHistory conversionHistory = new ConversionHistory();
		conversionHistory.setConversionHistoryId(fromCurrency + toCurrency);
		conversionHistory.setSourceCurrency(fromCurrency);
		conversionHistory.setTargetCurrency(toCurrency);
		conversionHistory.setCalculatedAmount(String.valueOf(rate));
		conversionHistory.setSourceAmount(amount);
		conversionHistoryRepository.save(conversionHistory);


		return new ConvertQueryResponse(rate); //TODO convertto dto
	}

	public List<ConversionHistory> getConversionHistory(String transactionId, String startDate, String endDate) {

		if(transactionId != null) {
			return conversionHistoryRepository.findAllByConversionHistoryId(transactionId);
		} else if(startDate != null && endDate != null) {
			return conversionHistoryRepository.findAllByCreateDateBetween(LocalDateTime.parse(startDate), LocalDateTime.parse(endDate)).orElseGet(null);
		} else {
			return conversionHistoryRepository.findAll();
		}

	}
}
