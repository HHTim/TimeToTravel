package com.tibame.timetotravel.controller;

import com.tibame.timetotravel.entity.Journey;
import com.tibame.timetotravel.service.JourneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("journeyController")
public class JourneyController {
    @Autowired
    @Qualifier("journeyService")
    private JourneyService journeyService;

    @PostMapping("/journey")
    public String insert(@RequestBody Journey journey){
        journeyService.insert(journey);
        return "新增成功!";
    }
    @PutMapping("/journey/{journeyId}")
    public String updateById(@PathVariable Integer journeyId,
                             @RequestBody Journey journey){
        journeyService.updateById(journeyId, journey);
        return "修改成功";
    }

    @GetMapping("/journey")
    public List<Journey> findAll(){
        return journeyService.findAll();
    }

    @GetMapping("/journey/{keyword}")
    public List<Journey> findByKeyword(@PathVariable String keyword){
        return journeyService.findByKeyword(keyword);
    }
}
