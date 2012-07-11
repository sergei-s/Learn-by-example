package net.sam_solutions.ldap;

import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.DistinguishedName;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
        DistinguishedName distinguishedName = new DistinguishedName(ctx.getDn());
        String ou = distinguishedName.getLdapRdn("ou").getValue();
        Collection<GrantedAuthority> mappedAuthorities = new ArrayList<GrantedAuthority>();
        if ("PEOPLE".equalsIgnoreCase(ou)) {
            mappedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        } else if ("ADMIN".equalsIgnoreCase(ou)) {
            mappedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else {
            mappedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
        return new User(username, "", true, true, true, true, mappedAuthorities);
    }

    @Override
    public void mapUserToContext(UserDetails user, DirContextAdapter ctx) {
    }
}
