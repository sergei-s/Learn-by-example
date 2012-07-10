package net.sam_solutions.ldap;

import org.apache.log4j.Logger;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

/**
 * User: Sergei Savenko
 * Date: 10.07.12
 */
@Component
public class UserContextMapper implements UserDetailsContextMapper {
    @Override
    public UserDetails mapUserFromContext(DirContextOperations ctx, String username, Collection<? extends GrantedAuthority> authorities) {

        final Logger log = Logger.getLogger(this.getClass());
        log.error("----------------------------------------------------- " +
                "----------------------------------------------------- " +
                "----------------------------------------------------- " +
                "----------------------------------------------------- " +
                "----------------------------------------------------- " +
                "----------------------------------------------------- --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------" +
                "");
        String cns = ctx.getStringAttribute("ou");
        log.error(cns + "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        log.error(ctx.getDn() + "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        Collection<? extends GrantedAuthority> auths = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        for (GrantedAuthority auth : auths) {
            log.error(auth.getAuthority());
        }
        log.error("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        log.error(authorities.isEmpty());
        Collection<GrantedAuthority> mappedAuthorities = new ArrayList<GrantedAuthority>();
        for (GrantedAuthority granted : authorities) {
            if ("PEOPLE".equalsIgnoreCase(granted.getAuthority())) {
                mappedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            } else if ("ADMIN".equalsIgnoreCase(granted.getAuthority())) {
                mappedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            }
        }
        if (mappedAuthorities.isEmpty()) {
            mappedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
        return new User(username, "", true, true, true, true, mappedAuthorities);
    }

    @Override
    public void mapUserToContext(UserDetails user, DirContextAdapter ctx) {
    }
}
