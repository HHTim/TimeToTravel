package com.tibame.timetotravel.service.ServiceImpl;

import com.tibame.timetotravel.common.PageBean;
import com.tibame.timetotravel.entity.Ann;
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

    @Autowired
    private PageBean<U2AMessage> pageBean;

    @Override
    @Transactional
    public void insert(U2AMessage u2AMessage) {
        u2AMessage.setU2aMsgSendingTime(new Timestamp(System.currentTimeMillis()));
        u2AMessageRepository.save(u2AMessage);
    }

    @Override
    public List<U2AMessage> findByPage(Integer currPage, Integer row) {
        return u2AMessageRepository.findByPage(currPage,row);
    }

    @Override
    public PageBean<U2AMessage> findAnnPageByRowData(Integer currPage, Integer rows) {
        int start = (currPage - 1) * rows;
        int pageSize = (int)(Math.ceil(u2AMessageRepository.findAll().size()/(double)rows));
        pageBean.setRows(findByPage(start,rows));
        pageBean.setPageSize(Math.max(pageSize,1));
        return pageBean;
    }

    @Override
    public List<U2AMessage> getALl() {
        return u2AMessageRepository.findAll();
    }
}
