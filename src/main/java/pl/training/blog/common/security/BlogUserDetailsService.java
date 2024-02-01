package pl.training.blog.common.security;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class BlogUserDetailsService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    private BlogUser blogUser;

    @PostConstruct
    public void init() {
        blogUser = BlogUser.builder()
                .login("admin")
                .password(passwordEncoder.encode("admin"))
                .email("admin@training.pl")
                .roles(Set.of("ADMIN"))
                .build();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!username.equals(blogUser.getUsername())) {
            throw new UsernameNotFoundException("User not found");
        }
        return blogUser;
    }

}
