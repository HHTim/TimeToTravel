package com.tibame.timetotravel.service.ServiceImpl;


import com.tibame.timetotravel.Entity.PublicScene;
import com.tibame.timetotravel.Service.PublicSceneService;
import com.tibame.timetotravel.repository.PublicSceneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("PublicSceneService")
public class PublicSceneServicelmpl implements PublicSceneService {

    @Autowired
    @Qualifier("PublicSceneRepository")
    private PublicSceneRepository publicSceneRepository;

    @Override
    public void insert(PublicScene publicScene) {
        publicSceneRepository.save(publicScene);
    }

    @Override
    public List<PublicScene> getAll() {
        return publicSceneRepository.findAll();
    }
}

