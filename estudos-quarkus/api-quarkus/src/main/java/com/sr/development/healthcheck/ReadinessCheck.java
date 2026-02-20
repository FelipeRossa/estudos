package com.sr.development.healthcheck;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.sr.development.StarWarsService;

/*
 * Verifica se a aplicação está pronta para execução
 * 
 * Essas classe podem retornar se a aplicação esta pronta 
 * para execução fazendo a validação de algum paramentro
 * */
@Readiness
public class ReadinessCheck implements HealthCheck {

	@RestClient
	StarWarsService svcStarwars;

	@Override
	public HealthCheckResponse call() {
		if (svcStarwars.getStarships().contains(StarWarsService.MSG_ERROR)) {
			return HealthCheckResponse.down("ERROR");
		} else {
			return HealthCheckResponse.up("OK");
		}
	}

}
