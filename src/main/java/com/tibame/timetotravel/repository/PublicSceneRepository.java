package com.tibame.timetotravel.repository;

import com.tibame.timetotravel.Entity.PublicScene;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("PublicSceneRepository")
public interface PublicSceneRepository extends JpaRepository<PublicScene,Integer> {
}
