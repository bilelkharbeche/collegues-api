package dev.exemple.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import dev.exemple.Collegue;

public class CollegueService {

	private Map<String, Collegue> data = new HashMap<>();

	public CollegueService() {
		// TODO alimenter data avec des données fictives
		// Pour générer un matricule : `UUID.randomUUID().toString()`

		data.put(UUID.randomUUID().toString(), new Collegue(UUID.randomUUID().toString(), "KHARBECHE", "Bilel",
				"test@hotmail.fr", LocalDate.of(1997, 05, 06), "URLDEMALADE"));
		data.put(UUID.randomUUID().toString(), new Collegue(UUID.randomUUID().toString(), "KHARBECHE2", "Bilel2",
				"test@hotmail.fr2", LocalDate.of(1997, 05, 06), "URLDEMALADE2"));
		data.put(UUID.randomUUID().toString(), new Collegue(UUID.randomUUID().toString(), "KHARBECHE3", "Bilel3",
				"test@hotmail.fr3", LocalDate.of(1997, 05, 06), "URLDEMALADE3"));
		data.put(UUID.randomUUID().toString(), new Collegue(UUID.randomUUID().toString(), "KHARBECHE4", "Bilel4",
				"test@hotmail.fr4", LocalDate.of(1997, 05, 06), "URLDEMALADE4"));
	}

	public List<Collegue> rechercherParNom(String nomRecherche) {
		// TODO retourner une liste de collègues dont le nom est fourni

		List<Collegue> listeColl = new ArrayList();
		Iterator<Collegue> IterColl = data.values().iterator();

		while (IterColl.hasNext()) {
			Collegue collegue = IterColl.next();
			if (collegue.getNom().equals(nomRecherche)) {
				listeColl.add(collegue);
			}
		}

		return listeColl;
	}

}
