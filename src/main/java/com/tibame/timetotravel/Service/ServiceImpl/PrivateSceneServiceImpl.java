package com.tibame.timetotravel.service.ServiceImpl;

import com.tibame.timetotravel.entity.PrivateScene;
import com.tibame.timetotravel.repository.PrivateSceneRepository;
import com.tibame.timetotravel.service.PrivateSceneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
