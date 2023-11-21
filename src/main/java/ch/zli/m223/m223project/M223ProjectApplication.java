package ch.zli.m223.m223project;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import ch.zli.m223.m223project.Config.RsaKeyProperties;
import ch.zli.m223.m223project.Model.ApplicationUser;
import ch.zli.m223.m223project.Model.Booking;
import ch.zli.m223.m223project.Model.Space;
import ch.zli.m223.m223project.Repository.BookingRepository;
import ch.zli.m223.m223project.Repository.SpaceRepository;
import ch.zli.m223.m223project.Repository.UserRepository;

import java.time.LocalDateTime;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class M223ProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(M223ProjectApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(SpaceRepository spaces, UserRepository users, PasswordEncoder passwordEncoder, BookingRepository bookings) {
		return args -> {
			// Create mock users
			ApplicationUser user1 = new ApplicationUser();
			user1.setPrename("user");
			user1.setSurname("user");
			user1.setUsername("user@user");
			user1.setPassword(passwordEncoder.encode("user"));
			user1.setRoles("ROLE_USER");
			users.save(user1);

			ApplicationUser user2 = new ApplicationUser();
			user2.setPrename("admin");
			user2.setSurname("admin");
			user2.setUsername("admin@admin");
			user2.setPassword(passwordEncoder.encode("admin"));
			user2.setRoles("ROLE_USER,ROLE_ADMIN");
			users.save(user2);

			ApplicationUser user3 = new ApplicationUser();
			user3.setPrename("John");
			user3.setSurname("Doe");
			user3.setUsername("john@doe");
			user3.setPassword(passwordEncoder.encode("john"));
			user3.setRoles("ROLE_USER");
			users.save(user3);

			// Create mock spaces
			Space space1 = new Space();
			space1.setName("A1");
			spaces.save(space1);

			Space space2 = new Space();
			space2.setName("A2");
			spaces.save(space2);

			Space space3 = new Space();
			space3.setName("A3");
			spaces.save(space3);

			// Create mock bookings
			Booking booking1 = new Booking();
			booking1.setBookingStart(LocalDateTime.now());
			booking1.setBookingEnd(LocalDateTime.now().plusHours(2));
			booking1.setIsApproved(true);
			booking1.setApplicationUser(user3);
			booking1.setSpace(space3);
			bookings.save(booking1);

			Booking booking2 = new Booking();
			booking2.setBookingStart(LocalDateTime.now().plusDays(1));
			booking2.setBookingEnd(LocalDateTime.now().plusDays(1).plusHours(3));
			booking2.setIsApproved(false);
			booking2.setApplicationUser(user2);
			booking2.setSpace(space2);
			bookings.save(booking2);

			Booking booking3 = new Booking();
			booking3.setBookingStart(LocalDateTime.now().plusDays(2));
			booking3.setBookingEnd(LocalDateTime.now().plusDays(2).plusHours(1));
			booking3.setIsApproved(true);
			booking3.setApplicationUser(user1);
			booking3.setSpace(space3);
			bookings.save(booking3);
		};
	}
}

