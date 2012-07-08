package net.sam_solutions.service;

import net.sam_solutions.model.UsersEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.openid.OpenIDAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
