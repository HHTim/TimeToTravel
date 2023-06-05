package com.tibame.timetotravel.service;

import com.tibame.timetotravel.entity.PublicScene;

import java.util.List;

public interface PublicSceneService {
    void insert(PublicScene publicScene);

    List<PublicScene> getAll();

    void update(Integer sceneId, PublicScene publicScene);


    List<PublicScene> findByName(String name);
}
