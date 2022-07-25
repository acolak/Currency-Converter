package com.colak.currencyconverter.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.function.Supplier;

/**
 * @author AhmetColak date 25.07.2022
 **/
@Data
@NoArgsConstructor
public class NoConversionHistoryRecordFoundException extends RuntimeException implements Supplier<NoConversionHistoryRecordFoundException> {

	private static final long serialVersionUID = 1L;
	private Integer errorCode;
	private String errorMessage;

	public NoConversionHistoryRecordFoundException(Integer errorCode, String errorMessage) {
		super(errorMessage);
		this.errorCode = errorCode;
	}

	@Override
	public NoConversionHistoryRecordFoundException get() {
		return this;
	}
}
