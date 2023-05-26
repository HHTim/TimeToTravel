package com.tibame.timetotravel.service.ServiceImpl;


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
    private CompanyRepository companyRepository;

    @Override
    @Transactional
    public void insert(Company company) {
        System.out.println("資料庫新增成功");
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
