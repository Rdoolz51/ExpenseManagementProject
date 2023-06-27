package com.revature.security;

import com.revature.daos.PersonDAO;
import com.revature.models.Person;
import com.revature.models.Role;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final PersonDAO personDAO;

    @Autowired
    public CustomUserDetailsService(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person a = personDAO.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("No Person Found"));

        return new User(a.getUsername(), a.getPassword(), mapRoleToAuthority(a.getRole()));

    }

    private Collection<GrantedAuthority> mapRoleToAuthority(Role role) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleTitle()));

        return grantedAuthorities;

    }

    public UserDetails loadUserByID(Authentication auth, int id) throws UsernameNotFoundException {
        int authenticatedUserId = ((Person) auth.getPrincipal()).getId();
        if (authenticatedUserId == id){
            Person p = personDAO.findById(id).orElseThrow(() -> new UsernameNotFoundException("no User found with Id" + id));

            return new User(p.getUsername(), p.getPassword(), mapRoleToAuthority(p.getRole()));
        } else {
            throw new UsernameNotFoundException("Access denied");
        }
    }
}

