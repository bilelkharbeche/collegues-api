package dev.exemple.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.exemple.Collegue;

public interface CollegueRepository extends JpaRepository<Collegue, Integer> {

	List<Collegue> findByNom(String nom);

	Collegue findByMatricule(String matricule);

	Optional<Collegue> findByEmail(String email);

}
