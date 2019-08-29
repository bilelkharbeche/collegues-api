package com.exemple.service;

import java.time.LocalDate;

import org.junit.Test;

import dev.exemple.Collegue;
import dev.exemple.exception.CollegueInvalideException;
import dev.exemple.service.CollegueService;

public class ColleguesApiApplicationTests {

	CollegueService collServ = new CollegueService();

	@Test(expected = CollegueInvalideException.class)
	public void NameTest() {
		Collegue collegue = new Collegue("k", "Bilel", "ell71@hotmail.fr", LocalDate.of(1997, 05, 06),
				"http://www.urlDeMalade");
		collServ.ajouterUnCollegue(collegue);
		System.out.println(collegue);
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
		Collegue collegue = new Collegue("kharbeche", "Bilel", "e@", LocalDate.of(1997, 05, 06),
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

}
