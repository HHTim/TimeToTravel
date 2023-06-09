package com.tibame.timetotravel.repository;

import com.tibame.timetotravel.view.ViewGift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ViewGiftRepository extends JpaRepository<ViewGift, Integer> {

    @Query(value = "SELECT * FROM view_gift WHERE GIFT_STATUS = 1 ORDER BY GIFT_ID DESC", nativeQuery = true)
    List<ViewGift> findAll();

    @Query(value = "SELECT * FROM view_gift WHERE GIFT_STATUS = 1 AND GIFT_NAME LIKE %?1%", nativeQuery = true)
    List<ViewGift> getByName(String giftName);

    @Query(value = "SELECT * FROM view_gift WHERE GIFT_STATUS = 1 ORDER BY GIFT_PRICE DESC", nativeQuery = true)
    List<ViewGift> descendGift();

    @Query(value = "SELECT * FROM view_gift WHERE GIFT_STATUS = 1 ORDER BY GIFT_PRICE ASC", nativeQuery = true)
    List<ViewGift> ascendGift();

    @Query(value = "SELECT * FROM view_gift WHERE GIFT_STATUS = 1 AND GIFT_TYPE_ID = ?1", nativeQuery = true)
    List<ViewGift> getByType(String giftTypeId);

}
