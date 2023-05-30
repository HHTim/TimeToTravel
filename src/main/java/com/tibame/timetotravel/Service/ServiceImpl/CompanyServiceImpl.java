package com.tibame.timetotravel.service.ServiceImpl;


import com.tibame.timetotravel.common.PageBean;
import com.tibame.timetotravel.entity.Company;
import com.tibame.timetotravel.repository.CompanyRepository;
import com.tibame.timetotravel.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("CompanyService")
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    @Qualifier("CompanyRepository")
    CompanyRepository companyRepository;

    @Autowired
    PageBean<Company> pageBean;

    @Transactional
    @Override
    public void insert(Company company) {
        companyRepository.save(company);
    }

    @Transactional
    @Override
    public String updateByComName(String companyName) {
        System.out.println(companyName);
        Company company = companyRepository.findByComName(companyName);
        company.setComNewsStatus(1);
        companyRepository.save(company);
        return companyName;
    }

    @Transactional
    @Override
    public String updateCompStatusByComName(String comName, Integer status) {
        companyRepository.updateCompanyStatus(comName, status);
        return "更新Company: " + comName + "的Status成功";
    }

    @Override
    public List<Company> findByPage(Integer currPage, Integer rows) {
        return companyRepository.findByPage(currPage,rows);
    }

    @Override
    public PageBean<Company> findByPageRowData(Integer currPage, Integer rows) {
        int start = (currPage - 1) * rows;
        int pageSize = (int)(Math.ceil(companyRepository.findAll().size()/(double)rows));
        pageBean.setRows(findByPage(start,rows));
        pageBean.setPageSize(Math.max(pageSize,1));
        return pageBean;
    }

    @Override
    public List<Company> findStatusByPage(Integer status, Integer currPage, Integer rows) {
        return companyRepository.findStatusByPage(status, currPage, rows);
    }

    @Override
    public PageBean<Company> findStatusByPageRowData(Integer status, Integer currPage, Integer rows) {
        int start = (currPage - 1) * rows;
        int pageSize = (int)(Math.ceil(companyRepository.findAllStatus(status)/(double)rows));
        pageBean.setRows(findStatusByPage(status, start, rows));
        pageBean.setPageSize(Math.max(pageSize,1));
        return pageBean;
    }

    @Override
    public List<Company> findAccountKeywordByPage(String keyword, Integer currPage, Integer rows) {
        return companyRepository.findAccountKeywordByPage(keyword, currPage, rows);
    }

    @Override
    public PageBean<Company> findAccountKeywordByPageRowData(String keyword, Integer currPage, Integer rows) {
        int start = (currPage - 1) * rows;
        int pageSize = (int)(Math.ceil(companyRepository.findAllByAccountKeword(keyword)/(double)rows));
        pageBean.setRows(findAccountKeywordByPage(keyword, start, rows));
        pageBean.setPageSize(Math.max(pageSize,1));
        return pageBean;
    }

    @Override
    public List<Company> findComNameKeywordByPage(String keyword, Integer currPage, Integer rows) {
        return companyRepository.findComNameKeywordByPage(keyword, currPage, rows);
    }

    @Override
    public PageBean<Company> findComNameKeywordByPageRowData(String keyword, Integer currPage, Integer rows) {
        int start = (currPage - 1) * rows;
        int pageSize = (int)(Math.ceil(companyRepository.findAllByComNameKeword(keyword)/(double)rows));
        pageBean.setRows(findComNameKeywordByPage(keyword, start, rows));
        pageBean.setPageSize(Math.max(pageSize,1));
        return pageBean;
    }

    @Override
    public List<Company> findComManagerKeywordByPage(String keyword, Integer currPage, Integer rows) {
        return companyRepository.findComManagerKeywordByPage(keyword, currPage, rows);
    }

    @Override
    public PageBean<Company> findComManagerKeywordByPageRowData(String keyword, Integer currPage, Integer rows) {
        int start = (currPage - 1) * rows;
        int pageSize = (int)(Math.ceil(companyRepository.findAllByComManagerKeword(keyword)/(double)rows));
        pageBean.setRows(findComManagerKeywordByPage(keyword, start, rows));
        pageBean.setPageSize(Math.max(pageSize,1));
        return pageBean;
    }

    @Override
    public List<Company> findAll() {
        return companyRepository.findAll();
    }
}
