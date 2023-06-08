package com.tibame.timetotravel.service;

import com.tibame.timetotravel.common.PageBean;
import com.tibame.timetotravel.entity.PublicScene;

import java.util.List;

public interface PublicSceneService {
    void insert(PublicScene publicScene);

    void deleteById(Integer sceneId);

    List<PublicScene> getAll();

    List<PublicScene> findByAddress(String sceneKeyword);

    void update(Integer sceneId, PublicScene publicScene);

    PageBean<PublicScene> findBySceneAddress(String keyword, Integer page);
}
