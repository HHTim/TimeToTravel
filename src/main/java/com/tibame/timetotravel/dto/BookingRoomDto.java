package com.tibame.timetotravel.dto;

import com.tibame.timetotravel.entity.PrivateScene;
import com.tibame.timetotravel.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingRoomDto {
    private String comName;
    private String comAddress;
    private String roomName;
    private String roomDesc;
    private Integer roomPrice;
    private List<Integer> orderRanks;
    private List<Room> rooms;
    private List<PrivateScene> privateScenes;
    //    private List<String> allComments;
//    private List<Integer> allOrderRanks;
//    private List<byte[]> allUserAvatars;
//    private List<String> allUserNames;
    private List<OrderWithUser> orderWithUsers;
//    private List<ViewUserOrderDetail> viewUserOrderDetails;
}
