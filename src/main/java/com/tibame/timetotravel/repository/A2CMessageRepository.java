package com.tibame.timetotravel.repository;

import com.tibame.timetotravel.entity.A2CMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("A2CMessageRepository")
public interface A2CMessageRepository extends JpaRepository<A2CMessage,Integer> {

    @Query(value = "SELECT * FROM ADMIN_TO_COM ORDER BY A2C_SENDING_TIME DESC LIMIT ?1,?2",nativeQuery = true)
    List<A2CMessage> findByPage(Integer currPage, Integer rows);
}
