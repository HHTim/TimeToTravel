package com.tibame.timetotravel.common;

import com.tibame.timetotravel.entity.PrivateScene;
import com.tibame.timetotravel.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingRoom {
    private String comName;
    private String comAddress;
    private String roomName;
    private String roomDesc;
    private List<Integer> orderRanks;
    private List<Room> rooms;
    private List<PrivateScene> privateScenes;
    private List<String> orderComments;
    private List<List<Integer>> allOrderRanks;
}
