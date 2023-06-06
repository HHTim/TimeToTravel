package com.tibame.timetotravel.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "gift_follow")
public class GiftFollow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GIFT_FOLLOW_ID", insertable = false, updatable = false)
    private Integer giftFollowId;

    @Column(name = "GIFT_ID", nullable = false)
    private Integer giftId;

    @Column(name = "USER_ID", nullable = false)
    private Integer userId;

}
