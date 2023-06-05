package com.tibame.timetotravel.repository;

import com.tibame.timetotravel.entity.Gift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("giftRepository")
public interface GiftRepository extends JpaRepository<Gift, Integer> {

    @Query(value = "SELECT * FROM GIFT WHERE GIFT_NAME LIKE %?1%", nativeQuery = true)
    List<Gift> findByKeyword(String keyword);

    @Query(value = "SELECT * FROM GIFT WHERE GIFT_TYPE_ID LIKE %?1%", nativeQuery = true)
    List<Gift> findByGiftType(String giftTypeValue);
}
