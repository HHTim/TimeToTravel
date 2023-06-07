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
    public String updateStatusById(@PathVariable Integer journeyId,
                             @RequestBody Journey journey){
        journeyService.updateStatusById(journeyId, journey);
        return "修改成功";
    }
    @PutMapping("/journey/updateJourney/{journeyId}")
    public String updateJourneyById(@PathVariable Integer journeyId,
                                @RequestBody Journey journey){
        journeyService.updateJourneyById(journeyId, journey);
        return "修改成功!";
    }

    @GetMapping("/journey")
    public List<Journey> findAll(){
        return journeyService.findAll();
    }
    @GetMapping("/journey/findByJourneyId/{journeyId}")
    public Journey findById(@PathVariable Integer journeyId){
        return journeyService.findById(journeyId);
    }

    @GetMapping("/journey/{keyword}")
    public List<Journey> findByKeyword(@PathVariable String keyword){
        return journeyService.findByKeyword(keyword);
    }
}
