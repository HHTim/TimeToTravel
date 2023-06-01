//package com.tibame.timetotravel.controller;
//
//import com.tibame.timetotravel.entity.Gift;
//import com.tibame.timetotravel.entity.Room;
//import com.tibame.timetotravel.repository.GiftPhotoRepository;
//import com.tibame.timetotravel.repository.GiftRepository;
//import com.tibame.timetotravel.service.GiftService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/giftController")
//public class GiftControllerTest {
//
//    @Autowired
//    private GiftRepository giftRepository;
//
//    @Autowired
//    private GiftPhotoRepository giftPhotoRepository;
//
//    @Autowired
//    @Qualifier("giftService")
//    private GiftService giftService;
//
//
//
//
//
//    @PostMapping("/gift")
//    public String insert(@RequestBody Gift gift){
//        giftService.insert(gift);
//        return "新增成功";
//    }
//
//    @DeleteMapping("/gift/{giftId}")
//    public String deleteById(@PathVariable Integer giftId){
//        giftService.deleteById(giftId);
//        return "刪除成功";
//    }
//
//    @PutMapping("/gift/{giftId}")
//    public String updateById(@PathVariable Integer giftId,
//                             @RequestBody Gift gift){
//        giftService.update(giftId, gift);
//        return "修改成功";
//    }
//    @GetMapping("/gift")
//    public List<Gift> findAll(){
//        return giftService.findAll();
//    }
//
//}
