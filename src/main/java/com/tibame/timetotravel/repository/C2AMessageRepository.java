package com.tibame.timetotravel.repository;

import com.tibame.timetotravel.entity.C2AMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("C2AMessageRepository")
public interface C2AMessageRepository extends JpaRepository<C2AMessage,Integer> {

    @Query(value = "SELECT * FROM COM_TO_ADMIN ORDER BY C2A_SENDING_TIME DESC LIMIT ?1,?2",nativeQuery = true)
    List<C2AMessage> findByPage(Integer currPage, Integer rows);
}
