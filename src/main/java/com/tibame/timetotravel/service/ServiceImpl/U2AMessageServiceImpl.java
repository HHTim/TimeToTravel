package com.tibame.timetotravel.service.ServiceImpl;

import com.tibame.timetotravel.common.PageBean;
import com.tibame.timetotravel.entity.U2AMessage;
import com.tibame.timetotravel.repository.U2AMessageViewRepository;
import com.tibame.timetotravel.service.U2AMessageService;
import com.tibame.timetotravel.repository.U2AMessageRepository;
import com.tibame.timetotravel.view.U2AMsgView;
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
    @Qualifier("U2AMessageViewRepository")
    U2AMessageViewRepository u2AMessageViewRepository;

    @Autowired
    private PageBean<U2AMessage> pageBean;

    @Autowired
    private PageBean<U2AMsgView> pageBeanView;

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
    public PageBean<U2AMessage> findMsgByPageRowData(Integer currPage, Integer rows) {
        int start = (currPage - 1) * rows;
        int pageSize = (int)(Math.ceil(u2AMessageRepository.findAll().size()/(double)rows));
        pageBean.setRows(findByPage(start,rows));
        pageBean.setPageSize(Math.max(pageSize,1));
        return pageBean;
    }

    public List<U2AMessage> getAll() {
        return u2AMessageRepository.findAll();
    }

    @Override
    public List<U2AMsgView> findViewByPage(Integer currPage, Integer row) {
        return u2AMessageViewRepository.findViewByPage(currPage, row);
    }

    @Override
    public List<U2AMsgView> getViewAll() {
        return u2AMessageViewRepository.findAll();
    }

    @Override
    public PageBean<U2AMsgView> findViewByPageRowData(Integer currPage, Integer rows) {
        int start = (currPage - 1) * rows;
        int pageSize = (int)(Math.ceil(getViewAll().size()/(double)rows));
        pageBeanView.setRows(findViewByPage(start,rows));
        pageBeanView.setPageSize(Math.max(pageSize,1));
        return pageBeanView;
    }

    @Override
    public Integer findViewByKeyWords(String keyword) {
        return u2AMessageViewRepository.findViewByKeyWords(keyword);
    }

    @Override
    public List<U2AMsgView> findViewByKeyWordsPage(String keyword, Integer currPage, Integer rows) {
        return u2AMessageViewRepository.findViewByKeyWordsPage(keyword, currPage, rows);
    }

    @Override
    public PageBean<U2AMsgView> findBeanPageViewByKeyWords(String keyword, Integer currPage, Integer rows) {
        int start = (currPage - 1) * rows;
        int pageSize = (int)(Math.ceil(findViewByKeyWords(keyword)/(double)rows));
        pageBeanView.setRows(findViewByKeyWordsPage(keyword,start,rows));
        pageBeanView.setPageSize(Math.max(pageSize,1));
        return pageBeanView;
    }

    @Override
    public Integer findViewByDateRange(String startDate, String endDate) {
        return u2AMessageViewRepository.findViewByDateRange(startDate, endDate);
    }

    @Override
    public List<U2AMsgView> findViewByDateRangePage(String startDate, String endDate, Integer currPage, Integer rows) {
        return u2AMessageViewRepository.findViewByDateRangePage(startDate, endDate, currPage, rows);
    }

    @Override
    public PageBean<U2AMsgView> findBeanPageViewByDateRange(String startDate, String endDate, Integer currPage, Integer rows) {
        int start = (currPage - 1) * rows;
        int pageSize = (int)(Math.ceil(findViewByDateRange(startDate, endDate)/(double)rows));
        pageBeanView.setRows(findViewByDateRangePage(startDate, endDate, start, rows));
        pageBeanView.setPageSize(Math.max(pageSize,1));
        return pageBeanView;
    }

    @Override
    public List<U2AMsgView> getNotifyMsgByUsers(Integer startIndex, Integer endIndex) {
        return u2AMessageViewRepository.findU2AViewMsgByUsers(startIndex, endIndex);
    }
}
