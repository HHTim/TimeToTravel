package com.tibame.timetotravel.service.ServiceImpl;


import com.tibame.timetotravel.dto.LoginCompanyDto;
import com.tibame.timetotravel.dto.RegisterCompanyDto;
import com.tibame.timetotravel.entity.Company;
import com.tibame.timetotravel.entity.User;
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
        company.setComAvatar(dto.getAvatar());
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
