package com.tibame.timetotravel.repository;

import com.tibame.timetotravel.entity.PrivateScene;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrivateSceneRepository extends CrudRepository<PrivateScene, Integer> {

    List<PrivateScene> findAllByComId(Integer comId);
}
