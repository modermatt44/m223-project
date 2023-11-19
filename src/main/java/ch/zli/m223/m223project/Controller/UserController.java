package ch.zli.m223.m223project.Controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ch.zli.m223.m223project.Model.ApplicationUser;
import ch.zli.m223.m223project.Repository.UserRepository;

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
        return user;
    }

    @PostMapping("/register")
    public String saveUser(@ModelAttribute("user") ApplicationUser user) {

        userRepo.save(new ApplicationUser(user.getUsername(), passwordEncoder.encode(user.getPassword()),
                user.getEmail(), user.getRoles()));

        return "redirect:/login";
    }
}
