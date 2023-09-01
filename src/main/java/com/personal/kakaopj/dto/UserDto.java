package com.personal.kakaopj.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private Long id;
    private String email;
    private String phoneNumber;
    private String name;
    private String kakaoId;
    private Boolean isBirthdayHidden;

    public UserDto(Long id, String email, String  phoneNumber, String name, String kakaoId, Boolean isBirthdayHidden){
        this.id = id;
        this.email = email;
        this.isBirthdayHidden = isBirthdayHidden;
        this.kakaoId = kakaoId;
        this.phoneNumber = phoneNumber;
        this.name = name;
    }
}
