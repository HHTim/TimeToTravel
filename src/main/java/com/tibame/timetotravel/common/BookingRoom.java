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
    String comName;
    String comAddress;
    List<Room> rooms;
    List<PrivateScene> privateScenes;
    List<String> orderComments;
    List<Integer> orderRanks;
}
