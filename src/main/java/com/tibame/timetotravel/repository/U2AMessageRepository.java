package com.tibame.timetotravel.repository;

import com.tibame.timetotravel.entity.U2AMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("U2AMessageRepository")
public interface U2AMessageRepository extends JpaRepository<U2AMessage,Integer> {

}
