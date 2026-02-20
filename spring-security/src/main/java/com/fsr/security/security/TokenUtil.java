package com.fsr.security.security;

import java.security.Key;
import java.util.Collections;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.fsr.security.model.Usuario;

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

	public static TokenRec encode(Usuario user) {
		try {
			Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
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
			System.out.println("Request header: " + header);
			if (header != null) {
				String token = header.replace("Bearer ", "");
				SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
				JwtParser parser = Jwts.parser().verifyWith(key).build();
				Claims claims = (Claims) parser.parse(token).getPayload();

				String subject = claims.getSubject();
				String issuer = claims.getIssuer();
				Date exp = claims.getExpiration();

				if (issuer.equals(EMISSOR) && subject.length() > 0 && exp.after(new Date(System.currentTimeMillis())))
					return new UsernamePasswordAuthenticationToken("valido", null, Collections.emptyList());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
