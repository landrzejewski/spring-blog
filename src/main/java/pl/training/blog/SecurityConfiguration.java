package pl.training.blog;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pl.training.blog.security.ApiKeyAuthenticationFilter;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, ApiKeyAuthenticationFilter apiKeyAuthenticationFilter) throws Exception {
        return httpSecurity
                .addFilterBefore(apiKeyAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(withDefaults())
                .authorizeHttpRequests(config -> config
                        .requestMatchers(GET, "/articles/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(GET, "/categories/**").hasAnyRole("USER", "ADMIN")
                        .anyRequest().hasRole("ADMIN")
                )
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsManager userDetailsManager() {
        var admin = User.withUsername("jan")
                .password(passwordEncoder().encode("123"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(admin);
    }

}
