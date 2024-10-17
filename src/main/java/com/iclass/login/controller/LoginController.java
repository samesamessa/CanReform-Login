package com.iclass.login.controller;

import com.iclass.login.dto.LoginDTO;
import com.iclass.login.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j

public class LoginController {

    private final LoginService loginService;


    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody LoginDTO dto){
        LoginDTO result = loginService.signup(dto);
        return ResponseEntity.ok(result);
    }

}