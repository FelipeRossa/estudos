package com.fsr.security.security;

import java.security.Key;
import java.util.Collections;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.fsr.security.model.Usuario;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

public class TokenUtil {

	public static final String EMISSOR = "FelipeSR"; // issuer | emissor -> informado no token
	public static final long EXPIRATION = 60 * 60 * 1000; // Expiração do token

	/**
	 * Chave secreta para criptografia do token - permite fazer a criptografia do
	 * token Precisa conter 256 Bits, cada caracter ocupa 8 bits, então são
	 * necessários pelo menos 32 caracteres
	 */
	public static final String SECRET_KEY = "0123456789012345678901234567890123456789";

	public static final String SECRET_KEY_GOOGLE = "283913695839-v3cgqt6dos1b67gbkj96nentlfiq3ggp.apps.googleusercontent.com";

	public static TokenRec encode(Usuario user) {
		try {
			Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

			// TODO montar o token com a senha encriptada do usuário
			String jwtToken = Jwts.builder().subject(user.getUserName()) // monta a chave com nome de usuario
					.expiration(new Date(System.currentTimeMillis() + EXPIRATION)) // data de expiração
					.issuer(EMISSOR).signWith(key) // Emissor com a chave
					// Também é possível adicionar ".claim(EMISSOR, key).signWith(key)" por exemplo,
					// util pra quando tiver tipos de usuários diferentes
					.compact();
			return new TokenRec(jwtToken);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public static Authentication decode(HttpServletRequest request) {
		try {
			String header = request.getHeader("Authorization");
			Boolean isLogingoogle = Boolean.parseBoolean(request.getHeader("loginGoole"));
			System.out.println("Request header: " + header);
			if (header != null) {
				String token = header.replace("Bearer ", "");

				if (isLogingoogle) {
					GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(),
							new JacksonFactory()).setAudience(Collections.singletonList(SECRET_KEY_GOOGLE)).build();

					GoogleIdToken idToken = verifier.verify(token);

					if (idToken != null) {

						GoogleIdToken.Payload payload = idToken.getPayload();

						/*
						 * Com o login do google é possível obter alguns dados do token e realizar o
						 * cadastro do usuário
						 * 
						 * TODO Verificar uma rotina para validar se usuário existe e fazer o cadastro
						 * 
						 */
						String email = payload.getEmail();
						System.out.println(email);
						System.out.println(payload.getIssuer());
						System.out.println(payload.getIssuee());
						System.out.println(payload.get("name"));
						System.out.println(payload.get("given_name"));
						System.out.println(payload.get("family_name"));
						System.out.println(payload.get("picture"));

						// Fazer uma validação melhor para autenticação
						Date expiration = new Date(payload.getExpirationTimeSeconds() * 1000);
						if (expiration.after(new Date(System.currentTimeMillis()))) {
							return new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList());
						}
					}

				} else {
					SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
					JwtParser parser = Jwts.parser().verifyWith(key).build();
					Claims claims = (Claims) parser.parse(token).getPayload();

					// Aqui tem vários dados que podem ser inseridos e obtidos do token

					String subject = claims.getSubject();
					String issuer = claims.getIssuer();
					Date exp = claims.getExpiration();

					if (issuer.equals(EMISSOR) && subject.length() > 0
							&& exp.after(new Date(System.currentTimeMillis())))
						return new UsernamePasswordAuthenticationToken("valido", null, Collections.emptyList());

				}

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
