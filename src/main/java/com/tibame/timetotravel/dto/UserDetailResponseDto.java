package com.tibame.timetotravel.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserDetailResponseDto {
    private String account;
    private String name;
    private String nickName;
    private String email;
    private String phone;
    private String avatar;
    private Date birthday;
    private boolean gender;
}
