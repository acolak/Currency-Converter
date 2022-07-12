package com.colak.currencyconverter.model;

/**
 * @author AhmetColak date 8.07.2022
 **/
public class GetResponse<T> {

	private T response;

	public T getResponse() {
		return response;
	}

	public void setResponse(T response) {
		this.response = response;
	}
}
