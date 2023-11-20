package ch.zli.m223.m223project;

import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import ch.zli.m223.m223project.Config.RsaKeyProperties;
import ch.zli.m223.m223project.Model.ApplicationUser;
import ch.zli.m223.m223project.Repository.UserRepository;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class M223ProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(M223ProjectApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(UserRepository users, PasswordEncoder passwordEncoder) {
		return args -> {
			users.save(new ApplicationUser("user", "user", passwordEncoder.encode("user"), "user@user", "ROLE_USER"));
			users.save(new ApplicationUser("admin", "admin", passwordEncoder.encode("admin"), "admin@admin",
					"ROLE_USER,ROLE_ADMIN"));
		};
	}
}
