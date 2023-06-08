package com.tibame.timetotravel.repository;

import com.tibame.timetotravel.entity.GiftOrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("giftOrderDetailsRepository")
public interface GiftOrderDetailsRepository extends JpaRepository<GiftOrderDetails, Integer> {

    List<GiftOrderDetails> findByGiftOrderId(Integer giftOrderId);

}
