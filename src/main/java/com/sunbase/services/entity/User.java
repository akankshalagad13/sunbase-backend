package com.sunbase.services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class User {

	@Column(name = "login_id")
	private String login_id;

	@Column(name = "password")
	private String password;
}
