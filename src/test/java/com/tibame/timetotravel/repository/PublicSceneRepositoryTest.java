package com.tibame.timetotravel.repository;

import com.tibame.timetotravel.entity.PublicScene;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class PublicSceneRepositoryTest {

    @Autowired
    PublicSceneRepository publicSceneRepository;

    @Test
    void findByNameTest() {
//        List<PublicScene> scenes = publicSceneRepository.findByName("台北");
//        for (PublicScene scene : scenes) {
//            System.out.println(scene);
//        }
    }
}