package com.tibame.timetotravel.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginUserDto {
    @NotBlank(message = "請填寫帳號")
    private String account;

    @NotBlank(message = "請填寫密碼")
    private String password;

    @NotBlank(message = "請填寫驗證碼")
    private String captcha;
}
