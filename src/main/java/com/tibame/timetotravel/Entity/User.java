package com.tibame.timetotravel.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID", nullable = false)
    private Integer userId;

    @Column(name = "USER_ACCOUNT", nullable = false)
    private String userAccount;

    @Column(name = "USER_PASSWORD", nullable = false)
    private String userPassword;

    @Column(name = "USER_NAME", nullable = false)
    private String userName;

    @Column(name = "USER_PHONE", nullable = false)
    private String userPhone;

    @Column(name = "USER_NICKNAME", nullable = false)
    private String userNickName;

    @Column(name = "USER_AVATAR")
    private byte[] userAvatar;

    @Column(name = "USER_GENDER", nullable = false)
    private Boolean userGender;

    @Column(name = "USER_BIRTHDATE", nullable = false)
    private Date userBirthDay;

    @Column(name = "USER_EMAIL", nullable = false)
    private String userEmail;

    @Column(name = "USER_STATUS", nullable = false)
    private Boolean userStatus;

    @Column(name = "USER_NEWS_STATUS", nullable = false)
    private Boolean userNewsStatus;
}
