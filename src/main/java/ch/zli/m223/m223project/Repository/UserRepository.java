package ch.zli.m223.m223project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.zli.m223.m223project.Model.ApplicationUser;

public interface UserRepository extends JpaRepository<ApplicationUser, Long> {
    
}
