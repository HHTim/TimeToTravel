package com.tibame.timetotravel.repository;

import com.tibame.timetotravel.entity.PrivateScene;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("privateSceneRepository")
public interface PrivateSceneRepository extends JpaRepository<PrivateScene, Integer> {
}
