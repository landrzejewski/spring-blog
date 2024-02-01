package pl.training.blog;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import pl.training.blog.application.ArticleAuthorActions;
import pl.training.blog.application.ArticleSearch;
import pl.training.blog.ports.ArticleRepository;

@Configuration
public class BlogConfiguration {

    @Bean
    public ArticleAuthorActions articleAuthorActions(ArticleRepository articleRepository) {
        return new ArticleAuthorActions(articleRepository);
    }

    @Bean
    public ArticleSearch articleSearch(ArticleRepository articleRepository) {
        return new ArticleSearch(articleRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

   /* @Bean
    public UserDetailsService userDetailsService() {
        var user = User
                .withUsername("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user);
    }*/

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(httpSecurityHttpBasicConfigurer -> {})
                .build();
    }

}
