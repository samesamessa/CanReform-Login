package com.iclass.login.service;

import com.iclass.login.dto.LoginDTO;
import com.iclass.login.entity.LoginEntity;
import com.iclass.login.repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional

public class LoginService {

    private final LoginRepository loginRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginDTO signup(LoginDTO dto) {
        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        dto.setPassword(encodedPassword);
        dto.setRole("ROLE_USER");
        LoginEntity entity = dto.toEntity();
        loginRepository.save(entity);

        return LoginDTO.toDTO(entity);
    }
}
