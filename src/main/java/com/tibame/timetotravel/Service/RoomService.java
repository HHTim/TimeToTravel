package com.tibame.timetotravel.service;

import com.tibame.timetotravel.entity.Room;

import java.util.List;

public interface RoomService {
    void insert(Room room);
    void deleteById(Integer roomId);
    void update(Integer roomId, Room room);
    Room findById(Integer roomId);
    List<Room> findAll();
}
