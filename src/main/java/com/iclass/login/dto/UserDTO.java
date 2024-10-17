package com.iclass.login.dto;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@Setter
@ToString

public class UserDTO extends User {

    public UserDTO(String username, String password, Collection<? extends GrantedAuthority> authorities){
        super(username, password, authorities);
    }
}
