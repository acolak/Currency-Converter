package com.colak.currencyconverter.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * @author AhmetColak date 8.07.2022
 **/

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorBody> handleGeneralException(Exception exception) {
		HttpStatus responseStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		ErrorBody errorBody = new ErrorBody(500, "Unexcepted Error!");
		log.error(exception.getMessage() + " trace : "+ exception.getStackTrace().toString());
		return new ResponseEntity<>(errorBody, responseStatus);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<Object> handleMethodArgumentTypeMismatch(HttpServletRequest request,MethodArgumentTypeMismatchException ex){
		return new ResponseEntity<>("Girdiginiz Parametreleri kontrol ediniz!", BAD_REQUEST);
	}


	@ExceptionHandler(FixerApiException.class)
	public ResponseEntity<ErrorBody> handleFixerApiException(FixerApiException exception) {
		HttpStatus responseStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		ErrorBody errorBody = new ErrorBody(exception.getErrorCode(), exception.getErrorMessage());
		log.error(exception.getErrorCode() + "-" + exception.getErrorMessage());
		return new ResponseEntity<>(errorBody, responseStatus);
	}

	@ExceptionHandler(RepositoryException.class)
	public ResponseEntity<ErrorBody> handleRepositoryException(RepositoryException exception) {
		HttpStatus responseStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		ErrorBody errorBody = new ErrorBody(exception.getErrorCode(), exception.getErrorMessage());
		log.error(exception.getErrorCode() + "-" + exception.getErrorMessage());
		return new ResponseEntity<>(errorBody, responseStatus);
	}

	@ExceptionHandler(NoConversionHistoryRecordFoundException.class)
	public ResponseEntity<ErrorBody> handleNoConversionHistoryRecordFoundException(NoConversionHistoryRecordFoundException exception) {
		HttpStatus responseStatus = HttpStatus.OK;
		ErrorBody errorBody = new ErrorBody(exception.getErrorCode(), exception.getErrorMessage());
		log.error(exception.getErrorCode() + "-" + exception.getErrorMessage());
		return new ResponseEntity<>(errorBody, responseStatus);
	}


}
