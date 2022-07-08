package com.colak.currencyconverter.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author AhmetColak date 8.07.2022
 **/

@Data
@AllArgsConstructor
public class ConversionHistoryResponseDTO {

	private List<ConversionHistoryDTO> conversionHistory;


}
