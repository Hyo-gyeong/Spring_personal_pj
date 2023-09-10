package com.personal.kakaopj.user.dto;

import com.personal.kakaopj.music.dto.ProfileMusicDto;
import com.personal.kakaopj.profile.dto.ProfileBgImgDto;
import com.personal.kakaopj.profile.dto.ProfileDto;
import com.personal.kakaopj.profile.dto.ProfileImgDto;
import com.personal.kakaopj.profile.dto.ProfilePlayListDto;
import com.personal.kakaopj.user.domain.User;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class UserDetailProfileDto {
    private Long id;
    private String name;
    private ProfileDto profileInfo;
    private ArrayList<ProfileImgDto> profileImgList;
    private ArrayList<ProfileBgImgDto> profileBgImgList;
    private ProfilePlayListDto profilePlayList;
    private ArrayList<ProfileMusicDto> profileMusicList;

    public UserDetailProfileDto(User user, ProfileDto profileDto, ProfilePlayListDto profilePlayList,
                                ArrayList<ProfileImgDto> profileImgList, ArrayList<ProfileBgImgDto> profileBgImgList,
                                ArrayList<ProfileMusicDto> profileMusicList){
        this.id = user.getId();
        this.name = user.getName();
        this.profilePlayList = profilePlayList;
        this.profileInfo = profileDto;
        this.profileImgList = profileImgList;
        this.profileBgImgList = profileBgImgList;
        this.profileMusicList = profileMusicList;
    }
}
