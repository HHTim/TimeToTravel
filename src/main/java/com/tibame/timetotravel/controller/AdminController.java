package com.tibame.timetotravel.controller;

import com.google.code.kaptcha.Constants;
import com.tibame.timetotravel.dto.LoginUserDto;
import com.tibame.timetotravel.dto.UserSessionDto;
import com.tibame.timetotravel.entity.Admin;
import com.tibame.timetotravel.service.AdminService;
import com.tibame.timetotravel.service.C2AMessageService;
import com.tibame.timetotravel.service.U2AMessageService;
import com.tibame.timetotravel.view.C2AMsgView;
import com.tibame.timetotravel.view.U2AMsgView;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/AdminController")
public class AdminController extends BaseController{

    @Autowired
    Validator validator;

    @Autowired
    @Qualifier("AdminService")
    AdminService adminService;

    @Autowired
    @Qualifier("U2AMessageService")
    U2AMessageService u2AMessageService;

    @Autowired
    @Qualifier("C2AMessageService")
    C2AMessageService c2AMessageService;

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
            request.getSession().setAttribute("user", new UserSessionDto(null, null, admin, "平台", new String(admin.getAdminAvatar())));
            System.out.println("UserSession: " + request.getSession().getAttribute("user"));
        } catch (Exception e) {
            return badRequest(e.getMessage());
        }

        return ResponseEntity.ok("{}");
    }

    @GetMapping(value = "/admin/{adminId}")
    public Admin getAdminById(@PathVariable("adminId") Integer adminId){
        System.out.println("接收到的adminId為:"+adminId);
        return adminService.findByAdminId(adminId);
    }

    @PatchMapping(value = "/adminName", consumes = "multipart/form-data")
    public String updateNewsStatus(@RequestParam("adminName") String adminName){
        System.out.println("接收到的平台名為:"+adminName);
        String act = adminService.updateNewsByAdminName(adminName);
        return "更新平台: " + act + "的消息狀態";
    }

    @PatchMapping(value = "/admin/newsStatus", consumes = "multipart/form-data")
    public String updateAdminNewsStatus(@RequestParam("account") String account,
                                       @RequestParam("newsStatus") Integer newsStatus){
        System.out.println("接收到的Admin 帳號為:"+account);
        System.out.println("接收到的Admin newsStatus為:"+newsStatus);

        return adminService.updateAdminNewsStatusByAccount(account, newsStatus);
    }

    @GetMapping("/message/u2a/view/notify/{startIndex}/{endIndex}")
    public ResponseEntity<List<U2AMsgView>> readViewByUserNotify(@PathVariable Integer startIndex,
                                                                 @PathVariable Integer endIndex){
        return ResponseEntity.ok(u2AMessageService.getNotifyMsgByUsers(startIndex, endIndex));
    }

    @GetMapping("/message/c2a/view/notify/{startIndex}/{endIndex}")
    public ResponseEntity<List<C2AMsgView>> readViewByCompanyNotify(@PathVariable Integer startIndex,
                                                                 @PathVariable Integer endIndex){
        return ResponseEntity.ok(c2AMessageService.getNotifyMsgByComps(startIndex, endIndex));
    }
}
