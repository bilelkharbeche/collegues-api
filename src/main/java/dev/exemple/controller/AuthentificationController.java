package dev.exemple.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dev.exemple.Collegue;
import dev.exemple.InfosAuthentification;
import dev.exemple.repository.CollegueRepository;

@RestController
public class AuthentificationController {

	public CollegueRepository collegueRepository;
	private AuthUtil authUtil;

	/**
	 * Constructor
	 * 
	 * @param collegueRepository
	 * @param authUtil
	 */
	public AuthentificationController(CollegueRepository collegueRepository, AuthUtil authUtil) {
		super();
		this.collegueRepository = collegueRepository;
		this.authUtil = authUtil;
	}

	@PostMapping(value = "/auth")
	public ResponseEntity<?> authenticate(@RequestBody InfosAuthentification infos) {

		return authUtil.auth(infos);
	}

	@GetMapping(value = "/auth/user")
	public ResponseEntity<?> afficherCollegueConnecte() {

		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		Optional<Collegue> collegueCo = collegueRepository.findByEmail(email);

		String infos = null;

		if (collegueCo.isPresent() == true) {
			infos = "{\nmatricule:".concat(collegueCo.get().getMatricule()).concat("\nnom:")
					.concat(collegueCo.get().getNom()).concat("\nprenoms:").concat(collegueCo.get().getPrenoms())
					.concat("\nrôles:").concat(collegueCo.get().getRoles().toString().concat("\n}"));
		}

		return ResponseEntity.status(HttpStatus.ACCEPTED).body(infos);

	}

}
