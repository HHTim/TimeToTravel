package com.tibame.timetotravel.repository;

import com.tibame.timetotravel.entity.Journey;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class JourneyRepositoryTest {

    @Autowired
    JourneyRepository journeyRepository;

    @Test
    public void findByComIdTest() {
        List<Journey> journeys = journeyRepository.findByComId(2);
        for (Journey journey : journeys) {
            System.out.println(journey);
        }
    }

    @Test
    public void findByJourneyIdTest() {
        Journey journey = journeyRepository.findByJourneyId(null);
        System.out.println(journey);
    }
}