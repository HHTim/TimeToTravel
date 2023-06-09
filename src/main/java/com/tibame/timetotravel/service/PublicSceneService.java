package com.tibame.timetotravel.service;

import com.tibame.timetotravel.common.PageBean;
import com.tibame.timetotravel.entity.PublicScene;

import java.util.List;
import java.util.Optional;

public interface PublicSceneService {
    PublicScene getById(Integer sceneId);//編輯

    void insert(PublicScene publicScene);

    void deleteById(Integer sceneId);

    List<PublicScene> getAll();

    void update(Integer sceneId, PublicScene publicScene);

    PageBean<PublicScene> findBySceneAddress(String keyword, Integer page);
}
