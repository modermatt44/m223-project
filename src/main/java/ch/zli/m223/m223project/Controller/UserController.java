package ch.zli.m223.m223project.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.zli.m223.m223project.Model.ApplicationUser;
import ch.zli.m223.m223project.Repository.UserRepository;
import ch.zli.m223.m223project.Service.UserService;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepo;

    ApplicationUser user = new ApplicationUser();

    @GetMapping("/new")
    public String showNewUserForm(@ModelAttribute("user") ApplicationUser user) {
        return "newUsers";
    }

    @PostMapping("/new")
    public String saveUser(@ModelAttribute("user") ApplicationUser user) {

        userRepo.save(user);

        return "redirect:/new";
    }

    // @GetMapping("/users")
    // public String listAll(Model model) {
    //     List<ApplicationUser> listUsers = userRepo.findAll();
    //     model.addAttribute("listUsers", listUsers);

    //     return "users";
    // }
}
