package com.tibame.timetotravel.repository;

import com.tibame.timetotravel.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("roomRepository")
public interface RoomRepository extends JpaRepository<Room, Integer> {
    @Query(value = "SELECT * FROM ROOM WHERE ROOM_NAME LIKE %?1%", nativeQuery = true)
    List<Room> findByKeyword(String keyword);

    @Query(value = "SELECT * FROM ROOM WHERE ROOM_BED LIKE %?1%", nativeQuery = true)
    List<Room> findByRoomType(String roomTypeValue);

    List<Room> findAllByComId(Integer comId);

    @Query(value = "SELECT COM_NAME FROM COMPANY WHERE COM_ID = (SELECT COM_ID FROM ROOM WHERE ROOM.ROOM_ID = ?1)", nativeQuery = true)
    String findComNameByRoomId(Integer roomId);

}
