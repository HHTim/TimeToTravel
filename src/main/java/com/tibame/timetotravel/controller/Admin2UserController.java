package com.tibame.timetotravel.controller;

import com.tibame.timetotravel.common.PageBean;
import com.tibame.timetotravel.dto.UserSessionDto;
import com.tibame.timetotravel.entity.A2UMessage;
import com.tibame.timetotravel.service.A2UMessageService;
import com.tibame.timetotravel.service.UserService;
import com.tibame.timetotravel.view.A2UMsgView;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/Admin2UserController")
public class Admin2UserController {

    @Autowired
    @Qualifier("A2UMessageService")
    A2UMessageService a2uMessageService;

    @Autowired
    @Qualifier("UserService")
    UserService userService;

    @PostMapping("/message")
    public String insert(@RequestBody A2UMessage message){
        System.out.println("執行資料庫Insert");
        a2uMessageService.insert(message);
        return "執行資料庫的 Insert 操作";
    }

    @PatchMapping(value = "/userAccount", consumes = "multipart/form-data")
    public String update(@RequestParam("account") String account){
        System.out.println("接收到的帳號為:"+account);
        String act = userService.updateByAccount(account);
        return "更新會員帳號: " + act + "的消息狀態";
    }

    @GetMapping("/message/page/{currPage}/{rows}")
    public PageBean<A2UMessage> readByPage(@PathVariable Integer currPage, @PathVariable Integer rows){
        System.out.println("分頁搜尋");
        return a2uMessageService.findAllByPageRowData(currPage,rows);
    }

    @GetMapping("/message/a2u/view/page/{currPage}/{rows}")
    public PageBean<A2UMsgView> readViewByPage(@PathVariable Integer currPage, @PathVariable Integer rows){
        System.out.println("View分頁搜尋");
        return a2uMessageService.findViewByPageRowData(currPage, rows);
    }

    @GetMapping("/message/a2u/view/page/dateRange/{currPage}/{rows}/{startDate}/{endDate}")
    public PageBean<A2UMsgView> readViewByDateRange(@PathVariable Integer currPage, @PathVariable Integer rows , @PathVariable String startDate, @PathVariable String endDate){
        System.out.println("日期分頁搜尋range: "+ startDate + " ~ " + endDate);
        return a2uMessageService.findBeanPageViewByDateRange(startDate, endDate , currPage, rows);
    }

    @GetMapping("/message/a2u/view/notify/{startIndex}/{endIndex}")
    public ResponseEntity<List<A2UMsgView>> readViewByUserNotify(@PathVariable Integer startIndex,
                                               @PathVariable Integer endIndex,
                                               HttpSession session){
        Integer userId = ((UserSessionDto) session.getAttribute("user")).getUser().getUserId();
        System.out.println("獲得使用者ID: "+ userId);
        return ResponseEntity.ok(a2uMessageService.getNotifyMsgById(userId, startIndex, endIndex));
    }
}
