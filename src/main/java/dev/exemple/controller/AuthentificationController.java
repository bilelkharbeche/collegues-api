package dev.exemple.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dev.exemple.Collegue;
import dev.exemple.InfosAuthentification;
import dev.exemple.repository.CollegueRepository;
import dev.exemple.service.CollegueService;
import io.jsonwebtoken.Jwts;

@RestController
public class AuthentificationController {

	@Value("${jwt.expires_in}")
	private Integer EXPIRES_IN;
	@Value("${jwt.cookie}")
	private String TOKEN_COOKIE;
	@Value("${jwt.secret}")
	private String SECRET;

	private CollegueRepository collegueRepository;
	private CollegueService collService;

	private PasswordEncoder passwordEncoder;

	public AuthentificationController(CollegueRepository collegueRepository, PasswordEncoder passwordEncoder) {
		this.collegueRepository = collegueRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@PostMapping(value = "/auth")
	public ResponseEntity<?> authenticate(@RequestBody InfosAuthentification infos) {

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

					return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, tokenCookie.toString()).build();
				}).orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}

	@GetMapping(value = "/auth/user")
	public ResponseEntity<?> afficherCollegueConnecte() {

		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		Optional<Collegue> collegueCo = collegueRepository.findByEmail(email);

		String infos = null;

		if (collegueCo.isPresent() == true) {
			infos = "{\nmatricule : ".concat(collegueCo.get().getMatricule()).concat("\nnom : ")
					.concat(collegueCo.get().getNom()).concat("\nprenoms : ").concat(collegueCo.get().getPrenoms())
					.concat("\nrôles : ").concat(collegueCo.get().getRoles().toString().concat("\n}"));
		}

		return ResponseEntity.status(HttpStatus.ACCEPTED).body(infos);

	}

}
