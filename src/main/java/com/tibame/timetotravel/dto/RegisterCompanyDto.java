package com.tibame.timetotravel.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterCompanyDto {
    @NotBlank(message = "請填寫公司行號")
    private String name;

    @NotBlank(message = "請填寫統一編號")
    private String tax;

    @NotBlank(message = "請填寫負責人姓名")
    private String manage;

    @NotBlank(message = "請填寫公司地址")
    private String address;

    @NotBlank(message = "請填寫電話號碼")
    private String phone;

    @NotBlank(message = "請填寫帳號")
    private String account;

    @NotBlank(message = "請填寫密碼")
    private String password;

    @NotBlank(message = "請填寫電子信箱")
    @Email(message = "請填寫正確的電子信箱格式")
    private String email;

    @NotNull
    private String avatar = "";
}
