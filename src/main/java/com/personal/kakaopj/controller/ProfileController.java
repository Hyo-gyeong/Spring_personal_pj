package com.personal.kakaopj.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.personal.kakaopj.dto.ProfileBgImgDto;
import com.personal.kakaopj.dto.ProfileDto;
import com.personal.kakaopj.service.ProfileBgImgService;
import com.personal.kakaopj.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;

@RestController
@RequestMapping("/")
public class ProfileController {
    @Autowired
    private ProfileService profileService;
    public void setProfileService(ProfileService profileService) {
        this.profileService = profileService;
    }

    @Autowired
    private ProfileBgImgService profileBgImgService;

    public void setProfileBgImgService(ProfileBgImgService profileBgImgService) {
        this.profileBgImgService = profileBgImgService;
    }

    // 내 프로필 이름, 상태 메시지 수정
    @PatchMapping("my-profile/edit/{profileId}")
    public ProfileDto myProfileEdit(@PathVariable long profileId, @RequestParam String name,
                                    @RequestParam String message){
        profileService.editMyProfile(profileId, name, message);
        return profileService.findProfileById(profileId);
    }

    // 내 프로필 배경 이미지 수정 - 추가
    @PostMapping("my-profile/bg-img/create/{profileId}")
    public ArrayList<ProfileBgImgDto> myProfileBgImgAdd(@PathVariable long profileId, @RequestParam String bgImg) throws ParseException {
        return profileBgImgService.addProfileBgImg(profileId, bgImg);
    }

    // 내 프로필 배경 이미지 수정 - 삭제
    @DeleteMapping("my-profile/bg-img/delete/{bgImgId}")
    public void myProfileBgImgRemove(@PathVariable Long bgImgId){
        profileBgImgService.removeProfileBgImg(bgImgId);
    }
}
