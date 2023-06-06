package com.tibame.timetotravel.common;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/UserLogout")
public class Logout {

    @PostMapping("/logout")
    public ResponseEntity<String> UserLogout(HttpSession session) {
        session.setAttribute("user",null);
        return ResponseEntity.ok("已成功登出");
    }
}
