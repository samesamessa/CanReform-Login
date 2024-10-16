package com.iclass.login.service;

import com.iclass.login.dto.LoginDTO;
import com.iclass.login.dto.UserDTO;
import com.iclass.login.entity.LoginEntity;
import com.iclass.login.repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor

public class CustomUserDetailsService implements UserDetailsService {

//    public final PasswordEncoder passwordEncoder;
    public final LoginService loginService;
    public final LoginRepository loginRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoginEntity entity = loginRepository.findByEmail(username);
        LoginDTO dto = LoginDTO.toDTO(entity);
        if(dto == null){
            throw new UsernameNotFoundException(username);
        }
        log.info(dto.toString());
        UserDetails userDetails = UserDTO.builder()
                .username(dto.getEmail())
                .password(dto.getPassword())
                .authorities(new SimpleGrantedAuthority(dto.getRole()))
                .build();
        log.info("CustomUserDetailsService.userDetails : {}", userDetails);
        return userDetails;
    }
}
