package com.personal.kakaopj.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;


@Getter
@Setter
public class UserMultiProfileDto {
    private Long profileId;
    @Nullable
    private ProfileDto profileDto;
    @Nullable
    private ProfileImgDto profileImg;

    public UserMultiProfileDto(Long id, ProfileDto profileDto, ProfileImgDto profileImg){
        this.profileId = id;
        this.profileDto = profileDto;
        this.profileImg = profileImg;
    }
}
