package com.colak.currencyconverter.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * @author AhmetColak date 8.07.2022
 **/

@Data
@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
public class ConversionHistory {

	@Id
	private String conversionHistoryId;

	@Builder.Default
	@Indexed
	private LocalDateTime createDate = LocalDateTime.now();
	private String targetCurrency;
	private String sourceCurrency;
	private String sourceAmount;
	private String calculatedAmount;
}
