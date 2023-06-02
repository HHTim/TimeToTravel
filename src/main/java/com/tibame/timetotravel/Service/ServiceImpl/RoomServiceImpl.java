package com.tibame.timetotravel.service.ServiceImpl;

import com.tibame.timetotravel.common.PageBean;
import com.tibame.timetotravel.dto.RoomPagination;
import com.tibame.timetotravel.dto.RoomPaginationByStatus;
import com.tibame.timetotravel.entity.Room;
import com.tibame.timetotravel.repository.RoomRepository;
import com.tibame.timetotravel.service.RoomService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service("roomService")
public class RoomServiceImpl implements RoomService {
    @Autowired
    @Qualifier("roomRepository")
    private RoomRepository roomRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void insert(Room room) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        room.setRoomRelease(timestamp);
        roomRepository.save(room);
    }

    @Override
    @Transactional
    public void deleteById(Integer roomId) {
        roomRepository.deleteById(roomId);
    }

    @Override
    @Transactional
    public void update(Integer roomId, Room room) {
        // 把舊的roomId拖出來
        Room newRoom = entityManager.find(Room.class, roomId);
        System.out.println(newRoom.getRoomStatus());
        newRoom.setRoomStatus(room.getRoomStatus());
        entityManager.merge(newRoom);
    }

    public Room findById(Integer roomId) {
        return roomRepository.findById(roomId).orElse(null);
    }

    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    @Override
    public List<Room> findByKeyword(String keyword) {
        if (keyword != null && !("".equals(keyword))) {
//            System.out.println(keyword + "hahaha");
            return roomRepository.findByKeyword(keyword);
        } else {
            return null;
        }
    }

    @Override
    public List<Room> findByRoomType(String roomTypeValue) {
        return roomRepository.findByRoomType(roomTypeValue);
    }

    @Override
    public RoomPagination findByPage(Integer pageNumber) {
        List<Room> roomList = roomRepository.findByPage((pageNumber - 1) * 10);
        double total = roomRepository.count();
        double totalPage = Math.ceil(total / 10);
        return new RoomPagination(roomList, (int) totalPage);
    }

    @Override
    public RoomPaginationByStatus findByPageByStatus(Integer roomStatus, Integer pageNumber) {
        List<Room> roomListByStatus = roomRepository.RoomPaginationByStatus(roomStatus, (pageNumber - 1) * 10);
        double total = roomRepository.totalPageByStatus(roomStatus);
        double totalPage = Math.ceil(total / 10);
        return new RoomPaginationByStatus(roomListByStatus, (int) totalPage);
    }


}
