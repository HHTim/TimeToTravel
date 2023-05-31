package com.tibame.timetotravel.repository;

import com.tibame.timetotravel.entity.GiftPhotos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("giftPhotoRepository")
public interface GiftPhotoRepository extends JpaRepository<GiftPhotos, Integer> {
}
