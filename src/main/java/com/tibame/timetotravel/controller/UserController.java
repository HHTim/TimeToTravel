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

import java.util.List;
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
    public ResponseEntity insertRegisterUser(@RequestBody RegisterUserDto dto){
        Set<ConstraintViolation<RegisterUserDto>> validateSet = validator.validate(dto);
        if (!validateSet.isEmpty()) {
            return badRequest(validateSet);
        }

        try {
            userService.insertRegisterUser(dto);
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
            User user = userService.findByUserId(id);
            request.getSession().setAttribute("user_id", id);
//            System.out.println("ssss:"+new String(user.getUserAvatar()));
            request.getSession().setAttribute("user", new UserSessionDto(user, null, null, "會員",new String(user.getUserAvatar())));
//            System.out.println("UserSession: " + request.getSession().getAttribute("user"));
        } catch (Exception e) {
            return badRequest(e.getMessage());
        }

        return ResponseEntity.ok("{}");
    }

    @PatchMapping(value = "/user/status", consumes = "multipart/form-data")
    public String updateUserStatus(@RequestParam("account") String account,
                                   @RequestParam("status") String status){
        System.out.println("接收到的User帳號為:"+account);
        System.out.println("接收到的User status為:"+status);

        return userService.updateUserStatusByAccount(account, ("true".equals(status) ? 1 : 0));
    }

    @PatchMapping(value = "/user/newsStatus", consumes = "multipart/form-data")
    public String updateUserNewsStatus(@RequestParam("account") String account,
                                   @RequestParam("newsStatus") Integer newsStatus){
        System.out.println("接收到的User帳號為:"+account);
        System.out.println("接收到的User newsStatus為:"+newsStatus);

        return userService.updateUserNewsStatusByAccount(account, newsStatus);
    }

    @GetMapping(value = "/user/{userId}")
    public User getUserById(@PathVariable("userId") Integer userId){
        System.out.println("接收到的userId為:"+userId);
        return userService.findByUserId(userId);
    }

    @PatchMapping(value = "/user/password", consumes = "multipart/form-data")
    public String updateUserPassword(@RequestParam("userId") Integer userId,
                                   @RequestParam("password") String password){
        System.out.println("接收到的User Id為:"+userId);
        System.out.println("接收到的User password為:"+password);

        return userService.updateByPassword(password, userId);
    }

    @GetMapping("/user/page/{currPage}/{rows}")
    public PageBean<User> readByPage(@PathVariable Integer currPage, @PathVariable Integer rows) {
        System.out.println("分頁搜尋");
        return userService.findByPageRowData(currPage, rows);
    }

    @GetMapping("/user/page/status/{status}/{currPage}/{rows}")
    public PageBean<User> readStatusByPage(@PathVariable Integer status, @PathVariable Integer currPage, @PathVariable Integer rows){
        System.out.println("狀態搜尋");
        return userService.findStatusByPageRowData(status, currPage, rows);
    }

    @GetMapping("/user/page/{currPage}/{rows}/keyword/{keyword}")
    public PageBean<User> readByKeyWords(@PathVariable Integer currPage, @PathVariable Integer rows, @PathVariable String keyword){
        System.out.println("關鍵字搜尋");
        return userService.findKeywordByPageRowData(keyword, currPage, rows);
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
            ((UserSessionDto) request.getSession().getAttribute("user")).setAvatar(dto.getAvatar());
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

    @GetMapping("/user/page/dateRange/{currPage}/{rows}/{startDate}/{endDate}")
    public PageBean<User> readViewByDateRange(@PathVariable Integer currPage, @PathVariable Integer rows , @PathVariable String startDate, @PathVariable String endDate){
        System.out.println("日期分頁搜尋range: "+ startDate + " ~ " + endDate);
        return userService.findBeanPageByDateRange(startDate, endDate , currPage, rows);
    }

    @GetMapping("/all")
    public List<User> findAll() {
        return userService.findAll();
    }
}
