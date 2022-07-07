package com.colak.currencyconverter.service.client;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author AhmetColak date 8.07.2022
 **/
@RequiredArgsConstructor
@Service
public class RestClient {

	//private final RestTemplate rt;

	/*
	public <T> String<T> post(String url, Object request, ParameterizedTypeReference<String<T>> typeReference) {

		HttpEntity<Object> httpEntity = new HttpEntity<>(request);
		ResponseEntity<PostResponse<T>> response = rt.exchange(url, HttpMethod.POST, httpEntity, typeReference);
		return response.getBody();
	}*/

}
