package com.sunbase.services.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sunbase.services.service.AdminExecutor;

@RestController
public class AdminController {

	@Autowired
	AdminExecutor adminexc;

	@PostMapping("admin/authe")
	public ResponseEntity<String> getAuthentication(@RequestParam String loginId, @RequestParam String password) {
		ResponseEntity<String> token = adminexc.getAuthentication(loginId, password);
		return token;
	}
}
