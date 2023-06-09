package com.tibame.timetotravel.service.ServiceImpl;


import com.tibame.timetotravel.common.PageBean;
import com.tibame.timetotravel.entity.PublicScene;
import com.tibame.timetotravel.repository.PublicSceneRepository;
import com.tibame.timetotravel.service.PublicSceneService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("PublicSceneService")
public class PublicSceneServicelmpl implements PublicSceneService {

    @Autowired
    @Qualifier("PublicSceneRepository")
    private PublicSceneRepository publicSceneRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public PublicScene getById(Integer sceneId) {
        return entityManager.find(PublicScene.class, sceneId);
    }

    @Override
    public void insert(PublicScene publicScene) {
        publicSceneRepository.save(publicScene);
    }

    @Override
    public void deleteById(Integer sceneId) {
        publicSceneRepository.deleteById(sceneId);
    }

    @Override
    public List<PublicScene> getAll() {
        return publicSceneRepository.findAll();
    }

    // @Override
    // public List<PublicScene> findByAddress(String sceneKeyword) {
    //     return publicSceneRepository.findBySceneAddress(sceneKeyword);
    // }


    @Override
    public void update(Integer sceneId, PublicScene publicScene) {
        PublicScene newPublicScene = publicSceneRepository.findById(sceneId).orElse(null);

        newPublicScene.setScenePhoto(publicScene.getScenePhoto());
        newPublicScene.setSceneName(publicScene.getSceneName());
        newPublicScene.setSceneAddr(publicScene.getSceneAddr());
        newPublicScene.setSceneLat(publicScene.getSceneLat());
        newPublicScene.setSceneLng(publicScene.getSceneLng());
        newPublicScene.setScenePlaceId(publicScene.getScenePlaceId());
        newPublicScene.setSceneDesc(publicScene.getSceneDesc());
        publicSceneRepository.save(newPublicScene);

    }

    @Override
    public PageBean<PublicScene> findBySceneAddress(String keyword, Integer page) {
        if (keyword != null && !("".equals(keyword))) {
            PageBean<PublicScene> pageBeen = new PageBean<>();
            List<PublicScene> scenes = publicSceneRepository.findBySceneAddress(keyword);
            pageBeen.setPageSize(scenes.size());
            pageBeen.setRows(scenes.stream().skip((page - 1) * 6).limit(6).collect(Collectors.toList()));
            return pageBeen;
        } else {
            return null;
        }

    }


}

