package com.tibame.timetotravel.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CompanyDetailResponseDto {
    private String account;
    private String name;
    private String tax;
    private String manage;
    private String address;
    private String phone;
    private String email;
    private String avatar;
    private Date signDate;
}
