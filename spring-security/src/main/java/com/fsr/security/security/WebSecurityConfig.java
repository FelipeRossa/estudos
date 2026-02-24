package com.fsr.security.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf((csrf) -> {
			csrf.disable();
		}).authorizeHttpRequests((auth) -> {

			/*
			 * Pode usar tambem auth.requestMatchers(new
			 * AntPathRequestMatcher("/open","GET")).permitAll() para estabelecer também o
			 * tipo da request nesse caso não sei porque mas não consigo importar
			 * AntPathRequestMatcher
			 */

			auth.requestMatchers("/novo-usuario").permitAll() //
					.requestMatchers("/auth/login").permitAll() //
					.requestMatchers("/login.html", "/css/**", "/js/**").permitAll()
					.anyRequest().authenticated();

		}).addFilterBefore(new AuthFilter(), UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}
}
