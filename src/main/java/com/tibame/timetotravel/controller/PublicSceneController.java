package com.tibame.timetotravel.controller;

import com.tibame.timetotravel.Entity.PublicScene;
import com.tibame.timetotravel.Service.PublicSceneService;
import com.tibame.timetotravel.Service.ServiceImpl.PublicSceneServicelmpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.List;


@RestController
@RequestMapping("/scenes")
public class    PublicSceneController {

    @Autowired
    private PublicSceneService publicSceneService;

    @PostMapping("/send")
    public String insertScene(@RequestBody PublicScene scene) {
        publicSceneService.insert(scene);
        return "Scene inserted successfully.";
    }

    @RequestMapping("/hi")
    public String text(){
        System.out.println("今天天氣很好");
        return "return";//顯示在網頁頁面
    }


    @RequestMapping("/getall")
    public List<PublicScene> getAll() {
        return publicSceneService.getAll();
    }

    // Other methods for updating, deleting, or retrieving specific scenes
}
