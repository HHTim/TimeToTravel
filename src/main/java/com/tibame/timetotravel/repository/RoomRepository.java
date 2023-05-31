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
    @Query(value = "SELECT * FROM ROOM ORDER BY ROOM_ID ASC LIMIT ?1, 10", nativeQuery = true)
    List<Room> findByPage(Integer pageNumber);


}
