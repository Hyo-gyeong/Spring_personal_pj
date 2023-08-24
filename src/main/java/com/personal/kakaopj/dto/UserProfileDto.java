package com.personal.kakaopj.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;


@Getter
@Setter
public class UserProfileDto {
    private Long id;
    private String name;
    @Nullable
    private ProfileDto profileDto;
    @Nullable
    private ProfileImgDto profileImg;
    @Nullable
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
