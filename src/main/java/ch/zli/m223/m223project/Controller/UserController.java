package ch.zli.m223.m223project.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.zli.m223.m223project.Model.ApplicationUser;

@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping
    public List<ApplicationUser> getAllUsers() {

        ArrayList<ApplicationUser> users = new ArrayList<ApplicationUser>();
        users.add(new ApplicationUser(1L, "test", "test", "email", "user"));
        users.add(new ApplicationUser(2L, "test2", "test2", "email2", "user"));

        return users;
    }
}
