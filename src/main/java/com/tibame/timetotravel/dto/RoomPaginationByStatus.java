package com.tibame.timetotravel.dto;

import com.tibame.timetotravel.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomPaginationByStatus {
    private List<Room> roomList;
    private Integer totalPage;
}
