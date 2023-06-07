package com.tibame.timetotravel.service.ServiceImpl;

import com.tibame.timetotravel.common.PageBean;
import com.tibame.timetotravel.entity.C2AMessage;
import com.tibame.timetotravel.repository.C2AMessageRepository;
import com.tibame.timetotravel.repository.C2AMessageViewRepository;
import com.tibame.timetotravel.service.C2AMessageService;
import com.tibame.timetotravel.view.C2AMsgView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service("C2AMessageService")
public class C2AMessageServiceImpl implements C2AMessageService {

    @Autowired
    @Qualifier("C2AMessageRepository")
    private C2AMessageRepository c2AMessageRepository;

    @Autowired
    @Qualifier("C2AMessageViewRepository")
    C2AMessageViewRepository c2AMessageViewRepository;

    @Autowired
    private PageBean<C2AMessage> pageBean;

    @Autowired
    private PageBean<C2AMsgView> pageBeanView;

    @Override
    @Transactional
    public void insert(C2AMessage c2AMessage) {
        c2AMessage.setC2aSendingTime(new Timestamp(System.currentTimeMillis()));
        c2AMessageRepository.save(c2AMessage);
    }

    @Override
    public List<C2AMessage> findByPage(Integer currPage, Integer row) {
        return c2AMessageRepository.findByPage(currPage,row);
    }

    @Override
    public PageBean<C2AMessage> findMsgByPageRowData(Integer currPage, Integer rows) {
        int start = (currPage - 1) * rows;
        int pageSize = (int)(Math.ceil(c2AMessageRepository.findAll().size()/(double)rows));
        pageBean.setRows(findByPage(start,rows));
        pageBean.setPageSize(Math.max(pageSize,1));
        return pageBean;
    }

    public List<C2AMessage> getAll() {
        return c2AMessageRepository.findAll();
    }

    @Override
    public List<C2AMsgView> findViewByPage(Integer currPage, Integer row) {
        return c2AMessageViewRepository.findViewByPage(currPage, row);
    }

    @Override
    public List<C2AMsgView> getViewAll() {
        return c2AMessageViewRepository.findAll();
    }

    @Override
    public PageBean<C2AMsgView> findViewByPageRowData(Integer currPage, Integer rows) {
        int start = (currPage - 1) * rows;
        int pageSize = (int)(Math.ceil(getViewAll().size()/(double)rows));
        pageBeanView.setRows(findViewByPage(start,rows));
        pageBeanView.setPageSize(Math.max(pageSize,1));
        return pageBeanView;
    }

    @Override
    public Integer findViewByKeyWords(String keyword) {
        return c2AMessageViewRepository.findViewByKeyWords(keyword);
    }

    @Override
    public List<C2AMsgView> findViewByKeyWordsPage(String keyword, Integer currPage, Integer rows) {
        return c2AMessageViewRepository.findViewByKeyWordsPage(keyword, currPage, rows);
    }

    @Override
    public PageBean<C2AMsgView> findBeanPageViewByKeyWords(String keyword, Integer currPage, Integer rows) {
        int start = (currPage - 1) * rows;
        int pageSize = (int)(Math.ceil(findViewByKeyWords(keyword)/(double)rows));
        pageBeanView.setRows(findViewByKeyWordsPage(keyword,start,rows));
        pageBeanView.setPageSize(Math.max(pageSize,1));
        return pageBeanView;
    }

    @Override
    public Integer findViewByDateRange(String startDate, String endDate) {
        return c2AMessageViewRepository.findViewByDateRange(startDate, endDate);
    }

    @Override
    public List<C2AMsgView> findViewByDateRangePage(String startDate, String endDate , Integer currPage, Integer rows) {
        return c2AMessageViewRepository.findViewByDateRangePage(startDate, endDate, currPage, rows);
    }

    @Override
    public PageBean<C2AMsgView> findBeanPageViewByDateRange(String startDate, String endDate, Integer currPage, Integer rows) {
        int start = (currPage - 1) * rows;
        int pageSize = (int)(Math.ceil(findViewByDateRange(startDate, endDate)/(double)rows));
        pageBeanView.setRows(findViewByDateRangePage(startDate, endDate, start, rows));
        pageBeanView.setPageSize(Math.max(pageSize,1));
        return pageBeanView;
    }

    @Override
    public List<C2AMsgView> getNotifyMsgByComps(Integer startIndex, Integer endIndex) {
        return c2AMessageViewRepository.findC2AViewMsgByComps(startIndex, endIndex);
    }
}
