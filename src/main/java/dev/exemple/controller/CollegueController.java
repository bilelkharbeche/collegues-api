package dev.exemple.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import dev.exemple.Collegue;
import dev.exemple.exception.CollegueNonTrouveException;
import dev.exemple.service.CollegueService;

@RestController
public class CollegueController {

	@Autowired
	CollegueService collService;

	@RequestMapping(value = "/collegues", method = RequestMethod.GET)
	public List<String> findByName(@RequestParam String nom) {

		List<Collegue> listeColl = collService.rechercherParNom(nom);
		List<String> listeMat = new ArrayList<>();

		for (Collegue liste : listeColl) {
			String matricule = liste.getMatricule();
			listeMat.add(matricule);
		}

		return listeMat;
	}

	@RequestMapping(value = "collegues/{matricule}", method = RequestMethod.GET)
	public Collegue findByMat(@PathVariable String matricule) {

		Collegue collegue = collService.rechercherParMatricule(matricule);

		return collegue;
	}

	@PostMapping("/collegues")
	public Collegue addColl(@RequestBody Collegue collegue) {

		Collegue coll = collService.ajouterUnCollegue(collegue);

		return coll;
	}

	@PatchMapping(value = "collegues/{matricule}")
	public Collegue modifColl(@PathVariable String matricule, @RequestBody String email) {

		Collegue coll = collService.modifierEmail(matricule, email);

		return coll;
	}

	@ExceptionHandler(value = CollegueNonTrouveException.class)
	protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
		String bodyOfResponse = "Collegue non trouvé";
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(bodyOfResponse);
	}

}
