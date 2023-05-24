package com.tibame.timetotravel.service.ServiceImpl;

import com.tibame.timetotravel.common.PageBean;
import com.tibame.timetotravel.entity.A2UMessage;
import com.tibame.timetotravel.repository.A2UMessageRepository;
import com.tibame.timetotravel.service.A2UMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service("A2UMessageService")
public class A2UMessageServiceImpl implements A2UMessageService {

    @Autowired
    @Qualifier("A2UMessageRepository")
    private A2UMessageRepository a2UMessageRepository;

    @Autowired
    private PageBean<A2UMessage> pageBean;

    @Transactional
    @Override
    public void insert(A2UMessage a2UMessage) {
        a2UMessage.setA2uSendingTime((new Timestamp(System.currentTimeMillis())));
        a2UMessageRepository.save(a2UMessage);
    }

    @Override
    public List<A2UMessage> findByPage(Integer currPage, Integer row) {
        return a2UMessageRepository.findByPage(currPage,row);
    }

    @Override
    public PageBean<A2UMessage> findAnnPageByRowData(Integer currPage, Integer rows) {
        int start = (currPage - 1) * rows;
        int pageSize = (int)(Math.ceil(a2UMessageRepository.findAll().size()/(double)rows));
        pageBean.setRows(findByPage(start,rows));
        pageBean.setPageSize(Math.max(pageSize,1));
        return pageBean;
    }

    @Override
    public List<A2UMessage> getALl() {
        return a2UMessageRepository.findAll();
    }
}
