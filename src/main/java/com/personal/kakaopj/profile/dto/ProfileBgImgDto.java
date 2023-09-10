package com.personal.kakaopj.profile.dto;

import com.personal.kakaopj.profile.domain.ProfileBgImg;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileBgImgDto {
    private Long id;
    private String imgAddress;
    private boolean isMain;
    private boolean isPrivate;

    public ProfileBgImgDto(ProfileBgImg profileBgImg){
        this.id = profileBgImg.getId();
        this.imgAddress = profileBgImg.getImgAddress();
        this.isMain = profileBgImg.isMain();
        this.isPrivate = profileBgImg.isPrivate();
    }
}
