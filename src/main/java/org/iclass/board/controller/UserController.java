package org.iclass.board.controller;

import lombok.RequiredArgsConstructor;
import org.iclass.board.dto.UserDTO;
import org.iclass.board.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")

public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserDTO dto){
        UserDTO result = userService.signup(dto);
        return ResponseEntity.ok(result);
    }

/*    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // 로그아웃 시 세션에서 사용자 정보 제거
        session.invalidate();
        return "redirect:/login"; // 로그아웃 후 로그인 페이지로 리다이렉트
    }*/
}
