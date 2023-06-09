package com.tibame.timetotravel.controller;

import com.tibame.timetotravel.common.PageBean;
import com.tibame.timetotravel.entity.PublicScene;
import com.tibame.timetotravel.service.PublicSceneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/scenes")
public class PublicSceneController {


    @Autowired
    private PublicSceneService publicSceneService;


    @PostMapping("/insert")
    public String insert(@RequestBody PublicScene publicScene) {
        publicSceneService.insert(publicScene);
        return "新增景點";
    }


    @RequestMapping("/search/all")
    public List<PublicScene> getAll() {
        System.out.println("找全部景點");
        return publicSceneService.getAll();
    }

    @GetMapping("/getBySceneId/{sceneId}")
    public PublicScene getById(@PathVariable Integer sceneId) {
        System.out.println("查詢成功");
        return publicSceneService.getById(sceneId);
    }

    @GetMapping("/sceneManageSearch/{keyword}/{page}")
    public PageBean<PublicScene> findBySceneAddress(
            @PathVariable String keyword, 
            @PathVariable Integer page
    ) {
        System.out.println("關鍵字查詢");
        return publicSceneService.findBySceneAddress(keyword, page);
    }

    @DeleteMapping("/deletePublicScene/{sceneId}")
    public String deleteById(@PathVariable Integer sceneId) {
        publicSceneService.deleteById(sceneId);
        return "刪除成功";
    }

    @PutMapping("/updatePublicScene/{sceneId}")
    public String update(@PathVariable Integer sceneId,
                         @RequestBody PublicScene publicScene){
        publicSceneService.update(sceneId,publicScene);
        return "修改成功";
    }
}
