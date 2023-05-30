package com.tibame.timetotravel.repository;

import com.tibame.timetotravel.entity.PrivateScene;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class PrivateSceneRepositoryTest {

    @Autowired
    PrivateSceneRepository privateSceneRepository;

    @Test
    public void findAllByComIdTest() {
        List<PrivateScene> scenes = privateSceneRepository.findAllByComId(2);
        for (PrivateScene scene : scenes) {
            System.out.println(scene);
        }
    }
}