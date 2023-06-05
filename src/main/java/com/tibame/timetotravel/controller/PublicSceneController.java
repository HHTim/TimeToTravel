package com.tibame.timetotravel.controller;

import com.tibame.timetotravel.entity.PublicScene;
import com.tibame.timetotravel.service.PublicSceneService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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


        @RequestMapping("/getall")
    public List<PublicScene> getAll() {
        System.out.println("找全部景點");
        return publicSceneService.getAll();
    }

    @GetMapping("/search")
    public List<PublicScene> findByAddress(HttpServletRequest req){
        HttpSession session = req.getSession();
        String sceneKeyword = (String) session.getAttribute("sceneKeyword");
        return publicSceneService.findByAddress(sceneKeyword);
    }

    @GetMapping("/sceneManageSearch/{keyword}")
    public List<PublicScene> findBySceneAddress(@PathVariable String keyword){
        System.out.println("關鍵字查詢");
        return publicSceneService.findBySceneAddress(keyword);
    }

    @DeleteMapping("/deletePublicScene/{sceneId}")
    public String deleteById(@PathVariable Integer sceneId){
        publicSceneService.deleteById(sceneId);
        return "刪除成功";
    }

    @PutMapping ("/updatePublicScene/{sceneId}")
    public String update(@PathVariable Integer sceneId,
                             @RequestBody PublicScene publicScene){
        publicSceneService.update(sceneId,publicScene);
        return "修改成功";
    }

}
