package dev.exemple.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.exemple.Collegue;
import dev.exemple.service.CollegueService;

@RestController
public class CollegueController {

	@RequestMapping(value = "/collegues/{nom}", method = RequestMethod.GET)
	public List<String> findMatriculeCli(@PathVariable String nom) {

		CollegueService collService = new CollegueService();
		List<Collegue> listeColl = collService.rechercherParNom(nom);
		List<String> listeMat = new ArrayList();

		for (Collegue liste : listeColl) {
			String matricule = liste.getMatricule();
			listeMat.add(matricule);
		}

		return listeMat;
	}
}
