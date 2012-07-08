package net.sam_solutions.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

/**
 * User: Sergei Savenko
 * Date: 04.07.12
 */
@Component
public class OpenIdUserDetailsServiceImpl implements UserDetailsService {

        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority> ();
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            return new User(username, "unused", authorities);
        }
}
