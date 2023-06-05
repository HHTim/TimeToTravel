package com.tibame.timetotravel.service.ServiceImpl;

import com.tibame.timetotravel.entity.PrivateScene;
import com.tibame.timetotravel.repository.PrivateSceneRepository;
import com.tibame.timetotravel.service.PrivateSceneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("privateSceneService")
public class PrivateSceneServiceImpl implements PrivateSceneService {
    @Autowired
    @Qualifier("privateSceneRepository")
    private PrivateSceneRepository privateSceneRepository;

    @Override
    @Transactional
    public void insert(PrivateScene privateScene) {
        privateSceneRepository.save(privateScene);
    }

    @Override
    public List<PrivateScene> findAll() {
        return privateSceneRepository.findAll();
    }

    @Override
    public void deleteById(Integer privateSceneId) {
        privateSceneRepository.deleteById(privateSceneId);
    }

    @Override
    public List<PrivateScene> findByKeyword(String keyword) {
        if ((keyword != null) && !("".equals(keyword))) {
            return privateSceneRepository.findByKeyword(keyword);
        } else {
            return null;
        }
    }

    @Override
    public PrivateScene findById(Integer privateSceneId) {
        return privateSceneRepository.findById(privateSceneId).orElse(null);
    }

    @Override
    public void updateById(Integer privateSceneId, PrivateScene privateScene) {
         PrivateScene newPrivateScene = privateSceneRepository.findById(privateSceneId).orElse(null);
         newPrivateScene.setPrivateSceneName(privateScene.getPrivateSceneName());
         newPrivateScene.setPrivateSceneDesc(privateScene.getPrivateSceneDesc());
         newPrivateScene.setPrivateScenePic(privateScene.getPrivateScenePic());
         privateSceneRepository.save(newPrivateScene);
    }
}
