package com.personal.kakaopj.profile.dto;

import com.personal.kakaopj.profile.domain.ProfileImg;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileImgDto {
    private Long id;
    private String imgAddress;
    private boolean isMain;
    private boolean isPrivate;

    public ProfileImgDto(ProfileImg profileImg){
        this.id = profileImg.getId();
        this.imgAddress = profileImg.getImgAddress();
        this.isMain = profileImg.isMain();
        this.isPrivate = profileImg.isPrivate();
    }
}
