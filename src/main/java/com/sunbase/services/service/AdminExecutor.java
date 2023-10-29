package com.sunbase.services.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AdminExecutor {

	private final String assignmentAuthUrl = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment_auth.jsp";

	public ResponseEntity<String> getAuthentication(String loginId, String password) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON_UTF8);
		String requestBody = String.format("{\"login_id\":\"%s\",\"password\":\"%s\"}", loginId, password);

		HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.exchange(assignmentAuthUrl, HttpMethod.POST, requestEntity,
				String.class);

		return response;
	}

}
