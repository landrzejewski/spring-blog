package pl.training.blog.common.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityRestController {

    @GetMapping("users/me")
    public BlogUser getInfo(/*Authentication authentication, Principal principal*/) {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        return (BlogUser) auth.getPrincipal();
    }

}
