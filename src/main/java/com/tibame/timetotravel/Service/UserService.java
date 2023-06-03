package com.tibame.timetotravel.service;

import com.tibame.timetotravel.common.PageBean;
import com.tibame.timetotravel.dto.*;
import com.tibame.timetotravel.entity.User;

import java.util.List;

public interface UserService {
    void insert(RegisterUserDto dto) throws Exception;

    int login(LoginUserDto dto) throws Exception;

    UserDetailResponseDto get(int userId) throws Exception;

    void modify(int userId, ModifyUserDto dto) throws Exception;

    void modify(int userId, ModifyUserPasswordDto dto) throws Exception;

    List<User> findByPage(Integer currPage, Integer rows);

    PageBean<User> findByPageRowData(Integer currPage, Integer rows);

    List<User> findStatusByPage(Integer status, Integer currPage, Integer rows);

    PageBean<User> findStatusByPageRowData(Integer status, Integer currPage, Integer rows);

    List<User> findKeywordByPage(String keyword, Integer currPage, Integer limit);

    PageBean<User> findKeywordByPageRowData(String keyword, Integer currPage, Integer rows);

    List<User> getAll();
}
