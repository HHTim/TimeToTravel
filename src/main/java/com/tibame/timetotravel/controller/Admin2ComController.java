package com.tibame.timetotravel.controller;

import com.tibame.timetotravel.common.PageBean;
import com.tibame.timetotravel.dto.UserSessionDto;
import com.tibame.timetotravel.entity.A2CMessage;
import com.tibame.timetotravel.service.A2CMessageService;
import com.tibame.timetotravel.service.CompanyService;
import com.tibame.timetotravel.view.A2CMsgView;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Admin2ComController")
public class Admin2ComController {

    @Autowired
    @Qualifier("A2CMessageService")
    A2CMessageService a2cMessageService;

    @Autowired
    @Qualifier("CompanyService")
    CompanyService companyService;

    @PostMapping("/message")
    public String insert(@RequestBody A2CMessage message){
        System.out.println("執行資料庫Insert");
        System.out.println("+++++++++++++++" + message);
        a2cMessageService.insert(message);
        return "執行資料庫的 Insert 操作";
    }

    @PatchMapping(value = "/comName", consumes = "multipart/form-data")
    public String updateNewsStatus(@RequestParam("comName") String comName){
        System.out.println("接收到的廠商名為:"+comName);
        String act = companyService.updateNewsByComName(comName);
        return "更新廠商: " + act + "的消息狀態";
    }
    @GetMapping("/message/page/{currPage}/{rows}")
    public PageBean<A2CMessage> readByPage(@PathVariable Integer currPage, @PathVariable Integer rows){
        System.out.println("分頁搜尋");
        return a2cMessageService.findAllByPageRowData(currPage, rows);
    }

    @GetMapping("/message/a2c/view/page/{currPage}/{rows}")
    public PageBean<A2CMsgView> readViewByPage(@PathVariable Integer currPage, @PathVariable Integer rows){
        System.out.println("View分頁搜尋");
        return a2cMessageService.findViewByPageRowData(currPage, rows);
    }

    @GetMapping("/message/a2c/view/page/dateRange/{currPage}/{rows}/{startDate}/{endDate}")
    public PageBean<A2CMsgView> readViewByDateRange(@PathVariable Integer currPage, @PathVariable Integer rows , @PathVariable String startDate, @PathVariable String endDate){
        System.out.println("日期分頁搜尋range: "+ startDate + " ~ " + endDate);
        return a2cMessageService.findBeanPageViewByDateRange(startDate, endDate , currPage, rows);
    }

    @GetMapping("/message/a2c/view/notify/{startIndex}/{endIndex}")
    public ResponseEntity<List<A2CMsgView>> readViewByCompanyNotify(@PathVariable Integer startIndex,
                                                                 @PathVariable Integer endIndex,
                                                                 HttpSession session){
        Integer comId = ((UserSessionDto) session.getAttribute("user")).getCompany().getComId();
        System.out.println("獲得商家ID: "+ comId);
        return ResponseEntity.ok(a2cMessageService.getNotifyMsgById(comId, startIndex, endIndex));
    }

}
