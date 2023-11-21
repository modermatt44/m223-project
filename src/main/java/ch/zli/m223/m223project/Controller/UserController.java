package ch.zli.m223.m223project.Controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ch.zli.m223.m223project.Model.ApplicationUser;
import ch.zli.m223.m223project.Repository.UserRepository;
import ch.zli.m223.m223project.Service.TokenService;

@RestController
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    private final UserRepository userRepo;

    private static TokenService tokenService;

    public UserController(UserRepository userRepo, TokenService tokenService) {
        this.userRepo = userRepo;
        UserController.tokenService = tokenService;
    }

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping(value = "/register", consumes = "application/json", produces = "application/json", name = "createUser")
    public ApplicationUser createUser(@RequestBody ApplicationUser user) {
        userRepo.save(
                new ApplicationUser(user.getPrename(), user.getSurname(), passwordEncoder.encode(user.getPassword()),
                        user.getUsername(), user.getRoles()));

        return user;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/admin/users", name = "getAllUsers")
    public List<ApplicationUser> manageUsers() {
        return userRepo.findAll();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/admin/users/{id}", name = "getUserById")
    public Optional<ApplicationUser> findUserById(@PathVariable("id") Long id) {
        return userRepo.findById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/admin/users/{id}", name = "deleteUserById")
    public String deleteUserbyId(@PathVariable("id") Long id) {
        userRepo.deleteById(id);
        return "Deleted user with id: " + id;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(value = "/admin/users/{id}", consumes = "application/json", produces = "application/json", name = "updateUserById")
    public String updateUserById(@PathVariable("id") Long id, @RequestBody ApplicationUser user) {
        Optional<ApplicationUser> userOptional = userRepo.findById(id);

        if (userOptional.isPresent()) {
            ApplicationUser userToUpdate = userOptional.get();
            userToUpdate.setPrename(user.getPrename());
            userToUpdate.setSurname(user.getSurname());
            userToUpdate.setUsername(user.getUsername());
            userToUpdate.setPassword(passwordEncoder.encode(user.getPassword()));
            userToUpdate.setRoles(user.getRoles());
            userRepo.save(userToUpdate);
            return "Updated user with id: " + id;
        } else {
            return "User not found";
        }
    }

    @PostMapping(value = "/token", name = "getTokenForUser")
    public String token(Authentication authentication) {
        LOG.debug("Token requested for user {}", authentication.getName());
        String token = tokenService.generateToken(authentication);
        LOG.debug("Token generated: {}", token);
        return token;
    }
}
