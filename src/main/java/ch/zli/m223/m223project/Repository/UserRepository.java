package ch.zli.m223.m223project.Repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import ch.zli.m223.m223project.Model.ApplicationUser;

public interface UserRepository extends CrudRepository<ApplicationUser, Long> {
    Optional<ApplicationUser> findByusername(String username);
}
