package BEBuildWeek2.Epic_Energy_Services_CRM.auth;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import BEBuildWeek2.Epic_Energy_Services_CRM.entities.Utente;
import BEBuildWeek2.Epic_Energy_Services_CRM.exceptions.UnauthorizedException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JWTTools {

	//trucco per usare i value dall'env 
	private static String secret;
	private static int expiration;
	
	@Value("${spring.application.jwt.secret}")
	public void setSecret(String secretKey) {
		secret = secretKey;
		//log.info(secret); controllo se il token viene letto
	}

	@Value("${spring.application.jwt.expirationindays}")
	public void setExpiration(String expirationInDays) {
		expiration = Integer.parseInt(expirationInDays) * 24 * 60 * 60 * 1000;
	}

	static public String createToken(Utente u) {
		String token = Jwts.builder().setSubject(u.getEmailUtente()).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(Keys.hmacShaKeyFor(secret.getBytes())).compact();
		return token;
		
	}

	static public void isTokenValid(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secret.getBytes())).build().parse(token);

		} catch (MalformedJwtException e) {
			throw new UnauthorizedException("Il token non è valido");
		} catch (ExpiredJwtException e) {
			throw new UnauthorizedException("Il token è scaduto");
		} catch (Exception e) {
			throw new UnauthorizedException("Problemi col token, per favore effettua di nuovo il login");
		}
	}
	
	
	//utilizzo la mail come subject dell'utente
	static public String extractSubject(String token) { 
		return Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secret.getBytes())).build().parseClaimsJws(token)
				.getBody().getSubject();
	}
}
