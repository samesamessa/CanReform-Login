package com.iclass.login.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
@RequiredArgsConstructor

public class HomeController {
    @GetMapping({"/", "/index"})
    public String index(Model model, HttpServletRequest request, Authentication authentication) {

        // 세션 ID 확인
        HttpSession session = request.getSession();
        log.info("로그인 후 세션 ID: {}", session.getId());

        log.info("사용자 인증 : {}", authentication);
        if(authentication != null && authentication.getPrincipal() instanceof UserDetails){
            model.addAttribute("user", authentication.getName());
        } else {
            log.info("사용자 인증 정보 없음");
        }
        return "index";
    }

    @GetMapping("/login")
    public String login(String error, Model model){
        if(error != null && error.isEmpty()) {
            model.addAttribute("error", "계정 정보가 일치하지 않습니다.");
            log.info("error : {}", error);
        }
        return "login";
    }

    @GetMapping("/signup")
    public String signup(){
        return "signup";
    }

    @GetMapping("/bulletin-board")
    public String bulletinBoard(){
        return "bulletin-board";
    }

}
