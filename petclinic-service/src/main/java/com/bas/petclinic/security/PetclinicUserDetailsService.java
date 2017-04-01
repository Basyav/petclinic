package com.bas.petclinic.security;

import com.bas.petclinic.dto.UserDTO;
import com.bas.petclinic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.stream.Collectors.toList;

/**
 *
 */
@Service
public class PetclinicUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO user = userService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User with name [" + username + "] not found");
        }
        return new PetclinicUser(user, user.getRoleTypes().stream().map(x -> new SimpleGrantedAuthority("ROLE_" + x.name())).collect(toList()));
    }
}
