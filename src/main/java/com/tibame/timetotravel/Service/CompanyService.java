package com.tibame.timetotravel.service;

import com.tibame.timetotravel.common.PageBean;
import com.tibame.timetotravel.dto.*;
import com.tibame.timetotravel.entity.Company;

import java.util.List;

public interface CompanyService {
    void insertRegisterCompany(RegisterCompanyDto dto) throws Exception;
    int login(LoginCompanyDto dto) throws Exception;

    CompanyDetailResponseDto get(int companyId) throws Exception;

    void modify(int companyId, ModifyCompanyDto dto) throws Exception;

    void modify(int companyId, ModifyCompanyPasswordDto dto) throws Exception;
    void deleteById(Integer comId);
    void updateById(Integer comId, Company company);

    String updateComapnyNewsStatusByAccount(String account, Integer newsStatus);
    Company findById(Integer comId);
    List<Company> findAll();

    void insert(Company company);

    String updateNewsByComName(String comName);

    String updateByPassword(String password, Integer id);

    String updateCompStatusByComName(String comName, Integer status);

    List<Company> findByPage(Integer currPage, Integer rows);
    PageBean<Company> findByPageRowData(Integer currPage, Integer rows);

    List<Company> findStatusByPage(Integer status, Integer currPage, Integer rows);

    PageBean<Company> findStatusByPageRowData(Integer status, Integer currPage, Integer rows);

    List<Company> findAccountKeywordByPage(String keyword, Integer currPage, Integer limit);

    PageBean<Company> findAccountKeywordByPageRowData(String keyword, Integer currPage, Integer rows);

    List<Company> findComNameKeywordByPage(String keyword, Integer currPage, Integer limit);

    PageBean<Company> findComNameKeywordByPageRowData(String keyword, Integer currPage, Integer rows);

    List<Company> findComManagerKeywordByPage(String keyword, Integer currPage, Integer limit);

    PageBean<Company> findComManagerKeywordByPageRowData(String keyword, Integer currPage, Integer rows);
}
