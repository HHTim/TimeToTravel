package com.tibame.timetotravel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomOrderDto {
    private Integer userId;
    private Integer roomId;
    private Integer journeyId;
    private Integer orderAmount;
    private String orderCheckIn;
    private String orderCheckOut;
    private String orderComment;
    private Integer orderRank;
}
