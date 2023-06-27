package com.revature.security;

import com.revature.daos.UserDAO;
import com.revature.models.Role;
import com.revature.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CustomerUserDetailsService implements UserDetailsService {

    private final UserDAO userDAO;

    @Autowired
    public CustomerUserDetailsService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User a = userDAO.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("No User Found"));

        return (UserDetails) new User(a.getUsername(), a.getPassword(), mapRoleToAuthority(a.getRole())); //This may be problem...remove userDetails if problem...

    }

    private Collection<GrantedAuthority> mapRoleToAuthority(Role role) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleTitle()));

        return grantedAuthorities;

    }
}

