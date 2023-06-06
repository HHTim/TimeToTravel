package com.tibame.timetotravel.service;

import com.tibame.timetotravel.entity.GiftPhotos;

import java.util.List;

public interface GiftPhotosService {

    void insert(GiftPhotos giftPhotos);

    void deleteById(Integer giftPhotosId);

    GiftPhotos updateById(Integer giftPhotosId, GiftPhotos giftPhotos);

    GiftPhotos findById(Integer giftPhotosId);

    List<GiftPhotos> findAll();
}
