package com.tibame.timetotravel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchRoomDto {
    private Integer comId;
    private String comName;
    private String comAddress;
    private Integer roomId;
    private String roomName;
    private String roomDesc;
    private byte[] roomPhoto;
    private List<Integer> orderRanks;
}
