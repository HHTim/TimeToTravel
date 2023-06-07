package com.tibame.timetotravel.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

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

    @Column(name = "USER_AVATAR", columnDefinition = "LONGBLOB")
    @Lob
    private byte[] userAvatar;

    @Column(name = "USER_GENDER", nullable = false)
    private Boolean userGender;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "USER_BIRTHDATE", nullable = false)
    private Date userBirthDay;

    @Column(name = "USER_SIGN_DATETIME", nullable = false)
    private Timestamp userSignDatetime;

    @Column(name = "USER_EMAIL", nullable = false)
    private String userEmail;

    @Column(name = "USER_STATUS", nullable = false)
    private Boolean userStatus;

    @Column(name = "USER_NEWS_STATUS", nullable = false)
    private Integer userNewsStatus;
}
