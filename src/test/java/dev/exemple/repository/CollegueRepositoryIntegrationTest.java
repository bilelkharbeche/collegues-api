package dev.exemple.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import dev.exemple.Collegue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CollegueRepositoryIntegrationTest {

	@Autowired
	private CollegueRepository collegueRepository;

	@Test
	public void testFindByNom() {

		collegueRepository.save(new Collegue(UUID.randomUUID().toString(), "KHARBECHE", "Bilel", "ell71@hotmail.fr",
				LocalDate.of(1997, 5, 6), "urldemalade"));

		List<Collegue> liste = collegueRepository.findByNom("KHARBECHE");
		Assert.assertEquals(1, liste.size());
	}

	@Test
	public void testFindByNomNonTrouve() {

		collegueRepository.save(new Collegue(UUID.randomUUID().toString(), "k", "Bilel", "ell71@hotmail.fr",
				LocalDate.of(1997, 5, 6), "urldemalade"));

		List<Collegue> liste = collegueRepository.findByNom("KHARBECHE");
		Assert.assertEquals(0, liste.size());
	}

	@Test
	public void testFindByMatricule() {

		String mat = UUID.randomUUID().toString();
		collegueRepository.save(
				new Collegue(mat, "kHARBECHE", "Bilel", "ell71@hotmail.fr", LocalDate.of(1997, 5, 6), "urldemalade"));

		Collegue collegue = collegueRepository.findByMatricule(mat);
		Assert.assertNotNull(collegue);
		;
	}

	@Test
	public void testFindByMatriculeNonTrouve() {

		String mat = UUID.randomUUID().toString();
		collegueRepository.save(
				new Collegue(mat, "kHARBECHE", "Bilel", "ell71@hotmail.fr", LocalDate.of(1997, 5, 6), "urldemalade"));

		Collegue collegue = collegueRepository.findByMatricule(UUID.randomUUID().toString());
		Assert.assertEquals(null, collegue);
	}

}
