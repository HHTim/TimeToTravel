package com.tibame.timetotravel.controller;

import com.google.code.kaptcha.Constants;
import com.tibame.timetotravel.dto.LoginUserDto;
import com.tibame.timetotravel.dto.UserSessionDto;
import com.tibame.timetotravel.entity.Admin;
import com.tibame.timetotravel.service.AdminService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/AdminController")
public class AdminController extends BaseController{

    @Autowired
    Validator validator;

    @Autowired
    @Qualifier("AdminService")
    AdminService adminService;

    @PostMapping("/admin/login")
    public ResponseEntity login(@RequestBody LoginUserDto dto, HttpServletRequest request){
        Set<ConstraintViolation<LoginUserDto>> validateSet = validator.validate(dto);
        if (!validateSet.isEmpty()) {
            return badRequest(validateSet);
        }

        if (!dto.getCaptcha().toLowerCase().equals(request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY))) {
            return badRequest("驗證碼錯誤");
        }

        try {
            int id = adminService.login(dto);
            Admin admin = adminService.findByAdminId(id);
//            request.getSession().setAttribute("user_id", id);
            request.getSession().setAttribute("user", new UserSessionDto(null, null, admin, "平台"));
            System.out.println("UserSession: " + request.getSession().getAttribute("user"));
        } catch (Exception e) {
            return badRequest(e.getMessage());
        }

        return ResponseEntity.ok("{}");
    }
}
