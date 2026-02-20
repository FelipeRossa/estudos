package com.fsr.security.dto;

import java.util.Calendar;

public class TokenDTO {
	private Long id;

	private String token;

	private Calendar expiracao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Calendar getExpiracao() {
		return expiracao;
	}

	public void setExpiracao(Calendar expiracao) {
		this.expiracao = expiracao;
	}

}
