package dev.exemple.service;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import dev.exemple.Collegue;
import dev.exemple.exception.CollegueInvalideException;

@Component
public class CollegueValidator {

	public void validerCollegue(Collegue collegueAValider) {

		if (collegueAValider.getNom().length() < 2 || collegueAValider.getPrenoms().length() < 2) {
			throw new CollegueInvalideException("Le nom et les prénoms doivent faire plus de deux caractères");
		}

		if (collegueAValider.getEmail().length() < 3 || !collegueAValider.getEmail().contains("@")) {
			throw new CollegueInvalideException("L'email doit posséder 3 caractères minimum et un '@'");
		}

		if (!collegueAValider.getPhotoUrl().startsWith("http")) {
			throw new CollegueInvalideException("L'url doit commencer par 'http'");
		}

		if (LocalDate.now().getYear() - collegueAValider.getDateDeNaissance().getYear() < 18) {
			throw new CollegueInvalideException("L'âge doit être supérieur ou égal à 18");
		}
	}

	// TODO Vérifier que le nom et les prenoms ont chacun au moins 2
	// caractères
	// TODO Vérifier que l'email a au moins 3 caractères et contient `@`
	// TODO Vérifier que la photoUrl commence bien par `http`
	// TODO Vérifier que la date de naissance correspond à un age >= 18
	// TODO Si une des règles ci-dessus n'est pas valide, générer une
	// exception :
	// `CollegueInvalideException`.

	// TODO générer un matricule pour ce collègue
	// (`UUID.randomUUID().toString()`)

	// TODO Sauvegarder le collègue

}
