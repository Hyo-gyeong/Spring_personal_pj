package com.personal.kakaopj.profile.dto;

import com.personal.kakaopj.profile.domain.ProfileBgImg;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
public class ProfileBgImgDto {

    @PositiveOrZero
    @NotNull(message = "필수 항목")
    private Long id;

    @NotNull(message = "필수 항목")
    @NotBlank(message = "빈칸 입력 불가")
    private String imgAddress;

    @NotNull(message = "필수 항목")
    private boolean isMain;

    @NotNull(message = "필수 항목")
    private boolean isPrivate;

    public ProfileBgImgDto(ProfileBgImg profileBgImg){
        this.id = profileBgImg.getId();
        this.imgAddress = profileBgImg.getImgAddress();
        this.isMain = profileBgImg.isMain();
        this.isPrivate = profileBgImg.isPrivate();
    }
}
