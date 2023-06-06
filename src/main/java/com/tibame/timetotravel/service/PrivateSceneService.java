package com.tibame.timetotravel.service;

import com.tibame.timetotravel.entity.PrivateScene;

import java.util.List;

public interface PrivateSceneService {

    void insert(PrivateScene privateScene);

    List<PrivateScene> findAll();

    void deleteById(Integer privateSceneId);

    List<PrivateScene> findByKeyword(String keyword);

    PrivateScene findById(Integer privateSceneId);

    void updateById(Integer privateSceneId, PrivateScene privateScene);
}
