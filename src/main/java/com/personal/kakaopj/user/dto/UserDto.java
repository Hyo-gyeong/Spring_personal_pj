package com.personal.kakaopj.user.dto;

import com.personal.kakaopj.user.domain.User;
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

    public UserDto(User user){
        this.id = user.getId();
        this.email = user.getEmail();
        this.isBirthdayHidden = user.getIsBirthdayHidden();
        this.kakaoId = user.getKakaoId();
        this.phoneNumber = user.getPhoneNumber();
        this.name = user.getName();
    }
}
