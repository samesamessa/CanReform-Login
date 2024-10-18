package org.iclass.board.service;

import org.iclass.board.dto.UserDTO;
import org.iclass.board.entity.LoginEntity;
import org.iclass.board.repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional

public class UserService {

    private final LoginRepository loginRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDTO signup(UserDTO dto) {
        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        dto.setPassword(encodedPassword);
        dto.setRole("ROLE_USER");
        LoginEntity entity = dto.toEntity();
        loginRepository.save(entity);

        return UserDTO.toDTO(entity);
    }
}
