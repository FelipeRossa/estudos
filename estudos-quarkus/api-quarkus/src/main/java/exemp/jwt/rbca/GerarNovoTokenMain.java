package exemp.jwt.rbca;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.microprofile.jwt.Claims;

import io.smallrye.jwt.build.Jwt;

public class GerarNovoTokenMain {

	public static void main(String[] args) throws IOException {

		String token = Jwt.issuer("https://example.com/issuer").upn("jdoe@quarkus.io").preferredUserName("jdoe")
				.groups(new HashSet<>(Arrays.asList("User", "Admin"))).claim(Claims.birthdate.name(), "2001-07-13")
				.sign();

		System.out.println(token);
		System.exit(0);
	}
}
