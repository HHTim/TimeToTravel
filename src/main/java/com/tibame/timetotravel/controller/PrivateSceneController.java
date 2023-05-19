package com.tibame.timetotravel.controller;


import com.tibame.timetotravel.Entity.PrivateScene;
import com.tibame.timetotravel.repository.PrivateSceneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequestMapping("/PrivateSceneController")
public class PrivateSceneController {
    @Autowired
    private PrivateSceneRepository privateSceneRepository;

    @RequestMapping("/redirect")
    public RedirectView redirectView(){
        return  new RedirectView("/html/primary_scene_manage.html");
    }

    @RequestMapping("/privateScene")
    public List<PrivateScene> selectAll(){
        return privateSceneRepository.findAll();
    }
}
