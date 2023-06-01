package com.tibame.timetotravel.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "GIFT_PHOTOS")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class GiftPhotos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GIFT_PHOTO_ID")
    private Integer giftPhotoId;

    @Column(name = "GIFT_ID", nullable = false)
    private Integer giftId;

    @Lob
    @Column(name = "GIFT_PHOTO")
    private byte[] giftPhoto;
}