package com.personal.kakaopj.controller;

import com.personal.kakaopj.dto.*;
import com.personal.kakaopj.service.ProfileBgImgService;
import com.personal.kakaopj.service.ProfileImgService;
import com.personal.kakaopj.service.ProfileService;
import com.personal.kakaopj.service.UserService;
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

    @Autowired
    private ProfileImgService profileImgService;

    public void setProfileImgService(ProfileImgService profileImgService) {
        this.profileImgService = profileImgService;
    }

    @Autowired
    private UserService userService;
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    // (index 화면) 내 프로필 정보 조회 : 이름, 상태 메세지, 대표 프로필 사진, 대표 음악
    @GetMapping("me/{userId}")
    public UserProfileDto myProfile(@PathVariable long userId){
        return userService.getMyProfile(userId);
    }

    // 내 멀티프로필 리스트 정보 조회 : 이름, 상태 메세지, 대표 프로필 사진
    @GetMapping("multi-profile/list/{userId}")
    public ArrayList<UserMultiProfileDto> myMultiProfileList(@PathVariable long userId){
        return userService.getMyMultiProfileList(userId);
    }

    // 내 프로필 상세 정보 : 이름, 상태 메시지, 프로필 사진 목록, 프로필 배경 사진 목록, 플레이리스트 및 음악목록
    @GetMapping("me/detail/{userId}")
    public UserDetailProfileDto myDetailProfile(@PathVariable long userId){
        return userService.getMyDetailProfile(userId);
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

    // 내 프로필 이미지 수정 - 추가
    @PostMapping("my-profile/img/create/{profileId}")
    public ArrayList<ProfileImgDto> myProfileImgAdd(@PathVariable long profileId, @RequestParam String img) throws ParseException {
        return profileImgService.addProfileImg(profileId, img);
    }

    // 내 프로필 이미지 수정 - 삭제
    @DeleteMapping("my-profile/img/delete/{imgId}")
    public void myProfileImgRemove(@PathVariable Long imgId){
        profileImgService.removeProfileImg(imgId);
    }
}
