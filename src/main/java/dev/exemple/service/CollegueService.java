package dev.exemple.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import dev.exemple.Collegue;
import dev.exemple.exception.CollegueInvalideException;
import dev.exemple.exception.CollegueNonTrouveException;

public class CollegueService {

	private Map<String, Collegue> data = new HashMap<>();

	public CollegueService() {
		// TODO alimenter data avec des données fictives
		// Pour générer un matricule : `UUID.randomUUID().toString()

		String matId1 = UUID.randomUUID().toString();
		String matId2 = UUID.randomUUID().toString();
		String matId3 = UUID.randomUUID().toString();
		String matId4 = UUID.randomUUID().toString();

		data.put(matId1, new Collegue(matId1, "KHARBECHE", "Bilel", "test@hotmail.fr", LocalDate.of(1997, 05, 06),
				"URLDEMALADE"));
		data.put(matId2, new Collegue(matId2, "KHARBECHE2", "Bilel2", "test@hotmail.fr2", LocalDate.of(1997, 05, 06),
				"URLDEMALADE2"));
		data.put(matId3, new Collegue(matId3, "KHARBECHE3", "Bilel3", "test@hotmail.fr3", LocalDate.of(1997, 05, 06),
				"URLDEMALADE3"));
		data.put(matId4, new Collegue(matId4, "KHARBECHE4", "Bilel4", "test@hotmail.fr4", LocalDate.of(1997, 05, 06),
				"URLDEMALADE4"));
	}

	public List<Collegue> rechercherParNom(String nomRecherche) {
		// TODO retourner une liste de collègues dont le nom est fourni
		List<Collegue> listeColl = new ArrayList<>();

		for (Collegue collegue : data.values()) {
			if (collegue.getNom().equals(nomRecherche)) {
				listeColl.add(collegue);
			}
		}

		return listeColl;
	}

	public Collegue rechercherParMatricule(String matriculeRecherche) {
		// TODO retourner le collègue dont le matricule est fourni

		// TODO retourner une exception `CollegueNonTrouveException` (à créer)
		// si le matricule ne correspond à aucun collègue

		Collegue collegue = data.get(matriculeRecherche);

		if (collegue == null) {
			throw new CollegueNonTrouveException("Collegue non trouvé");
		}
		return collegue;

	}

	public Collegue ajouterUnCollegue(Collegue collegueAAjouter) {

		if (collegueAAjouter.getNom().length() < 2 || collegueAAjouter.getPrenoms().length() < 2) {
			throw new CollegueInvalideException("Le nom et les prénoms doivent faire plus de deux caractères");
		}

		if (collegueAAjouter.getEmail().length() < 3 || !collegueAAjouter.getEmail().contains("@")) {
			throw new CollegueInvalideException("L'email doit posséder 3 caractères minimum et un '@'");
		}

		if (!collegueAAjouter.getPhotoUrl().startsWith("http")) {
			throw new CollegueInvalideException("L'url doit commencer par 'http'");
		}

		if (LocalDate.now().getYear() - collegueAAjouter.getDateDeNaissance().getYear() < 18) {
			throw new CollegueInvalideException("L'âge doit être supérieur ou égal à 18");
		}

		String collMat = UUID.randomUUID().toString();
		collegueAAjouter.setMatricule(collMat);

		data.put(collMat, collegueAAjouter);

		return collegueAAjouter;

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

}
