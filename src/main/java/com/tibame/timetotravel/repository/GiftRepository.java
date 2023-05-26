package com.tibame.timetotravel.repository;

import com.tibame.timetotravel.entity.Gift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("giftRepository")
public interface GiftRepository extends JpaRepository<Gift, Integer> {
}
