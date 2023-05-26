package com.tibame.timetotravel.service;

import com.tibame.timetotravel.entity.Company;

import java.util.List;

public interface CompanyService {
    public void insert(Company company);
    public void deleteById(Integer comId);
    public void updateById(Integer comId, Company company);
    public Company findById(Integer comId);
    public List<Company> findAll();

}
