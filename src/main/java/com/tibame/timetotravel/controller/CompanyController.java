package com.tibame.timetotravel.controller;

import com.google.code.kaptcha.Constants;
import com.tibame.timetotravel.common.PageBean;
import com.tibame.timetotravel.dto.*;
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
            Company company = companyService.findById(id);
            request.getSession().setAttribute("company_id", id);
            request.getSession().setAttribute("user", new UserSessionDto(null, company, null, "商家" ,new String(company.getComAvatar())));
//            System.out.println("CompanySession: " + request.getSession().getAttribute("user"));
        } catch (Exception e) {
            return badRequest(e.getMessage());
        }

        return ResponseEntity.ok("{}");
    }

    @GetMapping(value = "/company/{comId}")
    public Company getComById(@PathVariable("comId") Integer comId){
        System.out.println("接收到的comId為:"+comId);
        return companyService.findById(comId);
    }

    @PatchMapping(value = "/company/status", consumes = "multipart/form-data")
    public String updateCompanyStatus(@RequestParam("comName") String comName,
                                   @RequestParam("status") String status){
        System.out.println("接收到的Company名稱為:"+comName);
        System.out.println("接收到的Company status為:"+status);

        return companyService.updateCompStatusByComName(comName, ("1".equals(status) ? 1 : 0));
    }

    @PatchMapping(value = "/company/newsStatus", consumes = "multipart/form-data")
    public String updateUserNewsStatus(@RequestParam("account") String account,
                                       @RequestParam("newsStatus") Integer newsStatus){
        System.out.println("接收到的Company 帳號為:"+account);
        System.out.println("接收到的Company newsStatus為:"+newsStatus);

        return companyService.updateComapnyNewsStatusByAccount(account, newsStatus);
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
    public PageBean<Company> readByComManagerKeyWords(@PathVariable Integer currPage, @PathVariable Integer rows, @PathVariable String keyword) {
        System.out.println("ComManager 關鍵字搜尋");
        return companyService.findComManagerKeywordByPageRowData(keyword, currPage, rows);
    }

    @GetMapping("/company")
    public ResponseEntity detail(HttpServletRequest request){
        if (request.getSession().getAttribute("company_id") == null) {
            return unauthorized("尚未登入");
        }

        try {
            int id = (int) request.getSession().getAttribute("company_id");
            CompanyDetailResponseDto dto = companyService.get(id);

            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            return badRequest(e.getMessage());
        }
    }

    @PutMapping("/company")
    public ResponseEntity modify(@RequestBody ModifyCompanyDto dto, HttpServletRequest request){
        if (request.getSession().getAttribute("company_id") == null) {
            return unauthorized("尚未登入");
        }

        Set<ConstraintViolation<ModifyCompanyDto>> validateSet = validator.validate(dto);
        if (!validateSet.isEmpty()) {
            return badRequest(validateSet);
        }

        try {
            int id = (int) request.getSession().getAttribute("company_id");
            ((UserSessionDto) request.getSession().getAttribute("user")).setAvatar(dto.getAvatar());
            companyService.modify(id, dto);
        } catch (Exception e) {
            return badRequest(e.getMessage());
        }

        return ResponseEntity.ok("{}");
    }

    @PutMapping("/company/password")
    public ResponseEntity modifyPassword(@RequestBody ModifyCompanyPasswordDto dto, HttpServletRequest request){
        if (request.getSession().getAttribute("company_id") == null) {
            return unauthorized("尚未登入");
        }

        Set<ConstraintViolation<ModifyCompanyPasswordDto>> validateSet = validator.validate(dto);
        if (!validateSet.isEmpty()) {
            return badRequest(validateSet);
        }

        try {
            int id = (int) request.getSession().getAttribute("company_id");
            companyService.modify(id, dto);
        } catch (Exception e) {
            return badRequest(e.getMessage());
        }

        return ResponseEntity.ok("{}");
    }

    @GetMapping("/all")
    public List<Company> findAll() {
        return companyService.findAll();
    }
}
