package com.bas.petclinic.security;

import com.bas.petclinic.dto.UserDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 *
 */
public class PetclinicUser extends User {

    private UserDTO userFromDb;

    public PetclinicUser(UserDTO user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getUsername(), user.getPassword(), true, true, true, true, authorities);
        this.userFromDb = user;
    }

    public UserDTO getCurrentUser() {
        return userFromDb;
    }
}
