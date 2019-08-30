package dev.exemple.service;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import dev.exemple.Collegue;
import dev.exemple.repository.CollegueRepository;

@Component
public class StartupDataInit {

	@Autowired
	CollegueRepository collegueRepository;

	@EventListener(ContextRefreshedEvent.class)
	public void init() {

		collegueRepository.save(new Collegue(UUID.randomUUID().toString(), "KHARBECHE", "Bilel", "test@hotmail.fr",
				LocalDate.of(1997, 05, 06), "URLDEMALADE"));
		collegueRepository.save(new Collegue(UUID.randomUUID().toString(), "KHARBECHE2", "Bilel2", "test@hotmail.fr2",
				LocalDate.of(1997, 05, 06), "URLDEMALADE2"));
		collegueRepository.save(new Collegue(UUID.randomUUID().toString(), "KHARBECHE3", "Bilel3", "test@hotmail.fr3",
				LocalDate.of(1997, 05, 06), "URLDEMALADE3"));
		collegueRepository.save(new Collegue(UUID.randomUUID().toString(), "KHARBECHE4", "Bilel4", "test@hotmail.fr4",
				LocalDate.of(1997, 05, 06), "URLDEMALADE4"));
	}
}
