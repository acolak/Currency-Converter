package com.colak.currencyconverter.service.client;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

/**
 * @author AhmetColak date 8.07.2022
 **/
@RequiredArgsConstructor
@Service
public class RestClient {

	private final String API_KEY_HEADER_KEY = "apikey";
	@Value("${fixer.apiKey}")
	private String apiKey;
	private final RestTemplate restTemplate;

	public Map get(UriComponentsBuilder builder) {
		HttpHeaders headers = new HttpHeaders();
		headers.set(API_KEY_HEADER_KEY, apiKey);

		HttpEntity<List<String>> entity = new HttpEntity<>(null, headers);

		ResponseEntity<Map> response = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, entity, Map.class);
		return response.getBody();
	}

}
