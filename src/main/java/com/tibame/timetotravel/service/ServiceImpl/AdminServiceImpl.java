package com.tibame.timetotravel.service.ServiceImpl;

import com.tibame.timetotravel.dto.LoginUserDto;
import com.tibame.timetotravel.entity.Admin;
import com.tibame.timetotravel.entity.User;
import com.tibame.timetotravel.repository.AdminRepository;
import com.tibame.timetotravel.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service("AdminService")
public class AdminServiceImpl implements AdminService {
    @Autowired
    @Qualifier("AdminRepository")
    private AdminRepository adminRepository;


    @Override
    public int login(LoginUserDto dto) throws Exception {
        Admin admin = adminRepository.findByAdminAccount(dto.getAccount());
        if (admin == null) {
            throw new Exception("登入失敗");
        }
        return admin.getAdminId();
    }

    @Override
    public Admin findByAdminId(Integer adminId) {
        return adminRepository.findByAdminId(adminId);
    }
}
