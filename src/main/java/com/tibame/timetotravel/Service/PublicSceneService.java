package com.tibame.timetotravel.Service;

import com.tibame.timetotravel.Entity.PublicScene;

import java.util.List;

public interface PublicSceneService {
    void insert(PublicScene publicScene);
    List<PublicScene> getAll();

}
