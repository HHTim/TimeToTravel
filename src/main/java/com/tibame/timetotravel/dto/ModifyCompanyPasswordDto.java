package com.tibame.timetotravel.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModifyCompanyPasswordDto {
    @NotBlank(message = "請填寫原始密碼")
    private String originalPassword;

    @NotBlank(message = "請填寫新密碼")
    private String newPassword;
}
