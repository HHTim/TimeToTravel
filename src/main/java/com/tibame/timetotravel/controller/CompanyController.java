package com.tibame.timetotravel.controller;

import com.google.code.kaptcha.Constants;
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

    @PostMapping("/company/register")
    public ResponseEntity insert(@RequestBody RegisterCompanyDto dto){
        Set<ConstraintViolation<RegisterCompanyDto>> validateSet = validator.validate(dto);
        if (!validateSet.isEmpty()) {
            return badRequest(validateSet);
        }

        try {
            companyService.insert(dto);
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
