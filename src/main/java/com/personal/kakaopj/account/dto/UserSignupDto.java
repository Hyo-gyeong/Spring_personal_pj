package com.personal.kakaopj.account.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserSignupDto {

    private Long id;
    private String email;
    private String password;
    private String phoneNumber;
    private String name;
    private LocalDate birthday;
}
