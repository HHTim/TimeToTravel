package com.tibame.timetotravel.common;

import com.tibame.timetotravel.dto.UserSessionDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;

@RestController
@RequestMapping("/getCurrentUserController")
public class getCurrentUserController {
    @GetMapping("/current-user")
    public ResponseEntity<UserSessionDto> getCurrentUser(HttpSession session) {
        UserSessionDto user = (UserSessionDto) session.getAttribute("user");

        if (user != null) {
            // 根據使用者角色進行不同的處理
            if ("會員".equals(user.getRole())) {
                // 平台使用者
                System.out.println("當前登入者為會員");
            } else if ("商家".equals(user.getRole())) {
                // 商家使用者
                System.out.println("當前登入者為商家");
            } else if ("平台".equals(user.getRole())) {
                // 管理者使用者
                System.out.println("當前登入者為平台");
            }

            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
}
