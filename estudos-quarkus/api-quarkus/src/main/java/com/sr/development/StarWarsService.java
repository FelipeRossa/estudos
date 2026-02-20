package com.sr.development;


import java.time.temporal.ChronoUnit;

import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@RegisterRestClient(baseUri = "https://swapi.info/api/")
public interface StarWarsService {
	
	public static final String MSG_ERROR = "Fallback";

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("starships")
	@Timeout(
			value = 3,
			unit = ChronoUnit.SECONDS
	)
	@Fallback(
			fallbackMethod = "getStarshipsFallback" // Metodo que vai ser executado se der algum erro
	)
	@CircuitBreaker(
			requestVolumeThreshold = 2, // Define quantas requests vai usar pra definir que há algum problema
			failureRatio = .5, //porcentagens de falhas aceitas para querar o circuito
			delay = 3000L,
			successThreshold = 2// quantas execuções eu assumo que esta funcionando
	)
	public String getStarships();
	
	default String getStarshipsFallback() {
		return MSG_ERROR; 
	};

}
