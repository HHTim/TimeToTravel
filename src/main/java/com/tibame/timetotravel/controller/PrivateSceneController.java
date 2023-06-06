package com.tibame.timetotravel.controller;


import com.tibame.timetotravel.entity.PrivateScene;
import com.tibame.timetotravel.service.PrivateSceneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/privateSceneController")
public class PrivateSceneController {

    @Autowired
    @Qualifier("privateSceneService")
    private PrivateSceneService privateSceneService;

    @PostMapping("/privateScene")
    public String insert(@RequestBody PrivateScene privateScene){
        privateSceneService.insert(privateScene);
        return "新增成功!";
    }

    @GetMapping("/privateScene")
    public List<PrivateScene> findAll(){
        return privateSceneService.findAll();
    }

    @DeleteMapping("/privateScene/{privateSceneId}")
    public String deleteById(@PathVariable Integer privateSceneId){
        privateSceneService.deleteById(privateSceneId);
        return "刪除成功";
    }
    @GetMapping("/privateScene/{keyword}")
    public List<PrivateScene> findByKeyword(@PathVariable String keyword){
        return privateSceneService.findByKeyword(keyword);
    }
    @GetMapping("/privateScene/findByPrivateSceneId/{privateSceneId}")
    public PrivateScene findById(@PathVariable Integer privateSceneId){
        return privateSceneService.findById(privateSceneId);
    }
    @PutMapping("/privateScene/{privateSceneId}")
    public String updateById(@PathVariable Integer privateSceneId,
                             @RequestBody PrivateScene privateScene){
        privateSceneService.updateById(privateSceneId, privateScene);
        return "修改成功!";
    }
}
