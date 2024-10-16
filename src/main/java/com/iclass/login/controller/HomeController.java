package com.iclass.login.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j

public class HomeController {

    @GetMapping({"/", "/index"})
    public String index(Model model){
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        if(authentication != null && authentication.isAuthenticated()){
            log.info("Current user : {}", authentication);
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
