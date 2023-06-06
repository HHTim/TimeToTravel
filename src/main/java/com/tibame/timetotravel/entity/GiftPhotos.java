package com.tibame.timetotravel.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "gift_photos")
public class GiftPhotos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GIFT_PHOTO_ID", insertable = false, updatable = false)
    private Integer giftPhotoId;

    @Column(name = "GIFT_ID", nullable = false)
    private Integer giftId;

    @Column(name = "GIFT_PHOTO")
    private byte[] giftPhoto;

}
