package com.personal.kakaopj.profile.controller;
import com.personal.kakaopj.config.exception.BaseResponse;
import com.personal.kakaopj.profile.dto.ProfileBgImgDto;
import com.personal.kakaopj.profile.dto.ProfileDto;
import com.personal.kakaopj.profile.dto.ProfileImgDto;
import com.personal.kakaopj.profile.service.ProfileBgImgService;
import com.personal.kakaopj.profile.service.ProfileImgService;
import com.personal.kakaopj.profile.service.ProfileService;
import com.personal.kakaopj.user.dto.UserDetailProfileDto;
import com.personal.kakaopj.user.dto.UserProfileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("my-profile/")
public class MyProfileController {
    @Autowired
    private ProfileService profileService;
    public void setProfileService(ProfileService profileService) {
        this.profileService = profileService;
    }

    @Autowired
    private ProfileBgImgService profileBgImgService;
    public void setProfileBgImgService(ProfileBgImgService profileBgImgService) { this.profileBgImgService = profileBgImgService; }

    @Autowired
    private ProfileImgService profileImgService;
    public void setProfileImgService(ProfileImgService profileImgService) { this.profileImgService = profileImgService; }

    // ###### 내 프로필 정보 조회 ######
    // index 화면 정보 조회 : 이름, 상태 메세지, 대표 프로필 사진, 대표 음악
    @PostMapping("{userId}")
    public BaseResponse<UserProfileDto> getMyProfile(@PathVariable long userId){
        UserProfileDto rslt = profileService.getMyProfile(userId);
        return BaseResponse.onSuccess(rslt);
    }

    // 상세 정보 조회 : 이름, 상태 메시지, 프로필 사진 목록, 프로필 배경 사진 목록, 플레이리스트 및 음악목록
    @PostMapping("detail/{userId}")
    public BaseResponse<UserDetailProfileDto> getMyDetailProfile(@PathVariable long userId){
        UserDetailProfileDto rslt = profileService.getMyDetailProfile(userId);
        return BaseResponse.onSuccess(rslt);
    }

    // ###### 내 프로필 정보 수정 ######
    // 내 프로필 이름, 상태 메시지 수정
    @PatchMapping("me/edit")
    public BaseResponse<?> editMyProfile(@RequestBody ProfileDto profileDto) throws Exception {
        profileService.editMyProfile(profileDto);
        return BaseResponse.onSuccess(null);
    }

    // 내 프로필 배경 이미지 수정 - 추가
    @PostMapping("bg-img/create/{profileId}")
    public BaseResponse<ArrayList<ProfileBgImgDto>> addMyProfileBgImg(@PathVariable long profileId, @RequestParam String bgImg) {
        ArrayList<ProfileBgImgDto> rslt = profileBgImgService.addProfileBgImg(profileId, bgImg);
        return BaseResponse.onSuccess(rslt);
    }

    // 내 프로필 배경 이미지 수정 - 삭제
    @DeleteMapping("bg-img/delete/{bgImgId}")
    public BaseResponse removeMyProfileBgImg(@PathVariable Long bgImgId) throws Exception {
        profileBgImgService.removeProfileBgImg(bgImgId);
        return BaseResponse.onSuccess(null);
    }

    // 내 프로필 이미지 수정 - 추가
    @PostMapping("img/create/{profileId}")
    public BaseResponse<ArrayList<ProfileImgDto>> addMyProfileImg(@PathVariable long profileId, @RequestParam String img) {
        ArrayList<ProfileImgDto> rslt = profileImgService.addProfileImg(profileId, img);
        return BaseResponse.onSuccess(rslt);
    }

    // 내 프로필 이미지 수정 - 삭제
    @DeleteMapping("img/delete/{imgId}")
    public BaseResponse removeMyProfileImg(@PathVariable Long imgId) throws Exception {
        profileImgService.removeProfileImg(imgId);
        return BaseResponse.onSuccess(null);
    }

    // 내 프로필 음악 수정
}