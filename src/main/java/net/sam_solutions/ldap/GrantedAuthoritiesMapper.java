package net.sam_solutions.ldap;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

/**
 * User: Sergei Savenko
 * Date: 10.07.12
 */
@Component
public class GrantedAuthoritiesMapper implements org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper{
    @Override
    public Collection<? extends GrantedAuthority> mapAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Collection<GrantedAuthority> mappedAuthorities = new ArrayList<GrantedAuthority>();
        for (GrantedAuthority granted : authorities) {
            if ("PEOPLE".equalsIgnoreCase(granted.getAuthority())) {
                mappedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            } else if ("ADMIN".equalsIgnoreCase(granted.getAuthority())) {
                mappedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            }  else {
                mappedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            }
        }
        return mappedAuthorities;
    }
}
