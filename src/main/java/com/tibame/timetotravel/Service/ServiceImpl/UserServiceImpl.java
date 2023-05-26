package com.tibame.timetotravel.service.ServiceImpl;

import com.tibame.timetotravel.common.PageBean;
import com.tibame.timetotravel.entity.User;
import com.tibame.timetotravel.repository.UserRepository;
import com.tibame.timetotravel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    @Qualifier("UserRepository")
    UserRepository userRepository;

    @Autowired
    PageBean<User> pageBean;

    @Override
    public void insert(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> findByPage(Integer currPage, Integer rows) {
        return userRepository.findByPage(currPage,rows);
    }

    @Override
    public PageBean<User> findByPageRowData(Integer currPage, Integer rows) {
        int start = (currPage - 1) * rows;
        int pageSize = (int)(Math.ceil(userRepository.findAll().size()/(double)rows));
        pageBean.setRows(findByPage(start,rows));
        pageBean.setPageSize(Math.max(pageSize,1));
        return pageBean;
    }

    @Override
    public List<User> findStatusByPage(Integer status, Integer currPage, Integer rows) {
        return userRepository.findStatusByPage(status, currPage, rows);
    }

    @Override
    public PageBean<User> findStatusByPageRowData(Integer status, Integer currPage, Integer rows) {
        int start = (currPage - 1) * rows;
        int pageSize = (int)(Math.ceil(userRepository.findAllStatus(status)/(double)rows));
        pageBean.setRows(findStatusByPage(status, start, rows));
        pageBean.setPageSize(Math.max(pageSize,1));
        return pageBean;
    }

    @Override
    public List<User> findKeywordByPage(String keyword, Integer currPage, Integer rows) {
        return userRepository.findKeywordByPage(keyword, currPage, rows);
    }

    @Override
    public PageBean<User> findKeywordByPageRowData(String keyword, Integer currPage, Integer rows) {
        int start = (currPage - 1) * rows;
        int pageSize = (int)(Math.ceil(userRepository.findAllByKeword(keyword)/(double)rows));
        pageBean.setRows(findKeywordByPage(keyword, start, rows));
        pageBean.setPageSize(Math.max(pageSize,1));
        return pageBean;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }
}
