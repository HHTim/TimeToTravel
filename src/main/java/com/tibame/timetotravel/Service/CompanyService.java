package com.tibame.timetotravel.service;

import com.tibame.timetotravel.common.PageBean;
import com.tibame.timetotravel.entity.Company;
import java.util.List;

public interface CompanyService {

    void insert(Company company);

    String updateByComName(String comName);

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

    List<Company> findAll();
}
