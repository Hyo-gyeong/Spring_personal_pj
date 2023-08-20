package com.personal.kakaopj.dto;

import com.personal.kakaopj.domain.Profile;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
public class ProfileImgDto {
    private Long id;
    private String imgAddress;
    private boolean isMain;
    private boolean isPrivate;

    public ProfileImgDto(Long id, String imgAddress, boolean isMain, boolean isPrivate){
        this.id = id;
        this.imgAddress = imgAddress;
        this.isMain = isMain;
        this.isPrivate = isPrivate;
    }
}
