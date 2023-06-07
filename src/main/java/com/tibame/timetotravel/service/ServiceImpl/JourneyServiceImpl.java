package com.tibame.timetotravel.service.ServiceImpl;

import com.tibame.timetotravel.entity.Journey;
import com.tibame.timetotravel.repository.JourneyRepository;
import com.tibame.timetotravel.service.JourneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("journeyService")
public class JourneyServiceImpl implements JourneyService {
    @Autowired
    @Qualifier("journeyRepository")
    private JourneyRepository journeyRepository;

    @Override
    public void insert(Journey journey) {
        journeyRepository.save(journey);
    }

    @Override
    public void updateStatusById(Integer journeyId, Journey journey) {
        Journey newJourney = journeyRepository.findById(journeyId).orElse(null);
        newJourney.setJourneyStatus(journey.getJourneyStatus());
        journeyRepository.save(newJourney);
    }

    @Override
    public List<Journey> findAll() {
        return journeyRepository.findAll();
    }

    @Override
    public List<Journey> findByKeyword(String keyword) {
        if ((keyword != null) && !("".equals(keyword))) {
            return journeyRepository.findByKeyword(keyword);
        } else {
            return null;
        }
    }

    @Override
    public Journey findById(Integer journeyId) {
        return journeyRepository.findById(journeyId).orElse(null);
    }

    @Override
    public void updateJourneyById(Integer journeyId, Journey journey) {
        Journey newJourney = journeyRepository.findById(journeyId).orElse(null);
        newJourney.setJourneyName(journey.getJourneyName());
        newJourney.setJourneyPrice(journey.getJourneyPrice());
        newJourney.setJourneyDesc(journey.getJourneyDesc());
        newJourney.setJourneyPic(journey.getJourneyPic());
        journeyRepository.save(newJourney);
    }
}
