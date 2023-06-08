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

    @Query(value = "SELECT * FROM ROOM ORDER BY ROOM_ID DESC LIMIT ?1, 10", nativeQuery = true)
    List<Room> findByPage(Integer pageNumber);

    @Query(value = "SELECT * FROM ROOM WHERE ROOM_STATUS = ?1 ORDER BY ROOM_ID DESC LIMIT ?2, 10", nativeQuery = true)
    List<Room> RoomPaginationByStatus(Integer roomStatus, Integer pageNumber);

    @Query(value = "SELECT COUNT(*) FROM ROOM WHERE ROOM_STATUS = ?1 ORDER BY ROOM_ID DESC ", nativeQuery = true)
    Integer totalPageByStatus(Integer roomStatus);

    @Query(value = "SELECT COM_NAME FROM COMPANY WHERE COM_ID = (SELECT COM_ID FROM ROOM WHERE ROOM.ROOM_ID = ?1)", nativeQuery = true)
    String findComNameByRoomId(Integer roomId);

    @Query(value = "SELECT COM_ID FROM ROOM WHERE ROOM_ID = ?1", nativeQuery = true)
    Integer findComIdByRoomId(Integer roomId);
}
