package com.tibame.timetotravel.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class RegisterUserDto {
    @NotBlank(message = "請填寫姓名")
    private String name;

    private boolean gender;

    @NotNull(message = "請填寫生日")
    @Past(message = "請填寫正確的生日")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

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
