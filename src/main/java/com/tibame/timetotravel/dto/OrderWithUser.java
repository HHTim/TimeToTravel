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
    private Integer orderRank;
    private String orderComment;
    private Timestamp orderDateTime;
    private String roomName;
}
