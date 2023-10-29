package com.sunbase.services.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sunbase.services.entity.Customer;
import com.sunbase.services.service.CustomerExecutor;

@RestController
public class CustomerController {

	@Autowired
	CustomerExecutor customerexc;

	@PostMapping("customer/save")
	public ResponseEntity<String> getAuthentication(@RequestHeader("Authorization") String token,
			@RequestBody Customer customer) {
		ResponseEntity<String> res = customerexc.createCustomer(token, customer);
		return res;
	}

	@GetMapping("customer/list")
	public List<Customer> getCustomerList(@RequestHeader("Authorization") String token) {
		List<Customer> customerList = customerexc.getCustomerList(token);
		return customerList;
	}

	@PostMapping("customer/delete")
	public ResponseEntity<String> deleteCustomer(@RequestHeader("Authorization") String token,
			@RequestParam("uuid") String uuid) {
		ResponseEntity<String> response = customerexc.deleteCustomer(token, uuid);
		return response;
	}

	@PostMapping("customer/update")
	public ResponseEntity<String> updateCustomer(@RequestHeader("Authorization") String token,
			@RequestParam("uuid") String uuid, @RequestBody Customer customerRequest) {
		ResponseEntity<String> response = customerexc.updateCustomer(token, uuid, customerRequest);

		return response;
	}
}
