package com.tibame.timetotravel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderWithUser {
    private String userName;
    private byte[] userAvatar;
    private String convertAvatar;
    private Integer orderRank;
    private String orderComment;
    private Timestamp orderDateTime;
    private String roomName;

    public OrderWithUser(String userName, byte[] userAvatar, Integer orderRank, String orderComment, Timestamp orderDateTime, String roomName) {
        this.userName = userName;
        this.userAvatar = userAvatar;
        this.orderRank = orderRank;
        this.orderComment = orderComment;
        this.orderDateTime = orderDateTime;
        this.roomName = roomName;
    }
}
