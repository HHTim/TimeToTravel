package com.tibame.timetotravel.service.ServiceImpl;

import com.tibame.timetotravel.common.PageBean;
import com.tibame.timetotravel.entity.A2CMessage;
import com.tibame.timetotravel.repository.A2CMessageRepository;
import com.tibame.timetotravel.repository.A2CMessageViewRepository;
import com.tibame.timetotravel.service.A2CMessageService;
import com.tibame.timetotravel.view.A2CMsgView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service("A2CMessageService")
public class A2CMessageServiceImpl implements A2CMessageService {

    @Autowired
    @Qualifier("A2CMessageRepository")
    private A2CMessageRepository a2CMessageRepository;

    @Autowired
    @Qualifier("A2CMessageViewRepository")
    private A2CMessageViewRepository a2CMessageViewRepository;

    @Autowired
    private PageBean<A2CMessage> pageBean;

    @Autowired
    private PageBean<A2CMsgView> pageBeanView;

    @Transactional
    @Override
    public void insert(A2CMessage a2CMessage) {
        a2CMessage.setA2cSendingTime((new Timestamp(System.currentTimeMillis())));
        a2CMessageRepository.save(a2CMessage);
    }

    @Override
    public List<A2CMessage> findByPage(Integer currPage, Integer row) {
        return a2CMessageRepository.findByPage(currPage,row);
    }

    @Override
    public PageBean<A2CMessage> findAllByPageRowData(Integer currPage, Integer rows) {
        int start = (currPage - 1) * rows;
        int pageSize = (int)(Math.ceil(a2CMessageRepository.findAll().size()/(double)rows));
        pageBean.setRows(findByPage(start,rows));
        pageBean.setPageSize(Math.max(pageSize,1));
        return pageBean;
    }

    @Override
    public List<A2CMessage> getAll() {
        return a2CMessageRepository.findAll();
    }

    @Override
    public List<A2CMsgView> findViewByPage(Integer currPage, Integer row) {
        return a2CMessageViewRepository.findViewByPage(currPage,row);
    }

    @Override
    public List<A2CMsgView> getViewAll() {
        return a2CMessageViewRepository.findAll();
    }

    @Override
    public PageBean<A2CMsgView> findViewByPageRowData(Integer currPage, Integer rows) {
        int start = (currPage - 1) * rows;
        int pageSize = (int)(Math.ceil(getViewAll().size()/(double)rows));
        pageBeanView.setRows(findViewByPage(start,rows));
        pageBeanView.setPageSize(Math.max(pageSize,1));
        return pageBeanView;
    }

    @Override
    public Integer findViewByDateRange(String startDate, String endDate) {
        return a2CMessageViewRepository.findViewByDateRange(startDate, endDate);
    }

    @Override
    public List<A2CMsgView> findViewByDateRangePage(String startDate, String endDate, Integer currPage, Integer rows) {
        return a2CMessageViewRepository.findViewByDateRangePage(startDate, endDate, currPage, rows);
    }

    @Override
    public PageBean<A2CMsgView> findBeanPageViewByDateRange(String startDate, String endDate, Integer currPage, Integer rows) {
        int start = (currPage - 1) * rows;
        int pageSize = (int)(Math.ceil(findViewByDateRange(startDate, endDate)/(double)rows));
        pageBeanView.setRows(findViewByDateRangePage(startDate, endDate, start, rows));
        pageBeanView.setPageSize(Math.max(pageSize,1));
        return pageBeanView;
    }

    @Override
    public List<A2CMsgView> getNotifyMsgById(Integer comId, Integer startIndex, Integer endIndex) {
        return a2CMessageViewRepository.findA2CViewMsgByComId(comId, startIndex, endIndex);
    }
}
