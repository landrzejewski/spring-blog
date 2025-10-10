package pl.training.blog.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class InMemoryGrantedAuthoritiesConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    private static final String USER_NAME_KEY = "preferred_username";
    private static final GrantedAuthority DEFAULT_AUTHORITY = new SimpleGrantedAuthority("ROLE_USER");

    private final Map<String, Collection<GrantedAuthority>> mappings = Map.of(
            "user", List.of(new SimpleGrantedAuthority("ROLE_ADMIN"))
    );

    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        var userName = jwt.getClaimAsString(USER_NAME_KEY);
        return mappings.getOrDefault(userName, List.of(DEFAULT_AUTHORITY));
    }

}
