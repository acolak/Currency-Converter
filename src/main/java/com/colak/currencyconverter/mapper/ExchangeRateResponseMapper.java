package com.colak.currencyconverter.mapper;

import com.colak.currencyconverter.controller.dto.ExchangeRateResponseDTO;
import com.colak.currencyconverter.service.model.ExchangeRateResponse;
import org.mapstruct.Mapper;

/**
 * @author AhmetColak date 8.07.2022
 **/

@Mapper(componentModel = "spring")
public interface ExchangeRateResponseMapper {

	ExchangeRateResponseDTO toExchangeRateListDTO(ExchangeRateResponse exchangeRateResponse);


}
