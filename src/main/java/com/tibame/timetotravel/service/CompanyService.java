package com.tibame.timetotravel.service;

import com.tibame.timetotravel.dto.*;
import com.tibame.timetotravel.entity.Company;

import java.util.List;

public interface CompanyService {
    void insert(RegisterCompanyDto dto) throws Exception;

    int login(LoginCompanyDto dto) throws Exception;

    CompanyDetailResponseDto get(int companyId) throws Exception;

    void modify(int companyId, ModifyCompanyDto dto) throws Exception;

    void modify(int companyId, ModifyCompanyPasswordDto dto) throws Exception;

    void deleteById(Integer comId);

    void updateById(Integer comId, Company company);

    Company findById(Integer comId);

    List<Company> findAll();
}
