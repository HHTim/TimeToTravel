package com.tibame.timetotravel.repository;

import com.tibame.timetotravel.entity.GiftType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("giftTypeRepository")
public interface GiftTypeRepository extends JpaRepository<GiftType, Integer> {

}
