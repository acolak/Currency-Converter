package com.colak.currencyconverter.mapper;

import com.colak.currencyconverter.controller.dto.ExchangeRateResponseDTO;
import com.colak.currencyconverter.service.model.ExchangeRateResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author AhmetColak date 8.07.2022
 **/

@Mapper(componentModel = "spring")
public interface ExchangeRateResponseMapper {

	@Mapping(target = "exchangeRateList", source = "exchangeRateList")
	ExchangeRateResponseDTO toExchangeRateListDTO(ExchangeRateResponse exchangeRateResponse);


}
