package ch.zli.m223.m223project.Controller;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping
    public String helloWorld(JwtAuthenticationToken principal) {
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());

        Collection<String> roles = principal.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return "Hello:" + roles;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/user")
    public String helloUser(Authentication authentication) {
        System.out.println(authentication.getAuthorities());
        return "Hello User!";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin")
    public String helloAdmin() {
        return "Hello Admin!";
    }

}
