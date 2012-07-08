package net.sam_solutions.service;

import net.sam_solutions.dao.UserDao;
import net.sam_solutions.model.UsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

/*
 * User: Sergei Savenko
 * Date: 13.06.12
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        UsersEntity usersEntity = userDao.findByName(name);
        if (usersEntity == null) {
            throw new UsernameNotFoundException("user not found");
        }
        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority> ();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        User user = new User(usersEntity.getLogin(), usersEntity.getPassword(), true, true, true, true, authorities);
        return user;
    }
}
