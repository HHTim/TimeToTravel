package com.tibame.timetotravel.repository;

import com.tibame.timetotravel.entity.Journey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JourneyRepository extends CrudRepository<Journey, Integer> {
    public List<Journey> findByComId(Integer comId);
}
