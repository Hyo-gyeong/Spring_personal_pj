package com.personal.kakaopj.user.dto;

import com.personal.kakaopj.profile.dto.ProfileDto;
import com.personal.kakaopj.profile.dto.ProfileImgDto;
import com.personal.kakaopj.music.dto.ProfileMusicDto;
import com.personal.kakaopj.user.domain.User;
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

    public UserProfileDto(User user, ProfileDto profileDto, ProfileImgDto profileImg,
                          ProfileMusicDto music){
        this.id = user.getId();
        this.name = user.getName();
        this.profileDto = profileDto;
        this.profileImg = profileImg;
        this.music = music;
    }
}
