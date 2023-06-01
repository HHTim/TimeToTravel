package com.tibame.timetotravel.repository;

import com.tibame.timetotravel.entity.Journey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("journeyRepository")
public interface JourneyRepository extends JpaRepository<Journey, Integer> {

    @Query(value = "SELECT * FROM JOURNEY WHERE JOURNEY_NAME LIKE %?1%", nativeQuery = true)
    List<Journey> findByKeyword(String keyword);

    List<Journey> findByComId(Integer comId);

    @Query(value = "SELECT * FROM JOURNEY WHERE JOURNEY_ID = ?1", nativeQuery = true)
    Journey findByJourneyId(Integer journeyId);
}
