package ch.zli.m223.m223project.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.zli.m223.m223project.Model.ApplicationUser;
import ch.zli.m223.m223project.Repository.UserRepository;
import jakarta.persistence.EntityManager;

@Controller
public class UserController {

    private final UserRepository userRepo;

    public UserController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); // TODO: Change implementation

    // When forwarded show the created user
    @GetMapping("/register")
    public String findAll(@ModelAttribute("user") ApplicationUser user) {
        return "register";
    }

    // TODO: Fix this endpoint
    @GetMapping("/register/{id}")
    public ApplicationUser findById(@PathVariable("id") ApplicationUser user) {
        return null;
    }

    @PostMapping("/register")
    public String saveUser(@ModelAttribute("user") ApplicationUser user) {

        userRepo.save(
                new ApplicationUser(user.getPrename(), user.getSurname(), passwordEncoder.encode(user.getPassword()),
                        user.getUsername(), user.getRoles()));

        return "redirect:/register";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin/users")
    public List<ApplicationUser> manageUsers(@ModelAttribute("user") ApplicationUser user) {
        return null;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin/users/{id}")
    public Optional<ApplicationUser> findUserById(@PathVariable("id") Long id){
        return null;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/admin/users/{id}")
    public String deleteUserbyId(@PathVariable("id") Long id) {
        userRepo.deleteById(id);
        return "redirect:/users";
    }
}
