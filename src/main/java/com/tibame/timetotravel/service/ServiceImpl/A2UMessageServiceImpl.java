package com.tibame.timetotravel.service.ServiceImpl;

import com.tibame.timetotravel.common.PageBean;
import com.tibame.timetotravel.entity.A2UMessage;
import com.tibame.timetotravel.repository.A2UMessageRepository;
import com.tibame.timetotravel.repository.A2UMessageViewRepository;
import com.tibame.timetotravel.service.A2UMessageService;
import com.tibame.timetotravel.view.A2UMsgView;
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
    @Qualifier("A2UMessageViewRepository")
    private A2UMessageViewRepository a2UMessageViewRepository;

    @Autowired
    private PageBean<A2UMessage> pageBean;

    @Autowired
    private PageBean<A2UMsgView> pageBeanView;

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
    public PageBean<A2UMessage> findAllByPageRowData(Integer currPage, Integer rows) {
        int start = (currPage - 1) * rows;
        int pageSize = (int)(Math.ceil(a2UMessageRepository.findAll().size()/(double)rows));
        pageBean.setRows(findByPage(start,rows));
        pageBean.setPageSize(Math.max(pageSize,1));
        return pageBean;
    }

    @Override
    public List<A2UMessage> getAll() {
        return a2UMessageRepository.findAll();
    }

    @Override
    public List<A2UMsgView> findViewByPage(Integer currPage, Integer row) {
        return a2UMessageViewRepository.findViewByPage(currPage, row);
    }

    @Override
    public List<A2UMsgView> getViewAll() {
        return a2UMessageViewRepository.findAll();
    }

    @Override
    public PageBean<A2UMsgView> findViewByPageRowData(Integer currPage, Integer rows) {
        int start = (currPage - 1) * rows;
        int pageSize = (int)(Math.ceil(getViewAll().size()/(double)rows));
        pageBeanView.setRows(findViewByPage(start,rows));
        pageBeanView.setPageSize(Math.max(pageSize,1));
        return pageBeanView;
    }

    @Override
    public Integer findViewByDateRange(String startDate, String endDate) {
        return a2UMessageViewRepository.findViewByDateRange(startDate, endDate);
    }

    @Override
    public List<A2UMsgView> findViewByDateRangePage(String startDate, String endDate, Integer currPage, Integer rows) {
        return a2UMessageViewRepository.findViewByDateRangePage(startDate, endDate, currPage, rows);
    }

    @Override
    public PageBean<A2UMsgView> findBeanPageViewByDateRange(String startDate, String endDate, Integer currPage, Integer rows) {
        int start = (currPage - 1) * rows;
        int pageSize = (int)(Math.ceil(findViewByDateRange(startDate, endDate)/(double)rows));
        pageBeanView.setRows(findViewByDateRangePage(startDate,endDate,start,rows));
        pageBeanView.setPageSize(Math.max(pageSize,1));
        return pageBeanView;
    }

    @Override
    public List<A2UMsgView> getNotifyMsgById(Integer userId, Integer startIndex, Integer endIndex) {
        return a2UMessageViewRepository.findA2UViewMsgByUserId(userId, startIndex, endIndex);
    }

}
