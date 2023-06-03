package com.tibame.timetotravel.controller;

import com.google.code.kaptcha.Constants;
import com.tibame.timetotravel.common.PageBean;
import com.tibame.timetotravel.dto.*;
import com.tibame.timetotravel.entity.User;
import com.tibame.timetotravel.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@RestController
@RequestMapping("/UserController")
public class UserController extends BaseController {

    @Autowired
    Validator validator;

    @Autowired
    @Qualifier("UserService")
    UserService userService;

    @PostMapping("/user/register")
    public ResponseEntity insert(@RequestBody RegisterUserDto dto){
        Set<ConstraintViolation<RegisterUserDto>> validateSet = validator.validate(dto);
        if (!validateSet.isEmpty()) {
            return badRequest(validateSet);
        }

        try {
            userService.insert(dto);
        } catch (Exception e) {
            return badRequest(e.getMessage());
        }

        return ResponseEntity.ok("{}");
    }

    @PostMapping("/user/login")
    public ResponseEntity login(@RequestBody LoginUserDto dto, HttpServletRequest request){
        Set<ConstraintViolation<LoginUserDto>> validateSet = validator.validate(dto);
        if (!validateSet.isEmpty()) {
            return badRequest(validateSet);
        }

        if (!dto.getCaptcha().toLowerCase().equals(request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY))) {
            return badRequest("驗證碼錯誤");
        }

        try {
            int id = userService.login(dto);
            request.getSession().setAttribute("user_id", id);
        } catch (Exception e) {
            return badRequest(e.getMessage());
        }

        return ResponseEntity.ok("{}");
    }

    @GetMapping("/user")
    public ResponseEntity detail(HttpServletRequest request){
        if (request.getSession().getAttribute("user_id") == null) {
            return unauthorized("尚未登入");
        }

        try {
            int id = (int) request.getSession().getAttribute("user_id");
            UserDetailResponseDto dto = userService.get(id);

            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            return badRequest(e.getMessage());
        }
    }

    @PutMapping("/user")
    public ResponseEntity modify(@RequestBody ModifyUserDto dto, HttpServletRequest request){
        if (request.getSession().getAttribute("user_id") == null) {
            return unauthorized("尚未登入");
        }

        Set<ConstraintViolation<ModifyUserDto>> validateSet = validator.validate(dto);
        if (!validateSet.isEmpty()) {
            return badRequest(validateSet);
        }

        try {
            int id = (int) request.getSession().getAttribute("user_id");
            userService.modify(id, dto);
        } catch (Exception e) {
            return badRequest(e.getMessage());
        }

        return ResponseEntity.ok("{}");
    }

    @PutMapping("/user/password")
    public ResponseEntity modifyPassword(@RequestBody ModifyUserPasswordDto dto, HttpServletRequest request){
        if (request.getSession().getAttribute("user_id") == null) {
            return unauthorized("尚未登入");
        }

        Set<ConstraintViolation<ModifyUserPasswordDto>> validateSet = validator.validate(dto);
        if (!validateSet.isEmpty()) {
            return badRequest(validateSet);
        }

        try {
            int id = (int) request.getSession().getAttribute("user_id");
            userService.modify(id, dto);
        } catch (Exception e) {
            return badRequest(e.getMessage());
        }

        return ResponseEntity.ok("{}");
    }

}
