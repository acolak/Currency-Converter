package com.colak.currencyconverter.service.manager;

import com.colak.currencyconverter.controller.dto.ConvertCurrencyResponseDTO;
import com.colak.currencyconverter.exception.ErrorBody;
import com.colak.currencyconverter.exception.RepositoryException;
import com.colak.currencyconverter.mapper.ConversionCurrencyMapper;
import com.colak.currencyconverter.repository.ConversionHistoryRepository;
import com.colak.currencyconverter.repository.entity.ConversionHistory;
import com.colak.currencyconverter.service.model.ConvertCurrencyResponse;
import com.colak.currencyconverter.service.rate.RateProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author AhmetColak date 8.07.2022
 **/
@Service
@RequiredArgsConstructor
public class ConversionManager {

	private final RateProviderService rateProviderService;
	private final ConversionHistoryRepository conversionHistoryRepository;
	private final ConversionCurrencyMapper conversionCurrencyMapper;

	public ConvertCurrencyResponseDTO convertCurrencyAndSaveHistory(String amount, String fromCurrency, List<String> toCurrencyList) {

		ConvertCurrencyResponseDTO convertCurrencyResponseDTO = new ConvertCurrencyResponseDTO();

		List<ConvertCurrencyResponse> convertCurrencyResponseList = new ArrayList<>();

		toCurrencyList.forEach(toCurrency -> {
			convertCurrencyResponseList.add(rateProviderService.convertCurrency(amount, fromCurrency, toCurrency));
		});

		String transactionId = generateTransactionId();
		convertCurrencyResponseDTO.setConvertedCurrencyList(conversionCurrencyMapper.toConvertedCurrencyDTO(convertCurrencyResponseList));
		convertCurrencyResponseDTO.setTransactionId(transactionId);

		saveConversionHistory(convertCurrencyResponseList, transactionId, fromCurrency, toCurrencyList);

		return convertCurrencyResponseDTO;
	}

	public void saveConversionHistory(List<ConvertCurrencyResponse> convertCurrencyResponseList, String transactionId, String fromCurrency, List<String> toCurrencyList) {

		try {
			ConversionHistory.ConversionHistoryBuilder conversionHistoryBuilder = ConversionHistory.builder();
			conversionHistoryBuilder.conversionHistoryId(transactionId)
					.calculatedAmount(convertCurrencyResponseList)
					.conversionHistoryId(transactionId)
					.sourceCurrency(fromCurrency)
					.targetCurrency(toCurrencyList);

			conversionHistoryRepository.save(conversionHistoryBuilder.build());
		} catch (Exception e) {
			throw new RepositoryException(new ErrorBody(101, e.getMessage()));
		}
	}

	public List<ConversionHistory> getConversionHistory(String transactionId, LocalDateTime startDate, LocalDateTime endDate) {

		try {
			if (transactionId != null) {
				return conversionHistoryRepository.findAllByConversionHistoryId(transactionId);
			} else if (startDate != null && endDate != null) {
				return conversionHistoryRepository.findAllByCreateDateBetween(startDate, endDate).orElseGet(null);
			} else {
				return conversionHistoryRepository.findAll();
			}
		} catch (Exception e) {
			throw new RepositoryException(new ErrorBody(101, e.getMessage()));
		}
	}

	private String generateTransactionId() {
		return String.valueOf(System.currentTimeMillis());
	}

}
