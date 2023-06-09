package com.tibame.timetotravel.controller;

import com.tibame.timetotravel.common.PageBean;
import com.tibame.timetotravel.dto.UserSessionDto;
import com.tibame.timetotravel.entity.GiftOrder;
import com.tibame.timetotravel.service.GiftOrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/giftOrderController")
public class GiftOrderController {

    @Autowired
    @Qualifier("giftOrderService")
    private GiftOrderService giftOrderService;

    @PostMapping("/giftOrder/{userId}")
    public ResponseEntity<String> insert(@PathVariable Integer userId) {
        giftOrderService.insert(userId);
        return ResponseEntity.ok("新增一筆訂單囉！");
    }

    @GetMapping("/giftOrder/{userId}")
    public ResponseEntity<?> findByUserId(@PathVariable Integer userId) {
        List<GiftOrder> giftOrderList = giftOrderService.findByUserId(userId);

        if (giftOrderList.isEmpty()) {
            String empty = "沒有訂單紀錄 :)";
            return ResponseEntity.ok(empty);
        }

        return ResponseEntity.ok(giftOrderList);
    }

    @GetMapping("/giftOrderId/{giftOrderId}")
    public ResponseEntity<?> getByOrderId(HttpSession session,
                                          @PathVariable Integer giftOrderId) {
//        Integer userId = (Integer) session.getAttribute("user_id"); // 此行無法確定登入者的身分
        UserSessionDto user = (UserSessionDto) session.getAttribute("user");

        if (user != null) {
            if (user.getRole() == "會員") {
                Integer userId = user.getUser().getUserId();
                System.out.println(user.getRole() + "：" + userId);

                GiftOrder giftOrder = giftOrderService.getByOrderId(userId, giftOrderId);

                if (giftOrder == null) {
                    return null;
                }

                return ResponseEntity.ok(giftOrder);
            }
            return null;
        }
        return null;

    }

}


//    @GetMapping("/giftOrder/{userId}")
//    public ResponseEntity<?> findByUserId(@PathVariable Integer userId,
//                                          @PathVariable Integer page) {
//        PageBean<GiftOrder> giftOrderList = giftOrderService.findByUserId(userId, page);
//
//        if (giftOrderList == null) {
//            PageBean<GiftOrder> objectPageBean = new PageBean<>();
//            return ResponseEntity.ok(objectPageBean);
//        }
//
//        return ResponseEntity.ok(giftOrderList);
//    }
