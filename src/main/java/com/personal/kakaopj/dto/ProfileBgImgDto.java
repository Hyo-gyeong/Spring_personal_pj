package com.personal.kakaopj.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileBgImgDto {
    private Long id;
    private String imgAddress;
    private boolean isMain;
    private boolean isPrivate;

    public ProfileBgImgDto(Long id, String imgAddress, boolean isMain, boolean isPrivate){
        this.id = id;
        this.imgAddress = imgAddress;
        this.isMain = isMain;
        this.isPrivate = isPrivate;
    }
}
