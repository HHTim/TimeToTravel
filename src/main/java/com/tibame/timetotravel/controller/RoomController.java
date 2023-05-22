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
        return roomService.findAll();
    }
}
