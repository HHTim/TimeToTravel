package com.tibame.timetotravel.service;

import com.tibame.timetotravel.dto.RoomPagination;
import com.tibame.timetotravel.dto.RoomPaginationByStatus;
import com.tibame.timetotravel.entity.Room;

import java.util.List;

public interface RoomService {
    void insert(Room room);
    void deleteById(Integer roomId);
    void update(Integer roomId, Room room);
    Room findById(Integer roomId);
    List<Room> findAll();
    List<Room> findByKeyword(String keyword);
    List<Room> findByRoomType(String roomTypeValue);

    RoomPagination findByPage(Integer pageNumber);

    RoomPaginationByStatus findByPageByStatus(Integer roomStatus, Integer pageNumber);

    void updateRoomStatus(Integer roomId, Room room);
}
