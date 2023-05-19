package com.tibame.timetotravel.service.ServiceImpl;

import com.tibame.timetotravel.entity.U2AMessage;
import com.tibame.timetotravel.service.U2AMessageService;
import com.tibame.timetotravel.repository.U2AMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service("U2AMessageService")
public class U2AMessageServiceImpl implements U2AMessageService {

    @Autowired
    @Qualifier("U2AMessageRepository")
    private U2AMessageRepository u2AMessageRepository;

    @Override
    @Transactional
    public void insert(U2AMessage u2AMessage) {
        u2AMessage.setMsgSendingTime(new Timestamp(System.currentTimeMillis()));
        u2AMessageRepository.save(u2AMessage);
    }

    @Override
    public List<U2AMessage> getALl() {
        return u2AMessageRepository.findAll();
    }
}
