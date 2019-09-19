package dev.exemple.service;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import dev.exemple.Collegue;
import dev.exemple.exception.CollegueInvalideException;
import dev.exemple.exception.CollegueNonTrouveException;
import dev.exemple.repository.CollegueRepository;

@Component
public class CollegueService {

	private static final Logger LOG = LoggerFactory.getLogger(CollegueService.class);

	@Autowired
	private CollegueRepository collegueRepository;

	@Autowired
	CollegueValidator collValidator;

	public List<Collegue> rechercherParNom(String nomRecherche) {
		// TODO retourner une liste de collègues dont le nom est fourni
		List<Collegue> listeColl = collegueRepository.findByNom(nomRecherche);

		return listeColl;
	}

	public Collegue rechercherParMatricule(String matriculeRecherche) {
		// TODO retourner le collègue dont le matricule est fourni

		// TODO retourner une exception `CollegueNonTrouveException` (à créer)
		// si le matricule ne correspond à aucun collègue

		Collegue collegue = collegueRepository.findByMatricule(matriculeRecherche);

		if (collegue == null) {
			throw new CollegueNonTrouveException("Collegue non trouvé");
		}

		return collegue;
	}

	public Collegue ajouterUnCollegue(Collegue collegueAAjouter) {

		collValidator.validerCollegue(collegueAAjouter);
		collegueAAjouter.setMatricule(UUID.randomUUID().toString());
		collegueRepository.save(collegueAAjouter);

		return collegueAAjouter;
	}

	@Transactional
	public Collegue modifierEmail(String matricule, String email) {

		Collegue collegue = collegueRepository.findByMatricule(matricule);

		if (collegue == null) {
			throw new CollegueNonTrouveException("Collegue non trouvé");
		}

		if (collegue.getEmail().length() < 3 || !collegue.getEmail().contains("@")) {
			throw new CollegueInvalideException("L'adresse mail doit comporter au moins 3 caractères et un '@'");
		}

		collegue.setEmail(email);

		return collegue;

		// TODO retourner une exception `CollegueNonTrouveException`
		// si le matricule ne correspond à aucun collègue

		// TODO Vérifier que l'email a au moins 3 caractères et contient `@`
		// TODO Si la règle ci-dessus n'est pas valide, générer une exception :
		// `CollegueInvalideException`. avec un message approprié.

		// TODO Modifier le collègue
	}

	public Collegue modifierPhotoUrl(String matricule, String photoUrl) {

		// Collegue collegue = data.get(matricule);
		//
		// if (collegue == null) {
		// throw new CollegueNonTrouveException("Collegue non trouvé");
		// }
		//
		// if (!collegue.getPhotoUrl().startsWith("http")) {
		// throw new CollegueInvalideException("L'url doit commencer par
		// 'http'");
		// }
		//
		// collegue.setPhotoUrl(photoUrl);
		//
		// return collegue;
		return null;

		// TODO retourner une exception `CollegueNonTrouveException`
		// si le matricule ne correspond à aucun collègue

		// TODO Vérifier que la photoUrl commence bien par `http`
		// TODO Si la règle ci-dessus n'est pas valide, générer une exception :
		// `CollegueInvalideException`. avec un message approprié.

		// TODO Modifier le collègue
	}

}
