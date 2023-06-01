package com.tibame.timetotravel.controller;

import com.tibame.timetotravel.common.PageBean;
import com.google.code.kaptcha.Constants;
import com.tibame.timetotravel.dto.LoginCompanyDto;
import com.tibame.timetotravel.dto.RegisterCompanyDto;
import com.tibame.timetotravel.entity.Company;
import com.tibame.timetotravel.service.CompanyService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/CompanyController")
public class CompanyController extends BaseController {

    @Autowired
    Validator validator;

    @Autowired
    @Qualifier("CompanyService")
    CompanyService companyService;

    @PostMapping("/company")
    public String insert(@RequestBody Company company) {
        companyService.insert(company);
        return "執行資料庫的 Insert 操作";
    }
    @PostMapping("/company/register")
    public ResponseEntity insertRegisterCompany(@RequestBody RegisterCompanyDto dto){
        Set<ConstraintViolation<RegisterCompanyDto>> validateSet = validator.validate(dto);
        if (!validateSet.isEmpty()) {
            return badRequest(validateSet);
        }

        try {
            companyService.insertRegisterCompany(dto);
        } catch (Exception e) {
            return badRequest(e.getMessage());
        }

        return ResponseEntity.ok("{}");
    }

    @PostMapping("/company/login")
    public ResponseEntity login(@RequestBody LoginCompanyDto dto, HttpServletRequest request){
        Set<ConstraintViolation<LoginCompanyDto>> validateSet = validator.validate(dto);
        if (!validateSet.isEmpty()) {
            return badRequest(validateSet);
        }

        if (!dto.getCaptcha().toLowerCase().equals(request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY))) {
            return badRequest("驗證碼錯誤");
        }

        try {
            int id = companyService.login(dto);
            request.getSession().setAttribute("company_id", id);
        } catch (Exception e) {
            return badRequest(e.getMessage());
        }

        return ResponseEntity.ok("{}");
    }

    @PatchMapping(value = "/company/status", consumes = "multipart/form-data")
    public String updateCompanyStatus(@RequestParam("comName") String comName,
                                   @RequestParam("status") String status){
        System.out.println("接收到的Company名稱為:"+comName);
        System.out.println("接收到的Company status為:"+status);

        return companyService.updateCompStatusByComName(comName, ("1".equals(status) ? 1 : 0));
    }

    @PatchMapping(value = "/company/password", consumes = "multipart/form-data")
    public String updateCompanyStatus(@RequestParam("comId") Integer comId,
                                      @RequestParam("password") String password){
        System.out.println("接收到的Company ID為:"+comId);
        System.out.println("接收到的Company password為:"+password);

        return companyService.updateByPassword(password, comId);
    }

    @GetMapping("/company/page/{currPage}/{rows}")
    public PageBean<Company> readByPage(@PathVariable Integer currPage, @PathVariable Integer rows){
        System.out.println("分頁搜尋");
        return companyService.findByPageRowData(currPage,rows);
    }

    @GetMapping("/company/page/status/{status}/{currPage}/{rows}")
    public PageBean<Company> readStatusByPage(@PathVariable Integer status, @PathVariable Integer currPage, @PathVariable Integer rows){
        System.out.println("狀態搜尋");
        return companyService.findStatusByPageRowData(status, currPage, rows);
    }

    @GetMapping("/company/page/{currPage}/{rows}/keywordAccount/{keyword}")
    public PageBean<Company> readByAccountKeyWords(@PathVariable Integer currPage, @PathVariable Integer rows, @PathVariable String keyword){
        System.out.println("Account 關鍵字搜尋");
        return companyService.findAccountKeywordByPageRowData(keyword, currPage, rows);
    }

    @GetMapping("/company/page/{currPage}/{rows}/keywordComName/{keyword}")
    public PageBean<Company> readByComNameKeyWords(@PathVariable Integer currPage, @PathVariable Integer rows, @PathVariable String keyword){
        System.out.println("ComName 關鍵字搜尋");
//        System.out.println("keyword:"+keyword);
        return companyService.findComNameKeywordByPageRowData(keyword, currPage, rows);
    }

    @GetMapping("/company/page/{currPage}/{rows}/keywordComManager/{keyword}")
    public PageBean<Company> readByComManagerKeyWords(@PathVariable Integer currPage, @PathVariable Integer rows, @PathVariable String keyword){
        System.out.println("ComManager 關鍵字搜尋");
        return companyService.findComManagerKeywordByPageRowData(keyword, currPage, rows);
    }

    @GetMapping("/all")
    public List<Company> findAll() {
        return companyService.findAll();
    }
}
