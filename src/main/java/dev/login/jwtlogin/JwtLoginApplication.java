package dev.login.jwtlogin;

import dev.login.jwtlogin.service.CommandLineService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;


@SpringBootApplication
public class JwtLoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtLoginApplication.class, args);
	}

	@Bean
	@Transactional
	CommandLineRunner runner(
			CommandLineService commandLineService
	) {
		return args -> {
			commandLineService.initializeData();
		};
	}
}
