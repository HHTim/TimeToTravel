package com.tibame.timetotravel.service.ServiceImpl;

import com.tibame.timetotravel.common.PageBean;
import com.tibame.timetotravel.entity.Ann;
import com.tibame.timetotravel.repository.AdminAnnRepository;
import com.tibame.timetotravel.service.AdminAnnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service("adminAnnService")
public class AdminAnnServiceImpl implements AdminAnnService {
    @Autowired
    @Qualifier("adminAnnRepository")
    private AdminAnnRepository adminAnnRepository;

    @Autowired
    private PageBean<Ann> pageBean;

    @Override
    @Transactional
    public void insert(Ann ann) {
        ann.setAnnSendingTime(new Timestamp(System.currentTimeMillis()));
        adminAnnRepository.save(ann);
    }

    @Override
    @Transactional
    public String update(Integer annId, Ann ann) {
        ann.setAnnSendingTime(new Timestamp(System.currentTimeMillis()));
        adminAnnRepository.save(ann);
        return "執行資料庫的 Update 操作";
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        adminAnnRepository.deleteById(id);
    }

    @Override
    public List<Ann> findByPage(Integer currPage, Integer row) {
        return adminAnnRepository.findByPage(currPage,row);
    }

    @Override
    public List<Ann> findByDateRange(String startDate, String endDate, Integer currPage, Integer rows) {
        return adminAnnRepository.findByDateRange(startDate,endDate,currPage,rows);
    }

    public Integer findAllDateRange(String startDate, String endDate) {
        return adminAnnRepository.findAllDateRange(startDate,endDate);
    }

    @Override
    public List<Ann> findByKeyWords(String keyword, Integer currPage, Integer rows) {
        return adminAnnRepository.findByKeyWords(keyword,currPage,rows);
    }

    @Override
    public Integer findByAllKeyWords(String keyword) {
        return adminAnnRepository.findByAllKeyWords(keyword);
    }

    public PageBean<Ann> findAnnPageByRowData(Integer currPage, Integer rows) {
        int start = (currPage - 1) * rows;
        int pageSize = (int)(Math.ceil(adminAnnRepository.findAll().size()/(double)rows));
        pageBean.setRows(findByPage(start,rows));
        pageBean.setPageSize(Math.max(pageSize,1));
        return pageBean;
    }

    @Override
    public PageBean<Ann> findAnnPageByDateRange(String startDate, String endDate, Integer currPage, Integer rows) {
        int start = (currPage - 1) * rows;
        int pageSize;
        pageBean.setRows(findByDateRange(startDate, endDate, start, rows));
        pageSize = (int)(Math.ceil(findAllDateRange(startDate,endDate)/(double)rows));
        pageBean.setPageSize(Math.max(pageSize, 1));
        return pageBean;
    }

    @Override
    public PageBean<Ann> findAnnPageByKeyWords(String keyword, Integer currPage, Integer rows) {
        int start = (currPage - 1) * rows;
        int pageSize;
        pageBean.setRows(findByKeyWords(keyword,start, rows));
        pageSize = (int)(Math.ceil(findByAllKeyWords(keyword)/(double)rows));
        pageBean.setPageSize(Math.max(pageSize, 1));
        return pageBean;
    }

}
