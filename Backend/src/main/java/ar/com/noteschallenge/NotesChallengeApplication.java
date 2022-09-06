package ar.com.noteschallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class NotesChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotesChallengeApplication.class, args);
	}
}
