package com.tibame.timetotravel.service;

import com.tibame.timetotravel.dto.LoginUserDto;
import com.tibame.timetotravel.entity.Admin;

public interface AdminService {
    int login(LoginUserDto dto) throws Exception;

    Admin findByAdminId(Integer adminId);

    String updateNewsByAdminName(String adminName);

    String updateAdminNewsStatusByAccount(String account, Integer newsStatus);
}
