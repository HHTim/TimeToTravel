package com.tibame.timetotravel.repository;

import com.tibame.timetotravel.entity.PublicScene;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("PublicSceneRepository")
public interface PublicSceneRepository extends JpaRepository<PublicScene, Integer> {
    @Query(value = "select * from public_scene where SCENE_NAME like %?1% OR SCENE_ADDR like %?1%",nativeQuery = true)
    List<PublicScene> findBySceneAddress(String sceneKeyword);

}

