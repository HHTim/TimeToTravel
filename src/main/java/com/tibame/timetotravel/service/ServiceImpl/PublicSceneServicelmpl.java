package com.tibame.timetotravel.service.ServiceImpl;


import com.tibame.timetotravel.entity.PublicScene;
import com.tibame.timetotravel.repository.PublicSceneRepository;
import com.tibame.timetotravel.service.PublicSceneService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("PublicSceneService")
public class PublicSceneServicelmpl implements PublicSceneService {

    @Autowired
    @Qualifier("PublicSceneRepository")
    private PublicSceneRepository publicSceneRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public PublicScene getById(Integer sceneId) {
        return entityManager.find(PublicScene.class,sceneId);
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

//    @Override
//    public List<PublicScene> findByAddress(String sceneKeyword) {
//        return publicSceneRepository.findBySceneAddress(sceneKeyword);
//    }

    @Override
    public void update(Integer sceneId, PublicScene publicScene) {
        PublicScene newPublicScene = entityManager.find(PublicScene.class,sceneId);
            if(newPublicScene != null){
//                newPublicScene.setScenePhoto(publicScene.getScenePhoto());
                newPublicScene.setSceneName(publicScene.getSceneName());
//                newPublicScene.setSceneAddr(publicScene.getSceneAddr());
//                newPublicScene.setSceneLat(publicScene.getSceneLat());
//                newPublicScene.setSceneLng(publicScene.getSceneLng());
//                newPublicScene.setScenePlaceId(publicScene.getScenePlaceId());
//                newPublicScene.setSceneDesc(publicScene.getSceneDesc());
                entityManager.merge(newPublicScene);
            }
    }

    @Override
    public List<PublicScene> findBySceneAddress(String keyword) {
        if (keyword != null && !("".equals(keyword))) {
            return publicSceneRepository.findBySceneAddress(keyword);
        } else {
            return null;
        }

    }



}

