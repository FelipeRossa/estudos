package com.sr.development.healthcheck;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

/*
 * Verifica se a aplicação está pronta para execução ou em funcionamento
 * 
 * */
@Liveness
public class LivenessCheck implements HealthCheck{

	@Override
	public HealthCheckResponse call() {
		// TODO Auto-generated method stub
		return HealthCheckResponse.up("Liveness Check");
	}

}
