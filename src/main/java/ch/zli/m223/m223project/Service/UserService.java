package ch.zli.m223.m223project.Service;

import org.springframework.stereotype.Service;

import ch.zli.m223.m223project.Model.ApplicationUser;
import ch.zli.m223.m223project.Repository.UserRepository;

@Service("userService")
public class UserService {

    private UserRepository userRepo;

    public void addTestUser() {
        ApplicationUser user = new ApplicationUser();
        user.setUsername("test");
        user.setPassword("test");
        user.setEmail("email");
        user.setRole("user");
    }
}
