package com.tibame.timetotravel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomWithCompanyDto {
    //    private Room room;
//    private Company company;
    private Integer roomId;
    private String roomName;
    private String comName;
    private String comAddress;
}
