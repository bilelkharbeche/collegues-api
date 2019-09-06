package dev.exemple.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import dev.exemple.InfosAuthentification;
import dev.exemple.repository.CollegueRepository;
import dev.exemple.service.CollegueService;
import io.jsonwebtoken.Jwts;

@Component
public class AuthUtil {

	@Value("${jwt.expires_in}")
	public Integer EXPIRES_IN;
	@Value("${jwt.cookie}")
	public String TOKEN_COOKIE;
	@Value("${jwt.secret}")
	public String SECRET;
	@Autowired
	public CollegueRepository collegueRepository;
	@Autowired
	public CollegueService collService;
	@Autowired
	public PasswordEncoder passwordEncoder;

	public ResponseEntity<?> auth(InfosAuthentification infos) {
		return authPartial(infos).map(token -> ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, token).build())
				.orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}

	public Optional<String> authPartial(InfosAuthentification infos) {
		return this.collegueRepository.findByEmail(infos.getEmail())
				.filter(utilisateur -> passwordEncoder.matches(infos.getMotDePasse(), utilisateur.getMotDePasse()))
				.map(utilisateur -> {
					Map<String, Object> infosSupplémentaireToken = new HashMap<>();
					infosSupplémentaireToken.put("roles", utilisateur.getRoles());

					String jetonJWT = Jwts.builder().setSubject(utilisateur.getEmail())
							.addClaims(infosSupplémentaireToken)
							.setExpiration(new Date(System.currentTimeMillis() + EXPIRES_IN * 1000))
							.signWith(io.jsonwebtoken.SignatureAlgorithm.HS512, SECRET).compact();

					ResponseCookie tokenCookie = ResponseCookie.from(TOKEN_COOKIE, jetonJWT).httpOnly(true)
							.maxAge(EXPIRES_IN).path("/").build();

					return tokenCookie.toString();
				});
	}

}
