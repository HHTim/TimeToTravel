package com.tibame.timetotravel.repository;

import com.tibame.timetotravel.entity.PrivateScene;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("privateSceneRepository")
public interface PrivateSceneRepository extends JpaRepository<PrivateScene, Integer> {

    @Modifying // 告訴JPA不使用內建的deleteById()
    @Query(value = "DELETE FROM PRIVATE_SCENE WHERE PRIVATE_SCENE_ID = ?1", nativeQuery = true)
    void deleteById(Integer privateSceneId);

    @Query(value = "SELECT * FROM PRIVATE_SCENE WHERE PRIVATE_SCENE_NAME LIKE %?1%", nativeQuery = true)
    List<PrivateScene> findByKeyword(String keyword);

    List<PrivateScene> findAllByComId(Integer comId);
}
