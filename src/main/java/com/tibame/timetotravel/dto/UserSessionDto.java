package com.tibame.timetotravel.dto;

import com.tibame.timetotravel.entity.Admin;
import com.tibame.timetotravel.entity.Company;
import com.tibame.timetotravel.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserSessionDto {
    private User user;
    private Company company;
    private Admin admin;
    private String role;
    private String avatar;
}
