package dev.exemple.service;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import dev.exemple.Collegue;
import dev.exemple.exception.CollegueInvalideException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ColleguesApiApplicationTests {

	@Autowired
	CollegueService collServ;

	@Test(expected = CollegueInvalideException.class)
	public void NameTest() {
		Collegue collegue = new Collegue("k", "Bilel", "ell71@hotmail.fr", LocalDate.of(1997, 05, 06),
				"http://www.urlDeMalade");
		collServ.ajouterUnCollegue(collegue);
	}

	@Test(expected = CollegueInvalideException.class)
	public void SurnameTest() {
		Collegue collegue = new Collegue("kharbeche", "B", "ell71@hotmail.fr", LocalDate.of(1997, 05, 06),
				"http://www.urlDeMalade");
		collServ.ajouterUnCollegue(collegue);
	}

	@Test(expected = CollegueInvalideException.class)
	public void EmailWithoutArrobase() {
		Collegue collegue = new Collegue("kharbeche", "Bilel", "ell71hotmail.fr", LocalDate.of(1997, 05, 06),
				"http://www.urlDeMalade");
		collServ.ajouterUnCollegue(collegue);
	}

	@Test(expected = CollegueInvalideException.class)
	public void EmailCourt() {
		Collegue collegue = new Collegue("kharbeche", "Bilel", "e", LocalDate.of(1997, 05, 06),
				"http://www.urlDeMalade");
		collServ.ajouterUnCollegue(collegue);
	}

	@Test(expected = CollegueInvalideException.class)
	public void UrlHttp() {
		Collegue collegue = new Collegue("kharbeche", "Bilel", "ell71@hotmail.fr", LocalDate.of(1997, 05, 06),
				"htt://www.urlDeMalade");
		collServ.ajouterUnCollegue(collegue);
	}

	@Test(expected = CollegueInvalideException.class)
	public void Year18() {
		Collegue collegue = new Collegue("kharbeche", "Bilel", "ell71@hotmail.fr", LocalDate.of(2005, 05, 06),
				"http://www.urlDeMalade");
		collServ.ajouterUnCollegue(collegue);
	}

	@Test(expected = CollegueInvalideException.class)
	public void ModfiMailArrobase() {
		Collegue collegue = new Collegue("blabla", "kharbeche", "Bilel", "elll", LocalDate.of(1997, 05, 06),
				"http://www.urlDeMalade");

		collServ.ajouterUnCollegue(collegue);
		collServ.modifierEmail(collegue.getMatricule(), collegue.getEmail());
	}

	@Test(expected = CollegueInvalideException.class)
	public void ModfiMailTropCourt() {
		Collegue collegue = new Collegue("blabla", "kharbeche", "Bilel", "e@", LocalDate.of(1997, 05, 06),
				"http://www.urlDeMalade");

		collServ.ajouterUnCollegue(collegue);
		collServ.modifierEmail(collegue.getMatricule(), collegue.getEmail());
	}

}
