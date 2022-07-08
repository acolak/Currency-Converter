package com.colak.currencyconverter.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author AhmetColak date 8.07.2022
 **/
@Data
@AllArgsConstructor
public class ErrorBody {

	private int errorCode;
	private String errorDetail;
}
