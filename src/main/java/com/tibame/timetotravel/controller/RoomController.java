package com.tibame.timetotravel.controller;

import com.tibame.timetotravel.entity.Room;
import com.tibame.timetotravel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roomController")
public class RoomController {
    @Autowired
    @Qualifier("roomService")
    private RoomService roomService;

    @PostMapping("/room")
    public String insert(@RequestBody Room room){
        roomService.insert(room);
        return "新增成功";
    }
    @DeleteMapping("/room/{roomId}")
    public String deleteById(@PathVariable Integer roomId){
        roomService.deleteById(roomId);
        return "刪除成功";
    }
    @PutMapping("/room/{roomId}")
    public String updateById(@PathVariable Integer roomId,
                             @RequestBody Room room){
        roomService.update(roomId, room);
        return "修改成功";
    }
    @GetMapping("/room")
    public List<Room> findAll(){
        System.out.println("找全部房間");
        return roomService.findAll();
    }
    @GetMapping("/room/{keyword}")
    public List<Room> findByKeyword(@PathVariable String keyword){
        System.out.println("關鍵字搜尋");
        return roomService.findByKeyword(keyword);
    }
    @GetMapping("/room/roomType/{roomTypeValue}")
    public List<Room> findByRoomType(@PathVariable String roomTypeValue){
        System.out.println(roomTypeValue);
        System.out.println("分類查詢");
        return roomService.findByRoomType(roomTypeValue);
    }
}
