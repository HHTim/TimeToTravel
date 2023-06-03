package com.tibame.timetotravel.controller;

import com.tibame.timetotravel.Entity.PublicScene;
import com.tibame.timetotravel.Service.PublicSceneService;
import com.tibame.timetotravel.Service.ServiceImpl.PublicSceneServicelmpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.ls.LSOutput;

import java.sql.SQLOutput;
import java.util.List;


@RestController
@RequestMapping("/scenes")
public class    PublicSceneController {


    @Autowired
    private PublicSceneService publicSceneService;

    @PersistenceContext
    private EntityManager entityManager;
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

    @RequestMapping("/upPublicscene/{sceneId}")
    public void update(@PathVariable Integer sceneId,@RequestBody PublicScene publicScene){
        PublicScene upPublicscene = entityManager.find(PublicScene.class, sceneId);
        entityManager.merge(upPublicscene);
    }
//        PublicSceneService.update(sceneId,publicScene);
//        return ;




    // Other methods for updating, deleting, or retrieving specific scenes
}
