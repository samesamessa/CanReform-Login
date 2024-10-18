package org.iclass.board.service;

import org.iclass.board.dto.UserDTO;
import org.iclass.board.dto.UserDetailsDTO;
import org.iclass.board.entity.LoginEntity;
import org.iclass.board.repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor

public class CustomUserDetailsService implements UserDetailsService {

    public final LoginRepository loginRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("Response Email : {}", email);
        LoginEntity entity = loginRepository.findByEmail(email);
        log.info("Response Entity : {}", entity);
        if(entity == null){
            throw new UsernameNotFoundException(email);
        }

        UserDTO dto = UserDTO.toDTO(entity);
        log.info(dto.toString());

        UserDetails userDetails = UserDetailsDTO.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .authorities(new SimpleGrantedAuthority(dto.getRole()))
                .build();
        log.info("CustomUserDetailsService.userDetails : {}", userDetails);
        return userDetails;
    }
}
