package com.tibame.timetotravel.service;

import com.tibame.timetotravel.entity.Journey;
import org.hibernate.sql.Insert;
import org.springframework.stereotype.Service;

import java.util.List;

public interface JourneyService {
    void insert(Journey journey);

    void updateById(Integer journeyId, Journey journey);
    List<Journey> findAll();

    List<Journey> findByKeyword(String keyword);


}
