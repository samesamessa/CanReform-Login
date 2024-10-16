package com.iclass.login.controller;

import com.iclass.login.dto.LoginDTO;
import com.iclass.login.entity.LoginEntity;
import com.iclass.login.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.LoginException;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j

public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO dto) {
        try {
            LoginDTO result = loginService.login(dto);
            return ResponseEntity.ok(result);
        } catch (LoginException e) {
            // 로그인 실패 시 401 상태와 오류 메시지 반환
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("::::::", e.getMessage()));
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody LoginDTO dto){
        LoginDTO result = loginService.signup(dto);
        return ResponseEntity.ok(result);
    }

}