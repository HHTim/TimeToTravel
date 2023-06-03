package com.tibame.timetotravel.service.ServiceImpl;


import com.tibame.timetotravel.entity.PublicScene;
import com.tibame.timetotravel.repository.PublicSceneRepository;
import com.tibame.timetotravel.service.PublicSceneService;
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
    public void insert(PublicScene publicScene) {
        publicSceneRepository.save(publicScene);
    }

    @Override
    public List<PublicScene> getAll() {
        return publicSceneRepository.findAll();
    }

    @Override
    public void update (Integer sceneId,PublicScene publicScene){
        PublicScene upPublicscene = entityManager.find(PublicScene.class, sceneId);
        entityManager.merge(upPublicscene);
    }

}

