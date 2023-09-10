package com.personal.kakaopj.user.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Null;
import java.time.LocalDateTime;

@Getter
@Setter
public class UserSettingDto {
    private Long id;
    private String phoneNumber;
    private String kakaoId;
    private int isBirthdayHidden;
    @Nullable
    private LocalDateTime updateDateTime;
}
