package ch.zli.m223.m223project.Repository;

import java.util.Optional;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ch.zli.m223.m223project.Model.ApplicationUser;

@Repository
public interface UserRepository extends CrudRepository<ApplicationUser, Long> {
    Optional<ApplicationUser> findByUsername(String username);

    Optional<ApplicationUser> findById(Long id);

    List<ApplicationUser> findAll();
}
