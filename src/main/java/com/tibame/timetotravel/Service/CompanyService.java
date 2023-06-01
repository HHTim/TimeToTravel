package com.tibame.timetotravel.service;

import com.tibame.timetotravel.dto.LoginCompanyDto;
import com.tibame.timetotravel.dto.RegisterCompanyDto;
import com.tibame.timetotravel.entity.Company;

import java.util.List;

public interface CompanyService {
    void insert(RegisterCompanyDto dto) throws Exception;
    int login(LoginCompanyDto dto) throws Exception;
    void deleteById(Integer comId);
    void updateById(Integer comId, Company company);
    Company findById(Integer comId);
    List<Company> findAll();

}
