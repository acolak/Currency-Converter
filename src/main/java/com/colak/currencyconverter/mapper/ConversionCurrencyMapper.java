package com.colak.currencyconverter.mapper;

import com.colak.currencyconverter.controller.dto.ConvertCurrencyResponseDTO;
import com.colak.currencyconverter.service.model.ConvertCurrencyResponse;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author AhmetColak date 8.07.2022
 **/
@Mapper(componentModel = "spring")
public interface ConversionCurrencyMapper {

	List<ConvertCurrencyResponseDTO.ConvertedCurrencyDTO> toConvertedCurrencyDTO(List<ConvertCurrencyResponse> conversionHistory);

}
