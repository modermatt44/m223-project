package ch.zli.m223.m223project.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping
    public String helloWorld() {
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        return "Hello World!";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/user")
    public String helloUser() {
        return "Hello User!";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin")
    public String helloAdmin() {
        return "Hello Admin!";
    }

}
