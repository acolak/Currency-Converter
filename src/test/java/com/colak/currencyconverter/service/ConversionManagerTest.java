package com.colak.currencyconverter.service;

import com.colak.currencyconverter.controller.dto.ConvertCurrencyResponseDTO;
import com.colak.currencyconverter.mapper.ConversionCurrencyMapper;
import com.colak.currencyconverter.repository.ConversionHistoryRepository;
import com.colak.currencyconverter.repository.entity.ConversionHistory;
import com.colak.currencyconverter.service.manager.ConversionManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.mongodb.assertions.Assertions.assertNotNull;
import static com.mongodb.assertions.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * @author AhmetColak date 12.07.2022
 **/
@ExtendWith(MockitoExtension.class)
public class ConversionManagerTest{

	@Spy
	@InjectMocks
	private ConversionManager conversionManager;

	@Mock
	private ConversionHistoryRepository conversionHistoryRepository;

	@Mock
	private ConversionCurrencyMapper conversionCurrencyMapper;


	@Test
	@DisplayName("Check for saveConversionHistoryMethod")
	public void saveConversionHistory_When_Successfully() {

		conversionManager.saveConversionHistory(new ArrayList<>(), "EUR", "USD", new ArrayList<>());
		verify(conversionHistoryRepository, times(1)).save(any(ConversionHistory.class));

	}

	@Test
	@DisplayName("Check for convertCurrencyAndSaveHistory Method")
	public void convertCurrencyAndSaveHistory_When_Successfully() {

		when(conversionCurrencyMapper.toConvertedCurrencyDTO(anyList())).thenReturn(new ArrayList<>());

		doNothing().when(conversionManager).saveConversionHistory(anyList(), anyString(), anyString(), anyList());

		ConvertCurrencyResponseDTO convertCurrencyResponseDTO = conversionManager.convertCurrencyAndSaveHistory("EUR", "USD", new ArrayList<>());
		assertNotNull(convertCurrencyResponseDTO);
	}

	@Test
	@DisplayName("Check for getConversionHistory_By_TransactionId Method")
	public void getConversionHistory_When_Query_By_TransactionId() {

		List<ConversionHistory> mockConversionHistory = new ArrayList<>();
		mockConversionHistory.add(new ConversionHistory());
		when(conversionHistoryRepository.findAllByConversionHistoryId("123456")).thenReturn(mockConversionHistory);


		List<ConversionHistory> conversionHistoryList = conversionManager.getConversionHistory("123456", LocalDateTime.now(), LocalDateTime.now());
		assertTrue(!conversionHistoryList.isEmpty());
	}

	@Test
	@DisplayName("Check for getConversionHistory_By_Date Method")
	public void getConversionHistory_When_Query_By_Date() {

		List<ConversionHistory> mockConversionHistory = new ArrayList<>();
		mockConversionHistory.add(new ConversionHistory());
		Optional<List<ConversionHistory>> mockResponse = Optional.of(mockConversionHistory);
		when(conversionHistoryRepository.findAllByCreateDateBetween(any(LocalDateTime.class),  any(LocalDateTime.class))).thenReturn(mockResponse);

		List<ConversionHistory> conversionHistoryList = conversionManager.getConversionHistory(null, LocalDateTime.now(), LocalDateTime.now());
		assertTrue(!conversionHistoryList.isEmpty());
	}


	@Test
	@DisplayName("Check for getConversionHistory_By_Nothing Method")
	public void getConversionHistory_When_Query_By_Nothing() {

		List<ConversionHistory> mockConversionHistory = new ArrayList<>();
		mockConversionHistory.add(new ConversionHistory());
		when(conversionHistoryRepository.findAll()).thenReturn(mockConversionHistory);

		List<ConversionHistory> conversionHistoryList = conversionManager.getConversionHistory(null, null, null);
		assertTrue(!conversionHistoryList.isEmpty());
	}

}
