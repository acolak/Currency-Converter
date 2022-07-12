package com.colak.currencyconverter.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author AhmetColak date 8.07.2022
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FixerApiException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private ErrorBody errorBody;

	public FixerApiException(ErrorBody errorBody, Throwable cause) {
		super(errorBody.getErrorDetail(), cause);
	}

}
