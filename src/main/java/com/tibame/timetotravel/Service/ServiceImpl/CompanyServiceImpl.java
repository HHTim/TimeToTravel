package com.tibame.timetotravel.service.ServiceImpl;


import com.tibame.timetotravel.common.PageBean;
import com.tibame.timetotravel.dto.*;
import com.tibame.timetotravel.entity.Company;
import com.tibame.timetotravel.repository.CompanyRepository;
import com.tibame.timetotravel.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Timestamp;
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

    private String sha512(String input) {
        String toReturn = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            digest.reset();
            digest.update(input.getBytes("utf8"));
            toReturn = String.format("%0128x", new BigInteger(1, digest.digest()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return toReturn;
    }

    @Override
    public void insertRegisterCompany(RegisterCompanyDto dto) throws Exception {
        if (companyRepository.findByComAccount(dto.getAccount()) != null) {
            throw new Exception("該帳號已存在");
        }
        if (companyRepository.findByComTaxId(dto.getTax()) != null) {
            throw new Exception("該統一編號已存在");
        }

        Company company = new Company();
        company.setComAccount(dto.getAccount());
        company.setComPassword(sha512(dto.getPassword()));
        company.setComName(dto.getName());
        company.setComManager(dto.getManage());
        company.setComAddress(dto.getAddress());
        company.setComPhone(dto.getPhone());
        company.setComEmail(dto.getEmail());
        company.setComAvatar(dto.getAvatar().getBytes());
        company.setComTaxId(dto.getTax());

        company.setComSignDate(new Timestamp(System.currentTimeMillis()));
        company.setComStatus(1);
        company.setComNewsStatus(0);
        company.setComLatitude("");
        company.setComLongitude("");

        companyRepository.save(company);
    }

    @Override
    public int login(LoginCompanyDto dto) throws Exception {
        Company company = companyRepository.findByComAccount(dto.getAccount());
        if (company == null || !sha512(dto.getPassword()).equals(company.getComPassword())) {
            throw new Exception("登入失敗");
        }

        return company.getComId();
    }

    @Override
    public CompanyDetailResponseDto get(int companyId) throws Exception {
        Company company = companyRepository.findById(companyId).orElseThrow(() -> new Exception("廠商不存在"));

        CompanyDetailResponseDto dto = new CompanyDetailResponseDto();
        dto.setAccount(company.getComAccount());
        dto.setName(company.getComName());
        dto.setManage(company.getComManager());
        dto.setAddress(company.getComAddress());
        dto.setPhone(company.getComPhone());
        dto.setEmail(company.getComEmail());
        dto.setAvatar(new String(company.getComAvatar()));
        dto.setTax(company.getComTaxId());
        dto.setSignDate(company.getComSignDate());

        return dto;
    }

    @Override
    public void modify(int companyId, ModifyCompanyDto dto) throws Exception {
        Company company = companyRepository.findById(companyId).orElseThrow(() -> new Exception("廠商不存在"));
        company.setComName(dto.getName());
        company.setComManager(dto.getManage());
        company.setComAddress(dto.getAddress());
        company.setComPhone(dto.getPhone());
        company.setComEmail(dto.getEmail());
        company.setComAvatar(dto.getAvatar().getBytes());
        company.setComTaxId(dto.getTax());

        companyRepository.save(company);
    }

    @Override
    public void modify(int companyId, ModifyCompanyPasswordDto dto) throws Exception {
        Company company = companyRepository.findById(companyId).orElseThrow(() -> new Exception("廠商不存在"));
        if (!sha512(dto.getOriginalPassword()).equals(company.getComPassword())) {
            throw new Exception("原始密碼不一致");
        }

        company.setComPassword(sha512(dto.getNewPassword()));

        companyRepository.save(company);
    }

    @Override
    public void deleteById(Integer comId) {
        companyRepository.deleteById(comId);
        System.out.println("刪除comId: "+ comId);
    }

    @Override
    public void updateById(Integer comId, Company company) {
    }

    @Override
    public Company findById(Integer comId) {
        return companyRepository.findById(comId).orElse(null);
    }

    @Transactional
    @Override
    public String updateComapnyNewsStatusByAccount(String account, Integer newsStatus) {
        companyRepository.updateCompanyNewsStatus(account, newsStatus);
        return "更新Company: " + account + "的newsStatus成功";
    }

    @Transactional
    @Override
    public String updateNewsByComName(String companyName) {
        System.out.println(companyName);
        Company company = companyRepository.findByComName(companyName);
        company.setComNewsStatus(1);
        companyRepository.save(company);
        return companyName;
    }

    @Transactional
    @Override
    public String updateByPassword(String password, Integer id) {
        companyRepository.updateCompanyPassword(password, id);
        return "更新密碼成功";
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
