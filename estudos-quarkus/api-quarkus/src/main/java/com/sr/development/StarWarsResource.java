package com.sr.development;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("starwars")
@Produces(MediaType.APPLICATION_JSON)
public class StarWarsResource {

	@RestClient
	StarWarsService svcStarWars;
	
	
	@GET()
	@Path("starships")
	public String getStarships() {
		return svcStarWars.getStarships();
	}
}
