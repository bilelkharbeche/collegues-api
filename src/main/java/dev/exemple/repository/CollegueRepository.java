package dev.exemple.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dev.exemple.Collegue;
import dev.exemple.controller.DTO.CollegueDtoPhoto;

public interface CollegueRepository extends JpaRepository<Collegue, Integer> {

	List<Collegue> findByNom(String nom);

	Collegue findByMatricule(String matricule);

	Optional<Collegue> findByEmail(String email);

	@Query("select new dev.exemple.controller.DTO.CollegueDtoPhoto (coll.matricule, coll.photoUrl) FROM Collegue coll")
	List<CollegueDtoPhoto> findByMatAndPhoto();

}
