package dev.exemple.service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import dev.exemple.Collegue;
import dev.exemple.repository.CollegueRepository;

@Component
public class StartupDataInit {

	@Autowired
	private CollegueRepository collegueRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@EventListener(ContextRefreshedEvent.class)
	public void init() {

		collegueRepository.save(new Collegue(UUID.randomUUID().toString(), "KHARBECHE", "Bilel", "test@hotmail.fr",
				LocalDate.of(1997, 05, 06), "https://www.bing.com/th?id=OIP.4bw-bn6TArZf54StdtBlSwHaFj&pid=Api&rs=1",
				passwordEncoder.encode("pass1"), Arrays.asList("ROLE_ADMIN", "ROLE_USER")));
		collegueRepository.save(new Collegue(UUID.randomUUID().toString(), "KHARBECHE2", "Bilel2", "test@hotmail.fr2",
				LocalDate.of(1997, 05, 06),
				"https://i.pinimg.com/originals/44/9c/17/449c175588d56442fd162990ac428759.gif",
				passwordEncoder.encode("pass2"), Arrays.asList("ROLE_USER")));
		collegueRepository.save(new Collegue(UUID.randomUUID().toString(), "KHARBECHE3", "Bilel3", "test@hotmail.fr3",
				LocalDate.of(1997, 05, 06), "URLDEMALADE3", passwordEncoder.encode("pass3"),
				Arrays.asList("ROLE_USER")));
		collegueRepository.save(new Collegue(UUID.randomUUID().toString(), "KHARBECHE4", "Bilel4", "test@hotmail.fr4",
				LocalDate.of(1997, 05, 06), "URLDEMALADE4", passwordEncoder.encode("pass4"),
				Arrays.asList("ROLE_USER")));
	}
}
