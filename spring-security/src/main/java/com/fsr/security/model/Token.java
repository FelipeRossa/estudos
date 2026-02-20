package com.fsr.security.model;

import java.util.Calendar;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "token")
public class Token {

	@Id
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "token", unique = true, nullable = false)
	private String token;

	@Column(name = "data_expiracao", unique = true, nullable = false)
	private Calendar expiracao;
	
	
	

}
