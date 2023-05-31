package com.tibame.timetotravel.repository;

import com.tibame.timetotravel.entity.GiftOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("giftOrderRepository")
public interface GiftOrderRepository extends JpaRepository<GiftOrder, Integer> {

}
