package com.tibame.timetotravel.repository;

import com.tibame.timetotravel.entity.A2UMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("A2UMessageRepository")
public interface A2UMessageRepository extends JpaRepository<A2UMessage,Integer> {

    @Query(value = "SELECT * FROM ADMIN_TO_USER ORDER BY A2U_SENDING_TIME DESC LIMIT ?1,?2",nativeQuery = true)
    List<A2UMessage> findByPage(Integer currPage, Integer rows);
}
