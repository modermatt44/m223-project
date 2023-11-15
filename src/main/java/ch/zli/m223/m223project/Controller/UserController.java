package ch.zli.m223.m223project.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.zli.m223.m223project.Model.ApplicationUser;
import ch.zli.m223.m223project.Repository.UserRepository;
import ch.zli.m223.m223project.Service.UserService;
import jakarta.inject.Inject;

@Controller
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserRepository userRepo;

    @Inject
    private UserService UserService;
       
    @GetMapping
    public String listAll(Model model) {
        UserService.addTestUser();
        List<ApplicationUser> listUsers = userRepo.findAll();
        model.addAttribute("listUsers", listUsers);
           
        return "users";
    }
}
