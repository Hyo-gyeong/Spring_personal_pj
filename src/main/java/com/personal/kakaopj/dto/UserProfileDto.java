package com.personal.kakaopj.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserProfileDto {
    private Long id;
    private String name;
    private ProfileDto profileDto;
    private ProfileImgDto profileImg;
    private ProfileMusicDto music;

    public UserProfileDto(Long id, String name, ProfileDto profileDto, ProfileImgDto profileImg,
                          ProfileMusicDto music){
        this.id = id;
        this.name = name;
        this.profileDto = profileDto;
        this.profileImg = profileImg;
        this.music = music;
    }
}
