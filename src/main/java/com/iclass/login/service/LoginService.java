package com.iclass.login.service;

import com.iclass.login.dto.LoginDTO;
import com.iclass.login.entity.LoginEntity;
import com.iclass.login.repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.login.LoginException;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional

public class LoginService {

    private final LoginRepository loginRepository;

    public LoginDTO signup(LoginDTO dto) {
        LoginEntity entity = dto.toEntity();
        loginRepository.save(entity);

        return LoginDTO.toDTO(entity);
    }

    public LoginDTO login(LoginDTO dto) throws LoginException {
        LoginEntity entity = loginRepository.findByEmail(dto.getEmail());
        if (entity == null || !entity.getPassword().equals(dto.getPassword())) {
            throw new LoginException("회원 정보가 잘못되었습니다.");
        }
        return LoginDTO.toDTO(entity);
    }


}
