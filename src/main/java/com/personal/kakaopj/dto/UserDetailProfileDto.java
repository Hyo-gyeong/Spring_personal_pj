package com.personal.kakaopj.dto;

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

    public UserDetailProfileDto(Long id, String name, ProfileDto profileDto, ProfilePlayListDto profilePlayList,
                                ArrayList<ProfileImgDto> profileImgList, ArrayList<ProfileBgImgDto> profileBgImgList,
                                ArrayList<ProfileMusicDto> profileMusicList){
        this.id = id;
        this.name = name;
        this.profilePlayList = profilePlayList;
        this.profileInfo = profileDto;
        this.profileImgList = profileImgList;
        this.profileBgImgList = profileBgImgList;
        this.profileMusicList = profileMusicList;
    }
}
