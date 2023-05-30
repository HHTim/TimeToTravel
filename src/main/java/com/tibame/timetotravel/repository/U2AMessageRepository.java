package com.tibame.timetotravel.repository;

import com.tibame.timetotravel.entity.U2AMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("U2AMessageRepository")
public interface U2AMessageRepository extends JpaRepository<U2AMessage,Integer> {

    @Query(value = "SELECT * FROM USER_TO_ADMIN ORDER BY U2A_SENDING_TIME DESC LIMIT ?1,?2",nativeQuery = true)
    List<U2AMessage> findByPage(Integer currPage, Integer rows);
}
