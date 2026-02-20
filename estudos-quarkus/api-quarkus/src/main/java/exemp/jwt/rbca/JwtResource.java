package exemp.jwt.rbca;

import java.util.Set;

import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.Claims;

import io.quarkus.security.Authenticated;
import io.smallrye.jwt.build.Jwt;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/jwt")
@Produces(MediaType.TEXT_PLAIN)
@RequestScoped
public class JwtResource {

	@Claim(standard = Claims.preferred_username)
	private String username;

	@GET
	@Path("/claim")
	@RolesAllowed("Subscriber") // se colocar No-Subscriber por exemplo já não aceita pq foi gerado com Subscriber
	@Produces(MediaType.TEXT_PLAIN)
	public String getClaim() {
		/**
		 * // Mesmo sem nenhuma anotação de Autenticação no metodo, ele requisita a
		 * autenticação (TOKEN) aqui só nessa requisição pois parece que ao acessar uma
		 * variavel do tipo
		 * 
		 * @Claim(standard = Claims.preferred_username) private String username;
		 * 
		 *                 internamente o token deve ser validado
		 */
		return username;
	}

	// ISSO da pra usar pra gerar um token a partir de um usuário e senha e e-mail e
	// autenticar com esse token
	@GET()
	@Path("gerar-token")
	public String gerar() {
		return Jwt.issuer("https://example.com/issuer").upn("jdoe@quarkus.io").preferredUserName("jdoe")
				.groups(Set.of("Subscriber")).claim(Claims.birthdate.name(), "2001-07-13").sign();
	}

	@GET()
	@Authenticated // Isso aqui exige autenticação com o token
	@Path("teste-com-autenticacao")
	@Produces(MediaType.TEXT_PLAIN)
	public String testeComAutenticacao() {

		return "Acessou o Metodo";
	}

	// Aqui acessa sem token
	@GET()
	@Path("teste-sem-autenticacao")
	@Produces(MediaType.TEXT_PLAIN)
	public String testeSemAutenticacao() {

		return "Acessou o Metodo";
	}
}
