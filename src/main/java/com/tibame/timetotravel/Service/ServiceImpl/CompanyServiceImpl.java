package com.tibame.timetotravel.service.ServiceImpl;

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
    private CompanyRepository companyRepository;

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
    public void insert(RegisterCompanyDto dto) throws Exception {
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
        company.setComNewsStatus(false);
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
    @Transactional
    public void deleteById(Integer comId) {
        System.out.println("資料庫刪除成功");
        companyRepository.deleteById(comId);
    }

    @Override
    @Transactional
    public void updateById(Integer comId, Company company) {
        if(companyRepository.findById(comId).orElse(null) != null) {
            System.out.println("資料庫更新成功");
            companyRepository.save(company);
        }
    }

    public Company findById(Integer comId) {
        return companyRepository.findById(comId).orElse(null);
    }

    public List<Company> findAll() {
        return companyRepository.findAll();
    }

}
