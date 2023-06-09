package com.tibame.timetotravel.repository;

import com.tibame.timetotravel.entity.GiftFollow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("giftFollowRepository")
public interface GiftFollowRepository extends JpaRepository<GiftFollow, Integer> {

    GiftFollow findByUserIdAndGiftId(Integer userId, Integer giftId);
}
