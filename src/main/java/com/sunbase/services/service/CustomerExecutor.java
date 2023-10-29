package com.sunbase.services.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.sunbase.services.entity.Customer;

@Component
public class CustomerExecutor {

	private final String Customerurl = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp";

	public ResponseEntity<String> createCustomer(String token, Customer customer) {
		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON_UTF8);
		headers.set("Authorization", "Bearer " + token);

		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(Customerurl).queryParam("cmd", "create");

		String requestBody = String.format(
				"{\"first_name\":\"%s\",\"last_name\":\"%s\",\"street\":\"%s\",\"address\":\"%s\",\"city\":\"%s\",\"state\":\"%s\",\"email\":\"%s\",\"phone\":\"%s\"}",
				customer.getFirst_name(), customer.getLast_name(), customer.getStreet(), customer.getAddress(),
				customer.getCity(), customer.getState(), customer.getEmail(), customer.getPhone());

		HttpEntity<Customer> requestEntity = new HttpEntity<>(customer, headers);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, requestEntity,
				String.class);

		return response;

	}

	public List<Customer> getCustomerList(String token) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer " + token);

		String url = Customerurl + "?cmd=get_customer_list";

		HttpEntity<String> requestEntity = new HttpEntity<>(headers);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Customer[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity,
				Customer[].class);

		List<Customer> customerList = Arrays.asList(responseEntity.getBody());
		return customerList;

	}

	public ResponseEntity<String> deleteCustomer(String token, String uuid) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer " + token);

		String url = Customerurl + "?cmd=delete&uuid=" + uuid;

		HttpEntity<String> requestEntity = new HttpEntity<>(headers);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

		return response;
	}

	public ResponseEntity<String> updateCustomer(String token, String uuid, Customer customer) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer " + token);

		String url = Customerurl + "?cmd=update&uuid=" + uuid;

		HttpEntity<Customer> requestEntity = new HttpEntity<>(customer, headers);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

		return response;
	}
}
