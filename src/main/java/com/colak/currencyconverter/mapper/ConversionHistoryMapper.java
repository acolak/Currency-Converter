package com.colak.currencyconverter.mapper;

import com.colak.currencyconverter.controller.dto.ConversionHistoryDTO;
import com.colak.currencyconverter.repository.entity.ConversionHistory;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author AhmetColak date 8.07.2022
 **/
@Mapper(componentModel = "spring")
public interface ConversionHistoryMapper {

	ConversionHistoryDTO toConversionHistoryDTO(ConversionHistory conversionHistory);

	List<ConversionHistoryDTO> toConversionHistoryDTOList(List<ConversionHistory> conversionHistoryList);
}
