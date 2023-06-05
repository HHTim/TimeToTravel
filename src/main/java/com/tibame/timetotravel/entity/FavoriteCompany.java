package com.tibame.timetotravel.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "favorite_com")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FAVORITE_ID", insertable = false, updatable = false)
    private Integer favoriteId;

    @Column(name = "USER_ID")
    private Integer userId;

    @Column(name = "COM_ID")
    private Integer comId;
}
