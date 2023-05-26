package com.tibame.timetotravel.controller;

import com.tibame.timetotravel.entity.Company;
import com.tibame.timetotravel.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/CompanyController")
public class CompanyController {

    @Autowired
    @Qualifier("CompanyService")
    CompanyService companyService;

    @PostMapping("/com")
    public String insert(@RequestBody Company company){
        System.out.println("執行資料庫Insert");
        System.out.println("+++++++++++++++" + company);
        companyService.insert(company);
        return "執行資料庫的 Insert 操作";
    }

    @GetMapping("/all")
    public List<Company> findAll() {
        return companyService.findAll();
    }

}
